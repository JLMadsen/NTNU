def gcd(a,b):
    for i in range(a,0,-1):
        if a % i == 0 and b % i == 0:
            return i

def main():
    a = 1188
    b = 385
    print(gcd(a,b))

main()