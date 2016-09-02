/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 27 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programacion de Aplicaciones Interactivas
 * @title Assignment 10 - Quick Hull
 */

package controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gui.HullPoint;
import helpers.CoordsSystemTransformer;
import model.QuickHullModel;

/**
 * Clase para representar graficamente el algoritmo quickhull.
 */
public class QuickHullController {
	private QuickHullModel quickHull;							          // Algoritmo quickhull.
	private ArrayList<HullPartialSolution> resultList;			// Lista de resultados parciales a simular.
	private int index;										                  // Indice del resultado actualmente simulado.
	private int pointIndex;									                // Indice del ultimo punto pintado.
	private CoordsSystemTransformer transformer;		        // Transformador de coordenadas.
	private Map<Point,HullPoint> graphicPoints;			        // Lista de puntos a pintar.
	public static final int POINT_RADIUS = 3;				        // Radio de los puntos.
	
	/**
	 * Crea la estructura para simular el algoritmo quickhull con n puntos que se calcularan de forma aleatoria.
	 * Calculara ademas cada una de las soluciones parciales necesarias para la simulacion.
	 * @param npoints		Numero de puntos de la simulacion.
	 * @param left			Margen izquierdo donde representar la solucion.
	 * @param up			Margen superior donde representar la solucion.
	 * @param right			Margen derecho donde representar la solucion.
	 * @param bot			Margen inferior donde representar la solucion.
	 * @param ctransformer	Conversor de coordendadas.
	 */
	public QuickHullController(int npoints, int left, int up, int right, int bot,  CoordsSystemTransformer ctransformer) {
		int i = 0;
		int j = 0;
		boolean newResult = true;
		
		setTransformer(ctransformer);
		setIndex(0);
		setQuickHull(new QuickHullModel(npoints, left, up, right, bot));
		setResultList(new ArrayList<HullPartialSolution>());
		setGraphicPoints(new HashMap <Point, HullPoint>());
		getResultList().add(new HullPartialSolution(getQuickHull().getTotalPoints(), new ArrayList<Point>(), new ArrayList<Point>(), new ArrayList<Point>()));
		
		do {
			i++;
			getQuickHull().calculate(i);
			if (getQuickHull().getResult().size() != getResultList().get(j).getResult().size()) {
				getResultList().add(new HullPartialSolution(getQuickHull().getAnalyzedPoints(), getQuickHull().getUpperPoints(), getQuickHull().getLowerPoints(),getQuickHull().getResult()));
				getQuickHull().setResult(new ArrayList<Point>());
				j++;
			}
			else
				newResult = false;
			
		} while(newResult);
		
		getQuickHull().calculate(i);
		getResultList().add(new HullPartialSolution(getQuickHull().getAnalyzedPoints(), getQuickHull().getUpperPoints(), getQuickHull().getLowerPoints(),getQuickHull().getResult()));
		addGraphicPoints(getQuickHull().getTotalPoints(), Color.BLACK);
		setPointIndex(getResultList().get(0).getTotalPoints().size() - 1);
	}
	
	/**
	 * Dibuja el "fotograma".
	 * @param g1
	 */
	public void drawAll(Graphics g1) {
		Polygon polygon = new Polygon();
		Graphics2D g = (Graphics2D) g1;
		g.setStroke(new BasicStroke(2));
		if (getIndex() >= getResultList().size())
			setIndex(getResultList().size() - 1);
		
		drawPoints(g1);
		
		for (int j = getIndex() - 1; j <= getIndex(); j++) {
			g.setColor(Color.RED);
			if (j == getIndex())
				g.setColor(Color.BLACK);
			if (j >= 0) {
				for (int i = 0; i < getResultList().get(j).getResult().size(); i++) {
					polygon.addPoint((int)getResultList().get(j).getResult().get(i).getX(), (int)getResultList().get(j).getResult().get(i).getY());
				}
				g.drawPolygon(polygon);
				polygon = new Polygon();
			}
		}
	}

	/**
	 * Dibuja los puntos.
	 * @param g
	 */
	public void drawPoints(Graphics g) {
		for (int i = 0; i < getQuickHull().getTotalPoints().size(); i++) 
			getGraphicPoints().get(getQuickHull().getTotalPoints().get(i)).drawPoint(g.create());
	}
	
	/**
	 * Añade los puntos en la lista a la lista de puntos graficos con el color especificado.
	 * @param points	Puntos añadir.
	 * @param color		Color de los puntos.
	 */
	private void addGraphicPoints(ArrayList<Point> points, Color color) {
		for (int i = 0; i < points.size(); i++) {
			getGraphicPoints().put(points.get(i),new HullPoint(points.get(i), color, POINT_RADIUS, new CoordsSystemTransformer(1, 1)));
		}
	}

	public QuickHullModel getQuickHull() {
		return quickHull;
	}

	public void setQuickHull(QuickHullModel quickHull) {
		this.quickHull = quickHull;
	}

	public ArrayList<HullPartialSolution> getResultList() {
		return resultList;
	}

	public void setResultList(ArrayList<HullPartialSolution> resultList) {
		this.resultList = resultList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public CoordsSystemTransformer getTransformer() {
		return transformer;
	}

	public void setTransformer(CoordsSystemTransformer transformer) {
		this.transformer = transformer;
	}


	public int getPointIndex() {
		return pointIndex;
	}

	public void setPointIndex(int pointIndex) {
		this.pointIndex = pointIndex;
	}
	
	public Map<Point, HullPoint> getGraphicPoints() {
		return graphicPoints;
	}

	public void setGraphicPoints(Map<Point, HullPoint> graphicPoints) {
		this.graphicPoints = graphicPoints;
	}
	
	/**
	 * Calcula el color de los puntos en el siguiente "fotograma".
	 */
	public void nextStep() {
		setPointIndex(getPointIndex() + 1);
		HullPoint pointToChange;
		
		if (getPointIndex() >= getResultList().get(getIndex()).getTotalPoints().size()) {
			setPointIndex(0);
			setIndex(getIndex() + 1);
		}
		
		
		if (getIndex() >= getResultList().size()) {
			setIndex(getResultList().size() - 1);
			return;
		}
		
		pointToChange = getGraphicPoints().get(getResultList().get(getIndex()).getTotalPoints().get(getPointIndex()));
		
		if (getResultList().get(getIndex()).getUpperPoints().contains(pointToChange.getPoint())) {
			pointToChange.setColor(Color.RED);
		}
		else if (getResultList().get(getIndex()).getLowerPoints().contains(pointToChange.getPoint())) {
				pointToChange.setColor(Color.BLUE);
		}
		else
			pointToChange.setColor(Color.GRAY);
	}
	
	/**
	 * Calcula el color de los puntos para la siguiente solucion.
	 */
	public void nextSolution() {
		int actual = getIndex();
		
		while (actual == getIndex() && getIndex() < getResultList().size() - 1)
			nextStep();
		for (int i = 0; i < getResultList().get(getIndex()).getTotalPoints().size() - 1; i++)
			nextStep();		
	}
	
	 /**
   * Calcula el color de los puntos para la siguiente solucion.
   */
  public void finishAlgorithm() {
    int actual = getIndex();
    
    do {
      while (actual == getIndex() && getIndex() < getResultList().size() - 1){
        nextStep();
      }
      
      for (int i = 0; i < getResultList().get(getIndex()).getTotalPoints().size() - 1; i++) {
        nextStep();
      }
      actual++;
    } while(actual < getResultList().size() - 1);
  }
}
