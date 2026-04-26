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
		// make sure the edge were adding actually exists
		if (!vertexList.contains(start))
			vertexList.add(start);
		if (!vertexList.contains(end))
			vertexList.add(end);
		this.addEdge(new Edge<T>(start, end, directed, weight));

		// update the in-degree for the topological sort
		// if it's directed (A -> B), B's in-degree goes up.
		end.incrementInDegree();

		// If undirected, increment both, but topological is directed only
		// so not really applicable?
		if (directed) {
			start.incrementInDegree();
		}
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
	// this took the longest to debug
	// because i forgot to add an edge during the setup (:
	public ArrayList<Vertex<T>> topologicalSort() {
		// reset all inDegrees to be 0 and then count them again?
		for (Vertex<T> v : this.getVertexList()) {
			v.setInDegree(0);
		}
		for (Edge<T> e : this.getEdgeList()) {
			e.getSecond().incrementInDegree();
		}

		ArrayList<Vertex<T>> topologicalOrder = new ArrayList<>();
		Queue<Vertex<T>> queue = new LinkedList<>();

		// Sort to ensure initial nodes are picked alphabetically
		this.getVertexList().sort((v1, v2) -> v1.getData().toString().compareTo(v2.getData().toString()));

		// 1. Initialize predCount array
		int[] predCount = new int[this.getVertexList().size()];
		for (int i = 0; i < this.getVertexList().size(); ++i) {
			predCount[i] = this.getVertexList().get(i).getInDegree();
			if (predCount[i] == 0) {
				queue.add(this.getVertexList().get(i));
			}
		}

		// print the predCount array to make sure everything matches
		// (it goes vertex name:predCount)
		System.out.print("Starting predCounts: [");
		for (int i = 0; i < predCount.length; ++i) {
			System.out.print(this.getVertexList().get(i).getData() + ":" + predCount[i]
					+ (i < predCount.length - 1 ? ", " : "]\n\n"));
		}

		// 2. Loop through the queue
		while (!queue.isEmpty()) {
			// Show current state before processing
			System.out.println("Current Queue: " + queue);

			// take the head of the queue for the current iteration
			Vertex<T> current = queue.poll();
			// add it to the order for the return statement
			// otherwise you wilL HAVE AN EMPTY LIST
			topologicalOrder.add(current);

			System.out.println("Processing: " + current.getData());

			// now decrement each neighbor by 1
			for (Vertex<T> neighbor : current.getAdjecencyList()) {
				int neighborIndex = this.getVertexList().indexOf(neighbor);

				// Decrement the predCount for this neighbor
				--predCount[neighborIndex];

				// Show the change
				System.out.println("\t-> Decrementing predCount for " + neighbor.getData() + " (New count: "
						+ predCount[neighborIndex] + ")");

				if (predCount[neighborIndex] == 0) {
					queue.add(neighbor);
					System.out.println("\t-> " + neighbor.getData() + " hit 0 predCount. Adding to queue.");
				}
			}

			// Print the full predCount array for this step
			System.out.print("Current predCounts: [");
			for (int i = 0; i < predCount.length; ++i) {
				System.out.print(this.getVertexList().get(i).getData() + ":" + predCount[i]
						+ (i < predCount.length - 1 ? ", " : "]\n\n"));
			}
		}

		return topologicalOrder;
	}

	/**
	 * it's the method, the magic, the bastard itself
	 * DIJKSTRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
	 * 
	 * @param source
	 */
	// AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
	// pain, thank god for graduate friends who can review my code
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
		System.out.println("prim");

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

			// only want to print this if there is a predecessor
			// the first vertex/start won't have one
			if (current.getPredecessor() != null) {
				System.out.println("Adding edge: " + current.getPredecessor().getData() + " -- " + current.getData()
						+ " (Weight: " + current.getDistance() + ")");
			} else {
				System.out.println("Starting tree at: " + current.getData());
			}

			current.setVisited(true);

			// not efficient, should really make vertex class have a list of its edges
			// but i don't feel like refactoring
			for (Edge<T> e : this.getEdgeList()) {
				Vertex<T> neighbor = null;

				if (e.getFirst().equals(current)) {
					neighbor = e.getSecond();
				} else if (e.getSecond().equals(current)) {
					// need this second check due to issues with undirected graphs
					neighbor = e.getFirst();
				}
				// since we might be null, just abort if so
				if (neighbor != null) {

					if (!neighbor.isVisited() && e.getWeight() < neighbor.getDistance()) {
						neighbor.setDistance(e.getWeight());
						neighbor.setPredecessor(current);
						pq.add(neighbor);
					}
				}

			}
		}
	}

	public ArrayList<Edge<T>> kruskalTree() {
		System.out.println("kruskal");

		// make an array to contain the edges for the minimum spanning tree
		ArrayList<Edge<T>> mst = new ArrayList<Edge<T>>();

		// get the edges in sorted order
		this.getEdgeList().sort((e1, e2) -> Integer.compare(e1.getWeight(), e2.getWeight()));

		// make an array for each group to have its own number
		int[] groupID = new int[this.getVertexList().size()];
		for (int i = 0; i < groupID.length; ++i) {
			// each 'group' is initialized with its own number
			groupID[i] = i;
		}

		// now loop through the edges, checking if the start and end of a new edge are
		// already connected
		for (Edge<T> e : this.getEdgeList()) {
			int indexFirst = this.getVertexList().indexOf(e.getFirst());
			int indexSecond = this.getVertexList().indexOf(e.getSecond());

			if (groupID[indexFirst] != groupID[indexSecond]) {
				// they are part of different groups,
				// so this is the cheapest edge to add to connect them
				System.out.println("Adding edge: " + e.toString());
				mst.add(e);

				// now merge the groups by overwriting one of the ID's with the other
				int oldID = groupID[indexSecond];
				int newID = groupID[indexFirst];
				for (int i = 0; i < groupID.length; ++i) {
					if (groupID[i] == oldID) {
						groupID[i] = newID;
					}
				}

			}
			// If groupID[indexA] == groupID[indexB], we ignore it to prevent cycles
		}

		// at the end of this, mst is a list of edges that connect the whole graph
		return mst;
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