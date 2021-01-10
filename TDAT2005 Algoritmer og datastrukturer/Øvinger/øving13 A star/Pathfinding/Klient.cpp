#include <iostream>
#include <vector>
#include <string>
#include <stdlib.h>

#include "Graph.cpp"

using namespace std;

int main()
{
	Grapher g = Grapher(true);
	while (true)
	{
		int choice;
		while (true)
		{
			cout << "Choose:\n1) A*\n2) Djikstra\n3) clear\n4) Exit" << endl;
			cin >> choice;
			if (choice < 1 || choice > 3)
			{
				cout << "Exiting.";
				return 0;
			}
			else if (choice == 3)
			{
				system("CLS");
				cout << "" << flush;
				system("CLS");
				continue;
			}
			else
			{
				break;
			}
		}
		vector<int> nodes = { 2460904, 2419175, 1562696, 492171, 5709083, 5108028 };
		cout << "Choose start:\n1) Trondheim\n2) Oslo\n3) Skien\n4) Stockholm\n5) Kårvåg\n6) Gjemnes\n0) Custom" << endl;
		int i;
		cin >> i;
		int start;
		if (i == 0)
		{
			cout << "Enter nodeNr:" << endl;
			cin >> start;
		}
		else
		{
			start = nodes[i - 1];
		}
		cout << "Choose goal:\n1) Trondheim\n2) Oslo\n3) Skien\n4) Stockholm\n5) Kårvåg\n6) Gjemnes\n0) Custom" << endl;
		int j;
		cin >> j;
		int goal;
		if (j == 0)
		{
			cout << "Enter nodeNr:" << endl;
			cin >> goal;
		}
		else
		{
			goal = nodes[j - 1];
		}

		if (start == goal)
		{
			cout << "start == goal, restarting" << endl;
			continue;
		}

		g.findPath(start, goal, (choice == 1) );
	}
}