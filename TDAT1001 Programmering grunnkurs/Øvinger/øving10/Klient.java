import static javax.swing.JOptionPane.*;
class Klient{
	public static void main(String[] args) {
		Student a = new Student("Bob Kaare", 3);
		Student b = new Student("Rolf Johnny"); 
		Student c = new Student("Ole Freddy", 4);

		Student[] d = {a, b, c};

		Oppgaveoversikt e = new Oppgaveoversikt(d);

		while(true){
			String read1 = showInputDialog("Hva vil du gjore?\n1) Finn antall studenter. \n2) Finn antall oppgaver en bestemt student har lost. \n3) Registrer ny student. \n4) Ok antall oppgaver gjort for en student. \n5) toString() \n6) Avslutt.");
			int choice1 = Integer.parseInt(read1);
			switch(choice1){
				case 1:
				System.out.println("Det er "+ e.getAmountStud() +" studenter.");
				break;
				case 2:
				String input1 = "Hvilken student vil du sjekke?";
				for(int i = 0; i < e.getAmountStud(); i++){
					input1 += "\n"+ (i+1) +") "+ e.getName(i);
				}
				String read2 = showInputDialog(input1);
				int stud1 = Integer.parseInt(read2)-1;
				System.out.println(e.getName(stud1) +" har gjort "+ e.getTasksOf(stud1) +" oppgaver.");
				break;
				case 3:
				String read3 = showInputDialog("Hva heter den nye studenten?");
				String read4 = showInputDialog("Hvor mange oppgaver har studenten gjort?");
				int tasks1 = Integer.parseInt(read4);
				e.newStudent(read3, tasks1);
				break;
				case 4:
				String input2 = "Hvem har gjort oppgavene?";
				for(int i = 0; i < e.getAmountStud(); i++){
					input2 += "\n"+ (i+1) +") "+ e.getName(i);
				}
				String read5 = showInputDialog(input2);
				int stud2 = Integer.parseInt(read5)-1;
				String read6 = showInputDialog("Hvor mange oppgaver har "+ e.getName(stud2) +" gjort?");
				int tasks2 = Integer.parseInt(read6);
				e.increaseTasksOf(stud2, tasks2);
				System.out.println(e.getName(stud2) +" har nå gjort "+ e.getTasksOf(stud2) +" oppgaver.");
				break;
				case 5:
				for(int j = 0; j < e.getAmountStud(); j++){
					System.out.println(e.toString(j));
				}
				break;
				case 6:
				System.out.println("Avslutter.");
				return;
				default:
				System.out.println("Feil input, avslutter.");
				return;
			}
		}
	}
}