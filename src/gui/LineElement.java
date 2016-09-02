/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 27 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programacion de Aplicaciones Interactivas
 * @title Assignment 10 - Quick Hull
 */

package gui;

import java.awt.Point;

public class LineElement {
  private final static String LINE_ILLEGAL_ARGUMENT_TEXT = "'x' and 'y' can't be the same!";
	private Double slopeValue;     // The slope of the line
	private Double originValue;    // The origin value of the line
	
	/**
	 * Creates a line.
	 * @param newSlope The line slope
 	 * @param newOrigin Line intercept point (origin)
	 */
	public LineElement(double newSlope, double newOrigin) {
		slopeValue = newSlope;
		originValue = newOrigin;
	}
	
	/**
	 * Creates a line from 'x' to 'y'
	 * @param x First point
	 * @param y Second point
	 */
	public LineElement (Point x, Point y) throws IllegalArgumentException {
		if (x.x == y.x && x.y == y.y) {
			throw new IllegalArgumentException(LINE_ILLEGAL_ARGUMENT_TEXT);
		}
		if (x.x != y.x) {
		  slopeValue = ((double)(x.y - y.y)) / ((double)(x.x - y.x));
		  originValue = x.y - x.x * slopeValue;
		} else {
		  slopeValue = null;
		  originValue = x.getX();
		}
	}
	
	/**
	 * Evaluates the line in a given point 'x'.
	 * If 'y' is not an integer value, returns null
	 * 
	 * @return Point with a given 'x' value
	 */ 
	public Point evaluateLineWithXValue(int x) {
		double y = x * getSlope() + getOrigin();
		
		if ((int)y == y) {
			return new Point(x, (int)y);
		}
		
		return null;
	}
	
	/**
	 * Getter for the line slope
	 * @return The slope
	 */
	public Double getSlope() {
		return slopeValue;
	}
	
	/**
	 * Getter for the line origin value
	 * @return The origin value
	 */
	public Double getOrigin() {
		return originValue;
	}	
	
	 /**
   * Evalua el valor de la x.
   * @param x Valor a evaluar.
   * @return  Valor de la funcion en ese punto, null si no pertenece al dominio.
   */
  public Double evaluate(double x) {
    if (getSlope() != null) {
      return x * getSlope() + getOrigin();
    } else {
      return null;
    }
  }
  
  /**
   * Calcula la distancia de un punto a la recta.
   * @param p Punto a calcular distancia.
   * @return Distancia del punto a la recta.
   */
  public Double pointDistance(Point p) {
    if (getSlope() == null) {
      return Math.abs(getOrigin() - p.getX());
    } else {
      Double num = Math.abs(getSlope() * p.getX() - p.getY() + getOrigin());
      Double denom = Math.sqrt(getSlope() * getSlope() + 1);
      return num / denom;
    }
  }
}
