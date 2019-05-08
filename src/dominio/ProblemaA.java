package dominio;

import java.util.Vector;

/**
 * Para la resolución mediante la estrategia A
 *
 *
 * @author ordenador
 */
public class ProblemaA extends ProblemaVoraz {

    /**
     *
     * @param espacio
     * @param inicial
     * @param destinos
     */
    public ProblemaA(EspacioDeEstados espacio, long inicial, Vector<Long> destinos) {
        super(espacio, inicial, destinos);
    }

    /**
     * En este caso los sucesores añaden al nodo de búsqueda el costo y la
     * heurística
     *
     * @param e
     * @return
     */
    public Vector<NodoBusqueda> sucesores(NodoBusqueda e) {
        Vector<Estado> estadosSucesores = espacioEstados.sucesores(e.getActual());
        Vector<NodoBusqueda> r = new Vector();
        double costoaux = 0;
        double h;
        for (Estado aux : estadosSucesores) {
            h = heuristica(aux);
            costoaux = espacioEstados.peso(e.getActual(), aux);
            r.add(new NodoBusqueda(e, aux, e.getProfundidad() + 1,
                    costoaux + e.getCosto(), e.getCosto() + h + costoaux));
        }
        return r;
    }

}
