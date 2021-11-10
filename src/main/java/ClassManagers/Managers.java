/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassManagers;

import Lexical.LexicalAnalyzer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 *
 * @author Marco Munguia <@markomannder>
 */
public class Managers {

    public void initializeAnalyzer(JButton analyzeButton, JTextArea textArea, JTextArea tokenLogArea, JTextArea errorLogArea) {
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LexicalAnalyzer initialize = new LexicalAnalyzer(textArea, tokenLogArea, errorLogArea);

            }

        });
    }

    public void clearArea(JButton clearButton, JTextArea textArea, JTextArea tokenLogArea, JTextArea errorLogArea) {
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LexicalAnalyzer initialize = new LexicalAnalyzer(textArea, tokenLogArea, errorLogArea);
                textArea.setText(null);
                tokenLogArea.setText(null);
                errorLogArea.setText(null);
            }

        });
    }

}
