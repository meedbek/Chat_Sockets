import java.util.ArrayList;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.IOException;

public class Client implements Runnable {

    public int port;
    public String adress;
    public String name;
    public ArrayList<String> messages;
    public GUI_client GUI ;
    Socket client ;
    Scanner in ;

    // Action a faire quand le client clique sur envoyer
    private ActionListener sendListener = (event) -> {
        String message = null;
        try{
            message = GUI.messageArea.getText();
        }catch(NullPointerException e){
            return;
        }
        try{
            PrintWriter out = new PrintWriter(this.client.getOutputStream(),true);
            out.println(name+" : "+message);
            GUI.messageArea.setText("");
        }catch(Exception e){
            System.out.println("Erreur au niveau de sendListener ");
            return;
        }
        
    };

    //constructeur de la classe client    
    public Client(int port, String adress, String name){
        this.port = port;
        this.adress = adress;
        this.name = name;
        this.messages = new ArrayList<String>();
    }

    
    public void run(){
        try{
            client = new Socket(adress,port); //instatiation de la socket client
            in = new Scanner(client.getInputStream()); // Scanner qui recoit les m_essage recu de la part du serveur
            GUI = new GUI_client(name,messages,sendListener);  /*{
                @Override
                public void dispose()
                {
                    Thread.currentThread().interrupt();
                }
            };//instatiation du GUI client*/
            while(true){
                //si le client recoit un nouveau message on l'ajoute à la liste des messages et on met a jour le messagesLabel
                if(in.hasNextLine()){ 
                    messages.add(in.nextLine());  
                    String text =""; 
                    for(String line : messages)
                        text += line+"<br/>";
                    GUI.messagesLabel.setText("<html>"+text+"</html>"); 
                }
                    
                Thread.sleep(500);
            }
        }catch(IOException e){
            System.out.println("Erreur au niveau de la fonction run sur Classe client");
            return;
        }catch(InterruptedException e){
            System.out.println("Erreur au niveau de la fonction run sur Classe client");
            return;
        }
    }

    public void stop() throws IOException
    {
        in.close();
        client.close();
    }
}
