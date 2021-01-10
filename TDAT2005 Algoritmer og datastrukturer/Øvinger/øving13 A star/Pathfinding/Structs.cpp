#include <vector>
#include <string>

#pragma once

using namespace std;

struct Node
{
	int nodeNr;

	double latitude; // breddegrad
	double longitude; // lengdegrad
	double rad_lat;
	double rad_long;
	double cos_lat;
	vector<pair<Node*, unsigned int>> roads;

	unsigned int score;
	unsigned int heuristic = 0;

	unsigned int tScore() { return score + heuristic; }

	bool operator<(const Node& n) const {
		return (score+heuristic) < (n.score+n.heuristic);
	}
};
