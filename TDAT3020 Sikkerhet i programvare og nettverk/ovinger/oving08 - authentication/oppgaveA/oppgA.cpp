#include <iomanip>
#include <iostream>
#include <openssl/evp.h>
#include <openssl/sha.h>
#include <string>
#include <stdlib.h>

using namespace std;

const string HASH = "ab29d7b5c589e18b52261ecba1d3a7e7cbf212c6";
const string SALT = "Saltet til Ola";

static string hex(const string &input) 
{
  stringstream hex_stream;
  hex_stream << hex << internal << setfill('0');
  for (auto &byte : input)
    hex_stream << setw(2) << (int)(unsigned char)byte;
  return hex_stream.str();
}

static string pbkdf2(
    const string &password, 
    const string &salt, 
    int iterations = 2048, 
    int key_length_in_bits = 160) 
{
  auto key_length_in_bytes = key_length_in_bits / 8;
  string key;
  key.resize(key_length_in_bytes);
  PKCS5_PBKDF2_HMAC_SHA1(password.data(), 
                         password.size(),
                         (const unsigned char *)salt.data(), 
                         salt.size(), 
                         iterations,
                         key_length_in_bytes, 
                         (unsigned char *)key.data());
  return hex(key);
}

void recursivePasswordCracker(char set[], string prefix, int n, int k) 
{ 
    if (k == 0) 
    { 
        string generated = pbkdf2(prefix, SALT);
        if(generated.compare(HASH) == 0)
        {
          cout << "found pass: " << endl << prefix << " + " << SALT << " = " << generated << endl;
          exit(0);
        }
        return; 
    } 
  
    for (int i=0; i<n; i++) 
    { 
        string newPrefix; 
        newPrefix = prefix + set[i]; 
        recursivePasswordCracker(set, newPrefix, n, k - 1); 
    } 
} 
  
void passwordCracker(char set[], int k,int n) 
{ 
    recursivePasswordCracker(set, "", n, k); 
} 

int main() 
{
  char set[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                '0','1','2','3','4','5','6','7','8','9'}; 

  for(int i=0; i<4; i++)
    passwordCracker(set, i, sizeof(set) / sizeof(set[0]));

  return 0;
}
