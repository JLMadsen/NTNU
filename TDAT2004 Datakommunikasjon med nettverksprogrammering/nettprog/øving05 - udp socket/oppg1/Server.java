// UDP server

import java.io.IOException;
import java.net.*;

class Server
{
    private static int port = 1250;

    public static void main(String[] args) 
    {
        try
        {
            DatagramSocket datagramSocket = new DatagramSocket(port);
            byte[] sendBytes;
            byte[] receiveBytes = new byte[256];

            while(true)
            {
                DatagramPacket datagramPacket = new DatagramPacket(receiveBytes, receiveBytes.length);
                datagramSocket.receive(datagramPacket);

                String calculation = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("From client: " + calculation);

                int result = 0;
                if(calculation.contains("+"))
                {
                    String[] numbers = calculation.split("\\+");
                    result = Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);
                } 
                else
                {
                    String[] numbers = calculation.split("\\-");
                    result = Integer.parseInt(numbers[0]) - Integer.parseInt(numbers[1]);
                }

                InetAddress inetAddress = datagramPacket.getAddress();
                int packetPort = datagramPacket.getPort();

                Integer intObj = new Integer(result);
                sendBytes = intObj.toString().getBytes();
                //sendBytes = intObj.byteValue();

                DatagramPacket sendPacket = new DatagramPacket(sendBytes, sendBytes.length, inetAddress, packetPort);
                datagramSocket.send(sendPacket);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
