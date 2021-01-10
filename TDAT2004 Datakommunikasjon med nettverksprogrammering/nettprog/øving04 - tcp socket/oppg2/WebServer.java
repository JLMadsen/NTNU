import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class WebServer
{
    // koble til på localhost:80
    private static final int PORTNR = 80;

    public static void main(String[] args)
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(PORTNR);
            System.out.println("Server start...");

            Socket socket = serverSocket.accept();

            InputStreamReader input = new InputStreamReader(socket.getInputStream());
            BufferedReader reader   = new BufferedReader(input);
            PrintWriter writer      = new PrintWriter(socket.getOutputStream(), true);

            // nettleseren vil sende informasjon, vi lagrere dette og sender tilbake til bruker
            ArrayList<String> headerLines = new ArrayList<String>();
            String content = reader.readLine();

            while(content!=null)
            {
                System.out.println("Client sent: " + content);

                if(content.equals(""))
                {
                    writer.println("HTTP/1.1 200 OK");
                    writer.println("Content-Type: text/html");
                    writer.println();
                    writer.println("<html><body>");

                    // velkomst melding
                    writer.println("<h1>Welcome to a Socket webpage</h1>");
                    writer.println("<h1>:)</h1>");
                    writer.println("<img src=\"https://www.nortelco.no/no/ELKO-Double-surface-mounted-socket-outlets-with-earth-schuko?pid=Native-ProductBase-MainProductImage&r_n_d=216077_&adjust=1&x=600&y=600&from=0&q=85&qm=85\" alt=\"socket\"/>");

                    // informasjon fra nettleser
                    writer.println("Headers from client: ");
                    writer.println("<ul>");

                    for (int i = 0; i < headerLines.size(); i++) 
                    {
                        writer.println("<li>" + headerLines.get(i) + "</li>");
                    }

                    writer.println("</ul>");

                    // avlsutter html filen
                    writer.println("</body></html>");

                    reader.close();
                    writer.close();
                    socket.close();
                    return;
                } else {
                    headerLines.add(content);
                }

                content = reader.readLine();
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}