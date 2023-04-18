/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Uzias
 */
public class EntradaImg extends JPanel{
    @Override
    public void paintComponent(Graphics g){
        Dimension tamanio = getSize();
        //La ruta indica la imagen que saldrá en el inicio
        ImageIcon fondo = new ImageIcon(getClass().getResource("/imagenes/logo2.jpg"));
        g.drawImage(fondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
        setOpaque(false);
        super.paintComponent(g);
    }
}