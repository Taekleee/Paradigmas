import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


class HogwartsBot{
public static void main(String [ ] args){
   /**
   * Se instancia la clase interfaz, que se encarga de manejar la interacción entre chatbot y usuario y la clase Chatbot, la cual contiene la personalidad 
   * y posibles respuestas.
   */
		
	Interfaz interfaz = new Interfaz();
   Chatbot chat = new Chatbot();

      Scanner entrada = new Scanner(System.in);
      String bienvenida = "\n\n\n                   *******Bienvenido a Hogwarts! HOGWARTSBOT es un guía que te permitirá conocer información sobre el castillo y el mundo mágico*******\n          (-O-O-)/\n\n\n";
      boolean estadoConversacion = true; 
      String entradaInicial;
      String respuesta;
      System.out.println(bienvenida);
      int comando = 0;

    while(interfaz.getValidador()!=5){  
      System.out.print("["+ interfaz.getHora()+ "] USUARIO> ");
      entradaInicial = entrada.nextLine();
      String cadena []= entradaInicial.split("");
      interfaz.comandos(cadena[0],entradaInicial,chat);

	//}
      }
   }
}