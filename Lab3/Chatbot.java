import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/* La clase chatbot cuenta con todas las respuestas que puede generar el chatbot, las cuales dependen de la personalidad
 * que adopta este a lo largo del programa. Además cuenta con métodos que permiten encontrar la coincidencia entre la pregunta
 * ingresada por el usuario y  la respuesta que será entregada.
 */ 
class Chatbot{

	 //Atributos
	 /**
	 * Personalidad que adopta el chatbot, a medida que se generan conversaciones se agregan las personalidades
	 */
	 private List<String> personalidad = new ArrayList<String>();
	 /**
	 * Valor semilla que permite la variabilidad en las respuestas
	 */     
	private  int seed;
	 /**
	 * Valor Indice de las palabras Claves
	 */    
	private  int indice;
	 /**
	 * Valor Indice de las respuestas
	 */    
	private  int posicion;
	 /**
	 * Respuesta del chatbot
	 */    
	private  String respuesta; 
	 /**
	 * Tiempo en que el chatbot entrega la respuesta
	 */    
	private  String tiempo;
	 /**
	 * Hora en que el chatbot entrega la respuesta
	 */    
	private  int hora; 
	 /**
	 * Hora-minuto-segundo en que chatbot entrega la respuesta
	 */    
	private String [] horaSegundoMinuto;
	 /**
	 * Respuestas del chatbot con las tres personalidades (por defecto, enojado y optimista), con 3 posibles respuestas.
	 */    
	private String[] respuestas = new String[189];
	 /**
	 *Palabras clave que ayudan a encontrar la respuesta al usuario
	 */    
	private String[] palabrasClave = new String[20];
	/**
	* Constructor de chatbot con personalidadades, al momento de instanciar a Chatbot,se genera una lista de palabras claves y posibles respuestas,
	* las cuales serán utilizadas durante toda la ejecución del código.
	*/
	Chatbot(){
	     respuestas = new String[]{" Buenos días, bienvenido a la Hogwarts, hoy sere tu guia en el castillo, En que puedo ayudarte ? ", " Buenas tardes, bienvenido a Hogwarts, hoy sere tu guia en el castillo,  En que puedo ayudarte? ", " Buenas noches, Bienvenido a Hogwarts, hoy sere tu guia en el castillo,  En que puedo ayudarte? "," Buenos días, bienvenido a la Hogwarts, hoy sere tu guia en el castillo, En que puedo ayudarte ? ", " Buenas tardes, bienvenido a Hogwarts, hoy sere tu guia en el castillo,  En que puedo ayudarte? ", " Buenas noches, Bienvenido a Hogwarts, hoy sere tu guia en el castillo,  En que puedo ayudarte? "," Buenos días, bienvenido a la Hogwarts, hoy sere tu guia en el castillo, En que puedo ayudarte ? ", " Buenas tardes, bienvenido a Hogwarts, hoy sere tu guia en el castillo,  En que puedo ayudarte? ", " Buenas noches, Bienvenido a Hogwarts, hoy sere tu guia en el castillo,  En que puedo ayudarte? ",
	       " Hay cuatro casas en Hogwarts, ¿cual es la tuya? ", " ¿ A que casa de Hogwarts perteneces? ", " Todas las casas tienen algo bueno y tambien malo,¿ Cual es la tuya? ", " Lamentablemente hay cuatro casas en Hogwarts,todas son malas, ¿cual es la tuya? ", " Todas las casas son lamentables, ¿ A que casa de Hogwarts perteneces? ", " Todas las casas tienen algo bueno, pero es su mayor parte malo, ¿ Cual es la tuya? ",  " Hay cuatro casas en Hogwarts, ¡todas son magníficas! ¿cual es la tuya? ", " ¿ A que casa de Hogwarts perteneces?, ¡todas son fantásticas! ", " Todas las casas tienen algo muy muy bueno, pero tambien algo malo, ¿ Cual es la tuya ? ",
	       " Ravenclaw es una de las cuatro casas que componen el Colegio Hogwarts de Magia y Hechiceria, fundada por Rowena Ravenclaw, quien tuvo una hija, cuyo fantasma es la Dama Gris. Que mas quieres saber? ", " La casa Ravenclaw premia el aprendizaje, la sabiduria, el ingenio, y el intelecto de sus miembros. Que mas quieres saber? ", " Para entrar a la sala comun de ravenclaw, se debe resolver un acertijo que no siempre es el mismo, formulado por un aldaba de bronce con forma de aguila en una puerta sin picaporte. Que mas quieres saber? ",           " Ravenclaw es una de las cuatro casas que componen el Colegio Hogwarts de Magia y Hechiceria, fundada por Rowena Ravenclaw, quien tuvo una hija, cuyo fantasma es la Dama Gris. ¿Que mas quieres saber? ", " La casa Ravenclaw premia el aprendizaje, la sabiduria, el ingenio, y el intelecto de sus miembros. Todos son unos sabelo todo, ¿Que mas quieres saber? ", " Para entrar a la sala comun de ravenclaw, se debe resolver un acertijo que no siempre es el mismo, formulado por un aldaba de bronce con forma de aguila en una puerta sin picaporte. Cualquiera puede resolver el acertijo,¿ Que mas quieres saber? ",       " Ravenclaw es una de las cuatro casas que componen el Colegio Hogwarts de Magia y Hechiceria, fundada por Rowena Ravenclaw, quien tuvo una hija, cuyo fantasma es la Dama Gris. Que mas quieres saber? ", " La casa Ravenclaw premia el aprendizaje, la sabiduria, el ingenio, y el intelecto de sus miembros. Que mas quieres saber? ", " Para entrar a la sala comun de ravenclaw, se debe resolver un acertijo que no siempre es el mismo, formulado por un aldaba de bronce con forma de aguila en una puerta sin picaporte. ¡Los Ravenclaw son muy inteligentes! Que mas quieres saber? ",
	       " Fundada por Godric Gryffindor. Su animal emblematico es el leon y sus colores son el rojo escarlata y el dorado. Minerva McGonagall es la más reciente jefa de la casa. Que mas quieres saber? ", " La sala comun de Gryffindor esta localizada en la Torre de Gryffindor, la entrada esta localizada en el septimo piso y esta custodiada por el retrato de la Dama Gorda, que utiliza un vestido rosa. Ella permite la entrada solamente si la clave es la correcta (cambia regularmente).  Que mas quieres saber? ", " La Casa de Gryffindor estima el coraje, asi como la osadia, el temple y la caballerosidad, asi, sus miembros se caracterizan por ser valientes aunque a veces hasta el punto de ser imprudentes.  Que mas quieres saber? ",        " Fundada por Godric Gryffindor. Su animal emblematico es el leon y sus colores son el rojo escarlata y el dorado. Minerva McGonagall es la más reciente jefa de la casa. Que mas quieres saber? ", " La sala comun de Gryffindor esta localizada en la Torre de Gryffindor, la entrada esta localizada en el septimo piso y esta custodiada por el retrato de la Dama Gorda, que utiliza un vestido rosa. Ella permite la entrada solamente si la clave es la correcta (cambia regularmente).  Que mas quieres saber? ", " La Casa de Gryffindor estima el coraje, asi como la osadia, el temple y la caballerosidad, asi, sus miembros se caracterizan por ser valientes aunque a veces hasta el punto de ser imprudentes.  Que mas quieres saber? ",       " Fundada por Godric Gryffindor. Su animal emblematico es el leon y sus colores son el rojo escarlata y el dorado. Minerva McGonagall es la más reciente jefa de la casa. Que mas quieres saber? ", " La sala comun de Gryffindor esta localizada en la Torre de Gryffindor, la entrada esta localizada en el septimo piso y esta custodiada por el retrato de la Dama Gorda, que utiliza un vestido rosa. Ella permite la entrada solamente si la clave es la correcta (cambia regularmente).  Que mas quieres saber? ", " La Casa de Gryffindor estima el coraje, asi como la osadia, el temple y la caballerosidad, asi, sus miembros se caracterizan por ser valientes aunque a veces hasta el punto de ser imprudentes.  Que mas quieres saber? ",
	       " La sala comun de Slytherin esta ubicada detras de la entrada escondida en las mazmorras, sus ventanas proporcionan una luz verde, pues dan a las profundidades del lago de Hogwarts. Los alumnos de Slytherin, a menudo ven al calamar gigante nadando rapidamente y, a veces, criaturas aun mas interesantes.  Que mas quieres saber? ", " Los alumnos de Slytherin tienden a ser ambiciosos, inteligentes, astutos, lideres fuertes, y orientados hacia los logros. Tambien tienen muy desarrollado el instinto de supervivencia. Que mas quieres saber? ", " El Sombrero Seleccionador dice que la pureza de sangre es un factor en la selección de Slytherin. ",        " La sala comun de Slytherin esta ubicada detras de la entrada escondida en las mazmorras, sus ventanas proporcionan una luz verde, pues dan a las profundidades del lago de Hogwarts. Los alumnos de Slytherin, a menudo ven al calamar gigante nadando rapidamente y, a veces, criaturas aun mas interesantes.  Que mas quieres saber? ", " Los alumnos de Slytherin tienden a ser ambiciosos, inteligentes, astutos, lideres fuertes, y orientados hacia los logros. Tambien tienen muy desarrollado el instinto de supervivencia. Que mas quieres saber? ", " El Sombrero Seleccionador dice que la pureza de sangre es un factor en la selección de Slytherin. ",        " La sala comun de Slytherin esta ubicada detras de la entrada escondida en las mazmorras, sus ventanas proporcionan una luz verde, pues dan a las profundidades del lago de Hogwarts. Los alumnos de Slytherin, a menudo ven al calamar gigante nadando rapidamente y, a veces, criaturas aun mas interesantes.  Que mas quieres saber? ", " Los alumnos de Slytherin tienden a ser ambiciosos, inteligentes, astutos, lideres fuertes, y orientados hacia los logros. Tambien tienen muy desarrollado el instinto de supervivencia. Que mas quieres saber? ", " El Sombrero Seleccionador dice que la pureza de sangre es un factor en la selección de Slytherin. ",
	       " Hufflepuff es una de las cuatro casas del Colegio Hogwarts de Magia y Hechiceria. Su fundadora es la bruja medieval Helga Hufflepuff. La jefa de la casa es Pomona Sprout. Que mas quieres saber?  ", " Los estudiantes de esta casa son conocidos por ser trabajadores, amigables, leales y sin prejuicios. Debido a sus valores, los hufflepuffs no son tan competitivos como las otras casas, o son mas modestos con respecto a sus logros. Que mas quieres saber? ", " Cedric Diggory llevo a su casa a un esplendor poco comun como capitan de Quidditch y mas tarde como Campeon de Hogwarts en el Torneo de los Tres Magos en 1994.  Que mas quieres saber? ",        " Hufflepuff es una de las cuatro casas del Colegio Hogwarts de Magia y Hechiceria. Su fundadora es la bruja medieval Helga Hufflepuff. La jefa de la casa es Pomona Sprout. Que mas quieres saber?  ", " Los estudiantes de esta casa son conocidos por ser trabajadores, amigables, leales y sin prejuicios. Debido a sus valores, los hufflepuffs no son tan competitivos como las otras casas, o son mas modestos con respecto a sus logros. Que mas quieres saber? ", " Cedric Diggory llevo a su casa a un esplendor poco comun como capitan de Quidditch y mas tarde como Campeon de Hogwarts en el Torneo de los Tres Magos en 1994.  Que mas quieres saber? ",        " Hufflepuff es una de las cuatro casas del Colegio Hogwarts de Magia y Hechiceria. Su fundadora es la bruja medieval Helga Hufflepuff. La jefa de la casa es Pomona Sprout. Que mas quieres saber?  ", " Los estudiantes de esta casa son conocidos por ser trabajadores, amigables, leales y sin prejuicios. Debido a sus valores, los hufflepuffs no son tan competitivos como las otras casas, o son mas modestos con respecto a sus logros. Que mas quieres saber? ", " Cedric Diggory llevo a su casa a un esplendor poco comun como capitan de Quidditch y mas tarde como Campeon de Hogwarts en el Torneo de los Tres Magos en 1994.  Que mas quieres saber? ",
	       " El Colegio Hogwarts de Magia y Hechiceria es un internado magico ubicado en Escocia. El castillo se ubica en unas montañas cercanas a un lago. Te interesa algo mas? ", "  Los encantamientos que protegen al castillo son clasificados como encantamientos anti-Aparicion y encantos repelentes de Muggles, que lo hacen que ellos vean unas ruinas antiguas con un letrero que dice 'Peligro, prohibido el paso' en lugar del castillo. Quieres saber algo mas? ", " El lema de Hogwarts es: 'Draco dormiens nunquam titillandus' . La traduccion es: Nunca hagas cosquillas a un dragon dormido. Quieres saber algo mas? ",        " El Colegio Hogwarts de Magia y Hechiceria es un internado magico ubicado en Escocia. El castillo se ubica en unas montañas cercanas a un lago. Te interesa algo mas? ", "  Los encantamientos que protegen al castillo son clasificados como encantamientos anti-Aparicion y encantos repelentes de Muggles, que lo hacen que ellos vean unas ruinas antiguas con un letrero que dice 'Peligro, prohibido el paso' en lugar del castillo. Quieres saber algo mas? ", " El lema de Hogwarts es: 'Draco dormiens nunquam titillandus' . La traduccion es: Nunca hagas cosquillas a un dragon dormido. Quieres saber algo mas? ",        " El Colegio Hogwarts de Magia y Hechiceria es un internado magico ubicado en Escocia. El castillo se ubica en unas montañas cercanas a un lago. Te interesa algo mas? ", "  Los encantamientos que protegen al castillo son clasificados como encantamientos anti-Aparicion y encantos repelentes de Muggles, que lo hacen que ellos vean unas ruinas antiguas con un letrero que dice 'Peligro, prohibido el paso' en lugar del castillo. Quieres saber algo mas? ", " El lema de Hogwarts es: 'Draco dormiens nunquam titillandus' . La traduccion es: Nunca hagas cosquillas a un dragon dormido. Quieres saber algo mas? ",
	       " Algunas materias son: Adivinacion, defensa contra las artes oscuras y cuidado de criaturas magicas, ¿Quieres saber de alguna?  ", " Las asignaturas mas populares son Defensa contra las artes oscuras y pociones ¿Quieres saber algo mas? ", " Sin duda la mas divertida es cuidado de criaturas magicas, Te puedes imaginar montar un hipogrifo?",        " Algunas materias son: Adivinacion, defensa contra las artes oscuras y cuidado de criaturas magicas, ¿Quieres saber de alguna?  ", " Las asignaturas mas populares son Defensa contra las artes oscuras y pociones ¿Quieres saber algo mas? ", " Sin duda la mas divertida es cuidado de criaturas magicas, Te puedes imaginar montar un hipogrifo?",        " Algunas materias son: Adivinacion, defensa contra las artes oscuras y cuidado de criaturas magicas, ¿Quieres saber de alguna?  ", " Las asignaturas mas populares son Defensa contra las artes oscuras y pociones ¿Quieres saber algo mas? ", " Sin duda la mas divertida es cuidado de criaturas magicas, Te puedes imaginar montar un hipogrifo?",
	       " Defensa Contra las Artes Oscuras es una asignatura consistente en la enseñanza de variadas tecnicas para contrarrestar las Artes Oscuras y las criaturas de este tipo. Necesitas alguna otra informacion ? ", " Es una asignatura que se enseña desde el primer año al quinto en el Colegio Hogwarts de Magia y Hechiceria, con la opcion de un curso EXTASIS en los años sexto y septimo, siempre que se respete la nota minima, que varia segun el profesor. Hay alguna otra cosa que quieras saber?" ," Desde que entro a trabajar en Hogwarts, Severus Snape quiso enseñar Defensa Contra las Artes Oscuras. Sin embargo, Dumbledore no lo permitio y en cambio lo convirtio en profesor de Pociones. Alguna otra cosa que quieras saber? ",       " Defensa Contra las Artes Oscuras es una asignatura consistente en la enseñanza de variadas tecnicas para contrarrestar las Artes Oscuras y las criaturas de este tipo. Necesitas alguna otra informacion ? ", " Es una asignatura que se enseña desde el primer año al quinto en el Colegio Hogwarts de Magia y Hechiceria, con la opcion de un curso EXTASIS en los años sexto y septimo, siempre que se respete la nota minima, que varia segun el profesor. Hay alguna otra cosa que quieras saber?" ," Desde que entro a trabajar en Hogwarts, Severus Snape quiso enseñar Defensa Contra las Artes Oscuras. Sin embargo, Dumbledore no lo permitio y en cambio lo convirtio en profesor de Pociones. Alguna otra cosa que quieras saber? ",       " Defensa Contra las Artes Oscuras es una asignatura consistente en la enseñanza de variadas tecnicas para contrarrestar las Artes Oscuras y las criaturas de este tipo. Necesitas alguna otra informacion ? ", " Es una asignatura que se enseña desde el primer año al quinto en el Colegio Hogwarts de Magia y Hechiceria, con la opcion de un curso EXTASIS en los años sexto y septimo, siempre que se respete la nota minima, que varia segun el profesor. Hay alguna otra cosa que quieras saber?" ," Desde que entro a trabajar en Hogwarts, Severus Snape quiso enseñar Defensa Contra las Artes Oscuras. Sin embargo, Dumbledore no lo permitio y en cambio lo convirtio en profesor de Pociones. Alguna otra cosa que quieras saber? ",
	       " Transformaciones es una clase impartida en el Colegio Hogwarts de Magia y Hechiceria. Enseña el arte de cambiar la forma y apariencia de un objeto o al mago mismo. Necesitas algo mas?",  " Transformaciones es una asignatura obligatoria para todos los alumnos desde el primer año, con la opcion de cursos para los EXTASIS en sexto y septimo año. Necesitas algo mas? ", "  Minerva McGonagall fue la profesora de Transformaciones en Hogwarts desde 1956 hasta 1998, año en el que se volvio Directora del Colegio. Necesitas algo mas?",        " Transformaciones es una clase impartida en el Colegio Hogwarts de Magia y Hechiceria. Enseña el arte de cambiar la forma y apariencia de un objeto o al mago mismo. Necesitas algo mas?",  " Transformaciones es una asignatura obligatoria para todos los alumnos desde el primer año, con la opcion de cursos para los EXTASIS en sexto y septimo año. Necesitas algo mas? ", "  Minerva McGonagall fue la profesora de Transformaciones en Hogwarts desde 1956 hasta 1998, año en el que se volvio Directora del Colegio. Necesitas algo mas?",        " Transformaciones es una clase impartida en el Colegio Hogwarts de Magia y Hechiceria. Enseña el arte de cambiar la forma y apariencia de un objeto o al mago mismo. Necesitas algo mas?",  "HOGWARTSBOT> Transformaciones es una asignatura obligatoria para todos los alumnos desde el primer año, con la opcion de cursos para los EXTASIS en sexto y septimo año. Necesitas algo mas? ", "  Minerva McGonagall fue la profesora de Transformaciones en Hogwarts desde 1956 hasta 1998, año en el que se volvio Directora del Colegio. Necesitas algo mas?",
	       " Este antiguo y misterioso encantamiento conjura un guardian magico, una proyeccion de todos tus sentimientos alegres. Quieres saber algo mas? ", " El Encantamiento patronus es complicado, y muchas brujas y magos son incapaces de producir un patronus completamente corporeo. ", " De acuerdo a la leyenda, uno de los Patronus mas famosos de todos los tiempos fue un humilde raton, que creo un joven mago llamado Ilyius. Ilyius lanzo el encantamiento Patronus cuando su villa fue atacada por el mago tenebroso Raczidian y su ejercito de dementores. otra cosa que quieras saber? ",        " Este antiguo y misterioso encantamiento conjura un guardian magico, una proyeccion de todos tus sentimientos alegres. Quieres saber algo mas? ", " El Encantamiento patronus es complicado, y muchas brujas y magos son incapaces de producir un patronus completamente corporeo. ", " De acuerdo a la leyenda, uno de los Patronus mas famosos de todos los tiempos fue un humilde raton, que creo un joven mago llamado Ilyius. Ilyius lanzo el encantamiento Patronus cuando su villa fue atacada por el mago tenebroso Raczidian y su ejercito de dementores. otra cosa que quieras saber? ",        " Este antiguo y misterioso encantamiento conjura un guardian magico, una proyeccion de todos tus sentimientos alegres. Quieres saber algo mas? ", " El Encantamiento patronus es complicado, y muchas brujas y magos son incapaces de producir un patronus completamente corporeo. ", " De acuerdo a la leyenda, uno de los Patronus mas famosos de todos los tiempos fue un humilde raton, que creo un joven mago llamado Ilyius. Ilyius lanzo el encantamiento Patronus cuando su villa fue atacada por el mago tenebroso Raczidian y su ejercito de dementores. otra cosa que quieras saber? ",
	       " Los Dementores estan entre las criaturas mas nauseabundas del mundo. Infestan los lugares mas oscuros y mas sucios. Quieres saber algo mas? ", " Si alguien se acerca mucho a un Dementor, este le quitara hasta el ultimo sentimiento positivo y hasta el ultimo recuerdo dichoso. ", " Los Dementores son seres horribles de gran estatura, cubiertos por una capa de color negro. Son temidos porque se alimentan de la felicidad y de los recuerdos alegres Necesitas algo mas?",        " Los Dementores estan entre las criaturas mas nauseabundas del mundo. Infestan los lugares mas oscuros y mas sucios. Quieres saber algo mas? ", " Si alguien se acerca mucho a un Dementor, este le quitara hasta el ultimo sentimiento positivo y hasta el ultimo recuerdo dichoso. ", " Los Dementores son seres horribles de gran estatura, cubiertos por una capa de color negro. Son temidos porque se alimentan de la felicidad y de los recuerdos alegres Necesitas algo mas?",        " Los Dementores estan entre las criaturas mas nauseabundas del mundo. Infestan los lugares mas oscuros y mas sucios. Quieres saber algo mas? ", " Si alguien se acerca mucho a un Dementor, este le quitara hasta el ultimo sentimiento positivo y hasta el ultimo recuerdo dichoso. ", "  Los Dementores son seres horribles de gran estatura, cubiertos por una capa de color negro. Son temidos porque se alimentan de la felicidad y de los recuerdos alegres Necesitas algo mas?",
	       " No necesitan muros y agua para mantener a los prisioneros adentro, no cuando estan atrapados dentro de sus propias mentes, incapaces de tener un unico pensamiento feliz. Hay alguna otra cosa en la que te pueda ayudar?", " Azkaban es una fortaleza en una isla ubicada en el medio del Mar del Norte. Sirve a la comunidad magica de Gran Bretaña como una prision para criminales convictos.  otra cosa que quieras saber?" ," Desde 1717 el uso de cualquiera de las tres Maldiciones Imperdonables sobre otro ser humano acarreaba el castigo de cadena perpetua en Azkaban, otra cosa que quieras saber? ",       " No necesitan muros y agua para mantener a los prisioneros adentro, no cuando estan atrapados dentro de sus propias mentes, incapaces de tener un unico pensamiento feliz. Hay alguna otra cosa en la que te pueda ayudar?", " Azkaban es una fortaleza en una isla ubicada en el medio del Mar del Norte. Sirve a la comunidad magica de Gran Bretaña como una prision para criminales convictos.  otra cosa que quieras saber?" ," Desde 1717 el uso de cualquiera de las tres Maldiciones Imperdonables sobre otro ser humano acarreaba el castigo de cadena perpetua en Azkaban, otra cosa que quieras saber? ",        " No necesitan muros y agua para mantener a los prisioneros adentro, no cuando estan atrapados dentro de sus propias mentes, incapaces de tener un unico pensamiento feliz. Hay alguna otra cosa en la que te pueda ayudar?", " Azkaban es una fortaleza en una isla ubicada en el medio del Mar del Norte. Sirve a la comunidad magica de Gran Bretaña como una prision para criminales convictos.  otra cosa que quieras saber?" ," Desde 1717 el uso de cualquiera de las tres Maldiciones Imperdonables sobre otro ser humano acarreaba el castigo de cadena perpetua en Azkaban, otra cosa que quieras saber? ",
	       " El quidditch es el mejor deporte del mundo magico,¿juegas en alguna posicion ?",   " El quidditch es el mas conocido ¿Cuál es tu escoba para el quidditch? ", " El Quidditch es el deporte mas popular en la comunidad magica. Es una especie de futbol-baloncesto aereo que se juega volando sobre escobas. Necesitas algo mas? ",        " El quidditch es el mejor deporte del mundo magico,¿juegas en alguna posicion ?",   " El quidditch es el mas conocido ¿Cuál es tu escoba para el quidditch? ", " El Quidditch es el deporte mas popular en la comunidad magica. Es una especie de futbol-baloncesto aereo que se juega volando sobre escobas. Necesitas algo mas? ",        " El quidditch es el mejor deporte del mundo magico,¿juegas en alguna posicion ?",   " El quidditch es el mas conocido ¿Cuál es tu escoba para el quidditch? ", " El Quidditch es el deporte mas popular en la comunidad magica. Es una especie de futbol-baloncesto aereo que se juega volando sobre escobas. Necesitas algo mas? ",
	       " Se molestan con mucha facilidad. Nunca ofendais a ninguno, porque podria ser lo ultimo que hicierais. Quieres saber algo más?", " Un hipogrifo es un animal magico que se obtiene al cruzar un caballo con un grifo. Necesitas algo mas? " ," son orgullosos. Se molestan con mucha facilidad. Nunca ofendais a ninguno, porque podria ser lo ultimo que hicierais,  Hay alguna otra cosa en la que te pueda ayudar? ",        " Se molestan con mucha facilidad. Nunca ofendais a ninguno, porque podria ser lo ultimo que hicierais. Quieres saber algo más?", " Un hipogrifo es un animal magico que se obtiene al cruzar un caballo con un grifo. Necesitas algo mas? " ," son orgullosos. Se molestan con mucha facilidad. Nunca ofendais a ninguno, porque podria ser lo ultimo que hicierais,  Hay alguna otra cosa en la que te pueda ayudar? ",        " Se molestan con mucha facilidad. Nunca ofendais a ninguno, porque podria ser lo ultimo que hicierais. Quieres saber algo más?", " Un hipogrifo es un animal magico que se obtiene al cruzar un caballo con un grifo. Necesitas algo mas? " ," son orgullosos. Se molestan con mucha facilidad. Nunca ofendais a ninguno, porque podria ser lo ultimo que hicierais,  Hay alguna otra cosa en la que te pueda ayudar? ",
	       " Harry James Potter (n. el 31 de julio de 1980), es un mago de sangre mestiza, y el unico hijo de James y Lily Potter. otra cosa que quieras saber? ", " Es la unica persona conocida que ha sobrevivido a la maldicion de Avada Kedavra, haciendolo en dos ocasiones. Necesitas algo mas? ", "  Comienza a asistir a Hogwarts y es seleccionado en la Casa de Gryffindor. Se convierte en el mejor amigo de Ron Weasley y Hermione Granger, Hay alguna otra cosa en la que te pueda ayudar?",        " Harry James Potter (n. el 31 de julio de 1980), es un mago de sangre mestiza, y el unico hijo de James y Lily Potter. otra cosa que quieras saber? ", " Es la unica persona conocida que ha sobrevivido a la maldicion de Avada Kedavra, haciendolo en dos ocasiones. Necesitas algo mas? ", "  Comienza a asistir a Hogwarts y es seleccionado en la Casa de Gryffindor. Se convierte en el mejor amigo de Ron Weasley y Hermione Granger, Hay alguna otra cosa en la que te pueda ayudar?",        " Harry James Potter (n. el 31 de julio de 1980), es un mago de sangre mestiza, y el unico hijo de James y Lily Potter. otra cosa que quieras saber? ", " Es la unica persona conocida que ha sobrevivido a la maldicion de Avada Kedavra, haciendolo en dos ocasiones. Necesitas algo mas? ", "  Comienza a asistir a Hogwarts y es seleccionado en la Casa de Gryffindor. Se convierte en el mejor amigo de Ron Weasley y Hermione Granger, Hay alguna otra cosa en la que te pueda ayudar?",
	       " Ronald 'Ron' Bilius Weasley, nacido el 1 de marzo de 1980, es el hijo de Arthur y Molly Weasley, siendo el sexto de siete hijos ", " Ron crecio en el hogar de su familia, La Madriguera, cerca de la villa de Ottery St. Catchpole en Devon. Ron tiene cinco hermanos, Bill, Charlie, Percy, Fred y George. Tambien tiene una hermana pequeña llamada Ginny. otra cosa que quieras saber? ", " Lo seleccionaron para la casa de Gryffindor, como cualquier otro Weasley.  otra cosa que quieras saber?",       " Ronald 'Ron' Bilius Weasley, nacido el 1 de marzo de 1980, es el hijo de Arthur y Molly Weasley, siendo el sexto de siete hijos ", " Ron crecio en el hogar de su familia, La Madriguera, cerca de la villa de Ottery St. Catchpole en Devon. Ron tiene cinco hermanos, Bill, Charlie, Percy, Fred y George. Tambien tiene una hermana pequeña llamada Ginny. otra cosa que quieras saber? ", " Lo seleccionaron para la casa de Gryffindor, como cualquier otro Weasley.  otra cosa que quieras saber?",        " Ronald 'Ron' Bilius Weasley, nacido el 1 de marzo de 1980, es el hijo de Arthur y Molly Weasley, siendo el sexto de siete hijos ", " Ron crecio en el hogar de su familia, La Madriguera, cerca de la villa de Ottery St. Catchpole en Devon. Ron tiene cinco hermanos, Bill, Charlie, Percy, Fred y George. Tambien tiene una hermana pequeña llamada Ginny. otra cosa que quieras saber? ", " Lo seleccionaron para la casa de Gryffindor, como cualquier otro Weasley.  otra cosa que quieras saber?",
	       " Fue un gusto ayudarlo, distruta tu estadia en Hogwarts, que pases un buen día!  ", " Hogwarts es la mejor escuela del mundo magico, disfruta mucho, que pases una buena tarde!", " Adios, Cuando necesites ayuda no dudes en buscarme, buenas noches! ",         " Fue un gusto ayudarlo, distruta tu estadia en Hogwarts, que pases un buen día!  ", " Hogwarts es la mejor escuela del mundo magico, disfruta mucho, que pases una buena tarde!", " Adios, Cuando necesites ayuda no dudes en buscarme, buenas noches! ",        " Fue un gusto ayudarlo, distruta tu estadia en Hogwarts, que pases un buen día!  ", " Hogwarts es la mejor escuela del mundo magico, disfruta mucho, que pases una buena tarde!", " Adios, Cuando necesites ayuda no dudes en buscarme, buenas noches! ", 
	       " Usa alohomora cuando olvides tus llaves Quieres saber algo mas? ", " Los tres maleficios imperdonables son Avada kedavra, Crucius e Imperio, ¡Nunca los uses!, otra cosa que quieras saber?", " expelliarmus es el favorito de Harry, desarma de su varita a tu oponente Necesitas algo mas?",        " Usa alohomora cuando olvides tus llaves Quieres saber algo mas? ", " Los tres maleficios imperdonables son Avada kedavra, Crucius e Imperio, ¡Nunca los uses!, otra cosa que quieras saber?", " expelliarmus es el favorito de Harry, desarma de su varita a tu oponente Necesitas algo mas?",        " Usa alohomora cuando olvides tus llaves Quieres saber algo mas? ", " Los tres maleficios imperdonables son Avada kedavra, Crucius e Imperio, ¡Nunca los uses!, otra cosa que quieras saber?", " expelliarmus es el favorito de Harry, desarma de su varita a tu oponente Necesitas algo mas?",
	       " Los profesores son Minerva Mcgonagall de transformaciones, Rubius Hagrid de cuidado de criaturas magicas, pero ten cuidado en pociones con Snape, Quieres saber algo mas?", " Algunos dicen que la profesora de adivinacion esta loca! y que Mcgonagall es muy estricta. Quieres saber algo mas? ", " Dumbledore tambien fue profesor del colegio, pero hace muchos años, era el mejor en transformaciones. Quieres saber algo mas?",        " Los profesores son Minerva Mcgonagall de transformaciones, Rubius Hagrid de cuidado de criaturas magicas, pero ten cuidado en pociones con Snape, Quieres saber algo mas?", " Algunos dicen que la profesora de adivinacion esta loca! y que Mcgonagall es muy estricta. Quieres saber algo mas? ", " Dumbledore tambien fue profesor del colegio, pero hace muchos años, era el mejor en transformaciones. Quieres saber algo mas?",       " Los profesores son Minerva Mcgonagall de transformaciones, Rubius Hagrid de cuidado de criaturas magicas, pero ten cuidado en pociones con Snape, Quieres saber algo mas?", " Algunos dicen que la profesora de adivinacion esta loca! y que Mcgonagall es muy estricta. Quieres saber algo mas? ", " Dumbledore tambien fue profesor del colegio, pero hace muchos años, era el mejor en transformaciones. Quieres saber algo mas?",
	       " no logre entender tu pregunta porque es demasiado muggle, quizas quisiste decir ", " No entiendo lo que quieres, puede que sea algo del mundo muggle, quizas quisiste decir ", " No entiendo lo que quieres, quizas quisiste decir",       " no logre entender tu pregunta porque es demasiado muggle, quizas quisiste decir ", " No entiendo lo que quieres, puede que sea algo del mundo muggle, quizas quisiste decir ", " No entiendo lo que quieres, quizas quisiste decir",       " no logre entender tu pregunta porque es demasiado muggle, quizas quisiste decir ", " No entiendo lo que quieres, puede que sea algo del mundo muggle, quizas quisiste decir ", " No entiendo lo que quieres, quizas quisiste decir"};



	palabrasClave = new String[]{"Saludo", "casas Casas  Casas? casas?", "Ravenclaw ravenclaw ravenclaw? Ravenclaw?","Gryffindor gryffindor gryffindor? Gryffindor?","Slytherin slytherin Slytherin? slytherin?","Hufflepuff hufflepuff hufflepuff? Hufflepuff?", "historia Historia Hogwarts hogwarts historia? Historia? Hogwarts? hogwarts?","asignaturas materias ramos asignaturas?" ,"Defensa defensa oscuras oscuras?","transformaciones Transformaciones Transformaciones? transformaciones?", "patronus Patronus patronus? Patronus?" ,"dementor Dementor dementores Dementores dementor? Dementor? dementores? Dementores?","Azkaban prision azkaban Prision Azkaban? azkaban? prision? Prision?", "deportes deportes? quidditch quidditch? Quidditch Quidditch?",
	 "Hipogrifo hipogrifo hipogrifos Hipogrifos hipogrifos? hipogrifo? Hipogrifos? Hipogrifo?","Harry harry potter Potter Harry? harry? Potter? potter?","Ron ron Ronald ronald Weasley weasley ron? Ron? Weasley? weasley?","fin","hechizos Hechizos hechizo Hechizo hechizo? hechizos? ", "profesores Profesores maestros Maestros Profesor profesor Maestro profesores? Profesores?",""};
	}


	/**
	* MÉTODOS PÚBLICOS
	**/

	 /**
	 * Entrega la respuesta que se encuentra en una posición específica
	 * @param posicion : número con la posición en que se encuentra el valor requerido
	 *        respuestas: lista de respuestas con la personalidad del chatbot 
	 * @return Frase con la respuesta del chatbot
	 */    
	public String  getRespuesta(int posicion){
	      return respuestas[posicion];
	}

	 /**
	 * Busca una coincidencia entre las palabras clave y la frase que ingresa el usuario
	 * @param frase: Frase ingresada por el usuario
	 *        valor: número random, puede ser ingresado por el usuario o generado
	 *        personalidad: valor que indica que personalidad responderá al usuario
	 *		  log: log permite acceder a los métodos que modifican la conversación
	 *		  user: permite acceder a los métodos que entregan las palabras mas buscadas y son entregadas cuando el usuario ingresó una frase
	 *.             que chatbot no entiende
	 *        hora: String con la hora en que se generó el mensaje
	 * @return posicion: índice en donde se encuentra la respuesta de chatbot
	 */    
	public void coincidencia(String frase, int valor,int personalidad, Log log,Usuario user,String hora){
	    seed = getSemilla(valor);
		indice = 0;
		posicion = -1;
		int aux = palabrasClave.length;
		String [] palabras = frase.split(" ");
		for(String palabra: palabras){ 
			while(indice<aux){
				if(palabrasClave[indice].contains(palabra)){
					posicion = 9*indice + seed + personalidad*3;
					break;
				}
				indice++;

			}
			indice=0;
	}
	if(posicion == -1){
		posicion = 180 + seed+ personalidad;
		log.modificarLog("["+hora +"] USUARIO> " + frase,respuestas[posicion] + user.frecuencia(log.getLog()),hora);

	}
	else{
	 log.modificarLog("["+hora+"] USUARIO> " + frase,respuestas[posicion], hora);
}
	}


	/**
	 * Genera un valor random según la semilla
	 * @param valor: número de entrada que permite generar el valor seudo aleatorio
	 * @return Seed: valor seudo aleatorio generado
	 */    
	public int getSemilla(int valor){
	      seed = (valor * 2)% 3;
	      return seed;
	}

	 /**
	 * Devuelve la hora, minuto y segundo
	 * @return tiempo : Hora/Minuto/Segundo
	 */    
	private  String getTime(){
	      Date date = new Date();
	      DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
	      tiempo =   hourFormat.format(date);
	      return tiempo;
	 }

	/**
	 * Toma una respuesta de chatbot que contiene un mensaje de bienvenida al usuario.
	 * según la hora del día
	 * @param personalidad: valor que indica que personalidad responderá al usuario
	 *         log: permite acceder al método modificar log
	 *         horaInterfaz: hora en que se realizo la solicitud de inicio de conversación
	 * @return respuesta: String con mensaje de bienvenida.
	 */    
	public  void beginDialog(int personalidad, Log log, String horaInterfaz){
	      horaSegundoMinuto = getTime().split(":"); 
	      hora = Integer.parseInt(horaSegundoMinuto[0]);

	      if(hora>=7 && hora<12){
	            respuesta = respuestas[personalidad];
	      }
	      else if(hora>=12 && hora<=19){
	            respuesta = respuestas[1+personalidad*3];
	      }
	      else {
	            respuesta = respuestas[2+personalidad*3];

	      }
	     setPersonalidad(personalidad);
	     log.modificarLog("<<<<<INICIO CONVERSACIÓN>>>>>\n",respuesta,horaInterfaz);
	}


	 /**
	 * Toma una respuesta de chatbot que contiene un mensaje de despedida
	 * según la hora del día
	 * @param respuestas: personalidad del chatbot que se utiliza.
	 *        personalidad: valor que indica que personalidad responderá al usuario
	 * @return respuesta: String con mensaje de despedida.
	 */ 
	  public void endDialog(int personalidad,Log log, String horaInterfaz){

	      horaSegundoMinuto = getTime().split(":"); 
	      hora = Integer.parseInt(horaSegundoMinuto[0]);
	      if(hora>=7 && hora<12){
	            respuesta = respuestas[153 + personalidad*3];
	      }
	      else if(hora>=12 && hora<=19){
	            respuesta = respuestas[154 + personalidad*3];
	      }
	      else {
	            respuesta = respuestas[155 + personalidad*3];

	      }

		log.modificarLog(" ",respuesta + "\n<<<<<FIN CONVERSACIÓN>>>>>\n",horaInterfaz);
	}
	/**
	* Lista con las personalidades de cada conversación
	* @return: lista de personalidades
	*/
		public List<String>  getPersonalidad(){
					return personalidad;
			}

	/**
	* MÉTODOS PRIVADOS
	*/
	/**
	* Dependiendo del valor de la semilla asigna una personalidad que interactuará con el usuario durante toda la conversación
	* @param: Semilla: dato "random" ingresado por el usuario o elegido por el chatbot
	* @return : String con la personalidad del chatbot
	*/
	private void setPersonalidad(int semilla){

		if(semilla == 0) personalidad.add("Formal");
		else if(semilla == 1) personalidad.add("Simpático");
		else personalidad.add("Enojado");

	}
	








}