import math

oldN = 3
oldS = 1

counter = 0
while counter < 25:
    n = oldN*2 # antall sider
    oldN = n
    s = oldS # lengden på sidene
    newS = math.sqrt(((1-math.sqrt(1-((s/2)**2)))**2)+((s/2)**2))
    oldS = newS
    print((n*s)/2)
    counter += 1

    