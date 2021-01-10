import torch
import numpy as np

class LinearRegressionModel:

    def __init__(self):
        # requires_grad enables calculation of gradients
        self.W = torch.tensor([[0.0]], requires_grad=True)
        self.b = torch.tensor([[0.0]], requires_grad=True)

    # Predictor
    def f(self, x):
        try:
            return x @ self.W + self.b
        except:
            print(x)
            exit('Failed in f(x)')

    # Uses Mean Squared Error
    def loss(self, x, y):
        return torch.nn.functional.mse_loss(self.f(x), y)

class LinearRegressionModel3D:

    def __init__(self):
        # requires_grad enables calculation of gradients
        self.W = torch.tensor([[0.0], [0.0]], requires_grad=True)
        self.b = torch.tensor([[0.0]], requires_grad=True)

    # Predictor
    def f(self, x):
        return x @ self.W + self.b

    # Uses Mean Squared Error
    def loss(self, x, y):
        #return torch.nn.functional.mse_loss(self.f(x) -y)
        return torch.mean(torch.square(self.f(x) - y))

class NonLinearRegressionModel:
    def __init__(self):
        # requires_grad enables calculation of gradients
        self.W = torch.tensor([[0.0]], requires_grad=True)
        self.b = torch.tensor([[0.0]], requires_grad=True)

    # Predictor
    def f(self, x):
        return 20*torch.sigmoid(x @ self.W + self.b) + 31

    # Uses Mean Squared Error
    def loss(self, x, y):
        return torch.mean(torch.square(self.f(x) - y))
