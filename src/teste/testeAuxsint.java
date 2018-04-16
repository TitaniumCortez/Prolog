/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import sintaxe.SintaticoAux;
import sintaxe.mySintaxe;
import sintaxe.sintaxeBeans;

/**
 *
 * @author RCotez
 */
public class testeAuxsint {
    
    public static void main(String[] args) {
        /// somente para teste pois app vai ficar esperando a janela ser aberta para setar o valor no campo
        String teste[] = {"Predicado","PE","Constante" ,"Virgula" ,"Constante" ,"PD",""};
        String testes[] = {"","PE","Constante" ,"Virgula" ,"Constante" ,"PD",""};//adiconar uma um espaço em branco para
     /* SintaticoAux ana = new SintaticoAux(teste);
       ana = new SintaticoAux(testes);
     //sin.Predicado(g);
      */
        String test ="amigo(test";
      mySintaxe sintaxe = new mySintaxe();
        sintaxeBeans d = new sintaxeBeans();
          List<sintaxeBeans> send = sintaxe.analisador(test);
        DefaultTableModel defTableModel = new DefaultTableModel();
        String[] gf = new String[send.size()];
        String[] fbf = new String[send.size()];
        int i = 0;
        for (sintaxeBeans g : send) {
            System.out.println("Analisador: " + "(" + g.getType() + "," + g.getToken() + "," + g.getPosition() + ")");
            defTableModel.addColumn(g.getType());
            gf[i] = g.getToken();
            fbf[i] = g.getType();
            i++;
        }
    }
  
    
}
