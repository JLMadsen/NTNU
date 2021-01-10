"""
Written by: Jakob Lønnerød Madsen
For TDAT2005 øving 11
"""

class RegularExpression:
    def __init__(self, alphabet, acceptedStates, nextStateTable) -> None:
        self.l = alphabet
        self.s = acceptedStates
        self.c = nextStateTable
    
    def checkInput(self, string) -> bool:
        
        if not string:
            return False
        
        state = 0
        neighbors = self.c[0]
        
        for foo in string:
            
            if foo not in self.l:
                return False
            
            index = self.l.index(foo)
            
            state = self.c[state][index]
            neighbors = self.c[state]
        
        if state in self.s:
            return True
        else:
            return False
        
        
def main() -> None:
    print("Oppgave 3, C, I")
    
    alphabet = [0,1]
    acceptedStates = [2]
    nextStateTable = [[1,3],[1,2],[2,3],[3,3]]
    
    regexr = RegularExpression(alphabet, acceptedStates, nextStateTable)
    
    print("e =", regexr.checkInput( [] ))
    print("010 =", regexr.checkInput( [0,1,0] )) 
    print("111 =", regexr.checkInput( [1,1,1] )) 
    print("010110 =", regexr.checkInput( [0,1,0,1,1,0] )) 
    print("001000 =", regexr.checkInput( [0,0,1,0,0,0] )) 
    
    print("\nOppgave 3, C, II")

    alphabet = ['a','b']
    acceptedStates = [3]
    nextStateTable = [[1,2],[4,3],[3,4],[3,3],[4,4]]
    
    regexr = RegularExpression(alphabet, acceptedStates, nextStateTable)
    
    print("abbb =", regexr.checkInput( ['a','b','b','b'] ))
    print("aaab =", regexr.checkInput( ['a','a','a','b'] )) 
    print("babab =", regexr.checkInput( ['b','a','b','a','b'] )) 
    
if __name__ == '__main__':
    main()