#include <iostream>
#include <vector>
#include <random>
#include <chrono>

using namespace std;

void printTable(vector<int> table);

/*
Oppgave 1

Bruker innsetting sortering som hjelpe algortme.

Tider:

Length = 0 | Time used = 3.2921 ms
Length = 10 | Time used = 3.0919 ms
Length = 20 | Time used = 4.6916 ms
Length = 30 | Time used = 3.1021 ms
Length = 100 | Time used = 2.128 ms
Length = 200 | Time used = 3.0749 ms
Length = 300 | Time used = 5.1312 ms
Length = 1000 | Time used = 7.8607 ms
Length = 2000 | Time used = 23.7027 ms
Length = 3000 | Time used = 12.8041 ms

*/

// __________________________________________________________________________________________________________________________ SWAP
void swap(int* a, int* b)
{
	int t = *a;
	*a = *b;
	*b = t;
}

// __________________________________________________________________________________________________________________________ INSERT SORT
/*
Insert sortering flytter kortet til venstre helt til det er mindre enn elementet til høyre
*/
void insertionSort(vector<int> &table, int low, int high)
{
	// n = length
	int n = high-low;
	int i, key, j;

	for (i = 1; i < n; i++)
	{
		key = table[low+i];
		j = i - 1;

		while (j >= 0 && table[low+j] > key)
		{
			table[low+j + 1] = table[low+j];
			j = j - 1;
		}
		table[low+j + 1] = key;
	}
}

// __________________________________________________________________________________________________________________________ PARTITIONS
int partitions(vector<int> &table, int low, int high)
{
	// finner pivot verdien
	int pivot = table[high];

	// venstre peker
	int left = (low - 1);

	// iterer gjennom høyre peker
	for (int i = low; i < high; i++) 
	{
		// hvis venstre peker er mindre enn høyre, bytt verdier, og øker pekeren
		if (table[i] <= pivot) 
		{
			left++;
			swap(&table[left], &table[i]);
		}
	}
	// bytter pivot plassering
	swap(&table[left+1], &table[high]);
	return (left + 1);
}

// __________________________________________________________________________________________________________________________ QUICKSORT
void quickSort(vector<int> &table, int low, int high, int length)
{
	if (high - low < length) {
		insertionSort(table, low, high);
		return;
	}
	
	if (low < high)
	{
		int pivot = partitions(table, low, high);

		quickSort(table, low, pivot - 1, length);
		quickSort(table, pivot + 1, high, length);
	}
}

// __________________________________________________________________________________________________________________________ IS SORTED
bool isSorted(vector<int> table)
{
	for (int i = 0; i < (int) table.size()-1; i++)
	{
		if (table[i] > table[i+1])
		{
			return false;
		}
	}
	return true;
}

// __________________________________________________________________________________________________________________________ CHECK SUM
int checkSum(vector<int> table)
{
	int sum = 0;
	for (int i : table)
	{
		sum += i;
	}
	return sum;
}

// __________________________________________________________________________________________________________________________ RANDOM TABLE
vector<int> randomTable(int n)
{
	vector<int> table;
	for (int i = 0; i < n; i++)
	{
		// range: -20 to 20
		int num = rand() % 40 - 20;
		table.push_back(num);
	}
	return table;
}

// __________________________________________________________________________________________________________________________ PRINT TABLE
void printTable(vector<int> table)
{
	for (int i : table)
	{
		cout << i << " ";
	}
	cout << endl << endl;
}

// __________________________________________________________________________________________________________________________ MAIN
int main()
{
	srand(time(0));
	vector<int> lengths = { 0, 10, 20, 30,
							100, 200, 300,
							1000, 2000, 3000};

	// create list with random values
	vector<int> original = randomTable(100000);
	//printTable(table);

	for (int length : lengths)
	{
		{
			// copy original table
			vector<int> table = original;

			// check if table is sorted
			if (isSorted(table)) {
				cout << "List is already sorted" << endl;
			}

			// get checkSum before sorting
			int valueBefore = checkSum(table);

			auto start = chrono::steady_clock::now();

			// sort
			quickSort(table, 0, table.size() - 1, length);
			
			// tid
			auto end = chrono::steady_clock::now();
			auto duration = chrono::duration <double, milli>(end - start).count();

			cout << "Length = " << length << " | Time used = " << duration << " ms" << endl;
			
			// get checkSum after sorting and check if matches with before value
			int valueAfter = checkSum(table);
			if (valueBefore != valueAfter) {
				cout << "Check sum value mismatch." << endl;
			}

			// check if table is sorted
			if (isSorted(table) == false) {
				cout << "List is not sorted" << endl;
				printTable(table);
			}
		}
	}

	return 0;
}