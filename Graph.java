import java.time.Year;
import java.util.ArrayList;
import java.util.Random;

public class Graph {
	
	public ArrayList<Node> nodes = new ArrayList<Node>();
	
	public void generateNodes(int numberOfNodes, int xLimit, int yLimit) {
		nodes.clear();
		
        Random random = new Random();
		ArrayList<int[]> usedCoordinates = new ArrayList<int[]>();

		for(int i=0; i < numberOfNodes; i++) {
			boolean flag= true;
			boolean found = false;

			int tempX = random.nextInt(0, xLimit);
			int tempY = random.nextInt(0, yLimit);
			
			while(flag==true) {
				for(int j=0; j < usedCoordinates.size(); j++) {
					if(usedCoordinates.get(j)[0]== tempX && usedCoordinates.get(j)[1]== tempY) {
						found=true;
					}
				}

				if (found==true) {
					tempX= random.nextInt(0, xLimit);
					tempY= random.nextInt(0, yLimit);
				}
				else {
					usedCoordinates.add(new int[]{tempX,tempY});

					Node node = new Node("" + i, tempX, tempY);
					nodes.add(node);

					flag = false;
				}
			}
		}
        
        //showNodes();
    }
	
	private void showNodes() {		
		for (int i=0; i< nodes.size();i++) {
			System.out.println(nodes.get(i).nodeName + "-" + nodes.get(i).posX +","+ nodes.get(i).posY);
		}
	}
}
