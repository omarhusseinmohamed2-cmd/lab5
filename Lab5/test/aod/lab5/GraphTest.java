package aod.lab5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aod.lab5.graph.Edge;
import aod.lab5.graph.Graph;

/**
 * JUnit-tester för klassen {@link Graph}.
 * Testar alla publika metoder med minst två testfall vardera.
 *
 * @author omar hussein mohamed 
 */
public class GraphTest {

    /** Grafen som används i varje test. */
    private Graph<String> graph;

    /**
     * Skapar en ny graph och lägger till tre vertices och två kanter
     * innan varje testmetod körs.
     */
    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
        graph.addVertex(0, 0, "A");
        graph.addVertex(3, 4, "B");
        graph.addVertex(6, 0, "C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
    }

    // --- addVertex ---

    /**
     * Kontrollerar att addVertex ökar antalet vertices korrekt.
     */
    @Test
    public void testAddVertex_okasAntal() {
        assertEquals(3, graph.numberOfVertices());
    }

    /**
     * Kontrollerar att en vertex med samma identifierare inte kan läggas till igen.
     */
    @Test
    public void testAddVertex_dubblett_laggssInteIn() {
        graph.addVertex(99, 99, "A"); // "A" finns redan
        assertEquals(3, graph.numberOfVertices());
    }

    // --- addEdge ---

    /**
     * Kontrollerar att addEdge ökar antalet kanter korrekt.
     */
    @Test
    public void testAddEdge_okasAntal() {
        assertEquals(2, graph.numberOfEdges());
    }

    /**
     * Kontrollerar att en kant skapas i båda riktningar (oriktad kant).
     */
    @Test
    public void testAddEdge_badariktningar() {
        List<Edge<String>> kanterFranA = graph.getEdges("A");
        List<Edge<String>> kanterFranB = graph.getEdges("B");

        // A->B ska finnas
        boolean abFinns = kanterFranA.stream().anyMatch(e -> e.getTo().getInfo().equals("B"));
        // B->A ska också finnas
        boolean baFinns = kanterFranB.stream().anyMatch(e -> e.getTo().getInfo().equals("A"));

        assertTrue(abFinns);
        assertTrue(baFinns);
    }

    // --- getEdges ---

    /**
     * Kontrollerar att getEdges returnerar rätt antal kanter för en vertex.
     */
    @Test
    public void testGetEdges_rattAntal() {
        // B har kanter till A och C
        assertEquals(2, graph.getEdges("B").size());
    }

    /**
     * Kontrollerar att getEdges returnerar en tom lista för en vertex utan kanter.
     */
    @Test
    public void testGetEdges_ingenKant_tomLista() {
        graph.addVertex(10, 10, "D"); // D har inga kanter
        assertEquals(0, graph.getEdges("D").size());
    }

    // --- getAllVertices ---

    /**
     * Kontrollerar att getAllVertices returnerar alla vertices.
     */
    @Test
    public void testGetAllVertices_rattStorlek() {
        assertEquals(3, graph.getAllVertices().size());
    }

    /**
     * Kontrollerar att getAllVertices inte är null.
     */
    @Test
    public void testGetAllVertices_inteNull() {
        assertNotNull(graph.getAllVertices());
    }

    // --- remove ---

    /**
     * Kontrollerar att remove minskar antalet vertices.
     */
    @Test
    public void testRemove_minskarVertices() {
        graph.remove("A");
        assertEquals(2, graph.numberOfVertices());
    }

    /**
     * Kontrollerar att kanter kopplade till borttagen vertex försvinner.
     */
    @Test
    public void testRemove_tasBortKanter() {
        graph.remove("A");
        // B ska inte längre ha en kant till A
        boolean baFinns = graph.getEdges("B").stream()
                .anyMatch(e -> e.getTo().getInfo().equals("A"));
        assertFalse(baFinns);
    }

    // --- numberOfEdges ---

    /**
     * Kontrollerar att numberOfEdges stämmer efter flera addEdge.
     */
    @Test
    public void testNumberOfEdges_efterFlera() {
        graph.addEdge("A", "C");
        assertEquals(3, graph.numberOfEdges());
    }

    /**
     * Kontrollerar att numberOfEdges är noll för en tom graf.
     */
    @Test
    public void testNumberOfEdges_tomGraf() {
        Graph<String> tomGraf = new Graph<>();
        assertEquals(0, tomGraf.numberOfEdges());
    }

    // --- numberOfVertices ---

    /**
     * Kontrollerar att numberOfVertices är noll för en tom graf.
     */
    @Test
    public void testNumberOfVertices_tomGraf() {
        Graph<String> tomGraf = new Graph<>();
        assertEquals(0, tomGraf.numberOfVertices());
    }

    /**
     * Kontrollerar att numberOfVertices ökar för varje tillagd vertex.
     */
    @Test
    public void testNumberOfVertices_okasStegvis() {
        Graph<String> g = new Graph<>();
        g.addVertex(0, 0, "X");
        assertEquals(1, g.numberOfVertices());
        g.addVertex(1, 1, "Y");
        assertEquals(2, g.numberOfVertices());
    }

    // --- Edge distance ---

    /**
     * Kontrollerar att kanten A-B har korrekt euklidisk distans (3-4-5-triangeln = 5.0).
     */
    @Test
    public void testEdge_korrektAvstand() {
        Edge<String> kant = graph.getEdges("A").get(0); // A->B
        assertEquals(5.0, kant.getDistance(), 0.001);
    }

    /**
     * Kontrollerar att distansen alltid är positiv.
     */
    @Test
    public void testEdge_avstandPositivt() {
        for (Edge<String> e : graph.getEdges("B")) {
            assertTrue(e.getDistance() > 0);
        }
    }
}
