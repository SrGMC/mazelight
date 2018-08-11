# Documentation
## Table of contents
- [Position](#position)
- [Edge](#edge)
- [Graph](#graph)

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

*Returns*: Nothing

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

*Returns*: Nothing

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

<a name="graph6"></a>
#### public getWeight(start, end)
Returns the weight of an edge in the Graph.  
*Parameters*:
- `Position start`: The start Position object of the edge.  
- `Position end`: The end Position object of the edge.  

*Returns*: `int`: Weight of the edge (-1 if does not exist)