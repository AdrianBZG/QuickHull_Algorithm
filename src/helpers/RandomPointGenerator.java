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
import java.util.Random;

/**
 * Clase generadora de puntos de forma aleatoria entre un rango fijado.
 */
public class RandomPointGenerator {
	Random engine;		// Random number generator
	int leftBound;		//
	int upperBound;		//	Ranges for  the coordinates
	int rightBound;		//
	int bottomBound;	//
	
	public RandomPointGenerator(int left, int up, int right, int bot) {
		setEngine(new Random());
		setLeftBound(left);
		setUpperBound(up);
		setRightBound(right);
		setBottomBound(bot);
		
	}

	/**
	 * Genera un punto de forma aleatoria.
	 */
	public Point generatePoint() {
		int min = getLeftBound();
		int max = getRightBound();
		
		int x = getEngine().nextInt((max - min) + 1) + min;
		min = getUpperBound();
		max = getBottomBound();
		int y = getEngine().nextInt((max - min) + 1) + min;
		
		return new Point(x, y);
	}

	public int getLeftBound() {
		return leftBound;
	}

	public void setLeftBound(int leftBound) {
		this.leftBound = leftBound;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int topBound) {
		this.upperBound = topBound;
	}

	public int getRightBound() {
		return rightBound;
	}

	public void setRightBound(int rightBound) {
		this.rightBound = rightBound;
	}

	public int getBottomBound() {
		return bottomBound;
	}

	public void setBottomBound(int bottomBound) {
		this.bottomBound = bottomBound;
	}

	public Random getEngine() {
		return engine;
	}

	public void setEngine(Random engine) {
		this.engine = engine;
	}
}