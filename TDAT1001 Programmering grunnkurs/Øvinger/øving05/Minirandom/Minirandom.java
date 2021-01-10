import java.text.*;
class Minirandom{
	// Variabler
	private double upperdouble;
	private double lowerdouble;
	private int upperint;
	private int lowerint;

	// Lager ett object i av Random
	private java.util.Random i = new java.util.Random();

	// Konstruktøren
	public Minirandom(double upperdouble, double lowerdouble, int upperint, int lowerint){
		this.upperdouble = upperdouble;
		this.lowerdouble = lowerdouble;
		this.lowerint = lowerint;
		this.upperint = upperint;
	}
	// Metoder
	public int getRandomint(){
		return i.nextInt(upperint - lowerint) + lowerint;  // Bruker metoden i.nextInt() inni metoden getRandomint()
	}
	public double getRandomdouble(){
		return i.nextDouble() * (upperdouble - lowerdouble) + lowerdouble;  // Bruker metoden i.nextDouble() inni metoden getRandomdouble()
	}
	public String toString(){
		return upperdouble +" "+ lowerdouble +" "+ upperint +" "+ lowerint; // metoden toString returnerer alle attributtene
	}

	// KLIENT PROGRAM ____________________________________________________________________________
	public static void main(String[] args) {
		Minirandom a = new Minirandom(10, 0, 100, 0);      // Lager et nytt objekt a med konstruktøren Minirandom()
		DecimalFormat df = new DecimalFormat("0.00");      // Desimal formatering
		int k = 0;                                         // Teller variabel
		while(k < 100){                                    // Løkke
			System.out.println("desimaltall: "+ df.format(a.getRandomdouble()) +". Heltall: "+ a.getRandomint() +"");  // Printer ut metodene
			k++;                                           // Øker teller 1 hver gang
		}
		if(k==100){
			System.out.println(a.toString());              // Printer toString()
		}
	}
}
