import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import javax.swing.JPanel;
import javax.swing.JColorChooser;
import javax.swing.Timer;

public class TextPanel extends JPanel
  implements MouseInputListener, ActionListener
{
  private final int LINE_HEIGHT = 20;
  private final int xMargin = 10, yMargin = 10;
  private final Font font = new Font(Font.DIALOG_INPUT, Font.PLAIN, LINE_HEIGHT - 3);
  private final Color highlightColor = Color.LIGHT_GRAY;

  private long wakeUpTime = -1;
  private int yOffset = 0;
  private int yMouse = -1;
  private int yAnchor = -1;
  private int selectedLine = -1, toLine = -1;

  private LineList textLines;

  // Constructor:
  public TextPanel()
  {
    setBackground(Color.WHITE);
    addMouseListener(this);
    addMouseMotionListener(this);
    textLines = new LineList();
    Timer clock = new Timer(1000/LINE_HEIGHT, this);
    clock.start();
  }

  public void setWakeUpCall(long t)
  {
    wakeUpTime = t;
  }

  public int numLines()
  {
    return textLines.size();
  }

  public String getLine(int k)
  {
    return textLines.get(k);
  }

  // Called when a new line is added from the text entry field
  public void addLine(String text)
  {
    textLines.add(text);
    yOffset = Math.min(0, getHeight() - textLines.size() * LINE_HEIGHT - 2*yMargin);
    selectedLine = textLines.size() - 1;
    repaint();
  }

  // Called when a the "delete" button is clicked
  public void deleteLine()
  {
    if (selectedLine >= 0 && selectedLine < textLines.size())
    {
      textLines.remove(selectedLine);
      yOffset = Math.min(0, getHeight() - textLines.size() * LINE_HEIGHT - 2*yMargin);
      selectedLine = -1;
      toLine = -1;
      repaint();
    }
  }

  public void moveLine()
  {
    if (selectedLine >= 0 && selectedLine < textLines.size() &&
                     toLine >= 0 && toLine <= textLines.size() && selectedLine != toLine)
    {
      int newIndex = toLine;
      if (selectedLine < newIndex)
        newIndex--;
      textLines.move(selectedLine, newIndex);
      selectedLine = toLine;
      toLine = -1;
      repaint();
    }
  }

  public void shuffle()
  {
    textLines.shuffle();
    selectedLine = -1;
    repaint();
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    int w = getWidth();
    int x = xMargin;
    g.setFont(font);

    for (int k  = 0; k < textLines.size(); k++)
    {
      int y = yOffset + yMargin + k * LINE_HEIGHT;
      if (k == selectedLine)
      {
        g.setColor(highlightColor);
        g.fillRect(x - 2, y, w - 2*xMargin + 2, LINE_HEIGHT);
      }
      g.setColor(Color.BLACK);
      g.drawString(textLines.get(k), x, y + LINE_HEIGHT*3/4);
      if (k == toLine)
        g.drawLine(x, y, x + w - xMargin, y);
    }

    int k = textLines.size();
    if (k == toLine)
    {
      int y = yOffset + yMargin + k * LINE_HEIGHT;
      g.drawLine(x, y, x + w - xMargin, y);
    }
  }

  // Timer event handling (for scrolling)
  public void actionPerformed(ActionEvent e)
  {
    if (wakeUpTime > 0 && System.currentTimeMillis() > wakeUpTime)
    {
      shuffle();
      wakeUpTime = -1;
    }

    if (yMouse < 0)
      return;

    boolean needUpdate = false;
    if (yMouse <= yMargin && yOffset < 0)
    {
      yOffset += 2;
      needUpdate = true;
    }
    int h = getHeight();
    if (yMouse >= h - yMargin && yOffset > getHeight() - textLines.size() * LINE_HEIGHT - 2*yMargin)
    {
      yOffset -= 2;
      needUpdate = true;
    }
    if (needUpdate)
    {
      int k = (yMouse - yOffset - yMargin) / LINE_HEIGHT;
      if (k >= 0 && k <= textLines.size() && k != toLine)
        toLine = k;
      repaint();
    }
  }

  /*******************************************************************/
  /**********************  Mouse events handling *********************/
  /*******************************************************************/

  // Called when the mouse is clicked on the text area: selects a line
  public void mousePressed(MouseEvent e)
  {
    int y = e.getY();
    yMouse = y;
    yAnchor = y;

    int k = (y - yOffset - yMargin) / LINE_HEIGHT;

    boolean needUpdate = false;
    if (k >= 0 && k < textLines.size() && k != selectedLine)
    {
      selectedLine = k;
      needUpdate = true;
    }
    if (k >= 0 && k <= textLines.size() && k != toLine)
    {
      toLine = k;
      needUpdate = true;
    }
    if (needUpdate)
      repaint();
  }

  public void mouseReleased(MouseEvent e)
  {
    yMouse = -1;
    yAnchor = -1;
    moveLine();
    repaint();
  }

  public void mouseDragged(MouseEvent e)
  {
    int y = e.getY();
    yMouse = y;
    toLine = selectedLine + (y - yAnchor)/LINE_HEIGHT;
    repaint();
  }


  // Not used:
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mouseClicked(MouseEvent e) {}
  public void mouseMoved(MouseEvent e) {}
}
