package dominio;

/**
 * El nodo de búsqueda se crea a partir de los estados para crear el arbol de
 * búsqueda
 *
 * @author ordenador
 */
public class NodoBusqueda implements Comparable {

    private NodoBusqueda padre;
    private Estado actual;
    private int profundidad;
    private double costo;
    private double valor;

    /**
     *
     * @param padre
     * @param actual
     * @param profundidad
     * @param costo
     * @param valor
     */
    public NodoBusqueda(NodoBusqueda padre, Estado actual, int profundidad,
            double costo, double valor) {
        this.padre = padre;
        this.actual = actual;
        this.profundidad = profundidad;
        this.costo = costo;
        this.valor = valor;
    }

    /**
     *
     * @return
     */
    public NodoBusqueda getPadre() {
        return padre;
    }

    /**
     *
     * @param padre
     */
    public void setPadre(NodoBusqueda padre) {
        this.padre = padre;
    }

    /**
     *
     * @return
     */
    public Estado getActual() {
        return actual;
    }

    /**
     *
     * @param actual
     */
    public void setActual(Estado actual) {
        this.actual = actual;
    }

    /**
     *
     * @return
     */
    public int getProfundidad() {
        return profundidad;
    }

    /**
     *
     * @param profundidad
     */
    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    /**
     *
     * @return
     */
    public double getCosto() {
        return costo;
    }

    /**
     *
     * @param costo
     */
    public void setCosto(int costo) {
        this.costo = costo;
    }

    /**
     *
     * @return
     */
    public double getValor() {
        return valor;
    }

    /**
     *
     * @param valor
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        return (int) ((int) valor - ((NodoBusqueda) o).getValor());
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return actual + "";
    }

}
