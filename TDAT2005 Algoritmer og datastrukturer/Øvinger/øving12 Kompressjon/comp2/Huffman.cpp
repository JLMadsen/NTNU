#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <sstream>
#include <assert.h>
#include <bitset>

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
	string text;
	string filename;
	unsigned int offset = 0;

	Node* start;

	vector<Node*> nodes;
	vector<int> frequencies = vector<int>(256, 0);
	vector<vector<char>> bits = vector<vector<char>>(256, {'a'});

public:

	Huffman(string& filename_)
	{
		start = NULL;
		filename = filename_;
	}

	void encode(bool debug = false)
	{
		cout << "\nEncoding\n" << endl;

		readPlain(debug);
		createTree(debug);
		compress(debug);
	}

	void decode(bool debug = false)
	{
		cout << "\nDecoding\n" << endl;
		if (!readCoded(debug))
		{
			return;
		}
		createTree(debug);
		decompress(debug);
	}

	string read(string filename)
	{
		ifstream inFile;
		inFile.open(filename, ios::binary);
		stringstream strStream;
		strStream << inFile.rdbuf();
		inFile.close();
		string str = strStream.str();
		cout << "from file" << endl;
		cout << str << endl;
		return str;
	}

	void readPlain(bool debug)
	{
		text = read(filename);

		// teller hver char
		for (unsigned char c : text)
		{
			// noen bokstaver gir -61
			if (c < 0 || c > 255) { cout << c << endl; return; }
			frequencies[c]++;
		}
	}

	bool readCoded(bool debug)
	{
		if (debug) cout << "Reading" << endl;
		frequencies = vector<int>(256, 0);
		offset = 0;
		text.clear();

		text = read("C" + filename);

		unsigned int start = 0;
		int counter = 0;
		string buffer;
		for (unsigned int i = 0; i < text.length(); i++)
		{
			if (text[i] == '\n')
			{
				start = i+1;
				break;
			}
			if (text[i] == ' ')
			{
				int freq = stoi(buffer);
				buffer.clear();
				if (counter == 256)
				{
					offset = freq;
					continue;
				}
				frequencies[counter] = freq;
				counter++;
				continue;
			}
			buffer.push_back(text[i]);
		}
		text = text.substr(start, text.length());
		
		if (debug)
		{
			cout << "Reading from file" << endl;
			
			int freqSum = 0;
			for (unsigned int i = 0; i < frequencies.size(); i++) freqSum += frequencies[i];

			cout << "total frequency: " << freqSum << endl;
			cout << "text length: " << text.length() << endl;
			cout << "text:" << endl;
			cout << text << endl;
		}
		return true;
	}

	void printTable(vector<Node*> table, string label = " ")
	{
		if (label != " ")
		{
			cout << "\n" << label << " ";
		}

		for (Node* node : table)
		{
			cout << node->data << ", ";
		}
		cout << endl;
	}

	void printFreq()
	{
		cout << "\nFrequencies" << endl;
		for (int i : frequencies)
		{
			cout << i << " ";
		}
		cout << endl;
	}

	void printTree(Node* node = NULL)
	{
		if (node == NULL)
		{
			cout << "\n\nPrint tree" << endl;
			node = start;
		}
		cout << "\nName " << node->data << endl;
		cout << "freq " << node->freq << endl;

		if (node->left != NULL)
		{
			cout << "node left " << node->left->data << endl;
			printTree(node->left);
		}
		if (node->right != NULL)
		{
			cout << "node right " << node->right->data << endl;
			printTree(node->right);
		}

	}

	void createTree(bool debug)
	{
		nodes = vector<Node*>();
		if (debug) cout << "Creating tree" << endl;
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

		vector<Node*> queue = nodes;

		while (queue[0] != NULL && queue[1] != NULL)
		{
			if(debug) printTable(queue, "queue:");

			Node* node1 = queue[0];
			Node* node2 = queue[1];

			// lager en node som kobler løvnodene
			Node* connector = new Node((node1->freq + node2->freq), NULL, node1, node2);
			if(debug) cout << "Created connector with freq: " << connector->freq << " from (" << node1->data << ", " << node1->freq << ") and (" << node2->data << ", " << node2->freq << ")" << endl;

			// sletter fra køen
			queue.erase(queue.begin());
			queue.erase(queue.begin());

			// exit condition
			if (queue.size() == 0)
			{
				start = connector;
				return;
			}

			// legger inn i køen
			queue.push_back(connector);
			sort(queue);
		}
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

	void findBitstrings(vector<char> path = {}, Node* n = NULL)
	{
		// mapper alle chars
		if (n == NULL) { n = start; }
		if (n->left == NULL && n->right == NULL) { bits[(int)n->data] = path; }

		if (n->right != NULL) {
			vector<char> rightBits = path;
			rightBits.push_back('1');
			findBitstrings(rightBits, n->right);
		}
		if (n->left != NULL)
		{
			vector<char> leftBits = path;
			leftBits.push_back('0');
			findBitstrings(leftBits, n->left);
		}
	}

	void decompress(bool debug)
	{
		// gjør bitstring om til vector<char>
		vector<char> bitstring;
		for (unsigned char c : text)
		{
			string temp = (char_to_str(c));
			for (unsigned char h : temp)
			{
				bitstring.push_back(h);
			}
		}

		if (debug) cout << "\nDecompressing" << endl;
		vector<unsigned char> output;
		Node* currentNode = start;

		for (char c : bitstring)
		{
			// fiks offsett
			if (offset > 0)
			{
				offset--;
				if (debug) cout << "off--";
				continue;
			}
			// navigere gjennom treet til riktig char
			if (c == '1')
			{
				currentNode = currentNode->right;
			}
			else
			{
				currentNode = currentNode->left;
			}
			if (currentNode->data != NULL) // Exception thrown: read access violation.
										   // currentNode was nullptr.
			{
				output.push_back(currentNode->data);
				currentNode = start;
			}
		}
		if (debug) cout << endl;
		if (debug)
		{
			cout << "decompressed text:" << endl;
			for (unsigned int i = 0; i < output.size(); i++) cout << output[i];
			cout << endl;
		}
		writePlain(output);
	}

	void compress(bool debug)
	{
		if (debug) cout << "Compressing" << endl;
		vector<unsigned char> output;
		findBitstrings();

		for (unsigned char c : text)
		{
			for (unsigned char e : bits[(int)c])
			{
				output.push_back(e);
			}
		}

		writeCoded(output);
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

	void writeCoded(vector<unsigned char> bitstring)
	{
		cout << "\nWriting: " << (bitstring.size() / 8.0) << " letters" << endl;

		// hvis bitstrengen ikke er delelig på 8 øker vi lengden
		while (bitstring.size() % 8 != 0)
		{
			bitstring.insert(bitstring.begin(), '0');
			offset++;
		}
		
		ofstream file;
		file.open("C" + filename);
		assert(file.is_open());

		vector<unsigned char> output;

		for (unsigned int i = 0; i < bitstring.size(); i += 8)
		{
			string group(bitstring.begin() + i, bitstring.begin() + i + 8);
			unsigned char c = str_to_char(group);
			output.push_back(c);
		}

		// write frequencies
		for (int i : frequencies)
		{
			file << i << " ";
		}

		// write offset
		file << offset << "\n";

		// write bits
		for (unsigned char c : output)
		{
			file << c;
		}
		file.close();
	}

	void writePlain(vector<unsigned char> text)
	{
		ofstream file;
		file.open("D"+filename);
		assert(file.is_open());
		for (unsigned int i = 0; i < text.size(); i++) file << text[i];
		file.close();
	}
};