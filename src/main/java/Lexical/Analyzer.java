package Lexical;

import Logs.*;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author Marco Munguia <@markomannder>
 */
public class Analyzer {

    private String textEntered;
    private ArrayList<String> tokenArray = new ArrayList<>();
    private ArrayList<String> errorArray = new ArrayList<>();
    private ArrayList<String> transitionsArray = new ArrayList<>();
    private int stateMatrix[][] = new int[13][25];
    private int actualState = 0;
    private int index = 0;
    private int stringRow = 1;
    private int stringCoulmn = 0;
    private TokenLogs tokenLogs = new TokenLogs();
    private ErrorLogs errorLogs = new ErrorLogs();
    private TransitionLogs transitionLog = new TransitionLogs();
    private int stateCheck = 0;

    {

        for (int i = 0; i < stateMatrix.length; i++) {
            for (int j = 0; j < stateMatrix[0].length; j++) {
                stateMatrix[i][j] = -1;
            }
        }

        //Fill the Matrix w/ accepted transition states
        stateMatrix[0][0] = 1;
        stateMatrix[0][1] = 1;
        stateMatrix[0][2] = 2;
        stateMatrix[0][3] = 1;
        stateMatrix[0][4] = 2;
        stateMatrix[0][6] = 3;
        stateMatrix[0][7] = 4;
        stateMatrix[0][8] = 6;
        stateMatrix[0][9] = 6;
        stateMatrix[0][10] = 6;
        stateMatrix[0][11] = 6;
        stateMatrix[0][12] = 6;
        stateMatrix[0][13] = 6;
        stateMatrix[0][14] = 6;
        stateMatrix[0][15] = 6;
        stateMatrix[0][21] = 7;
        stateMatrix[0][22] = 10;
        stateMatrix[0][23] = 11;
        //
        stateMatrix[1][0] = 1;
        stateMatrix[1][1] = 1;
        stateMatrix[1][2] = 1;
        stateMatrix[1][3] = 1;
        stateMatrix[1][4] = 1;
        stateMatrix[1][5] = 1;
        stateMatrix[1][6] = 1;
        //
        stateMatrix[2][5] = 2;
        //
        stateMatrix[4][7] = 5;
        stateMatrix[4][16] = 4;
        //
        stateMatrix[6][8] = 6;
        stateMatrix[6][9] = 6;
        stateMatrix[6][10] = 6;
        stateMatrix[6][11] = 6;
        stateMatrix[6][12] = 6;
        stateMatrix[6][13] = 6;
        stateMatrix[6][14] = 6;
        stateMatrix[6][15] = 6;
        //
        stateMatrix[7][21] = 8;
        //
        stateMatrix[8][16] = 8;
        //
        stateMatrix[9][17] = 9;
        stateMatrix[9][18] = 9;
        stateMatrix[9][19] = 9;
        stateMatrix[9][20] = 9;
        //
        stateMatrix[10][22] = 10;
        //
        stateMatrix[11][16] = 11;
        stateMatrix[11][23] = 11;
        stateMatrix[11][24] = 12;
        //
        stateMatrix[12][24] = 12;

    }

    public Analyzer(JTextArea textArea, JTextArea tokenLogArea, JTextArea errorLogArea) {
        textEntered = textArea.getText();
        while (index < textEntered.length()) {
            getToken(tokenLogArea, errorLogArea);
        }
        stringRow++;
        tokenLogs.showTokenLog(tokenArray, tokenLogArea);
        errorLogs.showErrorLog(errorArray, errorLogArea);

    }

    public void getToken(JTextArea tokenLogArea, JTextArea errorLogArea) {
        boolean readFlag = true;
        char tmp;
        String tokenString = "";
        int temporalState = 0;
        while (readFlag == true && index < textEntered.length()) {
            if (Character.isSpaceChar(tmp = textEntered.charAt(index)) && stateCheck != 4 && stateCheck != 11) {
                readFlag = false;
            } else if (tmp == '\n' || tmp == '\t' || tmp == '\r' || tmp == '\f') {
                readFlag = false;
                stringCoulmn = 0;
                stringRow++;
            } else {
                temporalState = getNextState(actualState, getStateInt(tmp, stateCheck));
                stateCheck = temporalState;
                tokenString += tmp;
                transitionLog.fillTransitionLog(tokenString, transitionsArray, actualState, temporalState, stringRow);
                actualState = temporalState;
            }
            index++;
            stringCoulmn++;
        }
        finishStateChecker(tokenString, tokenLogArea, errorLogArea);
        stateCheck = 0;
        actualState = 0;
    }

    public int getNextState(int actualPosition, int character) {
        int result = -1;
        if (character >= 0 && character <= 25 && actualPosition != -1) {
            result = stateMatrix[actualPosition][character];
        }
        return result;
    }

    public int getStateInt(char actualChar, int stateCheck) {
        int result = -1;

        if ((Character.isLetter(actualChar)) && (stateCheck == 0 || stateCheck == 1)) {
            result = 0;
        } else if ((Character.isLetter(actualChar)) && (stateCheck == 0 || stateCheck == 1)) {
            result = 1;
        } else if ((actualChar == '-') && (stateCheck == 0 || stateCheck == 1)) {
            result = 2;
        } else if ((actualChar == '_') && (stateCheck == 0 || stateCheck == 1)) {
            result = 3;
        } else if ((Character.isDigit(actualChar) && actualChar != 0) && (stateCheck == 0 || stateCheck == 1)) {
            result = 4;
        } else if ((Character.isDigit(actualChar)) && (stateCheck == 1 || stateCheck == 2)) {
            result = 5;
        } else if ((actualChar == 0) && (stateCheck == 0 || stateCheck == 1)) {
            result = 6;
        } else if ((actualChar == '"') && (stateCheck == 0 || stateCheck == 4)) {
            result = 7;
        } else if ((actualChar != '"') && (stateCheck == 4 || stateCheck == 8 || stateCheck == 11)) {
            result = 16;
        } else if ((actualChar == '/') && (stateCheck == 0 || stateCheck == 7)) {
            result = 21;
        } else if ((actualChar == '+' || actualChar == '*' || actualChar == '=') && (stateCheck == 0 || stateCheck == 10)) {
            result = 22;
        } else if ((actualChar == '(') && (stateCheck == 0 || stateCheck == 11)) {
            result = 23;
        } else if ((actualChar == ')') && (stateCheck == 11 || stateCheck == 12)) {
            result = 24;
        } else {
            actualState = 0;
            stateCheck = 0;
        }
        return result;
    }

    public void finishStateChecker(String tokenString, JTextArea tokenLogArea, JTextArea errorLogArea) {

        if (actualState == -1) {
            errorLogs.fillErrorLog(tokenString, errorArray, stringRow, stringCoulmn);
            actualState = 0;
            stateCheck = 0;
        } else {
            tokenLogs.fillTokenLog(tokenString, tokenArray, stringRow, stringCoulmn, actualState);
        }

    }
}
