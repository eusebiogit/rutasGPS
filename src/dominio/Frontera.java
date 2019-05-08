package dominio;

import java.util.Vector;

/**
 * La frontera contiene los nodos accesibles a la búsqueda en función y se
 * gestiona en funcion del algoritmo utilizado
 *
 * @author ordenador
 */
public class Frontera {

    private Vector<NodoBusqueda> frontera;

    public Frontera() {
        frontera = new Vector<NodoBusqueda>();
    }

    /**
     * Elimina un nodo de búsqueda de la frontera
     *
     * @return
     */
    public NodoBusqueda elimina() {

        NodoBusqueda r = frontera.firstElement();
        frontera.remove(r);
        return r;
    }

    /**
     * Inserta por el final
     *
     * @param es
     */
    public void insertar(NodoBusqueda es) {
        frontera.add(es);
    }

    /**
     * Para voraz y A
     *
     *
     * @param es
     */
    public void insertarOrdenado(NodoBusqueda es) {
        boolean dentro = false;
        if (!frontera.isEmpty()) {
            for (int i = 0; i < frontera.size() && !dentro; i++) {
                if (es.compareTo(frontera.get(i)) < 0) {
                    frontera.add(i, es);
                    dentro = true;
                }
            }
        }
        if (!dentro) {
            insertar(es);
        }
    }

    /**
     * Inserta por alante (pila)
     *
     * @param primero
     */
    public void insertarPrimero(NodoBusqueda primero) {
        frontera.add(0, primero);
    }

    /**
     *
     * @return
     */
    public boolean esVacia() {
        return frontera.isEmpty();
    }

    /**
     *
     * @return
     */
    public Vector<NodoBusqueda> getFrontera() {
        return frontera;
    }

    /**
     *
     * @param frontera
     */
    public void setFrontera(Vector<NodoBusqueda> frontera) {
        this.frontera = frontera;
    }
}
