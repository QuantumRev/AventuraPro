/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Clases.Arma;
import Clases.Casilla;
import Clases.Enemigo;
import Clases.EscenarioTemp;
import Clases.Goblin;
import Clases.Objeto;
import Clases.Personaje;
import Clases.SoldadoSerpiente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JLabel;

/**
 *
 * @author 1DAM
 */
public class Escenario extends javax.swing.JFrame {
    Casilla[][] casillas = new Casilla[5][5];
    String guardCas="#";
    public Escenario() {
        creaEscenario();
        initComponents();
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }

    public String getGuardCas() {
        return guardCas;
    }

    public void setGuardCas(String guardCas) {
        this.guardCas = guardCas;
    }
    
    
    public void creaEscenario(){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                int[] posCas={i,j};
                this.casillas[i][j]=new Casilla(posCas,"#");
            }
        }
    }
    public void imprimeEscenario(){
        this.label00.setText("     "+this.casillas[0][0].getVisual());
        this.label01.setText("   "+this.casillas[0][1].getVisual());
        this.label02.setText("   "+this.casillas[0][2].getVisual());
        this.label03.setText("   "+this.casillas[0][3].getVisual());
        this.label04.setText("   "+this.casillas[0][4].getVisual());
        
        this.label10.setText("   "+this.casillas[1][0].getVisual());
        this.label11.setText("   "+this.casillas[1][1].getVisual());
        this.label12.setText("   "+this.casillas[1][2].getVisual());
        this.label13.setText("   "+this.casillas[1][3].getVisual());
        this.label14.setText("   "+this.casillas[1][4].getVisual());
        
        this.label20.setText("   "+this.casillas[2][0].getVisual());
        this.label21.setText("   "+this.casillas[2][1].getVisual());
        this.label22.setText("   "+this.casillas[2][2].getVisual());
        this.label23.setText("   "+this.casillas[2][3].getVisual());
        this.label24.setText("   "+this.casillas[2][4].getVisual());
        
        this.label30.setText("   "+this.casillas[3][0].getVisual());
        this.label31.setText("   "+this.casillas[3][1].getVisual());
        this.label32.setText("   "+this.casillas[3][2].getVisual());
        this.label33.setText("   "+this.casillas[3][3].getVisual());
        this.label34.setText("   "+this.casillas[3][4].getVisual());
        
        this.label40.setText("   "+this.casillas[4][0].getVisual());
        this.label41.setText("   "+this.casillas[4][1].getVisual());
        this.label42.setText("   "+this.casillas[4][2].getVisual());
        this.label43.setText("   "+this.casillas[4][3].getVisual());
        this.label44.setText("   "+this.casillas[4][4].getVisual());
         
    }
    public void hasPersonje(Personaje per){
        for(int i=0;i<this.casillas.length;i++){
            for(int j=0;j<casillas[i].length;j++){
                if((i==per.getPosPer()[0])&&(j==per.getPosPer()[1])){
                    casillas[i][j].setVisual("P");
                }
            }
        }   
    }
    public void setVisualCasilla(String visual,int pos1,int pos2){
        casillas[pos1][pos2].setVisual(visual);
    }
    public void compruebaCasilla(Personaje per){
        Casilla cas = casillas[per.getPosPer()[0]][per.getPosPer()[1]];
        switch(cas.getVisual()){
            case "G":
                Enemigo en=(Enemigo) cas.getEl();
                per.combate(en);
                this.guardCas="#";
                break;
            case "$":
                per.setOro(per.getOro()+10);  
                setCampoTexto("Has conseguido 10 de oro!");
                this.guardCas="#";
                break;
            case ")":
                Arma ar=(Arma) cas.getEl();
                per.cogerObjeto(new Arma(7,2,true,3,20,"Arco"));
                setCampoTexto("Has obtenido un Arco! ");
                this.guardCas="#";
                break;
            case "S":
                Enemigo en2=(Enemigo) cas.getEl();
                per.combate(en2);
                this.guardCas="#";
                break;
        }
    }
    
    public int[] moverPer(Personaje per, char mov){
        int[] posPer=per.getPosPer();
        switch(mov){
            case 'w':{
            casillas[posPer[0]][posPer[1]].setVisual(this.guardCas);
            posPer[0]-=1;
            if(posPer[0]==-1){
                posPer[0]=casillas.length-1;
            }
            this.guardCas=casillas[posPer[0]][posPer[1]].getVisual();
            compruebaCasilla(per);
            casillas[posPer[0]][posPer[1]].setVisual("P");
            break;
        }
        case 's':{
            casillas[posPer[0]][posPer[1]].setVisual(this.guardCas);
            posPer[0]+=1;
            if(posPer[0]==casillas.length){
                posPer[0]=0;
            }
            this.guardCas=casillas[posPer[0]][posPer[1]].getVisual();
            compruebaCasilla(per);
            casillas[posPer[0]][posPer[1]].setVisual("P");
            break;
        }
        case 'a':{
            casillas[posPer[0]][posPer[1]].setVisual(this.guardCas);
            posPer[1]-=1;
            if(posPer[1]==-1){
                posPer[1]=casillas.length-1;
            }
            this.guardCas=casillas[posPer[0]][posPer[1]].getVisual();
            compruebaCasilla(per);
            casillas[posPer[0]][posPer[1]].setVisual("P");
            break;
        }
        case 'd':{
            casillas[posPer[0]][posPer[1]].setVisual(this.guardCas);
            posPer[1]+=1;
            if(posPer[1]==casillas.length){
                posPer[1]=0;
            }
            this.guardCas=casillas[posPer[0]][posPer[1]].getVisual();
            compruebaCasilla(per);
            casillas[posPer[0]][posPer[1]].setVisual("P");
            break;
        }
        case 'e':{
            System.out.println(per.muestaPagPer()+"\n");
            break;
        }
        case 'i':{
            per.abreInventario();
            break;
        }
        }
        return posPer;
    }
    public void setCampoTexto(String texto){
        this.campoTexto.setText(this.campoTexto.getText()+"\n"+texto);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        campoTexto = new javax.swing.JTextArea();
        panel00 = new javax.swing.JPanel();
        label00 = new javax.swing.JLabel();
        panel02 = new javax.swing.JPanel();
        label02 = new javax.swing.JLabel();
        panel01 = new javax.swing.JPanel();
        label01 = new javax.swing.JLabel();
        panel04 = new javax.swing.JPanel();
        label04 = new javax.swing.JLabel();
        panel03 = new javax.swing.JPanel();
        label03 = new javax.swing.JLabel();
        panel12 = new javax.swing.JPanel();
        label12 = new javax.swing.JLabel();
        panel11 = new javax.swing.JPanel();
        label11 = new javax.swing.JLabel();
        panel14 = new javax.swing.JPanel();
        label14 = new javax.swing.JLabel();
        panel13 = new javax.swing.JPanel();
        label13 = new javax.swing.JLabel();
        panel10 = new javax.swing.JPanel();
        label10 = new javax.swing.JLabel();
        panel22 = new javax.swing.JPanel();
        label22 = new javax.swing.JLabel();
        panel21 = new javax.swing.JPanel();
        label21 = new javax.swing.JLabel();
        panel24 = new javax.swing.JPanel();
        label24 = new javax.swing.JLabel();
        panel23 = new javax.swing.JPanel();
        label23 = new javax.swing.JLabel();
        panel20 = new javax.swing.JPanel();
        label20 = new javax.swing.JLabel();
        panel32 = new javax.swing.JPanel();
        label32 = new javax.swing.JLabel();
        panel31 = new javax.swing.JPanel();
        label31 = new javax.swing.JLabel();
        panel34 = new javax.swing.JPanel();
        label34 = new javax.swing.JLabel();
        panel33 = new javax.swing.JPanel();
        label33 = new javax.swing.JLabel();
        panel30 = new javax.swing.JPanel();
        label30 = new javax.swing.JLabel();
        panel42 = new javax.swing.JPanel();
        label42 = new javax.swing.JLabel();
        panel41 = new javax.swing.JPanel();
        label41 = new javax.swing.JLabel();
        panel44 = new javax.swing.JPanel();
        label44 = new javax.swing.JLabel();
        panel43 = new javax.swing.JPanel();
        label43 = new javax.swing.JLabel();
        panel40 = new javax.swing.JPanel();
        label40 = new javax.swing.JLabel();
        botonAtacar = new javax.swing.JButton();
        botonHuir = new javax.swing.JButton();
        botonUsar = new javax.swing.JButton();
        botonInventario = new javax.swing.JButton();
        botonCoger = new javax.swing.JButton();
        flechaArriba = new javax.swing.JButton();
        flechaAbajo = new javax.swing.JButton();
        flechaDerecha = new javax.swing.JButton();
        flechaIzquierda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        campoTexto.setColumns(20);
        campoTexto.setRows(5);
        jScrollPane1.setViewportView(campoTexto);

        panel00.setBackground(new java.awt.Color(180, 180, 180));
        panel00.setPreferredSize(new java.awt.Dimension(50, 50));

        label00.setText("jLabel1");

        javax.swing.GroupLayout panel00Layout = new javax.swing.GroupLayout(panel00);
        panel00.setLayout(panel00Layout);
        panel00Layout.setHorizontalGroup(
            panel00Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel00Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label00, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel00Layout.setVerticalGroup(
            panel00Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel00Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label00, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel02.setBackground(new java.awt.Color(180, 180, 180));
        panel02.setPreferredSize(new java.awt.Dimension(50, 50));

        label02.setText("jLabel1");

        javax.swing.GroupLayout panel02Layout = new javax.swing.GroupLayout(panel02);
        panel02.setLayout(panel02Layout);
        panel02Layout.setHorizontalGroup(
            panel02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel02Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label02)
                .addContainerGap())
        );
        panel02Layout.setVerticalGroup(
            panel02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel02Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label02, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel01.setBackground(new java.awt.Color(180, 180, 180));
        panel01.setPreferredSize(new java.awt.Dimension(50, 50));

        label01.setText("jLabel1");

        javax.swing.GroupLayout panel01Layout = new javax.swing.GroupLayout(panel01);
        panel01.setLayout(panel01Layout);
        panel01Layout.setHorizontalGroup(
            panel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel01Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label01)
                .addContainerGap())
        );
        panel01Layout.setVerticalGroup(
            panel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel01Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label01, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel04.setBackground(new java.awt.Color(180, 180, 180));
        panel04.setPreferredSize(new java.awt.Dimension(50, 50));

        label04.setText("jLabel1");

        javax.swing.GroupLayout panel04Layout = new javax.swing.GroupLayout(panel04);
        panel04.setLayout(panel04Layout);
        panel04Layout.setHorizontalGroup(
            panel04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel04Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label04)
                .addContainerGap())
        );
        panel04Layout.setVerticalGroup(
            panel04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel04Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label04, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel03.setBackground(new java.awt.Color(180, 180, 180));
        panel03.setPreferredSize(new java.awt.Dimension(50, 50));

        label03.setText("jLabel1");

        javax.swing.GroupLayout panel03Layout = new javax.swing.GroupLayout(panel03);
        panel03.setLayout(panel03Layout);
        panel03Layout.setHorizontalGroup(
            panel03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel03Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label03)
                .addContainerGap())
        );
        panel03Layout.setVerticalGroup(
            panel03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel03Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label03, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel12.setBackground(new java.awt.Color(180, 180, 180));
        panel12.setPreferredSize(new java.awt.Dimension(50, 50));

        label12.setText("jLabel1");

        javax.swing.GroupLayout panel12Layout = new javax.swing.GroupLayout(panel12);
        panel12.setLayout(panel12Layout);
        panel12Layout.setHorizontalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label12)
                .addContainerGap())
        );
        panel12Layout.setVerticalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label12, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel11.setBackground(new java.awt.Color(180, 180, 180));
        panel11.setPreferredSize(new java.awt.Dimension(50, 50));

        label11.setText("jLabel1");

        javax.swing.GroupLayout panel11Layout = new javax.swing.GroupLayout(panel11);
        panel11.setLayout(panel11Layout);
        panel11Layout.setHorizontalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label11)
                .addContainerGap())
        );
        panel11Layout.setVerticalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label11, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel14.setBackground(new java.awt.Color(180, 180, 180));
        panel14.setPreferredSize(new java.awt.Dimension(50, 50));

        label14.setText("jLabel1");

        javax.swing.GroupLayout panel14Layout = new javax.swing.GroupLayout(panel14);
        panel14.setLayout(panel14Layout);
        panel14Layout.setHorizontalGroup(
            panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label14)
                .addContainerGap())
        );
        panel14Layout.setVerticalGroup(
            panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label14, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel13.setBackground(new java.awt.Color(180, 180, 180));
        panel13.setPreferredSize(new java.awt.Dimension(50, 50));

        label13.setText("jLabel1");

        javax.swing.GroupLayout panel13Layout = new javax.swing.GroupLayout(panel13);
        panel13.setLayout(panel13Layout);
        panel13Layout.setHorizontalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label13)
                .addContainerGap())
        );
        panel13Layout.setVerticalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label13, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel10.setBackground(new java.awt.Color(180, 180, 180));
        panel10.setPreferredSize(new java.awt.Dimension(50, 50));

        label10.setText("jLabel1");

        javax.swing.GroupLayout panel10Layout = new javax.swing.GroupLayout(panel10);
        panel10.setLayout(panel10Layout);
        panel10Layout.setHorizontalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label10)
                .addContainerGap())
        );
        panel10Layout.setVerticalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label10, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel22.setBackground(new java.awt.Color(180, 180, 180));
        panel22.setPreferredSize(new java.awt.Dimension(50, 50));

        label22.setText("jLabel1");

        javax.swing.GroupLayout panel22Layout = new javax.swing.GroupLayout(panel22);
        panel22.setLayout(panel22Layout);
        panel22Layout.setHorizontalGroup(
            panel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label22)
                .addContainerGap())
        );
        panel22Layout.setVerticalGroup(
            panel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label22, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel21.setBackground(new java.awt.Color(180, 180, 180));
        panel21.setPreferredSize(new java.awt.Dimension(50, 50));

        label21.setText("jLabel1");

        javax.swing.GroupLayout panel21Layout = new javax.swing.GroupLayout(panel21);
        panel21.setLayout(panel21Layout);
        panel21Layout.setHorizontalGroup(
            panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label21)
                .addContainerGap())
        );
        panel21Layout.setVerticalGroup(
            panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label21, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel24.setBackground(new java.awt.Color(180, 180, 180));
        panel24.setPreferredSize(new java.awt.Dimension(50, 50));

        label24.setText("jLabel1");

        javax.swing.GroupLayout panel24Layout = new javax.swing.GroupLayout(panel24);
        panel24.setLayout(panel24Layout);
        panel24Layout.setHorizontalGroup(
            panel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label24)
                .addContainerGap())
        );
        panel24Layout.setVerticalGroup(
            panel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label24, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel23.setBackground(new java.awt.Color(180, 180, 180));
        panel23.setPreferredSize(new java.awt.Dimension(50, 50));

        label23.setText("jLabel1");

        javax.swing.GroupLayout panel23Layout = new javax.swing.GroupLayout(panel23);
        panel23.setLayout(panel23Layout);
        panel23Layout.setHorizontalGroup(
            panel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label23)
                .addContainerGap())
        );
        panel23Layout.setVerticalGroup(
            panel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label23, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel20.setBackground(new java.awt.Color(180, 180, 180));
        panel20.setPreferredSize(new java.awt.Dimension(50, 50));

        label20.setText("jLabel1");

        javax.swing.GroupLayout panel20Layout = new javax.swing.GroupLayout(panel20);
        panel20.setLayout(panel20Layout);
        panel20Layout.setHorizontalGroup(
            panel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label20)
                .addContainerGap())
        );
        panel20Layout.setVerticalGroup(
            panel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label20, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel32.setBackground(new java.awt.Color(180, 180, 180));
        panel32.setPreferredSize(new java.awt.Dimension(50, 50));

        label32.setText("jLabel1");

        javax.swing.GroupLayout panel32Layout = new javax.swing.GroupLayout(panel32);
        panel32.setLayout(panel32Layout);
        panel32Layout.setHorizontalGroup(
            panel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel32Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label32)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panel32Layout.setVerticalGroup(
            panel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel32Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label32)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel31.setBackground(new java.awt.Color(180, 180, 180));
        panel31.setPreferredSize(new java.awt.Dimension(50, 50));

        label31.setText("jLabel1");

        javax.swing.GroupLayout panel31Layout = new javax.swing.GroupLayout(panel31);
        panel31.setLayout(panel31Layout);
        panel31Layout.setHorizontalGroup(
            panel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel31Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label31)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panel31Layout.setVerticalGroup(
            panel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel31Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label31)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel34.setBackground(new java.awt.Color(180, 180, 180));
        panel34.setPreferredSize(new java.awt.Dimension(50, 50));

        label34.setText("jLabel1");

        javax.swing.GroupLayout panel34Layout = new javax.swing.GroupLayout(panel34);
        panel34.setLayout(panel34Layout);
        panel34Layout.setHorizontalGroup(
            panel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel34Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label34)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panel34Layout.setVerticalGroup(
            panel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel34Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label34)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel33.setBackground(new java.awt.Color(180, 180, 180));
        panel33.setPreferredSize(new java.awt.Dimension(50, 50));

        label33.setText("jLabel1");

        javax.swing.GroupLayout panel33Layout = new javax.swing.GroupLayout(panel33);
        panel33.setLayout(panel33Layout);
        panel33Layout.setHorizontalGroup(
            panel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel33Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label33)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panel33Layout.setVerticalGroup(
            panel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel33Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label33)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel30.setBackground(new java.awt.Color(180, 180, 180));
        panel30.setPreferredSize(new java.awt.Dimension(50, 50));

        label30.setText("jLabel1");

        javax.swing.GroupLayout panel30Layout = new javax.swing.GroupLayout(panel30);
        panel30.setLayout(panel30Layout);
        panel30Layout.setHorizontalGroup(
            panel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel30Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label30)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panel30Layout.setVerticalGroup(
            panel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel30Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label30)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel42.setBackground(new java.awt.Color(180, 180, 180));
        panel42.setPreferredSize(new java.awt.Dimension(50, 50));

        label42.setText("jLabel1");

        javax.swing.GroupLayout panel42Layout = new javax.swing.GroupLayout(panel42);
        panel42.setLayout(panel42Layout);
        panel42Layout.setHorizontalGroup(
            panel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel42Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label42)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panel42Layout.setVerticalGroup(
            panel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel42Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label42)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel41.setBackground(new java.awt.Color(180, 180, 180));
        panel41.setPreferredSize(new java.awt.Dimension(50, 50));

        label41.setText("jLabel1");

        javax.swing.GroupLayout panel41Layout = new javax.swing.GroupLayout(panel41);
        panel41.setLayout(panel41Layout);
        panel41Layout.setHorizontalGroup(
            panel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel41Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label41)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panel41Layout.setVerticalGroup(
            panel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel41Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label41)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel44.setBackground(new java.awt.Color(180, 180, 180));
        panel44.setPreferredSize(new java.awt.Dimension(50, 50));

        label44.setText("jLabel1");

        javax.swing.GroupLayout panel44Layout = new javax.swing.GroupLayout(panel44);
        panel44.setLayout(panel44Layout);
        panel44Layout.setHorizontalGroup(
            panel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel44Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label44)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panel44Layout.setVerticalGroup(
            panel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel44Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label44)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel43.setBackground(new java.awt.Color(180, 180, 180));
        panel43.setPreferredSize(new java.awt.Dimension(50, 50));

        label43.setText("jLabel1");

        javax.swing.GroupLayout panel43Layout = new javax.swing.GroupLayout(panel43);
        panel43.setLayout(panel43Layout);
        panel43Layout.setHorizontalGroup(
            panel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel43Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label43)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panel43Layout.setVerticalGroup(
            panel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel43Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label43)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel40.setBackground(new java.awt.Color(180, 180, 180));
        panel40.setPreferredSize(new java.awt.Dimension(50, 50));

        label40.setText("jLabel1");

        javax.swing.GroupLayout panel40Layout = new javax.swing.GroupLayout(panel40);
        panel40.setLayout(panel40Layout);
        panel40Layout.setHorizontalGroup(
            panel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel40Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label40)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panel40Layout.setVerticalGroup(
            panel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel40Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label40)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        botonAtacar.setText("Atacar");

        botonHuir.setText("Huir");

        botonUsar.setText("Usar");

        botonInventario.setText("Inventario");
        botonInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInventarioActionPerformed(evt);
            }
        });

        botonCoger.setText("Coger");

        flechaArriba.setText("^");
        flechaArriba.setMinimumSize(new java.awt.Dimension(50, 45));
        flechaArriba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flechaArribaActionPerformed(evt);
            }
        });

        flechaAbajo.setText("v");
        flechaAbajo.setMinimumSize(new java.awt.Dimension(50, 45));
        flechaAbajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flechaAbajoActionPerformed(evt);
            }
        });

        flechaDerecha.setText(">");
        flechaDerecha.setMinimumSize(new java.awt.Dimension(50, 45));

        flechaIzquierda.setText("<");
        flechaIzquierda.setMinimumSize(new java.awt.Dimension(50, 45));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panel00, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonAtacar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonHuir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonUsar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonCoger, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(298, 298, 298)
                                .addComponent(flechaIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(flechaArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(flechaAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(panel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(panel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel03, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel04, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(flechaDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panel02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panel03, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panel00, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panel01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(panel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panel04, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(panel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(panel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(panel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonAtacar)
                            .addComponent(botonInventario))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(botonHuir)
                                    .addComponent(botonCoger))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(flechaIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(botonUsar)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(flechaArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(flechaAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(flechaDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void flechaAbajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flechaAbajoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_flechaAbajoActionPerformed

    private void flechaArribaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flechaArribaActionPerformed
        
    }//GEN-LAST:event_flechaArribaActionPerformed

    private void botonInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonInventarioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Escenario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Escenario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Escenario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Escenario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
        Escenario esc = new Escenario();
        
        Scanner sca = new Scanner(System.in);
        int[] posPer = {0,0};
        char mov;
        Arma espadaCorta = new Arma(4,2,false,2,10,"Espada corta");
        Personaje per = new Personaje("Pepe","Normal",3,3,3,15,15,1,0,10,espadaCorta,posPer);
        esc.hasPersonje(per);
        esc.setVisualCasilla("T", 4, 4);
        esc.setVisualCasilla("G", 0, 3);
        esc.getCasillas()[0][3].setEl(new Goblin(5,5,10,1,20,"Goblin",false));
        esc.setVisualCasilla("G", 1, 4);
        esc.getCasillas()[1][4].setEl(new Goblin(5,5,10,1,20,"Goblin",false));
        esc.setVisualCasilla(")", 0, 4);
        esc.getCasillas()[0][4].setEl(new Arma(7,2,true,3,20,"Arco"));
        esc.setVisualCasilla("S", 2, 4);
        esc.getCasillas()[2][4].setEl(new SoldadoSerpiente(10,10,20,5,50,"Soldado Serpiente",true));
        esc.setVisualCasilla("L", 3, 1);
        esc.setVisualCasilla("$", 2, 2);
        /*esc.setCampoTexto(esc.imprimeEscenario());*/
        esc.imprimeEscenario();
        esc.setVisible(true);
            /*esc.setCampoTexto("W: Arriba, S: Abajo, A: Izquierda, D: Derecha, E: Pagina de personaje, I: Inventario");
            mov=sca.nextLine().charAt(0);*/
            esc.flechaArriba.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   per.setPosPer(esc.moverPer(per,'w'));
                   int x=per.getPosPer()[0];
                   int y=per.getPosPer()[1];
                   esc.hasPersonje(per);
                   esc.imprimeEscenario();
                   esc.setVisible(true);
               }
            });
            esc.flechaAbajo.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   per.setPosPer(esc.moverPer(per,'s'));
                   int x=per.getPosPer()[0];
                   int y=per.getPosPer()[1];
                   esc.hasPersonje(per);
                   esc.imprimeEscenario();
                   esc.setVisible(true);
               }
            });
            esc.flechaIzquierda.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   per.setPosPer(esc.moverPer(per,'a'));
                   int x=per.getPosPer()[0];
                   int y=per.getPosPer()[1];
                   esc.hasPersonje(per);
                   esc.imprimeEscenario();
                   esc.setVisible(true);
               }
            });
            esc.flechaDerecha.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   per.setPosPer(esc.moverPer(per,'d'));
                   int x=per.getPosPer()[0];
                   int y=per.getPosPer()[1];
                   esc.hasPersonje(per);
                   esc.imprimeEscenario();
                   esc.setVisible(true);
               }
            });
            esc.botonInventario.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   per.abreInventario();
               }
            });
            
            if(per.getHpActual()<=0){
                esc.setCampoTexto(per.getNombre()+" ha muerto!");
            }
                
        
        
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAtacar;
    private javax.swing.JButton botonCoger;
    private javax.swing.JButton botonHuir;
    private javax.swing.JButton botonInventario;
    private javax.swing.JButton botonUsar;
    private javax.swing.JTextArea campoTexto;
    private javax.swing.JButton flechaAbajo;
    private javax.swing.JButton flechaArriba;
    private javax.swing.JButton flechaDerecha;
    private javax.swing.JButton flechaIzquierda;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label00;
    private javax.swing.JLabel label01;
    private javax.swing.JLabel label02;
    private javax.swing.JLabel label03;
    private javax.swing.JLabel label04;
    private javax.swing.JLabel label10;
    private javax.swing.JLabel label11;
    private javax.swing.JLabel label12;
    private javax.swing.JLabel label13;
    private javax.swing.JLabel label14;
    private javax.swing.JLabel label20;
    private javax.swing.JLabel label21;
    private javax.swing.JLabel label22;
    private javax.swing.JLabel label23;
    private javax.swing.JLabel label24;
    private javax.swing.JLabel label30;
    private javax.swing.JLabel label31;
    private javax.swing.JLabel label32;
    private javax.swing.JLabel label33;
    private javax.swing.JLabel label34;
    private javax.swing.JLabel label40;
    private javax.swing.JLabel label41;
    private javax.swing.JLabel label42;
    private javax.swing.JLabel label43;
    private javax.swing.JLabel label44;
    private javax.swing.JPanel panel00;
    private javax.swing.JPanel panel01;
    private javax.swing.JPanel panel02;
    private javax.swing.JPanel panel03;
    private javax.swing.JPanel panel04;
    private javax.swing.JPanel panel10;
    private javax.swing.JPanel panel11;
    private javax.swing.JPanel panel12;
    private javax.swing.JPanel panel13;
    private javax.swing.JPanel panel14;
    private javax.swing.JPanel panel20;
    private javax.swing.JPanel panel21;
    private javax.swing.JPanel panel22;
    private javax.swing.JPanel panel23;
    private javax.swing.JPanel panel24;
    private javax.swing.JPanel panel30;
    private javax.swing.JPanel panel31;
    private javax.swing.JPanel panel32;
    private javax.swing.JPanel panel33;
    private javax.swing.JPanel panel34;
    private javax.swing.JPanel panel40;
    private javax.swing.JPanel panel41;
    private javax.swing.JPanel panel42;
    private javax.swing.JPanel panel43;
    private javax.swing.JPanel panel44;
    // End of variables declaration//GEN-END:variables
}
