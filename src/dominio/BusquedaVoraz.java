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
public class BusquedaVoraz extends Busqueda{

    public BusquedaVoraz(Problema problema,boolean poda) {
        super(problema,poda);
    }
    
    @Override
   protected void creaListaNodosArbol(Vector<NodoBusqueda> LS, 
                                        NodoBusqueda actual) {
        for(NodoBusqueda n:LS){
            if(!poda(n))
                frontera.insertarOrdenado(n);
        }
    }
}
