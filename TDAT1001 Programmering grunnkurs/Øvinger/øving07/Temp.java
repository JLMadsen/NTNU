// Øving 7 oppg 9.11.2

import static javax.swing.JOptionPane.*;
import java.text.*;
class Temp {
	public static void main(String[] args){
		Temperatur tabell = new Temperatur();
		DecimalFormat df = new DecimalFormat("0.00");      // Desimal formatering
		String lest = " ";
		while(lest != null){
			lest = showInputDialog("Hva vil du finne? \n1: Gjennomsnitt temperatur for hver dag. \n2: Gjennomsnitt temperatur for hver time i døgnet. \n3: Gjennomsnitt temperaturen for hele måneden. \n4: Antall dager med temperatur mellom intervaller.");
			int valg = Integer.parseInt(lest);
			int lengdeDag = tabell.getTabellLengthDag();
			int lengdeTime = tabell.getTabellLengthTime();
			double[] tabelldag = tabell.getGjennomsnittDag();
			double[] tabelltime = tabell.getGjennomsnittTime();
			switch(valg){
				case 1:
				for(int i = 0; i < lengdeDag; i++){
					System.out.println("Gjennomsnitts temperatur for dag "+ (i+1) +" er "+ df.format(tabelldag[i]));
				}
				break;
				case 2:
				for(int i = 0; i < lengdeTime; i++){
					System.out.println("Gjennomsnitts temperatur for time "+ (i+1) +" er "+ df.format(tabelltime[i]));
				}
				break;
				case 3:
				System.out.println("Gjennomsnitts temperaturen for hele måneden er "+ df.format(tabell.getGjennomsnittManed()));
				break;
				case 4:
				int[] tabellg = tabell.getGruppe();
				System.out.println("Det er "+ tabellg[0] +" dager som er er kaldere enn -5 grader.");	
				System.out.println("Det er "+ tabellg[1] +" dager som er er mellom -5 og 0 grader.");
				System.out.println("Det er "+ tabellg[2] +" dager som er er mellom 0 og 5 grader.");
				System.out.println("Det er "+ tabellg[3] +" dager som er er mellom 5 og 10 grader.");
				System.out.println("Det er "+ tabellg[4] +" dager som er er over 10 grader.");
				break;
				case 5:
				for(int i = 0; i < lengdeDag; i++){
					for(int j = 0; j < lengdeTime; j++){
						System.out.print(""+ df.format(tabell.getTemp(i, j))+" | ");
					}
				}
				break;
				default:
				System.out.println("Feil input.");
				return;
			}
		}
	}
}