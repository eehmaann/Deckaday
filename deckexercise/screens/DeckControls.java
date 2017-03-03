/** DeckControls.java
** This class adds the action listeners and controls to a DeckBuilder.  It initiates a new case of Cards
**
**@author Eric Ehmann
**@version 2_28_2017
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class DeckControls extends Screens implements ActionListener{
	protected Cards currentDeck;
	protected boolean isTimed;
	protected int seconds;
	protected int startTime =0;
	protected int previousSuit;
	protected int[] suitTimes = {0,0,0,0};
	public final double REPS = 104.00;

	/** 
	*This is a constructor method that will set up a new deck with action listeners to run the program
	*/	
	public DeckControls(){
		currentDeck= new Cards();
		addListeners();
		isTimed=false;
	}
	/**
	* This method adds the action listeners to the buttons so the user can use the application
	*/
	public void addListeners(){
		for(int i = 0; i< exerciseChoices.length; i++){
			exerciseButtons[i].addActionListener(DeckControls.this);
		}
		undoButton.addActionListener(DeckControls.this);
		startButton.addActionListener(DeckControls.this);
		nextButton.addActionListener(DeckControls.this);
	}

		// This method will turn the timer on or off
	public void toggleTimer() {
		isTimed ^= true;
	}
	/**
	* This method will initiate a new timer so the seconds can be tracked.
	*/
	public void startTimer() {
		timer = new Timer(1000, DeckControls.this);
        timer.setInitialDelay(0);
        timer.start();
        toggleTimer();
	}

	/**
	* This method checks if the conditions have been met for the timer to start
	* and will return a boolean representing the answer
	*@return boolean true if conditions have been met for the timer to start
	*/
	public boolean checkTimer() {
		return isTimed;
	}

	// This method returns a string value for how the time should be displayed
	public String displayTime(int theseSeconds) {
		return((""+((theseSeconds/60)+100)).substring(1) + ":" + (""+(100+(theseSeconds%60))).substring(1));
	}

	// This method provides the actions that must be done if the timer is active
	public void runTimer() {
		if(checkTimer()){
				seconds++;
				time.setText(displayTime(seconds));
			}
	}
		
	/**
	* This method will be used to measure the number of seconds that it took the user to do that particular
	* movement and repetitions.  It will add to the time already spent on that particular suit.
	*/
	public void addTime(int suit) {
		int endTime=seconds;
		int duration = endTime-startTime;
		suitTimes[suit]+=duration;
		startTime=seconds;
	}


	/*
	* This method will take a card at random and display the informations on the screen
	* to show both the card that was drawn and what movement and number of repittions that translates into
	*/
	public void drawSet() {
		int currentCard=currentDeck.nextCard();
		previousSuit=currentDeck.getSuitValue(currentCard);
        command.setText(currentDeck.getSet(currentCard));
        cardDisplay.setText(currentDeck.getCardName(currentCard));
	}


	/** 
	* This method will calculate the average number seconds to do a particular movement or all movements and 
	* return a string value showing that answer.
	*@int totalSeconds the number of seconds being used to find the average.  Either total time in movement or deck
	*@int movements.  number of movement being calculated to determine average
	*
	*@String  represent the calculated average
	*/
	public String getAverageTime(int totalSeconds, int movements) {
		DecimalFormat dc = new DecimalFormat("0.00");
		double averageTime=totalSeconds/(movements *REPS);
			return(" Average " +dc.format(averageTime) + "seconds per rep ");

	}

	/**
	* This method will take a number of seconds and produce the string displaying the time.
	*
	*@param totalSeconds.  The number of seconds it took to do deck or movement
	*
	*@return String a statement showing the seconds as minutes and seconds
	*/
	public String getTotalTime(int totalSeconds) {
		return("Total time: " + displayTime(totalSeconds));
	}

	/**
	* This method sets the text on the a textfield of the results page, showing the total time to do the deck and the average 
	* number of seconds it took to complete a movement
	*/
	public void displayOverallResults() {
		String totalTime = getTotalTime(seconds);
		String averageTime = getAverageTime(seconds,4);
		JTextField overallTime = new JTextField("Overall "+ totalTime + averageTime);
		overallTime.setEditable(false);
		resultsScreen.add(overallTime);
	}

	/**
	* This methods creates a textfield for each suit, displaying the movement, the total time spent on these movements
	* and the average time it to took complete the movement once.
	*/
	public void displayMovementResults() {
		JTextField[] moveTimes = new JTextField[4];
		for(int i=0; i<4; i++) {
			String movement = currentDeck.getMovement(i) + " ";
			String totalTime = getTotalTime(suitTimes[i]);
			String averageTime = getAverageTime(suitTimes[i], 1);
			moveTimes[i] = new JTextField(movement + totalTime + averageTime);
			moveTimes[i].setEditable(false);
			resultsScreen.add(moveTimes[i]);

		}
	}

	/** 
		*This method detemines what actions will happen throughout the program
		*/

	public void actionPerformed( ActionEvent e ) {
			runTimer();
         	for (int i=0; i<16; i++) { 
         		if (e.getSource()== exerciseButtons[i] && currentDeck.getCounter()<4) {
         		int count = currentDeck.getCounter();
         		suitSelections[count].setText(exerciseChoices[i]);
         		repaint();
         		currentDeck.setMovement(currentDeck.getCounter(), exerciseChoices[i]);
         		currentDeck.increaseCounter();		
         	}        	
        }
         	if (e.getSource()== startButton && currentDeck.getCounter() == 4) { 
         		cardLayout.next(applicationScreens);
         		drawSet();
         		startTimer();
         	}

         	else if (e.getSource()==undoButton) {
         		currentDeck.decreaseCounter();
         		int count = currentDeck.getCounter();
         		suitSelections[count].setText("Make Selection");
         		repaint();
         	}

         	else if (e.getSource()==nextButton) {
         		addTime(previousSuit);
         		if(currentDeck.countCards()==0) {
         			cardLayout.next(applicationScreens);
         			displayOverallResults();
         			displayMovementResults();
         		}
         		else {
         			drawSet();
         		}
         	}
    }
}