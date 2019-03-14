package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class MainServer {
	private final static int PORT = 5002;

	public static void main(String[] args) throws IOException {

		//Socket de servidor para esperar peticiones de la red
		
		ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado en el puerto " + PORT);    
        System.out.println("Servidor, En espera de cliente...");
        
        Socket clientSocket;
        
        while(true){
            clientSocket = serverSocket.accept(); //En espera de la conexion, si existe la acepta

            //Para leer lo que envie el cliente, Conseguimos el canal de entrada 
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //Para imprimir datos de salida                
            PrintStream output = new PrintStream(clientSocket.getOutputStream());

            //se lee peticion del cliente
            String request = input.readLine();
            System.out.println("Cliente> petición [" + request +  "]");

            //se procesa la peticion y se espera resultado
            String strOutput = process(request);                

            //Se imprime en consola "servidor"
            System.out.println("Servidor> Resultado de petición");                    
            System.out.println("Servidor> \"" + strOutput + "\"");

            //se imprime en cliente
            output.flush();//vacia contenido
            output.println(strOutput);                

            //Cierra la conexion
            clientSocket.close();
        }
	}

	/**
	 * Procesa la peticion del cliente y retorna resultado
	 * @param request petición del cliente
	 * @return
	 */
	private static String process(String request) {
		String result="";        
        //frases
        String[] phrases = {
            "La tecnología se alimenta a si misma. La tecnología hace posible más tecnología.-Alvin Toffler.",
            "La tecnología es sólo una herramienta. En términos de llevar a los niños a trabajar juntos y motivarlos, el profesor es el más importante.-Bill Gates.",
            "La máquina tecnológicamente más eficiente que el hombre ha inventado es el libro.-Northrop Frye.",
            "Ya no hacen más los bugs como bunny - Olav Mjelde",
            "Un lenguaje de programación es de bajo nivel cuando requiere que prestes atencion a lo irrelevante.-Alan J. Perlis.",
            "Hablar es barato. Enséñame el código.-Linus Torvalds ",
            "No me importa si funciona en su máquina! No me envían su máquina!.-Vidiu Platon",
            "Siempre codifica como si la persona que finalmente mantendrá tu código fuera un psicópata violento que sabe dónde vives.-Martin Golding"};
	ArrayList<String> phrasesList = new ArrayList<>();
	Collections.addAll(phrasesList, phrases);
        //libros
        String[] books = {
            "Divina Comedia - Dante Alighieri", 
            "Don Quijote de la Mancha - Miguel de Cervantes",
            "Cien años de soledad - Gabriel García Márquez",
            "Moby Dick - Herman Melville",
            "Ana Karenina - Lev Tolstói",
            "Eneida - Virgilio",
            "Otelo - William Shakespeare",
            "El viejo y el mar - Ernest Hemingway",
            "Orgullo y prejuicio - Jane Austen"};
	ArrayList<String> booksList = new ArrayList<>();
	Collections.addAll(booksList, books);        
        
        if(request!=null) switch(request){
            case "frase":
                Collections.shuffle(phrasesList);
                result = phrasesList.get(0);
                break;
            case "libro":
                Collections.shuffle(booksList);
                result = booksList.get(0);
                break;
            case "exit":                
                result = "bye";
                break;
            default:
                result = "La peticion no se pudó resolver.";
                break;
        }
        return result;
	}
}


class ThreadServerHandler extends Thread {
    private Socket cliente;
    private BufferedReader entrada;
    private PrintWriter salida;
    
    public ThreadServerHandler(Socket socket){
        cliente = socket;
        try{
        	//Conseguimos el canal de entrada 
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            salida = new PrintWriter(cliente.getOutputStream(),true);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Establecer los flujos de entrada/salida para el socket,
     * Procesar las entradas y salidas según el protocolo, cerrar el socket
     */
    public void run() {
      try {
      }
      catch (Exception e) {
        // manipular las excepciones
      }
    }
  }
