/**
 * 
 */
package aod.lab5.graph;

import java.awt.Color;

/**
 * Representerar ett hörn (nod) i en graf.
 * Ett hörn har en position (x,y), en identifierare och en färg.
 * 
 * @param <T> typen av identifierare för hörnet
 */
public class Vertex<T> {
	
	/** Hörnets unika identifierare. */
	private T info;
	
	/** Hörnets x-koordinat. */
	private double x;
	
	/** Hörnets y-koordinat. */
	private double y;
	
	/** Hörnets färg för visuell representation. */
	private Color color;
	
	/**
	 * Skapar ett nytt hörn med given position och identifierare.
	 * Standardfärgen är svart.
	 * 
	 * @param x x-koordinat för hörnet
	 * @param y y-koordinat för hörnet
	 * @param info identifierare för hörnet
	 */
	public Vertex(double x, double y, T info) {
		this.x = x;
		this.y = y;
		this.info = info;
		this.color = Color.BLACK;
	}
	
	/**
	 * Returnerar hörnets identifierare.
	 * 
	 * @return identifieraren
	 */
	public T getInfo() {
		return info;
	}
	
	/**
	 * Returnerar hörnets x-koordinat.
	 * 
	 * @return x-koordinaten
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Returnerar hörnets y-koordinat.
	 * 
	 * @return y-koordinaten
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Returnerar hörnets färg.
	 * 
	 * @return färgen
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Sätter hörnets färg.
	 * 
	 * @param color den nya färgen
	 */
	public void setColor(Color color) {
		this.color = color;
	}
}