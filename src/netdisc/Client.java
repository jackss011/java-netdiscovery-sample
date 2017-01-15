package netdisc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * Created by Giacomo on 15/01/2017.
 */
public class Client
{


    class ClientThread extends Thread
    {
        MulticastSocket ms;

        @Override
        public void run()
        {
            try
            {
                InetAddress ia = InetAddress.getByName(Main.address);
                int port = Main.port;

                ms = new MulticastSocket(port);
                ms.joinGroup(ia);

                byte[] buff = new byte[256];

                for(;;)
                {
                    DatagramPacket p = new DatagramPacket(buff, buff.length);
                    ms.receive(p);

                    System.out.println("Data: " + new String(p.getData()) + " - " + "From: " + p.getAddress().getHostAddress());
                }
            }
            catch (UnknownHostException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            if (ms != null)
            {
                ms.close();
            }
        }
    }


    public void run()
    {
        new ClientThread().start();
    }
}
