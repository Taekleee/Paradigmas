#lang racket
;Es importante que los tipos de datos de entrada sean también los de salida

;representar la función (5 + 4+ (2-(3-(6+ 4/5))))/(3(6-2)(2-7))

(define (funcion) (/ (+ 5 (+ 4 (- 2 (- 3 (+ 6 (/ 4 5)))))) (* 3 (* (- 2 7) (- 6 2)))))

;Defina una función que tome como argumento tres números y retorne la
;suma de los cuadrados de los dos números mayores

(define (funcion2 a b c)
  (cond [(and (> a b) (> b c)) (+ (* a a) (* b b))]
        [(and (> b a) (> c a)) (+ (* b b) (* c c))]
        [else (+ (* a a) (* c c))]))

;Implemente la función my-expt que recibe dos enteros como argumento, el segundo siempre no negativo, y calcula el primero a la potencia del segundo.1

(define (funcion3 a b) (expt a b) )

;Implemente el algoritmo de Euclides para obtener el máximo común divi- sor entre dos enteros positivos
(define (funcion4 a b) (gcd a b) )


;función factorial
(define (factorial x)
  (if (= x 0) 1
      (* x (factorial (- x 1)))))
;función sumatoria

(define (sumatoria y)
  (if (= y 1) 1
      (+ y (sumatoria(- y 1)))))

;fibonacci

(define (fibonacci z)
  (if (<= z 2) 1
      (+ (fibonacci(- z 1)) (fibonacci(- z 2)))))
; Sumatoria en una lista

(define (sumatoria-lista l)
  (if (empty? l) 0
      (+ (car l) (sumatoria-lista(cdr l)))))


;Función que entregue #t si se cumple la secuencia de dominó y #f si no

(define (cortar lista) (cdr lista))

(define (domino lista)
  (cond [(empty? lista) #t]
        [(= (first lista) (second lista)) (domino (cddr lista))]
        [else #f] )
  )

; la composición de funciones se realiza mediante compose, en donde ambas reciben el mismo parametro
(define (f x) (+ 5 x))
(define (g x ) (* 2 x))

(define h (compose f g))

; mycom usa las funciones anonimas (no tienen nombres) para emular a compose 
(define (mycom f g)
  (lambda (x)
    (f (g x))))

;currificación, transforma una función de varios parámetros en n funciones de 1 parámetro
(define (suma a b c d ) (+ a b c d))
;con currificación, queda solo la función mas anidada con varios parámetros

(define (suma2 a)
  (lambda (b)
    (lambda (c)
      (lambda (d) (+ a b c d)))))
;evaluación perezosa (usa delay y force)



;Display sirve para mostrar una frase por pantalla 
(display "hola")

;helper o encapsulación con recursión de cola

(define (pow x y)
  (define (powIn x y z)
    (if (= y 0)
        z
        (powIn x (- y 1) (* z x))))
  (powIn x y 1)
  )

;Triángulo de Pascal, con recursión arborea, dominio y recorrido reales positivos

(define (pascal n k)
  (if (or (= n k) (= k 0))
      1
      (+ (pascal (- n 1) (- k 1)) (pascal (- n 1) k)))
  )