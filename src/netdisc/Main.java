package netdisc;

/**
 * Created by Giacomo on 15/01/2017.
 */

public class Main
{
    final static String address = "228.5.6.7";
    final static int port = 6576;

    static public void main(String [] args)
    {
        if(args.length > 0)
        {
            String mode = args[0];

            if (mode.equals("s"))
            {
                System.out.println("Running as a server...");
                new Server().run();
            }
            else if (mode.equals("c"))
            {
                System.out.println("Running as a client...");
                new Client().run();
            }
        }
        else
        {
            System.out.println("No mode defined");
        }
    }
}
