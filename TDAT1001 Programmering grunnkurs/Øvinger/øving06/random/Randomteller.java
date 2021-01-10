import static javax.swing.JOptionPane.*;
class Randomteller{
	public static void main(String[] args) {
		String read = showInputDialog("Hvor mange ganger vil du kjøre?");
		int run = Integer.parseInt(read);
		java.util.Random random = new java.util.Random();  // lager en ny random klasse/objekt
		int[] number = new int[10]; // deklarerer tabellen
		for(int counter = 0; counter <= run; counter++) {  // For løkke
			int i = random.nextInt(10);  // henter random tall
			number[i]++;
		}
		for(int n = 0; n<number.length; n++) {  // print løkke
			int star = (int)((number[n]*100/(double)run)+0.5);  // runder opp eller ned
			int j = 0;
			String stars = " ";
			while (j < star) {
				stars += "*";
				j++;
			}
			System.out.println("Antall "+ (n) +" er "+ number[n] +" "+ stars);

		}
	}
}