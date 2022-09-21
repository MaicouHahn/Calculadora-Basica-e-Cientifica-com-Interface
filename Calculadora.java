package Calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class Calculadora implements ActionListener{//Metodo action performed quando chamado(não esquecer porque ele não adiciona sozinho), esse metodo é para reconhecimento da função do botão
    
    //==================================================Paineis e janelas principais=================================
    JComboBox<String>combo=new JComboBox<String>();// Combobox para fazer a troca das calculadoras
    JFrame tela=new JFrame("Calculadora");// Janela principal do programa
    JPanel painel=new JPanel();// Painel separado que vai os botoes decimais e numericos em um grid separado
    JPanel painel2=new JPanel();// Historico de digitação da calculadora
    JTextField texto=new JTextField();// Variavel que vai receber os valores para depois converter em double(Tela dos numeros) 
    JLabel historico=new JLabel(); // Historico
    Font fonte= new Font("Courier New",Font.ITALIC,18);// Fonte customizada para os botões principais
    Font fonte2= new Font("Courier New",Font.ITALIC,30);// Fonte do historico e do painel de digitação
    //==================================================botoes e nomes das funções========================================

    JButton[] numBotao = new JButton [10];// Para percorrer os numeros decimais
    JButton[] funcBotao =new JButton [10];// Para percorrer as funçoes normais
    JButton[] funcCBotao = new JButton[8];// Para percorrer as funçoes cienntificas
    JButton adicBotao=new JButton("+");// Botões criados e com seus nomes declarados
    JButton subBotao= new JButton("-");
    JButton multBotao=new JButton("*");
    JButton divBotao= new JButton("/");
    JButton decBotao= new JButton(".");
    JButton iguBotao= new JButton("=");
    JButton delBotao= new JButton("<-");
    JButton limpBotao= new JButton(" C ");
    JButton quadrado=new JButton("x²");
    JButton negBotao=new JButton("(-)");
    JButton porcentagem= new JButton("%");
    JButton fatorial= new JButton("n!");
    JButton seno= new JButton("Sen");
    JButton cosseno= new JButton("Cos");
    JButton tangente= new JButton("Tg");
    JButton exponencial= new JButton("Exp");
    JButton raizquadrada= new JButton("√");
    JButton log10= new JButton("Lg10");
    
//================================================== Variaveis para uso global=============================================
    double num1=0,num2=0,resultado=0;// As variaveis que irão ser utilizadas (irão ser convertidas de string para double e double para string novamente e armazenadas em texto)
    String hist;// Variavel switch para converter num2 em string e mostar no historico
    char operador; // Para um switch que vai identificar e efetuar o calculo da função

         public Calculadora(){//============Construtor da classe(Janela da Calculadora)=============
        
            //=================Formatação e posicionamento da janela principal e da janela de texto===============================

            historico.setBounds(50, 50, 300, 50);
            historico.setHorizontalAlignment(SwingConstants.RIGHT);

            tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Fechar quando aperta no X
            tela.setSize(420, 650);// Janela principal que contém todo o programa
            tela.setLayout(null);// Não ira utilizar um layout na tela principal, sera declarado com setBounds
            tela.setLocationRelativeTo(null);// Inicia no centro da tela
            tela.setResizable(false);// Para não permitir que mude o tamanho da calculadora quando arrasta com o mouse
            texto.setHorizontalAlignment(SwingConstants.RIGHT); // Comando para iniciar a escrita da direita para esquerda    
            texto.setBounds(50, 100, 300, 50);// Posicão do campo de texto aonde vai os numeros
            combo.setBounds(50, 160, 80, 20);
            texto.setEditable(true);// Para o usuario digitar no campo do texto ou não(true ou false)

            //=============Botões relacionados a arrays para usar um for e adicionar funções para eles==============

            funcBotao[0]=adicBotao;
            funcBotao[1]=subBotao;
            funcBotao[2]=multBotao;
            funcBotao[3]=divBotao;
            funcBotao[4]=decBotao;
            funcBotao[5]=iguBotao;
            funcBotao[6]=delBotao;
            funcBotao[7]=limpBotao;
            funcBotao[8]=negBotao;
            funcBotao[9]=quadrado;
            funcCBotao[0]=porcentagem;
            funcCBotao[1]=fatorial;
            funcCBotao[2]=seno;
            funcCBotao[3]=cosseno;
            funcCBotao[4]=tangente;
            funcCBotao[5]=exponencial;
            funcCBotao[6]=raizquadrada;
            funcCBotao[7]=log10;
            
            //=================================Função de leitura nos botões e setando as fontes=================================
            combo.addActionListener(this);//ComboBox

            for(int i=0; i<10; i++){//===========For das funções Basicas===========

                funcBotao[i].addActionListener(this);//Cada botao vai receber a fuçao de leitura
                funcBotao[i].setFont(fonte);//Mudança da fonte
                funcBotao[i].setFocusable(false);//Retira os riscos da selecao que parecem erros

            }
            
            for(int i=0 ; i<8 ; i++){//=========For das funções cientificas========

                funcCBotao[i].addActionListener(this);
                funcCBotao[i].setFont(fonte);
                funcCBotao[i].setFocusable(false);

            }
            for(int i=0; i<10; i++){//=========For dos botoes decimais============

                numBotao[i]=new JButton(String.valueOf(i));//Criaçao e numeraçao
                numBotao[i].addActionListener(this);//Cada botao vai receber a fuçao de leitura
                numBotao[i].setFont(fonte);//Mudança da fonte
                numBotao[i].setFocusable(false);//Retira os riscos da selecao que parecem erros

            }
            //===================================Fim das adições de leitura e fontes============================================

            //====================================Layout e mudar as cores================================
            combo.setBackground(Color.LIGHT_GRAY);
            combo.setForeground(Color.darkGray);
            for(int i=0 ; i<10;i++){//=====Decimais========

                numBotao[i].setBackground(Color.lightGray);
                numBotao[i].setForeground(Color.darkGray);
            
            }
            for(int i=0 ; i<8;i++){//=====Cientifica========

                funcCBotao[i].setBackground(Color.gray);
                funcCBotao[i].setForeground(Color.darkGray);   
            }
            for(int i=0 ; i<10;i++){//=====Fuunções basicas========

                funcBotao[i].setBackground(Color.lightGray);
                funcBotao[i].setForeground(Color.darkGray);
            }

            //========================================Fim do layout das cores============================

            //====OBS: Os botoes de deletar, limpar, apagar,numero negativo, trocar para cientifica e voltar para a calculadora normal estao separados====
            
            //====Formatação desses botões que citei acima=====
            log10.setFont(new Font("Courier New",Font.ITALIC,15));
            texto.setFont(fonte2);
            historico.setFont(fonte2);

            //=====================fim dos botoes inferiores============================

            //=====================Paineis=============================================
            painel.setBounds(50, 190, 300, 375);// painel que esta os botões decimais e as funçoes deles
            painel.setLayout(new GridLayout(5,4,10,10));//um grid de 4x4 com 10 pixels de espaço cada espaço
            painel2.setBounds(360, 190, 150, 298);//painel dos botoes da calc cientifica
            painel2.setLayout(new GridLayout(4,4,10,10));//layout dos botoes da calc cientifica

            //=====================Fim do layout dos paineis de botões====================================

            //=====================Adicião dos botões em seus respectivos paineis=========================
            //=======ComboBOX=============
            combo.addItem("Padrão");
            combo.addItem("Cientifica");
            //=======Painel dos botoes e funções basicas=======
            painel.add(numBotao[7]);
            painel.add(numBotao[8]);
            painel.add(numBotao[9]);
            painel.add(adicBotao);
            painel.add(numBotao[4]);
            painel.add(numBotao[5]);
            painel.add(numBotao[6]);
            painel.add(subBotao);
            painel.add(numBotao[1]);
            painel.add(numBotao[2]);
            painel.add(numBotao[3]);
            painel.add(multBotao);
            painel.add(decBotao);
            painel.add(numBotao[0]);
            painel.add(iguBotao);
            painel.add(divBotao);
            painel.add(delBotao);
            painel.add(limpBotao);
            painel.add(negBotao);
            painel.add(quadrado);
            //=======painel Calculadora cientifica=======
            painel2.add(porcentagem);
            painel2.add(fatorial);
            painel2.add(seno);
            painel2.add(cosseno);
            painel2.add(tangente);
            painel2.add(exponencial);
            painel2.add(raizquadrada);
            painel2.add(log10);
            painel2.setVisible(false);
            //=======painel Janela principal======
            tela.add(combo);
            tela.add(texto);// Adição do campo de texto para ver os numeros digitados
            tela.add(historico);
            tela.add(painel);
            tela.add(painel2);
            tela.setVisible(true);

    }
    
public static void main(String[] args) {//=========Objeto principal==========
    
    new Calculadora();
}


//============================================Metodos dos botões==========================================

public void actionPerformed(ActionEvent e){

    for(int i=0; i<10; i++){//================Botões de 0 a 9========================

        if(e.getSource()==numBotao[i]){

            texto.setText(texto.getText().concat(String.valueOf(i)));// Concatena o numero que foi digitado pelo botão na linha dos numeros
        }
    }
    if(combo.getSelectedIndex()==1){//=============Mostra o painel cientifico==================
        
        texto.setBounds(50, 100, 460, 50);
        historico.setBounds(50, 50, 460, 50);
        tela.setSize(580, 650);
        painel2.setVisible(true);
       
    }
    if(combo.getSelectedIndex()==0){//=============Esconde o painel cientifico================

        tela.setSize(420, 650);
        texto.setBounds(50, 100, 300, 50);
        historico.setBounds(50, 50, 300, 50);
        painel2.setVisible(false);
        
    }

    if(e.getSource()==decBotao){//=============Esse loop é para colocar o . nos numeros============

        texto.setText(texto.getText().concat("."));// Se apertarado o . na calculadora ele concatena na string

    }
    if(e.getSource()==adicBotao){//============ Soma ================================
        
        num1= Double.parseDouble(texto.getText());
        historico.setText(String.valueOf(num1)+" + ");
        operador='+';
        texto.setText("");

    }
    if(e.getSource()==subBotao){//============== Subtração =======================

        num1= Double.parseDouble(texto.getText());
        historico.setText(String.valueOf(num1)+" - ");
        operador='-';
        texto.setText("");
 
    }
    if(e.getSource()==multBotao){//=============== Multiplicação ======================

        num1= Double.parseDouble(texto.getText());
        historico.setText(String.valueOf(num1)+" * ");
        operador='*';
        texto.setText("");
 
     }
     if(e.getSource()==divBotao){//================ Divisão ===================

        num1= Double.parseDouble(texto.getText());
        historico.setText(String.valueOf(num1)+" / ");
        operador='/';
        texto.setText("");
 
     }
     if(e.getSource()==quadrado){//================ Quadrado ===================
        num1= Double.parseDouble(texto.getText());
        resultado=num1*num1;
        historico.setText(String.valueOf(num1)+"² = ");
        texto.setText(String.valueOf(resultado));
     }
     if(e.getSource()==porcentagem){//================ Porcentagem ===================
        num1= Double.parseDouble(texto.getText());
        historico.setText(String.valueOf(num1)+" % de ");
        operador='%';
        texto.setText("");

     }
     if(e.getSource()==fatorial){//================ Fatorial ===================

        resultado=1;
        num1=Double.parseDouble(texto.getText());
        for(int i =1 ; i<= num1 ; i++){
            
            resultado= resultado*i;
        }
        historico.setText(num1+"! = ");
        texto.setText(String.valueOf(resultado));
     }
     if(e.getSource()==seno){//======================= Seno ======================
        double sen;
        num1=Double.parseDouble(texto.getText());
        historico.setText("Seno de "+num1+"° =");
        sen=Math.toRadians(num1);        
        texto.setText(String.valueOf(Math.sin(sen)));
     }
     if(e.getSource()==cosseno){//======================= Cosseno ======================
        double cossen;
        num1=Double.parseDouble(texto.getText());
        historico.setText("Cosseno de "+num1+"° =");
        cossen=Math.toRadians(num1);        
        texto.setText(String.valueOf(Math.cos(cossen)));
     }
     if(e.getSource()==tangente){//======================= Tangente ======================
        double tg;
        num1=Double.parseDouble(texto.getText());
        historico.setText("Tangente de "+num1+"° =");
        tg=Math.toRadians(num1);        
        texto.setText(String.valueOf(Math.tan(tg)));
     }
     if(e.getSource()==exponencial){//===================== Exponencial =====================

        num1=Double.parseDouble(texto.getText());
        operador='^';
        historico.setText(String.valueOf(num1)+"^");
        texto.setText("");
     }

     if(e.getSource()==raizquadrada){//=================== Raiz Quadrada =====================

        num1=Double.parseDouble(texto.getText());
        resultado=Math.sqrt(num1);
        historico.setText("√"+num1+" = ");
        texto.setText(String.valueOf(resultado));
     }
     if(e.getSource()==log10){//========================== Log de 10 =========================

        num1=Double.parseDouble(texto.getText());

        texto.setText(String.valueOf(Math.log10(num1)));

        historico.setText("Log10 "+num1+" = ");

     }


     if(e.getSource()==iguBotao){//=================Botão de igual====================
        
        num2= Double.parseDouble(texto.getText());
        hist= String.valueOf(num2);
        switch(operador){

            case'+':
                historico.setText(historico.getText().concat(hist)+" = ");
                resultado=num1+num2;
                break;
            case'-':
                historico.setText(historico.getText().concat(hist)+" = ");
                resultado=num1-num2;
                break;
            case'*':
                historico.setText(historico.getText().concat(hist)+" = ");
                resultado=num1*num2;
                break;
            case'/':
                historico.setText(historico.getText().concat(hist)+" = ");
                resultado=num1/num2;
                break;
            case'%':
                historico.setText(historico.getText().concat(hist)+" = ");
                resultado=(num1/100)*num2;
                break;
            case'^':// ==== Calculo do exponencial =====
                resultado=1;
                historico.setText(historico.getText().concat(String.valueOf(num2))+" = ");
                
                for(double i =1 ; i<= num2 ; i++){
                    resultado=resultado*num1;
                }
            break;       
        }

        texto.setText(String.valueOf(resultado));// Texto recebe o valor da variavel e converte em string
        //num1=resultado; // Para caso queira utilizar o mesmo resultado calculado

     }

     if(e.getSource()==limpBotao){//================ Botão Clear ==============

        texto.setText("");
        historico.setText("");
 
     }
 
     if(e.getSource()==delBotao){//================= Botão <- (del)==================

        String string = texto.getText();

        texto.setText("");

        for(int i = 0; i < string.length()-1 ; i++){// Copia e cola a string anterior com uma casa a menos

            texto.setText(texto.getText()+string.charAt(i));
        }
 
     }
     if(e.getSource()==negBotao){// Converte em numero negativo multiplicando por -1

        double temp = Double.parseDouble(texto.getText());// Converte string em double
        temp=temp*-1;
        texto.setText(String.valueOf(temp));// Converte double em string
 
     }

}
//==============================================Fim dos metodos dos botões=============================================

}
