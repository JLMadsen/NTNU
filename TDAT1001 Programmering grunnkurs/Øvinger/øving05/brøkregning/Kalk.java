import static javax.swing.JOptionPane.*;
class Kalk{
	public static void main(String[] args) {
		Brok b1 = new Brok(3000, 4);                       // Kaller 2 objekter med konstruktørene
		Brok b2 = new Brok(2);                          // dersom object bare får teller, vil nevner settes til 1
		String lest = showInputDialog("Hva vil du gjøre?: \n1) Addere. \n2) Subtrahere. \n3) Multiplisere. \n4) Dividere.");  // Input for å velge operatør
		int i = Integer.parseInt(lest);
		switch(i){                                      // Switch som utfører forskjellige metoder i henhold til input
			case 1:
			b1.add(b2);  // addisjon
			break;
			case 2:
			b1.sub(b2);  // subtraksjon
			break;
			case 3:
			b1.mul(b2);  // multiplikasjon
			break;
			case 4:
			b1.div(b2);  // divisjon
			break;
			default:
			System.out.print("Feil input.");           // Dersom input ikke er 1,2,3, eller 4 vil den printe en feilmelding
			return;
		}
		b1.forkort();
		int teller = b1.getTeller();
		int nevner = b1.getNevner();

		if(nevner==1){                                                             // if nevner er lik 1
			System.out.println("Svar: "+ teller);                                  // skriv ut som et helt tall
		}else{																	   // hvis ikke
			System.out.println("       "+ teller +"\nsvar:  --\n       "+ nevner); // skriv ut som brøk
		}
		System.out.println("----");
		System.out.println(b1.toString());       // Printer ut objektene
		System.out.println(b2.toString());
	}
}