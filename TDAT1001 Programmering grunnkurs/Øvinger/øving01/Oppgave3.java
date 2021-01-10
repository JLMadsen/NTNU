import static javax.swing.JOptionPane.*;
class Oppgave3{
	public static void main(String[] args){
		String sekunderlest = showInputDialog("Sekunder");
		int totalsekunder = Integer.parseInt(sekunderlest);
		int timer = totalsekunder/3600;
		int minutter = ((totalsekunder-(timer*3600))/60);
		int sekunder = (totalsekunder-(timer*3600)-(minutter*60));
		String utskrift = (""+ totalsekunder +" tilsvøæårer "+ timer +" timer, "+ minutter +" minutter og "+ sekunder +" sekunder");
		showMessageDialog(null, utskrift);
	}
}
