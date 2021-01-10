import static javax.swing.JOptionPane.*;

class primtall{
	public static void main(String[] args){
		String lest=".";
		while(lest!=null){
			lest = showInputDialog("Skriv tall, avslutt med ESC.");
			int tall = Integer.parseInt(lest);
			int i = 2;

			int j = 0;  // ikke primtall
			int n = 0;  // primtall

			while(i<=(tall-1)){
				if(tall%i==0){
					j++;
					break;
				} else{
					n++;
				}
				i++;
			}
			if(n==(tall-2)){
				System.out.println(""+ tall +" er primtall.");
			}else{
				System.out.println(""+ tall +" er ikke primtall.");
			}
		}
	}
}
