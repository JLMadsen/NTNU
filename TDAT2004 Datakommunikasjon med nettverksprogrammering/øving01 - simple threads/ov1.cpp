// g++ ov1rework.cpp -o ov1.exe -pthread && ./ov1.exe

#include <vector>
#include <iostream>
#include <thread>
#include <algorithm>
#include <math.h>
#include <mutex>
#include <chrono>

#include "../../utils/Timer.cpp"

using namespace std;

mutex mtx;
vector<unsigned int> primes; 

bool primeCheck(unsigned int num)
{
	if(num<=1) 
		return false;
	for(unsigned int i=2; i<=sqrt(num); i++)
		if(num%i==0)
			return false;
	return true;
}

void findPrimes(unsigned int from, unsigned int to)
{
	vector<unsigned int> threadVec;
	for(unsigned int i=from; i<to; i++)
	{
		if(primeCheck(i))
		{
			threadVec.push_back(i);
		}
	}
	mtx.lock();
	primes.insert(primes.end(), threadVec.begin(), threadVec.end());
	mtx.unlock();
	return;
}

int main()
{
	unsigned int from, to, nthreads = 0;

	cout << "Lower limit: "; cin >> from;
	cout << "Upper limit: "; cin >> to;
	if(from > to)
	{
		cout << "Upper < Lower" << endl;
		return 1;
	}

	cout << "Thread count: "; cin >> nthreads;
	if(nthreads <= 0)
	{
		cout << "Invalid thread count." << endl;
		return 1;
	}
	if(nthreads > to-from)
		nthreads = to-from;

	char print;
	cout << "Print primes? [y/n] "; cin >> print; 
 	
	// benchmark time with 1 thread
	Timer timer = Timer();
	timer.set();

	//findPrimes(from, to);
	
	double singleDuration = timer.stop();
	
	// find primes with more threads
	cout << "\nFinding primes between " << from << " to " << to << " with " << nthreads << " threads." << endl;
	primes = vector<unsigned int>();
	timer.set();

	// split from to area based on n threads
	unsigned int range = (to-from)/nthreads;
	
	// assign threads
	thread threads[nthreads];
	for(int i=0; i<nthreads; i++)
	{
	    unsigned int tfrom = from+range*i;
	    unsigned int tto   = tfrom+range-1;//from+range*(i+1)-1;
	    if(i == nthreads-1) tto = to;
    	    cout << "starting thread " << (i+1) << " : " << tfrom << " to " << tto << endl;
	    threads[i] = thread(findPrimes, tfrom, tto);
	}
	
	// wait for threads
	for(auto& th : threads) th.join();
	double threadDuration = timer.stop();
	
	// sort result
	if(print!='n')
		sort(primes.begin(), primes.end());

	// print
	if(print != 'n')
	{
		cout << "\nResult:" << endl;
		for(unsigned int prime : primes)
			cout << prime << ", ";
		cout << endl;
	}
	
	cout << "\nSingle Thread timing: " << singleDuration << " ms" << endl; 
	cout << "Multi- Thread timing: " << threadDuration << " ms" << endl;
        cout << "primes: " << primes.size() << endl;	
}
