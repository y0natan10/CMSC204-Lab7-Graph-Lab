// Yonatan Rubin
// M21105076

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph<T> {
	private ArrayList<Vertex<T>> vertexList;
	private ArrayList<Edge<T>> edgeList;

	public Graph(ArrayList<Vertex<T>> _vertexList, ArrayList<Vertex<T>> _edgeList) {
		this.setVertexList((ArrayList<Vertex<T>>) _vertexList.clone());
		this.setEdgeList((ArrayList<Edge<T>>) _edgeList.clone());

	}

	// add vertex/edge
	public void addVertex(Vertex<T> toAdd) {
		this.getVertexList().add(toAdd);
	}

	public void addEdge(Edge<T> toAdd) {
		this.getEdgeList().add(toAdd);
	}

	// isEmpty
	public boolean isEmpty() {
		return (this.getVertexList().size() == 0);
	}

	// hasEdge (checks if there is a bridge between vertex A and B)
	/**
	 * checks if there is an edge between A and B
	 * 
	 * @param start start of the hypothetical edge
	 * @param end   end of the hypothetical edge
	 * @return true if an edge exists
	 */
	public boolean hasEdge(Vertex<T> start, Vertex<T> end) {
		return (start.getAdjecencyList().contains(end));
	}

	/**
	 * getter method for the number of vertices
	 * 
	 * @return number of vertices in the graph
	 */
	public int getNumVertices() {
		return this.getVertexList().size();
	}

	/**
	 * getter method for the number of vertices
	 * 
	 * @return number of edges in the graph
	 */
	public int getNumEdges() {
		return this.getEdgeList().size();
	}

	/**
	 * clear method to wipe the graph of all data
	 */
	public void clear() {
		this.setEdgeList(null);
		this.setVertexList(null);
	}

	/**
	 * navigate through the graph in BFS order
	 * 
	 * @param start starting point of the navigation
	 */
	public void BFS(Vertex<T> start) {
		Queue<Vertex<T>> vertexQueue = new LinkedList<Vertex<T>>();
		ArrayList<Vertex<T>> BFSOrder = new ArrayList<Vertex<T>>();

		// initialize the queue with our starting point
		vertexQueue.add(start);

		// when the queue is empty it means we reached every possible vertex from our
		// starting point
		while (!vertexQueue.isEmpty()) {
			// add the vertex next to be dealt with to the order record
			BFSOrder.add(vertexQueue.peek());
			vertexQueue.peek().visit();
			// start to process the vertex's neighbors

			// for each element at the front of the queue,
			// go through the adjacency list of that vertex
			for (Vertex<T> v : vertexQueue.remove().getAdjecencyList()) {
				if (v.isVisited()) {
					continue;
				}

				// add each neighbor to the queue to be dealt with
				vertexQueue.add(v);
			}
		}
	}

	/**
	 * wraper method for navigating through the graph in DFS
	 * 
	 * @param start starting point of the navigation
	 */
	public void DFS(Vertex<T> start, Vertex<T> search) {
		ArrayList<Vertex<T>> DFSOrder = new ArrayList<Vertex<T>>();
		DFSOrder.add(start);

	}

	/**
	 * getter method for the list of vertices in the graph
	 * 
	 * @return the vertexList
	 */
	public ArrayList<Vertex<T>> getVertexList() {
		return this.vertexList;
	}

	/**
	 * setter method to initialize a graph's list of vertices
	 * 
	 * @param vertexList the vertexList to set
	 */
	public void setVertexList(ArrayList<Vertex<T>> vertexList) {
		this.vertexList = vertexList;
	}

	/**
	 * getter method for the list of edges in the graph
	 * 
	 * @return the edgeList
	 */
	public ArrayList<Edge<T>> getEdgeList() {
		return this.edgeList;
	}

	/**
	 * setter method to initialize a graph's list of edges
	 * 
	 * @param edgeList the edgeList to set
	 */
	public void setEdgeList(ArrayList<Edge<T>> edgeList) {
		this.edgeList = edgeList;
	}
}
