/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 27 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programacion de Aplicaciones Interactivas
 * @title Assignment 10 - Quick Hull
 */

package logic;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import gui.QuickHullView;

public class Main {
  public static void main(String[] args) {
    final String WRONG_NUMBER_OF_ARGUMENTS_TEXT = "Wrong number of arguments; correct usage: java Main NUMBER_OF_POINTS DELAY_IN_MS";
    final String WINDOW_TITLE = "QuickHull Algorithm";
    final int WINDOW_WIDTH = 500;
    final int WINDOW_HEIGHT = 500;
    Integer numberOfPoints;
    Integer drawDelay;

    try {
      numberOfPoints = new Integer(args[0]);
      drawDelay = new Integer(args[1]);
    }
    catch (Exception e) {
      System.err.println(WRONG_NUMBER_OF_ARGUMENTS_TEXT);
      return;
    }
    
    // Create a frame
    JFrame frame = new JFrame(WINDOW_TITLE);

    // Create an instance of the applet
    QuickHullView applet = new QuickHullView(numberOfPoints, drawDelay);
    
    // Get parameters from the command line
    applet.setStandalone(true);
    
    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);
    
    // Invoke applet's init method
    applet.init();
    applet.start();

    // Settings for the frame
    frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);                          // Center the frame
    
    // Display the frame
    frame.setVisible(true);
  }
}