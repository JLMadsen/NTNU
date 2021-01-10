import static javax.swing.JOptionPane.*;
import java.text.*;
class valutakalk {
	public static void main(String[] args) {
		valuta USD = new valuta("USD", 0.119301);
		valuta EUR = new valuta("EUR", 0.102723);
		valuta SEK = new valuta("SEK", 1.09069);
		valuta NOK = new valuta("NOK", 1.0);

		double kursUSD = USD.getKurs();
		double kursEUR = EUR.getKurs();
		double kursSEK = SEK.getKurs();
		double kursNOK = NOK.getKurs();

		String read1 = showInputDialog("Hvilken valuta vil du konverte fra? \n1) USD \n2) EUR \n3) SEK \n4) NOK \n5) Avslutt");
		int i = Integer.parseInt(read1);

		double kurs1 = 0;
		double kurs2 = 0;
		String val1 = ".";
		String read2 = ".";
		DecimalFormat df = new DecimalFormat("0.00");

		switch(i) {
			case 1:
			read2 = showInputDialog("Hvilken valuta vil du konverte til? \n2) EUR \n3) SEK \n4) NOK");
			kurs1 = kursUSD;
			val1 = "dollar";
			break;
			case 2:
			read2 = showInputDialog("Hvilken valuta vil du konverte til? \n1) USD \n3) SEK \n4) NOK");
			kurs1 = kursEUR;
			val1 = "euro";
			break;
			case 3:
			read2 = showInputDialog("Hvilken valuta vil du konverte til? \n1) USD \n2) EUR  \n4) NOK");
			kurs1 = kursSEK;
			val1 = "svenske kroner";
			break;
			case 4:
			read2 = showInputDialog("Hvilken valuta vil du konverte til? \n1) USD \n2) EUR \n3) SEK ");
			kurs1 = kursNOK;
			val1 = "norske kroner";
			break;
			case 5:
			System.out.println("Program avsluttes.");
			return;
			default:
			System.out.println("Feil input.");
		}

		int j = Integer.parseInt(read2);
		String val2 = ".";

		switch(j) {
			case 1:
			kurs2 = kursUSD;
			val2 = "dollar";
			break;
			case 2:
			kurs2 = kursEUR;
			val2 = "euro";
			break;
			case 3:
			kurs2 = kursSEK;
			val2 = "svenske kroner";
			break;
			case 4:
			kurs2 = kursNOK;
			val2 = "norske kroner";
			break;
			default:
			System.out.println("Feil input.");
			return;
		}
		String read3 = showInputDialog("Hvor mye vil du gjøre om?");
		int n = Integer.parseInt(read3);
		double output = (n/kurs1)*kurs2;
		System.out.println(""+ n +" "+ val1 +" tilsvarer "+ df.format(output) +" "+ val2 +"");
		System.out.println("____________");
		System.out.println(USD.toString());
		System.out.println(EUR.toString());
		System.out.println(NOK.toString());
		System.out.println(SEK.toString());

	}
}