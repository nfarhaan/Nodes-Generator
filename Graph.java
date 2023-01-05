import java.time.Year;
import java.util.ArrayList;
import java.util.Random;

public class Graph {
	
	public ArrayList<Node> nodes = new ArrayList<Node>();
	
	public void generateNodes(int numNodes, int xLimit, int yLimit) {
		nodes.clear();
        Random random = new Random();
		ArrayList<int[]> row = new ArrayList<int[]>();

        for(int i=0; i<numNodes; i++) {
            boolean flag= true;
            boolean found =false;
            int tempx= random.nextInt(0, xLimit);
            int tempy= random.nextInt(0, yLimit);

            while(flag==true) {
             for(int j=0; j<row.size(); j++) {
                if(row.get(j)[0]== tempx && row.get(j)[1]== tempy) {
                    found=true;
                }
             }

               if (found==true) {
                    tempx= random.nextInt(0, xLimit);
                    tempy= random.nextInt(0, yLimit);
                }
               else {
	                row.add(new int[]{tempx,tempy});
	                
	                Node node = new Node("" + i, tempx, tempy);
	                nodes.add(node);
	                
	                flag = false;
                }
            }
        }
        
        showNodes();
    }
	
	private void showNodes() {		
		for (int i=0; i< nodes.size();i++) {
			System.out.println(nodes.get(i).nodeName + "-" + nodes.get(i).posX +","+ nodes.get(i).posY);
		}
	}
}
