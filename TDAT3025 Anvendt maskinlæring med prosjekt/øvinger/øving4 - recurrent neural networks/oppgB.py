import torch
import torch.nn as nn

USE_RNN = False

class LongShortTermMemoryModel(nn.Module):
    def __init__(self, in_size, out_size):
        super(LongShortTermMemoryModel, self).__init__()
        print(in_size, out_size)
        self.lstm = nn.LSTM(in_size, 128) if not USE_RNN else nn.RNN(in_size, 128)
        self.dense = nn.Linear(128, out_size)

    def reset(self, batch_size):
        zero_state = torch.zeros(1, batch_size, 128)
        self.hidden_state = zero_state
        self.cell_state = zero_state

    def logits(self, x):
        if not USE_RNN:
            out, (self.hidden_state, self.cell_state) = self.lstm(x, (self.hidden_state, self.cell_state))
        else:
            out, self.hidden_state = self.lstm(x, self.hidden_state)
        return self.dense(out[-1].reshape(-1, 128)) # <--- added [:, -1, :]

    def f(self, x):
        return torch.softmax(self.logits(x), dim=1)

    def loss(self, x, y):
        return nn.functional.cross_entropy(self.logits(x), y.argmax(1))

def classifyLetters(letters):
    return [[0. if j!=i else 1. for j in range(len(letters))] for i, c in enumerate(letters)]

emojies = ['😎️',   '🐀️',   '🧢️',   '👶️',   '🎩️',   '🐈️',   '🏢️']
words   = ['matt', 'rat ', 'cap ', 'son ', 'hat ', 'cat ', 'flat']
chars   = [c for c in ''.join(set(''.join(words)))]

char_encodings  = classifyLetters(chars)
emoji_encodings = classifyLetters(emojies)

def encodeWord(word):
    return [char_encodings[chars.index(i)] for i in word]

x_train = torch.tensor([encodeWord(w) for w in words]).transpose(0, 1)
y_train = torch.tensor(emoji_encodings)

model = LongShortTermMemoryModel(len(chars), len(emojies))

optimizer = torch.optim.RMSprop(model.parameters(), 0.001)
for epoch in range(501):
    model.reset(7)
    model.loss(x_train, y_train).backward()
    optimizer.step()
    optimizer.zero_grad()

    if epoch % 125 == 0:

        print('_'*13, 'Epoch:', epoch)
        tests  = ['rt  ', 'rats', 'flat', 'caps', 'sn  ', 'cat ', 'cat ', 'fl  ', 'matt']
        tests2 = ['matt', 'rat ', 'cap ', 'son ', 'hat ', 'cat ', 'flat']

        for test in tests:

            model.reset(1)

            test_encoding = encodeWord(test)
            test_tensor = torch.tensor([test_encoding]).transpose(0, 1)
            y = model.f(test_tensor)
            index = y.argmax(1).numpy()[0]

            print(test, ' ', emojies[index], ' ', index)

while 1:
    model.reset(1)
    text = input('Enter word:')
    if text == '' or not text:
        exit(0)
    text += '    '
    text = text[:4]
    res = ''
    try:
        res = emojies[model.f(torch.tensor([encodeWord(text)]).transpose(0,1)).argmax(1).numpy()[0]]
    except:
        continue
    print('\n',text,'gives',res,'\n')