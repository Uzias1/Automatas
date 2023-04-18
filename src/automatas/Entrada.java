/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Uzías
 */
public class Entrada extends JFrame{
    public Entrada(){
        this.setSize(640, 640);
        this.setUndecorated(true);
        //Para centrar
        this.setLocationRelativeTo(null);
        ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/logo.png"));
        this.setIconImage(icono.getImage());
        EntradaImg obj = new EntradaImg();
        this.add(obj);
    }
}
