/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author ordenador
 */
public abstract class Busqueda {

    protected Problema problema;
    protected double costo;
    protected int complejidadEspacial;
    protected double complejidadTemporal;
    protected Frontera frontera;
    protected boolean poda;
    protected int profundidad;
    protected Hashtable<Integer, Double> estados;

    protected Busqueda(Problema problema, boolean poda) {
        this.problema = problema;
        frontera = new Frontera();
        estados = new Hashtable();
        this.poda = poda;
    }

    public int getComplejidadEspacial() {
        return complejidadEspacial;
    }

    public double getComplejidadTemporal() {
        return complejidadTemporal;
    }

    public double getCosto() {
        return costo;
    }

    protected boolean poda(NodoBusqueda n) {
        boolean r = false;
        if (poda) {
            Integer key = n.getActual().hashCode();
            Double valor = estados.get(key);
            if (valor == null) {
                estados.put(key, n.getValor());
            } else {
                if (valor <= n.getValor()) {
                    r = true;
                } else {
                    estados.put(key, n.getValor());
                }
            }
        }
        return r;
    }

    public Vector<Estado> buscar() {  //busqueda basica
        Vector<NodoBusqueda> LS = null;

        Vector<Estado> solucion = null;

        NodoBusqueda actual = null;

        NodoBusqueda inicial = new NodoBusqueda(
                null, problema.getInicial(), 0, 0, 0);
        frontera.insertar(inicial);
        double tiempoInicio = System.currentTimeMillis();
        boolean resuelto = false;
        do {
            actual = frontera.elimina();
            if (problema.testObjetivo(actual.getActual())) {
                resuelto = true;
            } else {
                LS = problema.sucesores(actual);
                creaListaNodosArbol(LS, actual);
                complejidadEspacial += LS.size();
            }

        } while (!resuelto && !frontera.esVacia());
        if (resuelto) {
            solucion = creaSolucion(actual);
        } else {
            //No hay solucion => solucion = null
        }
        complejidadTemporal = (System.currentTimeMillis() - tiempoInicio) / 1000;
        return solucion;
    }

    protected abstract void creaListaNodosArbol(
            Vector<NodoBusqueda> LS, NodoBusqueda actual);

    protected Vector<Estado> creaSolucion(NodoBusqueda actual) {
        costo = actual.getCosto();
        profundidad = actual.getProfundidad();

        Stack<Estado> pila = new Stack();
        Vector<Estado> r = new Vector();
        NodoBusqueda aux = actual;
        while (aux != null) {
            pila.add(aux.getActual());
            aux = aux.getPadre();

        }
        while (!pila.empty()) {
            r.add(pila.pop());
        }
        return r;
    }

    public static Busqueda getBusqueda(Problema problema, boolean poda) {
        Busqueda r = null;
        if (problema instanceof ProblemaCostoUniforme) {
            r = new BusquedaCostoUniforme(problema, poda);
        } else if (problema instanceof ProblemaAnchura) {
            r = new BusquedaAnchura(problema, poda);
        } else if (problema instanceof ProblemaProfundidad) {
            r = new BusquedaProfundidadSimple(problema, poda);
        } else if (problema instanceof ProblemaA) {
            r = new BusquedaA(problema, poda);
        } else if (problema instanceof ProblemaVoraz) {
            r = new BusquedaVoraz(problema, poda);
        }

        return r;
    }

    public static Busqueda getBusqueda(Problema problema, int profundidadMaxima, boolean poda) {
        Busqueda r = null;
        if (problema instanceof ProblemaProfundidad) {
            r = new BusquedaProfundidadAcotada(problema, profundidadMaxima, poda);
        }
        return r;
    }

    public static Busqueda getBusqueda(Problema problema, int profundidadMaxima, int iteracion, boolean poda) {
        Busqueda r = null;
        if (problema instanceof ProblemaProfundidad) {
            r = new BusquedaProfundidadIterativa(problema, profundidadMaxima, iteracion, poda);
        }
        return r;
    }

    @Override
    public String toString() {
        return "Complejidad Temporal: " + getComplejidadTemporal() + " s\n"
                + "Complejidad Espacial: " + getComplejidadEspacial() + " nodos\n"
                + "Costo: " + getCosto() + " m\n"
                + "Profundidad: " + profundidad;
    }

}
