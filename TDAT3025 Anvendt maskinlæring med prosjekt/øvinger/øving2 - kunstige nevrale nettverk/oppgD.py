import torch
import torchvision
import matplotlib.pyplot as plt
import time
start_time = time.time()
from util import getMnistTrain, getMnistTest, showW

LEARNING_RATE = .1
EPOCHS = 500

class numberModel():

    def __init__(self):
        self.m = torch.nn.Softmax(dim=1)
        self.W = torch.ones((784, 10), requires_grad=True)
        self.b = torch.ones((1, 10), requires_grad=True)

    def f(self, x):
        return self.m(x @ self.W + self.b)

    def loss(self, x, y):
        return torch.nn.functional.cross_entropy(self.f(x), y.argmax(1))

    def accuracy(self, x, y):
        return torch.mean(torch.eq(self.f(x).argmax(1), y.argmax(1)).float())

model = numberModel()

x_train, y_train = getMnistTrain()
x_test, y_test = getMnistTest()

optimizer = torch.optim.SGD([model.b, model.W], LEARNING_RATE)
for epoch in range(EPOCHS):
    model.loss(x_train, y_train).backward()
    optimizer.step()
    optimizer.zero_grad()

print("\nloss = %s\naccuracy = %s" % (model.loss(x_train, y_train).detach().numpy(), model.accuracy(x_test, y_test).detach().numpy()))

print("time:", time.time() - start_time, '\n')

showW(model)
