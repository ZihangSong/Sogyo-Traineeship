Decision tree (Required)
=
### A decision tree is a graph consisting of nodes and edges, where each node is a question and each edge is a decision. The goal of this exercise is to create a program that has a decision tree as input from a file and executes this for the user.
#
Example file:

**1. N2, Is the edge of the leaf serrated or does it have a saw-tooth pattern?<br>
2. N5, Is the leaf lobed or does it have indents?<br>
3. N6, Ash<br>
4. N7, Alder<br>
5. N8, Maple<br>
6. N9, Birch<br>
7. N0, Oak<br>
8. N3, Is the edge of the leaf doubly serrated instead of singly serrated?<br>
9. NA, Beech<br>
10. N5, NA, No<br>
11. N3, N7, No<br>
12. N1, N6, Yes<br>
13. N1, N2, No<br>
14. N2, N3, Yes<br>
15. N2, N4, No<br>
16. N4, N5, Yes<br>
17. N4, N8, No<br>
18. N1, Is the leaf a compound leaf consisting of multiple smaller leaflets?<br>
19. N4, Is the edge of the leaf smooth?<br>
20. N5, N0, Yes<br>
21. N3, N9, Yes<br>**

_File Format_<br>
The file is a UTF-8 text file that has an edge or a node on each line. A node consists of a name and a question. An edge consists of an origin node, destination node and an answer. Node and edge are mixed throughout the file.

Exercises<br>
Write a program that can read in a file in the above mentioned format. It should also translate the lines to node and edge objects. The nodes should be built in a way that they represent the problem at hand, the decision tree.<br>
Execute the decision tree by asking the questions from the decision tree and to navigate through the tree until an answer is found. The starting question is the node which is not a destination for any edge and an answer is a node where no edges originate from.<br>

_Resources_<br>
In the repository of your language, the example file is included as decision-tree-data.txt.

Learning goals
-
Creating a tree data structure<br>
Seperating different phases in the lifetime of data.<br>
