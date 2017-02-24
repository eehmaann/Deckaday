
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.ArrayList;

public class DeckBuilder extends Screens{
	public DeckBuilder(){
		super();
		exerciseChoices= new String[] {"Air Squats","Burpees","Butt Bridges","Bicycle Kicks","Broad Jumps","Crunches",
		"Flutter Kicks","Jumping Jacks", "Lunges", "Leg Lifts", "Mnt. Climbers", "Pistols", "Push Ups", "Sit-ups", "Squats", "Tricep Dips"};
		buildApplicationLayout();
		buildexerciseSelection();
		buildDrawnExerciseScreen();
		buildResultsScreen();
		configureSuitLabels();
		configureExcerciseButtons(exerciseChoices);
		configureUndoButton();
		configureStartButton();
		configureNextButton();
		createTimer();
		showScreen();
	}
}