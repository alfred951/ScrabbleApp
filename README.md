# Scrabble App

Aplicacion para Viajala.com

Por: Luis Alfredo Gallego Montoya

# Requerimentos

* JDK 1.8+
* Maven 3.0+ (Puede usarse el de Eclipse)
* Eclipse IDE o equivalente

# Especificaciones

La aplicación fue construida sobre el Framework Spring con el módulo de Spring Boot como base, esto implica que la aplicación hace uso de un servidor Apache Tomcat Embebido pre configuradon el IDE utilizado fue Spring Tool Suite, el cual es una simple distribucion de Eclipse con herramientas similares a las de la version de Eclipse para J2EE.

# Documentacion REST API

Obtener una palabra construida con las letras dadas:

* http://localhost:8080/rest/scrabble?list=a,b,c

Obtener todas las palabras posibles construidas con las letras dadas:

* http://localhost:8080/rest/scrabble/all?list=a,b,c

# Funcionamiento

La aplicacion funciona de la siguiente manera:

* Una vez enviado el request el cual es recibido por la clase ScrabbleController, este busca el parametro "list" y pasa su valor al constructor de la clase Scrabble.

* La clase Scrabble recibe la entrada como un String y lo pasa por un validador el cual construye un nuevo String conteniendo solo las letras e ignorando cualquier caracter no alfabetico como las ','.

* Una vez construida la cadena de letras esta es pasada a el metodo permute() el cual encuentra todas las posibles "palabras" con la permutacion de las letras y las almacena en una lista de strings llamada possibleWords.

* Para la validacion de las palabras se hace uso del API del diccionario de Oxford:
https://developer.oxforddictionaries.com/documentation

* Se itera sobre la lista de posibles palabras enviandolas una a una por medio de http requests a el API de el diccionario para identificar aquellas que efectivamente son palabras reales

* Se retorna la primera palabra real encontrada o en el caso de buscar todas se espera añaden a una lista que es mostrada al final.

# Notas Importantes

* En todos los diccionarios oficiales se incluyen "palabras" que no serian admitidas en Scrabble, por ejemplo cada letra del abecedario esta incluida, por esta razon el metodo que retorna una sola palabra suele retornar una sola letra como respuesa.

* La conexion con el API del diccionario de Oxford es lenta (alrededor de 1s por peticion) y por lo tanto el servicio /rest/scrabble/all puede tomar un largo tiempo en ejecucion.

* La version gratuita del API solo permite 3000 peticiones al mes, cuando se usa el metodo que obtiene todas las palabras posibles se hacen el siguiente numero de peticiones segun el numero de letras en la entrada:

Letras | Peticiones
--- | ---
1 | 1 
2 | 4
3 | 15
4 | 64
5 | 325
6 | 1959

* Por los dos puntos anteriores no es recomendable llamar el servicio scrabble/all con mas de 4 letras como entrada.

* El algoritmo no necesariamente recibe letras separadas por comas, para una mayor resistencia a errores, este ignora todos los caracteres no alfabeticos que encuentre en el parametro, por esta razon si se le pasa "a,b,c" es lo mismo que "abc" e incluso lo mismo que "a4;b&*c".

# Optimizacion

El algoritmo fue contruido de esta manera teniendo en cuenta que segun el problema solo era necesario retornar una palabra real conformada por las letras dadas, sin embargo, despues de ver el funcionamiento y decidir que era mejor agregar el metodo que retorna todas la palabras posibles la llamada de un API externo no es viable, en cambia seria preferible hacer la propia implementacion de un verificador de palabras, por ejemplo descargar un diccionario (mirar assets/dictionary.txt) y crear un arbol de decision que tome letra a letra y determine si es posible que exista una palabra que siga ese camino.