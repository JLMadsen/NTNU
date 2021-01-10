import static javax.swing.JOptionPane.*;
class oppgave2{
	public static void main(String[] args){
		String timerlest = showInputDialog("timer");
		String minutterlest = showInputDialog("minutter");
		String sekunderlest = showInputDialog("sekunder");
		double timer = Double.parseDouble (timerlest);
		double minutter = Double.parseDouble (minutterlest);
		double sekunder = Double.parseDouble (sekunderlest);
		double totalsek = ((timer*3600)+(minutter*60)+sekunder);
		String utskrift = (""+ timer +" timer, "+ minutter +" minutter og "+ sekunder +" sekunder blir totalt "+ totalsek +" sekunder");
		showMessageDialog (null, utskrift);
	}
}
