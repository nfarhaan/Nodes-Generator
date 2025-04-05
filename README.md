# Shortest Path Finder

**Authors:**

* Geerishraj Bheekhum
* Sarwin Rajiah
* Muhammad Farhaan Nazirkhan
* Levyn Kwong Hin Sang
* Feiz Ahmad Shakeel Roojee

This program is designed to find the shortest path between a set of nodes on a graph. It consists of two main parts:

* A graph to display the nodes and their connections. [cite: 8, 9]
   
* A side panel that allows users to: [cite: 10, 11, 12, 13, 14]
   
    1.  Specify the number of nodes to generate. [cite: 10]
       
    2.  Randomize the coordinates of the nodes. [cite: 10]
       
    3.  Start the computation for the shortest path. [cite: 11]
       
    4.  Toggle the display of node details. [cite: 11]
       
    5.  Toggle the display of all possible connections. [cite: 12]
       
    6.  Toggle the display of the shortest path. [cite: 13]
       
    7.  View the length of the shortest path. [cite: 13]
       
    8.  View the time taken to compute the shortest path. [cite: 14]
       
    9.  View the nodes that make up the shortest path. [cite: 14]

## Algorithm

The algorithm works by generating all possible paths and calculating their lengths to find the shortest one.

### Base Algorithm

* Generates all possible paths by permuting the nodes in lexicographic order. [cite: 15, 16]
   
* Calculates the length of each path by summing the Euclidean distances between nodes. [cite: 17]
   
* Compares each path length to find and save the shortest one. [cite: 17, 18, 19]

### Optimization 1

* Improves efficiency by checking the path length during calculation. [cite: 19, 20]
   
* If the current path length exceeds the shortest length found so far, it stops calculating the rest of the path. [cite: 19, 20]

### Optimization 2

* Further optimizes the algorithm by identifying and skipping unnecessary path calculations based on lexicographic order patterns. [cite: 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35]
   
* Divides the nodes into 'Used', 'Remaining', and 'tolterate' groups to manage path generation. [cite: 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35]
   
* Skips multiple paths that are guaranteed to be longer than the current shortest path. [cite: 27, 28, 29, 30, 31, 32, 33, 34, 35]
   
* Uses an array of numbers to represent the order of nodes, simplifying the calculations. [cite: 34, 35]

## Performance

The following table compares the execution time of the base algorithm and the optimized algorithm: [cite: 38, 39, 40]

| Number of Nodes   | Base Algorithm    | Optimized Algorithm |
| :---------------- | :---------------- | :---------------- |
| 2 - 8             | ≈ 0 seconds       | ≈ 0 seconds       |
| 9                 | ≈ 0.007 seconds   | ≈ 0.006 seconds   |
| 10                | ≈ 0.063 seconds   | ≈ 0.041 seconds   |
| 11                | ≈ 0.699 seconds   | ≈ 0.165 seconds   |
| 12                | ≈ 8.270 seconds   | ≈ 0.637 seconds   |
| 13                | ≈ 110.424 seconds | ≈ 3.369 seconds   |
| 14                | > 20 minutes      | ≈ 27.489 seconds  |
| 15                | > 20 minutes      | ≈ 83.237 seconds  |

## Code

The code is organized into the following Java files: [cite: 44]

* `Main.java` [cite: 44]
   
* `Node.java` [cite: 44]
   
* `GridPanel.java` [cite: 44]
   
* `Graph.java` [cite: 44]
   
* `DisplayPanel.java` [cite: 44]
   
* `Algorithm.java` [cite: 44]

## References

1.  How would you explain an algorithm that generates permutations using lexicographic ordering? - Quora [cite: 179, 180]
   
2.  Print all permutations in sorted (lexicographic) order - GeeksforGeeks [cite: 179, 180]
