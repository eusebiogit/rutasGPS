package dominio;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Abstrae el grafo para utilizarlo como un espacio de estados
 *
 * @author ordenador
 */
public class EspacioDeEstados {

    private Grafo grafo;

    /**
     * Constructor en caso de que no haya sido construido el grafo aun
     *
     * @param coor
     * @throws IOException
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public EspacioDeEstados(Coordenadas coor
    ) throws IOException, MalformedURLException, ParserConfigurationException, SAXException {
        grafo = new ConstruyeGrafo(coor).getGrafo();
    }

    /**
     *
     * @param longitud1
     * @param latitud2
     * @param longitud2
     * @param latitud1
     * @throws IOException
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public EspacioDeEstados(double longitud1,
            double latitud2, double longitud2, double latitud1)
            throws IOException, MalformedURLException, ParserConfigurationException, SAXException {
        this(new Coordenadas(longitud1, latitud1, longitud2, latitud2));
    }

    /**
     *
     * @param e1
     * @param e2
     * @return
     */
    public double peso(Estado e1, Estado e2) {
        return grafo.getPeso(e1.getNodoActual(), e2.getNodoActual());
    }

    /**
     *
     * @param e1
     * @param e2
     * @return
     */
    public double getDistancia(Nodo e1, Nodo e2) { //linea recta
        return grafo.getDistancia(e1, e2);
    }

    /**
     *
     * @param es
     * @return
     */
    public Vector<Estado> sucesores(Estado es) {
        Vector<Nodo> v = grafo.adyacentesNodo(es.getNodoActual().getId());
        Vector<Estado> vE = new Vector<Estado>();
        for (Nodo n : v) {
            if (n.getId() != es.getNodoActual().getId()) {
                vE.add(new Estado(n, es.getDestinos()));
            }
        }
        return vE;
    }

    /**
     * Convertir un vector de Long en un vector de Nodo
     *
     * @param o
     * @return
     */
    private Vector<Nodo> cargaObjetivos(Vector<Long> o) {
        Vector<Nodo> objetivosNodo = new Vector<Nodo>();
        for (int i = 0; i < o.size(); i++) {
            objetivosNodo.add(grafo.getNodo(o.get(i)));
        }
        return objetivosNodo;
    }

    /**
     *
     * @param n
     * @return
     */
    public boolean esValido(Nodo n) {
        return grafo.esta(n);
    }

    /**
     *
     * @param id
     * @return
     */
    public Nodo getNodo(long id) {
        return grafo.getNodo(id);
    }

    /**
     *
     * @return
     */
    public Grafo getGrafo() {
        return grafo;
    }
}
