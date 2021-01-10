/*
int øving = 2;
char student[] = "Jakob Lønnerød Madsen";
*/

// deklarerer metodene slik at jeg kan kalle de fra main()
void oppg1A();
void oppg1B();
void oppg4();
void oppg5();
void oppg6();
int find_sum(const int* table, int length);

#include <iostream>
#include <cstring>

using namespace std;

int main()
{
	// velg hvilken oppgave som skal kjøre her
	oppg6();

	cout << "Program finished" << endl;
	return 0;
}

/* ______________________ Oppgave 1A ______________________ */

/*
resultat:

i: value: 3 Adress: 010FFA94
j: value: 4 Adress: 010FFA88
p: value: 010FFA94 Adress: 010FFA7C
q: value: 010FFA88 Adress: 010FFA70

Her ser vi at int* p = $i lager en referanse til adressen til i, samme skjer for q.
int* er en pointer til verdien. 
*/

void oppg1A()
{
	int i = 3;
	int j = 5;
	int* p = &i;
	int* q = &j;

	cout << "i: value: " << i << " Adress: " << &i << endl;
	cout << "j: value: " << j << " Adress: " << &j << endl;
	cout << "p: value: " << p << " Adress: " << &p << endl;
	cout << "q: value: " << q << " Adress: " << &q << endl;
}

/* ______________________ Oppgave 1B ______________________ */
// Hva blir skrevet ut når følgende programbit utføres?

void oppg1B()
{
	int i = 3;
	int j = 5;
	int* p = &i; // p er adressen til i
	int* q = &j; // q er adressen til j

	*p = 7; // *p er verdien til peker som peker til 7, altså 7. dette vil også si at i også er 7.
	*q += 4; // verdien q peker til blir økt med 4, fra 5 til 9, siden q peker på j, blir j også økt.
	*q = *p + 1; // verdien til q er lik verdien til p + 1
	p = q; // p = q, og i = j, begge er lik 8

	cout << *p << " " << *q << endl; // printer 8 8 
}

/* ______________________ Oppgave 2 og 3 ______________________ */

// oppgave 2
/*
Strcpy kopierer en string til en annen.
Den sjekker ikke størrelsen på stringen den skal kopiere.
Dersom den nye stringen er større enn den gamle vil den skrive utenfor minnet den har fått allokert og kan skrive over annen variabel.
Det går også ikke an å kopiere en string til Null.
I vårt tilfelle er den originale variablen Null, og den nye "Dette er en tekst". Den nye er mye større og vil dermed overskrive andre variabler.
Dette er veldig likt hvordan get() metoden også feiler.
*/

// oppgave 3
/*
Dersom stringen ikke inneholder e vil pointeren fortsette utenfor array'et.
While loopen har ingen "end condition".
Dette vil da overskrive variablene som kommer etter plasseringen til teksten.
*/


/* ______________________ Oppgave 4 ______________________ */
// Finn alle syntaksfeil i følgende programbit:

void oppg4()
{
	/*
	Gammel kode

	int a = 5;
	int& b; // referanser må bli initialisert
	int* c;
	c = &b;	
	*a = *b + *c;// verken a eller b er en pointer så dette vil ikke fungere.
	&b = 2;
	*/

	// Ny kode

	int a = 5;
	int& b = a;
	int* c = &b;
	a = b + *c;
	b = 2;

	cout << a << " | " << b << endl;
}

/* ______________________ Oppgave 5 ______________________ */
// 3 metoder for å få verdien til number:
// Direkte
// Peker
// Referanse

void oppg5()
{
	double number = 10.0;
	double *pointer = &number;
	double &reference = number;

	cout << "direkte " << number << endl;
	cout << "peker " << *pointer << endl;
	cout << "referanse " << reference << endl;
}

/* ______________________ Oppgave 6 ______________________ */
/*
Antar at når det står:
summer de 10 første, 5 neste, og 5 siste
og arrayet er på 20 elementer.
kaller man find_sum() 3 ganger med verdiene 10, 5, og 5.
*/

void oppg6() {
	int table[20] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };

	int sum = find_sum(table, 10);
	cout << sum << endl;

	sum = find_sum(table, 5);
	cout << sum << endl;

	sum = find_sum(table, 5);
	cout << sum << endl;
}

int counter = 0;

int find_sum(const int* table, int length)
{
	int sum = 0;
	for (int i = 0; i<length; i++) {
		sum += *(table + counter);

		counter++;

		if (counter % 20 == 0) {
			counter = 0;
		}
	}
	return sum;
}