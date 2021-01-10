import numpy as np
import copy
import math
from sympy import Matrix
from ciphers import decodeKCipher, decodeVignere, decodeVignere2, encodeVignere

alphabet = 'abcdefghijklmnopqrstuvwxyzæøå'

def task1():
    print('\nOppgave 1\n', ((232 + 22 * 77 - 18**2) % 8))
     
def modInverse(a, m) : 
    a = a % m; 
    for x in range(1, m) : 
        if ((a * x) % m == 1) : 
            return x 
    return 1

def egcd(a, b):
    if a == 0:
        return (b, 0, 1)
    else:
        g, y, x = egcd(b % a, a)
        return (g, x - (b // a) * y, y)

def modinv(a, m):
    g, x, y = egcd(a, m)
    if g != 1:
        raise Exception('modular inverse does not exist')
    else:
        return x % m

def task2():
    mod = 82
    Z = [[str((i*j)%mod).ljust(3,' ') for i in range(1, mod)] for j in range(1, mod)]
    
    print('\nOppgave 2 A')
    for row in Z:
        print(row)
        
    print('\nOppgave 2 B')
    
    invs = []
    for row in Z:
        for num in row:
            
            inv = modInverse(int(num), mod)
            if inv != 1 and inv not in invs:
                print(inv)
                invs.append(inv)
                
    print('\nOppgave 2 C')    
    print('Rader og kolonner som inneholder 1 er primtall, og alle som inneholder 0 er delelige.')
    
# har invertibel matrise over Zm dersom determinant er 1
def task3():
    
    print('\nOppgave 3 A')
    A = np.matrix([[2, -1], [5, 8]])
    mod = 10
    det = A[0, 0] * A[1, 1] - A[0, 1] * A[1, 0]
    
    if det % mod == 1:
        print(A.I % mod)
    else:
        print('no inverse')
    
    print('\nOppgave 3 B')
    A = np.matrix([[2, -1], [5, 8]])
    mod = 9
    det = A[0, 0] * A[1, 1] - A[0, 1] * A[1, 0]
    
    if det % mod == 1:
        print(A.I % mod)
    else:
        print('no inverse')
    
def task4():
    
    print('\nOppgave 4 A')
    print('29!,', math.factorial(29))
    print('\nOppgave 4 B')
    print('Kan kombinere med et annet chiffer, som f.eks: blokk chiffer eller skift chiffer')
    print('\nOppgave 4 C')
    print('n! * antall blokker')
    
def task5():
    
    encrypted = 'YÆVFB VBVFR ÅVBV'
    k = 12
    print('\nOppgave 5')
    print(decodeKCipher(encrypted, k, alphabet.upper())) # Hjernen er alene

def task6():
    print('\nOppgave 6')
    
    print('definisjon: x = x1x2 ... -> ek(x1)ek(x2) ...')
    print('nøkler: b * N!')
    
def task7():
    
    print('\nOppgave 7 A')
    
    text = 'Nå er det snart helg'
    key = 'torsk'
    print('encoded:', (cipher := encodeVignere(text, key, alphabet)))
    print('decoded:', decodeVignere2(cipher, key, alphabet))
    
    print('\nOppgave 7 B')
    
    text = 'QZQOBVCAFFKSDC'
    key = 'brus'    
    print('decoded:', decodeVignere2(text, key, alphabet))    
    
    print('\nOppgave 7 C')
    
    print('29 ^ 5')
    
def task8():
    
    print('\nOppgave 8 A')
    
    mod = 29
    K = np.matrix([[11, 8],[3, 7]])
    Ki = Matrix(K).inv_mod(mod)
    print(Ki)

    print('\nOppgave 8 B')
    
    x = [ alphabet.index(c) for c in 'prim']
    x = np.matrix([x[:2], x[2:]])
    y = (x*K) % mod
    print('encoded:', ''.join([alphabet[i] for arr in y.tolist() for i in arr]) )
        
    print('\nOppgave 8 C')
    
    y = [ alphabet.index(c) for c in 'toyysn']
    y = np.matrix([ y[:2], y[2:4], y[4:] ])
    x = (y*Ki) % mod
    print('decoded:',''.join([alphabet[a] for b in x.tolist() for a in b]))
    
    print('\nOppgave 8 D')
       
    x = [ alphabet.upper().index(c) for c in 'EASY']
    x =  np.matrix([x[:2], x[2:]])
    xi = Matrix(x).inv_mod(29)
    
    y = [ alphabet.upper().index(c) for c in 'IØÅY']
    y = np.matrix([y[:2], y[2:]])

    k = xi * y % 29
    ki = Matrix(k).inv_mod(29)
    
    print(k)
    
    print('encoded:',''.join([alphabet[a] for b in ((x*k) % 29).tolist() for a in b]))
    print('decoded:',''.join([alphabet[a] for b in ((y*ki) % 29).tolist() for a in b]))    

def main():
    
    task1() # fin
    task2() # fin
    task3() # fin
    task4() # fin
    task5() # fin
    task6() # fin
    task7() # fin
    task8() # fin

if __name__ == '__main__':
    main()