import numpy as np
from collections import defaultdict

old = [1, 0]
new = [2, 0]

#print((np.array(old) - np.array(new))/[10, 1])

Q = defaultdict(lambda: [0 for _ in range(2)])

Q[(1, 2)][0] = 4
Q[(2, 1)][1] = 4

#for key, value in Q.items():
    #print(key, value)
    #print(key[0], key[1], value)
    #print(key[0], key[1], sum(value))

actions = [.1, .2, .3, .1]

print(actions)

print(np.argmax(actions))

#print(actions[-2:])