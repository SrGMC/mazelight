

![Logo Mazelight](https://github.com/SrGMC/mazelight/raw/master/logo.png)
Idioma: [en](https://github.com/SrGMC/mazelight/blob/master/README.md) **[es](https://github.com/SrGMC/mazelight/blob/master/README_es.md)**

# Mazelight
Herramienta CLI de código abierto basada en Java diseñada para resolver laberintos.

## Tabla de contenidos
- [Sobre Mazelight](#about)
- [Comenzar](#comenzar)
  - [Prerrequisitos](#prerrequisitos)
  - [Instrucciones](#instrucciones)
  - [Argumentos del programa](#programarguments) 
- [TODO](#todo)
- [Cómo funciona](#howitworks)
- [Documentación](https://github.com/SrGMC/mazelight/blob/master/DOCUMENTATION.md)
- [Contribuir](#contribuir)
- [Licencia](https://github.com/SrGMC/mazelight/blob/master/LICENSE)

<a name="about"></a>
## Acerca de Mazelight
Mazelight es una herramienta CLI de código abierto basada en Java que puede resolver laberintos. Utiliza un algoritmo creado desde cero para encontrar nodos y caminos en el laberinto que luego son añadidas a un grafo que se puede utilizar para resolver el laberinto con algoritmos de búsqueda de ruta más corta (como el algoritmo de Dijkstra).

<a name="getstarted"></a>
## Comenzar
<a name="prerequisites"></a>
### Prerrequisitos
Mazelight actualmente soporta imágenes de hasta 512x512px en blanco y negro (aunque la imagen resuelta contiene color para la solución).  
Los laberintos deben estar rodeados por un muro y sólo deben contener un punto de inicio y un punto de salida.  
Las paredes del laberinto y los caminos deben tener 1px de ancho.  
Ejemplos de laberintos se proporcionan en la carpeta `mazes/`.

<a name="instructions"></a>
### Instrucciones
Para empezar, primero asegúrese de tener al menos [Java 1.8](https://java.com/download) y siga las siguientes instrucciones:
1. Descargue la ~~[última versión](https://github.com/SrGMC/mazelight/releases)~~ (no disponible todavía).
2. Abre
  - Linux: Terminal
  - macOS: Terminal (Launchpad > Utilidades > Terminal)
  - Ventanas: cmd (Win + R > cmd)
3. Navegue hasta la ruta donde se encuentra la versión descargada.
4. Ejecute el programa con:  
   `java Mazelight.jar ruta/a/imagen -o salida [algoritmo] [-N] [-rN R][-gN G][-bN B] [-rP R][-gP G][-bP B] [-P]`.

<a name="programarguments"></a>
## Argumentos del programa
*Requerido*
- `Mazelight.jar`: El programa en sí.
- `ruta/a/imagen`: La ruta a la imagen del laberinto en blanco y negro (como .png, .jpg o .bmp).
- `-o salida`: La ruta de salida de la imagen.

*Opcional*
- `algoritmo`: El nombre del algoritmo para resolver el laberinto. Los algoritmos actualmente implementados son:
  - `dijkstra`: El algoritmo de Dijkstra. (Por defecto)
- `-N`: Muestra los nodos de la imagen de salida.
- `-rN R -gN G -bN B`: El color utilizado para mostrar los nodos en la imagen de salida (Predeterminado: -rN 0 -gN 255 -bN 0).
- `-rP R -gP G -bP B`: El color utilizado para la ruta resuelta en la imagen de salida (Predeterminado: -rP 255 -gP 0 -bP 0).
- `-P`: Imprime en la línea de comandos la ruta más corta encontrada.

<a name="todo"></a>
## TODO
Ordenados por prioridad.

 - [ ] Implementar el algoritmo de Dijkstra.
 - [ ] Optimizar Graph.java:
    No hay necesidad de añadir el borde invertido de nuevo.  
 - [ ] Implementar argumentos de línea de comandos.
 - [ ] Ampliar la documentación.
 - [x] Finalizar README.md.
 - [ ] Crear API.

<a name="howitworks"></a>
## Cómo funciona
Mazelight trabaja analizando una imagen de un laberinto y luego convirtiéndola en un gráfico, de manera que pueda ejecutar algoritmos para encontrar la mejor solución al problema.
Se puede encontrar más información ~~[aquí](https://github.com/SrGMC/mazelight/blob/master/HOWITWORKS_es.md)~~ (no disponible todavía).

## [Documentation](https://github.com/SrGMC/mazelight/blob/master/DOCUMENTATION.md)

<a name="contribute"></a>
## Contribuye
Siéntase libre de abrir problemas y pull requests para resolver errores o crear nuevas funciones. Por favor, eche un vistazo al archivo de licencia en el enlace que se encuentra más abajo.

## [License](https://github.com/SrGMC/mazelight/blob/master/LICENSE)
