/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Vector;

/**
 * A partir de profundidad acotada se puede particularizar a profundidad
 * iterativa
 *
 * @author ordenador
 */
public class BusquedaProfundidadIterativa extends BusquedaProfundidadAcotada {

    protected int iteracion;
    protected double tiempoInicioTotal;

    protected double complejidadTemporalTotal;
    protected int complejidadEspacialFinal;

    protected Vector<Double> complejidadTemporalIteraciones;
    protected Vector<Integer> complejidadEspacialIteraciones;

    /**
     *
     * @param problema
     * @param profundidadMaxima
     * @param iteracion
     * @param poda
     */
    protected BusquedaProfundidadIterativa(Problema problema,
            int profundidadMaxima, int iteracion, boolean poda) {
        super(problema, profundidadMaxima, poda);
        this.iteracion = iteracion;
        complejidadTemporalIteraciones = new Vector();
        complejidadEspacialIteraciones = new Vector();
    }

    /**
     * Se realizan las extensiones sobre el método buscar
     *
     * @return
     */
    @Override
    public Vector<Estado> buscar() {
        Vector<Estado> res;
        double tiempoInicioTotal = System.currentTimeMillis();
        do {
            res = super.buscar();
            if (res == null) {
                profundidadMaxima += iteracion;
            }
            complejidadTemporalIteraciones.add(super.getComplejidadTemporal());
            complejidadEspacialIteraciones.add(super.getComplejidadEspacial());
        } while (res == null);
        complejidadTemporalTotal = (System.currentTimeMillis() - tiempoInicioTotal) / 1000;
        complejidadEspacialFinal = super.getComplejidadEspacial();
        return res;
    }

    /**
     *
     * @return
     */
    @Override
    public double getComplejidadTemporal() {
        return complejidadTemporalTotal;
    }

    /**
     *
     * @return
     */
    @Override
    public int getComplejidadEspacial() {
        return complejidadEspacialFinal;
    }

    /**
     *
     * @return
     */
    public Vector<Double> getComplejidadTemporalIteraciones() {
        return complejidadTemporalIteraciones;
    }

    /**
     *
     * @return
     */
    public Vector<Integer> getComplejidadEspacialIteraciones() {
        return complejidadEspacialIteraciones;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String r = "Complejidad espacial en cada iteracion:\n";
        for (int i = 0; i < complejidadEspacialIteraciones.size(); i++) {
            r += "\tIteracion " + i + ": " + complejidadEspacialIteraciones.get(i) + "\n";
        }
        r += "Complejidad temporal en cada iteracion:\n";
        for (int i = 0; i < complejidadTemporalIteraciones.size(); i++) {
            r += "\tIteracion " + i + ": " + complejidadTemporalIteraciones.get(i) + "\n";
        }
        r += "Profundidad Maxima final: " + profundidadMaxima;
        r += "\nComplejidad Total:\n";
        r += super.toString();
        return r;
    }

}
