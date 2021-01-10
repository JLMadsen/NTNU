#include <iostream>
#include <string>

#include "Huffman.cpp"

using namespace std;

int main()
{
	string filename;

	cout << "filename:" << endl;
	cin >> filename;

	if (filename == "a") filename = "alphabet.txt";

	string mode;

	cout << "mode: (compress, decompress, both)" << endl;
	cin >> mode;

	if (mode == "c")
	{
		Huffman huff = Huffman(filename);
		huff.encode(true);
	}
	else if(mode == "d")
	{
		Huffman huff = Huffman(filename);
		huff.decode(true);
	}
	else
	{
		{
			Huffman huff = Huffman(filename);
			huff.encode();
		}
		{
			Huffman huff = Huffman(filename);
			huff.decode();
		}		
	}
}

