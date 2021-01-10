#include <string>
#include <iostream>
#include <vector>
#include <fstream>

#include "NewHashTable.cpp"

using namespace std;

vector<string> getNames();

int main()
{
	vector<string> names = getNames();

	HashTable ht( (int) names.size() );


	for (string name : names)
	{
		ht.addName(name);
	}

	string target = names[50];
	Node* fin = ht.find(target);
	
	ht.display();

	cout << "\nFind() = " << fin->name << endl;
	
	return 0;
}

vector<string> getNames()
{
	string filename = "Names.txt";
	vector<string> names;
	string line;

	ifstream myfile(filename);
	if (myfile.is_open())
	{
		while (getline(myfile, line))
		{
			names.push_back(line);
		}
		myfile.close();
	}
	else cout << "Unable to open file";

	return names;
}