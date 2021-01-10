#include <string>
#include <vector>
#include <iostream>
#include <stdlib.h>
#include <assert.h>

using namespace std;

struct Node
{
	string name;
	int value;
	Node* next;

	Node()
	{
		name = "";
		value = 0;
		next = NULL;
	}

	Node(string name_, int value_, Node* next_)
	{
		name = name_;
		value = value_;
		next = next_;
	}
};

class HashTabell
{
private:
	int size;
	int elements;

	vector<Node*> table;

public:
	HashTabell(int size_) : size(size_), elements(0) 
	{
		for (int i = 0; i < size_; i++)
		{
			Node* empty = &Node();

			table.push_back(empty);
		}
	};

	int mod(string name)
	{
		int charSum = 0;
		int counter = 1;

		for (char c : name)
		{
			charSum += counter * (int) c;
			counter++;
		}

		int mod = charSum % size;

		return mod;
	}

	void addName(string name)
	{
		Node* tmp = &Node(name, 5, NULL);

		int index = mod(name);

		if (table[index]->name == "")
		{
			table[index] = tmp;
		}
		else
		{
			tmp->next = table[index];
			table[index] = tmp;
		}
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
				ptr = ptr->next;
			}
		}
	}

	void display()
	{
		for (int i = 0; i < table.size(); i++)
		{
			cout << "Index " << i << endl;
			Node* ptr = table[i];

			while (true)
			{
				//assert(ptr != NULL);
				cout << ptr->name << endl;

				if (ptr->next == NULL)
				{
					cout << endl;
					break;
				}

				ptr = ptr->next;
			}
		}
	}

	double lastFaktor()
	{
		return elements / size;
	}
};