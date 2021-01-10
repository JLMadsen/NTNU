
/*
int øving = 1;
char student[] = "Jakob Lønnerød Madsen";
*/

int oppgA();
int oppgB();
void read_temperatures(double temperatures[], int length);

#include <iostream>
#include <cstdlib>
#include <fstream>

// bruker namespace std for cout og cin
using namespace std;

int main()
{
	cout << "Oppgave 1 A" << endl;
	oppgA();

	cout << "Oppgave 1 B" << endl;
	oppgB();

	return 0;
}

/* _________________________________________ Oppgave 1 A _________________________________________ */

int oppgA()
{
	// antall temperaturer.
	const int length = 5;

	// bruker variabler som teller underveis siden oppgaven sier jeg ikke skal bruke tabeller.
	int under10 = 0;
	int between10and20 = 0;
	int above20 = 0;

	cout << "skriv inn " << length << " temperaturer" << endl;

	// for løkke for antall temperaturer.
	for (int i = 0; i < length; i++) {
		double temp = 0.0;
		cin >> temp;

		// enkel sjekk.
		if (temp < 0) {
			continue;
		}

		// enkel else if struktur.
		if (temp <= 10) {
			under10++;
		}
		else if (temp > 10 and temp <= 20) {
			between10and20++;
		}
		else {
			above20++;
		}
	}

	// printer resultatene.
	cout << "temperaturer under 10 grader " << under10 << endl;
	cout << "temperaturer mellom 10 grader og 20 grader " << between10and20 << endl;
	cout << "temperaturer over 20 grader " << above20 << endl;

	return 0;
}

/* _________________________________________ Oppgave 1 B _________________________________________ */

int oppgB()
{
	const int length = 5;
	double temperatures[length];

	read_temperatures(temperatures, length);

	return 0;
}

void read_temperatures(double temperatures[], int length)
{
	cout << "\nold temperatures:" << endl;
	for (int i = 0; i < length; i++) {
		cout << temperatures[i] << endl;
	}

	// filnavn
	const char filename[] = "temperatures.dat";
	ifstream file;
	file.open(filename);

	// sjekker om filen eksisterer
	if (!file) {
		cout << "Error on reading file." << endl;
		exit(EXIT_FAILURE);
	}

	// leser fra fil
	double temperature;
	int counter = 0;
	while (file >> temperature) {
		temperatures[counter] = temperature;
		counter++;
	}

	file.close();

	// printer resultat fra fil
	cout << "\nnew temperatures:" << endl;
	for (int i = 0; i < length; i++) {
		cout << temperatures[i] << endl;
	}
}
