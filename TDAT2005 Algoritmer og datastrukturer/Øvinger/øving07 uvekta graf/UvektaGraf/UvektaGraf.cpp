#include <iostream>
#include <vector>
#include <fstream>
#include <string>
#include <algorithm>
#include <regex>

using namespace std;

struct Node
{
	Node(int num) : number(num) {}
	int number;
	vector<Node*> toNode;
};

vector<Node*> init()
{
	vector<Node*> nodes;
	int nodeCount = 0;
	int edgeCount = 0;

	// read nodes
	string filename = "BFSgraph.txt";
	const regex exp("\d+");
	string line;

	ifstream myfile(filename);
	if (myfile.is_open())
	{
		bool header = true;
		while (getline(myfile, line))
		{
			// add nodes
			smatch m;
			vector<int> temp;

			cout << "The following matches and submatches were found:" << endl;

			while (regex_search(line, m, exp)) {
				for (auto x : m) temp.push_back(stoi(x));
			}

			if (header)
			{
				nodeCount = temp[0];
				edgeCount = temp[1];
			}
			else
			{
				Node node2 = Node(temp[1]);
				Node node1 = Node(temp[0]);
				node1.toNode = &node2;
				
				nodes.push_back(&node1);
			}
			



		}
		myfile.close();
	}
	else cout << "Unable to open file";
	return nodes;
}

int main()
{
	vector<Node*> nodes = init();

	for (Node* n : nodes)
	{
		cout << n->number << endl;
	}
}
