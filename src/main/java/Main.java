// Yonatan Rubin
// M21105076

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		/*
		 * V(StateGraph) = {Oregon, Alaska, Texas, Hawaii, Vermont, NewYork, California}
		 * 
		 * E(StateGraph) = {(Alaska, Oregon), (Hawaii, Alaska), (Hawaii, Texas), (Texas,
		 * Hawaii), (Hawaii, California), (Hawaii, New York), (Texas, Vermont),
		 * (Vermont, California), (Vermont, Alaska)}
		 */
		ArrayList<Vertex<String>> vertexList = new ArrayList<Vertex<String>>();
		ArrayList<Edge<String>> edgeList = new ArrayList<Edge<String>>();
		Graph<String> myGraphStr = new Graph<String>(vertexList, edgeList);

		ArrayList<Vertex<Integer>> vertexListInt = new ArrayList<Vertex<Integer>>();
		ArrayList<Edge<Integer>> edgeListInt = new ArrayList<Edge<Integer>>();
		Graph<Integer> myGraphInt = new Graph<Integer>(vertexListInt, edgeListInt);

		slideOneAndTwo(myGraphStr);

		slideThree(myGraphStr);

		slideFour(myGraphStr);

		slideFiveAndSix(myGraphInt);

		slideSeven(myGraphStr);

		slideEight(myGraphInt);

		slideNine(myGraphStr);

	}

	public static <T> void slideOneAndTwo(Graph<String> myGraph) {
		// add all the edges and vertices, but put them in a block so i can minimize it
		if (true) {
			Vertex<String> Oregon = new Vertex<String>("Oregon");
			Vertex<String> Alaska = new Vertex<String>("Alaska");
			Vertex<String> Texas = new Vertex<String>("Texas");
			Vertex<String> Hawaii = new Vertex<String>("Hawaii");
			Vertex<String> Vermont = new Vertex<String>("Vermont");
			Vertex<String> NewYork = new Vertex<String>("NewYork");
			Vertex<String> California = new Vertex<String>("California");

			myGraph.addVertex(Oregon);
			myGraph.addVertex(Alaska);
			myGraph.addVertex(Texas);
			myGraph.addVertex(Hawaii);
			myGraph.addVertex(Vermont);
			myGraph.addVertex(NewYork);
			myGraph.addVertex(California);

			myGraph.addEdge(Alaska, Oregon);
			myGraph.addEdge(Hawaii, Alaska);
			myGraph.addEdge(Hawaii, Texas);
			myGraph.addEdge(Texas, Hawaii);
			myGraph.addEdge(Hawaii, California);
			myGraph.addEdge(Hawaii, NewYork);
			myGraph.addEdge(Texas, Vermont);
			myGraph.addEdge(Vermont, California);
			myGraph.addEdge(Vermont, Alaska);
		}

		System.out.println("Slide One and Two");
		System.out.println(myGraph.toString());

		displayGraphMatrix(myGraph);
		myGraph.clear();
	}

	public static <T> void slideThree(Graph<String> myGraph) {
		// add all the edges and vertices, but put them in a block so i can minimize it
		if (true) {
			Vertex<String> A = new Vertex<String>("A");
			Vertex<String> B = new Vertex<String>("B");
			Vertex<String> C = new Vertex<String>("C");
			Vertex<String> D = new Vertex<String>("D");
			Vertex<String> E = new Vertex<String>("E");
			Vertex<String> F = new Vertex<String>("F");
			Vertex<String> G = new Vertex<String>("G");

			myGraph.addVertex(A);
			myGraph.addVertex(B);
			myGraph.addVertex(C);
			myGraph.addVertex(D);
			myGraph.addVertex(E);
			myGraph.addVertex(F);
			myGraph.addVertex(G);

			myGraph.addEdge(A, B, false, 3);
			myGraph.addEdge(A, C, false, 5);
			myGraph.addEdge(A, D, false, 2);
			myGraph.addEdge(A, G, false, 4);

			myGraph.addEdge(B, C, false, 1);

			myGraph.addEdge(C, E, false, 3);
			myGraph.addEdge(C, F, false, 2);

			myGraph.addEdge(D, F, false, 2);

			myGraph.addEdge(E, G, false, 7);
		}
		displayGraphMatrix(myGraph);
		myGraph.clear();
	}

	public static <T> void slideFour(Graph<String> myGraph) {
		// add all the edges and vertices, but put them in a block so i can minimize it
		if (true) {
			Vertex<String> Austin = new Vertex<String>("Austin");
			Vertex<String> Chicago = new Vertex<String>("Chicago");
			Vertex<String> Dallas = new Vertex<String>("Dallas");
			Vertex<String> Denver = new Vertex<String>("Denver");
			Vertex<String> Houston = new Vertex<String>("Houston");
			Vertex<String> Washington = new Vertex<String>("Washington");
			Vertex<String> Atlanta = new Vertex<String>("Atlanta");

			myGraph.addVertex(Austin);
			myGraph.addVertex(Chicago);
			myGraph.addVertex(Dallas);
			myGraph.addVertex(Denver);
			myGraph.addVertex(Houston);
			myGraph.addVertex(Washington);
			myGraph.addVertex(Atlanta);

			myGraph.addEdge(Austin, Dallas, true, 200);
			myGraph.addEdge(Austin, Houston, true, 160);

			myGraph.addEdge(Chicago, Denver, true, 1000);

			myGraph.addEdge(Dallas, Austin, true, 200);
			myGraph.addEdge(Dallas, Chicago, true, 900);
			myGraph.addEdge(Dallas, Denver, true, 780);

			myGraph.addEdge(Denver, Chicago, true, 1000);
			myGraph.addEdge(Denver, Atlanta, true, 1400);

			myGraph.addEdge(Houston, Atlanta, true, 800);

			myGraph.addEdge(Washington, Dallas, true, 1300);
			myGraph.addEdge(Washington, Atlanta, true, 600);

			myGraph.addEdge(Atlanta, Houston, true, 800);
			myGraph.addEdge(Atlanta, Washington, true, 600);
		}
		displayGraphMatrix(myGraph);
		myGraph.clear();
	}

	public static <T> void slideFiveAndSix(Graph<Integer> myGraph) {
		// add all the edges and vertices, but put them in a block so i can minimize it
		if (true) {
			Vertex<Integer> zero = new Vertex<Integer>(0);
			Vertex<Integer> one = new Vertex<Integer>(1);
			Vertex<Integer> two = new Vertex<Integer>(2);
			Vertex<Integer> three = new Vertex<Integer>(3);
			Vertex<Integer> four = new Vertex<Integer>(4);
			Vertex<Integer> five = new Vertex<Integer>(5);

			myGraph.addVertex(zero);
			myGraph.addVertex(one);
			myGraph.addVertex(two);
			myGraph.addVertex(three);
			myGraph.addVertex(four);
			myGraph.addVertex(five);

			myGraph.addEdge(zero, one, false, 7);
			myGraph.addEdge(zero, two, false, 3);

			myGraph.addEdge(one, three, false, 5);
			myGraph.addEdge(one, five, false, 2);

			myGraph.addEdge(two, four, false, 8);
			myGraph.addEdge(two, five, false, 1);

			myGraph.addEdge(three, four, false, 6);

			myGraph.addEdge(four, five, false, 3);
		}
		// prim version

		// kruskal's algorithm

		displayGraphMatrix(myGraph);
		myGraph.clear();
	}

	public static <T> void slideSeven(Graph<String> myGraph) {
		// add all the edges and vertices, but put them in a block so i can minimize it
		if (true) {
			Vertex<String> DesMoines = new Vertex<String>("Des Moines");
			Vertex<String> MinneapolisStPaul = new Vertex<String>("Minneapolis/St. Paul");
			Vertex<String> StLouis = new Vertex<String>("St. Louis");
			Vertex<String> Madison = new Vertex<String>("Madison");
			Vertex<String> Milwaukee = new Vertex<String>("Milwaukee");
			Vertex<String> Chicago = new Vertex<String>("Chicago");
			Vertex<String> Detroit = new Vertex<String>("Detroit");

			myGraph.addVertex(DesMoines);
			myGraph.addVertex(MinneapolisStPaul);
			myGraph.addVertex(StLouis);
			myGraph.addVertex(Madison);
			myGraph.addVertex(Milwaukee);
			myGraph.addVertex(Chicago);
			myGraph.addVertex(Detroit);

			myGraph.addEdge(DesMoines, MinneapolisStPaul, false, 235);
			myGraph.addEdge(DesMoines, StLouis, false, 320);

			myGraph.addEdge(MinneapolisStPaul, Madison, false, 270);

			myGraph.addEdge(StLouis, Chicago, false, 270);

			myGraph.addEdge(Madison, Milwaukee, false, 80);
			myGraph.addEdge(Madison, Chicago, false, 150);

			myGraph.addEdge(Milwaukee, Chicago, false, 95);

			myGraph.addEdge(Chicago, Detroit, false, 280);
		}
		// need to use kruskal's algorithm starting from Minneapolis

		displayGraphMatrix(myGraph);
		myGraph.clear();
	}

	public static <T> void slideEight(Graph<Integer> myGraph) {
		// add all the edges and vertices, but put them in a block so i can minimize it
		if (true) {
			Vertex<Integer> zero = new Vertex<Integer>(0);
			Vertex<Integer> one = new Vertex<Integer>(1);
			Vertex<Integer> two = new Vertex<Integer>(2);
			Vertex<Integer> three = new Vertex<Integer>(3);
			Vertex<Integer> four = new Vertex<Integer>(4);
			Vertex<Integer> five = new Vertex<Integer>(5);
			Vertex<Integer> six = new Vertex<Integer>(6);
			Vertex<Integer> seven = new Vertex<Integer>(7);
			Vertex<Integer> eight = new Vertex<Integer>(8);
			Vertex<Integer> nine = new Vertex<Integer>(9);

			myGraph.addVertex(zero);
			myGraph.addVertex(one);
			myGraph.addVertex(two);
			myGraph.addVertex(three);
			myGraph.addVertex(four);
			myGraph.addVertex(five);
			myGraph.addVertex(six);
			myGraph.addVertex(seven);
			myGraph.addVertex(eight);
			myGraph.addVertex(nine);

			myGraph.addEdge(zero, one, true, 1);
			myGraph.addEdge(zero, five, true, 1);

			myGraph.addEdge(one, two, true, 1);
			myGraph.addEdge(one, three, true, 1);
			myGraph.addEdge(one, four, true, 1);
			myGraph.addEdge(one, five, true, 1);

			myGraph.addEdge(two, four, true, 1);

			myGraph.addEdge(four, three, true, 1);

			myGraph.addEdge(five, eight, true, 1);

			myGraph.addEdge(six, three, true, 1);
			myGraph.addEdge(six, eight, true, 1);

			myGraph.addEdge(seven, four, true, 1);
			myGraph.addEdge(seven, six, true, 1);
			myGraph.addEdge(seven, nine, true, 1);

			myGraph.addEdge(eight, nine, true, 1);
		}

		/*
		 * 9. List the nodes of the graph in a breadth first topological ordering. Show
		 * the steps using arrays predCount, topologicalOrder and a queue
		 */

		displayGraphMatrix(myGraph);
		myGraph.clear();
	}

	public static <T> void slideNine(Graph<String> myGraph) {
		// add all the edges and vertices, but put them in a block so i can minimize it
		if (true) {
			Vertex<String> Start = new Vertex<String>("Start");
			Vertex<String> Programming1 = new Vertex<String>("Programming 1");
			Vertex<String> DiscreteMath = new Vertex<String>("Discrete Math");
			Vertex<String> Programming2 = new Vertex<String>("Programming 2");
			Vertex<String> ComputerOrganization = new Vertex<String>("Computer Organization");
			Vertex<String> Algorithms = new Vertex<String>("Algorithms");
			Vertex<String> HighLevelLanguages = new Vertex<String>("High-Level Languages");
			Vertex<String> OperatingSystems = new Vertex<String>("Operating Systems");
			Vertex<String> TheoryOfComputation = new Vertex<String>("Theory Of Computation");
			Vertex<String> Compilers = new Vertex<String>("Compilers");
			Vertex<String> SeniorSeminar = new Vertex<String>("Senior Seminar");
			Vertex<String> End = new Vertex<String>("End");

			myGraph.addEdge(Start, DiscreteMath, true, 1);
			myGraph.addEdge(Start, Programming1, true, 1);

			myGraph.addEdge(Programming1, Programming2, true, 1);
			myGraph.addEdge(Programming1, ComputerOrganization, true, 1);

			myGraph.addEdge(DiscreteMath, Programming2, true, 1);

			myGraph.addEdge(Programming2, Algorithms, true, 1);
			myGraph.addEdge(Programming2, HighLevelLanguages, true, 1);

			myGraph.addEdge(ComputerOrganization, OperatingSystems, true, 1);

			myGraph.addEdge(Algorithms, TheoryOfComputation, true, 1);

			myGraph.addEdge(HighLevelLanguages, SeniorSeminar, true, 1);

			myGraph.addEdge(OperatingSystems, SeniorSeminar, true, 1);
			myGraph.addEdge(OperatingSystems, Compilers, true, 1);

			myGraph.addEdge(TheoryOfComputation, SeniorSeminar, true, 1);

			myGraph.addEdge(Compilers, SeniorSeminar, true, 1);

			myGraph.addEdge(SeniorSeminar, End, true, 1);
		}
		// that took forever

		/*
		 * 10. List the nodes of the graph in a breadth first topological ordering.
		 */

		displayGraphMatrix(myGraph);
		myGraph.clear();
	}

	/**
	 * Helper method in Main to display the adjacency matrix with fixed width
	 * formatting
	 */
	public static <T> void displayGraphMatrix(Graph<T> graph) {
		int[][] matrix = graph.getAdjacencyMatrix();
		ArrayList<Vertex<T>> vertices = graph.getVertexList();
		int n = vertices.size();

		// Define a fixed width for columns
		String format = "%-12s";

		// 1. Print the top header labels
		System.out.print(String.format(format, "")); // Corner gap
		for (Vertex<T> v : vertices) {
			System.out.print(String.format(format, v.getData()));
		}
		System.out.println();

		// 2. Print a divider line
		System.out.print(String.format(format, ""));
		for (int i = 0; i < n; ++i) {
			System.out.print(String.format(format, "---"));
		}
		System.out.println();

		// 3. Print rows with side labels
		for (int i = 0; i < n; i++) {
			// Print the state name on the left (fixed width)
			System.out.print(String.format("%-10s | ", vertices.get(i).getData()));

			for (int j = 0; j < n; j++) {
				// Print the number with the same fixed width
				System.out.print(String.format(format, matrix[i][j]));
			}
			System.out.println();
		}
	}
}
