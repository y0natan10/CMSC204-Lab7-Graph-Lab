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
		this(_first, _second, false, 1);
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
	public Edge(Vertex<T> _first, Vertex<T> _second, boolean _directed, int _weight) {
		this.directed = _directed;
		this.first = _first;
		this.second = _second;
		this.weight = _weight;

		syncVertices();
	}

	/**
	 * make sure the both vertices in the edge know that they are connected
	 */
	private void syncVertices() {
		if (first != null && second != null) {
			first.getAdjecencyList().add(second);
			second.incrementInDegree(); // needed for topological Sort

			if (!directed) {
				second.getAdjecencyList().add(first);
				first.incrementInDegree();
			}
		}
	}

	/**
	 * @return the first
	 */
	public Vertex<T> getFirst() {
		return this.first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(Vertex<T> first) {
		this.first = first;
	}

	/**
	 * @return the second
	 */
	public Vertex<T> getSecond() {
		return this.second;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(Vertex<T> second) {
		this.second = second;
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

	@Override
	public String toString() {
		// if it's directed i want to point one way
		// undirected should point both ways
		String connector = directed ? " -> " : " <-> ";
		return first.getData() + connector + second.getData() + " (weight: " + weight + ")";
	}
}
