/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author Uzías
 */
public class GeneraGrafo extends JFrame{
    public GeneraGrafo(){
        
    }
    
    public GeneraGrafo(String[][] tabla){
        proceso();
        //Agregando el JPanel
        GenerarGrafo obj = new GenerarGrafo(tabla);
        this.add(obj, BorderLayout.CENTER);
        
        JScrollPane s = new JScrollPane(obj);
        s.setViewportView(obj);
        s.getViewport().setView(obj);
        s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(s);
        
        ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/logo.png"));
        this.setIconImage(icono.getImage());
        //Para forzar el tamaño de ventana
        this.setSize(1200,700);
        this.setResizable(false);
        this.pack();
       // this.setLocationRelativeTo(null);
    }
    
    public void proceso(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AUTOMATA GENERADO");
        pack();
    }
}
