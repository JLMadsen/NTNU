import java.util.*;

class OppgaveOversiktList{

    private ArrayList<Student> students = new ArrayList<Student>();
    private int counter = 0;

    public OppgaveOversiktList(){
        Iterator<Student> iterator = students.iterator();
        while(iterator.hasNext()){
            counter++;
        }
    }
    public int getNumStud(){
        return counter;
    }
    public boolean newStudent(String name){
        if(index(name) != -1){
            return false;
        }
        Student idk = new Student(name);
        students.add(idk);
        counter++;
        return true;
    }
    public int index(String name){
        for(int i=0; i<counter; i++){
            if(students.get(i).getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }
    public void increaseTask(int n, String name){
        int i = index(name);
        if(i == -1){
            System.out.println("Ingen student med det navnet.");
            return;
        }
        int old = students.get(i).getTask();
        try{
            students.get(i).setTask((old+n));
        } catch (IllegalArgumentException e){
            System.out.println("Kan ikke ha negativt antall oppgaver.");
        }
    }
    public String[] finnAlleNavn(){
        Iterator<Student> iterator = students.iterator();
        String[] re = new String[counter];
        int i = 0;
        while(iterator.hasNext()){
            re[i] = iterator.next().getName();
            i++;
        }
        return re;
    }
    public String toString(){
        Iterator<Student> iterator = students.iterator();
        String re = "";
        while(iterator.hasNext()){
            re += iterator.next().toString();
        }
        return re;
    }
    public int getTaskStud(String name){
        int i = index(name);
        if(i == -1){
            return i;
        }
        return students.get(i).getTask();
    }
}