/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

/**
 *
 * @author RCotez
 */
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

@SuppressWarnings("serial")
public class TextAreaTrapUp extends JPanel {
    private JTextArea textArea = new JTextArea(20, 40);

    public TextAreaTrapUp() {
        // get JTextArea's InputMap and ActionMap
        int condition = JComponent.WHEN_FOCUSED;
        InputMap inputMap = textArea.getInputMap(condition);
        ActionMap actionMap = textArea.getActionMap();

        // get the up keystroke
        KeyStroke upKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        String upKey = (String) inputMap.get(upKeyStroke); // get the input map's key for this keystorke
        Action originalUpAction = actionMap.get(upKey); // and get the action map's original action for this key

        Action newUpAction = new NewUpAction(originalUpAction); // create our new up action passing in the old one
        actionMap.put(upKey, newUpAction); // and set this into our ActionMap

        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        add(new JScrollPane(textArea));
    }

    // Action called when up-arrow pressed
    private class NewUpAction extends AbstractAction {
        private Action originalUpAction; // the original action

        public NewUpAction(Action originalUpAction) {
            this.originalUpAction = originalUpAction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Up Arrow Pressed");

            // if you want to move the caret up, then call the original action
            // as well
            if (originalUpAction != null) {
                originalUpAction.actionPerformed(e);
            }
        }
    }

    private static void createAndShowGui() {
        TextAreaTrapUp mainPanel = new TextAreaTrapUp();

        JFrame frame = new JFrame("TextAreaTrapUp");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGui());
    }
}