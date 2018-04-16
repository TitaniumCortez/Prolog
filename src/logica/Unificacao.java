/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Arrays;

/**
 *
 * @author RCotez
 */
public class Unificacao {

    public void funcaoUnificar(String e1[], String token1[], String e2[], String token2[]) {

        String teste = "tes";
        boolean condicao = true;
        System.out.println("Unificação E1" + Arrays.toString(e1));
        System.out.println("Unificação E2" + Arrays.toString(e2));

        for (int i = 0; i <= e1.length - 2; i++) {

            if (e1[i].equals("PE")) {
                String tmp = e1[i]; // avanca as constante para dentro do parentese  (joao x
                e1[i] = e1[i - 1]; // na forma de tipo
                e1[i - 1] = tmp;
            }

        }

        for (int i = 0; i <= e2.length - 2; i++) {
            if (e2[i].equals("PE")) {
                String tmp = e2[i]; // avanca as constante para dentro do parentese  (joao x
                e2[i] = e2[i - 1];// na forma de tipo
                e2[i - 1] = tmp;
            }
        }
        for (int i = 0; i <= e1.length - 2; i++) {
            if (token1[i].equals("(")) {
                String tmp = token1[i]; // avanca as constante para dentro do parentese  (joao x
                token1[i] = token1[i - 1];// Pelo token
                token1[i - 1] = tmp;
            }

        }

        for (int i = 0; i <= e1.length - 2; i++) {
            if (token2[i].equals("(")) {
                String tmp = token2[i]; // avanca as constante para dentro do parentese  (joao x
                token2[i] = token2[i - 1]; // pelo token
                token2[i - 1] = tmp;
            }

        }

        // Cria novos vetor apos organização emanipulas a virgulas 
        String E1[] = new String[e1.length - 1];
        for (int i = 0; i <= e1.length - 2; i++) {
            if (!e1[i].equals("Virgula")) {
                E1[i] = e1[i];
            } else {
                String tmp = e1[i];// envia as virgulas para o final da expressao  (joao x
                E1[i] = e1[i + 1]; // pelo token  
                e1[i + 1] = tmp;
            }
        }

        String E2[] = new String[e2.length - 1];
        for (int i = 0; i <= e2.length - 2; i++) {
            if (!e2[i].equals("Virgula")) {
                E2[i] = e2[i];
            } else {
                String tmp = e2[i];// envia as virgulas para o final da expressao  (joao x
                E2[i] = e2[i + 1]; // pelo token  
                e2[i + 1] = tmp;
            }
        }
        String Tokens1[] = new String[token1.length - 1];
        for (int i = 0; i <= token1.length - 2; i++) {
            if (!token1[i].equals(",")) {
                Tokens1[i] = token1[i];
            } else {
                String tmp = token1[i];// envia as virgulas para o final da expressao  (joao x
                Tokens1[i] = token1[i + 1]; // pelo token  
                token1[i + 1] = tmp;
            }
        }

        String Tokens2[] = new String[token2.length - 1];
        for (int i = 0; i <= token2.length - 2; i++) {
            if (!token2[i].equals(",")) {
                Tokens2[i] = token2[i];
            } else {
                String tmp = token2[i];// envia as virgulas para o final da expressao  (joao x
                Tokens2[i] = token2[i + 1]; // pelo token  
                token2[i + 1] = tmp;
            }

        }

        System.out.println("Unificação E1 entrada" + Arrays.toString(E1));
        System.out.println("Unificação E2 entrada" + Arrays.toString(E2));
        System.out.println("Unificação Token1 entrada" + Arrays.toString(Tokens1));
        System.out.println("Unificação Token1 engtrad" + Arrays.toString(Tokens2));

        String unificada[] = null;

        int init = 0;
        int retorno = 0;
        int match = 0;

        while (condicao) {
            if (init <= (E1.length - 1) && init <= (E2.length - 1)) {
                switch (E1[init]) {

                    //E1 e E2 é predicado ou lista vazia
                    case "Predicado":
                        if (E1[init] == E2[init] && Tokens1[init].equals(Tokens2[init])) {
                            System.out.println("{}");
                            retorno++;
                            //unificada[init] = String.valueOf(E1[init]);
                        } else {
                            System.out.println("Falha");
                            condicao = false;
                        }
                        break;

                    //E1 e E2 é constante ou lista vazia                        
                    case "Constante":

                        if (E1[init] == E2[init] && Tokens1[init].equals(Tokens2[init])) {
                            System.out.println("{}");
                            retorno++;
                        } else {
                            //E2 é uma variavel 
                            if (E2[init].equals("Varivel")) {
                                System.out.println("{" + E1[init] + "/" + E2[init] + "}");
                                System.out.println(Tokens2[init] + " = " + Tokens1[init]);
                                match++;

                            } else {

                                if (E2[init].equals("PE") //(func consta )
                                        && E2[init + 1].equals("Constante")
                                        && E2[init + 2].equals("Constante")
                                        && E2[init + 3].equals("PD")) {
                                    System.out.println("->"+E2[init+1] + "(" + E2[init + 2] + ")" + "/"+ E1[init] );
                                    System.out.println(Tokens2[init+1] + "(" + Tokens2[init + 2] + ")" + " ="+ Tokens1[init] );
                                    match++;
                                } else {
                                    System.out.println("Falha");
                                    condicao = false;
                                }
                            }
                        }
                        break;
                    // E1 é uma variavel 

                    case "Varivel":
                        if (E1[init] == E2[init]) {
                            System.out.println("FALHA");
                            condicao = false;
                            match++;
                        }
                        if (E2[init].equals("Constante")) {
                            System.out.println("{" + E2[init] + "/" + E1[init] + "}");
                            System.out.println(Tokens1[init] + " = " + Tokens2[init]);

                            match++;

                        }

                        break;

                    case "PE":
                        //unificacao de funcao com constante                                               
                        if (E1[init + 1].equals("Constante") //(func consta consta )                                
                                && E1[init + 2].equals("Constante")
                                && E1[init + 3].equals("PD")) {

                            System.out.println("->"+E1[init + 1] + "(" + E1[init + 2] + ")" + "/" + E2[init]);
                            System.out.println(Tokens1[init + 1] + "(" + Tokens1[init + 2] + ")" + " =" + Tokens2[init]);
                            retorno++;
                        }

                        break;
                    case "Virgula":
                        System.out.println(",");
                        break;

                    case "PD":

                        break;

                    case "PT":
                        condicao = false;
                        break;

                    default:
                        System.out.println("" + E1[init]);
                        condicao = false;
                        System.out.println("Não Unificavel");
                }
            }
            init++;
        }
        if (retorno > match && match == 0) {
            System.out.println("TRUE");
        }

    }
}
