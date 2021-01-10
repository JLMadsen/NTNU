import re
import numpy as np

def decodeKCipher(message, k, alphabet):   
    return ''.join([alphabet[(alphabet.index(c)+k)%len(alphabet)] for c in re.sub(r' ', '', message)])

def encodeVignere(t,k,a):
    return ''.join([a[((a:=a.upper()).index(i)+a.index(j))%len(a)] for i,j in zip(((k*(int(len(t)/len(k))+1))[:len(t)]).upper(),re.sub(r' ','',t.upper()))]).upper()
    
def decodeVignere(t,k,a):
    return ''.join([a[((a:=a.upper()).index(i)-a.index(j))%len(a)] for i,j in zip(((k*(int(len(t)/len(k))+1))[:len(t)]).upper(),re.sub(r' ','',t.upper()))]).upper()

def decodeVignere2(text, key, alphabet):
    text, key, alphabet = text.upper(), key.upper(), alphabet.upper()
    key = (key*(int(len(text)/len(key))+1))[:len(text)]
    out = ''
    
    for a, b in zip(text, key):
        ind = alphabet.index(a) - alphabet.index(b)
        ind = ind % len(alphabet)
        out += alphabet[ind]

    return out

if __name__ == '__main__':
    from main import main
    main()