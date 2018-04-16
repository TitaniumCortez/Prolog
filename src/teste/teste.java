/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sintaxe.mySintaxe;
import sintaxe.sintaxeBeans;

/**
 *
 * @author RCotez
 */
public class teste {

    String consomeTokens[];

    public static void main(String[] args) {
        String teste = "eu testo !";
        int countChar = 0;
        // String fase1 = teste.replace(" ","");
        int x = 5;
        /*
        countChar = fase1.length();
        String tmp = String.valueOf(fase1.charAt(2));
        System.out.println(tmp +"  "+countChar+"...." +fase1.charAt(0));     

        do{
        countChar++;
        System.out.println("TETET");
        }while(countChar <=5);{
        
        } */

        String[] consomeTokens = {"jao", ",", "maria", "a", "G"};
        teste t = new teste();
        String test = "amigo(jaao,maria).";
        boolean allRec = true;
        boolean aux = false;

        String fff = "tetet";

        mySintaxe sintaxe = new mySintaxe();
        sintaxeBeans d = new sintaxeBeans();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] fbf;
        String funcao = "";

        List<sintaxeBeans> send = sintaxe.analisador(test);
        DefaultTableModel defTableModel = new DefaultTableModel();
        String[] gf = new String[send.size()];
        fbf = new String[send.size()];
        int i = 0;
        for (sintaxeBeans g : send) {
            System.out.println("Analisador: " + "(" + g.getType() + "," + g.getToken() + "," + g.getPosition() + ")");
            defTableModel.addColumn(g.getType());
            gf[i] = g.getToken();
            fbf[i] = g.getType();
            i++;
        }

        defTableModel.insertRow(0, gf);
        Color color = new Color(163, 209, 248);
        Font font = new Font("TimesRoman", Font.BOLD, 15);
        JTable table = new JTable();
        table.setFont(font);

        table.setEnabled(false);
        table.getAutoResizeMode();
        table.setBackground(color);
        // table.setBackground(Color.red);

        table.setModel(defTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(300, 150);
        frame.setVisible(true);

        //<PREDICADO><PE>((<CONSTANTE>|<VARIAVEL>|funcao())+<V>)<PD><PT>     
        for (int f = 0; f <= fbf.length - 1; f++) {
            if (fbf[f].contains("NReconhecido")) {
                System.out.println("Não unificavel");
                allRec = false;
            }

        }

        for (int g = 0; g <= fbf.length - 1; g++) {
            System.out.println("-> : " + fbf[g] + " p:" + g);
        }
        //<pred><pe>      <pd><pt>

        if (allRec) {
            if (fbf[0].contains("Predicado")) {
                if (!fbf[1].contains("PE")) {

                    System.out.println("Expection  ' ( ' "); allRec = false;
                    
                }

                for (int g = 2; g <= fbf.length - 2; g++) {                    
                                         
                    if (fbf[g].contains("Constante") || fbf[g].contains("Varivel")) {
                            
                       
                    }   else{
               
                     if (fbf[g].contains("Constante") && fbf[g + 1].contains("PE") && fbf[g + 2].contains("Constante")
                                && fbf[g + 3].contains("PD")) {
                            funcao = fbf[g] + fbf[g + 1] + fbf[g + 2] + fbf[g + 3];
                            g += 4;

                        } else {
                            System.out.println("Expection"
                                    + " Constante,"
                                    + "Variavel," + "Funcão."); allRec = false;
                        }  
                    }
                
                     
                    if (!fbf[g + 1].contains("Virgula")  && fbf.length<fbf.length-2 ) {
                        System.out.println("Exception    ' , ' "); allRec = false;
                        
                    } else {
                        g++;
                    }

                }
                if (!fbf[fbf.length-2].contains("PD")) {
                         System.out.println("Exception    ' ) ' "); allRec = false;
                    } 
                if (!fbf[fbf.length-1].contains("PT")) {
                         System.out.println("Exception    ' . ' "); allRec = false;
                    } 

            } else {
                System.out.println(" Expection ' Predicado '"); allRec = false;
            }
            
            System.out.println("Fbf: "+allRec);
        }
        

        /* final String regex = "\\[id='([^']+)'(?:\\s*or\\s*id='([^']+)')*\\]";
final String text = "[id='id1' or id='id2' or id='id3']";
final Pattern pattern = Pattern.compile(regex);
final Matcher matcher = pattern.matcher(text);*/
    }

}

//<PREDICADO><PE>((<CONSTANTE>|<VARIAVEL>|funcao())+<V>)<PD><PT>  
/// System.out.println(t.umOuMais(consomeTokens));
//System.out.println(t.predicado(f));
/* int i=0;
     public boolean umOuMais(String consomeTokens[]) {
        boolean test = false;
       
        // Busca em profundidade com recursividade 
        if(consomeTokens[0].equals("casaG"))
            return true;
        consomeTokens[0]+= consomeTokens[i+1];
        //encontrado alguma possibilidade retorna caso nao percorre profundidade
        i++;
        return umOuMais(consomeTokens);
    }
     
       public boolean predicado(String predicado) {
        sintaxeBeans s = new sintaxeBeans();

        boolean test = predicado.matches("[a-z].+");
        s.setPredicado(test);
        s.setToken(predicado);
        if(test ==true) {
        
    }
     return test;
}*/
