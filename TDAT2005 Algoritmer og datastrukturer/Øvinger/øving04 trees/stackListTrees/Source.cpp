
/* ______________________________________________________ Node ______________________________________________________ */

struct node
{
	int data;
	node* next;
};

/* ______________________________________________________ LinkedList ______________________________________________________ */

class LinkedList {
private:
	node * head, * tail;

public:
	LinkedList()
	{
		// Initilaliserer en tom liste
		head = NULL;
		tail = NULL;
	}

	addNode(int n)
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
};