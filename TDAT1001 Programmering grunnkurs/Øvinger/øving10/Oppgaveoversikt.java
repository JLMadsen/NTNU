import java.util.Arrays;

class Oppgaveoversikt{
	private Student[] person;
	private int amountStud = 0;

	public Oppgaveoversikt(Student[] person){
		this.person = person;
	}
	public int getAmountStud(){
		amountStud = person.length;
		return amountStud;
	}
	public String getName(int stud){
		return person[stud].getName();
	}
	public int getTasksOf(int stud){
		return person[stud].getTasks();
	}
	public void newStudent(String name, int tasks){
		person = Arrays.copyOf(person, person.length+1);
		Student g = new Student(name, tasks);
		person[person.length-1] = g;
	}
	public void increaseTasksOf(int stud, int tasks){
		person[stud].increaseTasks(tasks);
	}
	public String toString(int stud){
		return person[stud].toString();
	}
}