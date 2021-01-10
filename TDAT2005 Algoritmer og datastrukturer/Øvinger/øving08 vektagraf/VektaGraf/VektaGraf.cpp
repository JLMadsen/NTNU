#include <chrono>
#include <string>
#include <vector>

#include <fstream>
#include <iostream>
#include <sstream>

#include "Structs.cpp"
#include "Graph.cpp"

using namespace std;

vector<Node*> read(string filename)
{
	vector<Node*> nodes;
	ifstream infile(filename);
	string line;
	bool header = true;
	while (getline(infile, line))
	{
		istringstream iss(line);
		if (header)
		{
			int nodeCount, edgeCount = 0;
			if (!(iss >> nodeCount >> edgeCount))
			{
				cout << nodeCount << " " << edgeCount << endl;
				cout << "Reading failed at nodes." << endl;
				break;
			}
			for (int i = 0; i < nodeCount; i++)
			{
				Node* temp = new Node();
				temp->num = i;
				nodes.push_back(temp);
			}
			//cout << "Created " << nodes.size() << " nodes." << endl;
			header = false;
			continue;
		}
		// assumes graph is directional
		int node1, node2, weight;
		if (!(iss >> node1 >> node2 >> weight))
		{
			cout << "Reading failed at edges." << endl;
			break;
		}
		Edge* temp = new Edge(node1, node2, weight);
		//cout << "Pushing edge to node " << node1 << endl;
		try
		{
			nodes[node1]->edges.push_back(temp);
		}
		catch (exception e)
		{
			cout << "Exception at edges" << endl;
			cout << node1 << node2 << weight << endl;
			exit(0);
		}
	}
	//cout << "graph created." << endl;
	return nodes;
}

void displayGraph(vector<Node*> nodes)
{
	for (int i = 0; i < nodes.size(); i++)
	{
		cout << "Node " << i << endl;
		for (Edge* edge : nodes[i]->edges)
		{
			cout << edge->from << " -" << edge->weight << "-> " << edge->to << endl;
		}
	}
}

void eksamen2015()
{
	string file = "2015.txt";
	vector<Node*> nodes = read(file);
	displayGraph(nodes);
	Graph graph(nodes);
	cout << "Max flow = " << graph.EdmundKarp(0, 7) << endl;
}

int main()
{
	eksamen2015();
	return 0;
	vector<string> files{ "flytgraf1.txt", "flytgraf2.txt", "flytgraf3.txt"};
	double totalTime = 0.0;

	for (string file : files)
	{
		auto time1 = chrono::high_resolution_clock::now();
		cout << "\nRunning file: " << file << endl;

		vector<Node*> nodes = read(file);
		//displayGraph(nodes);
		Graph graph(nodes);
		cout << "Max flow = " << graph.EdmundKarp(0,1) << endl;

		auto time2 = chrono::high_resolution_clock::now();
		auto duration = chrono::duration_cast<std::chrono::microseconds>(time2 - time1).count();
		cout << "Time: " << duration << " ms" << endl;
		totalTime += duration;
	}
	cout << "\nTotal time: " << totalTime << " ms" << endl;
	return 0;
}

