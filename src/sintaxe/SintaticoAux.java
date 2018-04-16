/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintaxe;

import frame.PrologFrame;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author RCotez
 */
public final class SintaticoAux {

    private final String[] expressao;
    int p = 0;
    PrologFrame setExpect = new PrologFrame();

  
   
public SintaticoAux(String[] exp) {
    
        String[] expressao = new String [exp.length];
        System.out.println("Emprime"+Arrays.toString(exp));
        System.out.println("Emprime"+Arrays.toString(expressao));
        
        for (int i =0;i <= expressao.length-1;i++){
            
         
            if(i == expressao.length-1){ //ultima posiacão,   olhar uma posicao a frente 
                expressao[i] = "d";
             
            }else{                
                expressao[i] = exp[i];
              
            }
        }       
  
        this.expressao = expressao;
         System.out.println("Emprime"+Arrays.toString(expressao));
          System.out.println("Emprime"+Arrays.toString(this.expressao));
        Predicado();        

    }
    

    //Arvore de Alcancabilidade
    public  void Predicado() {
 
        System.out.println("->Pre" + expressao[p]);
        switch (expressao[p]) {

            case "Predicado":
                p++;
                PE();
                break;

            default:
           setExpect.setLabelExpect("Expect 'predicado(...).'");
         System.out.println("Expect 'predicado(...).'");
          
        }
       
    }

    public void PE() {
        System.out.println("->PE" + expressao[p]);
        switch (expressao[p]) {
            
            case "PE": // "("  caso
                p++;
                ConsFuncVar();
                break;

            default:
                setExpect.setLabelExpect("Expect ' ( '");
                System.out.println("Expect ' ( '");
        }
            
    }

    public void ConsFuncVar() {
        System.out.println("->ConsF" + expressao[p]);
        switch (expressao[p]) {
            case "Constante":
                p++;
                Constante();
                break;

            case "Varivel":
                p++;
                Variavel();
                break;

            default:
                setExpect.setLabelExpect("Expect Constante, Variavel,Funcão()");
                System.out.println("Expect Constante, Variavel,Funcão()");

        }

    }

    public void Constante() {
        System.out.println("->Cons" + expressao[p]);
        switch (expressao[p]) {
            case "Virgula": // caso " , "
                p++;
                ConsFuncVar();
                break;

            case "PE": // caso " ( "
                p++;
                ConstF();
                break;

            case "PD": // caso " ) "
                p++;
                PredPontoFunc();
                break;

            default:
                setExpect.setLabelExpect("Expect  ',' , '(' ");
                System.out.println("Expect  ',' , '(' ");

        }

    }

    public void Variavel() {
        System.out.println("->Var" + expressao[p]);
        switch (expressao[p]) {
            case "Virgula":
                p++;
                ConsFuncVar();
                break;

            case "PD": // caso " ) "
                p++;
                PredPontoFunc();
                break;
            default:
                setExpect.setLabelExpect("Expect  ',' , '(' ");
                System.out.println("Expect  ',' , '(' ");

        }

    }

    public void ConstF() {
        System.out.println("->ConsF" + expressao[p]);
        switch (expressao[p]) {

            case "Constante":
                p++;
                ParDirFunc();
                break;
            default:
                setExpect.setLabelExpect("Expect Constante(Constante) ");
                System.out.println("Expect Constante(Constante)");

        }

    }

    public void ParDirFunc() {
        System.out.println("->PArd" + expressao[p]);
        switch (expressao[p]) {
            case ")":
                p++;
                VirgFechaPred();
                break;
            default:
               setExpect.setLabelExpect("Expect ')'");
                System.out.println("Expect ')'");

        }

    }

    public void VirgFechaPred() {
        System.out.println("->VirgFex" + expressao[p]);
        switch (expressao[p]) {
            case ")":
                p++;
                PredPontoFunc();
                break;

            case ",":
                p++;
                ConsFuncVar();
                break;
            default:
                setExpect.setLabelExpect("Expect ')' , ',' ");
                System.out.println("Expect ')' , ',' ");
        }

    }

    public void PredPontoFunc() {
        System.out.println("->PT" + expressao[p]);
        switch (expressao[p]) {
            case "PT":
                //finaliza
                break;
            default:
                setExpect.setLabelExpect("Expect '.'");
                System.out.println("Expect '.'");
        }

    }

}
