number = 2                      # starts at 2 because 0 and 1 cannot be prime numbers
primesfound = 0                 # assignment wanted 1000 first primes so i need a counter
while primesfound < 1000:
    for i in range(2,number):   # testing all the numbers from 2 up to but not including current number
        if (number % i) == 0:   # if the number is dividable then it is not a prime
            break               # divided, not prime
    else:                       # if not dividded by any number, PRINT!
        print(number) 
        primesfound += 1        # keep track 
    number += 1                 # NEXT NUMBER!