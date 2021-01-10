// Øving 9 oppg 11.10.1

import static javax.swing.JOptionPane.*;
class Klient{
	public static void main(String[] args) {
		Person person = new Person("Bob", "Kåre", 1975);
		ArbTaker arbtaker = new ArbTaker(person, 0, 1990, 21000, 30);
		while(true){
			String read1 = showInputDialog("Hva vil du finne: \n1) Hvor mye lonn trekkes per maaned. \n2) Brutto aarslonn. \n3) Arslonn etter skatt. \n4) Navn. \n5) Alder. \n6) Antall aar ansatt. \n7) Har personen vaert ansatt lenger enn x? \n8) Endre data. \n9) toString() \n10) Avslutt");
			int choice1 = Integer.parseInt(read1);
			switch (choice1){
				case 1:
				System.out.println(arbtaker.getTaxCut() +" kroner blir trukket hver måned.");
				break;
				case 2:
				System.out.println("Brutto årslønn: "+ arbtaker.getGrossYearSalary() +" kroner.");
				break;
				case 3:
				System.out.println("Årlønn etter skatt: "+ arbtaker.getYearSalary() +" kroner.");
				break;
				case 4:
				System.out.println("Navn: "+ arbtaker.getName());
				break;
				case 5:
				System.out.println("Alder: "+ arbtaker.getAge());
				break;
				case 6:
				System.out.println(arbtaker.getName() +" har vært ansatt i "+ arbtaker.getTimeHired() +" år.");
				break;
				case 7:
				String read2 = showInputDialog("Har han vært ansatt lenger enn?");
				int time = Integer.parseInt(read2);
				boolean longer = arbtaker.workedLonger(time);
				if(longer == true){
					System.out.println("Han har jobbet lenger enn "+ time +" år.");
				}else{
					System.out.println("Han har ikke jobbet lenger enn "+ time +" år.");
				}
				break;
				case 8:
				String read3 = showInputDialog("Hva vil du endre? \n1) Månedslønn. \n2) Skatteprosent.");
				int choice2 = Integer.parseInt(read3);
				switch (choice2) {
					case 1:
					String read4 = showInputDialog("Ny månedslønn:");
					double newmonthsalary = Double.parseDouble(read4);
					arbtaker.setSalary(newmonthsalary);
					System.out.println("Den nye månedslønnen er "+ arbtaker.getSalary() +" kroner.");	
					break;
					case 2:
					String read5 = showInputDialog("Ny skatteprosent:");
					double newtax = Double.parseDouble(read5);
					arbtaker.setTax(newtax);
					System.out.println("Den nye skatteprosenten er "+ arbtaker.getTax() +" prosent.");	
					break;
					default:
					System.out.println("Feil input, avslutter.");
					return;					
				}
				break;
				case 9:
				System.out.println(arbtaker.toString());
				break;
				case 10:
				System.out.println("Avslutter.");
				return;
				default:
				System.out.println("Feil input, Avslutter");
				return;

			}
		}
	}
}