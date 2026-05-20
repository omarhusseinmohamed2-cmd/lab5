package aod.lab5.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * En graf som innehåller hörn (Vertex) och kanter (Edge).
 * 
 * @param <T> typen av identifierare för hörnen
 */
public class Graph<T> implements GraphInterface<T> {

	/** Antalet hörn i grafen. */
	private int nVertices;

	/** Antalet kanter i grafen. */
	private int nEdges;

	/** Lagrar alla vertices, sökbara med sin unika identifierare. */
	private HashMap<T, Vertex<T>> vertices;

	/** Lagrar alla kanter för varje hörn. */
	private HashMap<T, ArrayList<Edge<T>>> edges;

	/**
	 * Skapar en tom graf.
	 */
	public Graph() {
		this.nVertices = 0;
		this.nEdges = 0;
		this.vertices = new HashMap<>();
		this.edges = new HashMap<>();
	}

	/**
	 * Returnerar alla hörn i grafen.
	 * 
	 * @return lista med alla hörn
	 */
	@Override
	public List<Vertex<T>> getAllVertices() {
		return new ArrayList<>(vertices.values());
	}

	/**
	 * Returnerar alla kanter från ett specifikt hörn.
	 * 
	 * @param info identifieraren för hörnet
	 * @return lista med kanter från hörnet
	 */
	@Override
	public List<Edge<T>> getEdges(T info) {
		// Om nyckeln inte finns returneras en tom lista
		return edges.getOrDefault(info, new ArrayList<>());
	}

	/**
	 * Lägger till ett nytt hörn i grafen. Om ett hörn med samma info redan finns
	 * läggs ingen ny till.
	 * 
	 * @param x    x-koordinat för hörnet
	 * @param y    y-koordinat för hörnet
	 * @param info identifierare för hörnet
	 */
	@Override
	public void addVertex(double x, double y, T info) {
		// Kontrollera att identifieraren inte redan finns
		if (!vertices.containsKey(info)) {

			Vertex<T> nyVertex = new Vertex<>(x, y, info);
			vertices.put(info, nyVertex);
			edges.put(info, new ArrayList<>());
			nVertices++;
		}
	}

	/**
	 * Lägger till en kant mellan två hörn. Kanten är oriktad och lagras i båda
	 * riktningarna.
	 * 
	 * @param infoA identifierare för första hörnet
	 * @param infoB identifierare för andra hörnet
	 */
	@Override
	public void addEdge(T infoA, T infoB) {
		Vertex<T> vertexA = vertices.get(infoA);
		Vertex<T> vertexB = vertices.get(infoB);

		// Båda vertices måste existera
		if (vertexA == null || vertexB == null) {
			return;
		}

		// Skapa en kant i varje riktning (oriktad kant)
		Edge<T> kantAB = new Edge<>(vertexA, vertexB);
		Edge<T> kantBA = new Edge<>(vertexB, vertexA);

		edges.get(infoA).add(kantAB);
		edges.get(infoB).add(kantBA);

		nEdges++;
	}

	/**
	 * Tar bort ett hörn och alla kanter som är kopplade till det.
	 * 
	 * @param info identifieraren för hörnet som ska tas bort
	 */
	@Override
	public void remove(T info) {
		if (!vertices.containsKey(info)) {
			return;
		}

		// Ta bort alla kanter från andra vertices som pekar mot denna vertex
		for (T nyckel : edges.keySet()) {
			edges.get(nyckel).removeIf(edge -> edge.getTo().getInfo().equals(info));
		}

		// Ta bort vertexens egna kanter och sedan själva vertexen
		edges.remove(info);
		vertices.remove(info);

		nVertices--;
		// Räkna om antalet kanter korrekt
		nEdges = raknaKanter();
	}

	/**
	 * Hjälpmetod som räknar det totala antalet oriktade kanter i grafen. Eftersom
	 * varje oriktad kant lagras som två riktade kanter, divideras summan av alla
	 * kantlistors storlek med 2.
	 *
	 * @return antal oriktade kanter
	 */
	private int raknaKanter() {
		int summa = 0;
		for (ArrayList<Edge<T>> lista : edges.values()) {
			summa += lista.size();
		}
		return summa / 2;
	}

	/**
	 * Returnerar antalet kanter i grafen.
	 * 
	 * @return antal kanter
	 */
	@Override
	public int numberOfEdges() {
		return nEdges;
	}

	/**
	 * Returnerar antalet hörn i grafen.
	 * 
	 * @return antal hörn
	 */
	@Override
	public int numberOfVertices() {
		return nVertices;
	}
}