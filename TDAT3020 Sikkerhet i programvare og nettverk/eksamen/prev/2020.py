import string

alph = string.ascii_lowercase+'æøå'

K = 21

dec = "START"
enc = "ÆJVULO"

from utils import auto_encrypt, auto_decrypt

print( auto_encrypt(dec.lower(), alph[K]) )
print( auto_decrypt(enc.lower(), alph[K]) )
