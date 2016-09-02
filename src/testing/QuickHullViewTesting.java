/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 27 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programacion de Aplicaciones Interactivas
 * @title Assignment 10 - Quick Hull
 */

package testing;

import static org.assertj.swing.core.MouseClickInfo.leftButton;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.awt.BorderLayout;

import gui.QuickHullView;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuickHullViewTesting {
  final String WINDOW_TITLE = "QuickHull Algorithm";
  final int WINDOW_WIDTH = 500;
  final int WINDOW_HEIGHT = 500;
  final int DEFAULT_NUMBER_OF_POINTS = 100;
  final int DEFAULT_DELAY_TO_DRAW = 1;
  final int TIMES_TO_CLICK_A_BUTTON = 10;                                // Times to click a button, used for testing
  private FrameFixture window;                                           // The window that AssertJ Swing will use
  private QuickHullView view = new QuickHullView(DEFAULT_NUMBER_OF_POINTS, DEFAULT_DELAY_TO_DRAW);                                            // The random walk view
  
  @Before
  public void setup() {    
    // Create a frame
    JFrame frame = new JFrame(WINDOW_TITLE);

    // Create an instance of the applet
    QuickHullView applet = new QuickHullView(DEFAULT_NUMBER_OF_POINTS, DEFAULT_DELAY_TO_DRAW);
    
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
    window = new FrameFixture(frame);                                     // Start the FrameFixture for AssertJ Swing testing
  }
  
  /**
   * This test verifies that the button exists
   */
  @Test
  public void testStartStopButtonExistsInKeyboard() { 
    assertThat(view.getInicializar(), instanceOf(JButton.class));
  }
  
  /**
   * This test verifies that the button exists
   */
  @Test
  public void testFinishButtonExistsInKeyboard() { 
    assertThat(view.getStartStop(), instanceOf(JButton.class));
  }
  
  /**
   * This test verifies that the button exists
   */
  @Test
  public void testRandomColorButtonExistsInKeyboard() { 
    assertThat(view.getSiguiente(), instanceOf(JButton.class));
  }
  
  /**
   * This test verifies that the button exists
   */
  @Test
  public void testSelectColorButtonExistsInKeyboard() { 
    assertThat(view.getFinishButton(), instanceOf(JButton.class));
  }
  
  /**
   * This test verifies that the button exists
   */
  @Test
  public void testChangeDelayButtonExistsInKeyboard() { 
    assertThat(view.getChangeDelayButton(), instanceOf(JButton.class));
  }
  
  /**
   * This test verifies that the button exists
   */
  @Test
  public void testChangeDensityButtonExistsInKeyboard() { 
    assertThat(view.getChangeNumberOfPointsButton(), instanceOf(JButton.class));
  }
  
  /**
   * This test verifies that all buttons are clickable, this implicitily verifies that:
   * - The buttons exist
   * - The buttons are working
   */
  @Test
  public void testButtonsAreClickable() throws Exception {
    final String INITIALIZE_BUTTON_TEXT = "Initialize";
    final String FINISH_BUTTON_TEXT = "Finish";
    final String CHANGE_DELAY_BUTTON_TEXT = "Change delay";
    final String CHANGE_NUM_OF_POINTS_BUTTON_TEXT = "Change num. of Points";
    final String EXECUTE_BUTTON_TEXT = "Execute";
    final String NEXT_STEP_BUTTON_TEXT = "Next step";
    final int WAIT_TIME_BETWEEN_BUTTON_CLICKING = 1000;         // Used to wait between button clicking, fix needed for AssertJ
    
    window.button(INITIALIZE_BUTTON_TEXT).click(leftButton().times(1));
    Thread.sleep(WAIT_TIME_BETWEEN_BUTTON_CLICKING);
    window.button(FINISH_BUTTON_TEXT).click(leftButton().times(TIMES_TO_CLICK_A_BUTTON));
    Thread.sleep(WAIT_TIME_BETWEEN_BUTTON_CLICKING);
    window.button(CHANGE_DELAY_BUTTON_TEXT).click(leftButton().times(TIMES_TO_CLICK_A_BUTTON));
    Thread.sleep(WAIT_TIME_BETWEEN_BUTTON_CLICKING);
    window.button(CHANGE_NUM_OF_POINTS_BUTTON_TEXT).click(leftButton().times(TIMES_TO_CLICK_A_BUTTON));
    Thread.sleep(WAIT_TIME_BETWEEN_BUTTON_CLICKING);
    window.button(EXECUTE_BUTTON_TEXT).click(leftButton().times(TIMES_TO_CLICK_A_BUTTON));
    Thread.sleep(WAIT_TIME_BETWEEN_BUTTON_CLICKING);
    window.button(NEXT_STEP_BUTTON_TEXT).click(leftButton().times(TIMES_TO_CLICK_A_BUTTON));
    Thread.sleep(WAIT_TIME_BETWEEN_BUTTON_CLICKING);
  }
  
  /**
   * We clean the window element (the FrameFixture), it's a good practice
   */
  @After
  public void clear() {
    window.cleanUp();
  }
}
