package dominio;

import java.util.Vector;

/**
 * 
 * @author ordenador
 */
public class ProblemaCostoUniforme extends ProblemaAnchura{

    /**
     * 
     * @param espacio
     * @param inicial
     * @param destinos 
     */
    public ProblemaCostoUniforme(EspacioDeEstados espacio, long inicial, Vector<Long> destinos) {
        super(espacio, inicial, destinos);
    }
    
}
