// Yonatan Rubin
// M21105076

import java.util.ArrayList;

public class Vertex<T> {
	// what does the vertex contain
	private T data;

	// direct neighbors
	private ArrayList<Vertex<T>> adjecencyList;

	// tracks the number of incoming edges for topological sorting
	private int inDegree;

	// while traversing through the graph, has this vertex been visited or not
	private boolean visited;

	/**
	 * basic constructor to initialize a vertex
	 * 
	 * @param _data
	 */
	public Vertex(T _data) {
		this.setData(_data);
		this.setVisited(false);
		this.inDegree = 0;
		this.adjecencyList = new ArrayList<Vertex<T>>();
	}

	/**
	 * method for when we visit a vertex
	 */
	public void visit() {
		this.setVisited(true);
	}

	/**
	 * getter method for the list of of vertexes this vertex can reach aka this
	 * vertex's neighbors
	 * 
	 * @return the adjecencyList
	 */
	public ArrayList<Vertex<T>> getAdjecencyList() {
		return this.adjecencyList;
	}

	/**
	 * getter method for the data of a vertex
	 * 
	 * @return the data
	 */
	public T getData() {
		return this.data;
	}

	/**
	 * setter method for the data of a vertex
	 * 
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return this.visited;
	}

	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * @return the inDegree
	 */
	public int getInDegree() {
		return this.inDegree;
	}

	/**
	 * @param inDegree the inDegree to set
	 */
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}

	/**
	 * Increments the in-degree count
	 */
	public void incrementInDegree() {
		++this.inDegree;
	}

	/**
	 * Decrements the in-degree count
	 */
	public void decrementInDegree() {
		++this.inDegree;
	}

	@Override
	public String toString() {
		return data.toString();
	}
}