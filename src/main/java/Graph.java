// Yonatan Rubin
// M21105076

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph<T> {
	private ArrayList<Vertex<T>> vertexList;
	private ArrayList<Edge<T>> edgeList;

	public Graph(ArrayList<Vertex<T>> _vertexList, ArrayList<Edge<T>> _edgeList) {
		this.vertexList = new ArrayList<Vertex<T>>(_vertexList);
		this.edgeList = new ArrayList<Edge<T>>(_edgeList);
	}

	public void addVertex(Vertex<T> toAdd) {
		this.vertexList.add(toAdd);
	}

	public void addEdge(Edge<T> toAdd) {
		this.edgeList.add(toAdd);
	}

	public void addEdge(Vertex<T> start, Vertex<T> end) {
		this.addEdge(start, end, true, 1);
	}

	public void addEdge(Vertex<T> start, Vertex<T> end, boolean directed, int weight) {
		this.addEdge(new Edge<T>(start, end, directed, weight));
	}

	/**
	 * Breadth First Search traversal
	 * 
	 * @param start
	 */
	public void BFS(Vertex<T> start) {
		// initialize all vertices to be not visited
		for (Vertex<T> v : vertexList) {
			v.setVisited(false);
		}

		Queue<Vertex<T>> vertexQueue = new LinkedList<Vertex<T>>();
		// 'process' the starting node
		vertexQueue.add(start);
		start.visit();

		while (!vertexQueue.isEmpty()) {
			Vertex<T> current = vertexQueue.poll();
			// let me see the result please :3
			System.out.print(current.getData() + " ");

			for (Vertex<T> neighbor : current.getAdjecencyList()) {
				if (!neighbor.isVisited()) {
					neighbor.visit();
					vertexQueue.add(neighbor);
				}
			}
		}
	}

	/**
	 * Depth First Search traversal
	 * 
	 * @param start
	 */
	public void DFS(Vertex<T> start) {
		for (Vertex<T> v : vertexList)
			v.setVisited(false);
		DFS_Helper(start);
	}

	private void DFS_Helper(Vertex<T> current) {
		current.visit();
		System.out.print(current.getData() + " ");

		for (Vertex<T> neighbor : current.getAdjecencyList()) {
			if (!neighbor.isVisited()) {
				DFS_Helper(neighbor);
			}
		}
	}

	/**
	 * Generates an adjacency matrix with vertices in alphabetical order
	 */
	public int[][] getAdjacencyMatrix() {
		int n = vertexList.size();
		int[][] matrix = new int[n][n];

		// Sort vertices alphabetically by their data string representation
		vertexList.sort((v1, v2) -> v1.getData().toString().compareTo(v2.getData().toString()));

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				matrix[i][j] = hasEdge(vertexList.get(i), vertexList.get(j)) ? 1 : 0;
			}

		}
		return matrix;
	}

	/**
	 * Displays the adjacency matrix with fixed-width formatting. Satisfies the lab
	 * requirement for alphabetical order.
	 */
	public void displayAdjacencyMatrix() {
		int[][] matrix = this.getAdjacencyMatrix(); // This sorts the list internally
		int n = vertexList.size();
		String format = "%-12s";

		System.out.println("Adjacency Matrix (Alphabetical):");

		// Header Row
		System.out.print(String.format(format, ""));
		for (Vertex<T> v : vertexList) {
			System.out.print(String.format(format, v.getData()));
		}
		System.out.println();

		// Divider
		System.out.print(String.format(format, ""));
		for (int i = 0; i < n; ++i) {
			System.out.print(String.format(format, "---"));
		}
		System.out.println();

		// Data Rows
		for (int i = 0; i < n; ++i) {
			System.out.print(String.format("%-10s | ", vertexList.get(i).getData()));
			for (int j = 0; j < n; ++j) {
				System.out.print(String.format(format, matrix[i][j]));
			}
			System.out.println();
		}
	}

	/**
	 * Displays the adjacency lists with fixed-width labels.
	 */
	public void displayAdjacencyLists() {
		// Ensure they are sorted alphabetically to match the matrix
		vertexList.sort((v1, v2) -> v1.getData().toString().compareTo(v2.getData().toString()));

		System.out.println("\nAdjacency Lists:");

		for (Vertex<T> v : vertexList) {
			System.out.print(String.format("%-12s | ", v.getData()));
			ArrayList<Vertex<T>> neighbors = v.getAdjecencyList();

			if (neighbors.isEmpty()) {
				System.out.print("[No neighbors]");
			} else {
				for (int i = 0; i < neighbors.size(); ++i) {
					System.out.print(neighbors.get(i).getData() + (i < neighbors.size() - 1 ? ", " : ""));
				}
			}
			System.out.println();
		}
	}

	/**
	 * checks if there is an edge between 2 vertices
	 * 
	 * @param start start of the edge
	 * @param end   end of the edge
	 * @return true if there is an edge between the 2 vertices
	 */
	private boolean hasEdge(Vertex<T> start, Vertex<T> end) {
		return start.getAdjecencyList().contains(end);
	}

	/**
	 * breadth first search for topological sort
	 * 
	 * @return the vertices as an ArrayList in the order that they were encountered
	 */
	public ArrayList<Vertex<T>> topologicalSort() {
		ArrayList<Vertex<T>> result = new ArrayList<>();
		Queue<Vertex<T>> queue = new LinkedList<>();

		// alphabetical order for the start
		this.vertexList.sort((v1, v2) -> v1.getData().toString().compareTo(v2.getData().toString()));

		// Add all vertices with 0 in-degree to the queue
		for (Vertex<T> v : this.vertexList) {
			if (v.getInDegree() == 0) {
				queue.add(v);
			}
		}

		while (!queue.isEmpty()) {
			Vertex<T> current = queue.poll();
			result.add(current);

			for (Vertex<T> neighbor : current.getAdjecencyList()) {
				neighbor.decrementInDegree();
				if (neighbor.getInDegree() == 0) {
					queue.add(neighbor);
				}
			}
		}
		return result;
	}

	/**
	 * it's the method, the magic, the bastard itself
	 * DIJKSTRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
	 * 
	 * @param source
	 */
	public void dijkstra(Vertex<T> source) {
		// 1. Reset all vertices
		for (Vertex<T> v : this.getVertexList()) {
			v.setDistance(Integer.MAX_VALUE);
			v.setVisited(false);
			v.setPredecessor(null);
		}

		// 2. Set source distance to 0 and add to PriorityQueue
		source.setDistance(0);
		PriorityQueue<Vertex<T>> pq = new PriorityQueue<>();
		pq.add(source);

		while (!pq.isEmpty()) {
			Vertex<T> u = pq.poll();

			if (u.isVisited())
				continue;
			u.setVisited(true);

			// 3. Relax edges
			for (Edge<T> edge : edgeList) {
				// Find edges starting from current vertex u
				if (edge.first.equals(u)) {
					Vertex<T> v = edge.second;
					int weight = edge.getWeight();
					int distanceThroughU = u.getDistance() + weight;

					if (distanceThroughU < v.getDistance()) {
						v.setDistance(distanceThroughU);
						v.setPredecessor(u);
						pq.add(v);
					}
				}
			}
		}
	}

	/**
	 * Prints the shortest paths from the source to all other vertices. Must be
	 * called AFTER running dijkstra(source).
	 */
	public void printShortestPaths(Vertex<T> source) {
		// i like the alphabetical order
		this.getVertexList().sort((v1, v2) -> v1.getData().toString().compareTo(v2.getData().toString()));

		// general plan is
		// for each vertex, add it to a String,
		// then look at the vertex that is the predecessor,
		// then add that and so on and so forth

		System.out.println("Shortest Paths from " + source.getData());

		for (Vertex<T> v : this.getVertexList()) {
			if (v.equals(source))
				continue;

			System.out.print("To " + v.getData() + " (" + v.getDistance() + "): ");

			if (v.getDistance() == Integer.MAX_VALUE) {
				System.out.println("No path found.");
				continue;
			}

			// Logic: Build the path string right-to-left
			String path = v.getData().toString();
			Vertex<T> current = v.getPredecessor();

			while (current != null) {
				// Add the predecessor to the FRONT of the string
				path = current.getData() + " -> " + path;
				current = current.getPredecessor();
			}

			System.out.println(path);
		}
	}

	/**
	 * make a minimal spanning tree using prim's algorithm
	 * 
	 * @param start
	 */
	public void primTree(Vertex<T> start) {
		// initialize the vertices
		for (Vertex<T> v : this.getVertexList()) {
			v.setDistance(Integer.MAX_VALUE);
			v.setVisited(false);
			v.setPredecessor(null);
		}
		start.setDistance(0);
		Queue<Vertex<T>> pq = new PriorityQueue<Vertex<T>>();
		pq.add(start);
		while (!pq.isEmpty()) {
			Vertex<T> current = pq.poll();
			if (current.isVisited()) {
				// don't make a cycle
				continue;
			}
			current.setVisited(true);

			// not efficient, should really make vertex class have a list of its edges
			// but i don't feel like refactoring
			for (Edge<T> e : this.getEdgeList()) {
				if (e.getFirst().equals(current)) {
					Vertex<T> neighbor = e.getSecond();
					if (!neighbor.isVisited() && e.getWeight() < neighbor.getDistance()) {
						neighbor.setDistance(e.getWeight());
						neighbor.setPredecessor(current);
						pq.add(neighbor);
					}
				}
			}
		}
	}

	public void kruskalTree() {
		ArrayList<Edge<T>> sortedEdges = new ArrayList<Edge<T>>();
		sortedEdges = (ArrayList<Edge<T>>) this.getEdgeList().clone();
		sortedEdges.sort((e1, e2) -> Integer.compare(e1.getWeight(), e2.getWeight()));
		// the edges of the graph are now sorted
	}

	public ArrayList<Vertex<T>> getVertexList() {
		return this.vertexList;
	}

	public ArrayList<Edge<T>> getEdgeList() {
		return this.edgeList;
	}

	public void setEdgeList(ArrayList<Edge<T>> edgeList) {
		this.edgeList = edgeList;
	}

	/**
	 * reset the graph by clearing the vertex and edge list
	 */
	public void clear() {
		this.getVertexList().clear();
		this.getEdgeList().clear();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();

		return res.toString();
	}
}