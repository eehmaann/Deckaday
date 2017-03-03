import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
public class ExerciseButton extends JButton {
	private JButton exerciseButton;
	public ExerciseButton(String label) {
		super(label);
		JButton exerciseButton= new JButton("");
			exerciseButton.setFont (new Font("Times", Font.BOLD, 12));
            exerciseButton.setHorizontalAlignment(JButton.CENTER);
            exerciseButton.setPreferredSize(new Dimension(100,100));
	}
}