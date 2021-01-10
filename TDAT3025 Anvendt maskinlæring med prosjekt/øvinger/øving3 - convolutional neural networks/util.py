import torch
import torch.nn as nn
import torchvision

def showW(model, save=False):
    h, w, c, r, x = 28, 28, 5, 2, 0
    
    fig=plt.figure(figsize=(8, 8))
    
    for i in range(1, c*r+1):
        img = model.W.detach().numpy()[:, x].reshape(28, 28)
        x+=1
        fig.add_subplot(r, c, i)
        plt.imshow(img)
        
    if(save):
        plt.savefig("imgs/oppgD.png")
        
    plt.show()    

def getMnistTrain():
    mnist_train = torchvision.datasets.MNIST('./data', train=True, download=True)
    x_train = mnist_train.data.reshape(-1, 1, 28, 28).float()  # torch.functional.nn.conv2d argument must include channels (1)
    y_train = torch.zeros((mnist_train.targets.shape[0], 10))  # Create output tensor
    y_train[torch.arange(mnist_train.targets.shape[0]), mnist_train.targets] = 1  # Populate output
    
    return [x_train, y_train]

def getMnistTest():
    mnist_test = torchvision.datasets.MNIST('./data', train=False, download=True)
    x_test = mnist_test.data.reshape(-1, 1, 28, 28).float()  # torch.functional.nn.conv2d argument must include channels (1)
    y_test = torch.zeros((mnist_test.targets.shape[0], 10))  # Create output tensor
    y_test[torch.arange(mnist_test.targets.shape[0]), mnist_test.targets] = 1  # Populate output
    
    return [x_test, y_test]

def getFMnistTrain():
    mnist_train = torchvision.datasets.FashionMNIST('./data', train=True, download=True)
    x_train = mnist_train.data.reshape(-1, 1, 28, 28).float()  # torch.functional.nn.conv2d argument must include channels (1)
    y_train = torch.zeros((mnist_train.targets.shape[0], 10))  # Create output tensor
    y_train[torch.arange(mnist_train.targets.shape[0]), mnist_train.targets] = 1  # Populate output
    
    return [x_train, y_train]

def getFMnistTest():
    mnist_test = torchvision.datasets.FashionMNIST('./data', train=False, download=True)
    x_test = mnist_test.data.reshape(-1, 1, 28, 28).float()  # torch.functional.nn.conv2d argument must include channels (1)
    y_test = torch.zeros((mnist_test.targets.shape[0], 10))  # Create output tensor
    y_test[torch.arange(mnist_test.targets.shape[0]), mnist_test.targets] = 1  # Populate output
    
    return [x_test, y_test]





