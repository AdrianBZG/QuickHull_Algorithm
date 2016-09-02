/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 27 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programacion de Aplicaciones Interactivas
 * @title Assignment 10 - Quick Hull
 */

package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import helpers.CoordsSystemTransformer;

/**
 * Clase utilizada para pintar puntos 2D.
 */
public class HullPoint {
	private Point point;
	private Color color;
	private int radius;
	private CoordsSystemTransformer transformer;
	
	public HullPoint(Point p, Color c, int r, CoordsSystemTransformer ctransformer) {
		setPoint(p);
		setColor(c);
		setRadius(r);
		setTransformer(ctransformer);
	}

	public void drawPoint(Graphics g) {
		g.setColor(color);
		Point tpoint = transformer.transform(getPoint());
		g.fillOval((int)tpoint.getX() - getRadius(), (int)tpoint.getY() - getRadius(), getRadius() * 2, getRadius() * 2);
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public CoordsSystemTransformer getTransformer() {
		return transformer;
	}

	public void setTransformer(CoordsSystemTransformer transformer) {
		this.transformer = transformer;
	}	
}