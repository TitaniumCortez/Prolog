/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintaxe;

import java.util.ArrayList;

/**
 *
 * @author RCotez
 */
public class mySintaxe {

    java.util.List<sintaxeBeans> analisador = new ArrayList<sintaxeBeans>();
    java.util.List<sintaxeBeans> conjunto = new ArrayList<sintaxeBeans>();

    String[] pe = new String[1];
    String[] constante = new String[1];
    sintaxeBeans setTrue = new sintaxeBeans();

    /*Atencao*/
    int countGlobal = 0;

    public java.util.List<sintaxeBeans> analisador(String full) {

        int countChar;
        String[] consomeTokens;
        String simboloNr = null;
        java.util.List<sintaxeBeans> conj = new ArrayList<sintaxeBeans>();
        // Estrutura 
        //<PREDICADO><PE>((<CONSTANTE>|<VARIAVEL>|funcao())+<V>)<PD><PT>
        /**
         * ************ fase 1 separação *****************
         */
try{        String fase1 = full.trim();
        countChar = fase1.length();
        int simbolonaoreconhecido = 0;
        consomeTokens = new String[countChar + 1];
     //   System.out.println(""+countChar);
        for (int i = 0; i <= countChar; i++) {

            if (i == countChar) { //escape ultima posição para nao estourar o vetor caso algum constante venha no final sem 
              
                consomeTokens[i] = "1";
                // System.out.println("p"+i+" "+consomeTokens[i]);
            } else {
                consomeTokens[i] = String.valueOf(fase1.charAt(i));
              //  System.out.println("p"+i+consomeTokens[i]);
            }
        }

        String tmp = "";
        boolean Nreconhecido = true;
        boolean reconhecido = false;

        boolean predicado = true;

        while (Nreconhecido) {
            while (countGlobal <= countChar) {

                String uniaop = "";
                String uniaoc = "";
                sintaxeBeans predi = new sintaxeBeans();
                sintaxeBeans cons = new sintaxeBeans();
                sintaxeBeans parentE = new sintaxeBeans();
                sintaxeBeans parentD = new sintaxeBeans();
                sintaxeBeans var = new sintaxeBeans();
                sintaxeBeans virg = new sintaxeBeans();
                sintaxeBeans ponto = new sintaxeBeans();

                //adicionado countGlobal <= consomeTokens.length-1 para envio caracter apartir da minha Tela
                //System.out.println(consomeTokens[countGlobal] + " N: " + countGlobal);
                if (countGlobal < countChar) {
                    while (predicado(consomeTokens[countGlobal]) && predicado && countGlobal <= consomeTokens.length - 1) {
                        uniaop += consomeTokens[countGlobal];
                        predi.setReconhecido(true);
                        predi.setToken(uniaop);
                        predi.setType("Predicado");
                        predi.setPosition(countGlobal);
                        countGlobal++;
                        reconhecido = true;
                    }
                }
                predicado = false;
                if (reconhecido) {
                    conj.add(predi);
                    reconhecido = false;
                }
                if (countGlobal < countChar) {
                    if (pe(consomeTokens[countGlobal]) && countGlobal <= consomeTokens.length - 1) {
                        // sintaxeBeans s = new sintaxeBeans();
                        parentE.setReconhecido(true);
                        parentE.setToken("(");
                        parentE.setType("PE");
                        parentE.setPosition(countGlobal);
                        countGlobal++;
                        reconhecido = true;

                    }
                }
                if (reconhecido) {
                    conj.add(parentE);
                    reconhecido = false;
                }
                if (countGlobal < countChar) {
                    
                    while (constante(consomeTokens[countGlobal]) && countGlobal <= consomeTokens.length - 1) {
                        //sintaxeBeans s = new sintaxeBeans();
                        uniaoc += consomeTokens[countGlobal];
                        cons.setReconhecido(true);
                        cons.setToken(uniaoc);
                        cons.setType("Constante");
                        cons.setPosition(countGlobal);
                        countGlobal++;
                        reconhecido = true;

                    }
                }
                if (reconhecido) {
                    conj.add(cons);
                    reconhecido = false;
                }

                if (countGlobal < countChar) {
                    if (variavel(consomeTokens[countGlobal]) && countGlobal <= consomeTokens.length - 1) {
                        //sintaxeBeans s = new sintaxeBeans();
                        var.setReconhecido(true);
                        var.setToken(consomeTokens[countGlobal]);
                        var.setType("Varivel");
                        var.setPosition(countGlobal);
                        countGlobal++;
                        reconhecido = true;

                    }
                }
                if (reconhecido) {
                    conj.add(var);
                    reconhecido = false;
                }
                if (countGlobal < countChar) {
                    if (countGlobal <= (consomeTokens.length - 1) && pd(consomeTokens[countGlobal])) {
                        // sintaxeBeans s = new sintaxeBeans();
                        parentD.setReconhecido(true);
                        parentD.setToken(")");
                        parentD.setType("PD");
                        parentD.setPosition(countGlobal);

                        countGlobal++;
                        reconhecido = true;
                    }
                }
                if (reconhecido) {
                    conj.add(parentD);
                    reconhecido = false;
                }

                if (countGlobal < countChar) {
                    if (virgula(consomeTokens[countGlobal]) && countGlobal <= consomeTokens.length - 1) {
                        //sintaxeBeans s = new sintaxeBeans();
                        virg.setReconhecido(true);
                        virg.setToken(",");
                        virg.setType("Virgula");
                        virg.setPosition(countGlobal);
                        countGlobal++;
                        reconhecido = true;
                    }
                }

                if (reconhecido) {
                    conj.add(virg);
                    reconhecido = false;
                }
                
 if (countGlobal <= countChar) {
                if (ponto(consomeTokens[countGlobal])) {
                    //sintaxeBeans s = new sintaxeBeans();
                    ponto.setReconhecido(true);
                    ponto.setToken(".");
                    ponto.setType("PT");
                    ponto.setPosition(countGlobal);
                    reconhecido = true;
                    countGlobal += 2;
                }
                }

                if (reconhecido) {
                    conj.add(ponto);
                    reconhecido = false;
                }

                if (countGlobal <= countChar) {
                    if (consomeTokens[countGlobal].equals(simboloNr) && consomeTokens[countGlobal] != null) {
                        //caso a o simbolo retorne e nao seja reconhecido
                        // percorrer 3 vezes na tentativa de ser reconhecido                
                        String simboloReturn = consomeTokens[countGlobal];
                        simbolonaoreconhecido++;
                    }
                    if (simbolonaoreconhecido == 2) {
                        simbolonaoreconhecido = 0;
                        sintaxeBeans s = new sintaxeBeans();
                        s.setReconhecido(false);
                        s.setType("NReconhecido");
                        s.setToken(consomeTokens[countGlobal]);
                        s.setPosition(countGlobal);
                        conj.add(s);
                        countGlobal++;
                    }

                    simboloNr = consomeTokens[countGlobal];
                }
                // Para noa estourar vetor
                 if (countGlobal <= countChar) {if(consomeTokens[countGlobal].equals("1")){countGlobal+=2;}}
            }
            Nreconhecido = false;
        } }catch(Exception e){
        e.getMessage();}

        return conj;
    }

    /*Resolve o vetor*/
    int i = countGlobal;

    public boolean umOuMais(String consomeTokens[]) {
        boolean test = false;
        /* Busca em profundidade com recursividade */

        if (constante(consomeTokens[countGlobal])
                ^ variavel(consomeTokens[countGlobal])
                ^ funcao(consomeTokens[countGlobal])) {

            return true;
        }
        consomeTokens[countGlobal] += consomeTokens[i];
        i++;

        return umOuMais(consomeTokens);
    }

    public boolean predicado(String predicado) {
        sintaxeBeans s = new sintaxeBeans();
        boolean test = predicado.matches("[a-z]+");
        s.setReconhecido(true);
        s.setToken(predicado);
        if (test == true) {
            setTrue.setReconhecido(true);
            setTrue.setToken(predicado);
        }
        analisador.add(s);
        return test;
    }

    public boolean pe(String predicado) {
        sintaxeBeans s = new sintaxeBeans();

        boolean test = predicado.equals("(");
        s.setToken(predicado);
        analisador.add(s);
        if (test == true) {
            setTrue.setReconhecido(true);
            setTrue.setToken(predicado);
        }
        return test;
    }

    public boolean pd(String predicado) {
        sintaxeBeans s = new sintaxeBeans();

        boolean test = predicado.equals(")");
        s.setReconhecido(true);
        s.setToken(predicado);
        analisador.add(s);
        if (test == true) {
            setTrue.setReconhecido(true);
            setTrue.setToken(predicado);
        }
        return test;
    }

    public boolean constante(String predicado) {
        sintaxeBeans s = new sintaxeBeans();

        boolean test = predicado.matches("[a-z]+");
        s.setReconhecido(true);
        s.setToken(predicado);
        analisador.add(s);
        if (test == true) {
            setTrue.setReconhecido(true);
            setTrue.setToken(predicado);
        }
        return test;
    }

    public boolean variavel(String predicado) {
        sintaxeBeans s = new sintaxeBeans();

        boolean test = predicado.matches("[A-Z]?");
        s.setReconhecido(true);
        s.setToken(predicado);
        analisador.add(s);
        if (test == true) {
            setTrue.setReconhecido(true);
            setTrue.setToken(predicado);
        }
        return test;
    }

    public boolean funcao(String predicado) {
        sintaxeBeans s = new sintaxeBeans();
        boolean test = false;
        //java.util.List<sintaxeBeans> conj = new ArrayList<sintaxeBeans>();

        //boolean test = predicado.matches();
        // boolean test = predicado.matches(".+[A-Z].+");
        if (predicado("")
                && pe("")
                && constante("")
                && pd("")) {
            test = true;
        };

        s.setReconhecido(true);
        s.setToken(predicado);
        analisador.add(s);
        if (test == true) {
            setTrue.setReconhecido(true);
            setTrue.setToken(predicado);
        }
        return test;
    }

    public boolean virgula(String predicado) {
        sintaxeBeans s = new sintaxeBeans();
        boolean test = predicado.equals(",");
        s.setReconhecido(true);
        s.setToken(predicado);
        analisador.add(s);
        if (test == true) {
            setTrue.setReconhecido(true);
            setTrue.setToken(predicado);
        }
        return test;
    }

    public boolean ponto(String predicado) {
        sintaxeBeans s = new sintaxeBeans();
        boolean test = predicado.equals(".");
        s.setReconhecido(true);
        s.setToken(predicado);
        analisador.add(s);

        if (test == true) {
            setTrue.setReconhecido(true);
            setTrue.setToken(predicado);
        }

        return test;
    }

    //<PREDICADO><PE>((<CONSTANTE>|<VARIAVEL>|funcao())+<V>)<PD><PT>
}
