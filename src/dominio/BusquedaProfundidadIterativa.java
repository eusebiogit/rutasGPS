/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Vector;

/**
 *
 * @author ordenador
 */
public class BusquedaProfundidadIterativa extends BusquedaProfundidadAcotada{

    protected int iteracion;
    protected double tiempoInicioTotal;
    
    protected double complejidadTemporalTotal;
    protected int complejidadEspacialFinal;
    
    protected Vector<Double> complejidadTemporalIteraciones;
    protected Vector<Integer> complejidadEspacialIteraciones;
    
    
    protected BusquedaProfundidadIterativa(Problema problema, 
                    int profundidadMaxima, int iteracion,boolean poda) {
        super(problema, profundidadMaxima,poda);
        this.iteracion=iteracion;
        complejidadTemporalIteraciones=new Vector();
        complejidadEspacialIteraciones=new Vector();
    }

    
    public Vector<Estado> buscar(){
        Vector<Estado> res;
        double tiempoInicioTotal=System.currentTimeMillis();
            do{
                res=super.buscar();
                if(res==null){
                    profundidadMaxima+=iteracion;
                }
                complejidadTemporalIteraciones.add(super.getComplejidadTemporal());
                complejidadEspacialIteraciones.add(super.getComplejidadEspacial());
            }while(res==null);
            complejidadTemporalTotal=(System.currentTimeMillis()-tiempoInicioTotal)/1000;
            complejidadEspacialFinal=super.getComplejidadEspacial();
            return res;
    }
    
    
    
    @Override
    public double getComplejidadTemporal() {
        return complejidadTemporalTotal;
    }

    @Override
    public int getComplejidadEspacial() {
        return complejidadEspacialFinal;
    }

    public Vector<Double> getComplejidadTemporalIteraciones() {
        return complejidadTemporalIteraciones;
    }

    public Vector<Integer> getComplejidadEspacialIteraciones() {
        return complejidadEspacialIteraciones;
    }
    
    @Override
    public String toString(){
        String r="Complejidad espacial en cada iteracion:\n";
        for(int i=0;i<complejidadEspacialIteraciones.size();i++){
            r+="\tIteracion "+i+": "+complejidadEspacialIteraciones.get(i)+"\n";
        }
        r+="Complejidad temporal en cada iteracion:\n";
        for(int i=0;i<complejidadTemporalIteraciones.size();i++){
            r+="\tIteracion "+i+": "+complejidadTemporalIteraciones.get(i)+"\n";
        }
        r+="Profundidad Maxima final: "+profundidadMaxima;
        r+="\nComplejidad Total:\n";
        r+=super.toString();
        return r;
    }
    
    
    
}
