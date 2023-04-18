/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import javax.swing.JOptionPane;

/**
 *
 * @author Uzías
 */
public class Automatas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        
        try {
            Runnable ejecutable = new Runnable(){
                public void run (){
                    Entrada bienvenida = new Entrada();
                    bienvenida.setVisible(true);
                    try {
                        //Tiempo de duración del hilo
                        Thread.sleep(1 * 1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    bienvenida.dispose();
                    //se crea un objeto de la primer ventana y se muestra
                    FInicio obj = new FInicio();
                    obj.setVisible(true);
                }
            };
            Thread tarea = new Thread(ejecutable);
            tarea.start();
        }catch(Exception e){
            //Excepcion en caso de que falle la creacion del hilo
            JOptionPane.showMessageDialog(null, "Algo falló", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
}
