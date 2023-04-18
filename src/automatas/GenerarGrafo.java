/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.geom.Path2D;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Uzías
 */
public class GenerarGrafo extends JPanel {
    //Atributos
    private String[][] tabla;
    //Variables globales
    Font txt = new Font("Impact", Font.PLAIN, 30);
    Font txt2 = new Font("Impact", Font.PLAIN, 20);
    //Colores en codigo rgb
    Color fondo1 = new Color(254,174,1);
    Color fondo2 = new Color(13,63,79);
    Color resalte1 = new Color(255,255,47);
    Color resalte2 = new Color(25,216,213);
    Color azulext = new Color(101,135,128);
    
    Color adelante = new Color(9,10,15);
    Color atras = new Color(54,27,19);
    
    Color aux1 = new Color(244,241,222); //Color similar al "crema", para escribir en los azules
    Color aux2 = new Color(51,54,77); //Color similar al "negro"
    
    public GenerarGrafo(){
        /*setTitle("AUTOMATA RESULTANTE");
        setSize(1200,700);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(new JScrollPane(), BorderLayout.CENTER); */
    }
    
    public GenerarGrafo(String[][] tabla){
        this.tabla = tabla;
        //repaint();
        //setTitle("AUTOMATA RESULTANTE");
        
        
        /*JScrollPane scrollPane = new JScrollPane(this); 
        scrollPane.setPreferredSize( new Dimension( 1200, 700 ) );
        add(scrollPane, BorderLayout.CENTER); */
      //  this.setLayout(null);
        
       // this.setVisible(true);
       // this.setResizable(false);
        //this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(1200, 700));
        this.setLayout(null);
    }
    
    @Override 
    public void paint(Graphics g){
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        
        Dimension tamanio = getSize();
        ImageIcon fondo = new ImageIcon(getClass().getResource("/imagenes/fond_auto.jpg"));
        g.drawImage(fondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
        
        ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/logo.png"));
       // this.setIconImage(icono.getImage());
        
        int filas = getFilas(this.getTabla());
        int columnas = getColumnas(this.getTabla());
        int x = 100;
        int y = 100;
        int aumento = 250;
        boolean bandera = false;
        
        //Arreglo con las coordenadas
        String [][] edoCord = new String[filas-2][3]; //-2 debido a los titulos que trae la tabla
        
        //imprimir los circulos
        for (int i = 2; i < filas; i++) { //eliminando los titulos
            g2d.setStroke(new BasicStroke(2)); //grosor de linea
            //casos => Inicial, Inicial Aceptacion, Aceptacion, Ninguno
            int size = this.getTabla()[i][0].length();
            System.out.println("El tamaño es: "+size);
           // System.out.println("El valor de multiplicacion quedaría "+(50*size));
            switch (this.getTabla()[i][1]){
                case "Inicial":
                    g2d.setColor(resalte1);
                    g2d.fillOval(100, 100, 85+(10*size), 100);//Estaticos debido a que es inicial
                    bandera = true;
                    break;
                case "Inicial / Aceptacion":
                    g2d.setColor(resalte2);
                    g2d.fillOval(100, 100, 85+(10*size), 100);//Estaticos debido a que es inicial    
                    g2d.setColor(aux2);
                    g2d.drawOval(100-10, (100-10), 100+(23*(size/2)), 120);
                    bandera = true;
                    break;
                case "Aceptacion":
                    //Aumentar las coordenadas en x
                    if(x == 100){
                        x += aumento*size;
                    }
                    
                    g2d.setColor(resalte2);
                    g2d.fillOval(x, y, 85+(10*size), 100);
                    g2d.setColor(aux2);
                    g2d.drawOval(x-(10), (y-10), 100+(23*(size/2)), 120);
                    break;
                default:
                    //Aumentar las coordenadas en x
                    if(x == 100){
                        x += aumento*size;
                    }
                    g2d.setColor(fondo1);
                    g2d.fillOval(x, y, 85+(10*size), 100);
            }
            
            //Imprimir el nombre del estado
            g2d.setColor(aux2);
            g2d.setFont(txt);
            g2d.drawString(this.getTabla()[i][0], x+35, y+60);
            
            //Almacenar las coordenadas necesarias con el estado
            edoCord[i-2][0] = this.getTabla()[i][0];    
            edoCord[i-2][1] = Integer.toString(x+30);
            edoCord[i-2][2] = Integer.toString((y+60));
            
            if(bandera){
                //Imprimir el nombre del estado
                g2d.setColor(aux2);
                g2d.setFont(txt);
                g2d.drawString(this.getTabla()[i][0], 100+35, 100+60);

                //Almacenar las coordenadas necesarias con el estado
                edoCord[i-2][0] = this.getTabla()[i][0];    
                edoCord[i-2][1] = Integer.toString(100+30);
                edoCord[i-2][2] = Integer.toString((100+60));
            } else {
                g2d.setColor(aux2);
                g2d.setFont(txt);
                g2d.drawString(this.getTabla()[i][0], x+35, y+60);

                //Almacenar las coordenadas necesarias con el estado
                edoCord[i-2][0] = this.getTabla()[i][0];    
                edoCord[i-2][1] = Integer.toString(x+30);
                edoCord[i-2][2] = Integer.toString((y+60));
            }
            //Aumentar las coordenadas en x
            x += aumento+(50*size);
            bandera = false;
        }
        //Imprimir las flechas
        g2d.setStroke(new BasicStroke(4)); //grosor de linea
        g2d.setColor(Color.BLACK); //Color de linea
        //System.out.println("***************************************************************************");
       // imprimeTabla(edoCord, filas-2,3);
        //System.out.println("***************************************************************************");
        
        int ymayor = y;
        for (int i = 2; i < filas; i++) {
            for (int j = 2; j < columnas; j++) {
                //Variables necesarias
                String valor = this.getTabla()[0][j];
                String con = this.getTabla()[i][j]; //Estado de conexion
                String busca = this.getTabla()[i][0]; //Nodo a buscar
               // System.out.println("Busca vale "+busca+" y con vale "+con);
                int xtemp = strToInt(edoCord[i-2][1]);
                int ytemp = strToInt(edoCord[i-2][2]);
                //Valores agregados para imprimir afn
                int size = this.getTabla()[i][0].length();
                //System.out.println("El tamaño essss: "+size);
                //Estado inicial
                if(this.getTabla()[i][1].equals("Inicial") || this.getTabla()[i][1].equals("Inicial / Aceptacion")){
                    //flecha de estado inicial
                    g2d.drawArc(xtemp-90, ytemp-80, 100, 100, 135, 150);
                    g2d.drawLine(xtemp-23, ytemp+18, (xtemp-23)-20, (ytemp+18)-20);
                    g2d.drawLine(xtemp-23, ytemp+18, (xtemp-23)-20, (ytemp+18)+20);
                } 
                //Demas estados
                int idCord = buscaEn(busca, edoCord);
                int idDest = buscaEn(con, edoCord);
                
                //Se encontró el estado
                if(idCord > -1 && idDest > -1){
                    int xprov = Integer.parseInt(edoCord[idCord][1]);
                    int yprov = Integer.parseInt(edoCord[idCord][2]);
                    
                    int xdest = Integer.parseInt(edoCord[idDest][1]);
                    int ydest = Integer.parseInt(edoCord[idDest][2]);
                    
                    //buscar el destino
                    boolean back = isBack(xprov, xdest);
                    boolean away = isMoreTwoAway(busca, con,edoCord);
                    boolean same = isSame(busca, con);
                   // System.out.println("El valor de away es "+away+" con el nodo "+busca+" y la conexion "+con);
                    //Diferencia entre los nodos
                    int diNodos = difNodos(busca,con,edoCord);
                    
                    if(away){
                        if(back){
                            g2d.setColor(atras); //Color de linea
                            //Curva hacia atras de mas de 1 nodo
                            //(30+yprov-(((xdest-xprov)/10)*diNodos)/2)+((((xdest-xprov)/10)*diNodos));
                            //
                            g2d.drawArc(xdest+30, 40+ydest-(((xprov-xdest)/10)*diNodos)/2, xprov-xdest, ((xprov-xdest)/10)*diNodos, 0, -180);
                          //  System.out.println("Curva atras +1");
                            
                            //Imprimir la flecha
                            g2d.drawLine(xdest+30, ydest+30, (xdest+30)-15, (ydest+30)+20);
                            g2d.drawLine(xdest+30, ydest+30, (xdest+30)+15, (ydest+30)+20);
                            
                            //Imprimir el valor, j es el iterador 
                            g2d.setFont(txt2);
                            int xtxt = (((xdest+50) + xprov)/2)+((j-2)*15);
                            int ytxt = (30+ydest-(((xprov-xdest)/10)*diNodos)/2)+(((xprov-xdest)/10)*diNodos);
                            if(ymayor < ytxt){
                                ymayor = ytxt;
                            }
                            g2d.drawString(valor+",", xtxt, ytxt);
                        } else {
                            g2d.setColor(adelante); //Color de linea
                            //Curva hacia adelante
                            g2d.drawArc(xprov+30, 40+yprov-(((xdest-xprov)/10)*diNodos)/2, xdest-xprov, ((xdest-xprov)/10)*diNodos, 0, -180);
                            System.out.println("La distancia es"+(xdest-xprov));
                        //    System.out.println("Curva adelante +1");
                            
                            //Imprimir la flecha
                            g2d.drawLine(xdest+30, ydest+30, (xdest+30)-15, (ydest+30)+20);
                            g2d.drawLine(xdest+30, ydest+30, (xdest+30)+15, (ydest+30)+20);
                            
                            //Imprimir el valor, j es el iterador 
                            g2d.setFont(txt2);
                            int xtxt = (((xprov+50) + xdest)/2)+((j-2)*15);
                            int ytxt = (30+yprov-(((xdest-xprov)/10)*diNodos)/2)+((((xdest-xprov)/10)*diNodos));
                            if(ymayor < ytxt){
                                ymayor = ytxt;
                            }
                            g2d.drawString(valor+",", xtxt, ytxt);
                        }
                    } else {
                        if(same){
                            g2d.setColor(Color.BLACK); //Color de linea
                            //Arco en sí mismo
                            g2d.drawArc(xprov, yprov-100, 40, 70, 0, 200);
                      //      System.out.println("Arco en si mismo");
                            //Imprimir la flecha
                            g2d.drawLine(xprov+40, yprov-60, (xprov+40)-15, (yprov-60)-20);
                            g2d.drawLine(xprov+40, yprov-60, (xprov+40)+15, (yprov-60)-20);
                            
                            //Imprimir el valor, j es el iterador 
                            g2d.setFont(txt2);
                            int xtxt = ((xprov+50)) + ((j-2)*15);
                            int ytxt = yprov-90;
                            if(ymayor < ytxt){
                                ymayor = ytxt;
                            }
                            g2d.drawString(valor+",", xtxt, ytxt);
                        } else{
                            if(back){
                                g2d.setColor(atras); //Color de linea
                                //curva atras de una posicion
                                g2d.drawArc(xdest+30, ydest-10, xprov-xdest, 100, 0, -180);
                            //    System.out.println("Curva atras 1");
                                //Imprimir la flecha
                                g2d.drawLine(xdest+30, ydest+30, (xdest+30)-15, (ydest+30)+20);
                                g2d.drawLine(xdest+30, ydest+30, (xdest+30)+15, (ydest+30)+20);
                                
                                //Imprimir el valor, j es el iterador 
                                g2d.setFont(txt2);
                                int xtxt = (((xdest+50) + xprov)/2)+((j-2)*15);
                                int ytxt = (ydest)+(65*diNodos);
                                if(ymayor < ytxt){
                                    ymayor = ytxt;
                                }
                                g2d.drawString(valor+",", xtxt, ytxt);
                            } else{
                                g2d.setColor(adelante); //Color de linea
                                //recta normal hacia adelante
                                g2d.drawLine(xprov+57+(size*10), yprov, xdest-30, ydest);
                              //  System.out.println("Recta");
                                //Imprimir la flecha
                                g2d.drawLine(xdest-30, ydest, (xdest-30)-20, (ydest)-20);
                                g2d.drawLine(xdest-30, ydest, (xdest-30)-20, (ydest)+20);
                                
                                //Imprimir el valor, j es el iterador 
                                g2d.setFont(txt2);
                                int xtxt = ((((xprov+67) + xdest-30)-20)/2) + ((j-2)*15);
                                int ytxt = ydest-15;
                                if(ymayor < ytxt){
                                    ymayor = ytxt;
                                }
                                g2d.drawString(valor+",", xtxt, ytxt);
                            }
                        }
                    }
                    
                    
                } else {
                    //En teoria esto no deberia pasar, ya está validado
                    System.out.println("Estado no encontrado");
                }
            }
        }
        this.setPreferredSize(new Dimension(x,ymayor));
        //componente(x,y);
    }
    
    public void componente(int x, int y){
        JLabel label = new JLabel("HOLAA");
        label.setBounds(x,y,100,100);
        add(label);
    }
    
    public int strToInt(String conv){
        int aux = 0;
        aux = Integer.parseInt(conv);
        return aux;
    }
    
    public boolean isBack(int inicio, int fin){
        //pasar los enteros eliminando la "q"
        if(inicio < fin){
            return false;
        }
        return true;
    }
    
    public int difNodos(String inicio, String fin, String[][] coordenadas){
        //Obtener los tamaños de los estados
        int si1 = inicio.length();
        int si2 = fin.length();
        //Obtener sus coordenadas
        int cor1 = strToInt(coordenadas[buscaEn(inicio, coordenadas)][1]);
        int cor2 = strToInt(coordenadas[buscaEn(fin, coordenadas)][1]);
        //Recordando que a cada estado se le asignan ovalos con tamaño de 50n
        //El aumento de coordenadas es de 250 + 50n
        //coordenadas finales
        int temp1 = cor1+(50*si1);
        int temp2 = cor2+(50*si2);
        //Comparar primero con el mas pequeño en coordenadas
        if(temp1 < temp2 ){
            int t = (cor2 - temp1)/250;
           // System.out.println("La diferencia de nodos es " + t+ " cord2 vale y temp1 vale "+cor2 + " "+temp1);
            return t;
        } else {
            int y = (int) Math.ceil((temp2 - cor1)/250);
           // System.out.println("La diferencia de nodos es, entrando en else " + y + " temp2 vale y cor1 vale "+temp2 + " "+cor1);
            return -y;
        }
    }
    
    public boolean isSame(String inicio, String fin){
        //Se pasan los valores de ambas cadenas
        if(sortString(inicio).equals(sortString(fin))){
            return true;
        }
        return false;
    }
    
    public static String sortString(String inputString) {
        // Converting input string to character array
        char tempArray[] = inputString.toCharArray();
 
        // Sorting temp array using
        Arrays.sort(tempArray);
 
        // Returning new sorted string
        return new String(tempArray);
    }
    
    public boolean isMoreTwoAway(String inicio, String fin, String[][] tabla){
        if (difNodos(inicio, fin, tabla) >= 2){
            return true;
        }
        return false;
    }
    
    public int buscaEn(String busca, String tabla[][]){
        int aux = -1;
       // System.out.println("Entró, el length es "+tabla[0].length+2);
       
        if (busca == null) {
            return aux;
        }
       
        for (int i = 0; i < tabla.length; i++) {
            try {
                String temp1=sortString(busca);
                String temp2=sortString(tabla[i][0]);
                if(temp1.equals(temp2)){
                    aux = i;
                    //System.out.println("Encontredo, el valor es "+aux);
                    return aux;
                }
            } catch (NullPointerException e){
                //System.out.println("Llego a la excepcion con el valor "+busca);
                
            }
            
            
        }
        return aux;
    }
    
    public void imprimeTabla(String[][] tabla, int filas, int columnas){
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(tabla[i][j] + " | ");
            }
            System.out.println("");
        }
    }
    
    public int getFilas(String[][] tabla){
        return tabla.length;
    }
    
    public int getColumnas(String[][] tabla){
        return tabla[0].length;
    }
    
    public String[][] getTabla() {
        return tabla;
    }

    public void setTabla(String[][] tabla) {
        this.tabla = tabla;
    }

    
}
