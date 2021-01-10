import torch
import torchvision
import matplotlib.pyplot as plt

def grid(model, lower=0, upper=1, steps=20):
    gx, gy = [], []
    mod = (upper - lower) / steps

    for i in [(x * mod) + lower for x in range(0, steps+1)]:
        for j in [(x * mod) + lower for x in range(0, steps+1)]:

            gx.append(i)
            gy.append(j)

    gz = model.f(torch.FloatTensor([[i, j] for i, j in zip(gx, gy)])).detach()
    return [gx, gy, gz]

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

def getMnistTrain(fashion = False):
    mnist_train = torchvision.datasets.MNIST('./data', train=True, download=True) if not fashion else torchvision.datasets.FashionMNIST('./data', train=True, download=True)
    x_train = mnist_train.data.reshape(-1, 784).float()  # Reshape input
    y_train = torch.zeros((mnist_train.targets.shape[0], 10))  # Create output tensor
    y_train[torch.arange(mnist_train.targets.shape[0]), mnist_train.targets] = 1  # Populate output
    
    return [x_train, y_train]

def getMnistTest(fashion = False):
    mnist_test = torchvision.datasets.MNIST('./data', train=True, download=True) if not fashion else torchvision.datasets.FashionMNIST('./data', train=True, download=True)
    x_test = mnist_test.data.reshape(-1, 784).float()
    y_test = torch.zeros((mnist_test.targets.shape[0], 10))
    y_test[torch.arange(mnist_test.targets.shape[0]),mnist_test.targets] = 1
    
    return [x_test, y_test]
