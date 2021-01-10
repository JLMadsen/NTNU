#include <iostream>
#include <mutex>
#include <vector>
#include <functional>
#include <thread>
#include <list>
#include <condition_variable>

using namespace std;

class Workers
{
private:
	unsigned int thread_count;
	vector<thread> threads;
	list<function<void()>> tasks;
	
	mutex task_mtx;
	condition_variable cv;
	
	bool fin = false;
	
public:
	Workers(unsigned int nthreads) : thread_count(nthreads) {};

	// main function that threads are running
	void doTasks()
	{
		cout << "thread start with id = " << this_thread::get_id() << endl;
		while(true)
		{
			function<void()> task;

			{
				// check if task availible
				unique_lock<mutex> lock(task_mtx);
				if(!tasks.empty())
				{
					task = *tasks.begin();
					tasks.pop_front();
				}
				else
				{
					if(fin)
						return;
			
					// if not finished, but no tasks, wait.
					cv.wait(lock);
				}
			}
			// execute task
			if(task)
				task();
		}	
	}

	void start()
	{
		for(unsigned int i=0; i<thread_count; i++)
			threads.push_back( thread(&Workers::doTasks, this));
	}

	void stop()
	{
		{
			lock_guard<mutex> lock(task_mtx);
			fin = true;
		}

		// notify all sleeping threads to return
		cv.notify_all();
		for(auto& t : threads)
		{
			if(t.joinable())
			{
				t.join();
			}
		}
	}

	void post(const function<void()>& t)
	{
		lock_guard<mutex> lock(task_mtx);
		tasks.emplace_back(t);

		// notify one thread to fetch new task
		cv.notify_one();
	}

	// post func after time
	void post_timeout(const function<void()>& t)
	{
		post([&t, this] 
		{
			this_thread::sleep_for(20ms);
			t();
		});
	}
};

// print lock for cleaner print
mutex ptx;

void func()
{
	this_thread::sleep_for(30ms);
	lock_guard<mutex> print_lock(ptx);
	cout << "task done by " << this_thread::get_id() << endl;
}

int main()
{
	cout << "Start worker 1 with 4 threads." << endl;
	Workers worker(4);
	worker.start();

	worker.post(func);
	worker.post(func);
	worker.post(func);
	worker.post(func);
	worker.post(func);
	worker.post(func);

	worker.post_timeout(func);
	worker.post_timeout(func);

	worker.stop();



	cout << "\nStart worker 2 with 1 thread." << endl;
	Workers worker2(1);
	worker2.start();

	worker2.post(func);
	worker2.post(func);

	worker2.stop();



	return 0;
}

