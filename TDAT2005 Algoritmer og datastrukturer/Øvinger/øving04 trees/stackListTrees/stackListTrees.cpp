/*
Øving 4
*/

#include <iostream>
#include <vector>
#include "LinkedList.cpp"
#include <string>

using namespace std;

void oppg1();
void oppg2(string text);

int main()
{
	//oppg1();

	string text = "(([[{{}}]]))";
	oppg2(text);
	return 0;
}

/* ______________________________________________________ Oppgave 1 ______________________________________________________ */

struct node
{
	int data;
	node* next;
};

class LinkedList 
{
private:
	node* head, * tail;

public:
	LinkedList()
	{
		// Initilaliserer en tom liste
		head = NULL;
		tail = NULL;
	}

	void addNode(int n)
	{
		// lager en ny node med data
		node* tmp = new node;
		tmp->data = n;
		// Siden det er en sirkulær liste er next starten.
		tmp->next = head;

		if (head == NULL)
		{
			head = tmp;
			tail = tmp;
		}
		else
		{
			// setter forrige sluts next til nye slutt.
			tail->next = tmp;
			// setter ny slutt til nye noden
			tail = tail->next;
		}
	}
	// printer ut alle elementene i listen
	void display()
	{
		node* tmp;
		tmp = head;
		bool finished = false;
		while (tmp != NULL)
		{
			if (finished)
			{
				break;
			}
			cout << tmp->data << endl;
			tmp = tmp->next;
			if (tmp == head)
			{
				finished = true;
				continue;
			}
		}
	}

	node execute()
	{
		node* previous = tail;
		node* current = head;
		
		int counter = 1;
		while (true)
		{
			// hvis det bare er ett element igjen returner den.
			if (previous == current)
			{
				return *current;
			}
			// om den har nådd fjerde, drep, eller fjern fra liste
			if (counter == 4) 
			{
				counter = 1;
				//cout << "Dreper " << current->data << endl;
				node* tmp = current;
				current = previous;
				previous->next = tmp->next;
			}
			// iterer
			previous = current;
			current = current->next;
			counter++;
		}
	}
};

void oppg1()
{
	int romans = 40;
	cout << "Oppgave 1.\n" << romans << " romere, den som overlever er:" << endl;
	LinkedList list;

	for (int i = 0; i < romans; i++) 
	{
		list.addNode(i+1);
	}

	node safe = list.execute();
	cout << safe.data << endl;
}

/* ______________________________________________________ Oppgave 2 ______________________________________________________ */

void oppg2(string text) 
{
	const char* openings = "([{";
	const char* closings = ")]}";

	vector<char> open;

	for (char c : text)
	{
		
		for (char d : open)
		{
			cout << d << " ";
		}
		cout << endl;

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
					cout << "Lukker " << open.back() << " " << c << endl;
					open.pop_back();
				}
				else
				{
					cout << "Ikke gyldig." << endl;
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