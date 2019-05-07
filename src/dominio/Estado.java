package dominio;

import java.util.Objects;
import java.util.Vector;

/**
 * Un estado es representado por el nodo y el conjunto de destinos que se
 * aspiran alcanzar desde ese nodo
 *
 * @author ordenador
 */
public class Estado {

    private Nodo nodoActual;
    private Vector<Nodo> destinos;

    /**
     *
     * @param nodoActual
     * @param nodosDestinos
     */
    public Estado(Nodo nodoActual, Vector<Nodo> nodosDestinos) {
        this.nodoActual = nodoActual;
        this.destinos = (Vector<Nodo>) nodosDestinos.clone();
    }

    /**
     *
     * @return
     */
    public Nodo getNodoActual() {
        return nodoActual;
    }

    /**
     *
     * @param nodoActual
     */
    public void setNodoActual(Nodo nodoActual) {
        this.nodoActual = nodoActual;
    }

    /**
     *
     * @return
     */
    public Vector<Nodo> getDestinos() {
        return destinos;
    }

    /**
     *
     * @param destinos
     */
    public void setDestinos(Vector<Nodo> destinos) {
        this.destinos = destinos;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Estado {" + " nodoActual = " + nodoActual + ","
                + " destinos = " + destinos + "}";
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.nodoActual);
        hash = 37 * hash + Objects.hashCode(this.destinos);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Estado other = (Estado) obj;
        if (!Objects.equals(this.nodoActual, other.nodoActual)) {
            return false;
        }
        if (!Objects.equals(this.destinos, other.destinos)) {
            return false;
        }
        return true;
    }

}
