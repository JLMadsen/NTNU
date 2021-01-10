#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <sstream>
#include <assert.h>
#include <bitset>
#include <tuple>

#pragma once

using namespace std;

struct Node
{
	int freq;
	unsigned char data = NULL;
	Node* left, * right;

	Node(int freq_ = NULL, unsigned char data_ = NULL, Node* left_ = NULL, Node* right_ = NULL)
	{
		freq = freq_;
		data = data_;
		left = left_;
		right = right_;
	}
};


class Huffman
{
private: 
	string filename;
	vector<unsigned char> finalBitString;
	vector<unsigned char> finalText;
	vector<vector<char>> bits = vector<vector<char>>(256, { 'a' });

public:
	Huffman(string& filename_)
	{
		filename = filename_;
	}

	vector<unsigned char> read(string filename)
	{
		ifstream inFile;
		inFile.open(filename, ios::binary);
		stringstream strStream;
		strStream << inFile.rdbuf();
		inFile.close();
		string str = strStream.str();

		vector<unsigned char> text;
		for (unsigned char c : str)
		{
			text.push_back(c);
		}

		return text;
	}

	vector<unsigned char> readBytes(string filename_)
	{
		vector<unsigned char> bytes;
		ifstream file1(filename_, ios_base::in | ios_base::binary);
		unsigned char ch = file1.get();
		while (file1.good())
		{
			bytes.push_back(ch);
			ch = file1.get();
		}
		return bytes;
	}

	void printCharVec(vector<unsigned char> vec, string label = "Vector")
	{
		cout << "\n" << label << ":" << endl;
		for (unsigned char c : vec)
		{
			cout << c;
		}
		cout << endl;
	}

	void sort(vector<Node*>& table)
	{
		// selection sort
		for (unsigned int i = 0; i < table.size(); i++)
		{
			int lowest = i;
			for (unsigned int j = i; j < table.size(); j++)
			{
				if (table[lowest]->freq > table[j]->freq)
				{
					lowest = j;
				}
			}
			Node* temp = table[lowest];
			table[lowest] = table[i];
			table[i] = temp;
		}
	}

	Node* createTree(vector<int> frequencies)
	{
		// lager f�rst noder
		vector<Node*> nodes = vector<Node*>();
		Node* start;
		for (unsigned int i = 0; i < frequencies.size(); i++)
		{
			if (frequencies[i] == 0)
			{
				continue;
			}
			else
			{
				Node* temp = new Node(frequencies[i], (unsigned char)i);
				nodes.push_back(temp);
			}
		}
		sort(nodes);

		// lager en k� av nodene
		vector<Node*> queue = nodes;

		// lager treet av nodene.
		while (queue[0] != NULL && queue[1] != NULL)
		{
			// henter ut de 2 f�rste nodene.
			Node* node1 = queue[0];
			Node* node2 = queue[1];

			// lager en node som kobler l�vnodene
			int freq = node1->freq + node2->freq;
			Node* connector = new Node(freq, NULL, node1, node2);

			// sletter fra k�en
			queue.erase(queue.begin());
			queue.erase(queue.begin());

			// exit condition
			if (queue.size() == 0)
			{
				start = connector;
				return start;
			}

			// legger inn i k�en
			queue.push_back(connector);
			sort(queue);
		}
	}

	void findBitstrings(Node* n, vector<char> path = {})
	{
		// mapper alle chars
		if (n->left == NULL && n->right == NULL) { bits[n->data] = path; }

		if (n->right != NULL) {
			vector<char> rightBits = path;
			rightBits.push_back('1');
			findBitstrings(n->right, rightBits);
		}
		if (n->left != NULL)
		{
			vector<char> leftBits = path;
			leftBits.push_back('0');
			findBitstrings(n->left, leftBits);
		}
	}

	void printBits()
	{
		cout << "Bitstrings:" << endl;

		for (unsigned int i = 0; i < bits.size(); i++)
		{
			if (bits[i][0] == 'a') continue;
			cout << (unsigned char)i << ": ";
			for (unsigned char c : bits[i])
			{
				cout << c;
			}
			cout << endl;
		}
	}
	void printBitstring()
	{
		cout << "Total bitstring:" << endl;
		for (unsigned char c : finalBitString)
		{
			cout << c;
		}
		cout << endl;
	}
	void printText()
	{
		cout << "Text" << endl;
		for (unsigned char c : finalText)
		{
			cout << c;
		}
		cout << endl;
	}

	unsigned char str_to_char(string group)
	{
		// convert a group of 8 digits to a character
		bitset<8> temp(group);
		return temp.to_ulong();
	}

	string char_to_str(unsigned char ch)
	{
		// convert a character to a group of eight digits
		bitset<8> temp(ch);
		return temp.to_string();
	}

	void encode()
	{
		cout << "Encoding..." << endl;

		// leser tekst
		vector<unsigned char> text = readBytes(filename);

		// teller chars
		vector<int> frequencies = vector<int>(256, 0);
		for (unsigned char c : text)
		{
			if (c < 0 || c > 256)
			{
				cout << "out of range \'" << c << "\'" << endl;
			}
			frequencies[c]++;
		}

		// lager treet
		Node* start = createTree(frequencies);
		findBitstrings(start);

		// gj�r om fra chars til bitstrenger
		vector<unsigned char> bitstring;
		for (unsigned char c : text)
		{
			for (unsigned char e : bits[c])
			{
				bitstring.push_back(e);
			}
		}
		finalBitString = bitstring;

		// hvis bitstrengen ikke er delelig p� 8 �ker vi lengden
		int offset = 0;
		while (bitstring.size() % 8 != 0)
		{
			bitstring.insert(bitstring.begin(), '0');
			offset++;
		}

		// gj�r om bitstringen til chars/bytes
		vector<unsigned char> chars;
		for (unsigned int i = 0; i < bitstring.size(); i += 8)
		{
			// group = 01100001
			// c = 'a'
			string group(bitstring.begin() + i, bitstring.begin() + i + 8);
			unsigned char c = str_to_char(group);
			chars.push_back(c);
		}
		finalText = chars;

		// lager fil
		ofstream file;
		file.open("C" + filename);
		//assert(file.is_open());

		// skriver frequencies
		for (int i : frequencies)
		{
			file << i << " ";
		}

		// skriver offset
		file << offset << "\n";

		// skriver bits
		for (unsigned char c : chars)
		{
			file << c;
		}
		file.close();

		cout << "Done." << endl;
	}
	
	void decode()
	{
		cout << "Decoding..." << endl;

		// deklarerer variabler jeg trenger
		vector<int> frequencies = vector<int>(256, 0);
		int offset = 0;
		vector<unsigned char> text;

		// leser bytes fra fil
		text = readBytes("C" + filename);

		// initialiserer frequencies, offset og tekst
		unsigned int start = 0;
		int counter = 0;
		string buffer;
		for (unsigned int i = 0; i < text.size(); i++)
		{
			if (text[i] == '\n')
			{
				start = i + 1;
				break;
			}
			if (text[i] == ' ')
			{
				int freq = stoi(buffer);
				buffer.clear();
				
				frequencies[counter] = freq;
				counter++;
				continue;
			}
			buffer.push_back(text[i]);
			if (counter == 256)
			{
				int freq = stoi(buffer);
				offset = freq;
				continue;
			}
		}

		// henter ut tekst
		vector<unsigned char> res;
		for (unsigned int i = 0; i < text.size(); i++)
		{
			if (i >= start)
			{
				res.push_back(text[i]);
			}
		}
		text = res;
		finalText = text;

		// lager treet
		Node* treeStart = createTree(frequencies);

		// lager bitstrings
		findBitstrings(treeStart);

		// gj�r om tekst til bitstring
		vector<unsigned char> bitstring;
		for (unsigned char c : text)
		{
			for (unsigned char x = 128; x > 0; x >>= 1)
			{
				if (x & c)
				{
					bitstring.push_back('1');
				}
				else
				{
					bitstring.push_back('0');
				}
			}
		}
		finalBitString = bitstring;

		// gj�r om bitstring til chars
		vector<unsigned char> output;
		Node* currentNode = treeStart;

		for (char c : bitstring)
		{
			// fiks offsett
			if (offset > 0)
			{
				offset--;
				continue;
			}
			// navigere gjennom treet til riktig char
			if (c == '1')
			{
				currentNode = currentNode->right;
			}
			else if(c == '0')
			{
				currentNode = currentNode->left;
			}
			// sjekker om den er null for "debugging"
			if (currentNode == NULL)
			{
				cout << "currentNode = nullptr, last char c: " << c << endl;
				for (unsigned char f : output)
				{
					cout << f;
				}
				cout << endl;
			}
			if (currentNode->data != NULL)
			{
				output.push_back(currentNode->data);
				currentNode = treeStart;
			}
		}

		// skriver til fil
		ofstream file;
		file.open("D" + filename, ios_base::in | ios_base::binary);
		//assert(file.is_open());
		file.write((char*)& output[0], output.size());
		//for (unsigned int i = 0; i < output.size(); i++) file << output[i];
		file.close();

		cout << "Done." << endl;
	}
};