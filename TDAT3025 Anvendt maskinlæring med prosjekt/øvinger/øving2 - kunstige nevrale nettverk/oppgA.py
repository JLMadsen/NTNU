import torch
import matplotlib.pyplot as plt
import numpy as np

LEARNING_RATE = 1
EPOCHS = 10_000

x_train = torch.tensor([[0.0], [1.0]])
y_train = torch.tensor([[1.0], [0.0]])

# not operator
class notSigmoidModel():

    def __init__(self):
        self.W = torch.tensor([[0.0]], requires_grad=True)
        self.b = torch.tensor([[0.0]], requires_grad=True)

    def f(self, x):
        return torch.sigmoid(x @ self.W + self.b)

    def loss(self, x, y):
        return torch.nn.functional.binary_cross_entropy_with_logits(self.f(x), y)

model = notSigmoidModel()

print("W = %s, b = %s, loss = %s" % (model.W, model.b, model.loss(x_train, y_train)))

optimizer = torch.optim.SGD([model.b, model.W], LEARNING_RATE)
for epoch in range(EPOCHS):
    model.loss(x_train, y_train).backward()
    optimizer.step()
    optimizer.zero_grad()
    
print("W = %s, b = %s, loss = %s" % (model.W, model.b, model.loss(x_train, y_train)))

trained_line = torch.linspace(0, 1, steps=20).reshape(-1, 1)

plt.plot(x_train, y_train, 'o', c="green", label="input")
plt.plot(trained_line, model.f(trained_line).detach(), color="red", label="output")
plt.legend()
plt.savefig('imgs/oppgA.png')
plt.show()
