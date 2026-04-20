// Yonatan Rubin
// M21105076

import java.util.ArrayList;

public class Vertex<T> {
	// what does the vertex contain
	private T data;

	// direct neighbors
	private ArrayList<Vertex<T>> adjecencyList;

	// well traversing through the graph, has this vertex been visited or not
	private boolean visited;

	/**
	 * basic constructor to initialize a vertex
	 * 
	 * @param _data
	 */
	public Vertex(T _data) {
		this.setData(_data);
		this.setVisited(false);
	}

	/**
	 * method for when we visit a vertex
	 */
	public void visit() {
		this.setVisited(true);
		// TODO: something happens when you visit
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
	 * @param _data the data to set
	 */
	public void setData(T _data) {
		this.data = _data;
	}

	/**
	 * checks if a vertex has been visited or not
	 * 
	 * @return the visited
	 */
	public boolean isVisited() {
		return this.visited;
	}

	/**
	 * change the visited status of a vertex
	 * 
	 * @param _visited the visited to set
	 */
	public void setVisited(boolean _visited) {
		this.visited = _visited;
	}

}
