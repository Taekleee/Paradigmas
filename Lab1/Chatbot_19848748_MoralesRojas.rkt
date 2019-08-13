#lang scheme
(require racket/date)
;------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
;TDA CHATBOT
;Representación: Lista de listas con las respuestas que puede generar el chatbot,
;en el inicio de cada sublista se encuentra un ID que permite contextualizar lo ingresado por el usuario
;                con la respuesta del chatbot. Este chatbot corresponde a un guía por Hogwarts.   
(define chatbot
 (list (list "Saludo" " HOGWARTSBOT: Buenos Días, bienvenido a la Hogwarts, hoy seré tu guía en el castillo, ¿En qué puedo ayudarte ? " " HOGWARTSBOT: Buenas Tardes, bienvenido a Hogwarts, hoy seré tu guía en el castillo,  ¿En qué puedo ayudarte? " "HOGWARTSBOT: Buenas noches,  bienvenido a Hogwarts, hoy seré tú guía en el castillo,  ¿En qué puedo ayudarte? ")
       (list "casas"  " HOGWARTSBOT: Hay cuatro casas en Hogwarts, ¿cuál es la tuya? " " HOGWARTSBOT: ¿A que casa de Hogwarts perteneces? " " HOGWARTSBOT: Todas las casas tienen algo bueno y también malo, ¿Cuál es la tuya? ")
       (list "Ravenclaw" " HOGWARTSBOT: Ravenclaw es una de las cuatro casas que componen el Colegio Hogwarts de Magia y Hechicería, fundada por Rowena Ravenclaw, quién tuvo una hija, cuyo fantasma es la Dama Gris. ¿Qué más quieres saber? " "  HOGWARTSBOT: La casa Ravenclaw premia el aprendizaje, la sabiduría, el ingenio, y el intelecto de sus miembros. ¿Qué más quieres saber? " "HOGWARTSBOT: Para entrar a la sala común de ravenclaw, se debe resolver un acertijo que no siempre es el mismo, formulado por un aldaba de bronce con forma de águila en una puerta sin picaporte. ¿Qué más quieres saber? ") (list "Gryffindor" " HOGWARTSBOT:  Fundada por Godric Gryffindor. Su animal emblemático es el león y sus colores son el rojo escarlata y el dorado. Minerva McGonagall es la más reciente jefa de la casa. ¿Qué más quieres saber? " " HOGWARTSBOT: La sala común de Gryffindor está localizada en la Torre de Gryffindor, la entrada está localizada en el séptimo piso y está custodiada por el retrato de la Dama Gorda, que utiliza un vestido rosa. Ella permite la entrada solamente si la clave es la correcta (cambia regularmente).  ¿Qué más quieres saber? " " HOGWARTSBOT: La Casa de Gryffindor estima el coraje, así como la osadía, el temple y la caballerosidad, así, sus miembros se caracterizan por ser valientes aunque a veces hasta el punto de ser imprudentes.  ¿Qué más quieres saber? ")(list "Slytherin" " HOGWARTSBOT: La sala común de Slytherin está ubicada detrás de la entrada escondida en las mazmorras, sus ventanas proporcionan una luz verde, pues dan a las profundidades del lago de Hogwarts. Los alumnos de Slytherin, a menudo ven al calamar gigante nadando rápidamente y, a veces, criaturas aún más interesantes.  ¿Qué más quieres saber? " " HOGWARTSBOT: Los alumnos de Slytherin tienden a ser ambiciosos, inteligentes, astutos, líderes fuertes, y orientados hacia los logros. También tienen muy desarrollado el instinto de supervivencia. ¿Qué más quieres saber? " "HOGWARTSBOT: El Sombrero Seleccionador dice que la pureza de sangre es un factor en la selección de Slytherin. ")(list "Hufflepuff" " HOGWARTSBOT: Hufflepuff es una de las cuatro casas del Colegio Hogwarts de Magia y Hechicería. Su fundadora es la bruja medieval Helga Hufflepuff. La jefa de la casa es Pomona Sprout. ¿Qué más quieres saber?  " " HOGWARTSBOT: Los estudiantes de esta casa son conocidos por ser trabajadores, amigables, leales y sin prejuicios. Debido a sus valores, los hufflepuffs no son tan competitivos como las otras casas, o son más modestos con respecto a sus logros. ¿Qué más quieres saber? " " HOGWARTSBOT:  Cedric Diggory llevó a su casa a un esplendor poco común como capitán de Quidditch y más tarde como Campeón de Hogwarts en el Torneo de los Tres Magos en 1994.  ¿Qué más quieres saber? ")
       (list "historia" " HOGWARTSBOT: El Colegio Hogwarts de Magia y Hechicería es un internado mágico ubicado en Escocia. El castillo se ubica en unas montañas cercanas a un lago. ¿Te interesa algo más? " "HOGWARTSBOT:  Los encantamientos que protegen al castillo son clasificados como encantamientos anti-Aparición y encantos repelentes de Muggles, que lo hacen que ellos vean unas ruinas antiguas con un letrero que dice 'Peligro, prohibido el paso' en lugar del castillo. ¿Quieres saber algo más? " " HOGWARTSBOT: El lema de Hogwarts es: 'Draco dormiens nunquam titillandus' . La traducción es: Nunca hagas cosquillas a un dragón dormido.¿Quieres saber algo más? ")
       (list "asignaturas" " HOGWARTSBOT: Algunas materias son: Adivinación, defensa contra las artes oscuras y cuidado de criaturas mágicas, ¿Quieres saber de alguna?  " " HOGWARTSBOT: Las asignaturas más populares son Defensa contra las artes oscuras y pociones ¿Quieres saber algo más? " " HOGWARTSBOT: Sin duda la más divertida es cuidado de criaturas mágicas, ¿Te puedes imaginar montar un hipogrifo? ") (list "Defensa" " HOGWARTSBOT: Defensa Contra las Artes Oscuras es una asignatura consistente en la enseñanza de variadas técnicas para contrarrestar las Artes Oscuras y las criaturas de este tipo. " " HOGWARTSBOT: Es una asignatura que se enseña desde el primer año al quinto en el Colegio Hogwarts de Magia y Hechiceria, con la opción de un curso EXTASIS en los años sexto y séptimo, siempre que se respete la nota mínima, que varía según el profesor.¿ otra cosa que quieras saber?" " HOGWARTSBOT: Desde que entró a trabajar en Hogwarts, Severus Snape quiso enseñar Defensa Contra las Artes Oscuras. Sin embargo, Dumbledore no lo permitió y en cambio lo convirtió en profesor de Pociones.¿ otra cosa que quieras saber? ") (list  "Adivinación" " HOGWARTSBOT: Transformaciones es una clase impartida en el Colegio Hogwarts de Magia y Hechiceria. Enseña el arte de cambiar la forma y apariencia de un objeto o al mago mismo. ¿Necesitas algo más?"  " HOGWARTSBOT: Transformaciones es una asignatura obligatoria para todos los alumnos desde el primer año, con la opción de cursos para los ÉXTASIS en sexto y séptimo año.¿Necesitas algo más? " " HOGWARTSBOT:  Minerva McGonagall fue la profesora de Transformaciones en Hogwarts desde 1956 hasta 1998, año en el que se volvió Directora del Colegio. ¿Necesitas algo más?") 
       (list "patronus" " HOGWARTSBOT: Este antiguo y misterioso encantamiento conjura un guardián mágico, una proyección de todos tus sentimientos alegres. ¿Quieres saber algo más? " " HOGWARTSBOT: El Encantamiento patronus es complicado, y muchas brujas y magos son incapaces de producir un patronus completamente corpóreo. " " HOGWARTSBOT: De acuerdo a la leyenda, uno de los Patronus más famosos de todos los tiempos fue un humilde ratón, que creó un joven mago llamado Ilyius. Ilyius lanzó el encantamiento Patronus cuando su villa fue atacada por el mago tenebroso Raczidian y su ejército de dementores.¿ otra cosa que quieras saber? ")
       (list "dementor" " HOGWARTSBOT: Los Dementores están entre las criaturas mas nauseabundas del mundo. Infestan los lugares mas oscuros y mas sucios. ¿Quieres saber algo más? " " HOGWARTSBOT: Si alguien se acerca mucho a un Dementor, este le quitara hasta el ultimo sentimiento positivo y hasta el ultimo recuerdo dichoso. " " HOGWARTSBOT: Los Dementores son seres horribles de gran estatura, cubiertos por una capa de color negro. Son temidos porque se alimentan de la felicidad y de los recuerdos alegres ¿Necesitas algo más?") (list "Azkaban" " HOGWARTSBOT: No necesitan muros y agua para mantener a los prisioneros adentro, no cuando están atrapados dentro de sus propias mentes, incapaces de tener un único pensamiento feliz. " " HOGWARTSBOT: Azkaban es una fortaleza en una isla ubicada en el medio del Mar del Norte. Sirve a la comunidad mágica de Gran Bretaña como una prisión para criminales convictos. ¿ otra cosa que quieras saber?" " HOGWARTSBOT: Desde 1717 el uso de cualquiera de las tres Maldiciones Imperdonables sobre otro ser humano acarreaba el castigo de cadena perpetua en Azkaban¿ otra cosa que quieras saber? ")
       (list "deportes" " HOGWARTSBOT: El quidditch es el mejor deporte del mundo mágico,¿juegas en alguna posición ?"  " HOGWARTSBOT: ¿Tienes alguna posición en el equipo?" " HOGWARTSBOT: El quidditch es el más conocido ¿Cuál es tu escoba para el quidditch? " " HOGWARTSBOT: El Quidditch es el deporte más popular en la comunidad mágica. Es una especie de fútbol-baloncesto aéreo que se juega volando sobre escobas.¿Necesitas algo más? ")
       (list "Hipogrifo" " HOGWARTSBOT: Se molestan con mucha facilidad. Nunca ofendáis a ninguno, porque podría ser lo último que hicierais. ¿Quieres saber algo más?" " HOGWARTSBOT: Un hipogrifo es un animal mágico que se obtiene al cruzar un caballo con un grifo.¿Necesitas algo más? " " HOGWARTSBOT: son orgullosos. Se molestan con mucha facilidad. Nunca ofendáis a ninguno, porque podría ser lo último que hicierais. ")
       (list "Harry" " HOGWARTSBOT: Harry James Potter (n. el 31 de julio de 1980), es un mago de sangre mestiza, y el único hijo de James y Lily Potter. ¿ otra cosa que quieras saber? " " HOGWARTSBOT: Es la única persona conocida que ha sobrevivido a la maldición de Avada Kedavra, haciéndolo en dos ocasiones. ¿Necesitas algo más? " " HOGWARTSBOT:  Comienza a asistir a Hogwarts y es seleccionado en la Casa de Gryffindor. Se convierte en el mejor amigo de Ron Weasley y Hermione Granger. ")
       (list "Ron" " HOGWARTSBOT: Ronald 'Ron' Bilius Weasley, nacido el 1 de marzo de 1980, es el hijo de Arthur y Molly Weasley, siendo el sexto de siete hijos " " HOGWARTSBOT: Ron creció en el hogar de su familia, La Madriguera, cerca de la villa de Ottery St. Catchpole en Devon. Ron tiene cinco hermanos, Bill, Charlie, Percy, Fred y George. También tiene una hermana pequeña llamada Ginny.¿ otra cosa que quieras saber? " " HOGWARTSBOT: Lo seleccionaron para la casa de Gryffindor, como cualquier otro Weasley. ¿ otra cosa que quieras saber?") 
       (list "fin" " HOGWARTSBOT: Fue un gusto ayudarlo, distruta tu estadía en Hogwarts ¿Quieres saber algo más? " " HOGWARTSBOT: Hogwarts es la mejor escuela del mundo mágico, disfruta mucho" " HOGWARTSBOT: Adiós, Cuando necesites ayuda no dudes en buscarme " " HOGWARTSBOT: Si quieres saber algo sólo búscame ")
       (list "nula" " HOGWARTSBOT: no logré entender tu pregunta porque es demasiado muggle, quizás quisiste decir " "HOGWARTSBOT: No entiendo lo que quieres, puede que sea algo del mundo muggle, quizás quisiste decir " " HOGWARTSBOT: La lechuza con lo que me decías no llegó, la encontró un muggle quizás quisiste decir" "HOGWARTSBOT: No entiendo lo que quieres, quizás quisiste decir")
       (list "hechizos" " HOGWARTSBOT: Usa alohomora cuando olvides tus llaves ¿Quieres saber algo más? " " HOGWARTSBOT: Los tres maleficios imperdonables son Avada kedavra, Crucius e Imperio, ¡Nunca los uses! ¿ otra cosa que quieras saber?" "  HOGWARTSBOT:expelliarmus es el favorito de Harry, desarma de su varita a tu oponente ¿Necesitas algo más?")
       (list "profesores" " HOGWARTSBOT: Los profesores son Minerva Mcgonagall de transformaciones, Rubius Hagrid de cuidado de criaturas mágicas, pero ten cuidado en pociones con Snape ¿Quieres saber algo más?" " HOGWARTSBOT: Algunos dicen que la profesora de adivinación está loca! y que Mcgonagall es muy estricta. ¿Quieres saber algo más? " " HOGWARTSBOT: Dumbledore también fue profesor del colegio, pero hace muchos años, era el mejor en transformaciones. ¿Quieres saber algo más?")
       (list "puntos" " HOGWARTSBOT: no se cuantos puntos tienen las casas ahora, te lo digo más tarde" " HOGWARTSBOT: no se cuantos puntos tienen las casas ahora, te lo digo más tarde ¿ otra cosa que quieras saber?"  " HOGWARTSBOT: no se cuantos puntos tienen las casas ahora, te lo digo más tarde ¿ otra cosa que quieras saber?" )
       (list "ahora" " HOGWARTSBOT: Gryffindor 1000 Hufflepuf 500 Ravenclaw 400  slytherin 100 " " HOGWARTSBOT: Gryffindor 1000 Hufflepuf 500 Ravenclaw 400  slytherin 100 " " HOGWARTSBOT: Gryffindor 1000 Hufflepuf 500 Ravenclaw 400  slytherin 100 " " HOGWARTSBOT: Gryffindor 1000 Hufflepuf 500 Ravenclaw 400  slytherin 100 ")
       (list "palabrasClave" "ravenclaw"  "harry"  "hechizos"  "profesores"  "hipogrifo" )
       (list  0 2 0 0 0)
       (list "rate")
  ))
;Como log corresponde al resultado de la conversación, se encuentra vacío en un inicio
(define log "")
;Usuarios
(define user1 (list "cuantos puntos tienen las casas ?  "  " yo soy ravenclaw" "quien es harry ?  " "qué es un hipogrifo ?" "me gustaría conocer algunos hechizos" "cuales son las asignaturas ?" "ahora cuantos puntos tienen las casas " "quienes son los profesores ?"))
(define user2 (list "mi casa es gryffindor" " quiero conocer algunos hechizos " "cuales son las asignaturas de Hogwarts ?" " qué es un dementor ? "  " ¿ quién es Harry ?" " ¿hay deportes ? " " espero pasar paradigmas " " qué es defensa contra las artes oscuras ?" " qué hechizos son conocidos ?"))
(define user3 (list " quienes son los profesores ?" " me dijeron que un alumno se llama Ron , ¿ quién es ?" " cuáles son las asignaturas ?" "¿ cuántos puntos tienen las casas ?" "¿ Qué es Azkaban ?" " ¿ Qué es adivinación ? o ¿ de qué trata ?"))
;------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
;CONSTRUCTOR
;Para que sea un TDA CHATBOT, debe corresponder a una lista de listas, las cuales el primer elemento corresponde a un string identificador
;( excepto en 1 caso) de respuestas, el resto puede ser tanto string como números.
;Dominio: Lista de listas
;Recorrido: Lista de listas
;Recursión: Natural, ya que debe esperar hasta el último llamado para construir la lista de listas.
(define (Chatbot respuestas)
  (if (null? respuestas)
      null
      (if  (map list? (car respuestas))
          (cons (car respuestas) (chatbot (cdr respuestas)))
          null
      )))

;------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
;FUNCIÓN DE PERTENENCIA 
;Nombre función : chatbot?
;Dominio: Lista con las respuestas del chatbot
;Recorrido: booleano que indica si chatbot pertenece al tipo de dato chatbot, donde el primer elemento es una lista, y el primer elemento de la
;lista es un string que corresponde al ID de la palabra buscada
(define (chatbot?  R )
 (and (list? chatbot) (not (null? R))
      (list? (car R))
      (string? (caar R))
       ))
;Usuario?
;Dominio: string ingresado por el usuario
;Recorrido: booleano que indica si lo ingresado por el usuario corresponde a un string.
(define (usuario? U)
  (andmap string? U))

;------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
;SELECTORES
;Nombre función : getRespuesta
;Dominio: String entrada de usuario, TDA chatbot
;Recorrido: String con respuesta al usuario
;Comprueba que el dato ingresado sea tipo Chatbot, que la entrada del usuario sea un string  y Utiliza la función "random" para elegir cual respuesta de la lista de respuestas utilizará
;Tipo Recursión : de cola, ya que no son necesario los estados de espera, porque para la recursión cuando encuentra la línea con el identificador buscado y entrega el string de resultado

(define (getRespuesta InUsuario chatbot seed)
  (if (and (chatbot? chatbot) (string? InUsuario))
      (if (string-ci=? InUsuario  (caar chatbot))
          (list-ref (car chatbot)  (semilla seed))
          (getRespuesta InUsuario (cdr chatbot) seed)
      )
      null
      ))
;PalabrasClave
;Dominio: String
;Recorrido: Entero positivo que corresponde a la posición de la palabra que presenta mayor frecuencia, no puede ser mayor al largo de la lista.
;Recursión: Natural, ya que espera hasta el último llamado para devolver la posición de la palabra con mayor frecuencia
(define (palabrasClave chatbot)
      (if (null? chatbot)
          null
          (if (string-ci=? "palabrasClave"  (caar chatbot))
              (palabraClave2 (cdadr chatbot) 0 0)
              (palabrasClave (cdr chatbot)))))
;PalabraClave2
;Dominio: chatbot, entero con la posición, y entero contador
;Recorrido: posición de la palabra con mayor frecuencia
;Recursión: Natural, ya que espera hasta el último llamado para devolver la posición de la palabra con mayor frecuencia
(define (palabraClave2 chatbot posicion cont)
  (if (null?  chatbot)
      posicion
      (if (>= (car chatbot) cont)
          (palabraClave2 (cdr chatbot)  (+ posicion 1) (car chatbot))
          (palabraClave2 (cdr chatbot) posicion cont)
  )))

;------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
;MODIFICADORES
;beginDialog
;Dominio: TDA chatbot, log donde se almacena la información y la semilla que indica que respuesta entregar (número positivo)
;Recorrido: Log actualizado con un identificador que indica que el chatbot envió el mensaje y el mensaje enviado 
;No presenta recursión, llama a la función get para obtener de el TDA chatbot un mensaje de saludo.
;Si los parámetros ingresados no son los correctos, se retorna null.
(define (beginDialog chatbot log seed)
  (if (and (chatbot? chatbot) (string? log) (>= seed 0))
  (beginDialog2 chatbot log seed (time seed) (ID seed) (string->number(hora seed)))
  null))

(define (beginDialog2 chatbot log seed time ID hora)
            (string-append log "<" (number->string ID) ">  " "<" time "> "  "*****BeginDialog *****"
                           (cond
                             [(and (>= hora 0) (< hora 12)) (second (car chatbot))]
                             [(and (>= hora 12) (< hora 21)) (third (car chatbot))]
                             [(and (>= hora 21) (<= hora 23)) (fourth (car chatbot))]) "\n"))

;SendMessage
;Entrada: mensaje del usuario, TDA chatbot, el log anterior y la semilla
;Salida: log actualizado con el mensaje ingresado por el usuario y la respuesta del chatbot, además de la hora
;No presenta recursión, genera un string con el log anterior, el tiempo, el mensaje del usuario y la respuesta del usuario
; que se obtiene llamando a la función senMessage2
(define (sendMessage msg chatbot log seed)
  (define (agregarHora msg chatbot log seed time)
   (if (and (string? msg) (chatbot? chatbot) (string? log) (number? seed) (>= seed 0))
    (string-append log "<" time ">" "USUARIO: " msg "\n"
                (sendMessage2 (cadena msg) chatbot log seed) 
                    "\n")
    null
    ))
  
  (agregarHora msg chatbot log seed (time seed)))

;sendMessage2
;Dominio: mensaje del usuario, TDA chatbot, el log anterior y la semilla
;Recorrido: String con la respuesta del chatbot
;Salida: Si el mensaje entregado por el usuario es igual a un id en el TDA, se retorna la respuesta del chatbot (string), de lo contrario
;        retorna una respuesta indicando que no se entendió lo ingresado por el usuario.
;Recursión:  De cola, no presenta estados pendientes, si encuentra un vinculo entre el ID y el mensaje del usuario, retorna la respuesta
; de lo contrario avanza a la siguiente lista y realiza el mismo proceso, si se llega al final de la lista sin encontrar ningun
;vínculo, retorna un mensaje diciendo que no se entiende lo ingresado
(define (sendMessage2 msg chatbot log seed  )
    (if (null? msg)
      (string-append (getRespuesta "nula" chatbot seed) (getRespuesta "palabrasClave" chatbot (palabrasClave chatbot))  )
      (if  (null? (getRespuesta (car msg) chatbot seed))
          (sendMessage2 (cdr msg) chatbot log seed)
          (getRespuesta (car msg) chatbot seed))
        ))

;endDialog
;Dominio: TDA chatbot, log con la información almacenada en la conversación y semilla para saber que respuesta de la lista seleccionada tomar
;Recorrido: Log actualizado con un identificador del chatbot.
;Se llama a la función resumen usuario para generar el log. Resumen usuario en base a una lista de palabras retorna las preferencias del usuario
;(memoria a mediano plazo)
(define (endDialog chatbot log seed)
  (define (endDialog2 chatbot log seed ID time)
     (if (and (chatbot? chatbot) (string? log) (>= seed 0))
         (string-append log "<" (number->string ID) ">  " "<" time "> " "*****endDialog *****" (resumenUsuario log))
         null))
      
  (endDialog2 chatbot log seed (ID seed) (time seed)))
  

;Resumen usuario
;Dominio: log
;Recorrido: string con datos del log para dar un mensaje de despedida al usuario
;No existe recursión, se genera de esta manera la memoria a mediano plazo, la que el chatbot toma los datos
;del usuario para generar el mensaje
(define (resumenUsuario log)
     (string-append " HOGWARTSBOT: " (cond
        [(string-contains?  log "hipogrifo")  "Espero puedas montar pronto un hipogrifo para ver todo el castillo!, Si necesitas ayuda no dudes en llamarme"]
        [(string-contains?  log "dementor")  "Si sales de Hogwarts ten cuidado con los dementores, no quieres encontrarte con ellos, Si necesitas ayuda no dudes en llamarme"]
        [(string-contains?  log "Harry")  "Espero que puedas ver a Harry y Ron en el castillo, son muy simpáticos y buenos magos, Si necesitas ayuda no dudes en llamarme"]
        [(string-contains?  log "casas")  "Ojalá que ganes muchos puntos para tu casa, Si necesitas ayuda no dudes en llamarme "]
        [else "Espero que tengas una fantástica estadía en el castillo, la comida es deliciosa! Si necesitas ayuda no dudes en llamarme"]
        )))

;test
;Dominio: usuario (que es una lista de string para generar la conversación), TDA chatbot, el log y la semilla ingresada por el usuario
;Recorrido: Llamado a la función test2, que entrega un Log actualizado con la conversación completa y log anterior.
;Recursión : natural, ya que se debe esperar hasta el último llamado de la función para retornar el log
;actualizado (en la función test2)
(define (test user chatbot log seed)
   (if (and (chatbot? chatbot) (string? log) (>= seed 0))
       (string-append log (test2 user chatbot (beginDialog chatbot log seed) seed))
       null
       ))
(define (test2 user chatbot log seed)
  (if (null? user)
     (endDialog chatbot log seed)
     (test2 (cdr user) chatbot (sendMessage (car user) chatbot log seed) seed)
  ))


;rate
;Dominio: TDA chatbot, metrica, calificación usuario, log.
;Recorrido: TDA chatbot modificado (lista de listas modificada)
;No hay recursión, se llama a la función para el cálculo de la métrica y a memoria para guardar los datos en el chatbot
(define (rate chatbot score metrica log)
  (if (and (chatbot? chatbot) (number? score) (string? log))
  (memoria  (string-append "||ID: "
             (number->string (ID 3)) "||Calificación usuario " (number->string score) "|| Calificación programa: " (number->string (metrica log)) "||") "rate" chatbot log)
  null)
  )
  
;metrica
;Dominio: log de la conversación (string)
;Recorrido: Función metrica2, la cual entrega el valor calculado
;Si log es null, se devuelve -1, de lo contrario un valor entre 1 y 5.
(define (metrica log) 
  (if (null? (cadena log))
  -1
  (metrica2 (cadena log) 0 0)))

;metrica2
;Dominio: log, valor y valor 2 son enteros en donde se guardarán el número de veces en que el chatbot no entiende el mensaje del usuario
;y el número de preguntas que realiza el usuario.
;Recorrido: calificación entre 1 y 5 que se le pone al chatbot
;Recursión; Natural, ya que se debe recorrer todo el log para calcular el puntaje, el cual corresponde a una relación entre el número de preguntas realizadas
;por el usuario y las veces que chatbot no entendió lo preguntado.
(define (metrica2 log valor valor2)
  (if (null? log)
      (cond
        [(< (/ valor valor2) 0.2) (>= (/ valor valor2)0) 5]
        [(< (/ valor valor2) 0.4) (>= (/ valor valor2) 0.2) 4]
        [(< (/ valor valor2) 0.6) (>= (/ valor valor2) 0.4) 3]
        [(< (/ valor valor2) 0.8) (>= (/ valor valor2) 0.6) 2]
        [(< (/ valor valor2) 1) (>= (/ valor valor2) 1) 1])       
      (cond
        [(string-ci=? (car log) "muggle,") (metrica2 (cdr log) (+ valor 1) valor2)]
        [(string-ci=? (car log) "HOGWARTSBOT:") (metrica2 (cdr log) valor (+ valor2 1))]
        [else (metrica2 (cdr log) valor valor2)]
        )))
          


;Memoria
;Dominio: palabras a agregar (string), identificador de la lista, y TDA chatbot
;Recorrido: Tda chatbot modificado
;Se recorre la lista, se detiene cuando se encuentra el identificador y se llama a la función memoria 2, en donde se recorre la lista interna
;cuando se llega al final se agrega el resultado de rate.
;Recursión: cola.??
(define (memoria palabra  identificador chatbot log)
      (if (string-ci=? identificador  (caar chatbot))
          (list (memoria2 palabra (car chatbot)))
          (if (string-ci=? (caar chatbot) "palabrasClave")
              (cons (car chatbot) (cons (modificarPalabras (car chatbot) (cadena log)) (memoria palabra identificador (cddr chatbot) log)))
              (cons (car chatbot) (memoria palabra identificador (cdr chatbot) log))
      )))
(define (memoria2 palabra chatbot) ;memoria2 entrega el resultado de rate para ser agregado a chatbot
 (if (null? chatbot)
    (cons palabra null)
      (cons  (car chatbot)  (memoria2 palabra (cdr chatbot)))))
;modificarPalabras
;Dominio: chatbot y string log
;Recorrido: llamada a la función contador, la cual entrega la frecuencia de la palabra.
;Recursión: natural
(define (modificarPalabras chatbot log )
  (if (null? chatbot)
      null
     (cons (contador (car chatbot)   log 0) (modificarPalabras (cdr chatbot) log))))
;contador
;Dominio: string palabra, string log entero acumulador
;Recorrido: entero con la frecuencia con que aparece la palabra en el log (palabra que se encuentra en una lista predefinida de las más buscadas)
;Recursión: Natural.
(define (contador palabra log acumulador)
  (if (null? log)
      acumulador
      (if (string-ci=? palabra (car log))
          (contador palabra (cdr log) (+ acumulador 1))
          (contador palabra (cdr log) acumulador))
  ))       





;------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
;OTRAS FUNCIONES

;Semilla
;Dominio: seed ingresado por el usuario (número positivo)
;Recorrido: Valor con la posición de la palabra que se entregará al usuario (Entero que nunca será mayor al largo de la lista, ni tampoco 0 porque esa posición corresponde al identificador)
;No presenta recursión
(define (semilla seed)
  (+ (modulo
   (+ seed 2) 2) 1))

;ID
;Dominio: seed ingresado por el usuario (número positivo)
;Recorrido: Número "aleatorio" que representa el identificador de la conversación
;No presenta recursión
(define (ID seed)
 (modulo (* 242340 seed)6783 ))

;Cadena
;Dominio: cadena de string que ingresa el usuario
;Recorrido: lista con los string separados según los espacios
;No presenta recursión
(define (cadena InUsuario)
  (string-split InUsuario))

;Time
;Dominio: seed ingresado por el usuario
;Recorrido: string con la hora cuando se llama a la función
;No presenta recursión
(define (time x)
  (let ((date (seconds->date (current-seconds))))
               (string-append (number->string (date-hour date))
                 ":"
                 (number->string (date-minute date))))
)
;Hora
(define (hora x)
  (let ((date (seconds->date (current-seconds))))
               (string-append(number->string (date-hour date)))))
;DisplayLog
;Dominio: lista de listas chatbot y string log
;Recorrido: log
;No presenta recursión, se encarga de mostrar log por pantalla de forma más estética.
( define (displayLog chatbot log)
   (if (and (string? log) (chatbot? chatbot))
   (display log)
   null))
