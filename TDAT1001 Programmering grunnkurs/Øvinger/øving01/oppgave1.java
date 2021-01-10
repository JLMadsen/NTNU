import static javax.swing.JOptionPane.*;
class oppgave1{
	public static void main(String[] args){
		String tommerlest = showInputDialog("Lengde (tommer)");
		double tommer = Double.parseDouble(tommerlest);
		double centimeter = tommer*2.54;
		String utskrift =""+ tommer +" tommer er "+ centimeter +" centimeter.";
		showMessageDialog(null, utskrift);
	}
}
