#include <iostream>
#include <string>
#include <regex>

using namespace std;

string replace(string input)
{
    string newString;
    newString = regex_replace(input, regex("&"), "&amp;");
    newString = regex_replace(newString, regex("<"), "&lt;");
    newString = regex_replace(newString, regex(">"), "&gt;");
    return newString;
}

int main()
{
    string input;
    cout << "Enter text: "; 
    cin >> input;

    string replacedText = replace(input);

    cout << "output: " << replacedText << endl;
}