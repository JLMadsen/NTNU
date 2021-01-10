import torch
import torch.nn as nn
import torchvision
import matplotlib.pyplot as plt

from util import getFMnistTest, getFMnistTrain

import time

LEARNING_RATE = .001
EPOCHS = 1
BATCHES = 600

x_train, y_train = getFMnistTrain()
x_test, y_test = getFMnistTest()

# normaliser
mean = x_train.mean()
std = x_train.std()
x_train = (x_train - mean) / std
x_test = (x_test - mean) / std

# split batches
x_train_batches = torch.split(x_train, BATCHES)
y_train_batches = torch.split(y_train, BATCHES)

class ConvolutionalNeuralNetworkModel(nn.Module):
    def __init__(self):
        super(ConvolutionalNeuralNetworkModel, self).__init__()

        self.conv1 = nn.Conv2d(1, 32, kernel_size=5, padding=2)
        self.pool1 = nn.MaxPool2d(kernel_size=2)

        self.conv2 = nn.Conv2d(32, 64, kernel_size=5, padding=2)
        self.pool2 = nn.MaxPool2d(kernel_size=2)

        self.dense1 = nn.Linear(64*7*7, 1024)
        self.dense2 = nn.Linear(1024, 10)
        
        self.ReLU = nn.ReLU()

    def logits(self, x):

        x = self.conv1(x)
        x = self.pool1(x)
        x = self.ReLU(x)

        x = self.conv2(x)
        x = self.pool2(x)
        x = self.ReLU(x)

        x = self.dense1(x.reshape(-1, 64 * 7 * 7))
        x = self.dense2(x)
        return x

    # Predictor
    def f(self, x):
        return torch.softmax(self.logits(x), dim=1)

    # Cross Entropy loss
    def loss(self, x, y):
        return nn.functional.cross_entropy(self.logits(x), y.argmax(1))

    # Accuracy
    def accuracy(self, x, y):
        return torch.mean(torch.eq(self.f(x).argmax(1), y.argmax(1)).float())

model = ConvolutionalNeuralNetworkModel()

start = time.time()
optimizer = torch.optim.Adam(model.parameters(), LEARNING_RATE)
for epoch in range(EPOCHS):
    for batch in range(len(x_train_batches)):
        model.loss(x_train_batches[batch], y_train_batches[batch]).backward()
        optimizer.step()
        optimizer.zero_grad()

    print("accuracy = %s\ntime = %s" % (model.accuracy(x_test, y_test), time.time() - start))
    start = time.time()