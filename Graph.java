import java.util.ArrayList;
import java.util.Random;

public class Graph {

	public ArrayList<Node> nodes = new ArrayList<Node>();
	public ArrayList<Node[]> allPossibleNodeConnections = new ArrayList<Node[]>();
	
	public ArrayList<Node[]> shortestNodeConnection = new ArrayList<Node[]>();

	public void generateNodes(int numberOfNodes, int xLimit, int yLimit) {
		nodes.clear();
		shortestNodeConnection.clear();

		Random random = new Random();
		ArrayList<int[]> usedCoordinates = new ArrayList<int[]>();

		for (int i = 0; i < numberOfNodes; i++) {
			boolean flag = true;
			boolean found = false;

			int tempX = random.nextInt(0, xLimit);
			int tempY = random.nextInt(0, yLimit);

			while (flag == true) {
				for (int j = 0; j < usedCoordinates.size(); j++) {
					if (usedCoordinates.get(j)[0] == tempX && usedCoordinates.get(j)[1] == tempY) {
						found = true;
					}
				}

				if (found == true) {
					tempX = random.nextInt(0, xLimit);
					tempY = random.nextInt(0, yLimit);
					found = false;
				} else {
					usedCoordinates.add(new int[] { tempX, tempY });

					Node node = new Node(getNodeName(i), tempX, tempY);
					nodes.add(node);

					flag = false;
				}
			}
		}

		createAllPossibleLinks();
	}

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

	public void linkNodes(int[] order) {
		shortestNodeConnection.clear();
		for (int i = 0; i < order.length - 1; i++) {
			shortestNodeConnection.add(new Node[] { nodes.get(order[i]), nodes.get(order[i + 1]) });
		}
	}
}
