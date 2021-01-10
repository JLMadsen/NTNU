import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

	final static int PORTNR = 1250;

	public static void main(String[] args)
	{
		Socket socket = null;

		try 
		{
			ServerSocket serverSocket = new ServerSocket(PORTNR);

			while(true)
			{
				socket = serverSocket.accept();
				Thread clientThread = new ClientHandler(socket);
				clientThread.start();
			}

		} catch(IOException ioe) {

        }
	}
}