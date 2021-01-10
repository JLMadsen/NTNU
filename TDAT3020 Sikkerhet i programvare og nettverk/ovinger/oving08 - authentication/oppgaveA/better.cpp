#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int main()
{
    int length = 3;
    
    string set = "ABCDEFGH";
    
    string s = "";
    for(unsigned int i=0; i<set.length(); i++)
    {
        for(unsigned int j=0; j<length; j++)
            s.push_back(set.at(i));
    }

    string prev = "";
    do {
        string curr = s.substr(0, length);
        if(curr != prev)
        {
            cout << curr << endl;
            prev = curr;
        }
                
    }while(next_permutation(s.begin(), s.end()));

    return 0;
}
