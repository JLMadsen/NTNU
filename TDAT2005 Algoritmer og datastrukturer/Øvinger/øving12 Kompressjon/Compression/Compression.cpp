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
	if (filename == "t") filename = "test.txt";

	string mode;

	cout << "mode: (compress, decompress, both)" << endl;
	cin >> mode;
	cout << endl;

	if (mode == "c")
	{
		Huffman huff = Huffman(filename);
		huff.encode();
	}
	else if (mode == "d")
	{
		Huffman huff = Huffman(filename);
		huff.decode();
	}
	else
	{
		{
			Huffman huff = Huffman(filename);
			huff.encode();
			/*huff.printBits();
			huff.printText();
			huff.printBitstring();*/
		}
		{
			Huffman huff = Huffman(filename);
			huff.decode();
			/*huff.printBits();
			huff.printText();
			huff.printBitstring();*/
		}
	}
}