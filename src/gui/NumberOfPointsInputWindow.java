/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 27 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programacion de Aplicaciones Interactivas
 * @title Assignment 10 - Quick Hull
 */

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class NumberOfPointsInputWindow extends AskForInputWindow {

  private QuickHullView quickHullPanel;

  /**
   * Creates the frame to write the new density
   * @param title
   * @param walk Panel to modify it's density
   */
  public NumberOfPointsInputWindow(String title, QuickHullView walk) {	
    super(title);

    setRandomWalk(walk);
    initializeListeners();
  }

  /**
   * Getter for the random walk panel
   * @return The random walk as a RandomWalkPanel
   */
  public QuickHullView getRandomWalkPanel() {
    return quickHullPanel;
  }

  /**
   * Setter for the random walk panel
   * @param randomWalk
   */
  public void setRandomWalk(QuickHullView randomWalk) {
    this.quickHullPanel = randomWalk;
  }
  
  /**
   * Initialize the listener for the confirmation button
   */
  private void initializeListeners() {
    final String WRONG_FORMAT_ERROR_TEXT = "Wrong format (Needs to be an integer)";
    getConfirmationButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Integer density;
        try {
          density = Integer.parseInt(getText());
          getRandomWalkPanel().setNumberOfPoints(density);
          getRandomWalkPanel().getQuickHullPanel().initialize(getRandomWalkPanel().getNumberOfPoints());
          getRandomWalkPanel().repaint();
          dispose();
        }
        catch (Exception exc) {
          JOptionPane.showMessageDialog(null, WRONG_FORMAT_ERROR_TEXT);
        }     
      }
    });
  }
}
