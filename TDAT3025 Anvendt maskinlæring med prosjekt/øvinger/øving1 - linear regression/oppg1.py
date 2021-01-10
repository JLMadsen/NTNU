import torch
import matplotlib.pyplot as plt
from LinearRegressionModel import LinearRegressionModel

LEARNING_RATE = 0.0001
EPOCHS = 120_000

model = LinearRegressionModel()

lengths = []
weigths = []

with open('data/length_weight.csv') as f:
    content = f.read()
    for i, line in enumerate(content.split('\n')):
        if i == 0 or line == '':
            continue
        length, weigth = map(float, line.split(','))
        lengths.append(length)
        weigths.append(weigth)

x_train = torch.tensor(lengths).reshape(-1, 1)
y_train = torch.tensor(weigths).reshape(-1, 1)

def draw(data = True):
    if (data):
        plt.plot(x_train, y_train, 'o', label='$(\\hat x^{(i)},\\hat y^{(i)})$')
    plt.xlabel('x')
    plt.ylabel('y')
    x = torch.tensor([[torch.min(x_train)], [torch.max(x_train)]])
    plt.plot(x, model.f(x).detach(), label='$y = f(x) = xW+b$')
    plt.legend()
    plt.show()

print("W = %s, b = %s, loss = %s" % (model.W, model.b, model.loss(x_train, y_train)))

optimizer = torch.optim.SGD([model.b, model.W], LEARNING_RATE)
for epoch in range(EPOCHS):
    model.loss(x_train, y_train).backward()
    optimizer.step()
    optimizer.zero_grad()
    
print("W = %s, b = %s, loss = %s" % (model.W, model.b, model.loss(x_train, y_train)))
draw()
