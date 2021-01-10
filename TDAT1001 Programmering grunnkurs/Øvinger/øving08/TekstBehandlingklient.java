// Øving 8 oppg 8.16.3

import static javax.swing.JOptionPane.*;
class TekstBehandlingklient {
	public static void main(String[] args) {
		String read1 = showInputDialog("Tekst:");
		TekstBehandling text = new TekstBehandling(read1);
		System.out.println(read1);
		while(true){
			String read2 = showInputDialog("Velg handling: \n1) Finn antall ord i teksten. \n2) Finn gjennomsnittlig ordlengde. \n3) Finn gjennomsnitt mengde ord per setning. ( . ! : ? ) \n4) Bytt ut ett ord med ett annet. \n5) Print den opprinnelige teksten. \n6) Print teksten med store bokstaver.");
			int choice1 = Integer.parseInt(read2);
			switch(choice1){
				case 1:
				System.out.println("Det er "+ text.getAntallOrd() +" ord.");
				break;
				case 2: 
				System.out.println("Gjennomsnittslengden per ord er "+ text.getGjennomsnittLengde());
				break;
				case 3:
				System.out.println("Gjennomsnitt for antall ord per setning er "+ text.getGjennomsnittPeriode());
				break;
				case 4:
				String ord1 = showInputDialog("Hvilket ord vil du bytte ut?");
				String ord2 = showInputDialog("Hva vil du bytte det ut med?");
				System.out.println("Bytter ut "+ ord1.toLowerCase() +" med "+ ord2.toLowerCase() +". \nNy tekst: "+ text.byttOrd(ord1, ord2));
				break;
				case 5:
				System.out.println("Teksten uten endring: "+ text.getTekst());
				break;
				case 6:
				System.out.println("Teksten i store bokstaver: "+ text.getStore());
				break;
				default:
				System.out.println("Feil input, avslutter.");
				return;
			}
		}
	}
}