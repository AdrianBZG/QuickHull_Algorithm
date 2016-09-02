/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 27 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programacion de Aplicaciones Interactivas
 * @title Assignment 10 - Quick Hull
 */

package controller;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Clase que representa soluciones parciales del algoritmo quickhull.
 */
public class HullPartialSolution {
	private ArrayList<Point> totalPoints;		// Total de puntos analizados en esta solucion.
	private ArrayList<Point> upperPoints;		// Puntos superiores.
	private ArrayList<Point> lowerPoints;		// Puntos inferiores.
	private ArrayList<Point> result;			  // Resultado.
	
	public HullPartialSolution(ArrayList<Point> totals, ArrayList<Point> uppers, ArrayList<Point> lowers, ArrayList<Point> result) {
		setTotalPoints(totals);
		setUpperPoints(uppers);
		setLowerPoints(lowers);
		setResult(result);
	}

	public ArrayList<Point> getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(ArrayList<Point> totalPoints) {
		this.totalPoints = totalPoints;
	}

	public ArrayList<Point> getUpperPoints() {
		return upperPoints;
	}

	public void setUpperPoints(ArrayList<Point> upperPoints) {
		this.upperPoints = upperPoints;
	}

	public ArrayList<Point> getLowerPoints() {
		return lowerPoints;
	}

	public void setLowerPoints(ArrayList<Point> lowerPoints) {
		this.lowerPoints = lowerPoints;
	}

	public ArrayList<Point> getResult() {
		return result;
	}

	public void setResult(ArrayList<Point> result) {
		this.result = result;
	}
}
