class OppgaveOversikt{
    
    private Student[] students = new Student[5];
    private int counter = 0;

    public OppgaveOversikt(Student[] students){
        this.students = students;
        for(int i=0; i<students.length; i++){
            if(students[i]!=null){
                counter++;
            }
        }
    }
    public OppgaveOversikt(){ }
    public boolean newStudent(String newName){
        if(index(newName)!=-1){ // hvis navnet finnes returneres "false"
            return false;
        }
        if(counter == students.length){ // hvis tabellen er full utvides den
            expand();
        }
        Student idk = new Student(newName);
        students[counter] = idk;
        counter++;
        return true;
    }
    public void expand(){
        Student[] temp = new Student[students.length+5];
        for(int i=0; i<counter; i++){
            temp[i] = students[i];
        }
        students = temp;
    }
    public int getNumStud(){
        return counter;
    }
    public int getTaskStud(String name){
        int i = index(name);
        if(i == -1){
            return i;
        }
        return students[i].getTask();
    }
    public void increaseTask(int n, String name){
        int i = index(name);
        if(i == -1){
            System.out.println("Ingen student men det navnet.");
        } else {
            try{
                students[i].setTask((students[i].getTask()+n));
            } catch (IllegalArgumentException e){
                System.out.println("Kan ikke ha negativt antall oppgaver.");
            }
            
        }
    }
    public int index(String name){
        for(int i=0; i<counter; i++){
            if(students[i] != null){ 
                if(students[i].getName().equalsIgnoreCase(name)){
                    return i;
                }
            }
        }
        return -1;
    }
    public String[] finnAlleNavn(){
        String[] idk = new String[counter];
        for(int i=0; i<counter; i++){
            idk[i] = students[i].getName();
        }
        return idk;
    }
    public String toString(){
        String re = "";
        for(int i=0; i<counter; i++){
            re += (i+1) +". Navn: "+ students[i].getName() +". Oppgaver: "+ students[i].getTask() +".";
        }
        return re;
    }
}