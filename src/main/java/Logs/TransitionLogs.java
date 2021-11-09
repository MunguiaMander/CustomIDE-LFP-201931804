/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logs;

import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author Marco Munguia <@markomannder>
 */
public class TransitionLogs {

    public void fillTransitionLog(String tmpString, ArrayList<String> transitionLogs, int initialState, int actualState, int stringRow) {

        transitionLogs.add("[" + stringRow + "]" + " Se detecto un movimiento del estado: " + initialState + " al estado : " + actualState + " para la cadena: " + tmpString + "\n");
    }

    public void showTransitionLog(ArrayList<String> transitionLogs,  JTextArea transitionLogArea) {
        for (int i = 0; i < transitionLogs.size(); i++) {
            transitionLogArea.setText(transitionLogArea.getText() + transitionLogs.get(i));
        }

    }

}
