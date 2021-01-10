"""
Chemistry assigment.

Done by Jakob and Pascal
"""

from readTables import readElements
from prettytable import PrettyTable
import math
import Element

AVOCADO = 6.022*10**23

def printElements(elements):
    headers = ['nr', 'name', 'symbol', 'mass', 'density', 'calc density', 'difference1', 'emp density', 'difference2', 'distance', 'zetta']
    
    table = PrettyTable(headers)
    
    for el in elements:
        table.add_row(el.getItems())
    
    print(table)

def weight(atomic_mass):
    return atomic_mass / AVOCADO

def volumeFromRadii(radius):
    return (4/3)*math.pi*(radius*10**(-10))**3

def volumeFromMass(atomic_mass, density_):
    try:
        return weight(atomic_mass)/density_
    except:
        return 0

def density(atomic_mass, radius):
    if(atomic_mass == 0 or radius == 0): return 0
    return weight(atomic_mass) / volumeFromRadii(radius)

def distance(mass, density_):
    return ((3*volumeFromMass(mass, density_))/4*math.pi)**(1/3)
    
def zettaPerVolume(mass, density_):
    try:
        return (1/volumeFromMass(mass, density_))/10**21
    except:
        return 0.0
    
def main():
    elementFilename = 'radiiTable.txt'
    densityFilename = 'densityTable.txt'
    periodicFilename = "periodicTable.txt"

    elements = readElements(elementFilename, periodicFilename, densityFilename)
    
    for element in elements:
        
        mass = element.atomic_mass
        density_ = element.density
        
        empRadius = element.empirical
        calcRadius = element.calculated
        
        element.setDensity(density(mass, calcRadius), density(mass, empRadius))
                
        dist = distance(mass, density_)
        element.setDistance(dist)
        
        zetta = zettaPerVolume(mass, density_)
        element.setZetta(zetta)

    printElements(elements)

if __name__ == '__main__':
    main()