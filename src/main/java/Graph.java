// Yonatan Rubin
// M21105076

import java.util.ArrayList;
import java.util.LinkedList;
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