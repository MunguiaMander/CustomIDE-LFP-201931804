/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logs;

import Tokens.Token;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author Marco Munguia <@markomannder>
 */
public class TokenLogs {

    private Token token;
    private String tokenName;
    private int tokenCounter = 1;

    public void fillTokenLog(String tmpString, ArrayList<String> tokenLogs, int tokenRow, int tokenColumm, int acceptedState) {
        acceptedState = reservedCheck(tmpString, acceptedState);
        tokenLogs.add("[" + tokenCounter + "] Cadena: " + tmpString + " Tipo de Token: " + tokenType(acceptedState) + " Detectado en la fila: " + tokenRow + " Columna: " + (tokenColumm - 1) + "\n");
        tokenCounter++;
    }

    public int reservedCheck(String tokenString, int actualState) {
        if (tokenString.equals("ESCRIBIR") || tokenString.equals("FIN") || tokenString.equals("SI") || tokenString.equals("REPETIR") || tokenString.equals("INICIAR") || tokenString.equals("VERDADERO") || tokenString.equals("FALSO") || tokenString.equals("ENTONCES")) {
            actualState = 6;
        }
        return actualState;
    }

    public String tokenType(int acceptedState) {

        switch (acceptedState) {
            case 1:
                tokenName = token.IDENTFIER.getTokenName();
                break;
            case 2:
                tokenName = token.ENTIRENUMBER.getTokenName();
                break;
            case 3:
                tokenName = token.ENTIRENUMBER.getTokenName();
                break;
            case 5:
                tokenName = token.LITERAL.getTokenName();
                break;
            case 6:
                tokenName = token.RESERVED.getTokenName();
                break;
            case 8:
                tokenName = token.COMMENT.getTokenName();
                break;
            case 10:
                tokenName = token.OPERATOR.getTokenName();
                break;
            case 12:
                tokenName = token.OPERATION.getTokenName();
                break;
            default:
        }

        return tokenName;
    }

    public void showTokenLog(ArrayList<String> tokenLogs, JTextArea tokenLogArea) {
        for (int i = 0; i < tokenLogs.size(); i++) {
            tokenLogArea.setText(tokenLogArea.getText() + tokenLogs.get(i));
        }

    }

}
