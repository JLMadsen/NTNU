import time, timeit
from random import randint

# lager verdien for aksjene for hver dag
def getValues(changes):
    values = []

    for change in changes:
        previous_day = 0
        if values:
            previous_day = values[-1]
        values.append((previous_day + change))

    return values

# kjøper hver dag og selger alle dager som følger
def buyAll(values):
    highest = [0, 0, 0]
    
    for i in range(len(values)):
        buy = values[i]
        for j in range(i, len(values)):
            sell = values[j]
            profit = sell-buy
            if profit > highest[-1]:
                highest = [i+1, j+1, profit]
    
    return highest

def randomChanges(n):
    changes = []

    for i in range(n):
        changes.append(randint(-10, 10))

    return changes

if __name__ == '__main__':
    

    # eksempel fra boka
    changes = [-1, 3, -9, 2, 2, -1, 2, -1, -5]
  
    # hvor mange tilfeldige endringer man skal analysere
    dataSets = [500,
                1000,
                2000,
                4000,
                8000,
                16000]
    
    results = {}
    
    for data in dataSets:
        # ikke tell med initiliseringen
        changes = randomChanges(data)

        start = timeit.default_timer()
        
        values = getValues(changes)
        result = buyAll(values)

        end = timeit.default_timer()
        totalTime = end-start
        
        results[data] = totalTime
        
    for data, time in results.items():
        print(data, ":", time)
        
    times = list(results.values())[::-1]
    for i in range(len(times)-1):
        if times[i+1] == 0:
            break
        print(times[i]/times[i+1])
    
"""
Kompleksitet: n^2
En dobling i datasett gir firedobling i tid.
Det vil si at det er kvadratisk.

datasett : tid

500 :   0.0247
1000 :  0.0928
2000 :  0.3851
4000 :  1.2493
8000 :  5.6843
16000 : 19.044

økning i tid fra forrige

3.350
4.549
3.243
4.148
3.744

"""


