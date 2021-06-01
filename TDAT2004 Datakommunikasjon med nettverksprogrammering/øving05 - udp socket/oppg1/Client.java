// UDP client

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

class Client
{
    private static int port = 1250;

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        try
        {
            DatagramSocket datagramSocket = new DatagramSocket();
            InetAddress inetAddress = InetAddress.getByName("localhost");

            byte[] sendBytes;
            byte[] receiveBytes = new byte[256];

            while(true)
            {
                System.out.println("Enter calculation:");
                String message = input.nextLine();

                if(!(message.contains("+") || message.contains("-")))
                {
                    datagramSocket.close();
                    return;
                }

                sendBytes = message.getBytes();

                DatagramPacket datagramPacket = new DatagramPacket(sendBytes, 0, sendBytes.length, inetAddress, port);
                datagramSocket.send(datagramPacket);

                DatagramPacket receivePacket = new DatagramPacket(receiveBytes, receiveBytes.length);
                datagramSocket.receive(receivePacket);

                String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("result from server: " + result);
            }

         } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}