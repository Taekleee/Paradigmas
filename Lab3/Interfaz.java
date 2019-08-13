import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* La clase interfaz se encarga de generar todas las interacciones con el usuario
*/
class Interfaz{
	/**
	* Se instancia Log, usuario y evaluaciones, ya que en esta clase se utilizan métodos de esas clases o sus retornos
	* deben ser entregados como parámetros.
	*/
    Log log = new Log();
    Usuario user = new Usuario();
	Evaluaciones ev = new Evaluaciones();
	/**
	* ATRIBUTOS
	*/

	/**
	*	Validador permite a la interfaz saber si el programa se continuará ejecutando o no. Parte con un valor inicial que es 0
	* y se modifica a medida que se genera la interacción con el usuario
	*/
	private int validador = 0 ;
	/**
	* Estado de la conversación indica si se tiene una conversación en ejecución o no
	*/
	private int estadoConversacion = 0; 
	/**
	* Valor incicial que ingresa el usuario y es utilizado para escoger la personalidad con la cual interactuará
	*/
	private int personalidad  = 0;



	/**
	* MÉTODOS PUBLICOS
	**/

	/**
	* Comandos se encarga de encontrar qué es lo que debe responder el chatbot en base a lo ingresado por el usuario, verificando
	* en primera instancia si lo ingresado corresponde o no a un comando y en base a eso llama a métodos de la clase chatbot para
	* responder
	* @param: verificador: corresponde a un string que indica si lo ingresado contiene el símbolo de comando (!)
	*         frase: contiene todo lo ingresado por el usuario, en caso de ser un comando que requiera más parámetros o una consulta
	*		  chat: Corresponde a un parámetro de la clase chatbot, el cual permite acceden a métodos como beginDialog, endDialog, etc
	*         log: parámetro de la clase log que permite modificar la conversación que se almacena
	* 		  user: parámetro de la clase usuario que guarda la información importante de este para futuras referencias
	*@return:  retorna un entero que indica si la conversación fue finalizada o no (5 cuando se finaliza).
	*
	**/
	public void comandos(String verificador, String frase, Chatbot chat){
		String cadena [] = frase.split(" ");
		int datos = cadena.length;
		if(verificador.equals("!")){
			switch(cadena[0]){
				case "!beginDialog":
					if(datos == 1 &&  (estadoConversacion==0 || estadoConversacion==2)){
						this.personalidad = chat.getSemilla(numero());
						chat.beginDialog(personalidad,log, getHora());
						setEstado(1); setValidador(1); break;
					}
					else if(estadoConversacion == 0 || estadoConversacion==2){
						this.personalidad = chat.getSemilla(Integer.parseInt(cadena[1]));
						chat.beginDialog(personalidad,log,getHora());
						setEstado(1); setValidador(1); break;
					}
				case "!endDialog":
					if(estadoConversacion == 1){
						chat.endDialog(personalidad,log,getHora());
						setEstado(2); setValidador(1); break;
					}
					else {
						System.out.println("[" +getHora() + "] HOGWARTSBOT> No puedes terminar una conversación que no ha sido iniciada");
						setValidador(1); break;
					}
				case "!loadLog":
		    		if(datos==1){
		    			System.out.println("[" +getHora() + "] HOGWARTSBOT> Los parámetros ingresados a loadLog son incorrectos, vuelve a intentar");
		    		 setValidador(1); break;
		    		}
			    	else if(estadoConversacion == 1){
			    		System.out.println("[" +getHora() + "] HOGWARTSBOT> No puedes cargar una conversación, ya que tienes una en curso");
			    		setValidador(1); break;
			    	}
			    	else if(datos==2 && (estadoConversacion == 0|| estadoConversacion == 2)){
			    		log.cargarArchivo(cadena[1]);
						System.out.println("[" +getHora() + "] HOGWARTSBOT> Bienvenido nuevamente! Creo que aun tienes dudas sobre Hogwarts, vuelve a iniciar una conversación con HOGWARTSBOT");	
						setEstado(2);	
						setValidador(1); break;   		
			    	}

				case "!saveLog":
					if(estadoConversacion==2){
						log.crearArchivo(getHora());
						setValidador(1); break;
					}
					else if(estadoConversacion == 0){
						System.out.println("[" +getHora() + "] HOGWARTSBOT> No tienes ninguna conversación que pueda ser guardada");	
						setValidador(1); break;
					}
					else{
						System.out.println("[" +getHora() + "] HOGWARTSBOT> No puedes guardar la conversación hasta que sea finalizada");	
						setValidador(1); break;

					}
				case "!rate":
					if(datos==3 && (Integer.parseInt(cadena[1]))<= 5 && (Integer.parseInt(cadena[1]))>=0 && (Integer.parseInt(cadena[2]))<= 5 && (Integer.parseInt(cadena[2])>= 0)  && estadoConversacion==2){
						ev.setCalificacion(Integer.parseInt(cadena[2]), Integer.parseInt(cadena[1]), getHora());
						setValidador(1); break;
					}
					else if(datos ==3 && (!(Integer.parseInt(cadena[1])<= 5) || !(Integer.parseInt(cadena[1])>=0) || !(Integer.parseInt(cadena[2])<= 5) || !(Integer.parseInt(cadena[2])>= 5))){
						System.out.println("[" +getHora() + "] HOGWARTSBOT> Los datos ingresados no se encuentran dentro de lo solicitado, vuelve a ingresar");	
						setValidador(1); break;
					}
					else if(estadoConversacion==1 || estadoConversacion==0){
						System.out.println("[" +getHora() + "] HOGWARTSBOT> No puedes ingresar las notas hasta que termines la conversación");	setValidador(1); break;

					}
				case "!exit":
					setValidador(5); break;
				case "!saveXML": 
				    log.crearArchivoXML();
				    setValidador(1); break;
				case "!chatbotPerformance":
					if(estadoConversacion==2){
						ev.performance(chat.getPersonalidad());
						setValidador(1); break;
					}
				default:
					System.out.println("[" +getHora() + "] HOGWARTSBOT> El comando ingresado es inválido, intente nuevamente");	
					setValidador(1); break;
			}
		}
		else{
			if(estadoConversacion == 1){
				chat.coincidencia(frase,numero(),personalidad,log,user,getHora());
		 		setValidador(1); 
		 	}
		 	else{
		 		System.out.println("[" + getHora() + "] HOGWARTSBOT> No puedes hablar con HOGWARTSBOT si no inicias una conversación");
		 		setValidador(1); 
		 	}
		}
	}

	/**
	 * Genera la hora con que se genera el archivo de salida
	 * @return hora:  HORA-MINUTO-SEGUNDO_DIA-MES-AÑO
	 */ 
	 public  String getHora(){
	      Date fecha = new Date();
	      DateFormat fecha2 = new SimpleDateFormat("HH:mm:ss:dd:MM:yyyy");
	      String hora = fecha2.format(fecha);
	      String [] tiempo = hora.split(":");
	      hora = tiempo[5]+"-"+tiempo[4]+"-"+tiempo[3]+"_"+tiempo[0]+"-"+tiempo[1]; 
	      return hora;
		}
	/**
	* Retorna el validador para saber si la conversación debe o no continuar
	* @return: validador
	*/
		public int getValidador(){
			return validador;
		}

	/**
	* METODOS PRIVADOS
	*/

	/*
	* numero genera un valor que permite obtener respuestas variables (dentro de las 3 posibles respuestas por personalidad encuentra una)
	* el valor es calculado en base a el segundo en que se recibe el mensaje del usuario
	* @return: entero con el segundo en que se consultó
	**/
	private int numero(){
			 Date fecha = new Date();
	 		 DateFormat fecha2 = new SimpleDateFormat("HH:mm:ss:dd:MM:yyyy");
	   		 String hora = fecha2.format(fecha);
			 String tiempo [] = hora.split(":");
			 return Integer.parseInt(tiempo[2]);
	}
	/**
	* setEstado modifica el valor del estado de la conversación. Cuando se encuentra en 0 indica que no ha comenzado ninguna conversación, si 
	* se encuentra en uno que existe una en curso y si está en 2 significa que ya terminó una o más conversaciones
	* @param: entero con el estado de la conversación
	**/
	private void setEstado(int estado){
		this.estadoConversacion = estado;
	}

	/**
	* Se modifica el valor del validador
	*/
	private void setValidador(int valor){
		this.validador = valor;
	}

}