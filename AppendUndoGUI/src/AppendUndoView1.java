import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * View class.
 *
 * @author Nathan Thompson
 */
@SuppressWarnings("serial")
public class AppendUndoView1 extends JFrame implements AppendUndoView {

    /**
     * Controller object.
     */
    private AppendUndoController controller;

    /**
     * Text areas.
     */
    private final JTextArea inputText, outputText;

    /**
     * Buttons.
     */
    private final JButton reset, append, undo;

    /**
     * Constants.
     */
    private static final int LINES_IN_TEXT_AREAS = 10,
            LINE_LENGTHS_IN_TEXT_AREAS = 100, ROWS_IN_BUTTON_PANEL_GRID = 1,
            COLUMNS_IN_BUTTON_PANEL_GRID = 3, ROWS_IN_THIS_GRID = 3,
            COLUMNS_IN_THIS_GRID = 1;;

    /**
     * Default constructor.
     */
    public AppendUndoView1() {

        super("Append Undo GUI");

        /*
         * Widgets
         */
        this.inputText = new JTextArea("", LINES_IN_TEXT_AREAS,
                LINE_LENGTHS_IN_TEXT_AREAS);
        this.outputText = new JTextArea("", LINES_IN_TEXT_AREAS,
                LINE_LENGTHS_IN_TEXT_AREAS);
        this.reset = new JButton("Reset");
        this.append = new JButton("Append");
        this.undo = new JButton("Undo");

        this.inputText.setEditable(true);
        this.inputText.setLineWrap(true);
        this.outputText.setEnabled(false);
        this.outputText.setLineWrap(true);
        this.undo.setEnabled(false);

        JScrollPane topScrollPane = new JScrollPane(this.inputText);
        JScrollPane bottomScrollPane = new JScrollPane(this.outputText);

        JPanel buttonPanel = new JPanel(new GridLayout(
                ROWS_IN_BUTTON_PANEL_GRID, COLUMNS_IN_BUTTON_PANEL_GRID));
        buttonPanel.add(this.reset);
        buttonPanel.add(this.append);
        buttonPanel.add(this.undo);

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(topScrollPane);

        JPanel outputPanel = new JPanel(new FlowLayout());
        outputPanel.add(bottomScrollPane);

        this.setLayout(new GridLayout(ROWS_IN_THIS_GRID, COLUMNS_IN_THIS_GRID));
        this.add(inputPanel);
        this.add(buttonPanel);
        this.add(outputPanel);

        /*
         * Observers
         */
        this.reset.addActionListener(this);
        this.append.addActionListener(this);
        this.undo.addActionListener(this);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    /**
     * Determine which event has occurred that we are being notified of by this
     * callback; in each case, tell the controller to do whatever is needed to
     * update the model and to refresh the view.
     *
     * @param e
     *            the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /*
         * Set cursor to indicate computation on-going.
         */
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        /*
         * Get action and call the controller.
         */
        Object source = e.getSource();
        if (source == this.reset) {
            this.controller.processResetEvent();
        } else if (source == this.append) {
            this.controller.processAppendEvent(this.inputText.getText());
        } else if (source == this.undo) {
            this.controller.processUndoEvent();
        }
        /*
         * Set the cursor back to normal.
         */
        this.setCursor(Cursor.getDefaultCursor());
    }

    /**
     * Register argument as observer/listener of this; this must be done first,
     * before any other methods of this class are called.
     *
     * @param controller
     *            controller to register
     */
    @Override
    public void registerObserver(AppendUndoController controller) {
        this.controller = controller;
    }

    /**
     * Updates input display based on String provided as argument.
     *
     * @param input
     *            new value of input display
     */
    @Override
    public void updateInputDisplay(String input) {
        this.inputText.setText(input);
    }

    /**
     * Updates output display based on String provided as argument.
     *
     * @param output
     *            new value of output display
     */
    @Override
    public void updateOutputDisplay(String output) {
        this.outputText.setText(output);
    }

    /**
     * Updates undo button based on boolean provided as argument.
     *
     * @param allowed
     *            the condition that undo should be enabled or disabled
     */
    @Override
    public void updateUndoAllowed(boolean allowed) {
        this.undo.setEnabled(allowed);
    }

}
