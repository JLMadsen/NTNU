/*
Øving 4
*/

#include <iostream>
#include <vector>
#include "LinkedList.cpp"
#include <string>
#include <fstream>

using namespace std;

void oppg1();
void oppg2(string text);
void oppg3();
string readFile();

int main()
{
	// oppgave 1
	oppg1();


	// oppgave 2
	string test = "(([[{{}}]]))";
	string feil = "(((((((([))]))))";
	string klasse = readFile();
	string jan = "{([{([{  [  }])}])}";

	// velg hvilken string du vil teste, klasse er LinkedList.cpp
	oppg2(klasse);
	return 0;
}

/* ______________________________________________________ Oppgave 1 ______________________________________________________ */

void oppg1()
{
	int romans = 40;
	int interval = 4;

	cout << "Oppgave 1.\n" << romans << " romere, med intervallet: " << interval << ". den som overlever er:" << endl;
	Army list;

	for (int i = 0; i < romans; i++) 
	{
		list.addSoldier(i+1);
	}

	// første bool er for å printe ut hvem man dreper.
	node safe = list.execute(false, 4);

	node* head = list.begin();

	cout << safe.data << endl;
}

/* ______________________________________________________ Oppgave 2 ______________________________________________________ */

void oppg2(string text) 
{
	cout << "\nOppgave 2" << endl;

	const char* openings = "([{";
	const char* closings = ")]}";

	vector<char> open;

	for (char c : text)
	{
		for (int i = 0; i < (int) strlen(openings); i++)
		{
			if (c == openings[i])
			{
				open.push_back(c);
			}
		}

		for (int i = 0; i < (int) strlen(closings); i++)
		{
			// hvis det er en parantes som lukker
			if (c == closings[i])
			{
				if (open.back() == openings[i])
				{
					// Visualiser listen.
					for (char d : open)
					{
						cout << d << " ";
					}
					cout << "- " << c << endl;

					open.pop_back();
				}
				else
				{
					cout << "Ikke gyldig." << open.back() << " != " << c << endl;
					return;
				}
			}
		}
	}
	if (open.size() != 0) 
	{
		cout << "Ikke gyldig, ikke alle ble lukket" << endl;
		for (char d : open)
		{
			cout << d << " ";
		}
		cout << endl;
	}
	else
	{
		cout << "Gyldig!" << endl;
	}
}

string readFile() 
{
	string line;
	string output = "";
	ifstream myfile("LinkedList.cpp");
	if (myfile.is_open())
	{
		while (getline(myfile, line))
		{
			output += line;
		}
		myfile.close();
	}
	else cout << "Unable to open file";

	return output;
}