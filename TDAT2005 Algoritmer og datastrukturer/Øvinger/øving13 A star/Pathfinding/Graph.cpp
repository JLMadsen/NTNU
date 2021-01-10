#define _USE_MATH_DEFINES
#include <vector>
#include <string>
#include <limits>
#include <algorithm>
#include <chrono>
#include <queue>
#include "math.h"
#include <fstream>
#include <iostream>
#include <sstream>
#include <stdio.h>
#include <stdlib.h>

#include "Structs.cpp"
#include "Heap.cpp"

#pragma once

using namespace std;

class Grapher
{
private:

	string filename;
	vector<Node*> nodes;
	int v = 0; // visited nodes

public:

	Grapher(bool norden)
	{
		if (!readMap(norden))
			cout << "Map reading failed." << endl;
	}

	void findPath(int from, int to, bool astar = true)
	{
		v = 0;
		string mode;
		if (astar)
			mode = "A*";
		else
			mode = "Djikstra";

		cout << "\nStart " << mode << ":" << endl;

	    auto t1 = chrono::high_resolution_clock::now();

		Node* start = nodes[from];
		Node* goal = nodes[to];
		vector<int> pred = pathfinder(start, goal, astar);

		auto t2 = chrono::high_resolution_clock::now();
		auto time_span = chrono::duration_cast<chrono::duration<double>>(t2 - t1).count();

		printPath(pred, goal);
		cout << time_span << " s Time used for " << mode << "\n" << endl;
		reset();
	}
	
	void reset()
	{
		for (Node* n : nodes)
			n->heuristic = 0;
	}

	bool readMap(bool norden)
	{
		cout << "Reading..." << endl;

		string nodeFile;			// format: nodeNr latitude longitude
		string edgeFile;			// format: fromNodeNr toNodeNr time distance speedlimit
		string pointFile;			// format: nodeNr 0 name

		if (norden)
		{
			cout << "Map = Norden" << endl;
			nodeFile = "noder.txt";
			edgeFile = "kanter.txt";
			pointFile = "interessepkt.txt";
		}
		else
		{
			cout << "Map = Island" << endl;
			nodeFile = "Inoder.txt";
			edgeFile = "Ikanter.txt";
			pointFile = "Iinteressepkt.txt";
		}
		

		int nodecount = 0;
		int edgecount = 0;
		int pointcount = 0;

		bool header = true;
		string line;

		cout << "Reading nodes: ";
		ifstream file(nodeFile);

		while (getline(file, line))
		{
			istringstream iss(line);
			if (header)
			{
				if (!(iss >> nodecount)) return false;
				cout << nodecount << endl;
				header = false;
				continue;
			}

			int nodeNr;
			double latitude, longitude;
			if (!(iss >> nodeNr)) return false;
			if (!(iss >> latitude >> longitude)) return false;

			Node* temp = new Node();
			temp->nodeNr = nodeNr;
			temp->latitude = latitude;
			temp->longitude = longitude;
			temp->rad_lat = latitude * (M_PI / 180);
			temp->rad_long = longitude * (M_PI / 180);
			temp->cos_lat = cos(temp->rad_lat);

			nodes.push_back(temp);
			nodecount--;
		}
		if (nodecount != 0) { cout << "Node mismatch, diff: " << nodecount << endl; return false; }

		cout << "Reading edges: ";
		header = true;
		file = ifstream(edgeFile);

		while (getline(file, line))
		{
			istringstream iss(line);
			if (header)
			{
				if (!(iss >> edgecount)) return false;
				cout << edgecount << endl;
				header = false;
				continue;
			}

			int from, to;
			unsigned int time;

			if (!(iss >> from >> to)) return false;
			if (!(iss >> time)) return false;

			Node* n1 = nodes[from];
			Node* n2 = nodes[to];

			n1->roads.push_back(make_pair(n2, time));
			edgecount--;
		}
		if (edgecount != 0) { cout << "Edge mismatch, diff: " << edgecount << endl; return false; }

		// lengden på første kanten
		//cout << nodes[0]->roads[0].second << endl;

		cout << "Done reading." << endl;
		return true;
	}

	void printPath(vector<int> pred, Node* goal)
	{
		int current = goal->nodeNr;
		unsigned int totalLength = 0;
		vector<Node*> path;

		while (current != -1)
		{
			path.insert(path.begin(), nodes[current]);
			current = pred[current];
		}

		cout << "\nPath:" << endl;
		for (Node* n : path)
			cout << fixed << n->latitude << ", " << fixed << n->longitude << endl; // fixed = floating point stuff for printing

		int seconds = (int)goal->score / 100;
		int	minutes = (int)seconds / 60;
		int hours = (int)minutes / 60;

		cout << "\n" << path.size() << " Path size" << endl;
		cout << v << " Visited nodes" << endl;
		cout << hours << " h " << (minutes % 60) << " m " << (seconds % 60) << "s Driving time" << endl;
	}

	unsigned int distance(Node* n1, Node* n2)
	{
		double seconds = 35285538.46153846153846153846;
		double sin_lat = sin((n1->rad_lat - n2->rad_lat) / 2);
		double sin_long = sin((n1->rad_long - n2->rad_long) / 2);

		unsigned int distance = (unsigned int)seconds * asin(sqrt((sin_lat * sin_lat) + (n1->cos_lat * n2->cos_lat * sin_long * sin_long)));

		if (distance == 0)
		{
			if (n1->nodeNr != n2->nodeNr)
			{
				cout << "distance == 0.. values:" << endl;
				cout << "seconds = " << seconds << endl;
				cout << "sin_lat = " << sin_lat << endl;
				cout << "sin_long = " << sin_long << endl;
				cout << "n1 cos_lat = " << n1->cos_lat << endl;
				cout << "n2 cos_lat = " << n2->cos_lat << endl << endl;
			}
		}

		return distance;
	}

	void sort(vector<Node*>& table)
	{
		for (int i = 0; i < table.size(); i++)
		{
			int lowest = i;
			for (int j = i + 1; j < table.size(); j++)
			{
				if (table[lowest]->score+ table[lowest]->heuristic > table[j]->score + table[j]->heuristic)
				{
					lowest = j;
				}
			}
			Node* temp = table[lowest];
			table[lowest] = table[i];
			table[i] = temp;
		}
	}

	// Gjør både Djikstra og A* basert på astar variablen
	// Eneste forskjellen er om den skal bruke den heuristiske verdien
	vector<int> pathfinder(Node* start, Node* goal, bool astar = true)
	{
		MinHeap mh = MinHeap(nodes.size());
		mh.insertKey(start);
		unsigned int max = -1;
		for (Node* node : nodes)
			node->score = max;

		vector<int> predecessors(nodes.size(), -2);
		predecessors[start->nodeNr] = -1;
		start->score = 0;

		while (mh.get_heap_size() != 0)
		{
			Node* current = mh.extractMin();
			v++; // teller hvor mange noder som blir behandlet
			if (current->nodeNr == goal->nodeNr)
			{
				return predecessors;
			}
			for (pair<Node*, unsigned int> next : current->roads)
			{
				unsigned int new_score = current->score + next.second;
				if (new_score < next.first->score)
				{
					predecessors[next.first->nodeNr] = current->nodeNr;
					next.first->score = new_score;
					if (astar && next.first->heuristic == 0)
						next.first->heuristic = distance(next.first, goal);
					mh.insertKey(next.first);
				}
			}
		}
		cout << "No path found" << endl;
		return predecessors;
	}
};