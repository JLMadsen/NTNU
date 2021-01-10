import sys, time

'''
Text for inginious

https://en.wikipedia.org/wiki/RSA_(cryptosystem)#Attacks_against_plain_RSA

1)
Started with the public key, the two numbers represent n and e.
Used the my program for an earlier assignment and got a list of primes, since both n and e are "low" numbers i know the primes arent that big.
When i factored the number 100127 i got the primes 449 and 223.
Since i got P and Q i can now calculate phi using the formula given in the rsa algorithm, this is 99456.
Using phi and the function muliplicative_inverse() from rsa.py i calculated d.
The private key, d, is 64327.
Using this key i could decrypt the message.

2)
Not using big enough primes, really easy to calculate using a list of primes and enough time.
Using low encryption exponent.

3)
No.
I was only able to find the p and q values 449 and 223.
'''

def multiplicative_inverse(a, b):
    x = 0
    y = 1
    lx = 1
    ly = 0
    oa = a  # Remember original a/b to remove
    ob = b  # negative values from return results
    while b != 0:
        q = a // b
        (a, b) = (b, a % b)
        (x, lx) = ((lx - (q * x)), x)
        (y, ly) = ((ly - (q * y)), y)
    if lx < 0:
        lx += ob  # If neg wrap modulo orignal b
    if ly < 0:
        ly += oa  # If neg wrap modulo orignal a
    return lx

def primefinder():
    result = []
    number = 2
    primesfound = 0
    while primesfound < 90:
        for i in range(2,number):
            if (number % i) == 0:
                break
        else:
            result.append(number)
            primesfound += 1
        number += 1
    return result

def factorfinder(primes, n):
    for p in primes:
        for q in primes:
            if p*q == n:
                factors = (p, q)
                break
    return factors

def bruteforce(e, phi, n, cipher):
    k = 1
    while True:
        d = int((k*phi)/e)
        char = chr(pow(84620, d, n))
        char2 = chr(pow(66174, d, n))
        print("|k "+ str(k)+"|", end='')
        if char == "h":
            print("h funnet")
            if char2 == "t":
                print("t funnet")
                print("K = "+ str(k))
                return k
        k += 1

# assignment
pk = (29815, 100127)
# test
#pk = (46297, 148613)

# assignment
cipher = [84620, 66174, 66174, 5926, 9175, 87925, 54744, 54744, 65916, 79243, 39613, 9932, 70186, 85020, 70186, 5926, 65916, 72060, 70186, 21706, 39613, 11245, 34694, 13934, 54744, 9932, 70186, 85020, 70186, 54744, 81444, 32170, 53121, 81327, 82327, 92023, 34694, 54896, 5926, 66174, 11245, 9175, 54896, 9175, 66174, 65916, 43579, 64029, 34496, 53121, 66174, 66174, 21706, 92023, 85020, 9175, 81327, 21706, 13934, 21706, 70186, 79243, 9175, 66174, 81327, 5926, 74450, 21706, 70186, 79243, 81327, 81444, 32170, 53121]
# test
#cipher = [23527, 40236, 112267, 22097, 112390, 93688, 93688]

n = pk[1]
e = pk[0]

primes = primefinder()

factors = factorfinder(primes, n)

print("factors = "+ str(factors))

phi = (factors[0]-1)*(factors[1]-1)
print("phi = "+ str(phi))

# invers mod
d = multiplicative_inverse(e, phi)
# brute force
#d = bruteforce(e, phi, n, cipher)

print("private key, d = "+ str(d))



text = ''
for thing in cipher:
    text += chr(pow(thing, d, n))

print("Cipher = "+ str(cipher))
print("Hacking . . .")
print("Plain text = "+ str(text))