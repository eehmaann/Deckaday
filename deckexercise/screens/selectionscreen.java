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

public class selectionscreen extends JFrame implements ActionListener{
	String[] exerciseChoices;
	private ExerciseButton[] exerciseButtons = new ExerciseButton[16];
	private JButton startButton = new JButton ("Start");
	private JButton undoButton = new JButton ("Undo");
	private JLabel [] suitSelections = new JLabel[4];
	private JPanel applicationScreens = new JPanel(new CardLayout());
	private JPanel exerciseSelectionScreen = new JPanel ();
	private JPanel drawnExerciseScreen = new JPanel();
	private JPanel resultsScreen= new JPanel();
	private Timer timer; 
	CardLayout cardLayout = new CardLayout();
	private JFrame frame = new JFrame("");
	
	
	
	public selectionscreen(){
		exerciseChoices= new String[] {"Air Squats","Burpees","Butt Bridges","Bicycle Kicks","Broad Jumps","Crunches",
		"Flutter Kicks","Jumping Jacks", "Lunges", "Leg Lifts", "Mnt. Clinmbers", "Pistols", "Push Ups", "Sit-ups", "Squats", "Tricep Dips"};
		buildApplicationLayout();
		buildexerciseSelection();
		buildDrawnExerciseScreen();
		buildResultsScreen();
		configureExcerciseButtons(exerciseChoices);
		configureUndoButton();
		configureStartButton();
		configureNextButton();
		createTimer();
		showScreen();
	}

	public String[] getexerciseChoices(){
		return exerciseChoices;
	}

	public void buildApplicationLayout(){
		applicationScreens.setLayout(new CardLayout());		
	}
	public void buildexerciseSelection(){
		exerciseSelectionScreen.setLayout (new GridLayout (5, 4) );
		exerciseSelectionScreen.setBackground(Color.GREEN);
		applicationScreens.add(exerciseSelectionScreen, "1");
	}

	public void buildDrawnExerciseScreen(){
		drawnExerciseScreen.setLayout (new BorderLayout());
		JTextField command = new JTextField (" 14 Mountain Climbers "); // shows current exercise
		command.setHorizontalAlignment(SwingConstants.CENTER);
		command.setFont (new Font ("Ariel", Font.BOLD, 26));
		command.setEditable(false);
		drawnExerciseScreen.add(command, BorderLayout.SOUTH);
		applicationScreens.add(drawnExerciseScreen, "2");
	}

	public void buildResultsScreen(){
		resultsScreen.setLayout (new GridLayout (7,1));	
		JTextField congrats= new JTextField("Congratulations! You are Done!");
		congrats.setEditable(false);  
		resultsScreen.add(congrats);
		JTextField overallTime= new JTextField("Overall time: ");
		overallTime.setEditable(false);
		resultsScreen.add(overallTime);
		applicationScreens.add(resultsScreen, "3");
	}


	public void showScreen(){
		applicationScreens.setLayout(cardLayout);
		cardLayout.show(applicationScreens, "1");
		frame.add(applicationScreens);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void configureExcerciseButtons( String[]exerciseChoices){
		for(int i = 0; i< exerciseChoices.length; i++){
			System.out.println(exerciseChoices[i]);
			exerciseButtons[i]= new ExerciseButton ("Pizza", selectionscreen.this);
			exerciseSelectionScreen.add(exerciseButtons[i]);            
		}
	}

	public void configureUndoButton(){
		undoButton.addActionListener (selectionscreen.this);
		exerciseSelectionScreen.add(undoButton);
	}

	public void configureStartButton(){
		startButton.addActionListener (selectionscreen.this);
		exerciseSelectionScreen.add(startButton);
	}

	public void configureNextButton(){
		JButton next = new JButton ("Next");
		next.addActionListener (selectionscreen.this);
		drawnExerciseScreen.add(next, BorderLayout.EAST);
	}

	public void createTimer(){
		JTextField time= new JTextField ("0:00");
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setFont (new Font ("Ariel", Font.BOLD, 20));
		drawnExerciseScreen.add(time, BorderLayout.NORTH); 
	}



	public void actionPerformed( ActionEvent e ){
	}

}