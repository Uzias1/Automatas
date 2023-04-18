/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Uzías
 */
public class PInicio extends JPanel{
    //Objetos globales 
    JFileChooser fc = new JFileChooser();
    JLabel name = new JLabel();
    JButton validar = new JButton("Validar");
    JTextField cad = new JTextField();
    JLabel finalisima = new JLabel("");
    //Fuentes
    Font tit = new Font("Impact", Font.PLAIN, 80);
    Font txt = new Font("Arial Narrow", Font.PLAIN, 30);
    Font txt2 = new Font("Arial Narrow", Font.PLAIN, 60);
    
    //Colores en codigo rgb
    Color fondo1 = new Color(254,174,1);
    Color fondo2 = new Color(13,63,79);
    Color resalte1 = new Color(255,255,47);
    Color resalte2 = new Color(25,216,213);
    Color azulext = new Color(101,135,128);
    
    Color aux1 = new Color(244,241,222); //Color similar al "crema", para escribir en los azules
    Color aux2 = new Color(51,54,77); //Color similar al "negro"
    
    public PInicio(){
        Componentes();
        //dimensiones de ventana
        this.setPreferredSize(new Dimension(1200, 700));
        this.setLayout(null);
    }
    
    @Override
    public void paintComponent(Graphics g){
        Dimension tamanio = getSize();
        ImageIcon fondo = new ImageIcon(getClass().getResource("/imagenes/fondo2.png"));
        g.drawImage(fondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
        setOpaque(false);
        super.paintComponent(g);
    }
    
    public void Componentes(){
        JLabel titulo = new JLabel("VALIDADOR DE CADENAS");
        titulo.setFont(tit);
        titulo.setForeground(azulext);
        titulo.setBounds(375,10, 750, 90);
        //titulo.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(titulo);
        
        JLabel menu = new JLabel("Menu");
        menu.setFont(tit);
        menu.setForeground(aux1);
        menu.setBounds(55,10,190,90);
        //menu.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(menu);
        
        JButton fil = new JButton("Archivo");
        fil.setFont(txt);
        fil.setForeground(aux2);
        fil.setBounds(0,110,300,50);
        fil.setBackground(resalte1);
        fil.addActionListener((ActionEvent e) -> {
            //Para el archivo a seleccionar
            
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos TXT", "txt");
            fc.setFileFilter(filter);
            int ret = fc.showOpenDialog(menu);
            if(ret == JFileChooser.APPROVE_OPTION){
                //Para obtener el nombre del archivo 
                //System.out.println("El archivo elegido es "+fc.getSelectedFile().getName());
                name.setText(fc.getSelectedFile().getName());
                validar.setEnabled(true);
            }
            
        });
        this.add(fil);
        
        //Componentes principales a partir de aqui
        JLabel archi = new JLabel("El archivo seleccionado es:");
        archi.setFont(txt);
        archi.setForeground(aux1);
        archi.setBounds(310,130,320,40);
        //archi.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(archi);
        
        
        name.setFont(txt);
        name.setBounds(640,130,550,40);
        name.setForeground(aux1);
        //name.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(name);
        
        JLabel cadena = new JLabel("Ingrese la cadena a evaluar:");
        cadena.setFont(txt);
        cadena.setForeground(aux1);
        cadena.setBounds(310,200,320,40);
        //cadena.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(cadena);
        
        
        cad.setFont(txt);
        cad.setBounds(640,200,550,40);
        this.add(cad);
        
        
        validar.setFont(txt);
        validar.setBackground(resalte2);
        validar.setBounds(637,270,225,50);
        validar.setEnabled(false);
        validar.addActionListener((ActionEvent e) -> {
            //Mandar a llamar a la funcion 
            
            if(titulo.getText().equals("VALIDADOR DE CADENAS")){
                File file = fc.getSelectedFile();
                crearTabla(obtenerLineas(file),obtenerColumnas(file));
            } else {
                File file = fc.getSelectedFile();
                //System.out.println("Las lineas son "+obtenerLineas(file) + " y las col "+obtenerColumnas(file));
                convierteYGenera(obtenerLineas(file),obtenerColumnas(file));
            }
            
           // System.out.println("********************************************************************");
        });
        this.add(validar);
        
        //Resultados ***************************************************************************
        JLabel finl = new JLabel("La cadena es:");
        finl.setFont(txt2);
        finl.setBounds(490,440,330,70);
        finl.setForeground(aux1);
        //finl.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(finl);
        
        
        finalisima.setFont(txt2);
        finalisima.setBounds(830,440,180,70);
        finalisima.setForeground(aux1);
        //finalisima.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(finalisima);
        //Resultados ***************************************************************************
        
        JLabel autor = new JLabel("by: Garcia Lucero Uzias");
        autor.setFont(txt);
        autor.setBounds(15,660,270,40);
        autor.setForeground(aux1);
        //autor.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(autor);
        
        JLabel autor2 = new JLabel("& Fuentes Jiménez Roberto");
        autor2.setFont(txt);
        autor2.setBounds(300,660,350,40);
        autor2.setForeground(aux2);
        autor2.setForeground(aux1);
        this.add(autor2);
        
        JLabel grupo = new JLabel("Grupo: 4CV2");
        grupo.setFont(txt);
        grupo.setBounds(1050,660,150,40);
        grupo.setForeground(aux1);
        //grupo.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(grupo);
        
        
        //Para seleccionar partes del menu
        JButton afn = new JButton("Conversion de un AFN");
        afn.setFont(txt);
        afn.setForeground(aux2);
        afn.setBounds(0,160,300,50);
        afn.setBackground(resalte1);
        afn.addActionListener((ActionEvent e) -> {
            finl.setText("");
            cad.setEnabled(false);
            titulo.setText("   CONVERTIR AFN A AFD");
            finalisima.setText("");
        });
        this.add(afn);
        
        JButton afd = new JButton("Validacion de AFD");
        afd.setFont(txt);
        afd.setForeground(aux2);
        afd.setBounds(0,210,300,50);
        afd.setBackground(resalte1);
        afd.addActionListener((ActionEvent e) -> {
            finl.setText("La cadena es:");
            cad.setEnabled(true);
            titulo.setText("VALIDADOR DE CADENAS");
        });
        this.add(afd);
        
        JButton info = new JButton("Acerca de");
        info.setFont(txt);
        info.setForeground(aux2);
        info.setBounds(0,260,300,50);
        info.setBackground(resalte1);
        info.addActionListener((ActionEvent e) -> {
            JLabel adv1 = new JLabel("<html>Este programa permite validar cadenas al cargar un automata desde un txt"
                    + "<br> La codificacion que debe tener el automata es la siguiente:"
                    + "<br> *****************************"
                    + "<br> Primera linea: El alfabeto"
                    + "<br> Segunda linea: Estados (verificar que los estados pertenezcan al alfabeto)"
                    + "<br> N lineas: Estados, con el caracter \"*\" se indica estado inicial y el \"$\" para estados de aceptacion"
                    + "<br> NOTA: El caracter \"*\" deberá indicarse antes del nombre del estado y \"$\" despues"
                    + "<br> *****************************"
                    + "<br> Un ejemplo sería el siguiente:"
                    + "<br>0 1"
                    + "<br>Estados 0 1"
                    + "<br>*q0 q0 q1"
                    + "<br>q1 q1 q2"
                    + "<br>q2$ q2 q2");
            JLabel adv2 = new JLabel("<html><h1>CODIFICACION PARA UN AFN VALIDO</h1>"
                    + " Primera linea: El alfabeto"
                    + "<br> Segunda linea: Estados (verificar que los estados pertenezcan al alfabeto)"
                    + "<br> N lineas: Estados, con el caracter \"*\" se indica estado inicial y el \"$\" para estados de aceptacion"
                    + "<br> NOTA: El caracter \"*\" deberá indicarse antes del nombre del estado y \"$\" despues"
                    + "<br> Los estados que contengan mas de una conexion se deberán separar con comas"
                    + "<br> Deberá añadirse una columna de estados para el epsilon, representado con el caracter \".\""
                    + "<br> Em caso de querer representar que no hay conexion con algun nodo debe usarse el caracter \"_\""
                    + "<br> En la última linea se deberán añadir caracteres \"-\" para representar el estado vacío"
                    + "<br> Un ejemplo sería el siguiente:"
                    + "<br><strong>. 0 1"
                    + "<br>Estados . 0 1"
                    + "<br>*q0 q0,q1,q3 q3 q2"
                    + "<br>q1 q1,q3 q0,q2 q2"
                    + "<br>q2$ q2 q2 _"
                    + "<br>q3 q3 q2 q2"
                    + "<br>- - - -</strong>");
            if(titulo.getText().equals("VALIDADOR DE CADENAS")){
                adv1.setFont(txt);
                JOptionPane.showMessageDialog(null, adv1,"Informacion",JOptionPane.INFORMATION_MESSAGE);
            } else {
                adv2.setFont(txt);
                JOptionPane.showMessageDialog(null, adv2,"Informacion",JOptionPane.INFORMATION_MESSAGE);
            }
            
        });
        this.add(info);
    }
    
    public void convierteYGenera(int estados, int valores){
        //Variables
        String table [][] = new String[estados][valores+1]; //+2 por la columna de estado y tipo, +1 por la fila de encabezados
        File file = fc.getSelectedFile();
        int l = obtenerLineas(file);
        String states="";
        JLabel temp = new JLabel("<html>");
        
        //llenar la tabla
        table[0][0] = "Estado";
        table[0][1] = "Tipo";
        
        //Guardar todas las lineas del documento
        try {
            List<String> allLines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            for (int i=0; i<l; i++) {
		//System.out.println(line);
                if (i == 0){
                    //Leer el lenguaje que a su vez son los estados
                    states = allLines.get(i); //Están separados por espacios
                } else if(i == 1){
                    //Leer los valores de los estados
                    String aux1 [] = allLines.get(i).split(" ");
                    for (int j = 1; j < valores; j++) { //En uno porque la primera palabra es "Estado"
                        table[0][j+1] = aux1[j];
                    }
                } else {
                    //Leer los estados
                    String t = temp.getText() + "<br>" + allLines.get(i);
                    temp.setText(t);
                    String aux [] = allLines.get(i).split(" "); //guarda en un arreglo los valores de las rows
                    for (int k=0; k<valores; k++) {
                        table[i][0] = aux[0];
                        table[i][1] = obtenerTipo(aux[0]); //obtener tipo
                        if (k > 0){
                            table[i][k+1] = aux[k];
                        }
                    }
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            menError(1);
        }
        
        
        
        //Borrar los caracteres extra de la tabla
        for (int i = 0; i < estados; i++) {
            //obtener el tipo
            try{
                //Necesario por la fila de null que agrega
                String tipo = table[i][1];
                switch (tipo){
                    case "Inicial / Aceptacion":
                        //borrar el primer y ultimo caracter
                        table[i][0] = table[i][0].substring(1, table[i][0].length()-1);
                        break;
                    case "Inicial":
                        //Borrar el primero
                        table[i][0] = table[i][0].substring(1, table[i][0].length());
                        break;
                    case "Aceptacion":
                        //Borrar el ultimo
                        table[i][0] = table[i][0].substring(0, table[i][0].length()-1);
                        break;
                }
            
            } catch (Exception e){
                //Nothing to do
            }
        }
        
        //ya quedo la tabla
        try {
            PruebasAFN obj = new PruebasAFN();
            String[][] convertido = obj.PruebasAFN(table,valores+1,estados);
            String [][] error =
            {
              {"A","u","t","o","m","a","t","a"},
              {"null","null","null","null","null","null","null","null" }, //no te preocupes, yo no muevo tus nulos xdd
              {"I","n","v","a","l","i","d","o"}  
            };
            if(convertido.equals(error)){
                menError(3);
            } else{
                GeneraGrafo grafo = new GeneraGrafo(convertido);
                grafo.setVisible(true);
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    public void crearTabla (int estados, int valores){//filas y columnas
        //Variables
        String table [][] = new String[estados][valores+1]; //+2 por la columna de estado y tipo, +1 por la fila de encabezados
        File file = fc.getSelectedFile();
        int l = obtenerLineas(file);
        String states="";
        JLabel temp = new JLabel("<html>");
        
        //llenar la tabla
        table[0][0] = "Estado";
        table[0][1] = "Tipo";
        
        //Guardar todas las lineas del documento
        try {
            List<String> allLines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            for (int i=0; i<l; i++) {
		//System.out.println(line);
                if (i == 0){
                    //Leer el lenguaje que a su vez son los estados
                    states = allLines.get(i); //Están separados por espacios
                } else if(i == 1){
                    //Leer los valores de los estados
                    String aux1 [] = allLines.get(i).split(" ");
                    for (int j = 1; j < valores; j++) { //En uno porque la primera palabra es "Estado"
                        table[0][j+1] = aux1[j];
                    }
                } else {
                    //Leer los estados
                    String t = temp.getText() + "<br>" + allLines.get(i);
                    temp.setText(t);
                    String aux [] = allLines.get(i).split(" "); //guarda en un arreglo los valores de las rows
                    for (int k=0; k<valores; k++) {
                        table[i][0] = aux[0];
                        table[i][1] = obtenerTipo(aux[0]); //obtener tipo
                        if (k > 0){
                            table[i][k+1] = aux[k];
                        }
                    }
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            menError(1);
        }
        
        
        
        //Borrar los caracteres extra de la tabla
        for (int i = 0; i < estados; i++) {
            //obtener el tipo
            try{
                //Necesario por la fila de null que agrega
                String tipo = table[i][1];
                switch (tipo){
                    case "Inicial / Aceptacion":
                        //borrar el primer y ultimo caracter
                        table[i][0] = table[i][0].substring(1, table[i][0].length()-1);
                        break;
                    case "Inicial":
                        //Borrar el primero
                        table[i][0] = table[i][0].substring(1, table[i][0].length());
                        break;
                    case "Aceptacion":
                        //Borrar el ultimo
                        table[i][0] = table[i][0].substring(0, table[i][0].length()-1);
                        break;
                }
            
            } catch (Exception e){
                //Nothing to do
            }
        }
        
       /* //Imprimir la tabla
        for (int i = 0; i < estados; i++) {
            for (int j = 0; j < valores+1; j++) { //+1 por la columna de Tipo
                System.out.print(table[i][j] + " ");
            }
            System.out.println("");
        }*/
        
        //ya quedo la tabla
        //Mandar a llamar metodo para imprimir el automata
        GeneraGrafo obj = new GeneraGrafo(table);
       
        //Mandar a llamar a la validacion
        String cadena = cad.getText();
        Validacion objeto = new Validacion();
        
        String tabla[][] = new String[estados][valores+1];
        
        
            for (int j = 0; j < valores+1; j++) {
                tabla[0][j] = table[0][j];
               // System.out.print(tabla[0][j]+" ");
            }
        
        for (int i = 1; i < estados-1; i++) {
            for (int j = 0; j < valores+1; j++) {
                try{
                    if( !(table[i][j] == null) || !table[i][j].isEmpty()){
                    tabla[i][j] = table[i][j];
                }
                } catch(Exception e){
                    
                }
                
            }
        }
        int colxd = valores+1;
        boolean var = objeto.Validacion(table, cadena, estados, colxd);
        if (var) {
            finalisima.setText("Valida");
        } else {
            finalisima.setText("Invalida");
        }
        if (!objeto.validaAFD(table)) {
            menError(2);
        } else {
            obj.setVisible(true);
        }
        // System.out.println("Las columnas son ");
        
        
       // JOptionPane.showMessageDialog(null, temp, "Tabla",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public int obtenerLineas(File f){
        int temp = 0;
        try {
            List<String> allLines = Files.readAllLines(Paths.get(f.getAbsolutePath()));
            for (String line : allLines) {
		temp++;
               // System.out.println("La linea es "+line);
            }
        } catch (Exception ex) {
            menError(1);
        }
       // System.out.println("Las lineas son: "+temp);
        return temp;
    }
    
    public int obtenerColumnas(File f){
        int t = 0;
        try {
            List<String> allLines = Files.readAllLines(Paths.get(f.getAbsolutePath()));
            for (String line : allLines) {
		String xd[] = line.split(" ");
                t = xd.length;
            }
        } catch (Exception ex) {
            menError(1);
        }
       // System.out.println("Las columnas aqui son "+t);
        return t;
    }
    
    public void menError(int op){
        switch(op){
            case 1:
                JLabel adv1 = new JLabel("<html>Error en la lectura del archivo <br>Compruebe su codificacion");
                adv1.setFont(txt);
                JOptionPane.showMessageDialog(null, adv1,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case 2:
                JLabel adv2 = new JLabel("<html>Error en la codificacion del automata <br>Compruebe su codificacion");
                adv2.setFont(txt);
                JOptionPane.showMessageDialog(null, adv2,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case 3:
                JLabel adv3 = new JLabel("<html>El automata es invalido <br>Compruebe su codificacion");
                adv3.setFont(txt);
                JOptionPane.showMessageDialog(null, adv3,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            default:
                System.out.println("No hay mensaje de error para esta opcion");
        }
    }
    
    public String obtenerTipo(String a){
        String au = "";
        char inicial = '*';
        char acept = '$';
        /*Hay tres casos, que en la cadena haya un 
        * al inicio que significa que es estado inicial
        $ al final que significa que es estado de aceptacion
        Ninguno de los anteriores, que significa que no tiene ningun estado.
        */
        if(a.contains(String.valueOf(inicial)) && a.contains(String.valueOf(acept))){
            au = "Inicial / Aceptacion";
            return au;
        }
        
        if(a.contains(String.valueOf(inicial))){
            au = "Inicial";
        } else if(a.contains(String.valueOf(acept))){
            au = "Aceptacion";
        } else {
            au = " - ";
        }
        
        return au;
    }
}
