/**
 * Represents the control panel in Shuffler.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ControlPanel extends JPanel
{
  private TextPanel canvas;
  private JTextField textInput;
  private JButton deleteButton;

  public ControlPanel(TextPanel canvas)
  {
    this.canvas = canvas;

    textInput = new JTextField(40);
    textInput.addActionListener(new TextInputListener());
    textInput.setBackground(Color.YELLOW);
    add(textInput);

    deleteButton = new JButton("Delete");
    deleteButton.addActionListener(new DeletButtonListener());
    add(deleteButton);
  }

  private class TextInputListener
      implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      canvas.addLine(textInput.getText());
      textInput.setText("");
    }
  }

  private class DeletButtonListener
      implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      canvas.deleteLine();
      textInput.setText("");
    }
  }
}
