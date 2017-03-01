// You need create a frame class, a deck class, a circuit class, and a results class.  All things pertaining to frames needs 
// to be here and can't be moved.  

/**
* This class creates the selection screen for a making a circuit for a deck exercise
*
*@author Eric_Ehmann
*@version 2_13_17
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.ArrayList;

public abstract class Screens extends JFrame{
	String[] exerciseChoices;
	protected ExerciseButton[] exerciseButtons = new ExerciseButton[16];
	protected JButton startButton = new JButton ("Start");
	protected JButton undoButton = new JButton ("Undo");
	protected JButton nextButton = new JButton ("Next");
	private JFrame frame = new JFrame("");
	protected JLabel [] suitSelections = new JLabel[4];
	protected JLabel[] suitLabels = new JLabel[4]; 
	protected JPanel applicationScreens = new JPanel(new CardLayout());
	protected JPanel exerciseSelectionScreen = new JPanel ();
	protected JPanel drawnExerciseScreen = new JPanel();
	protected JPanel resultsScreen= new JPanel();
	protected JTextField time= new JTextField ("0:00");
	protected JTextField command = new JTextField ("");
	protected JTextField cardDisplay= new JTextField();
	protected Timer timer; 
	CardLayout cardLayout = new CardLayout();
	
	
	// THis method returns an array of the available choices for picking exerercises
	public String[] getexerciseChoices(){
		return exerciseChoices;
	}
	// This method builds the cardlayout needed for goign through the program
	public void buildApplicationLayout(){
		applicationScreens.setLayout(new CardLayout());		
	}
	// This methods creates the first screen, where the user will choose which movements to include in deck
	public void buildexerciseSelection(){
		exerciseSelectionScreen.setLayout (new GridLayout (7, 4) );
		exerciseSelectionScreen.setBackground(Color.GREEN);
		applicationScreens.add(exerciseSelectionScreen, "1");
	}

	// This method will create the screen where the user will be going through the deck
	public void buildDrawnExerciseScreen(){
		drawnExerciseScreen.setLayout (new BorderLayout());
		command.setHorizontalAlignment(SwingConstants.CENTER);
		command.setFont (new Font ("Ariel", Font.BOLD, 26));
		command.setEditable(false);
		cardDisplay.setEditable(false);
		drawnExerciseScreen.add(cardDisplay, BorderLayout.CENTER);
		drawnExerciseScreen.add(command, BorderLayout.SOUTH);
		applicationScreens.add(drawnExerciseScreen, "2");
	}
	// This method creates the screen that the user will see after the deck has been finished.
	public void buildResultsScreen(){
		resultsScreen.setLayout (new GridLayout (7,1));	
		JTextField congrats= new JTextField("Congratulations! You are Done!");
		congrats.setEditable(false);  
		resultsScreen.add(congrats);
		applicationScreens.add(resultsScreen, "3");
	}

	// This method will make all of the screens visible, when chosen
	public void showScreen(){
		applicationScreens.setLayout(cardLayout);
		cardLayout.show(applicationScreens, "1");
		frame.add(applicationScreens);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	// This method will create the exercise buttons, and place labels on the buttons
	public void configureExcerciseButtons( String[]exerciseChoices){
		for(int i = 0; i< exerciseChoices.length; i++){
			 exerciseButtons[i]= new ExerciseButton (exerciseChoices[i]);
			exerciseSelectionScreen.add(exerciseButtons[i]);            
		}
	}

	// This method will create labels for the suits so the user can see which exercise goes with which suit
	public void configureSuitLabels(){
		String[] suitList =new String[]{"Clubs: ", "Diamonds: ", "Hearts: ", "Spades: "};
		for(int i =0; i<4; i++){
			suitLabels[i] = new JLabel(suitList[i]);
			suitSelections[i]= new JLabel("Make Selection");
			exerciseSelectionScreen.add(suitLabels[i]);
			exerciseSelectionScreen.add(suitSelections[i]);
		}
	}

	// This method creates the undo button
	public void configureUndoButton(){
		exerciseSelectionScreen.add(undoButton);
	}

	// This method creates the start button
	public void configureStartButton(){
		exerciseSelectionScreen.add(startButton);
	}

	// This method creates and places the next button
	public void configureNextButton(){
		drawnExerciseScreen.add(nextButton, BorderLayout.EAST);
	}

	// This method creates and displays the area needed for displaying the time
	public void createTimer(){
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setFont (new Font ("Ariel", Font.BOLD, 20));
		drawnExerciseScreen.add(time, BorderLayout.NORTH); 
	}
	
}