class Student{

    private final String name;
    private int task;

    public Student(String name, int task){
        this.name = name;
        this.task = task;
    }
    public Student(String name){
        this.name = name;
        task = 0;
    }
    public String getName(){
        return name; 
    }
    public int getTask(){ 
        return task; 
    }
    public void setTask(int newTask){
        if(newTask < 0){
            throw new IllegalArgumentException();
        } else {
            task = newTask;
        }
    }
    public String toString(){ return "Navn: "+ name +". Oppgaver: "+ task +".\n"; }
}