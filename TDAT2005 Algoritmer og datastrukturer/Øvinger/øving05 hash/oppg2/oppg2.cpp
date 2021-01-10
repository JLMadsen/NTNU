#include <iostream>
#include <vector>
#include <random>
#include <time.h>
#include <chrono>
#include <map>

#include "HashTable.cpp"

using namespace std;

vector<int> randomTable(int numbers);

int main()
{
	srand(time(0));
	cout << "oppg 2 started" << endl;
	int numbers = 5000000;

	/*
	— Hvis tabellstørrelsen m er en toerpotens:
	• h1 (k): multiplikativ hashfunksjon
	• h2 (k) = (2 |k| + 1) mod m
	• h2 produserer alltid oddetall, ingen felles faktor med 2x

	*/
	int max = 2;
	while (true)
	{
		if (max > numbers)
		{
			break;
		}
		max *= 2;
	}
	// 8388607
	cout << "size found = " << max << endl;

	// lager en tabell med unike random verdier
	vector<int> table(numbers, -1);
	for (int i = 0; i < numbers; i++)
	{
		table[i] = rand() % (max * 13);
	}

	// lager hashtable
	HashTable ht(max);

	auto t1 = chrono::high_resolution_clock::now();

	// setter inn
	for (int n : table)
	{
		ht.addNumbers(n);
	}

	auto t2 = chrono::high_resolution_clock::now();
	auto d1 = chrono::duration_cast<std::chrono::microseconds>(t2 - t1).count();

	map<int, int> C;

	auto t3 = chrono::high_resolution_clock::now();

	for (int i = 0; i < numbers; ++i) C[i] = table[i];

	auto t4 = chrono::high_resolution_clock::now();
	auto d2 = chrono::duration_cast<std::chrono::microseconds>(t4 - t2).count();

	cout << "Jakob: " << d1 << " mikrosekunder" << endl;
	cout << "C++: " << d2 << " mikrosekunder" << endl;
	cout << "Difference: " << abs(d2 - d1) << " mikrosekunder" << endl;

	return 0;
}


