/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 27 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programacion de Aplicaciones Interactivas
 * @title Assignment 10 - Quick Hull
 */

package helpers;

import java.awt.Point;

/**
 * Clase utilizada para pasar de un sistema de coordenadas a otro.
 */
public class CoordsSystemTransformer {
	private int rowSize;
	private int colSize;
	
	public CoordsSystemTransformer(int rowsize, int colsize) {
		setRowSize(rowsize);
		setColSize(colsize);
	}
	
	/**
	 * Transforma un punto de un sistema a otro.
	 */
	public Point transform (Point point) {
		return new Point ((int)(point.getX() * getColSize()), (int)(point.getY() * getRowSize()));
	}

	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public int getColSize() {
		return colSize;
	}

	public void setColSize(int colSize) {
		this.colSize = colSize;
	}	
}
