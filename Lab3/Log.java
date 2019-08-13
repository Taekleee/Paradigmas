import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
  /*La clase log está formada por los mensajes que genera el chatbot y el usuario, por lo que cuenta con métodos
   * que se encargan de modificar el log, agregar fecha, identificadores, crear archivos con el log existente y leer
   * archivos con un log anterior. 
  */

  class Log{
  //Atributos
  /**
  * Log con la conversación vista por el usuario
  */
   private  String logUsuario;
  /**
  * Hora en que se genera el log
  */
   private  String hora;

  /**
  * Constructor del log, se inicializa con un string vacío.
  */ 
  Log(){
    logUsuario = "";
  }

  /**
  * METODOS PUBLICOS
  **/

   /**
   * getLog permite obtener el log de la conversación, que será utilizado en el resto de
   * las clases
   */

   public String getLog(){
      return logUsuario;
   }

  /**
   * Modifica el log para entregar la conversación completa, Añade el log anterior junto con la 
   * consulta realizada por el usuario y la respuesta generada por el chatbot.
   * Modifica el log que es mostrado al usuario y uno del chatbot, que sirve como referencia para
   * generar los distintos formatos de salida
   * @param entradaUsuario : frase que ingresa el usuario
   *        respuesta: respuesta generada por el chatbot
   *        hora: tiempo en que se desarrolla la modificación del log
   * @return nuevoLog: log modificado con log Anterior + hora + entrada usuario + respuesta chatbot 
   */ 
   public  void modificarLog(String entradaUsuario, String respuesta,String hora){
   	    System.out.println("["+hora+ "] HOGWARTSBOT> " + respuesta);
        this.logUsuario = logUsuario + entradaUsuario +"\n"+"["+hora+ "] HOGWARTSBOT> " +respuesta+ "\n";
   }

  /**
   * genera una archivo de salida con el nombre: "hora.log" que contiene cada interacción que fue realizada a lo largo
   * del programa.
   * @param logUsuario: log con la conversación
   * @return archivo : archivo con el desarrollo de la conversación escrita
   */
   public  void crearArchivo(String hora){
   	    String [] frases = logUsuario.split("\n");
   	    FileWriter fichero = null;
          PrintWriter escritura = null;
          try
          {
              fichero = new FileWriter(hora+".log");
              escritura = new PrintWriter(fichero);

              for (String linea: frases)
                  escritura.println(linea);

          } catch (Exception e) {
              System.out.println("["+hora+ "] HOGWARTSBOT> No fue posible crear el archivo: "+ hora+".log");
          } finally {
             try {
            //Se cierra el archivo
             if (null != fichero)
                fichero.close();
             } catch (Exception e2) {
                System.out.println("["+hora+ "] HOGWARTSBOT> No fue posible cerrar el archivo: "+hora+".log");
             }
          }
          System.out.println("["+hora+ "] HOGWARTSBOT> Se ha creado el archivo: '" + hora + "'.log");
  }



  /**
  * Recibe el nombre de un archivo que contiene una conversación previa con el chatbot, lee cada
  * línea y las agrega a un string (nuevo log).
  * @param nombreArchivo: nombre y extensión del archivo que contiene la conversación
  * @return log: log con la conversación contenida en el archivo.
   */ 
  public  void cargarArchivo(String nombreArchivo){
  	    File archivo = null;
        FileReader lectura = null;
        BufferedReader br = null;
        String log = " ";
        int aux;
        try {
           archivo = new File (nombreArchivo);
           lectura = new FileReader (archivo);
           br = new BufferedReader(lectura);
           //* Lectura del fichero línea a línea y se agrega al string log junto con un \n, el cual
           //* luego permite la separación de las interacciones con el chatbot.
           String linea;
           while((linea=br.readLine())!=null)
             log = log + linea+ "\n";
              System.out.println("[" + hora + "] HOGWARTSBOT> Se ha cargado el archivo: "+ nombreArchivo);

        }
        catch(Exception e){
           System.out.println("[" + hora + "] HOGWARTSBOT> No fue posible abrir el archivo: " + nombreArchivo);
        }finally{
           try{                    
              if( null != lectura ){   
                 lectura.close();     
              }                  
           }catch (Exception e2){ 
              System.out.println("[" + hora + "] HOGWARTSBOT> No fue posible cerrar el archivo: "+nombreArchivo);
           }
        }
        this.logUsuario = logUsuario + log;

  }

/**
   * genera una archivo de salida con el nombre: "hora.logxml" que contiene cada interacción que fue realizada a lo largo
   * del programa en formato XML.
   * @param logUsuario: log con la conversación
   * @return archivo : archivo con el desarrollo de la conversación escrita
   */


   public  void crearArchivoXML(){
        String [] frases = logUsuario.split("\n");
        FileWriter fichero = null;
          PrintWriter escritura = null;
          try
          {
              fichero = new FileWriter(hora+".logxml");
              escritura = new PrintWriter(fichero);
              escritura.println("<INICIO HOGWARTSBOT>");
              for (String linea: frases){
                  if(linea.equals("<<<<<INICIO CONVERSACIÓN>>>>>")) escritura.println("<INICIO CONVERSACIÓN>");
                  else if(linea.equals("<<<<<FIN CONVERSACIÓN>>>>>")) escritura.println("<FIN CONVERSACIÓN>");
                  else {
                    String frases2 []= linea.split(" ");
               //     System.out.println(linea  + "FRASES: " + frases2[0] + "  "+ frases2[1]  );
                    escritura.println("<FECHA>  " + frases2[0] + "  </FECHA>\n" + "<EMISOR>  " + frases2[1] +  "  </EMISOR>\n"); 

                  }
                //  escritura.println(linea);
          }
          } catch (Exception e) {
              System.out.println("["+hora+ "] HOGWARTSBOT> No fue posible crear el archivo: "+hora+".log");
          } finally {
             try {
            //Se cierra el archivo
             if (null != fichero)
                fichero.close();
             } catch (Exception e2) {
                System.out.println("["+hora+ "] HOGWARTSBOT> No fue posible cerrar el archivo: "+hora+".log");
             }
          }
  }
  }