class Element:
    columnLength = 12

    def __init__(self, atomic_number_, short_name_, full_name_, empirical_, calculated_, density_, atoms_per_volume_, atomic_mass_):
        # from datasett
        self.atomic_number =    atomic_number_
        self.short_name =       short_name_
        self.full_name =        full_name_
        self.empirical =        float(empirical_)
        self.calculated =       float(calculated_)
        self.density =          float(density_)
        self.atoms_per_volume = float(atoms_per_volume_) # number of atoms per volume unit
        self.atomic_mass =      float(atomic_mass_)

        # calculated
        self.calcDensity = 0.0
        self.densityCalcDifference = 0.0
        
        self.empDensity = 0.0
        self.densityEmpDifference = 0.0
        
        self.distance = 0.0
        self.zetta = 0.0
        
    def setZetta(self, zetta_):
        self.zetta = zetta_
        
    def setDistance(self, distance_):
        self.distance = distance_
                
    def setDensity(self, calcDensity_, empDensity_):
        self.calcDensity = calcDensity_
        self.empDensity = empDensity_
        
        self.densityCalcDifference = abs(self.density - self.calcDensity)
        self.densityEmpDifference = abs(self.density - self.empDensity)

    def getItems(self):
        precision = 4
        return [self.atomic_number, 
                self.full_name,
                self.short_name, 
                self.atomic_mass, 
                self.density, 
                round(self.calcDensity,precision), 
                round(self.densityCalcDifference,precision),
                round(self.empDensity,precision), 
                round(self.densityEmpDifference,precision), 
                round(self.distance,8), 
                round(self.zetta,precision)]

if __name__ == '__main__':
    import os
    os.system('python Assignment.py')