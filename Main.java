package Serveur_de_chat;

public class Main {
    public static void main(String arg[])
    {
        Serveur server = new Serveur(8187);
        server.startListening();
    }
}
