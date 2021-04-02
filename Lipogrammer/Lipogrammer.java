import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lipogrammer extends JFrame
        implements ActionListener {
    private JTextArea original, marked, words;

    // Constructor
    public Lipogrammer() {
        super("Lipogrammer");

        setJMenuBar(new LipogrammerMenu(this));
        setupGui();

        original.setText("This thingy tests and marks out the letter 'g' from sentences and paragraphs.");
        refresh();
    }

    public String getText() {
        return original.getText();
    }

    public void setText(String text) {
        original.setText(text);
    }

    public void refresh() {
        String text = original.getText();
        LipogramAnalyzer analyzer = new LipogramAnalyzer(text.toLowerCase());
        marked.setText(analyzer.mark('g'));
        words.setText(analyzer.allWordsWith('g'));
    }

    // Called when the Refresh button is clicked
    public void actionPerformed(ActionEvent e) {
        refresh();
    }

    // **********************  GUI setup ********************************

    private void setupGui() {
        original = new JTextArea(10, 20);
        original.setLineWrap(true);
        original.setWrapStyleWord(true);
        JScrollPane originalPane = new JScrollPane(original,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        marked = new JTextArea(10, 20);
        marked.setEditable(false);
        marked.setBackground(Color.LIGHT_GRAY);
        marked.setLineWrap(true);
        marked.setWrapStyleWord(true);
        JScrollPane markedPane = new JScrollPane(marked,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        words = new JTextArea(10, 10);
        words.setEditable(false);
        words.setBackground(Color.LIGHT_GRAY);
        JScrollPane wordsPane = new JScrollPane(words,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton go = new JButton("Refresh");
        go.addActionListener(this);

        Box box1 = Box.createVerticalBox();
        box1.add(Box.createVerticalStrut(10));
        box1.add(originalPane);
        box1.add(Box.createVerticalStrut(10));
        box1.add(markedPane);
        box1.add(Box.createVerticalStrut(10));
        box1.add(go);
        Box box2 = Box.createVerticalBox();
        box2.add(Box.createVerticalStrut(10));
        box2.add(wordsPane);
        box2.add(Box.createVerticalStrut(40));
        Box box3 = Box.createHorizontalBox();
        box3.add(box1);
        box3.add(Box.createHorizontalStrut(20));
        box3.add(box2);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(box3);
    }

    public static void main(String[] args) {
        Lipogrammer window = new Lipogrammer();
        window.setBounds(100, 100, 480, 480);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
