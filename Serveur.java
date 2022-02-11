package Serveur_de_chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Serveur {
    public int port_ecoute ;
    public ServerSocket socket ;
    public ArrayList<TraitementClient> clients;
    public boolean running = true;

    public Serveur(int port_ecoute) {
        this.port_ecoute = port_ecoute;

        clients = new ArrayList<TraitementClient>();
        try{
            socket = new ServerSocket(port_ecoute);
        }catch(Exception e){
            System.out.println("Erreur au niveau du constructeur Serveur");
            return;
        }
    }

    public void startListening()
    {
        Socket socket_travail;
        while(running)
        {
            try{
                socket_travail = socket.accept();
                TraitementClient client = new TraitementClient(socket_travail,clients);
                clients.add(client);
                Thread traitement = new Thread(client);
                traitement.start();
            }catch(Exception e){
                System.out.println("Erreur au niveau de startListening");
                return;
            }
        }
    }

    public void stop()
    {
        running = false;
        try{
            socket.close();
        }catch(Exception e){
            return;
        }
        
    }

    public String getAdress()
    {
        return socket.getInetAddress().getHostName();
    }

    public int getPort()
    {
        return socket.getLocalPort();
    }
}
