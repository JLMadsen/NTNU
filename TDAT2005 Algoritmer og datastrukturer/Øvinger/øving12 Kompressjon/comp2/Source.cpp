#pragma once

using namespace std;

struct Node
{
	char data;
	unsigned int freq;
	Node* left, * right;
};