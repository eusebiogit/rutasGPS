package dominio;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * La información del área descargada de la api de openstreetmap se abstrae en
 * el formato de un grafo
 *
 * @author ordenador
 */
public class Grafo {

    private Hashtable<Long, Nodo> indice;
    private Hashtable<Long, Vector<Ady>> tablaGrafo;

    /**
     *
     */
    public Grafo() {
        tablaGrafo = new Hashtable<Long, Vector<Ady>>();
        indice = new Hashtable<Long, Nodo>();
    }

    /**
     *
     * @return
     */
    public Hashtable<Long, Nodo> getTablaIndice() {
        return indice;
    }

    /**
     * Inserta un nodo en el grafo
     *
     * @param id
     * @param v
     */
    public void putVertice(Long id, Vector<Ady> v) {

        Vector<Ady> ady = tablaGrafo.get(id);
        //se controla si el nodo ha sido annadido antes
        if (ady != null) {
            v.addAll(ady);
        }
        tablaGrafo.put(id, v);
    }

    /**
     * lista de nodos adyacentes
     *
     * @param id
     * @return
     */
    public Vector<Ady> adyacentes(Long id) {
        return tablaGrafo.get((Long) id);
    }

    /**
     *
     * @param n
     * @return
     */
    public Vector<Nodo> adyacentesNodo(Nodo n) {
        return adyacentesNodo(n.getId());
    }

    /**
     *
     * @param id
     * @return
     */
    public Vector<Nodo> adyacentesNodo(Long id) {
        Vector<Ady> ady = adyacentes(id);
        Vector<Nodo> r = new Vector<Nodo>();
        Nodo aux;
        for (Ady a : ady) {
            aux = new Nodo(a.getId(), indice.get(a.getId()).getLongitud(), indice.get(a.getId()).getLatitud());
            r.add(aux);
        }
        return r;
    }

    /**
     *
     * @return
     */
    public boolean esVacio() {
        return tablaGrafo.isEmpty();
    }

    /**
     *
     * @param v
     */
    public void putWay(Vector<Long> v) {
        if (v != null) {
            Vector<Ady> r;
            Ady auxAdy;
            for (int i = 0; i < v.size(); i++) {
                r = new Vector<Ady>();
                if (i > 0) {
                    auxAdy = new Ady(v.get(i - 1), 0, v.get(i));
                    r.add(auxAdy);
                }
                if (i < v.size() - 1) {
                    auxAdy = new Ady(v.get(i + 1), 0, v.get(i));
                    r.add(auxAdy);
                }
                putVertice(v.get(i), r);
            }
        }
    }

    /**
     * coloca un nodo en la tabla indice
     *
     * @param n
     */
    public void putNodo(Nodo n) {
        indice.put(n.getId(), n);
    }

    /**
     * Devuelve el objeto nodo de la tabla indice de nodos pasandole un id
     *
     * @param id
     * @return
     */
    public Nodo getNodo(Long id) {
        return indice.get((Long) id);
    }

    /**
     *
     * @param n
     * @return
     */
    public boolean esta(Nodo n) {
        return getNodo(n.getId()) != null;
    }

    /**
     * Devuelve un string con una representacion del contenido del grafo
     *
     * @return
     */
    public String toString() {
        String r = "{";
        Enumeration<Long> e = tablaGrafo.keys();
        Long auxLong = e.nextElement();
        Vector<Ady> auxAdyV;
        Ady auxAdy;
        Nodo auxNodo;
        for (; e.hasMoreElements(); auxLong = e.nextElement()) {
            auxNodo = indice.get(auxLong);
            r += auxLong + " : { " + auxNodo + ", 'Ady': [";
            auxAdyV = tablaGrafo.get(auxLong);
            for (int i = 0; i < auxAdyV.size(); i++) {
                auxAdy = auxAdyV.get(i);
                if (auxAdy.getPeso() == 0) {
                    putPeso(auxNodo, auxAdy);
                }
                r += auxAdy + ", ";
            }
            r += "]}, ";
        }
        return r;
    }

    /**
     * registra el peso entre un nodo y su adyacente
     *
     * @param n1
     * @param ady
     */
    private void putPeso(Nodo n1, Ady ady) {
        Nodo n2 = indice.get((Long) ady.getId());
        double peso = n1.getDistancia(n2);
        ady.setPeso(peso);
    }

    /**
     * devuelve el peso entre un nodo y su adyacente
     *
     * @param n1
     * @param ady
     * @return
     */
    private double getPeso(Nodo n1, Ady ady) {
        if (n1.equals(indice.get(ady.getIdPredecesor())) && ady.getPeso() == 0) {
            putPeso(n1, ady);
        }
        return ady.getPeso();
    }

    /**
     * devuelve el peso entre dos nodos
     *
     * @param n1
     * @param n2
     * @return
     */
    public double getPeso(Nodo n1, Nodo n2) {
        double peso = 0;
        if (esNodoAdyacente(n1, n2)) {
            peso = getPeso(n1, devolverAdy(n1, n2));
        } else {
            peso = getPesoExploracion(n1, n2);
        }
        return peso;
    }

    /**
     * distancia en linea recta
     *
     * @param n1
     * @param n2
     * @return
     */
    public double getDistancia(Nodo n1, Nodo n2) {
        double r = 0;
        if (esNodoAdyacente(n1, n2)) {
            r = getPeso(n1, devolverAdy(n1, n2));
        } else {
            r = n1.getDistancia(n2);

        }
        return r;
    }

    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    public boolean esNodoAdyacente(Nodo n1, Nodo n2) {
        return devolverAdy(n1, n2) != null;
    }

    /**
     * Devuelve el ady asociado con n2 si n2 es adyacente de n1 null en caso
     * contrario
     *
     * @param n1
     * @param n2
     * @return
     */
    public Ady devolverAdy(Nodo n1, Nodo n2) {
        Vector<Ady> va = tablaGrafo.get(n1.getId());
        Ady a = va.get(0);
        for (int i = 1; a.getId() != n2.getId() && i < va.size(); i++) {
            a = va.get(i);
        }
        return a.getId() == n2.getId() ? a : null;
    }

    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    private double getPesoExploracion(Nodo n1, Nodo n2) {
        //Explora los adyacentes recursivamente sumando los pesos
        //hasta encontrar el objetivo

        return 0;
    }
}
