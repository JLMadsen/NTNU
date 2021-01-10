/*
int øving = 3;
char student[] = "Jakob Lønnerød Madsen"
*/

void oppg1();
void oppg2();
void oppg3();
void oppg4();

#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main()
{
	// velg hvilken oppgave du vil kjøre
	oppg4();

	return 0;
}

/*________________________ oppgave 1 ________________________*/

const double pi = 3.141592;

class Circle {
	// metoder
public:
	Circle(double radius_);
	double get_area() const;
	double get_circumference() const;

	// attributter
private:
	double radius;
};

// ==> Implementasjon av klassen Circle

Circle::Circle(double radius_) : radius(radius_) {}

double Circle::get_area() const {
	return pi * radius * radius;
}

double Circle::get_circumference() const {
	double circumference = 2.0 * pi * radius;
	return circumference;
}

void oppg1()
{
	Circle cir(pi);

	double area = cir.get_area();
	double circumference = cir.get_circumference();

	std::cout << "Area: " << area << ". Circumference: " << circumference << std::endl;
}

/*________________________ oppgave 2 ________________________*/

void oppg2() {  // int main() {
	Circle circle(5);

	double area = circle.get_area();
	std::cout << "Arealet er lik " << area << std::endl;

	double circumference = circle.get_circumference();
	std::cout << "Omkretsen er lik " << circumference << std::endl;
}

/*________________________ oppgave 3 ________________________*/

double tax = 0.25;

class Commodity {
// attributter
private:
	string name;
	int id;
	double price = 0.0;
// metoder
public:
	Commodity(const string& name_, int id_, double price_) : name(name_), id(id_), price(price_) {}
	string get_name() const { return name; }
	int get_id() const { return id; }
	double get_price(int amount) const { return price * amount; }
	void set_price(double newPrice) { price = newPrice; }
	double get_price_with_sales_tax(int amount) const { return price * amount * tax; }

};

void oppg3()
{
	const double quantity = 2.5;
	Commodity commodity("Norvegia", 123, 73.50);

	cout << "Varenavn: " << commodity.get_name() << ", varenr: " << commodity.get_id() << " Pris pr enhet: " << commodity.get_price(quantity) << endl;

	cout << "Kilopris: " << commodity.get_price(quantity) << endl;
	cout << "Prisen for " << quantity << " kg er " << commodity.get_price(quantity) << " uten moms" << endl;
	cout << "Prisen for " << quantity << " kg er " << commodity.get_price_with_sales_tax(quantity) << " med moms" << endl;

	commodity.set_price(79.60);
	cout << "Ny kilopris: " << commodity.get_price(quantity) << endl;
	cout << "Prisen for " << quantity << " kg er " << commodity.get_price(quantity) << " uten moms" << endl;
	cout << "Prisen for " << quantity << " kg er " << commodity.get_price_with_sales_tax(quantity) << " med moms" << endl;
}


/*________________________ oppgave 4 ________________________*/

void oppg4()
{
	cout << endl << "a" << endl;
	// a) Les inn tre ord fra brukeren. Kall variablene word1, word2 og word3.
	string word1, word2, word3;

	cout << "Skriv inn 3 ord:  (del med space)" << endl;
	
	cin >> word1;
	cin >> word2;
	cin >> word3;
	
	cout << "ord1 = " << word1 << "\nord2 = " << word2 << "\nord3 = " << word3 << endl;

	cout << endl << "b" << endl;
	// b) Lag en streng der du skjøter sammen disse med mellomrom mellom og punktum til slutt.Kall variabelen sentence.Skriv ut.
	string sentence = word1 +" "+ word2 +" "+ word3 +".";
	cout << sentence << endl;

	cout << endl << "c" << endl;
	// c) Skriv ut lengden til hvert enkelt ord, og lengden til setningen som helhet.
	int length1 = word1.length();
	int length2 = word2.length();
	int length3 = word3.length();
	cout << "lenger: ord1: " << length1 << ". ord2: " << length2 << ". ord3: " << length3 << endl;
	cout << "lengden paa setningen: " << sentence.length() << endl;

	cout << endl << "d" << endl;
	// d) Lag en kopi av sentence.Kall kopien sentence2.
	
	// når sentence blir endret vil ikke sentence2 bli endret.
	string sentence2 = sentence;

	cout << endl << "e" << endl;
	// e) Bytt ut tegn nr 10 - 12 i sentence2 med x’er.Tegnene nummereres fra og med 0. Husk å kontrollere at dette er gyldige posisjoner!Skriv ut sentence og sentence2.
	if (sentence2.length() > 11) {
		char* c = &sentence2[0];
		for (int i = 0; i < 13; i++) {
			if (i >= 10) {
				*(c + i) = 'x';
			}
		}
	}
	cout << sentence2 << endl;

	cout << endl << "f" << endl;
	// f) Lagre de fem første tegnene i sentence i objektet sentence_start.Skriv ut sentence og sentence_start. (Husk kontroll av gyldige posisjoner)
	string sentence_start;
	if (sentence.length() >= 5) {
		char* c = &sentence[0];
		for (int i = 0; i < 5; i++) {
			sentence_start.push_back(*(c + i));
		}
	}
	cout << sentence << endl;
	cout << sentence_start << endl;

	cout << endl << "g" << endl;
	// g) Finn ut om sentence inneholder ordet "hallo".Skriv ut resultatet.
	string hallo = "hallo";
	bool found = false;

	for (int i = 0; i<sentence.length(); i++)
	{
		char c = sentence[i];
		if (c == 'h')
		{
			for (int j = 1; j < hallo.length(); j++)
			{
				if (sentence[i+j] != hallo[j])
				{
					break;
				}
				if (j == hallo.length() - 1)
				{
					found = true;
				}
			}
		}
	}

	if (found)
	{
		cout << "setning \"" << sentence << "\" inneholder ordet " << hallo << endl;
	}
	else
	{
		cout << "setning \"" << sentence << "\" inneholder IKKE ordet " << hallo << endl;
	}

	cout << endl << "h" << endl;
	// h) Finn alle forekomster i sentence av strengen "er".Skriv ut resultatet.
	int hits = 0;
	
	for (int i = 0; i < sentence.length()-1; i++)
	{
		if (sentence[i] == 'e' && sentence[i+1] == 'r')
		{
			hits++;
		}
	}

	cout << "Setningen inneholder \"er\" " << hits << " ganger." << endl;
}