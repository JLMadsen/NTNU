class Student{
	private String name;
	private int tasks;

	public Student(String name, int tasks){
		this.name = name;
		this.tasks = tasks;
	}
	public Student(String name){
		this.name = name;
		this.tasks = 0;
	}
	public String getName(){
		return name;
	}
	public int getTasks(){
		return tasks;
	}
	public void increaseTasks(int increase){
		tasks += increase;
	}
	public String toString(){
		return name +" "+ tasks;
	}
}