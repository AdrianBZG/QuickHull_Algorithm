/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 27 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programacion de Aplicaciones Interactivas
 * @title Assignment 10 - Quick Hull
 */

package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import controller.QuickHullController;
import helpers.CoordsSystemTransformer;

/**
 * Panel para dibujar el comportiamiento del algoritmo quickhull.
 */
public class HullPanel extends JPanel {
	
	private QuickHullController quickHull;		// Clase que contiente el algoritmo junto con las esctructuras que definen el problema.

	public HullPanel () {		
	}
	
	/**
	 * Inicializa el problema.
	 * @param npoints
	 */
	public void initialize(int npoints) {
		
		setQuickHull(new QuickHullController(npoints, (int)(this.getWidth() * 0.01), (int)(this.getHeight() * 0.01), 
					(int)(this.getWidth() * 0.99), (int)(this.getHeight() * 0.95),  new CoordsSystemTransformer(1, 1)));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		getQuickHull().drawAll(g.create());
	}
	
	/**
	 * Muestra el siguiente "fotograma" de la simulacion.
	 */
	public void nextStep() {
		getQuickHull().nextStep();
		repaint();
	}
	
	/**
	 * Muestra la siguiente solucion pintando de golpe todos los puntos.
	 */
	public void nextSolution() {
		getQuickHull().nextSolution();
		repaint();
	}
	
	 /**
   * Muestra la siguiente solucion pintando de golpe todos los puntos.
   */
  public void finishAlgorithm() {
    getQuickHull().finishAlgorithm();
    repaint();
  }

	public QuickHullController getQuickHull() {
		return quickHull;
	}

	public void setQuickHull(QuickHullController quickHull) {
		this.quickHull = quickHull;
	}	
}
	