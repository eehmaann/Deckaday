
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.ArrayList;

public class DeckControls extends DeckBuilder implements ActionListener{
	protected Cards currentDeck;
	

	public DeckControls(){
		currentDeck= new Cards();
		addListeners();
	}

	public void addListeners(){
		for(int i = 0; i< exerciseChoices.length; i++){
			exerciseButtons[i].addActionListener(DeckControls.this);
		}
		undoButton.addActionListener (DeckControls.this);
		startButton.addActionListener (DeckControls.this);
		next.addActionListener (DeckControls.this);
	}

	public void startTimer(){
		timer = new Timer(1000, this);
        timer.setInitialDelay(0);
        timer.start();
	}
	
	public void actionPerformed( ActionEvent e ){
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
         	}

         	if (e.getSource()==undoButton){
         		currentDeck.decreaseCounter();
         		int count = currentDeck.getCounter();
         		suitSelections[count].setText("Make Selection");
         		repaint();
         	}
    }
}