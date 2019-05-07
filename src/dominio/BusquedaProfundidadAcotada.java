/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author ordenador
 */
public class BusquedaProfundidadAcotada extends BusquedaProfundidadSimple{

    protected int profundidadMaxima;
    
    
    protected BusquedaProfundidadAcotada(Problema problema,int profundidadMaxima,boolean poda) {
        super(problema,poda);
        this.profundidadMaxima=profundidadMaxima;
    }
    
    @Override
    public Vector<Estado> buscar(){  
        estados=new Hashtable();

        Vector<NodoBusqueda> LS=null;
        
        Vector<Estado> solucion=null;
        
        NodoBusqueda actual=null;

        NodoBusqueda inicial=new NodoBusqueda(
                    null,problema.getInicial(),0,0,0);
        frontera.insertar(inicial);
        double tiempoInicio=System.currentTimeMillis();
        boolean resuelto=false;
        do{
            actual=frontera.elimina();
                if(problema.testObjetivo(actual.getActual())){ 
                    resuelto=true;
                }
                else{
                    LS=problema.sucesores(actual);
                    
                    if(LS.get(0).getProfundidad()<=profundidadMaxima){ //busqueda profundidad acotada

                        creaListaNodosArbol(LS,actual);
                        complejidadEspacial+=LS.size();
                    }
                }
            
        }while(!resuelto&&!frontera.esVacia());
        if(resuelto){
            solucion=creaSolucion(actual);
        }else{
            //No hay solucion => solucion = null
        }
        complejidadTemporal=(System.currentTimeMillis()-tiempoInicio)/1000;
        return solucion;
    }
    
    
}
