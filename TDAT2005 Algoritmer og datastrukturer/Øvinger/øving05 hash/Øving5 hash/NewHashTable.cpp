#include <string>
#include <vector>
#include <iostream>

using namespace std;

struct Node
{
	string name;
	Node* next;
};

class HashTable
{
private:
	int size;
	int elements;

	vector<Node*> table;
public:
	HashTable(int size_) : size(size_), elements(0)
	{
		Node* empty = new Node;
		empty->name = "";
		empty->next = NULL;
		for (int i = 0; i < size_; i++)
		{
			table.push_back(empty);
		}
	}

	int mod(string name)
	{
		int charSum = 0;
		for (int i = 0; i < name.size(); i++)
		{
			charSum += (i + 1) * (int)name[i];
		}
		int index = charSum % size;
		cout << name << " -> " << charSum << " -> " << index << endl;
		return index;
	}

	void addName(string name)
	{
		int index = mod(name);

		Node* temp = new Node;
		temp->name = name;
		temp->next = NULL;
		
		if (table[index]->name == "")
		{
			table[index] = temp;
		}
		else
		{
			cout << name << " kolliderer med " << table[index]->name << endl << endl;

			Node* ptr = table[index];
			while (ptr->next != NULL)
			{
				ptr = ptr->next;
			}
			if (ptr->next == NULL)
			{
				ptr->next = temp;
			}
		}
		//cout << index << " | " << name << endl;
		elements++;
	}

	void display()
	{
		cout << "------------------------------------- Display()" << endl;
		int coll = 0;
		for (int i = 0; i < size; i++)
		{
			cout << "Index " << i << endl;
			Node* ptr = table[i];

			while (true)
			{
				cout << ptr->name;

				if (ptr->next == NULL)
				{
					cout << endl;
					break;
				}
				else
				{
					cout << " | ";
					ptr = ptr->next;
					coll++;
				}
			}
		}
		cout << "\nKollisjoner per persjon = " << (double) coll / elements << "\nLast faktor = " << elements / size << endl;
	}

	Node* find(string name)
	{
		int index = mod(name);
		Node* ptr = table[index];
		while (true)
		{
			if (ptr->name == name)
			{
				return ptr;
			}
			else
			{
				if (ptr->next == NULL)
				{
					return NULL;
				}
				else
				{
					ptr = ptr->next;
				}
			}
		}
	}
};