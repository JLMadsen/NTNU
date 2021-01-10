import os, pandas

#________________________________________________________________________________ Classes
'''
A country has a name, fertility, starting fertility, and last recorded fertility.
The fertility is the last recorded fertility.
'''
class Country:
    
    def __init__(self, name, fertility):
        self.name = name
        self.fertility = fertility
        self.first = fertility
        self.last = fertility

    def setFertility(self, newFertility):
        self.fertility = newFertility

    def getFertility(self):
        return self.fertility

    def getName(self):
        return self.name

    def getTrend(self):
        return ( self.fertility - self.first )

    # format floats to only show 2 decimals, dont need more
    def __str__(self):
        return str(self.name) +", fertility "+ 	"{:.2f}".format(self.fertility) +", trend "+ "{:.2f}".format(self.getTrend())

'''
Class for statistics, it takes a list of countries
Init method also sorts it, high to low
'''
class Statistics:

    def __init__(self, countries):
        countries.sort(key=lambda x: x.getFertility(), reverse=True)
        self.countries = countries
        
    def getHighestFertility(self):
        return self.countries[0]

    def getLowestFertility(self):
        return self.countries[len(self.countries)-1]

    def getHighestTrend(self):
        newlist = self.countries
        newlist.sort(key=lambda x: x.getTrend(), reverse=True)
        return newlist[0]

    def getLowestTrend(self):
        newlist = self.countries
        newlist.sort(key=lambda x: x.getTrend(), reverse=False)
        return newlist[0]
#________________________________________________________________________________ Program methods
'''
Read function reads the excel file and creates new countries if they dont exists, and adds new data to already existing ones.
'''
def read(filename):

    source = pandas.read_excel(io = filename, sheet_name = "Fertility", skiprows=0)
    prev_country = None
    countries = []

    for i in source.index:
        if(i == 0): # skip header
            continue
        country_name = source['Country'][i]

        # some countries have entries that does not contain a fertility, if this casting fails it means no data, so skip.
        try:
            fertility = float(source['Total fertility'][i])
        except:
            continue

        if(prev_country != None): # if first country create new
            if(country_name == prev_country.getName()): # since countries are grouped i just need to check previous one
                prev_country.setFertility(fertility)
            else:
                prev_country = Country(country_name, fertility)
                countries.append(prev_country)
        else:
            prev_country = Country(country_name, fertility)
            countries.append(prev_country)

    return countries

'''
Method for finding right working directory, my computer seems to start in wrong dir.
'''
def change_dir():
    abspath = os.path.abspath(__file__)
    dname = os.path.dirname(abspath)
    os.chdir(dname)

'''
Main function
'''
def main():
    change_dir()
    countries = read("Fertility.xlsx")
    stats = Statistics(countries)

    print("Highest fertility:")
    print(stats.getHighestFertility())
    print("Lowest fertility:")
    print(stats.getLowestFertility())
    print("Highest Trend:")
    print(stats.getHighestTrend())
    print("Lowest Trend:")
    print(stats.getLowestTrend())

if __name__ == '__main__':
    main()
