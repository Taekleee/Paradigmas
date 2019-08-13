:-[chatbot].
%TDA: 
%La REPRESENTACIÓN de chatbot se encuentra en el archivo "chatbot.pl" y corresponde a un hecho que contiene la listas con todas las posibles 
%respuestas, además de otro hecho con una lista con todas las palabras claves, la manera de relacionarlas es multiplicando la posición de la palabra
%clave por tres para obtener la respuesta, a la cual luego mediante una función seudo random se le suma un valor entre 0 y 2, para así variar la respuesta 
%CONSTRUCTOR: Debido a que no es posible modificar los hechos y predicados a lo largo del desarrollo del programa, los constructores corresponden a los hechos
%             de la representación de chatbot (chatbot, idChatbot y usuario).
%FUNCIÓN DE PERTENENCIA: Cada predicado corresponde a una función de pertenencia, debido a que si el valor que se quiere consultar no corresponde al esperado
%                        el predicado retornará falso.
%SELECTORES : En este caso, getRespuesta corresponde a un selector, ya que retorna un string con la respuesta del chatbot a lo consultado por el usuario.
%MODIFICADORES: Son utilizados para modificar la salida (output), en donde se agregarn elementos al string de entrada, pero dentro de la estructura chatbot no es posible 
%               realizar cambios (en chatbot, idChatbot y usuarios).
% OTRAS FUNCIONES: el predicado time entrega un string con la hora en que se realiza la consulta, semilla y numero chatbot retornan valores seudo-aleatorios.

%*****************************DOMINIOS*****************************
%Respuesta = String.
%Retorno= atomo (número).
%Chatbot = Lista de listas.
%Seed = entero positivo entre 0 y 2  (Número de posibles respuestas).
%InputLog = string.
%OutputLog = string.
%idChatbot = Lista de string.
%MsgL= Lista con la entrada del usuario.
%Msg = String con la entrada del usuario.
%UserL= lista con frases predefinidas para test.
%User= nombre del usuario que se seleccionará.
%StrRep = String.
%N = atomo (entero).

%***************************PREDICADOS***************************
%semilla(Seed,Retorno,N).
%getRespuesta(Chatbot,Seed,Respuesta).
%beginDialog(Chatbot,InputLog,Seed,Output).
%numeroChatbot(Seed,Retorno).
%idRespuesta(idChatbot,0,InputLog).
%getRespuesta2(MsgL,idChatbot,OutputLog,Seed,Chatbot).
%sendMessage(Msg,Chatbot,InputLog,Seed,OutputLog).
%endDialog(Chatbot,InputLog,Seed,OutputLog).
%test2(UserL,Chatbot,InputLog,Seed,OutputLog).
%test(User,Chatbot,InputLog,Seed,OutputLog).
%logToStr(InputLog, StrRep).
%tiempo(Retorno).


%*************************METAS******************************++***
%Primarias: 
%beginDialog(Chatbot,InputLog,Seed,Output): Inicia una conversación con el usuario, donde es el 
%											chatbot quien parte el diálogo.
%sendMessage(Msg,Chatbot,InputLog,Seed,OutputLog): Recibe un mensaje de parte del usuario (String) y retorna una
%												   respuesta apropiada.
%endDialog(Chatbot,InputLog,Seed,OutputLog): Envía un mensaje final al usuario.
%test(User,Chatbot,InputLog,Seed,OutputLog): Genera automáticamente una conversación entre un usuario (User) y el
%											Chatbot.
%logToStr(InputLog, StrRep): Recibe como entrada el log y lo devuelve de una manera entendible.



%***************************HECHOS********************************
%getRespuesta([L|_],0,L).
%El resto se encuentra en "chatbot.pl".


%********************CLAUSULAS DE HORN***+************************


%idRespuesta: Aridad 3,
%Compara el string que ingresa el usuario con los elementos de la lista de posibles respuestas, si coinciden,
%entrega el índice de la posición en que se encuentra la respuesta.
idRespuesta([L|_],0,Input):- L == Input.
idRespuesta([_|C],N,Input):- idRespuesta(C,Nsig,Input),
                             N is Nsig+1.

%getRespuesta: Aridad 3.
%Según la posición en que se encuentra la respuesta (número), se obtiene del TDA chatbot una frase, para ser mostrada
%al usuario. Toma a la cola como nueva lista y resta 1 al número de la posición hasta llegar a 0, para así entregar la cabeza de la cola.
getRespuesta([L|_],0,L).
getRespuesta([_|C],N,Respuesta):- Nsig is N-1,
								  getRespuesta(C,Nsig,Respuesta).
								  

%getRespuesta2: Aridad 5.
% Toma como entrada el string ingresado por el usuario, el cual lo separa en una lista, para poder comparar cada palabra con la lista de 
%palabras claves, si encuentra relación, retorna la posición de la palabra, para luego llamar a getRespuesta y retornar la frase.
getRespuesta2([],_,OutputLog,Seed,Chatbot):-  semilla(Seed,Retorno,20),
									          getRespuesta(Chatbot,Retorno,OutputLog).
getRespuesta2([L|_],ID,OutputLog,Seed,Chatbot):- idRespuesta(ID,N,L), semilla(Seed,Retorno,N),
									             getRespuesta(Chatbot,Retorno,Respuesta),
									             atom_concat("",Respuesta,OutputLog).
getRespuesta2([_|C],ID,OutputLog,Seed,Chatbot):-  getRespuesta2(C,ID,OutputLog,Seed,Chatbot).


%beginDialog: Aridad 4.
%Permite iniciar el dialogo con el Chatbot, consulta a getRespuesta para encontrar un mensaje de bienvenida, genera el número identificador
%de la conversación, y agrega la fecha. 
beginDialog(Chatbot,InputLog,Seed,Output):- 	idChatbot(ID), chatbot(Chatbot,Personalidad),
												idRespuesta(ID,N,"Saludo"),
												semilla(Seed,Retorno,N),
												getRespuesta(Personalidad,Retorno,Respuesta), 
												tiempo(Fecha), numeroChatbot(Seed,Identificador),
												atomic_list_concat(["*********BEGIN DIALOG********** <<",Identificador,">> \n",InputLog,"",Fecha,Respuesta]," ",Output).

%sendMessage: Aridad 5.
%Genera una lista con el string ingresado por el usuario, que luego es ingresada a getRespuesta2 para encontrar una frase de respuesta,
%agrega al OutputLog el input anterior, la fecha, el mensaje entregado por el usuario y la respuesta del chatbot.
sendMessage(Msg,Chatbot,InputLog,Seed,OutputLog):-  split_string(Msg," ",",",Frases),
													idChatbot(ID), chatbot(Chatbot,Personalidad),
													getRespuesta2(Frases,ID,Salida,Seed,Personalidad),
													tiempo(Fecha),
													atomic_list_concat([InputLog,"\n",Fecha,"USUARIO: ",Msg,"\n",Fecha,Salida,"\n"]," ",OutputLog).

%endDialog: Aridad 4.
%La consulta genera un mensaje de despedida para el usuario, el cual contiene el inputLog, un identificador de conversación
%La fecha y el mensaje.
endDialog(Chatbot,InputLog,Seed,OutputLog):- 	idChatbot(ID), chatbot(Chatbot,Personalidad),
												idRespuesta(ID,N,"fin"),
												semilla(Seed,Retorno,N), tiempo(Fecha),
												numeroChatbot(Seed,Identificador),
												getRespuesta(Personalidad,Retorno,Respuesta),
												atomic_list_concat([InputLog,"**********END DIALOG********** <<",Identificador,">> \n",Fecha,Respuesta]," ",OutputLog).


test2([],Chatbot,InputLog,Seed,OutputLog):- endDialog(Chatbot,InputLog,Seed,OutputLog).
test2([X|Xs],Chatbot,InputLog,Seed,OutputLog):- sendMessage(X,Chatbot,InputLog,Seed,Output),
										        test2(Xs,Chatbot,Output,Seed,OutputLog).

%test: Aridad 5.
%La consulta entrega una conversación entre el User (lista de frases) y el chatbot, contando con las funciones beginDialog, endDialog y sendMessage
test(User,Chatbot,InputLog,Seed,OutputLog):- usuarios(User,Usuario), 
											 beginDialog(Chatbot,InputLog,Seed,Out1),
											 test2(Usuario,Chatbot,Out1,Seed,OutputLog).

%getPossibles: Aridad 4.
%Si se llega al final de la lista de entrada del usuario, se entregan las posibles respuestas nulas, de lo contrario, se consulta cual es la posición en
%que se encuentra y se retornar las sub siguientes dos respuestas en un string.
getPossibles([],_,Responses,Chatbot):- getRespuesta(Chatbot,60,Response1), getRespuesta(Chatbot,61,Response2), getRespuesta(Chatbot,62,Response3),
											atomic_list_concat([Response1,"/",Response2,"/",Response3],Responses). 
getPossibles([L|_],ID,Responses,Chatbot):- idRespuesta(ID,N,L), 
									       getRespuesta(Chatbot,N*3,Response1), getRespuesta(Chatbot,N*3+1,Response2), getRespuesta(Chatbot,N*3+2,Response3),
										  atomic_list_concat([Response1,"/",Response2,"/",Response3],Responses). 
getPossibles([_|C],ID,Responses,Chatbot):-  getPossibles(C,ID,Responses,Chatbot).

%possiblesResponses: Aridad 4.
%La consulta entrega una lista con las posibles respuestas a la frase ingresada por el usuario.											 
possiblesResponses(Message,Chatbot,_,Responses):-chatbot(Chatbot,Personalidad), idChatbot(ID),
														split_string(Message," ",",",Msg),
														getPossibles(Msg,ID,Response,Personalidad),
														split_string(Response,"/",",",Responses).
														
%***************************OTRAS FUNCIONES*****************************************************											        

%logToStr: Aridad 2.
%La consulta muestra la conversación de manera entendible para el usuario, en la variable StrRep.
logToStr(Log, StrRep):-  atom_concat(Log,"",StrRep),
						write(StrRep).
%tiempo: Aridad 1.
%La consulta genera un string con la hora, minuto, día, mes y año en que se está realizando la conversación.
tiempo(Fecha) :- 
   get_time(Time), 
   convert_time(Time, Year, Month, Day, Hour, Minute, _, _),
   atomic_list_concat(["<<[",Hour,":",Minute,"]"," ",Day,"/",Month,"/",Year,">>"],"",Fecha).

 %Semilla: Aridad 3. 
%ID = Indice en donde se encuentra la palabra buscada.
%Semilla toma el indice en donde se encuentra la palabra que se quiere extraer y genera un numero "random" que puede variar entre
%3 dígitos (3 posibles respuestas).
semilla(Seed,Retorno,ID):- Retorno is (((Seed * 2) mod 3)+ID*3).

%Número Chatbot: Aridad 2.
%Genera un número identificador para cada conversación.
numeroChatbot(Seed,Retorno):-  Retorno is (242340* Seed) mod 6783. 
