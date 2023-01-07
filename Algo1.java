import java.util.ArrayList;


public class Algo1 {
	
	static ArrayList<Node> lowestCostPath = new ArrayList<Node>();
	static float lowestCost = -1;
	
	public static void GetShortestPath(Graph graph) {
		permute(graph.nodes, 0);
	}
	
	static void permute(ArrayList<Node> arr, int k) {
		 for (int i = k; i < arr.size(); i++) {
	            Node temp = arr.get(k);
	            arr.set(k, arr.get(i));
	            arr.set(i, temp);
	            //arr[k] = arr[i];
	            //arr[i] = temp;
	                 
	            permute(arr, k + 1);

//	            temp = arr[k];
//	            arr[k] = arr[i];
//	            arr[i] = temp;
	            temp = arr.get(k);
	            arr.set(k, arr.get(i));
	            arr.set(i, temp);
	        }
	        if (k == arr.size() - 1) {
	            for (int z = 0; z < arr.size(); z++) {
	            	System.out.print(arr.get(z).nodeName + " -> ");
	            }
	            System.out.println(" ");
	        }
	}
}
