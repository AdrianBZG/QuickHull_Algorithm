/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 27 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programacion de Aplicaciones Interactivas
 * @title Assignment 10 - Quick Hull
 */

package model;

import java.awt.Point;
import java.util.ArrayList;

import gui.LineElement;
import helpers.RandomPointGenerator;

public class QuickHullModel {
	private ArrayList<Point> totalPoints;						// Total de puntos de la nube.
	private ArrayList<Point> result;							  // Puntos pertenecientes a la envolvente.
	private ArrayList<Point> upperPoints;						// Últimos puntos "superiores" que se han evaluado.
	private ArrayList<Point> lowerPoints;						// Últimos puntos "inferiores" que se han evaluado.
	private ArrayList<Point> analyzedPoints;				// Últimos puntos evaluados.
	private int recursionLimit;									    // Limite de recursion, utilizado para acotar el número de llamadas recursivas que se desea hacer.
	
	public QuickHullModel(int npoints, int left, int up, int right, int bot) {
		setTotalPoints(new ArrayList<Point>());
		setResult(new ArrayList<Point>());
		setUpperPoints(new ArrayList<Point>());
		setLowerPoints(new ArrayList<Point>());
		setAnalyzedPoints(new ArrayList<Point>());
		
		RandomPointGenerator generator = new RandomPointGenerator(left, up, right, bot);
		
		for (int i = 0; i < npoints; i++) {
			getTotalPoints().add(generator.generatePoint());
		}
	}

	public QuickHullModel() {
		setTotalPoints(new ArrayList<Point>());
		setResult(new ArrayList<Point>());
		setUpperPoints(new ArrayList<Point>());
		setLowerPoints(new ArrayList<Point>());
		setAnalyzedPoints(new ArrayList<Point>());
	}
	
	/**
	 * Calcula la envolvente total o hasta que se hayan hecho n llamadas recursivas.
	 * @param n
	 */
	public void calculate(int n) {
		int minX = 0;
		int maxX = 0;

		ArrayList<Point> upperPoints;
		ArrayList<Point> lowerPoints;
		recursionLimit = n;
		
		for (int i = 0; i < getTotalPoints().size(); i++) {
			if (getTotalPoints().get(minX).getX() > getTotalPoints().get(i).getX()) {
				minX = i;
			}
			if (getTotalPoints().get(maxX).getX() < getTotalPoints().get(i).getX()) {
				maxX = i;
			}
		}
	
		result.add(getTotalPoints().get(maxX));
	
		upperPoints = upperPoints(getTotalPoints(), getTotalPoints().get(minX), getTotalPoints().get(maxX));
		lowerPoints = lowerPoints(getTotalPoints(), getTotalPoints().get(minX), getTotalPoints().get(maxX));
		
		recursionLimit--;
		
		setUpperPoints(upperPoints);
		setLowerPoints(lowerPoints);
		setAnalyzedPoints(getTotalPoints());
		
		recursiveUpperQuickHull(upperPoints, getTotalPoints().get(minX), getTotalPoints().get(maxX));
		result.add(getTotalPoints().get(minX));
		recursiveLowerQuickHull(lowerPoints, getTotalPoints().get(minX), getTotalPoints().get(maxX));
	}
	
	/**
	 * Calcula recursivamente la parte superior.
	 * @param pointList Lista de puntos a analizar
	 * @param p1 Primer punto de la recta.
	 * @param p2 Segundo punto de la recta.
	 */
	private void recursiveUpperQuickHull(ArrayList<Point> pointList, Point p1, Point p2) {
		ArrayList<Point> upperRights = null;
		ArrayList<Point> upperLefts = null;
		LineElement lineR;
		LineElement lineL;
		
		if (pointList.size() == 0 || recursionLimit <= 0)
			return;
		else {
			recursionLimit--;
			Point farthest = farthestPoint (pointList, new LineElement(p1, p2));
			lineR = new LineElement(farthest, p2);
			lineL = new LineElement(farthest, p1);
			if (lineR.getSlope() != null)
				upperRights = upperPoints(pointList, farthest, p2);
			else {
				upperRights = rightsPoints(pointList, farthest, p2);
			}
			
			if(lineL.getSlope() != null)
				upperLefts = upperPoints(pointList, farthest, p1);
			else
				upperLefts = leftsPoints(pointList, farthest, p1);
			
			setUpperPoints(upperLefts);
			setLowerPoints(upperRights);
			setAnalyzedPoints(pointList);
			//Llamada recursiva.
			recursiveUpperQuickHull(upperRights, farthest, p2);
			
			result.add(farthest);
			
			recursiveUpperQuickHull(upperLefts, p1, farthest);
	
		}
	}
	
	/**
	 * Calcula recursivamente la parte inferior.
	 * @param pointList Lista de puntos a analizar
	 * @param p1 Primer punto de la recta.
	 * @param p2 Segundo punto de la recta.
	 */
	private void recursiveLowerQuickHull(ArrayList<Point> pointList, Point p1, Point p2) {
		ArrayList<Point> lowerRights = null;
		ArrayList<Point> lowerLefts = null;
		LineElement lineR;
		LineElement lineL;
		
		if (pointList.size() == 0 || recursionLimit <= 0)
			return;
		else {
			recursionLimit--;
			Point farthest = farthestPoint (pointList, new LineElement(p1, p2));
			lineR = new LineElement(farthest, p2);
			lineL = new LineElement(farthest, p1);
			
			if (lineR.getSlope() != null)
				lowerRights = lowerPoints(pointList, farthest, p2);
			else
				lowerRights = rightsPoints(pointList, farthest, p2);
			if (lineL.getSlope() != null)
				lowerLefts = lowerPoints(pointList, farthest, p1);
			else
				lowerLefts = leftsPoints(pointList, farthest, p1);
			
			setUpperPoints(lowerLefts);
			setLowerPoints(lowerRights);
			setAnalyzedPoints(pointList);
				
			// Recursive call
			recursiveLowerQuickHull(lowerLefts, p1, farthest);
	
			result.add(farthest);
			
			recursiveLowerQuickHull(lowerRights, farthest, p2);
		}
	}
	
	/**
	 * Calcula el punto mas lejano de la lista de puntos a la recta.
	 * @param pointList Lista de puntos.
	 * @param recta
	 * @return
	 */
	private Point farthestPoint(ArrayList<Point> pointList, LineElement recta) {
		int farthest = 0;
		for (int i = 0; i < pointList.size(); i++) {
			if (recta.pointDistance(pointList.get(farthest)) < recta.pointDistance(pointList.get(i)))
					farthest = i;
		}
		
		return pointList.get(farthest);
	}
	
	/**
	 * Calcula aquellos puntos que estan por arriba de la recta formada por p1 y p2.
	 * @param totalPointList 	Lista de puntos.
	 * @param p1				Primer punto.
	 * @param p2				Segundo Punto.
	 * @return
	 */
	private ArrayList<Point> upperPoints(ArrayList<Point> totalPointList, Point p1, Point p2) {
		ArrayList<Point> pointList = new ArrayList<Point>();
		LineElement recta = new LineElement(p1, p2);
		Boolean eval;
		for (int i = 0; i < totalPointList.size(); i++) {
			eval = pointIsOverLine(totalPointList.get(i), recta);
			if (eval!= null && eval)
				pointList.add(totalPointList.get(i));
		}
		
		return pointList;
	}
	
	/**
	 * Calcula aquellos puntos que estan por debajo de la recta formada por p1 y p2.
	 * @param totalPointList 	Lista de puntos.
	 * @param p1				Primer punto.
	 * @param p2				Segundo Punto.
	 * @return
	 */
	private ArrayList<Point> lowerPoints(ArrayList<Point> totalPointList, Point p1, Point p2) {
		ArrayList<Point> pointList = new ArrayList<Point>();
		LineElement recta = new LineElement(p1, p2);
		Boolean eval;
		for (int i = 0; i < totalPointList.size(); i++) {
			eval = pointIsOverLine(totalPointList.get(i), recta);
			if (eval!= null && !eval)
				pointList.add(totalPointList.get(i));
		}
		
		return pointList;
	}
	
	/**
	 * Calcula aquellos puntos que estan a la izquierda de la recta formada por p1 y p2, solo para cuando la recta es
	 *  de la forma x = q.
	 * @param totalPointList 	Lista de puntos.
	 * @param p1				Primer punto.
	 * @param p2				Segundo Punto.
	 * @return
	 */
	private ArrayList<Point> leftsPoints(ArrayList<Point> totalPointList, Point p1, Point p2) {
		ArrayList<Point> pointList = new ArrayList<Point>();
		LineElement recta = new LineElement(p1, p2);
		
		for (int i = 0; i < totalPointList.size(); i++) {
			if (totalPointList.get(i).getX() < recta.getOrigin())
				pointList.add(totalPointList.get(i));
		}
		
		return pointList;
	}
	
	/**
	 * Calcula aquellos puntos que estan a la derecha de la recta formada por p1 y p2, solo para cuando la recta es
	 *  de la forma x = q.
	 * @param totalPointList 	Lista de puntos.
	 * @param p1				Primer punto.
	 * @param p2				Segundo Punto.
	 * @return
	 */
	private ArrayList<Point> rightsPoints(ArrayList<Point> totalPointList, Point p1, Point p2) {
		ArrayList<Point> pointList = new ArrayList<Point>();
		LineElement recta = new LineElement(p1, p2);
		
		for (int i = 0; i < totalPointList.size(); i++) {
			if (totalPointList.get(i).getX() > recta.getOrigin())
				pointList.add(totalPointList.get(i));
		}
		
		return pointList;
	}
	
	/**
	 * True si el punto está por encima de la recta.
	 * @param p
	 * @param linea
	 * @return
	 */
	private Boolean pointIsOverLine(Point p, LineElement linea){
		Double evaluation = linea.evaluate(p.getX());
		try {
			evaluation = (double) Math.round(evaluation);	
		}
		catch(Exception e) {
			return null;
		}
		
		if (evaluation == null || p.getY() == evaluation) {
			return null;
		}	else if (evaluation < p.getY()) {
			return true;
		}	else {
			return false;
		}
	}

	public ArrayList<Point> getResult() {
		return result;
	}

	public void setResult(ArrayList<Point> result) {
		this.result = result;
	}

	public ArrayList<Point> getTotalPoints() {
		return totalPoints;
	}

	private void setTotalPoints(ArrayList<Point> totalPoints) {
		this.totalPoints = totalPoints;
	}

	public ArrayList<Point> getUpperPoints() {
		return upperPoints;
	}

	private void setUpperPoints(ArrayList<Point> upperPoints) {
		this.upperPoints = upperPoints;
	}

	public ArrayList<Point> getLowerPoints() {
		return lowerPoints;
	}

	private void setLowerPoints(ArrayList<Point> lowerPoints) {
		this.lowerPoints = lowerPoints;
	}

	public ArrayList<Point> getAnalyzedPoints() {
		return analyzedPoints;
	}

	private void setAnalyzedPoints(ArrayList<Point> analyzedPoints) {
		this.analyzedPoints = analyzedPoints;
	}	
}
