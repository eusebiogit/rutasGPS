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
public class ProblemaVoraz extends Problema{
    
      public ProblemaVoraz(EspacioDeEstados espacio, long inicial, Vector<Long> destinos) {
        super(espacio, inicial, destinos);
    }
    
 
    
      @Override
  public Vector<NodoBusqueda> sucesores(NodoBusqueda e){
        Vector<Estado> estadosSucesores=espacioEstados.sucesores(e.getActual());
        Vector<NodoBusqueda> r=new Vector();
        double costoaux=0;
        double h;
        for(Estado aux:estadosSucesores){
            h=heuristica(aux);
                costoaux=espacioEstados.peso(e.getActual(), aux)+e.getCosto();
                r.add(new NodoBusqueda(e,aux,e.getProfundidad()+1,
                   costoaux,h));
        }
        return r;
    }
  
  
    protected double heuristica(Estado e){  // máxima distancia(Loc,LocVi) para i= 1,m
        //Distancia en linea recta desde el estado e hasta el mas lejano de los destinos
        Vector<Nodo> destinos=(Vector<Nodo>)e.getDestinos().clone();
        Nodo actual=e.getNodoActual();
        double max=Double.MAX_VALUE;
        double distancia=0;
        for(Nodo n:destinos){
            distancia=espacioEstados.getDistancia(actual, n);
            max=distancia<max?distancia:max;
        }
        return max;
    }
     
    
    
    
}
