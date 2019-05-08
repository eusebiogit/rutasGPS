package dominio;

import java.util.Vector;

/**
 * Se recorrerá el árbol de búsqueda con la estrategia en anchura
 *
 * @author ordenador
 */
public class BusquedaAnchura extends Busqueda {

    protected BusquedaAnchura(Problema problema, boolean poda) {
        super(problema, poda);
    }

    /**
     * Con la operación insertar, sobre la frontera, el algoritmo de búsqueda
     * tendrá como efecto realizar la búsqueda en anchura
     *
     * @param LS
     * @param actual
     */
    @Override
    protected void creaListaNodosArbol(Vector<NodoBusqueda> LS,
            NodoBusqueda actual) {
        //primero en anchura, frontera tipo cola
        for (NodoBusqueda n : LS) {
            if (!poda(n)) {
                frontera.insertar(n);
            }
        }
    }

}
