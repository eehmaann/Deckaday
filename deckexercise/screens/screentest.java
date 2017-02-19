import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.*;

public class screentest{
	public static void main(String[]arg){
		selectionscreen test= new selectionscreen();
		int numberOfChoices=(test.getexerciseChoices().length);
		for (int i=0; i<numberOfChoices; i++){
		System.out.println(test.getexerciseChoices()[i]);
		}

	}
}