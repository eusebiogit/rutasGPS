package dominio;

import java.util.Vector;

/**
 *
 * @author ordenador
 */
public class BusquedaVoraz extends Busqueda {

    /**
     *
     * @param problema
     * @param poda
     */
    public BusquedaVoraz(Problema problema, boolean poda) {
        super(problema, poda);
    }

    /**
     * Para la búsqueda voraz los elementos entran en la frontera ordenados por
     * un cierto valor
     *
     * @param LS
     * @param actual
     */
    @Override
    protected void creaListaNodosArbol(Vector<NodoBusqueda> LS,
            NodoBusqueda actual) {
        for (NodoBusqueda n : LS) {
            if (!poda(n)) {
                frontera.insertarOrdenado(n);
            }
        }
    }
}
