import java.util.ArrayList;
import java.util.Random;

// This class is responsible for the nodes and paths
public class Graph {

	public ArrayList<Node> nodes = new ArrayList<Node>();	// Store all the generated nodes
	public ArrayList<Node[]> allPossibleNodeConnections = new ArrayList<Node[]>();	// Store all the links between the generated nodes
	
	public ArrayList<Node[]> shortestNodeConnection = new ArrayList<Node[]>();	// Store the links between the nodes of the shortest path

	// Function to randomly generate the nodes
	public void generateNodes(int numberOfNodes, int xLimit, int yLimit) {
		// Clear the arrays before generating/regenerating the nodes
		nodes.clear();
		shortestNodeConnection.clear();

		Random random = new Random();		
		ArrayList<int[]> usedCoordinates = new ArrayList<int[]>();	// Store already generated nodes to avoid duplicates

		for (int i = 0; i < numberOfNodes; i++) {	// Will loop for specified number of node to generate
			boolean flag = true;
			boolean found = false;

			int tempX = random.nextInt(0, xLimit);	// temporary x-coordinate of the node
			int tempY = random.nextInt(0, yLimit);	// temporary y-coordinate of the node

			// Check if the node has already been generated
			while (flag == true) {
				for (int j = 0; j < usedCoordinates.size(); j++) {
					if (usedCoordinates.get(j)[0] == tempX && usedCoordinates.get(j)[1] == tempY) {
						found = true;
					}
				}
				
				if (found == true) {	// If already generated, regenerate another set of coordinates
					tempX = random.nextInt(0, xLimit);
					tempY = random.nextInt(0, yLimit);
					found = false;
				} else {
					usedCoordinates.add(new int[] { tempX, tempY });	// Add new node to array of already generated nodes

					Node node = new Node(getNodeName(i), tempX, tempY);
					nodes.add(node);	// Add node to list of nodes

					flag = false;
				}
			}
		}

		createAllPossibleLinks();
	}

	// Function to generate names for nodes in ascending order
	// If name is greater than A the it continues on to AA, AB, AC,...
	private String getNodeName(int index) {
		String name = "";
		index++;

		while (index > 0) {
			int r = index % 26;
			if (r == 0) {
				name = "Z" + name;
				index = index / 26 - 1;
			} else {
				name = (char) (r + 64) + name;
				index = index / 26;
			}
		}
		return name;
	}

	// Link all nodes to each other
	public void createAllPossibleLinks() {
		allPossibleNodeConnections.clear();
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = 0; j < nodes.size(); j++) {
				if (i != j) {
					allPossibleNodeConnections.add(new Node[] { nodes.get(i), nodes.get(j) });
				}
			}
		}
	}

	// Link the nodes of the shortest path
	public void linkNodes(int[] order) {
		shortestNodeConnection.clear();
		for (int i = 0; i < order.length - 1; i++) {
			shortestNodeConnection.add(new Node[] { nodes.get(order[i]), nodes.get(order[i + 1]) });
		}
	}
}
