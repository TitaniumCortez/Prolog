/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolog;

import frame.PrologFrame;
import javax.swing.JFrame;

/**
 *
 * @author RCotez
 */
public class Prolog {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PrologFrame start = new PrologFrame();
        start.setTitle("Prolog");
        start.frame(start);
        start.setSize(600, 560);
        start.setVisible(true);
      
        
    }
    
}
