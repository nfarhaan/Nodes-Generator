public class Node {
	
	int posX, posY;		// X and Y position of node
	String nodeName;	// Name of node
	
	// Constructor to set the values of the node when instantiating
	public Node(String name, int x, int y) {
		nodeName = name;
		posX = x;
		posY = y;
	}
}