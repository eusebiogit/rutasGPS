package dominio;

import java.util.Vector;

/**
 * Se resuelve como en el caso general
 * @author ordenador
 */
public class ProblemaProfundidad extends Problema{

    /**
     * 
     * @param espacio
     * @param inicial
     * @param destinos 
     */
    public ProblemaProfundidad(EspacioDeEstados espacio, long inicial, Vector<Long> destinos) {
        super(espacio, inicial, destinos);
    }
    
}
