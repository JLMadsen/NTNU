from Element import Element
import re

NUMREG = r'\d+\.?\d*'

def readContent(filename):
    datafile = open(filename)
    content = datafile.read().split('\n')
    datafile.close()
    return content

# safe casting, data sett contains text
def castFloat(data):
    try:
        return float(data)
    except:
        # if casting fails, extract number
        try:
            data = re.findall(NUMREG, data)
            return float(data[0])
        except:
            # if no numbers, return 0
            return 0.0

def readElements(elementFilename, periodicFilename, densityFilename):

    elementContent = readContent(elementFilename)
    densityContent = readContent(densityFilename)
    priodicContent = readContent(periodicFilename)

    elements = []

    for elementRow, periodicRow in zip(elementContent[1::], priodicContent[1::]):

        elementData = elementRow.split(',')
        densityData = []
        periodicData = periodicRow.split(',')
        
        atomic_number =    elementData[0]
        short_name =       elementData[1]
        full_name =        elementData[2]
        empirical =        castFloat(elementData[3])
        calculated =       castFloat(elementData[4])
        density =          0.0
        grams_per_cm3 =    0.0 # g/cm³
        atoms_per_volume = 0.0 # Zetta-atoms/cm³
        atomic_mass =      castFloat(periodicData[3])
        
        # density table is not sorted by atomic number...
        for densityRow in densityContent[1::]:
            
            densityData = densityRow.split(',')
            
            if densityData[4] == atomic_number:
        
                density =          castFloat(densityData[2]) 
                grams_per_cm3 =    castFloat(densityData[3]) # g/cm³
                atoms_per_volume = castFloat(densityData[4]) # Zetta-atoms/cm³
                
                break
        
        e = Element(atomic_number,
                    short_name,
                    full_name,
                    empirical,
                    calculated,
                    density,
                    atoms_per_volume,
                    atomic_mass)

        elements.append(e)

    return elements

if __name__ == '__main__':
    import os
    os.system('python Assignment.py')