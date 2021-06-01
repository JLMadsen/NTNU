import java.io.*;
import java.net.*;
import java.util.Scanner;

class Klient {

    final static int PORTNR = 1250;
    final static String hostName = "localhost";

	public static void main(String[] args) throws IOException
	{
		Scanner input = new Scanner(System.in);

		// connect to server
		Socket connection = new Socket(hostName, PORTNR);

		// IO objects
		InputStreamReader readConnection = new InputStreamReader(connection.getInputStream());
		BufferedReader reader            = new BufferedReader(readConnection);
		PrintWriter writer               = new PrintWriter(connection.getOutputStream(), true);

		// read connection message
		String connectionMessage = reader.readLine();
		System.out.println(connectionMessage + "\n");

		String content = input.nextLine();
		while(!content.equals(""))
		{
			writer.println(content);

			String response = reader.readLine();
			System.out.println("From server: " + response);

			content = input.nextLine();
		}

		reader.close();
		writer.close();
		connection.close();
		System.out.println("Connection closed");
	}
}