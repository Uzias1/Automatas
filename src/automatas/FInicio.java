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
/**
 *
 * @author Uzías
 */
public class FInicio extends JFrame{
    public FInicio(){
        proceso();
        //Agregando el JPanel
        PInicio obj = new PInicio();
        this.add(obj, BorderLayout.CENTER);
        
        ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/logo.png"));
        this.setIconImage(icono.getImage());
        //Para forzar el tamaño de ventana
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    public void proceso(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Validacion de cadenas con automatas");
        pack();
    }
}
