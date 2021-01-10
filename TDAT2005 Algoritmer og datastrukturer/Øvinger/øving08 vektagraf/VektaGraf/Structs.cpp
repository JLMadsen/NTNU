#include <vector>

#pragma once

using namespace std;

struct Edge
{
	int from;
	int to;
	int weight;

	Edge(int from_, int to_, int weight_)
	{
		from = from_;
		to = to_;
		weight = weight_;
	}
};

struct Node
{
	int num;
	vector<Edge*> edges;
};

