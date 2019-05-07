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
public class ProblemaA extends ProblemaVoraz{

    
    public ProblemaA(EspacioDeEstados espacio, long inicial, Vector<Long> destinos) {
        super(espacio, inicial, destinos);
    }
    

    
    public Vector<NodoBusqueda> sucesores(NodoBusqueda e){
        Vector<Estado> estadosSucesores=espacioEstados.sucesores(e.getActual());
        Vector<NodoBusqueda> r=new Vector();
        double costoaux=0;
        double h;
        for(Estado aux:estadosSucesores){
            h=heuristica(aux);
            costoaux=espacioEstados.peso(e.getActual(), aux);
                r.add(new NodoBusqueda(e,aux,e.getProfundidad()+1,
                   costoaux+e.getCosto(),e.getCosto()+h+costoaux));
        }
        return r;
    }
   

    
    
}
