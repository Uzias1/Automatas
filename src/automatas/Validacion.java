package automatas;

import java.util.ArrayList;
import java.util.*;

public class Validacion{
  private String[][] automata;
  private String cadena;
  private int filas;
  private int columnas;  
  
  public Validacion(){
      
  }
  
  public boolean Validacion (String[][] automata, String cadena, int filas, int columnas) {
      
    
    setColumnas(columnas);
    setFilas(filas);
    boolean resuelveAFD = resuelveAFD(automata,cadena);//aqui pones tu cadena que quieras probar
      System.out.println("La cadena es: "+cadena);
      System.out.println("El automata es: ");
      for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(automata[i][j]+" ");
            }
            System.out.println("");
        }
    if(resuelveAFD==true){
      return true;
    }else{
      return false;
    }
  }
  
  
  
  //Recorre el AFD de acuerdo a la cadena
//devuelve true si la cadena llega a un estado de aceptacion 
public  boolean resuelveAFD(String automata[][],String cadena){
  if( !validaCadena(automata,cadena) ){
    System.out.println("Cadena");
    return false;
  }
  
    if (!validaAFD(automata)) {
        System.out.println("AFD invalido");
        return false;
    }
  /*File file = fc.getSelectedFile();
  int l = this.getFilas() this.getFilas() this.getFilas() obtenerLineas(file);
  int c = this.getColumnas() obtenerColumnas(file);*/
  int c = this.getColumnas();
  int l = this.getFilas();
  int posicion=0;
  boolean aceptacion=false;

  //encuentra la posicion del estado inicial
  for(int i=2;i<l;i++){
    if(automata[i][1].equals("Inicial") || automata[i][1].equals("Inicial / Aceptacion")){
      posicion=i;
      break;
    }
  }

  for(int i=0;i<cadena.length();i++){
    aceptacion = false;
    for(int j=2;j<c;j++){
      if(String.valueOf(cadena.charAt(i)).equals(automata[0][j])){//leer el caracter de la cadena

        String estadoactual=automata[posicion][j];//guardar el estado al que se llego
        posicion=buscaEstado(automata,estadoactual);//buscarlo para leer el siguiente caracter desde ahí

        if(automata[posicion][1].equals("Aceptacion") || automata[posicion][1].equals("Inicial / Aceptacion")){
          aceptacion=true;
        }
      }
    }
  }
  if(!aceptacion){
    return false;
  }
  return true;
}
//Busca el numero de linea de un estado mediante el nombre
//devuelve el número de linea donde esta el estado en el arreglo bidimensional
//si no encuentra el estado regresa -1
public  int buscaEstado(String automata[][],String estado){

  /*File file = fc.getSelectedFile();
  int l = this.getFilas() this.getFilas() this.getFilas() obtenerLineas(file);*/
  int l = this.getFilas();

  for(int i=2;i<l;i++){
    if(automata[i][0].equals(estado) ){
        return i;
    }
  }
  return -1;
}
//Valida todo el AFD (tambien valida un afn si se quita o modifica el validaEstados)
public  boolean validaAFD(String automata[][]){

  if( validaTipo(automata)&&validaLenguaje(automata)&&validaEstados(automata)&&validaTransicion(automata) ){
    return true;
  }

  return false;
}
//Valida que la cadena se encuentra en terminos del lenguaje
//todo chido con true
public  boolean validaCadena(String automata[][],String cadena){

  /*File file = fc.getSelectedFile();
  int c = this.getColumnas() obtenerColumnas(file);*/
  int c = this.getColumnas();
   // System.out.println("Columnas vale: "+c);

  for(int i=0;i<cadena.length();i++){
    //caracter[i] = cadena.charAt(i);
    //  System.out.println("La longitud de la cadena es "+cadena.length());
    boolean found = false;
    //System.out.println("Caracter "+String.valueOf(cadena.charAt(i)));
    
    for(int j=2;j<c;j++){
       // System.out.println("El automata trae "+automata[0][j]);
      if( String.valueOf(cadena.charAt(i)).equals(automata[0][j]) ){
        found=true;
          System.out.println("Caracter "+cadena.charAt(i)+" encontrado");
        break;
      }
    }
    if(!found){
        System.out.println("Caracter "+cadena.charAt(i)+" nooooo encontrado");
        return false;
    }
  }
  return true;
}
//Valida que hay al menos una forma de llegar a cada estado excepto el inicial
//regresa true si esta todo bien
public boolean validaTransicion(String automata[][]){

/*File file = fc.getSelectedFile();
  int l = this.getFilas() this.getFilas() this.getFilas() obtenerLineas(file);
  int c = this.getColumnas() obtenerColumnas(file);*/
  int l = this.getFilas();
  int c = this.getColumnas();
  boolean found;

  for (int i=2;i<l;i++) {
    found = false;
    if(!(automata[i][1].equals("Inicial") || automata[i][1].equals("Inicial / Aceptacion") || automata[i][1] == null)){
      for (int j=1;j<l;j++) {
        for(int k=2;k<c;k++){
          if (j!=i && automata[i][0].equals(automata[j][k])) {
            found = true;
            break;
          }
        }        
      }
    }else found = true;
    if(!found) {
        return false;
    }
  }
    return true;
}
//Valida que no haya caracteres repetidos en el alfabeto
//tambien valida que el alfabeto sea de solo caracteres
//Regresa true si está todo bien
public boolean validaLenguaje(String automata[][]){

/*File file = fc.getSelectedFile();
  int c = this.getColumnas() obtenerColumnas(file);*/
  int c = this.getColumnas();

  for (int i=2;i<c;i++) {
    if(automata[0][i].length()!=1){
        System.out.println("validaLenguaje va a ser falso");
      return false;
    }
    for(int j=i+1;j<c;j++){
      if(automata[0][i].equals(automata[0][j])){
          System.out.println("validaLenguaje va a ser falso");
        return false;
      }
    }
  }
  System.out.println("validaLenguaje va a ser verdadero");
    return true;
}
//valida que un estado solo pueda ir a algun estado ya definido
//ademas valida que no haya estados repetidos
//devuelve true si está todo bien
public boolean validaEstados(String automata[][]){

/*File file = fc.getSelectedFile();
  int l = this.getFilas() this.getFilas() this.getFilas() obtenerLineas(file);
  int c = this.getColumnas() obtenerColumnas(file);*/
  int l = this.getFilas();
  int c = this.getColumnas();
  int i,j;
  boolean found = false;

  //hace primer comentario
  for (i=2;i<l;i++){
    found = false;
      for (j=2;j<c;j++){
        for(int k=1;k<l;k++){
          if (automata[i][j].equals(automata[k][0])) {
            found = true;
            break;
          }
        }        
      }
    }
    if (!found) {
        System.out.println("validaEstados va a ser FALSOOOOO");
        return false;
    }

   //segundo comentario
    /*for (i=2;i<l;i++){
      for(j=i+1;j<l;j++){
        if(automata[0][i].equals(automata[0][j])){
            System.out.println("validaEstados va a ser FALSOOOOO2");
          return false;
        }
      }
    }*/
    System.out.println("validaEstados va a ser VERDADEROOOO");
    return true;
}
//Valida que haya un unico estado inicial
//y al menos un estado de aceptacion
//true cuando todo esta bien
public  boolean validaTipo(String automata[][]){

/*File file = fc.getSelectedFile();
  int l = this.getFilas() this.getFilas() this.getFilas() obtenerLineas(file);*/
  int l = this.getFilas();
  int ini=0,acept=0;

  for (int i=2;i<l;i++) {
    if(automata[i][1].equals("Inicial") ){
      ini++;
    }
    if(automata[i][1].equals("Inicial / Aceptacion") ){
      ini++;
      acept++;
    }
    if(automata[i][1].equals("Aceptacion") ){
      acept++;
    }
  }
    
  if((ini==1)&&(acept>0)){
      System.out.println("El valor a restornar en validaTipo es verdadero");
    return true;
  }
  System.out.println("El valor a restornar en validaTipo es falseo");
  return false;
}

    public String[][] getAutomata() {
        return automata;
    }

    public void setAutomata(String[][] automata) {
        this.automata = automata;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
  


}
