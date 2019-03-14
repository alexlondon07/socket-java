package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MainCliente {

	   /**
	    * Puerto
	    * */
	    private final static int PORT = 5002;
	    /**
	    * Host
	    * */
	    private final static String SERVER = "localhost";
	    
	    public static void main(String[] args) {
	    	
	    	boolean exit = false;
	    	Socket socket; 
	    	
	    try {            
	            while( !exit ){                       
	                socket = new Socket(SERVER, PORT);
	                
	                //Para leer lo que envie el servidor      
	                BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream())); 
	                
	                //Para imprimir datos del servidor
	                PrintStream output = new PrintStream(socket.getOutputStream());   
	                
	                //Para leer lo que escriba el usuario            
	                BufferedReader brRequest = new BufferedReader(new InputStreamReader(System.in));            
	                System.out.println("Cliente: Escriba comando...");      
	                
	                //Captura el comando escrito por el usuario
	                String request = brRequest.readLine(); 
	                
	                //Manda LA peticion al Servidor
	                output.println(request); 
	                
	                String messageResponse = input.readLine();
	                if( messageResponse != null ) 
	                		System.out.println("Respuesta del servidor: " + messageResponse );    
	                
	                if( request.equals("exit") ){
	                    exit = true;                  
	                    System.out.println("Cliente: El programa ha finalizado.");    
	                }  
	                /*input.close();
	                output.close();
	                brRequest.close();*/
	                socket.close();
	                
	            }  
	       } catch (IOException ex) {        
	         System.err.println("Error en el cliente: " + ex.getMessage());   
	       }
	    }
}
