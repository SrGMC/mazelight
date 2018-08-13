# Documentation
## Table of contents
- [Position](#position)
- [Edge](#edge)
- [Graph](#graph)
- [Mazelight](#mazelight)

## Position
- [public Position(x,y)](#position1)
- [public getX()](#position2)
- [public getY()](#position3)
- [public equals(pos)](#position4)

<a name="position1"></a>
#### public Position(x, y)
Constructor.  
*Parameters*:
- `int x`: X coordinate.  
- `int y`: Y coordinate.

*Returns*: Nothing.  

<a name="position2"></a>
#### public getX()
Gets the X coordinate of the Position object.  
*Parameters*: None.  
*Returns*: `int`: X coordinate.  

<a name="position3"></a>
#### public getY()
Gets the Y coordinate of the Position object.  
*Parameters*: None.  
*Returns*: `int`: Y coordinate.  

<a name="position4"></a>
#### public equals(pos)
Compares the current Position object with another Position object.  
*Parameters*:
- `Position pos`: Position object to compare.

*Returns*: `boolean`: true or false.  

## Edge
- [public Edge(start, end, weight)](#edge1)
- [public getStart()](#edge2)
- [public getEnd()](#edge3)
- [public getWeight()](#edge4)
- [public setWeight(weight)](#edge5)

<a name="edge1"></a>
#### public Edge(start, end, weight)
Constructor.  
*Parameters*:
- `Position start`: The start Position object.  
- `Position end`: The end Position object.  
- `int weight`: The weight of the edge.

*Returns*: Nothing.  
*Throws*:
- [Error 001x001](#)

<a name="edge2"></a>
#### public getStart()
Gets the start Position object of the Edge.  
*Parameters*: None.  
*Returns*: `Position`: start Position object.  

<a name="edge3"></a>
#### public getEnd()
Gets the end Position object of the Edge.  
*Parameters*: None.  
*Returns*: `Position`: end Position object.  

<a name="edge4"></a>
#### public getWeight()
Gets the weight of the Edge object.  
*Parameters*: None.  
*Returns*: `int`: The weight of the edge.  

<a name="edge5"></a>
#### public setWeight(weight)
Sets the weight of the Edge object.  
*Parameters*:
- `int weight`: The weight of the edge.

*Returns*: Nothing.  

## Graph
- [public Graph()](#graph1)
- [public size()](#graph2)
- [public addEdge(start, end)](#graph3)
- [public removeEdge(start, end)](#graph4)
- [public isEdge(start, end)](#graph5)
- [public getWeight(start, end)](#graph6)
- [public getAdjacents(pos)](#graph7)

<a name="graph1"></a>
#### public Graph()
Constructor.  
*Parameters*: None.  
*Returns*: Nothing.  

<a name="graph2"></a>
#### public size()
Returns the total number of edges.  
*Parameters*: None.  
*Returns*: `int`: Total number of edges.  

<a name="graph3"></a>
#### public addEdge(start, end)
Creates a new edge in the Graph.  
*Parameters*:
- `Position start`: The start Position object of the edge.  
- `Position end`: The end Position object of the edge.  

*Returns*: Nothing.  
*Throws*:
- [Error 001x001](#)

<a name="graph4"></a>
#### public addEdge(start, end)
Removes an already existing edge in the Graph.  
*Parameters*:
- `Position start`: The start Position object of the edge.  
- `Position end`: The end Position object of the edge.  

*Returns*: Nothing

<a name="graph5"></a>
#### public isEdge(start, end)
Checks if the edge exists.  
*Parameters*:
- `Position start`: The start Position object of the edge.  
- `Position end`: The end Position object of the edge.  

*Returns*: `boolean`: true or false.

<a name="graph7"></a>
#### public getWeight(start, end)
Returns the weight of an edge in the Graph.  
*Parameters*:
- `Position start`: The start Position object of the edge.  
- `Position end`: The end Position object of the edge.  

*Returns*: `int`: Weight of the edge (-1 if does not exist)

#### public getAdjacents(pos)
Returns a list with the adjacents to the pos object.  
*Parameters*:
- `Position pos`: The Position object from where to get the adjacents.  

*Returns*: `LinkedList<Position>`: Adjacent list.

## Mazelight
- [private isNode(list, pos)](#mazelight0)
- [private imageToBuffer(path)](#mazelight1)
- [private bufferToGraph(graphics)](#mazelight2)

<a name="mazelight0"></a>
#### private isNode(list, pos)
Checks if the Position pos object is a node in the LinkedList list object.  
*Parameters*:
- `LinkedList<Position> list`: The LinkedList list object.
- `Position pos`: The Position pos object to check.  

*Returns*: `boolean`: true or false.  

<a name="mazelight1"></a>
#### private imageToBuffer(path)
Converts an image to a BufferedImage object.
*Parameters*:
- `String path`: The path of the image.  

*Returns*: `BufferedImage`: Black and white BufferedImage object.  
*Throws*:
- `IOException`  

<a name="mazelight2"></a>
#### private bufferToGraph(buffer)
Converts an BufferedImage object to a Graph object. Every node in the Graph is identified by its pixel position in the image (and stored into a Position object). A node is an intersection between two paths in a maze. The weight of the edge is the number of pixels between two nodes.  
*Parameters*:
- `BufferedImage buffer`: The BufferedImage object.  

*Returns*: `Graph`: The Graph object that contains the maze.  