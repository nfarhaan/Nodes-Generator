import java.util.Arrays;
import java.util.Iterator;
import java.util.function.IntPredicate;

public class Algorithm {
	static Graph graph;
	static int[] shortestPath;
	
	static void swap(int[] indexPool, int i, int j) {
    	int t = indexPool[i];
        indexPool[i] = indexPool[j];
        indexPool[j] = t;
    }

    static void reverse(int indexPool[], int l, int h) {
        while (l < h) {
            swap(indexPool, l, h);
            l++;
            h--;
        }
    }

    static int findCeil(int indexPool[], int first, int l, int h) {
        int ceilIndex = l;

        for (int i = l + 1; i <= h; i++)
            if (indexPool[i] > first && indexPool[i] < indexPool[ceilIndex])
                ceilIndex = i;

        return ceilIndex;
    }
    
    static double calculateEulerDistance(int x1, int y1, int x2, int y2) {
    	return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    
    static double getPathDistance(int[] order) {
    	double d = 0;
    	for (int i = 0; i < order.length - 1; i++) {
    		d += calculateEulerDistance(graph.nodes.get(order[i]).posX, graph.nodes.get(order[i]).posY, graph.nodes.get(order[i + 1]).posX, graph.nodes.get(order[i + 1]).posY);
    	}
    	return d;
    }
    
    static void permute(Graph graph) {
    	Algorithm.graph = graph;
    	
    	int[] indexPool = new int[graph.nodes.size()];
        Arrays.setAll(indexPool, i -> i);
        
        shortestPath = indexPool;
        double shortestDistance = 99999999;
        double currentDistance = 0;
    	
        int size = indexPool.length;

        boolean isFinished = false;
        while (!isFinished) {
        	currentDistance = getPathDistance(indexPool);
      
        	if(shortestDistance > currentDistance) {
        		shortestDistance = currentDistance;
        		shortestPath = indexPool.clone();
        	}
        	
        	for (int i : indexPool) {
        		System.out.print(i);
    		}
        	System.out.println(" - " + currentDistance);
        	System.out.print("\n");
        	
            int i;
            for (i = size - 2; i >= 0; --i)
                if (indexPool[i] < indexPool[i + 1])
                    break;

            if (i == -1)
                isFinished = true;
            else {
                int ceilIndex = findCeil(indexPool, indexPool[i], i + 1,
                        size - 1);

                swap(indexPool, i, ceilIndex);

                reverse(indexPool, i + 1, size - 1);
            }
        }
       
        String s = "";
        for (int k : shortestPath) {
    		s += k;
		}
        System.out.println("shortest path: " + s + " with a distance of " + shortestDistance);
    }
}

