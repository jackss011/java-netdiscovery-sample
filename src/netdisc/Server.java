package netdisc;

import java.io.IOException;
import java.net.*;

/**
 * Created by Giacomo on 15/01/2017.
 */
public class Server
{
    class ServerThread extends Thread
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

                byte[] mess;// new String(String.valueOf(Math.random())).getBytes();

                for(;;)
                {
                    mess = String.valueOf(Math.random()).getBytes();
                    DatagramPacket p = new DatagramPacket(mess, mess.length, ia, port);
                    ms.send(p);
                    System.out.println("Sent...");

                    Thread.sleep(1000);
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
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            if (ms != null) ms.close();
        }
    }

    public void run()
    {
        new ServerThread().start();
    }

}
