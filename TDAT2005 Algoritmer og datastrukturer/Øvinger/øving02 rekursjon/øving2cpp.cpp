double rek1(double x, int n);
double rek2(double x, int n);

#include <iostream>
#include <math.h>
#include <chrono>

using namespace std;

int main()
{
	double x = 1.001;
	int n = 4000;

	auto time1 = chrono::steady_clock::now();

	double res1 = rek1(x, n);

	auto time2 = chrono::steady_clock::now();

	double res2 = rek2(x, n);

	auto time3 = chrono::steady_clock::now();

	double res3 = pow(x, n);

	auto time4 = chrono::steady_clock::now();

	cout << "\n--> Oppgave 1:\n" << res1 << endl;
	cout << "\n--> Oppgave 2:\n" << res2 << endl;
	cout << "\n--> Innebygd metode:\n" << res3 << endl;
	
	cout << "\noppgave 1 tid: " << chrono::duration <double, nano>(time2 - time1).count() << " ns" << endl;
	cout << "oppgave 2 tid: " << chrono::duration <double, nano>(time3 - time2).count() << " ns" << endl;
	cout << "innebygget tid: " << chrono::duration <double, nano>(time4 - time3).count() << " ns" << endl;

	return 0;
}

/* _______________ oppgave 1 _______________ */

double rek1(double x, int n)
{
	if (n == 0) {
		return 1;
	}
	return x * rek1(x, n - 1);
}

/* _______________ oppgave 2 _______________ */

double rek2(double x, int n)
{
	if (n == 0) {
		return 1;
	}
	if (n % 2 == 0) {
		return rek2(x*x, n/2);
	}
	return rek2(x*x, (n-1)/2) * x;
}