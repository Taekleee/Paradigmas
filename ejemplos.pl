contiene([L|_],L).
contiene([_|C],E):- contiene(C,E).

concatenar([],L,L).
concatenar([L|C],L2,[L|C3]):- concatenar(C,L2,C3).

agregar([],E,[E]).
agregar([L|C],E,[L|C2]):- agregar(C,E,C2).




cc(opel,1600).
cc(fiat,1400).
abs(renault).
air(opel).
air(renault).


cilindradaMedia(Auto):- cc(Auto,1600).
cilindradraBaja(Auto):- cc(Auto,1400).
seguridad(Auto):- abs(Auto), air(Auto).

invertir([],[]).
invertir([L|C],[C2|L]):- invertir(C,C2).




%union(L,[],[]).
%union(L,[L2|C2],[L2|LF]):- contiene(L,L2),
%					  union(L,C2,LF).


factorial(1,1).
factorial(X,Y):- X1 is X-1, factorial(X1,Y1), Y is X*Y1.

intersectar(L,[],[]).
intersectar(L,[L2|C2],[L2|C3]):- contiene(L,L2), intersectar(L,C2,C3). 
intersectar(L,[_|C2],C3]):- intersectar(L,C2,C3).


inters([],Q,[]).
inters([X|R],Q,[X|Z]):-elemento(X,Q),inters(R,Q,Z).
inters([X|R],Q,Z):-inters(R,Q,Z).