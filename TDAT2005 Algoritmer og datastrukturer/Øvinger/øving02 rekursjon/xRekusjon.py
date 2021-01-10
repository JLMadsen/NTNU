# x^n = x*x^n-1
def rek(x,n):
    if n == 0:
        return 1
    return x*x**rek(x,n-1)

def main():
    x = 2
    n = 2
    print(rek(x,n))

if __name__ == '__main__':
    main()