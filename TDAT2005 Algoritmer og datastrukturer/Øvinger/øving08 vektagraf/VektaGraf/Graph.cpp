#include <vector>
#include <queue>
#include <algorithm>
#include <string>
#include <iostream>

#include "Structs.cpp"

using namespace std;

class Graph
{
private:
	int n;
	vector<vector<int>> adj; // list of neighbor nodes
	vector<vector<int>> capacity; // capacity matrix
	vector<Node*> nodes;
public:
	Graph(vector<Node*> nodes_)
	{
		nodes = nodes_;
		n = (int) nodes.size();
		for (int i = 0; i<n; i++)
		{
			vector<int> tmp1;
			capacity.push_back(tmp1);
			vector<int> tmp2;
			adj.push_back(tmp2);
			for (int j = 0; j < n; j++)
			{
				capacity[i].push_back(0);
			}
		}
		for (Node* node : nodes)
		{
			for (Edge* edge : node->edges)
			{
				// graph is undirected
				adj[edge->from].push_back(edge->to);
				adj[edge->to].push_back(edge->from);
				capacity[edge->from][edge->to] = edge->weight;
				//capacity[edge->to][edge->from] = edge->weight;
			}
		}
	}

	int bfs(int source, int destination, vector<int>& parent) 
	{
		fill(parent.begin(), parent.end(), -1);
		parent[source] = -2;
		queue<pair<int, int>> q;
		q.push({ source, 9999999 });
		
		while (!q.empty()) 
		{
			int cur = q.front().first;
			int flow = q.front().second;
			q.pop();

			// for all neighbors
			for (int next : adj[cur]) 
			{
				// if neighbor is unvisited and has a capacity, meaning there is an connection.
				if (parent[next] == -1 && capacity[cur][next]) 
				{
					parent[next] = cur;
					int new_flow = min(flow, capacity[cur][next]);
					if (next == destination)
					{
						// print flow and path
						{
							cout << "  " << new_flow << " :  ";
							int printNode = next;
							string result = "";
							while (true)
							{
								result += to_string(printNode) + " ";
								if (printNode == source) {
									break;
								}
								printNode = parent[printNode];
							}
							reverse(result.begin(), result.end());
							cout << result << endl;
						}

						return new_flow;
					}
					q.push({ next, new_flow });
				}
			}
		}

		return 0;
	}

	int EdmundKarp(int source, int destination) 
	{
		int flow = 0;
		vector<int> parent(n);
		int new_flow;

		cout << "Flow:   Path" << endl;
		while (new_flow = bfs(source, destination, parent)) 
		{
			flow += new_flow;
			int cur = destination;
			while (cur != source) 
			{
				int prev = parent[cur];
				capacity[prev][cur] -= new_flow;
				capacity[cur][prev] += new_flow;
				cur = prev;
			}
		}
		return flow;
	}
};
