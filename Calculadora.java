package Calculadora;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculadora implements ActionListener{//Metodo action performed quando chamado(não esquecer porque ele não adiciona sozinho), esse metodo é para reconhecimento da função do botão
    
    //==================================================Paineis e janelas principais=================================

    JFrame tela=new JFrame("Calculadora");// Janela principal do programa
    JPanel painel=new JPanel();// Aqui o painel separado que vai os botoes decimais e numericos em um grid separado
    JPanel painel2=new JPanel();
    JTextField texto=new JTextField();// Essa variavel que vai receber os valores para depois converter em double(Tela dos numeros)  
    Font fonte= new Font("Courier New",Font.ITALIC,30);// Fonte customizada para os botões principais
    Font fonte2= new Font("Courier New",Font.ITALIC,18);// Fonte para os botoes da ultima fileira inferior

    //==================================================botoes e nomes das funções========================================

    JButton[] numBotao = new JButton [10];// Para percorrer os numeros decimais
    JButton[] funcBotao =new JButton [9];// Para percorrer as funçoes normais
    JButton[] funcCBotao = new JButton[8];// Para percorrer as funçoes cienntificas
    JButton adicBotao=new JButton("+");// Botões criados e com seus nomes declarados
    JButton subBotao= new JButton("-");
    JButton multBotao=new JButton("*");
    JButton divBotao= new JButton("/");
    JButton decBotao= new JButton(".");
    JButton iguBotao= new JButton("=");
    JButton delBotao= new JButton("<-");
    JButton limpBotao= new JButton(" C ");
    JButton negBotao=new JButton("(-)");
    JButton calc2 = new JButton("CI");
    JButton calc2V=new JButton("<");
    JButton porcentagem= new JButton("%");
    JButton fatorial= new JButton("f");
    JButton n3= new JButton("C");// Esses aqui ainda estou fazendo as funções, deixei como letras para identificar melhor
    JButton n4= new JButton("D");
    JButton n5= new JButton("E");
    JButton n6= new JButton("F");
    JButton n7= new JButton("G");
    JButton n8= new JButton("H");

//================================================== Variaveis para uso global=============================================
    double num1=0,num2=0,resultado=0;// As variaveis que irão ser utilizadas (irão ser convertidas de string para double e double para string novamente e armazenadas em texto)
    char operador; // Para um switch que vai identificar e efetuar o calculo da função

         public Calculadora(){//============Construtor da classe(Janela da Calculadora)=============
        
            //=================Formatação e posicionamento da janela principal e da janela de texto===============================

            tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Fechar quando aperta no X
            tela.setSize(420, 550);// Janela principal que contém todo o programa
            tela.setLayout(null);// Não ira utilizar um layout na tela principal, sera declarado com setBounds
            tela.add(texto);// Adição do campo de texto para ver os numeros digitados
            tela.setLocationRelativeTo(null);// Inicia no centro da tela
            tela.setResizable(false);// Para não permitir que mude o tamanho da calculadora quando arrasta com o mouse   
            texto.setHorizontalAlignment(SwingConstants.RIGHT); // Comando para iniciar a escrita da direita para esquerda    
            texto.setBounds(50, 25, 300, 50);// Posicão do campo de texto aonde vai os numeros
            texto.setFont(fonte);
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
            funcCBotao[0]=porcentagem;
            funcCBotao[1]=fatorial;
            funcCBotao[2]=n3;
            funcCBotao[3]=n4;
            funcCBotao[4]=n5;
            funcCBotao[5]=n6;
            funcCBotao[6]=n7;
            funcCBotao[7]=n8;

            //=================================Função de leitura nos botões e setando as fontes=================================
            
            calc2.addActionListener(this);//Botão de clique para mostrar o painel cientifico
            calc2V.addActionListener(this);//Botão de cique para esconder o painel cientifico

            for(int i=0; i<9; i++){//===========For das funções Basicas===========

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

            for(int i=0 ; i<10;i++){//=====Decimais========

                numBotao[i].setBackground(Color.lightGray);
                numBotao[i].setForeground(Color.darkGray);
            
            }
            for(int i=0 ; i<8;i++){//=====Cientifica========

                funcCBotao[i].setBackground(Color.gray);
                funcCBotao[i].setForeground(Color.darkGray);   
            }
            for(int i=0 ; i<9;i++){//=====Fuunções basicas========

                funcBotao[i].setBackground(Color.lightGray);
                funcBotao[i].setForeground(Color.darkGray);
            }
            //=====Os dois botoes para mudar o modo da calculadora=====
            calc2.setBackground(Color.lightGray);
            calc2.setForeground(Color.darkGray);
            calc2V.setBackground(Color.gray);
            calc2V.setForeground(Color.darkGray);

            //========================================Fim do layout das cores============================

            //====OBS: Os botoes de deletar, limpar, apagar,numero negativo, trocar para cientifica e voltar para a calculadora normal estao separados====
            
            //====Formatação desses botões que citei acima=====
            delBotao.setBounds(51, 410,68, 63);
            delBotao.setFont(fonte2);
            limpBotao.setBounds(129, 410,67, 63);
            limpBotao.setFont(fonte2);
            negBotao.setBounds(205, 410, 68, 63);
            negBotao.setFont(fonte2);

            //=====================fim dos botoes inferiores============================

            calc2.setBounds(281, 410, 68, 63);//botão que abre a calc cientifica
            painel.setBounds(50, 100, 300, 300);// painel que esta os botões decimais e as funçoes deles
            painel.setLayout(new GridLayout(4,4,10,10));//um grid de 4x4 com 10 pixels de espaço cada espaço
            painel2.setBounds(360, 100, 150, 300);//painel dos botoes da calc cientifica
            painel2.setLayout(new GridLayout(4,4,10,10));//layout dos botoes da calc cientifica

            //=====================Fim do layout dos paineis de botões====================================

            //=====================Adicião dos botões em seus respectivos paineis=========================

            //=======Painel dos botoes e funções basicas=======
            painel.add(numBotao[1]);
            painel.add(numBotao[2]);
            painel.add(numBotao[3]);
            painel.add(adicBotao);
            painel.add(numBotao[4]);
            painel.add(numBotao[5]);
            painel.add(numBotao[6]);
            painel.add(subBotao);
            painel.add(numBotao[7]);
            painel.add(numBotao[8]);
            painel.add(numBotao[9]);
            painel.add(multBotao);
            painel.add(decBotao);
            painel.add(numBotao[0]);
            painel.add(iguBotao);
            painel.add(divBotao);
            //=======painel Calculadora cientifica=======
            painel2.add(porcentagem);
            painel2.add(fatorial);
            painel2.add(n3);
            painel2.add(n4);
            painel2.add(n5);
            painel2.add(n6);
            painel2.add(n7);
            painel2.add(n8);
            painel2.setVisible(false);
            //=======painel Janela principal======
            tela.add(painel);
            tela.add(delBotao);
            tela.add(limpBotao);
            tela.add(negBotao);
            tela.add(calc2);
            tela.add(calc2V);
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
    if(e.getSource()==calc2){//=============Mostra o painel cientifico==================
        
        texto.setBounds(50, 25, 460, 50);
        tela.setSize(580, 550);
        delBotao.setBounds(51, 410,68, 63);
        delBotao.setFont(fonte2);
        limpBotao.setBounds(129, 410,67, 63);
        limpBotao.setFont(fonte2);
        negBotao.setBounds(205, 410, 68, 63);
        negBotao.setFont(fonte2);
        calc2.setBounds(281, 410, 68, 63);
        calc2V.setBounds(360, 410, 70, 63);
        calc2V.setVisible(true);
        painel2.setVisible(true);
       
    }
    if(e.getSource()==calc2V){//=============Esconde o painel cientifico================

        delBotao.setBounds(51, 410,68, 63);
        delBotao.setFont(fonte2);
        limpBotao.setBounds(129, 410,67, 63);
        limpBotao.setFont(fonte2);
        negBotao.setBounds(205, 410, 68, 63);
        negBotao.setFont(fonte2);
        calc2.setBounds(281, 410, 68, 63);
        tela.setSize(420, 550);
        texto.setBounds(50, 25, 300, 50);
        calc2V.setVisible(false);
        painel2.setVisible(false);

    }

    if(e.getSource()==decBotao){//=============Esse loop é para colocar o . nos numeros============

        texto.setText(texto.getText().concat("."));// Se apertarado o . na calculadora ele concatena na string

    }
    if(e.getSource()==adicBotao){//============ Soma ================================

       num1= Double.parseDouble(texto.getText());
       operador='+';
       texto.setText("");

    }
    if(e.getSource()==subBotao){//============== Subtração =======================

        num1= Double.parseDouble(texto.getText());
        operador='-';
        texto.setText("");
 
    }
    if(e.getSource()==multBotao){//=============== Multiplicação ======================

        num1= Double.parseDouble(texto.getText());
        operador='*';
        texto.setText("");
 
     }
     if(e.getSource()==divBotao){//================ Divisão ===================

        num1= Double.parseDouble(texto.getText());
        operador='/';
        texto.setText("");
 
     }
     if(e.getSource()==porcentagem){//================ Porcentagem ===================
        num1= Double.parseDouble(texto.getText());
        operador='%';
        texto.setText("");

     }
     if(e.getSource()==fatorial){//================ Fatorial ===================

        resultado=1;
        num1=Double.parseDouble(texto.getText());
        for(int i =1 ; i<= num1 ; i++){
            
            resultado= resultado*i;
        }
        texto.setText(String.valueOf(resultado));
     }

     if(e.getSource()==iguBotao){//=================Botão de igual====================
        
        num2= Double.parseDouble(texto.getText());
        
        switch(operador){

            case'+':
                resultado=num1+num2;
                break;
            case'-':
                resultado=num1-num2;
                break;
            case'*':
                resultado=num1*num2;
                break;
            case'/':
                resultado=num1/num2;
                break;
            case'%':
                resultado=(num1/100)*num2;
                break;       
        }

        texto.setText(String.valueOf(resultado));// Texto recebe o valor da variavel e converte em string
        num1=resultado; // Para caso queira utilizar o mesmo resultado calculado

     }

     if(e.getSource()==limpBotao){//================ Botão Clear ==============

        texto.setText("");
 
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
