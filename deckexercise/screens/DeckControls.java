import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.ArrayList;

public class DeckControls extends DeckBuilder implements ActionListener{
	protected Cards currentDeck;
	protected boolean isTimed;
	protected int seconds;
	protected int startTime =0;
	protected int previousSuit;
	protected int[] suitTimes ={0,0,0,0};

	public DeckControls(){
		currentDeck= new Cards();
		addListeners();
		isTimed=false;

	}

	public void addListeners(){
		for(int i = 0; i< exerciseChoices.length; i++){
			exerciseButtons[i].addActionListener(DeckControls.this);
		}
		undoButton.addActionListener (DeckControls.this);
		startButton.addActionListener (DeckControls.this);
		nextButton.addActionListener (DeckControls.this);
	}

	public void startTimer(){
		timer = new Timer(1000, DeckControls.this);
        timer.setInitialDelay(0);
        timer.start();
        toggleActive();
	}
	public boolean checkActive(){
		return isTimed;
	}

	public void toggleActive(){
		isTimed ^= true;
	}

	public void runTimer(){
		if(checkActive()){
				seconds++;
				time.setText(displayTime(seconds));
			}
	}

	public String displayTime(int theseSeconds){
		return ((""+((theseSeconds/60)+100)).substring(1) + ":" + (""+(100+(theseSeconds%60))).substring(1));
	}


	/*
	* This method will take a card at random and display the informations on the screen
	*/
	public void drawSet(){
		int currentCard=currentDeck.nextCard();
		previousSuit=currentDeck.getSuitValue(currentCard);
        command.setText(currentDeck.getSet(currentCard));
	}
	public void displayCard(){}

	public void addTime(int suit){
		int endTime=seconds;
		int duration = endTime-startTime;
		suitTimes[suit]+=duration;
		startTime=seconds;
	}

	public void calculateResults(){
		for(int i=0; i<4; i++){
			System.out.println(displayTime(suitTimes[i]));
		}
	}




	public void actionPerformed( ActionEvent e ){
			runTimer();
         	for (int i=0; i<16; i++){ 
         		if (e.getSource()== exerciseButtons[i] && currentDeck.getCounter()<4){
         		int count = currentDeck.getCounter();
         		suitSelections[count].setText(exerciseChoices[i]);
         		repaint();
         		currentDeck.setMovement (currentDeck.getCounter(), exerciseChoices[i]);
         		currentDeck.increaseCounter();		
         	}        	
        }
         	if (e.getSource()== startButton && currentDeck.getCounter() == 4){ 
         		cardLayout.next(applicationScreens);
         		drawSet();
         		startTimer();
         	}

         	if (e.getSource()==undoButton){
         		currentDeck.decreaseCounter();
         		int count = currentDeck.getCounter();
         		suitSelections[count].setText("Make Selection");
         		repaint();
         	}

         	if (e.getSource()==nextButton){
         		addTime(previousSuit);
         		if(currentDeck.countCards()==0){
         			cardLayout.next(applicationScreens);
         			calculateResults();
         		}
         		else{
         			drawSet();
         		}
         	}
    }
}