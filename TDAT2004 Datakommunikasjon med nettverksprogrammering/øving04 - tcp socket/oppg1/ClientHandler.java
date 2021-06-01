import java.io.*;
import java.net.*;

class ClientHandler extends Thread
{
	private Socket connection;
	
	public ClientHandler(Socket connection)
	{
		this.connection = connection;
	}

	@Override
	public void run()
	{
		try
		{
			// IO objects
			InputStreamReader readConnection = new InputStreamReader(connection.getInputStream());
			BufferedReader reader            = new BufferedReader(readConnection);
			PrintWriter writer               = new PrintWriter(connection.getOutputStream(), true);

			System.out.println("Connection established\n");
			writer.println("Connected to server");

			String content = reader.readLine();
			while(content != null)
			{
				System.out.println("Klient sent: "+ content);

				String[] numbers = content.split(" ");

				int a = Integer.parseInt( numbers[0] );
				int b = Integer.parseInt( numbers[1] );

				int result = a + b;

				writer.println(result);

				System.out.println("Sending " + result);
				content = reader.readLine();
			}

			reader.close();
			writer.close();
			connection.close();
			System.out.println("\nConnection closed");
		} 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
	}
}