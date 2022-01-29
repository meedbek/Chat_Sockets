import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

public class TraitementClient implements Runnable{
    public Socket socket;
    public ArrayList<TraitementClient> clients;
    public String message;
    
    public TraitementClient(Socket socket,ArrayList<TraitementClient> clients)
    {
        this.socket = socket;
        this.clients = clients;
    }

    public void run()
    {
        try{
            // Créer les flots d’E/S pour la communication
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = null;
            while(true)
            {
                if(in.hasNextLine())
                {
                    message = in.nextLine(); 
                           
                    for(TraitementClient client : clients)
                    {
                        out = new PrintWriter(client.socket.getOutputStream(), true);
                        out.println(message);
                    }
                }
                Thread.sleep(100);
                if(Thread.currentThread().isInterrupted())
                {
                    in.close();
                    out.close();
                }
            }
            

        }catch(Exception e){
            return;
        }
    } 
}
