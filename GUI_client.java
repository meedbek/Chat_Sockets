package Serveur_de_chat;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.GridLayout;

public class GUI_client extends JFrame{

    public JTextField messageArea;
    JPanel content;
    JLabel messagesLabel;
    ArrayList<String> messages;

    public GUI_client(String clientName, ArrayList<String> messages, ActionListener sendListener){
        
        super(clientName);


        this.messages = messages;
        content = new JPanel();
        content.setLayout(new GridLayout(2,1,10,10));

        //messagesLabel contient l'historique des messages 
        messagesLabel = new JLabel();
        String text = " ";

        for(String line : messages)
            text += line+"<br/>";
        messagesLabel.setText("<html>"+text+"</html>");

        //textField pour ecrire le message a envoye
        messageArea = new JTextField();
        messageArea.addActionListener(sendListener);

         
        content.add(messagesLabel);
        content.add(messageArea);

        
        this.setContentPane(content);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    

}
