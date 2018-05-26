# Uninformed-Search
Shortest distance search between two cities

**Purpose**

This program is used to find the shortest distance between two cities when given an input text file which describes the distance between all the cities involved.

**Implementation**

Using a Breadth-First search strategy, the program will create the nodes necessary for the overview of the map, then branch out from the given starting node in the arguments. Due to the Breadth-First search implementation, the program will always return the optimal path between the starting and end node given.

**Compile and Run**

1st argument: The input text file containing the edges of the map.

2nd argument: The starting city node

3rd argument: The ending city node

  *javac find_route.java*
  
  *java find_route input1.txt Bremin Berlin*
  
[find_route.java](https://github.com/Jcisneros92/Uninformed-Search/blob/master/find_route.java)
- Contains main method and perfoms functions.

[input1.txt](https://github.com/Jcisneros92/Uninformed-Search/blob/master/input1.txt)
- Sample map provided with all nodes and edges along with the distance between them.
