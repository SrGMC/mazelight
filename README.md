
![Mazelight logo](https://github.com/SrGMC/mazelight/raw/master/logo.png)  
Language: **[en](https://github.com/SrGMC/mazelight/blob/master/README.md)** [es](https://github.com/SrGMC/mazelight/blob/master/README_es.md)

# Mazelight
[WIP] Java-based Open Source CLI tool designed to solve mazes.

## Table of contents
- [About Mazelight](#about)
- [Get started](#getstarted)
  - [Prerequisites](#prerequisites)
  - [Instructions](#instructions)
  - [Program arguments](#programarguments) 
- [TODO](#todo)
- [How it works](#howitworks)
- [Documentation](https://github.com/SrGMC/mazelight/blob/master/DOCUMENTATION.md)
- [Contribute](#contribute)
- [License](https://github.com/SrGMC/mazelight/blob/master/LICENSE)

<a name="about"></a>
## About Mazelight
Mazelight is a Java-based Open Source CLI tool that can solve mazes. It uses a custom made algorithm to find nodes and paths in the maze that are later added to a graph that can be used to solve the maze with shortest path finding algorithm like Dijkstra's algorithm.

<a name="getstarted"></a>
## Get started
<a name="prerequisites"></a>
### Prerequisites
Mazelight currently supports images up to 512x512px in black and white (although the solved image contains color for the solution).  
Mazes must be surrounded by a wall and only contain one start point and one exit point.  
Maze walls and paths must have 1px of width.  
Example mazes are provided in the `mazes/` folder.

<a name="instructions"></a>
### Instructions
To get started first make sure you have at least [Java 1.8](https://java.com/download) and follow the next instructions:
1. Download the ~~[latest release](https://github.com/SrGMC/mazelight/releases)~~ (not available as it is a working in progress project).
2. Open
  - Linux: Terminal
  - macOS: Terminal (Launchpad > Utilities > Terminal)
  - Windows: cmd (Win + R > cmd)
3. Navigate to the path where the dowloaded release is contained.
4. Run the program with:  
   `java Mazelight.jar path/to/image -o output [algorithm] [-N] [-rN R][-gN G][-bN B] [-rP R][-gP G][-bP B] [-P]`

<a name="programarguments"></a>
### Program arguments
*Required*
- `Mazelight.jar`: The program itself.
- `path/to/image`: The path to the maze image in black and white (as .png, .jpg or .bmp).
- `-o output`: The output image path.

*Optional*
- `algorithm`: The algorith name for solving the maze. Currently implemented algorithms are:
  - `dijkstra`: Dijkstra's algorithm. (Default)
- `-N`: Shows nodes in the output image.
- `-rN R -gN G -bN B`: The color used to show the nodes in the output image (Default: -rN 0 -gN 255 -bN 0).
- `-rP R -gP G -bP B`: The color used for the solved path in the output image (Default: -rP 255 -gP 0 -bP 0).
- `-P`: Prints in the command line the shortest path found.

<a name="todo"></a>
## TODO
Ordered by priority.

 - [ ] Implement Dijkstra's algorithm.
 - [ ] Optimize Graph.java:
    There's no need to add the edge inversed again.  
 - [ ] Implement command line arguments.
 - [ ] Expand documentation.
 - [x] Finish README.md.
 - [ ] Create API.

<a name="howitworks"></a>
## How it works
Mazelight works by analyzing an image of a maze and then converting it into a graph, so it can run algorithms to find the best solution to the problem.
More information can be found ~~[here](https://github.com/SrGMC/mazelight/blob/master/HOWITWORKS.md)~~ (Not available, yet).

## [Documentation](https://github.com/SrGMC/mazelight/blob/master/DOCUMENTATION.md)

<a name="contribute"></a>
## Contribute
Feel free to create issues and pull requests to solve bugs or create new features. Please check the license file in the link below  

## [License](https://github.com/SrGMC/mazelight/blob/master/LICENSE)
