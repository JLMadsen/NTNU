#include <string>
#include <vector>
#include <functional>

class HashTabell
{
private:
	int size;
	int elements;

	vector<hash<int>> keys;
	vector<int> values;

public:
	HashTabell::HashTabell(int size_) : size(size_) {};

	int getLastFaktor() const 
	{ 
		return elements / size; 
	}

	bool addName(string name)
	{

	}
	
	int getIndex(hash<int> hash)
	{

	}

	int getFromName(string name)
	{
		hash<int> hash;
		hash = getHash(name);
		return getFromHash(hash)
	}

	int getFromHash(hash<int> hash)
	{

	}

	hash<int> getHash(string name)
	{
		int num = 0;

		int weight = 0;
		for (char c : name)
		{
			num += weight * (int)c;
			weight++;
		}
		hash<int> hash = hash(num);
		return hash;
	}

};