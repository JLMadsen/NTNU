import static javax.swing.JOptionPane.*;
import java.io.*;

class Klient
{
	public static void main(String[] args)
	{
		String fileName = "Saldo.txt"; // Filnavn
		Konto account = new Konto();
		while(true)
		{
			String read1 = showInputDialog("Hva vil du gjore? \n1) Hent Saldo. \n2) Transaksjon. \n3) Set saldo. (testing) \n4) Reset filer. (testing) \n5) Avslutt.");
			int choice1 = Integer.parseInt(read1);
			switch(choice1)
			{
				case 1:
				System.out.println("Saldo: "+ account.getCashMoneyFlow() +" kr."); // henter saldo
				break;

				case 2:
				int ans1 = showConfirmDialog(null, "Vil du sette inn penger?", "Transaksjon", YES_NO_OPTION); 
				if(ans1 == YES_OPTION)
				{
					String ans2 = showInputDialog("Hvor mye vil du sette inn?");
					double ans3 = Double.parseDouble(ans2);
					account.transaction(ans3);
				} 
				else 
				{
					String ans2 = showInputDialog("Hvor mye vil du ta ut?");
					double ans3 = Double.parseDouble(ans2);
					ans3 = 0 - ans3;
					account.transaction(ans3);
				}
				break;

				case 3:
				String read2 = showInputDialog("Hvor stor er saldoen?"); // setter ny saldo | kun for testing
				double generiskvariabelnavn = Double.parseDouble(read2);
				account.setSaldo(generiskvariabelnavn);
				break;

				case 4:
				account.reset();
				System.out.println("Saldo.txt er blank."); // lager nye filer | for testing
				break;

				case 5:
				System.out.println("Avslutter."); // avslutter programmet
				return;

				default:
				System.out.println("Feil input, avslutter."); // feil input, avslutter
				return;
			}
		}
	}
}