import static javax.swing.JOptionPane.*;
class gangetabell {
	public static void main(String[] args){

		String gangelest1 = showInputDialog("Fra gangetabell:");
		int tall1 = Integer.parseInt(gangelest1);
		String gangelest2 = showInputDialog("Til gangetabell:");
		int tall2 = Integer.parseInt(gangelest2);

		for( ; tall1 <= tall2; tall1++){
			System.out.println("Gangetabell for tallet "+ tall1 +":");
			int n = 1;
			while(n <= 10){
				int gange = tall1*n;
				System.out.println(""+ tall1 +" * "+ n +" = "+ gange +"");
				n++;
			}
		}
	}
}

