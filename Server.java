    
    import java.io.*;
    import java.net.*;
    
public class Server {
   protected ServerSocket server = null;
   protected Socket client = null;
   protected String stringaRicevuta = null;
   protected String stringaModifica = null;
   protected BufferedReader inDalServer;
   protected DataOutputStream  outVersoClient;
   
   public Socket attendi(){
      try{
       System.out.println("1) Server in esecuzione");
       server = new ServerSocket(6789); //avvio server sulla porta 6789
       client = server.accept(); //attesa di un client
       server.close();
       inDalServer = new BufferedReader(new InputStreamReader (client.getInputStream()));
       outVersoClient = new DataOutputStream(client.getOutputStream());
      }
      catch(Exception e){
          System.out.println(e.getMessage());
          System.out.println("Errore durante l'avvio del server");
          System.exit(1);
      }
       
    
      return client;
   }
   
   public void comunica() {
       try {
           System.out.println("3) benvenuto client, scrivi una frase e la trasformo in maiuscolo attendo....\n"); //attesa client
           stringaRicevuta = inDalServer.readLine(); //lettura stringa dal client
           System.out.println("6) ricevuta la stringa dal cliente: " + stringaRicevuta);
           
           //modifica della stringa inviata dal client
           stringaModifica = stringaRicevuta.toUpperCase();
           System.out.println("7) invio stringa modificata\n");
           outVersoClient.writeBytes(stringaModifica + "\n");
           
           //fine servizio chiusura server
           System.out.println("9) SERVER: fine elaborazione ....fine servizio");
           client.close();
           
       } catch (Exception e) {
           System.out.println(e.getMessage());
          System.out.println("Errore durante la comunicazione col client");
          System.exit(1);
       }
   }
}
