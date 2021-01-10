#include <vector>
#include <iostream>

using namespace std;

class HashTable
{
private:
	int size;
	int elements = 0;
	int coll = 0;
	const double a = (sqrt(5) - 1) / 2;
	vector<int> table;

public:
	HashTable(int size_)
	{
		size = size_;
		table = vector<int>(size, -1);
		cout << "Contructor finished" << endl;
	};

	int h1(int n)
	{
		return (int)floor(size * (n * a - floor(n * a)));
	}

	int h2(int n)
	{
		return (int)((2 * abs(n)) + 1) % size;
	}

	void addNumbers(int n)
	{
		int h_1 = h1(n);
		int h_2 = h2(n);
		int index = h_1;
		while (table[index] != -1)
		{
			index += h_2;
			index %= size;
		}
		table[index] = n;
	}

	void display()
	{
		for (int n : table)
		{
			cout << n << " ";
		}
		cout << endl;
	}
};