# -*- coding: utf-8 -*-

import random, timeit

"""
finner pivot 

"""
def getPivot(table, low, high):
    # finner pivot verdien
    pivot = table[high]
    
    # venstre peker
    i = (low-1)
    
    # høyre peker
    for j in range(low, high):
        if table[j] <= pivot:
            
            # hvis venstre peker er mindre enn høyre, bytt verdier
            i += 1
            table[i], table[j] = table[j], table[i]
    
    # bytter pivot plassering
    table[i+1], table[high] = table[high], table[i+1]
    
    return i+1

"""
Hoved quicksort funksjonen.

"""
def quickSort(table, low, high):
    if low < high:
        
        # finner pivot indeksen
        pivotIndex = getPivot(table, low, high)
        
        quickSort(table, low, pivotIndex-1)
        quickSort(table, pivotIndex+1, high)

    return table

# sjekker om listen er sortert
def isSorted(table):
    for i in range(len(table)-1):
        if table[i] > table[i+1]:
            return False
    else:
        return True

# sjekksum
def checkSum(table):
    value = 0
    for num in table:
        value += num
    return value

# lager en tilfelding liste
def randomTable(n):
    table = []
    for i in range(n):
        table.append(random.randint(-20, 20))
    return table

#__________________________________________________________ MAIN

def main():
    
    # initialiserer listen
    table_length = 10000
    table = randomTable(table_length)
    print("Starting list\nUnsorted list")
    print(table)
    
    # sjekksummen før sortering
    valueBefore = checkSum(table)
    
    # sjekker først om listen er sortert
    if isSorted(table):
        print("List sorted")
    
    print("Sorting...")
    newTable = quickSort(table, 0, len(table)-1)
    print(newTable)
    
    # sjekker om listen faktisk er sortert
    if isSorted(table):
        print("List sorted")
    
    # sjekker om summen før og etter er lik
    valueAfter = checkSum(table)
    if valueBefore != valueAfter:
        print(table)
        print(newTable)
        exit("checkSum not matching before and after sorting")
    else:
        print("value before and after match")
        
    return    
        
if __name__ == '__main__':
    main()
    exit("Program finished")