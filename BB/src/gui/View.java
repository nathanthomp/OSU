package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * View Class.
 *
 * @author Nathan Thompson
 */
public final class View extends JFrame {

    /**
     * Default constructor.
     */
    public View() {
        super("Graphical User Interface");

        // Set up the GUI widgets --------------------------------------------

        JFrame frame = new JFrame();

        JPanel inputPane = new JPanel();

        JPanel outputPane = new JPanel();

        JTextArea outputText = new JTextArea("output:", 20, 100);

        JScrollPane outputScrollPane = new JScrollPane(outputText);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
