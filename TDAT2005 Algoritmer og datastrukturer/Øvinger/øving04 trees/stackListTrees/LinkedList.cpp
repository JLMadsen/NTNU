#include <iostream>

using namespace std;

/* ____________________________________________________ NODE ____________________________________________________ */

struct node
{
	int data;
	node* next;
};

/* ____________________________________________________ LinkedList ____________________________________________________ */

class Army
{
private:
	node* head, * tail;

public:
	Army()
	{
		// Initilaliserer en tom liste
		head = NULL;
		tail = NULL;
	}

	node* begin()
	{
		return head;
	}

	void addSoldier(int n)
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

	node execute(bool print, int interval)
	{
		node* previous = tail;
		node* current = head;

		int counter = 1;
		while (true)
		{
			// hvis det bare er ett element igjen returner den.
			if (previous == current)
			{
				head = current;
				tail = current;
				return *current;
			}
			// om den har nådd fjerde, drep, eller fjern fra liste
			if (counter == interval)
			{
				counter = 0;

				int killed = current->data;

				// unlink node
				previous->next = current->next;
				

				if (print)
				{
					cout << "Dreper: " << killed << endl;
				}
			}
			// iterer
			previous = current;
			current = current->next;
			counter++;
		}
	}
};