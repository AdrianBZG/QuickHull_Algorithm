/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 27 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programacion de Aplicaciones Interactivas
 * @title Assignment 10 - Quick Hull
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Clase con la ventana para mostrar la simulacion del quickhull.
 */
public class QuickHullView extends JApplet {
  final String INITIALIZE_BUTTON_TEXT = "Initialize";
  final String FINISH_BUTTON_TEXT = "Finish";
  final String CHANGE_DELAY_BUTTON_TEXT = "Change delay";
  final String CHANGE_NUM_OF_POINTS_BUTTON_TEXT = "Change num. of Points";
  final String EXECUTE_BUTTON_TEXT = "Execute";
  final String PAUSE_BUTTON_TEXT = "Pause";
  final String NEXT_STEP_BUTTON_TEXT = "Next step";
  
	private JPanel buttonsPanel;							    // Panel with the buttons
	private JButton initializeButton;							// Button to initialize the hull points
	private JButton startStopButton;							// Button to stop or continue the algorithm
	private JButton nextStepButton;								// Button to show the next solution step
	private JButton finishButton;
	private JButton changeNumberOfPointsButton;
	private JButton changeDelayButton;
	private HullPanel quickHullPanel;				      // Panel where the hull'll be drawn
	private Timer algorithmTimer;								  // Timer used for the animation
	private int numberOfPoints = 500;							// Number of points
	private int algorithmDelay = 10;							// Delay for the simulation
	private boolean isStandalone = false;
	
	public QuickHullView(int npoints, int delay) {
	  initializeData(npoints, delay);
	  initializeButtons();
	  initializeListeners();
	  initializePanels();
	  initializeSettings();
	}
	
	private void initializeData(int newNumOfPoints, int newDelay) {
    setNumberOfPoints(newNumOfPoints);
    setDelay(newDelay);
	}
	
	private void initializeButtons() {	  
	  setButtonsPanel(new JPanel());
    setInicializar(new JButton(INITIALIZE_BUTTON_TEXT));
    setFinishButton(new JButton(FINISH_BUTTON_TEXT));
    setChangeDelayButton(new JButton(CHANGE_DELAY_BUTTON_TEXT));
    setChangeNumberOfPointsButton(new JButton(CHANGE_NUM_OF_POINTS_BUTTON_TEXT));
    setStartStop(new JButton(EXECUTE_BUTTON_TEXT));
    setQuickHullPanel(new HullPanel());
    setTemporizador(new Timer(getDelay(), new TimerHandler()));
    setSiguiente(new JButton(NEXT_STEP_BUTTON_TEXT));
	}
	
	private void initializeListeners() {
	  getInicializar().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getQuickHullPanel().initialize(getNumberOfPoints());
        pauseAlgorithm();
        repaint();
      }      
    });
	      
    getStartStop().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (getTemporizador().isRunning()) {
          pauseAlgorithm();
        }
        else {
          continueAlgorithm();
        }
      }      
    });
    
    getSiguiente().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        pauseAlgorithm();
        getQuickHullPanel().nextSolution();
      }      
    });
    
    getFinishButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        pauseAlgorithm();
        getQuickHullPanel().finishAlgorithm();
      }      
    });
    
    getChangeDelayButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        final String DELAY_WINDOW_TITLE = "Change the draw delay of the algorithm";
        
        pauseAlgorithm();
        DelayInputWindow delayWindow = new DelayInputWindow(DELAY_WINDOW_TITLE, getView());
        delayWindow.setLocationRelativeTo(null); 
        delayWindow.setVisible(true);
      }      
    });
    
    getChangeNumberOfPointsButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        final String NUMBER_OF_POINTS_WINDOW_TITLE = "Change the number of points";
        
        pauseAlgorithm();
        NumberOfPointsInputWindow numberOfPointsWindow = new NumberOfPointsInputWindow(NUMBER_OF_POINTS_WINDOW_TITLE, getView());
        numberOfPointsWindow.setLocationRelativeTo(null); 
        numberOfPointsWindow.setVisible(true);
      }      
    });
  }

	private void initializePanels() {
	  final int BUTTONS_PANEL_NUMBER_OF_COLUMNS = 5;
	  
	  getButtonsPanel().setLayout(new GridLayout(1, BUTTONS_PANEL_NUMBER_OF_COLUMNS));    
    getButtonsPanel().add(getInicializar());
    getButtonsPanel().add(getStartStop());
    getButtonsPanel().add(getSiguiente());
    getButtonsPanel().add(getFinishButton());
    getButtonsPanel().add(getChangeDelayButton());
    getButtonsPanel().add(getChangeNumberOfPointsButton());
	}

	private void initializeSettings() {
	  //final String WINDOW_TITLE = "QuickHull Algorithm";
	  final int WINDOW_WIDTH = 500;
	  final int WINDOW_HEIGHT = 500;
	  
	  getQuickHullPanel().setBackground(Color.WHITE);	  
    add(getQuickHullPanel());
    add(getButtonsPanel(), BorderLayout.SOUTH);
    addComponentListener(new WindowHandler());
    
    // Settings for the frame
    //setTitle(WINDOW_TITLE);
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //setLocationRelativeTo(null);                          // Center the frame
	}
	
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		getQuickHullPanel().initialize(getNumberOfPoints());
	}
	
	public void showView() {
	  setVisible(true);
	}
	
	public void setStandalone(boolean isStandalone) {
	  this.isStandalone = isStandalone;
	}
	
	public boolean getStandalone() {
	  return isStandalone;
	}
	
	public HullPanel getQuickHullPanel() {
		return quickHullPanel;
	}

	public JButton getSiguiente() {
		return nextStepButton;
	}

	public void setSiguiente(JButton siguiente) {
		this.nextStepButton = siguiente;
		this.nextStepButton.setName(NEXT_STEP_BUTTON_TEXT);
	}
	
	public JButton getFinishButton() {
	  return finishButton;
	}

	public void setFinishButton(JButton finishButton) {
	  this.finishButton = finishButton;
	  this.finishButton.setName(FINISH_BUTTON_TEXT);
	}
	
	 public JButton getChangeDelayButton() {
    return changeDelayButton;
  }

  public void setChangeDelayButton(JButton changeDelayButton) {
    this.changeDelayButton = changeDelayButton;
    this.changeDelayButton.setName(CHANGE_DELAY_BUTTON_TEXT);
  }
	
	public JButton getChangeNumberOfPointsButton() {
    return changeNumberOfPointsButton;
  }

  public void setChangeNumberOfPointsButton(JButton changeNumberOfPointsButton) {
    this.changeNumberOfPointsButton = changeNumberOfPointsButton;
    this.changeNumberOfPointsButton.setName(CHANGE_NUM_OF_POINTS_BUTTON_TEXT);
  }

	public void setQuickHullPanel(HullPanel quickHullPanel) {
		this.quickHullPanel = quickHullPanel;
	}


	public JPanel getButtonsPanel() {
		return buttonsPanel;
	}

	public void setButtonsPanel(JPanel buttonsPanel) {
		this.buttonsPanel = buttonsPanel;
	}

	public JButton getInicializar() {
		return initializeButton;
	}

	public void setInicializar(JButton ini) {
		this.initializeButton = ini;
		this.initializeButton.setName(INITIALIZE_BUTTON_TEXT);
	}

	public JButton getStartStop() {
		return startStopButton;
	}

	public void setStartStop(JButton startStop) {
		this.startStopButton = startStop;
		this.startStopButton.setName(EXECUTE_BUTTON_TEXT);
	}

	public Timer getTemporizador() {
		return algorithmTimer;
	}

	public void setTemporizador(Timer temporizador) {
		this.algorithmTimer = temporizador;
	}
	
	public int getNumberOfPoints() {
		return numberOfPoints;
	}

	public void setNumberOfPoints(int npoints) {
		this.numberOfPoints = npoints;
	}

	public int getDelay() {
		return algorithmDelay;
	}

	public void setDelay(int delay) {
		this.algorithmDelay = delay;
	}
	
	private void pauseAlgorithm() {
	  getTemporizador().stop();
    getStartStop().setText(EXECUTE_BUTTON_TEXT);
	}
	
	private void continueAlgorithm() {
    getTemporizador().start();
    getStartStop().setText(PAUSE_BUTTON_TEXT);
	}
	
	private QuickHullView getView() {
	  return this;
	}
	
	public void resetTimer() {
	  setTemporizador(new Timer(getDelay(), new TimerHandler()));
	}
	
	/**
	 * Clase utilizada para manejar las acciones del temporizdor.
	 */
	class TimerHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			getQuickHullPanel().nextStep();
		}
		
	}
	/**
	 * Clase utilizada para repintar cada vez que se redimensiona la ventana.
	 */
	class WindowHandler extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent arg0) {
			 getQuickHullPanel().initialize(getNumberOfPoints());
			 pauseAlgorithm();
			 repaint();	
		}		
	}
}
