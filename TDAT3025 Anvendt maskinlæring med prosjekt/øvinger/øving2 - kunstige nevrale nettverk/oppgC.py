import torch
import matplotlib.pyplot as plt

from util import grid

LEARNING_RATE = 0.0001
EPOCHS = 10_000

x_train = torch.tensor([[0.0, 0.0], [1.0, 0.0], [0.0, 1.0], [1.0, 1.0]])
y_train = torch.tensor([[0.0], [1.0], [1.0], [0.0]])

# XOR operator
class xorSigmoidModel():

    def __init__(self):
        self.W1 = torch.tensor([[10.0, -10.0], [10.0, -10.0]], requires_grad=True)
        self.b1 = torch.tensor([[-5.0, 15.0]], requires_grad=True)
        self.W2 = torch.tensor([[10.0], [10.0]], requires_grad=True)
        self.b2 = torch.tensor([[-15.0]], requires_grad=True)

    def f1(self, x):
        return torch.sigmoid(x @ self.W1 + self.b1)

    def f2(self, x):
        return torch.sigmoid(x @ self.W2 + self.b2)

    def f(self, x):
        return self.f2(self.f1(x))

    def loss(self, x, y):
        return torch.nn.functional.binary_cross_entropy(self.f(x), y)

model = xorSigmoidModel()

#print(model.W1, model.W2, model.b1, model.b2, model.loss(x_train, y_train))

optimizer = torch.optim.SGD([model.b1, model.b2, model.W1, model.W2], LEARNING_RATE)
for epoch in range(EPOCHS):
    model.loss(x_train, y_train).backward()
    optimizer.step()
    optimizer.zero_grad()
    
print(model.W1, model.W2, model.b1, model.b2, model.loss(x_train, y_train))

fig = plt.figure().gca(projection='3d')

x, y, z = [], [], []

for i in x_train:
    x.append(i[0].detach())
    y.append(i[1].detach())

z = model.f(x_train).detach()

# show original data
fig.scatter(x, y, y_train.detach(), c='red', label="input")

# show "grid"
x1, y1, z1 = grid(model)
#fig.scatter(x1, y1, z1, c='blue')
fig.plot_surface(x1, y1, z1)

# show output of original x data
fig.scatter(x, y, z, c='blue', label="output")

fig.legend()
plt.show()
        