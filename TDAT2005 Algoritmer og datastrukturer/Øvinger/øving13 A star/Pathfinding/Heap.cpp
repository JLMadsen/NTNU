// A C++ program to demonstrate common Binary Heap Operations
#include <iostream>
#include <climits>
#include <vector>

#include "Structs.cpp"

#pragma once

using namespace std;


class MinHeap
{
	vector<Node*> harr;     // pointer to array of elements in heap
	int capacity;  // maximum possible size of min heap
	int heap_size; // Current number of elements in min heap
public:
	MinHeap(int cap)
	{
		heap_size = 0;
		capacity = cap;
		harr = vector<Node*>(cap);
	}

	inline int get_heap_size() { return heap_size; }

	inline void MinHeapify(int i)
	{
		int l = left(i);
		int r = right(i);
		int smallest = i;
		if (l < heap_size && harr[l]->tScore() < harr[i]->tScore())
			smallest = l;
		if (r < heap_size && harr[r]->tScore() < harr[smallest]->tScore())
			smallest = r;
		if (smallest != i)
		{
			Node* temp = harr[i];
			harr[i] = harr[smallest];
			harr[smallest] = temp;
			MinHeapify(smallest);
		}
	}

	inline int parent(int i) { return (i - 1) / 2; }

	inline int left(int i) { return (2 * i + 1); }

	inline int right(int i) { return (2 * i + 2); }

	inline Node* extractMin()
	{
		if (heap_size <= 0)
		{
			Node* temp = new Node();
			temp->score = -2;
		}
		if (heap_size == 1)
		{
			heap_size--;
			return harr[0];
		}

		Node* root = harr[0];
		harr[0] = harr[heap_size - 1];
		heap_size--;
		MinHeapify(0);

		return root;
	}

	inline void decreaseKey(int i, unsigned int new_val)
	{
		harr[i]->score = new_val;
		while (i != 0 && harr[parent(i)]->tScore() > harr[i]->tScore())
		{
			Node* temp = harr[i];
			harr[i] = harr[parent(i)];
			harr[parent(i)] = temp;
			i = parent(i);
		}
	}


	inline Node* getMin() { return harr[0]; }

	inline void deleteKey(int i)
	{
		decreaseKey(i, INT_MIN);
		extractMin();
	}

	inline void insertKey(Node* k)
	{
		if (heap_size == capacity)
		{
			cout << "\nOverflow: Could not insertKey\n";
			return;
		}

		heap_size++;
		int i = heap_size - 1;
		harr[i] = k;

		while (i != 0 && harr[parent(i)]->tScore() > harr[i]->tScore())
		{
			Node* temp = harr[i];
			harr[i] = harr[parent(i)];
			harr[parent(i)] = temp;
			i = parent(i);
		}
	}
};