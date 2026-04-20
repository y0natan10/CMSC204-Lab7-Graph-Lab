// Yonatan Rubin
// M21105076

public class Edge<T> {
	// is the edge directed or not
	boolean directed;

	// the start of the edge (in case it's directed)
	Vertex<T> first;
	// end of the edge
	Vertex<T> second;

	// weight of an edge
	private int weight;

	/**
	 * default constructor that sets up an undirected and unweighted edge *
	 * 
	 * @param _first  start of the edge
	 * @param _second end of the edge
	 */
	public Edge(Vertex<T> _first, Vertex<T> _second) {
		this.directed = false;
		this.weight = 1;
		this.first = _first;
		this.second = _second;
	}

	/**
	 * 
	 * fully parameterized constructor for an edge set if an edge is directed, the
	 * start/first vertex, the end/second vertex, and the weight of the edge
	 * 
	 * @param _directed if the edge one way/directed
	 * @param _first    start of the edge
	 * @param _second   end of the edge
	 * @param _weight   the cost/weight of the edge
	 */
	public Edge(boolean _directed, Vertex<T> _first, Vertex<T> _second, int _weight) {
		this.directed = _directed;
		this.first = _first;
		this.second = _second;
		this.weight = _weight;
	}

	/**
	 * setter method for the weight of an edge
	 * 
	 * @param _weight
	 */
	public void setWeight(int _weight) {
		this.weight = _weight;
	}

	/**
	 * getter method for the weight of an edge
	 * 
	 * @return Edge.weight
	 */
	public int getWeight() {
		return this.weight;
	}
}
