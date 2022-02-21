package ServerSocket;


public class App {

    
    public static void main(String[] args) {
          SSocket server = new SSocket(6789);
        
        server.connetti();
        server.comunica();
    }
    
}
