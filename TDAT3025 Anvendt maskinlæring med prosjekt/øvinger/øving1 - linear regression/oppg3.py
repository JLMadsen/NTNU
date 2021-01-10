import torch
import matplotlib.pyplot as plt
from LinearRegressionModel import NonLinearRegressionModel

LEARNING_RATE = 0.000001
EPOCHS = 80_000

days = []
circums = []

with open('data/day_head_circumference.csv') as f:
    content = f.read().split('\n')
    for i, line in enumerate(content):
        if i == 0 or line == '':
            continue
        day, circ = map(float ,line.split(','))
        days.append(day)
        circums.append(circ)

y_train = torch.tensor(circums).reshape(-1, 1)
x_train = torch.tensor(days).reshape(-1, 1)

model = NonLinearRegressionModel()

print("W = %s, b = %s, loss = %s" % (model.W, model.b, model.loss(x_train, y_train)))

def draw(data = True):
    if (data):
        plt.plot(x_train, y_train, 'o', label='$(\\hat x^{(i)},\\hat y^{(i)})$')
    plt.xlabel('x')
    plt.ylabel('y')
    plt.plot(x_train, model.f(x_train).detach(), 'o')
    plt.legend()
    plt.show()

optimizer = torch.optim.SGD([model.b, model.W], LEARNING_RATE)
for epoch in range(EPOCHS):
    #print(model.loss(x_train, y_train))
    model.loss(x_train, y_train).backward()
    optimizer.step()
    optimizer.zero_grad()
    
print("W = %s, b = %s, loss = %s" % (model.W, model.b, model.loss(x_train, y_train)))
draw()
