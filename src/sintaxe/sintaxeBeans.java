/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintaxe;

/**
 *
 * @author RCotez
 *///<PREDICADO><PE>((<CONSTANTE>|<VARIAVEL>|funcao())+<V>)<PD><PT>
public class sintaxeBeans {

      
    private boolean reconhecido;
    private String token;
    private String type;
    private int position;    

    public boolean isReconhecido() {
        return reconhecido;
    }

    public void setReconhecido(boolean reconhecido) {
        this.reconhecido = reconhecido;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    



}
