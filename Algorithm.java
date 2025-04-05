import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// This class is responsible for the calculations of the shortest path
public class Algorithm {
	public static int[] shortestPath;	// Store the order in which the nodes must be arranged to get the shortest path
	public static double shortestDistance = 99999999;	// Store the shortest distance
	public static double timeTaken = 0;	// Store the time taken to execute the algorithm
	
	private static Graph graph;
	private static int[] indexPool;	// Store indexes
	private static boolean hasSkiped, isFinished = false;

	// Swap the elements of indexPool at location i and j
	private static void swap(int[] indexPool, int i, int j) {
		int t = indexPool[i];
		indexPool[i] = indexPool[j];
		indexPool[j] = t;
	}

	// Reverse the array indexPool from index l to h
	private static void reverse(int indexPool[], int l, int h) {
		while (l < h) {
			swap(indexPool, l, h);
			h--;
			l++;
		}
	}

	// Find the highest element of indexPool from l to h
	static int findCeil(int indexPool[], int first, int l, int h) {
		int ceilIndex = l;

		for (int i = l + 1; i <= h; i++)
			if (indexPool[i] > first && indexPool[i] < indexPool[ceilIndex])
				ceilIndex = i;

		return ceilIndex;
	}

	// Function to calculate the distance between node x and y
	static double calculateEulerDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

	// Function to get the total distance of the path
	static double getPathDistance(int[] order) {
		double distance = 0;

		ArrayList<Integer> used = new ArrayList<Integer>();	// Array for elements already in the path
		ArrayList<Integer> remaining = new ArrayList<Integer>();	// Array for elements not used in the path

		// Clear the arrays
		used.clear();
		remaining.clear();

		int toIterate = -1;

		used.add(order[0]);

		for (int i = 0; i < order.length - 1; i++) {

			// Increment the distance of the currently considered path
			distance += calculateEulerDistance(graph.nodes.get(order[i]).posX, graph.nodes.get(order[i]).posY,
					graph.nodes.get(order[i + 1]).posX, graph.nodes.get(order[i + 1]).posY);
			
			if (distance > shortestDistance) {	// When the current distance is greater than the shortest distance
				toIterate = order[i + 1];	// Index at which the current distance exceeds the shortest distance 

				// Add the next indexes in array remaining
				for (int j = i + 2; j < order.length; j++) {
					remaining.add(order[j]);
				}
				
				// Add new value of toIterate to array remaining
				remaining.add(toIterate);

				if (remaining.size() > 2) {	// If there are more than 2 elements in array remaining
					hasSkiped = true;

					toIterate++;
					while (used.contains(toIterate)) {	// Search for the next value to iterate
						toIterate++;
					}

					int mainCount = 0;
					if (toIterate > order.length - 1) {

						int usedLastElement = -1;
						while (true) {
							if (used.size() == 1 && mainCount >= 1) {
								if (used.get(0) == order.length - 1) {
									isFinished = true;		// The remaining permutations can safely be skipped
									return 9999999;
								}
							}

							mainCount++;

							usedLastElement = used.get(used.size() - 1);

							// Remove last element of Used and assign its value to toIterate
							remaining.add(usedLastElement);
							used.remove(Integer.valueOf(usedLastElement));

							int newToIterate = usedLastElement + 1;

							boolean found = true;
							while (!remaining.contains(newToIterate)) {
								newToIterate++;
								if (newToIterate > order.length - 1) {
									found = false;
									break;
								}
							}

							if (found) {
								toIterate = newToIterate;
								break;
							}
						}
					}

					// Concatenate in the order of used, toIterate and remaining
					for (int h = 0; h < used.size(); h++) {
						indexPool[h] = used.get(h);
					}

					indexPool[used.size()] = toIterate;

					// Sort remaining before concatenating
					remaining.remove(Integer.valueOf(toIterate));
					Collections.sort(remaining);

					for (int z = 0; z < remaining.size(); z++) {
						indexPool[used.size() + 1 + z] = remaining.get(z);
					}
				}

				return distance;
			} else {
				used.add(order[i + 1]);	// Add the element in array used
			}
		}
		return distance;
	}
	
	// Function to permute all nodes in lexicographical order
	// REFERENCE
	// https://www.quora.com/How-would-you-explain-an-algorithm-that-generates-permutations-using-lexicographic-ordering
	// https://www.geeksforgeeks.org/lexicographic-permutations-of-string/
	static void permute(Graph graph) {
		shortestDistance = 99999999;

		long startTime = System.currentTimeMillis();

		Algorithm.graph = graph;

		// Set all values of indexPool in ascending order
		indexPool = new int[graph.nodes.size()];
		Arrays.setAll(indexPool, i -> i);

		shortestPath = indexPool;
		double currentDistance = 0;

		int size = indexPool.length;

		isFinished = false;
		while (!isFinished) {

			// Get the distance of the current permutation
			hasSkiped = false;
			currentDistance = getPathDistance(indexPool);

			if (!hasSkiped) {
				if (shortestDistance > currentDistance) {	// Update the shortest distance
					shortestDistance = currentDistance;
					shortestPath = indexPool.clone();		// Set the shortest path to the current path
				}

				int i;
				for (i = size - 2; i >= 0; --i)			// Find the largest index such that indexPool[i] < indexPool[i+1]
					if (indexPool[i] < indexPool[i + 1])
					break;

				if (i == -1)	// There is no more permutations in lexicographical order
					isFinished = true;
				else {
					int ceilIndex = findCeil(indexPool, indexPool[i], i + 1,
							size - 1);

					swap(indexPool, i, ceilIndex);

					reverse(indexPool, i + 1, size - 1);
				}
			}
		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		timeTaken = totalTime / 1000.0;
	}
}
