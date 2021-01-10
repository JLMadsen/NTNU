/*
int øving = 4;
String student = "Jakob Lønnerød Madsen";
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <Windows.h>

void oppg1();
void oppg2();

using namespace std;

int main()
{
	oppg2();

	return 0;
}


void oppg1()
{
	// Opprett en vektor av double. Legg inn fem tall (behøver ikke leses inn.)
	vector<double> vec = { 1.0, 2.0, 3.0, 4.0, 5.0};

	// Prøv ut medlemsfunksjonene front() og back(). De returnerer hver et element, og de har ingen argumenter.
	cout << "Front() = " << vec.front() << endl;
	cout << "Back() = " << vec.back() << endl;

	// Bruk emplace() til å sette inn et tall etter det første elementet. Skriv ut resultatet av front() etterpå.
	vec.emplace(vec.begin() + 1, 6.0);
	cout << "Front() etter Emplace() = " << vec.front() << endl;

	// Prøv ut STL-algoritmen find()
	double target = 4.0;

	auto result = find(vec.begin(), vec.end(), target);
	
	if (result != vec.end())
	{
		cout << "Vectoren inneholder " << target << endl;
	}
	else
	{
		cout << "Vectoren IKKE inneholder " << target << endl;
	}

}

void oppg2()
{

}