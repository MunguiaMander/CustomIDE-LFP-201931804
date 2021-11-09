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
public class ErrorLogs {

    private int errorCounter = 1;

    public void fillErrorLog(String tmpString, ArrayList<String> errorLogs, int errorRow, int errorColumm) {

        errorLogs.add("[" + errorCounter + "]" + " Se detecto error en la cadena: " + tmpString + " En la fila: " + errorRow + " Columna: " + errorColumm + "\n");
        errorCounter++;
    }

    public void showErrorLog(ArrayList<String> errorLogs, JTextArea errorLogArea) {
        for (int i = 0; i < errorLogs.size(); i++) {
            errorLogArea.setText(errorLogArea.getText() + errorLogs.get(i));
        }

    }

}
