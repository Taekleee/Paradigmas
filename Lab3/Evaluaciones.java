import java.util.*;
/**
* La clase evaluaciones se encarga de manejas las notas que el usuario pone sobre el chatbot y el mismo
*/
class Evaluaciones{
	/**
	* ATRIBUTOS
	*/

	/**
	*Lista con las calificaciones que entrega el usuario al chatbot según cada conversación realizada
	*/
	private List<Integer> calificacion = new ArrayList<Integer>();
	/**
	 * Lista con las notas de los usuarios
	 */
	private List <Integer>  puntaje = new ArrayList<Integer>();
	/**
	* Almacena los tiempos en que se realiza la evaluación del chatbot
	**/
	private List<String> marcaDeTiempo = new ArrayList<String>();
	/**
	* Contiene el promedio de chatbot según todas las conversaciones que realiza
	*/
	private int promedioChatbot;
	/**
	* Contiene la desviación estándar de las notas de chatbot
	*/
	private int desviacionChatbot;
	/**
	*  Contiene el promedio del usuario según todas las conversaciones que realiza
	*/
	private int promedioUsuario;
	/**
	* Contiene la desviación estándar de las notas del usuario
	*/
	private int desviacionUsuario;


/**
* MÉTODOS PÚBLICOS
*/

/**
* Asigna los valores de las calificaciones, y la hora en que fueron puestas. Se realiza cuando se ejecuta rate
* almacena los datos
* @param: puntaje: nota del usuario
*         calificacion: nota del chatbot
*         hora: string con la hora en que se asignan los puntajes
*/
public void setCalificacion(int puntaje, int calificacion, String hora){
	this.puntaje.add(puntaje);
	this.marcaDeTiempo.add(hora);
	this.calificacion.add(calificacion);
}

/**
* performance calcula en base a las notas tanto del usuario como del chatbot el promedio de cada uno y lo muestra por pantalla
*@param:  Lista con las personalidades que interactuaron con el usuario
*/

public void performance(List<String> personalidad){
	int i = 0;
	double promedioU = 0; double promedioC = 0;
	while(i < calificacion.size()){
		System.out.println("Personalidad: " + personalidad.get(i) + " Marca temporal: " + marcaDeTiempo.get(i) + " Nota HOGWARTSBOT: " + calificacion.get(i) + " Nota Usuario: " + puntaje.get(i));
		promedioC = promedioC + calificacion.get(i);
		promedioU = promedioU + puntaje.get(i);
		i++;
	}
	System.out.println("PROMEDIO CHATBOT: " + promedioC/calificacion.size() + "PROMEDIO USUARIO: " + promedioU/calificacion.size());
	desviacion(calificacion.size(),promedioC/calificacion.size(), promedioU/calificacion.size());
}


/**
* MÉTODOS PRIVADOS
*/

/**
* Desviación se encarga de calcular la desviación del chatbot y del usuario mediante el uso de la fórmula conocida
* y lo entrega por pantalla
*@param : cantidad de datos: cantidad total de notas ingresadas en rate
*         promedioC: promedio de las notas de chatbot
*         promedioU: promedio de las notas del usuario
*/
private void desviacion(int cantidadDatos, double promedioC, double promedioU){
	int i = 0;
	double diferenciaC = 0;
	double diferenciaU = 0;

	while(i < cantidadDatos){
		diferenciaC = diferenciaC + Math.abs(calificacion.get(i) - promedioC); 	
		diferenciaU = diferenciaU + Math.abs(puntaje.get(i) - promedioU); 	
		i++;
	}
	System.out.println("DESVIACIÓN CHATBOT: " + Math.sqrt(diferenciaC/cantidadDatos) + " DESVIACION USUARIO: " + Math.sqrt(diferenciaU/cantidadDatos));

}


}