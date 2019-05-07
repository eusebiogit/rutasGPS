/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;
/**
 *
 * @author ordenador
 */
public class NodoBusqueda implements Comparable{
    private NodoBusqueda padre;
    private Estado actual;
    private int profundidad;
    private double costo;
    private double valor;
    //accion
    

    public NodoBusqueda(NodoBusqueda padre, Estado actual, int profundidad, 
            double costo, double valor) {
        this.padre = padre;
        this.actual = actual;
        this.profundidad = profundidad;
        this.costo = costo;
        this.valor = valor;
    }

    public NodoBusqueda getPadre() {
        return padre;
    }

    public void setPadre(NodoBusqueda padre) {
        this.padre = padre;
    }

    public Estado getActual() {
        return actual;
    }

    public void setActual(Estado actual) {
        this.actual = actual;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public int compareTo(Object o) {
        return (int) ((int)valor-((NodoBusqueda)o).getValor());
    }
    @Override
    public String toString(){
        return actual+"";
    }
    
}
