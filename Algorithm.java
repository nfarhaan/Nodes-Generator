import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Algorithm {
	public static int[] shortestPath;
	public static double shortestDistance = 99999999;
	public static double timeTaken = 0;
	
	private static Graph graph;
	private static int[] indexPool;
	private static boolean hasSkiped, isFinished = false;

	private static void swap(int[] indexPool, int i, int j) {
		int t = indexPool[i];
		indexPool[i] = indexPool[j];
		indexPool[j] = t;
	}

	private static void reverse(int indexPool[], int l, int h) {
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
		double distance = 0;

		ArrayList<Integer> used = new ArrayList<Integer>();
		ArrayList<Integer> remaining = new ArrayList<Integer>();

		used.clear();
		remaining.clear();

		int toIterate = -1;

		used.add(order[0]);

		for (int i = 0; i < order.length - 1; i++) {

			distance += calculateEulerDistance(graph.nodes.get(order[i]).posX, graph.nodes.get(order[i]).posY,
					graph.nodes.get(order[i + 1]).posX, graph.nodes.get(order[i + 1]).posY);
			if (distance > shortestDistance) {
				toIterate = order[i + 1];

				for (int j = i + 2; j < order.length; j++) {
					remaining.add(order[j]);
				}

				remaining.add(toIterate);

				if (remaining.size() > 2) {
					hasSkiped = true;

					toIterate++;
					while (used.contains(toIterate)) {
						toIterate++;
					}

					int mainCount = 0;
					if (toIterate > order.length - 1) {

						int usedLastElement = -1;
						while (true) {
							if (used.size() == 1 && mainCount >= 1) {
								if (used.get(0) == order.length - 1) {
									isFinished = true;
									return 9999999;
								}
							}

							mainCount++;

							usedLastElement = used.get(used.size() - 1);

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

					for (int h = 0; h < used.size(); h++) {
						indexPool[h] = used.get(h);
					}

					indexPool[used.size()] = toIterate;

					remaining.remove(Integer.valueOf(toIterate));
					Collections.sort(remaining);

					for (int z = 0; z < remaining.size(); z++) {
						indexPool[used.size() + 1 + z] = remaining.get(z);
					}
				}

				return distance;
			} else {
				used.add(order[i + 1]);
			}
		}
		return distance;
	}
	
	static void permute(Graph graph) {
		shortestDistance = 99999999;

		long startTime = System.currentTimeMillis();

		Algorithm.graph = graph;

		indexPool = new int[graph.nodes.size()];
		Arrays.setAll(indexPool, i -> i);

		shortestPath = indexPool;
		double currentDistance = 0;

		int size = indexPool.length;

		isFinished = false;
		while (!isFinished) {

			hasSkiped = false;
			currentDistance = getPathDistance(indexPool);

			if (!hasSkiped) {
				if (shortestDistance > currentDistance) {
					shortestDistance = currentDistance;
					shortestPath = indexPool.clone();
				}

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
		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		timeTaken = totalTime / 1000.0;
	}
}
