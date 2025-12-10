package model;

import view.Janela;

import javax.swing.JTextField;

public class Calculadora {
    private char operacao;
    private String operando1;
    private String operando2;
    private String visor;
    private double resultado;

    public Calculadora(){
        this.operacao = ' ';
        this.operando1 = "";
        this.operando2 = "";
        this.visor = "";
        this.resultado = 0;
    }

    public void atribuirOperadores(char c){
        if (operacao == ' '){
            if (c == '.' && operando1.equals("")){
                operando1 += 0;
            }
            operando1 += c;
            visor = operando1;
        }
        else{
            if (c == '.' && operando2.equals("")){
                operando2 += 0;
            }
            operando2 += c;
            visor = operando1 + operacao + operando2;
        }
    }

    public void decidirOperacao(char valor, JTextField jtext){
        if (valor == 'C'){
            this.limparTudo();
        }

        if (valor == '⌫'){
            if (operacao == ' '){
                try {
                    operando1 = operando1.substring(0, operando1.length() - 1);
                    visor = operando1;
                }
                catch (StringIndexOutOfBoundsException e){
                    Janela.emptyfied(jtext);
                }
            }
            else if(operando2.equals("")){
                operacao = ' ';
                visor = operando1;
            }
            else{
                operando2 = operando2.substring(0, operando2.length()-1);
                visor = operando1 + operacao + operando2;
            }
        }

        else if (valor == '.'){
            atribuirOperadores(valor);
        }
        else if (valor == '+' || valor == '-' || valor == '/' || valor == '*' || valor == '%' || valor == '^'){
            operacao = valor;
            visor = operando1 + operacao;
        }

        else if (valor == '='){
            try {
                switch (operacao) {
                    case '+':
                        resultado = Double.valueOf(operando1) + Double.valueOf(operando2);
                        break;
                    case '-':
                        resultado = Double.valueOf(operando1) - Double.valueOf(operando2);
                        break;
                    case '/':
                        resultado = Double.valueOf(operando1) / Double.valueOf(operando2);
                        break;
                    case '*':
                        resultado = Double.valueOf(operando1) * Double.valueOf(operando2);
                        break;
                    case '%':
                        resultado = Double.valueOf(operando2) / 100 * Double.valueOf(operando1);
                        break;
                    case '^':
                        double total = 1;
                        double op1 = Double.valueOf(operando1);
                        for (int i = 1; i <= Integer.valueOf(operando2); i++) {
                            total = total * op1;
                        }
                        resultado = total;
                        break;
                }
                operando1 = String.valueOf(resultado);
                operacao = ' ';
                operando2 = "";
                visor = operando1;
            }
            catch (NumberFormatException e){
                Janela.emptyfied(jtext); //efeito
            }
        }
    }

    public void limparTudo(){
        this.operacao = ' ';
        this.operando1 = "";
        this.operando2 = "";
        this.visor = "";
        this.resultado = 0;
    }

    public String getVisor(){
        return visor;
    }
}
