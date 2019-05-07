/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author ordenador
 */
public class BusquedaCostoUniforme extends BusquedaVoraz{

    protected BusquedaCostoUniforme(Problema problema,boolean poda) {
        super(problema,poda);
    }
}
