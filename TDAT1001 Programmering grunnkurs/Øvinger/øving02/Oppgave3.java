// oppgave side 113

import static javax.swing.JOptionPane.*;
class Oppgave3{
	public static void main(String[] args){
		String årlest = showInputDialog("årstall:");
		int år = Integer.parseInt(årlest);
		if((år%100)==0){
			if((år%400)==0){
				showMessageDialog(null, ""+ år +" er skuddår.");
			}
			else{
				showMessageDialog(null, ""+ år +" er ikke skuddår.");
			}
		}else{
			if((år%4)==0){
				showMessageDialog(null, ""+ år +" er skuddår.");
			}
			else{
				showMessageDialog(null, ""+ år +" er ikke skuddår.");
			}
		}
	}
}

