import torch
import matplotlib.pyplot as plt
import numpy as np

from util import grid

LEARNING_RATE = 1
EPOCHS = 10_000

x_train = torch.tensor([[0.0, 0.0], [1.0, 0.0], [0.0, 1.0], [1.0, 1.0]])
y_train = torch.tensor([[1.0], [1.0], [1.0], [0.0]])

# Nand operator
class nandSigmoidModel():

    def __init__(self):
        self.W = torch.tensor([[0.0], [0.0]], requires_grad=True)
        self.b = torch.tensor([[0.0]], requires_grad=True)

    def f(self, x):
        return torch.sigmoid(x @ self.W + self.b)

    def loss(self, x, y):
        return torch.nn.functional.binary_cross_entropy_with_logits(self.f(x), y)

model = nandSigmoidModel()

print("W = %s, b = %s, loss = %s" % (model.W, model.b, model.loss(x_train, y_train)))

optimizer = torch.optim.SGD([model.b, model.W], LEARNING_RATE)
for epoch in range(EPOCHS):
    model.loss(x_train, y_train).backward()
    optimizer.step()
    optimizer.zero_grad()
    
print("W = %s, b = %s, loss = %s" % (model.W, model.b, model.loss(x_train, y_train)))

fig = plt.figure().gca(projection='3d')

# show original data
fig.scatter(x_train[:, 0], x_train[:, 1], y_train, s=50, alpha=1, c="red", label="input")

# show "grid"
x1, y1, z1 = grid(model, steps=30)
fig.scatter(x1, y1, z1, c='blue', label="output")

fig.legend()
plt.show()