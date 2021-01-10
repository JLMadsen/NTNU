#include <iostream>
#include <stdlib.h>

using namespace std;

int height = 20;

int main()
{
    srand(time(NULL));
	for(int i=0; i<height; i++)
    {
        if(i&1)
        {

            for(int j=0; j<(height-i)/2; j++)
                cout << " ";

            for(int j=0; j<i; j++)
            {
                if(i == 1)
                {
                    cout << "X";
                } else {
                    if(rand() % 10 < 2) {
                        if(rand() % 10 < 5) {
                            cout << "i";
                        } else {
                            cout << "o";
                        }
                    } else {
                        cout << "*";
                    }
                }
            }
            cout << endl;
        }
    }
    
    for(int i=0; i<(height/2)-2; i++)
        cout << " ";
    
    cout << "| |\n";
	return 0;
}
