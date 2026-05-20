package aod.lab5.graph;

import java.awt.Color;

/**
 * Representerar en kant som förbinder två hörn i en graf.
 * En kant har ett starthörn, ett sluthörn, en beräknad sträcka
 * och en visuell färgattribut för rendering.
 *
 * @param <T> typen av data som lagras i hörnen som denna kant förbinder
 * @author Omar hussein mohamed
 */
public class Edge<T> {
    
    /**
     * Startpunkten (hörnet) för denna kant.
     */
    private Vertex<T> from;
    
    /**
     * Slutpunkten (hörnet) för denna kant.
     */
    private Vertex<T> to;
    
    /**
     * Det  avståndet mellan de två hörnen.
     */
    private double distance;
    
    /**
     * Färgen som används för att visuellt representera denna kant.
     */
    private Color color;
    
    /**
     * Skapar en ny kant mellan två hörn.
     * Avståndet beräknas automatiskt baserat på hörnens positioner,
     * och standardfärgen sätts till grå.
     *
     * @param from starthörnet (kan inte vara null)
     * @param to   sluthörnet (kan inte vara null)
     */
    public Edge(Vertex<T> from, Vertex<T> to) {
        this.from = from;
        this.to = to;
        this.distance = beraknaAvstand(from, to);
        this.color = Color.GRAY;
    }
    
    /**
     * Beräknar det euklidiska avståndet mellan två hörn.
     *
     * @param a det första hörnet
     * @param b det andra hörnet
     * @return det euklidiska avståndet beräknat som sqrt((x2-x1)² + (y2-y1)²)
     */
    private double beraknaAvstand(Vertex<T> a, Vertex<T> b) {
        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    /**
     * Returnerar starthörnet för denna kant.
     *
     * @return starthörnet
     */
    public Vertex<T> getFrom() {
        return from;
    }
    
    /**
     * Returnerar sluthörnet för denna kant.
     *
     * @return sluthörnet
     */
    public Vertex<T> getTo() {
        return to;
    }
    
    /**
     * Returnerar avståndet mellan de två hörnen.
     *
     * @return kantens euklidiska avstånd
     */
    public double getDistance() {
        return distance;
    }
    
    /**
     * Returnerar kantens aktuella färg.
     *
     * @return kantens visningsfärg
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Anger visningsfärgen för denna kant.
     *
     * @param color den nya färgen för denna kant
     */
    public void setColor(Color color) {
        this.color = color;
    }
}