Coding Challenge 1 ::

The first challenge is contained in the ASCIIArt.java file. In essence, I created a dynamic 2D character array by creating an ArrayList where its elements were ArrayLists of characters. This felt the most intuitive to me because of the add(int index, Object O) method which could add elements at a specified index of the array. I included a dump() function for debugging and testing purposes and believe the functionality should be correct according to the way I interpreted the specification.


Coding Challenge 2 ::

The second challenge is contained in the MaxPath.java file. My implementation of the specifications included a barebones Graph implementation and a strange traversal method in order to calculate the result of the maximizing monetary path. The traversal method I used was a result of confusion over implementing the algorithm I wanted to use, Kruskal's with a reverse order comparator in order to switch from Minimal Spanning Path to Maximal Spanning path. However, I ran into complications with the labeled vertices/edges and instead used a node pointer to do the traversals which has its obvious downfalls but was the best solution I could come up without the Kruskal's implementation complications.