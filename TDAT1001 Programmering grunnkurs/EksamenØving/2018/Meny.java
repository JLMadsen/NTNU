import static javax.swing.JOptionPane.*;

class Meny {

	private static final String[] MULIGHETER = {"MULIGHET1", "MULIGHET2", "MULIGHET3", "MULIGHET4", "MULIGHET5", "MULIGHET6",};
	private static final int MULIGHET1 = 0;
	private static final int MULIGHET2 = 1;
	private static final int MULIGHET3 = 2;
	private static final int MULIGHET4 = 3;
	private static final int MULIGHET5 = 4;
	private static final int MULIGHET6 = 5;

	public Meny(){

	}
	public int lesValg() {
		int valg = showOptionDialog(null,"Beskrivelse", "tittel p� boks", 0, PLAIN_MESSAGE, null, MULIGHETER, MULIGHETER[0]);
		return valg;
	}
