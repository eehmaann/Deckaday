import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
public class ExerciseButton extends JButton{
	private JButton exerciseButton;
	// = new JButton ("Undo");
	public ExerciseButton (String label, ActionListener listener){
		super();
		JButton exerciseButton= new JButton ("Pizza");
			exerciseButton.setFont (new Font ("Times", Font.BOLD, 12));
            exerciseButton.setHorizontalAlignment(JButton.CENTER);
            exerciseButton.setPreferredSize(new Dimension(100,100));
            exerciseButton.addActionListener (listener);
	}
}