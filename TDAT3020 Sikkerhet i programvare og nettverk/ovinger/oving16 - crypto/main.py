import math
import string
import utils

alphabet = string.ascii_lowercase + 'æøå'

def task1():
    print('\nOppgave 1 A')
    [print(key, ':', math.pow(4, len(key)-1)) for key in ['1000', '0011', '1111']]      

    print('\nOppgave 1 B')
    [print(key, ':', math.pow(2, len(key)-1)) for key in ['1000', '0011', '1111']]  

def task2():
    print('\nOppgave 2 A')
    plain_text = 'goddag'
    k = 17
    cipher_text = utils.auto_encrypt(plain_text, alphabet[k])
    print(cipher_text)
    
    print('\nOppgave 2 B')
    cipher_text = ''.join([alphabet[i] for i in [23, 8, 23, 12, 21, 2, 4, 3, 17, 13, 19]])
    k = 5
    plain_text = utils.auto_decrypt(cipher_text, alphabet[k])
    print(plain_text)
    
def task3():
    K = '1001'
    ipad = '0011'
    opad = '0101'
    
    print('\nOppgave 3 A') # 11000100
    x = '0110'
    print(str(utils.hmac(*[x, K, ipad, opad]))[2:])
    
    print('\nOppgave 3 B')
    x = '0111'
    print(str(utils.hmac(*[x, K, ipad, opad]))[2:], '!= 00000100')
    
def task4():
    print('\nOppgave 4')
    x  = 0b_1101_1111_0101_0001
    x1 = 0b_0010_1100_0001_1111     
        
    print('x  :', utils.cbc_hmac(x))
    print('x1 :', utils.cbc_hmac(x1))
    
def task5():
    key = [0x67, 0x71, 0x35, 0xc4, 0xff, 0xda, 0xe5, 0xff, 0x1c, 0x54, 0xe1, 0xfd, 0x7f, 0x2e, 0x88, 0xb7]

    print('\nOppgave 5 A')
    plaintext_hex = [0x24, 0x59, 0x66, 0x0c, 0x99, 0xda, 0x9b, 0x00, 0xd6, 0x55, 0xfd, 0x20, 0xe9, 0xff, 0x46, 0x95]
    plaintext_str = ''.join([alphabet[int(x)%len(alphabet)] for x in plaintext_hex])
    
    print('ADDROUNDKEY :', (addrkey  := utils.add_round_key(plaintext_hex, key)))
    print('SUBBYTES    :', (subbytes := utils.sub_bytes(addrkey)))
    print('SHIFTROWS   :', (shift    := utils.shift_rows(subbytes)))
    
    print('\n',''.join([alphabet[c%len(alphabet)] for c in shift]))
    
    print('\nOppgave 5 B')
    cipher_text = [0x26, 0xFA, 0x83, 0xE7, 0x2D, 0xCD, 0x5D, 0xB8, 0xC4, 0xDC, 0xEB, 0x12, 0x70, 0xCF, 0xD6, 0x1E]
    
    print('ADDROUNDKEY :', (addrkey  := utils.add_round_key(cipher_text, key)))
    print('SUBBYTES    :', (subbytes := utils.sub_bytes(addrkey)))
    print('SHIFTROWS   :', (shift    := utils.shift_rows(subbytes)))
    
    print('\n', ''.join([alphabet[c%len(alphabet)] for c in shift]))
    
def task6():
    print('\nOppgave 6')
    

def main():
        
    task1()
    task2()
    task3()
    task4()
    task5()
    task6()

if __name__ == "__main__":
    main()