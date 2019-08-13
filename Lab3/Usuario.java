import java.util.*;
	/**
	*La clase usuario reune la información que entrega el usuario sobre sus preferencias
	*/
	class Usuario{
	/**
	* ATRIBUTOS
	*/
	/**
	 * Palabras más buscada
	 */
	private String masBuscado;
	/**
	 * Casa del alumno
	 */
	 private String casa; 
	/**
	 * Palabras posiblemente más buscadas
	 */
	private String [] lPalabrasMasBuscadas = new String[10];
	/**
	 * Frecuencia con la que son buscadas las palabras por el usuario.
	 */
	private int [] frecuenciaPalabras = new int[10];
	/**
	 *Frases de palabras posiblemente más buscadas
	 */
	private String [] lFrasesMasBuscadas = new String[10];

	/*
	 * CONSTRUCTOR USUARIO con las posibles palabras mas usadas, la tabla de frecuencia de las palabras y las posibles frases formadas
	 * con esas palabras
	 */
	Usuario(){
		lPalabrasMasBuscadas = new String []{"historia", "casas","patronus","dementor","hechizos","hipogrifo","Azkaban","deportes","Defensa","Ravenclaw"};
		frecuenciaPalabras = new int []{1,0,0,0,0,0,0,0,0,0};
		lFrasesMasBuscadas = new String []{"'Historia de Hogwarts'", "'Casas de Hogwarts'","'encantamiento Patronus'","'Dementor'","'Hechizos'","'Hipogrifo y animales'","'prisión de Azkaban'","'Deportes de Hogwarts'","'Defensa contra las artes oscuras'","'casa Ravenclaw'"};


	}

	/**
	* MÉTODOS PÚBLICOS
	**/
	/**
	* Modifica los valores en la tabla de frecuencia y busca la palabra que más se repite
	 * @param log: String con el log actual, que se usará para encontrar la palabra que más se repite con respecto a la lista
	 *             de palabras más buscadas.
	 * @return lFrasesMasBuscadas: frase asociada a la lista de palabra que más se repite en el log
	 */
	public String frecuencia(String log){
		int contador;
		int i = 0;
		String [] nuevoLog = log.split(" ");
		for(String palabra: lPalabrasMasBuscadas){
			contador = 0;
			for(String palabras: nuevoLog){
				if(palabras.equals(palabra)){
					contador++;
				}
			}
			setFrecuencia(i,contador);
			i++;
		}

		 i=0; int mayor = 0; int indiceMayor = 0;
		for(int cantidad:frecuenciaPalabras){
			if(cantidad>mayor){
			 mayor = cantidad;
			 indiceMayor = i;
			}
			i++;
		}
		masBuscado  = lFrasesMasBuscadas[indiceMayor];
		return masBuscado;



	}

	/**
	* MÉTODOS PRIVADOS 
	*/

	/** Modifica los valores de la tabla de frecuencia
	* @param i: posición de la tabla de frecuencia que se debe modificar
	*        contador: Nº de veces en que se repite la palabra (frecuencia)
	*/
	private void setFrecuencia(int i, int contador){
		this.frecuenciaPalabras[i] = contador;
	}


	/**
	 * Se asigna la casa al usuario
	 * @param casa: String con la casa del usuario.
	 */
	 private void setCasa(String casa){
	 	this.casa = casa;
	 




	}
}