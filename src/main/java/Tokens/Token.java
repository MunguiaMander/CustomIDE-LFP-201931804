/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokens;

/**
 *
 * @author Marco Munguia <@markomannder>
 */
public enum Token {
    
    IDENTFIER("Identificador"), ENTIRENUMBER("Numero Entero"), OPERATOR("Operador"), LITERAL("Literal"), COMMENT("Comentario"), RESERVED("Palabra Reservada"), OPERATION("Operacion");
    
    private final String tokenName;
    
    private Token(String tokenName){
        this.tokenName = tokenName;
    }
    
    public String getTokenName(){
        return tokenName;
    }
    
}
