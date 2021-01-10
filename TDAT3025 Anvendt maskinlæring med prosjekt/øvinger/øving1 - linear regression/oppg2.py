import torch
from LinearRegressionModel import LinearRegressionModel3D

import numpy as np
import matplotlib
import matplotlib.pyplot as plt
from matplotlib import cm
from mpl_toolkits.mplot3d import axes3d, art3d

LEARNING_RATE = 0.0001
EPOCHS = 500_000

model = LinearRegressionModel3D()

days = []
lengths = []
weigths = []

with open('data/day_length_weight.csv') as f:
    content = f.read().split('\n')
    for i, line in enumerate(content):
        if i == 0 or line == '':
            continue
        day, length, weigth = map(float ,line.split(','))
        days.append(day)
        lengths.append(length)
        weigths.append(weigth)

x_train = torch.tensor([[length, weigth] for length, weigth in zip(lengths, weigths)])
y_train = torch.tensor(days).reshape(-1, 1)

def draw():
    fig = plt.figure('Linear regression: 3D')
    plot1 = fig.add_subplot(111, projection='3d')
    plot1.plot(x_train[:, 0].squeeze(),
           x_train[:, 1].squeeze(),
           y_train[:, 0].squeeze(),
           'o',
           label='$(\\hat x_1^{(i)}, \\hat x_2^{(i)},\\hat y^{(i)})$',
           color='blue')

    plot1_f = plot1.plot_wireframe(np.array([[]]), np.array([[]]), np.array([[]]), color='green', label='$y = f(x) = xW+b$')
    plot1_info = fig.text(0.01, 0.02, '')

    plot1.set_xlabel('$x_1$')
    plot1.set_ylabel('$x_2$')
    plot1.set_zlabel('$y$')
    plot1.legend(loc='upper left')
    plot1.set_xticks([])
    plot1.set_yticks([])
    plot1.set_zticks([])
    plot1.w_xaxis.line.set_lw(0)
    plot1.w_yaxis.line.set_lw(0)
    plot1.w_zaxis.line.set_lw(0)
    plot1.quiver([0], [0], [0], [torch.max(x_train[:, 0] + 1)], [0], [0], arrow_length_ratio=0.05, color='black')
    plot1.quiver([0], [0], [0], [0], [torch.max(x_train[:, 1] + 1)], [0], arrow_length_ratio=0.05, color='black')
    plot1.quiver([0], [0], [0], [0], [0], [torch.max(y_train[:, 0] + 1)], arrow_length_ratio=0.05, color='black')

    plot1_f.remove()
    x1_grid, x2_grid = np.meshgrid(np.linspace(1, 6, 10), np.linspace(1, 4.5, 10))
    y_grid = np.empty([10, 10])
    for i in range(0, x1_grid.shape[0]):
        for j in range(0, x1_grid.shape[1]):
            l = [x1_grid[i, j], x2_grid[i, j]]
            y_grid[i, j] = model.f(torch.FloatTensor(l))
    plot1_f = plot1.plot_wireframe(x1_grid, x2_grid, y_grid, color='green')

    plot1_info.set_text(
        '$W=\\left[\\stackrel{%.2f}{%.2f}\\right]$\n$b=[%.2f]$\n$loss = \\frac{1}{n}\\sum_{i=1}^{n}(f(\\hat x^{(i)}) - \\hat y^{(i)})^2 = %.2f$' %
        (model.W[0, 0], model.W[1, 0], model.b[0, 0], model.loss(x_train, y_train)))

    fig.canvas.draw()
    plt.show()

optimizer = torch.optim.SGD([model.b, model.W], LEARNING_RATE)
for epoch in range(EPOCHS):
    model.loss(x_train, y_train).backward()
    optimizer.step()
    optimizer.zero_grad()

print("W = %s\nb = %s\nloss = %s" % (model.W, model.b, model.loss(x_train, y_train)))

draw()