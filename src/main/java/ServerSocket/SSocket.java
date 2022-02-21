
package ServerSocket;

import java.io.*;
import java.net.*;

public class SSocket {
     
    protected int porta; //porta
    protected ServerSocket server; //Il server socket aspetterà l'arrivo dei client (del client)
    protected Socket client; //il socket del client
    
    //cose per la comunicazione
    private BufferedReader input; //qui si riceverà ciò verrà inviato dal client
    private DataOutputStream output; //inviare i dati al client
    
    public SSocket(int porta) {
        this.porta = porta;
        try {
            server = new ServerSocket(porta);
        } catch (IOException ex) { System.out.println("Errore nell'inizializzazione del server\n" + ex.getMessage() ); }
    }
    
    public void connetti(){
        
        try {
            System.out.println("Server in attesa dell'arrivo di un client...");
            client = server.accept();
            System.out.println("Client arrivato: " + client.toString());
            
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new DataOutputStream(client.getOutputStream());
            
        } catch (IOException ex) {
            System.out.println("Errore nella connessione" + ex.getMessage());  
        }
        
    }
    
    public void comunica(){
        String messaggio = "";
        System.out.println("Inizio comunicazione");
        
        
        try {
            output.writeBytes("Benvenuto nel server, inviami un messaggio" + '\n');
            
            for(;;){
                messaggio = input.readLine();
                
                if(messaggio.equalsIgnoreCase("chiudi")){
                    break;
                }
                
                output.writeBytes("Hai inviato il messaggio \"" + messaggio + "\"" + '\n'); // hai inviato il messaggio "jhkasbfkajb"
            }
        } catch (IOException ex) { 
            System.out.println("Errore nella comunicazione" + ex.getMessage()); 
            chiudi(); 
        }
        
        chiudi();
    }
    
    public void chiudi(){
        System.out.println("Chiusura in corso...");
        try {
            output.close();
            input.close();
            client.close();
            server.close();
        } catch (IOException ex) { 
            System.err.println("Errore durante la chiusura" + ex.getMessage()); 
        }
    }
    
}

