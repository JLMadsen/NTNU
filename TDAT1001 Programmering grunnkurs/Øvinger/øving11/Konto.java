import java.io.*;

class Konto 
{ 
	private double balance; // saldo

	private static String fileName1 = "Saldo.txt"; // Filnavn på saldo fil
	private static String fileName2 = "Transaksjon.txt"; // filnavn på transaksjon fil

	public Konto() // kontruktør
	{
		String idk = "";
		String currentLine = "";
		try
		{
			FileReader a = new FileReader(fileName1);
			BufferedReader reader = new BufferedReader(a); // Finner den siste linjen i filen
			while((currentLine = reader.readLine()) != null){ // bytter ut stringen med linjen den leser helt til den kommer til enden
			idk = currentLine;
			}
			reader.close();
		}
		catch(IOException e)
		{
			System.out.println("Error: Ca");// feilmelding med feilkode
		} 
		balance = Double.parseDouble(idk);
	}
	public String getCashMoneyFlow() // metode for å hente saldo
	{
		String idk = "";
		String currentLine = "";
		try
		{
			FileReader a = new FileReader(fileName1);
			BufferedReader reader = new BufferedReader(a); // Finner den siste linjen i filen
			while((currentLine = reader.readLine()) != null){ // bytter ut stringen med linjen den leser helt til den kommer til enden
			idk = currentLine;
			}
			reader.close();
		}
		catch(IOException e)
		{
			System.out.println("Error: Ca");// feilmelding med feilkode
		} 
		return idk;
	}
	public void transaction(double g) // metode med input for å utføre transaksjon
	{
		try
		{
			double balance3 = balance;
			balance = balance+g;
			if(balance >= 0)
			{
				FileWriter c = new FileWriter(fileName1, true);
				PrintWriter writer = new PrintWriter(new BufferedWriter(c));
				writer.println(balance);  // skriver ny saldo i Saldo.txt
				writer.close();

				FileWriter b = new FileWriter(fileName2, true);
				PrintWriter writer2 = new PrintWriter(new BufferedWriter(b));
				if(g > 0)
				{ 						// sjekker om verdien er positiv eller negativ
					writer2.println("I "+ g);   // hvis positiv skriver den I
					System.out.println("Setter inn "+ g +" kr.");
				} 
				else 
				{
					double gg = 0-g;
					writer2.println("U "+ gg);	// hvis negativ skriver den U
					System.out.println("Tar ut "+ gg +" kr.");
				}
				writer2.close();
				System.out.println("Transaksjon fullført, ny saldo: "+ balance +" kr.");
			} 
			else 
			{
				balance = balance3;
				System.out.println("Saldoen ble negativ, transaksjon avbrutt.");

			/*	PrintWriter writer2 = new PrintWriter(fileName2, "UTF-8");
				writer2.close();*/
			}
		}
		catch(IOException e)
		{
			System.out.println("Error: Tr");
		}
	}
	public void reset() // resetter filene | kun for testing
	{
		try
		{
			PrintWriter writer = new PrintWriter(fileName1, "UTF-8"); // erstatter saldo.txt med en blank saldo.txt
			balance = 0;
			writer.println(balance);
			writer.close();
			PrintWriter writer2 = new PrintWriter(fileName2, "UTF-8");
			writer2.close();
		}
		catch(IOException e)
		{
			System.out.println("Error: Reeeeee");
		}
	}
	public void setSaldo(double f) // setter saldoen | kun for testing
	{
		try
		{
			balance = f;
			FileWriter b = new FileWriter(fileName1, true); // åpner fil 1
			PrintWriter writer = new PrintWriter(new BufferedWriter(b));
			writer.println(balance); // skriver saldo på fil 1
			writer.close(); // lukker data strømmen
			System.out.println("Den nye saldoen er: "+ balance +" kr.");
		}
		catch(IOException e)
		{
			System.out.println("Error: Sa");
		}
	}
}