package presentacion;


import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ordenador
 */
public class Leer {
    
    
    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */



// nextDouble() nextLine() leer.next();

private static final Scanner teclado=new Scanner(System.in);

public static String cadena(){
      return teclado.next();
  }
  public static char caracter(){
      return caracter("");
  }
  public static char caracter(String s){
      System.out.println(s);
      return teclado.next().charAt(0);
  }



  public static int entero() throws Exception{
    return entero("");
  }
  public static int entero(String s) throws Exception{
    int res=0;
    boolean vale=true;
    do{
      vale=true;
      System.out.println(s);
      try{
        res=teclado.nextInt();
      }
      catch (Exception e) {
        vale=false;
        teclado.next();
      }
    }while(!vale);
    teclado.nextLine();
    return res;
  }
  public static int entero(String s, int min, int max)throws Exception{
    int res=min-1;
    while(res<min || res>max)
    res=entero(s+"["+min+","+max+"]");
    return res;
  }
public static double continuo() throws Exception{
    return continuo("");
  }
  public static double continuo(String s) throws Exception{
    double res=0;
    boolean vale=true;
    do{
      vale=true;
      System.out.println(s);
      try{
        res=teclado.nextDouble();
      }
      catch (Exception e) {
        vale=false;
        teclado.next();
      }
    }while(!vale);
    teclado.nextLine();
    return res;
  }
   public static long grande() throws Exception{
    return grande("");
  }
  public static long grande(String s) throws Exception{
    long res=0;
    boolean vale=true;
    do{
      vale=true;
      System.out.println(s);
      try{
        res=teclado.nextLong();
      }
      catch (Exception e) {
        vale=false;
        teclado.next();
      }
    }while(!vale);
    teclado.nextLine();
    return res;
  }



    
    
}