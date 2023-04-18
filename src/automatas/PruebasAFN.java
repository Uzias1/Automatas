package automatas;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PruebasAFN{
    private String[][] afn;
    private int col;
    private int fil;
    
    //Para la sobrecarga de métodos
    public PruebasAFN(){
        
    }
  public String[][] PruebasAFN(String[][] afn, int col, int fil) {
    this.setFil(fil);
    this.setCol(col);
    int l = fil;
    int c = col;
    
    String automataAFNtoAFD [][] = convierteAFNtoAFD(afn,l,c);

    int columnas = automataAFNtoAFD[0].length;
    int filas = automataAFNtoAFD.length;

    for(int i=0;i<filas;i++){
      for(int j=0;j<columnas;j++){
        System.out.printf(automataAFNtoAFD[i][j]+"\t\t");
      }
      System.out.printf("\n");
    }
    return automataAFNtoAFD;
  }

//deberia devolver una estructura bidimensional en el formato de afd
public  String[][] convierteAFNtoAFD(String automataAFN[][], int filas, int columnas){
  String [][] error =
  {
    {"A","u","t","o","m","a","t","a"},
    {"null","null","null","null","null","null","null","null" }, //no te preocupes, yo no muevo tus nulos xdd
    {"I","n","v","a","l","i","d","o"}  
  };

  if(!validaAFN(automataAFN,filas,columnas)){
    return error;
  }

  List <List<String>> afdTemp = new ArrayList<List<String>>();//esta como columnas luego filas
  //o sea AFDtemp.get(0).get(1) = AFDtemp en la columna 0 fila 1
  List <String> arreglotemp2 = new ArrayList<String>();
  List <String> arreglotemp3 = new ArrayList<String>();

  int posicion = 0;
  int numerodefilashechas = 0;//me quede sin creatividad
  int centinela = 1;

  for(int i=0; i<columnas-1; i++){
    afdTemp.add(new ArrayList<String>());
  }
  
  afdTemp.get(0).add("Estado");
  afdTemp.get(1).add("Tipo");
  for(int i=3;i<columnas;i++){ //esto llena la primera fila excluye el epsilon
  	afdTemp.get(i-1).add(automataAFN[0][i]);
  }

  for(int i=0;i<columnas-1;i++){ //llena tu fila de nulos -1 porque quita la columandel epsilon
  	afdTemp.get(i).add("null");
  }

  for(int i=2;i<filas;i++){
    if(automataAFN[i][1].equals("Inicial") || automataAFN[i][1].equals("Inicial / Aceptacion")){ //busca el estado inicila
      posicion=i;
      break;
    }
  }

  afdTemp.get(0).add(automataAFN[posicion][2]); //le pone el epsilon de inicla 
  afdTemp.get(1).add(automataAFN[posicion][1]);

  do{
  	String [] arreglotemp1 = afdTemp.get(0).get(numerodefilashechas+2).split(",");//+2 por fila de estados y nulos
  	for(int i=3;i<columnas;i++){
  	  for(int j=0;j<arreglotemp1.length;j++){
  		arreglotemp2.add(automataAFN[buscaEstado(automataAFN,arreglotemp1[j])][i]);
      }
  	  arreglotemp2=limpiaLista(arreglotemp2);

  	  for(int k=0;k<arreglotemp2.size();k++){
  		arreglotemp3.add(automataAFN[buscaEstado(automataAFN,arreglotemp2.get(k))][2]);
  	  }
  	  arreglotemp3=limpiaLista(arreglotemp3);

      String resultado = String.join(",", arreglotemp3);
      String [] arreglotemp4 = resultado.split(",");

  	  afdTemp.get(i-1).add(resultado);

  	  boolean found=false;
      for(int l=2;l<afdTemp.get(0).size();l++){
        if( (afdTemp.get(0).get(l).equals(resultado)) ){
          found = true;
      	  break;
      	}
      }
      if (found == false){
      	afdTemp.get(0).add(resultado);
      	centinela++;
        boolean tipoAfound = false;
      	for(int m=0;m<arreglotemp4.length;m++){
          if( automataAFN[buscaEstado(automataAFN,arreglotemp4[m])][1].equals("Aceptacion") ){
          	tipoAfound = true;
            break;
          }
        }

        if(tipoAfound == true ){
          afdTemp.get(1).add("Aceptacion");
        }else{
          afdTemp.get(1).add(" - ");
        }
      }

      arreglotemp2.clear();
      arreglotemp3.clear();
  	}
  	numerodefilashechas++;

  	centinela--;

  }while(centinela > 0);

  String [][] automataAFNtoAFD = new String [afdTemp.get(0).size()][columnas-1];//declara el automata que devuelve 

  for(int gg=0;gg<afdTemp.get(0).size();gg++){ //llena el automata final como arreglo bidimensional
      for(int gg2=0;gg2<columnas-1;gg2++){
        automataAFNtoAFD[gg][gg2]=afdTemp.get(gg2).get(gg);
      }
  }

  return automataAFNtoAFD;
}

//ordena la lista y quita los repetidos
//recibe la lista mal devuelve lista bien xd
public static List<String> limpiaLista(List <String> lista){

  List <String> listaElemSep = new ArrayList<String>();
  List <Integer> listaVacios = new ArrayList<Integer>();
  boolean vacio=true;

  for(int i=0;i<lista.size();i++){
  	String [] arreglotemp1 = lista.get(i).split(",");
    vacio=true;
  	for(int j=0; j<arreglotemp1.length;j++){
  	  if(arreglotemp1[j].equals("_") ){
  	  	listaElemSep.add(arreglotemp1[j]);
        listaVacios.add(j);
  	  }else{
        listaElemSep.add(arreglotemp1[j]);
        vacio=false;
  	  }
  	}
  	if(vacio==false){
  	  for(int k=0;k<listaVacios.size();k++){
  	  	listaElemSep.remove((int)listaVacios.get(k));
  	  }
  	}
  }

  listaElemSep.sort(null);

  Set<String> set = new LinkedHashSet<>(listaElemSep); 

  List<String> listaLista = new ArrayList<>(set);

  return listaLista;
}

//Busca el numero de linea de un estado mediante el nombre
//devuelve el número de linea donde esta el estado en el arreglo bidimensional
//si no encuentra el estado regresa -1
public  int buscaEstado(String automata[][],String estado){

  int l = 7;

  for(int i=2;i<l;i++){
    if(automata[i][0].equals(estado) ){
      return i;
    }
  }
  System.out.println("Estado no encontrado");
  return -1;
}

//valida todo el afn (falta el valida estados) ((ya no falta perra)) 
public  boolean validaAFN(String automata[][], int filas, int columnas){

  if(!validaTipoAFN(automata,filas)){
  	System.out.println("Tipos de Estado invalidos (más de un inicial o sin aceptacion)");
    return false;
  }
  else if(!validaLenguajeAFN(automata,columnas)){
  	System.out.println("Lenguaje invalido");
  	return false;
  }
  else if(!validaTransicionAFN(automata,filas, columnas)){
  	System.out.println("Estado aislado (transicion invalida)");
    return false;
  }
  else if(!validaEstadosAFN(automata,filas,columnas)){
  	System.out.println("Estado no correspondiente al AFN (Estados invalidos)");
  	return false;
  }

  return true;
}
//Valida que hay al menos una forma de llegar a cada estado excepto el inicial
//valida que el estado vacio solo pueda ir al estado vacio
//regresa true si esta todo bien
public  boolean validaTransicionAFN(String automata[][], int filas, int columnas){

  int l = filas;
  int c = columnas;
  boolean found;

  for (int i=2;i<l;i++){
    found = false;
    if(!(automata[i][1].equals("Inicial") || automata[i][1].equals("Inicial / Aceptacion") )){
      for (int j=2;j<l;j++){
        for(int k=2;k<c;k++){
          String [] estadotemp = automata[j][k].split(",");
          for(int m=0;m<estadotemp.length;m++){
            if (j!=i && automata[i][0].equals(estadotemp[m])) {
              found = true;
              break;
            }
          }
        }        
      }
    }else found = true;
    if(!found) {
        return false;
    }//esta parte es para que el estado vacio solo pueda ser tipo transitorio e ir a si mismo
    if( automata[i][0].equals("_") ){
      if( automata[i][1].equals(" - ") ){
        for(int n=2;n<columnas;n++){
          if(!(automata[i][n].equals("_")) ){
            return false;
          }
        }
      }else{
        return false;
      }
    }
  }
  return true;
}

//Ahora tambien valida que haya alfabeto xd
//Regresa true si está todo bien
public  boolean validaLenguajeAFN(String automata[][], int columnas){
  int c = columnas;

  if(c<4){
  	return false;
  }
  
  /*
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
  */

  for (int i=2;i<c;i++) {
    if(automata[0][i].length()!=1){
      return false;
    }
    for(int j=i+1;j<c;j++){ //era j=i+1
      if(automata[0][i].equals(automata[0][j])){
        return false;
      }
    }
  }
  return true;
}
//valida que un estado solo pueda ir a algun estado ya definido
//ademas valida que no haya estados repetidos o vacios ( "" )
//devuelve true si está todo bien
public  boolean validaEstadosAFN(String automata[][], int filas, int columnas){

  int l = filas;
  int c = columnas;
  int i,j;
  boolean found = false;

  //hace primer comentario
  for (i=2;i<l;i++){
    found = false;
      for (j=2;j<c;j++){
        for(int k=2;k<l;k++){
          String []estadotemp = automata[i][j].split(",");
          for(int m=0;m<estadotemp.length;m++){
            if (estadotemp[m].equals(automata[k][0])) {
              found = true;
              break;
            }
          }
        }        
      }
    }
    if (!found) {
        return false;
    }
    //segundo comentario
    /*for (i=2;i<l-1;i++){
      for(j=i+1;j<l;j++){                                   
        if(automata[0][i].equals(automata[0][j])){
          return false;
        }
      }
    }*/

    return true;
}
//Valida que haya un unico estado inicial
//y al menos un estado de aceptacion
//true cuando todo esta bien
public  boolean validaTipoAFN(String automata[][], int filas){

  int l = filas;
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
    return true;
  }
  return false;
}

    public String[][] getAfn() {
        return afn;
    }

    public void setAfn(String[][] afn) {
        this.afn = afn;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getFil() {
        return fil;
    }

    public void setFil(int fil) {
        this.fil = fil;
    }
}