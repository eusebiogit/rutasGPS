package dominio;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Clase general para resolver el problema de búsqueda
 *
 * @author ordenador
 */
public class Problema {

    protected EspacioDeEstados espacioEstados;
    protected Estado inicial;

    /**
     *
     * @param espacio
     * @param inicial
     * @param destinos
     */
    public Problema(EspacioDeEstados espacio, long inicial, Vector<Long> destinos) {
        espacioEstados = espacio;
        this.inicial = new Estado(espacioEstados.getNodo(inicial), cargaObjetivos(destinos));
    }

    /**
     *
     * @param longitud1
     * @param latitud2
     * @param longitud2
     * @param latitud1
     * @param inicial
     * @param objetivos
     * @throws IOException
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public Problema(double longitud1,
            double latitud2, double longitud2, double latitud1, long inicial, Vector<Long> objetivos)
            throws IOException, MalformedURLException, ParserConfigurationException, SAXException {

        this(new EspacioDeEstados(new Coordenadas(longitud1, latitud1, longitud2, latitud2)), inicial, objetivos);
    }

    /**
     *
     * @param coor
     * @param inicial
     * @param objetivos
     * @throws IOException
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public Problema(Coordenadas coor, long inicial, Vector<Long> objetivos)
            throws IOException, MalformedURLException, ParserConfigurationException, SAXException {
        this(new EspacioDeEstados(coor), inicial, objetivos);
    }

    /**
     * Nodo inicial en el árbol de búsqueda
     *
     * @return
     */
    public Estado getInicial() {
        return inicial;
    }

    /**
     *
     * @param inicial
     */
    public void setInicial(Estado inicial) {
        this.inicial = inicial;
    }

    /**
     * Devuelve los nodos de búsqueda accesibles desde el actual
     *
     * @param e
     * @return
     */
    public Vector<NodoBusqueda> sucesores(NodoBusqueda e) {
        Vector<Estado> estadosSucesores = espacioEstados.sucesores(e.getActual());
        Vector<NodoBusqueda> r = new Vector();
        double costoaux = 0;
        for (Estado aux : estadosSucesores) {
            costoaux = espacioEstados.peso(e.getActual(), aux);
            r.add(new NodoBusqueda(e, aux, e.getProfundidad() + 1,
                    costoaux + e.getCosto(), costoaux + e.getValor()));
        }
        return r;
    }

    /**
     * Comprueba si el problema ha sido resuelto en el estado actual
     *
     * @param e
     * @return
     */
    public boolean testObjetivo(Estado e) {
        boolean r = false;
        Vector<Nodo> destinos = (Vector<Nodo>) e.getDestinos().clone();
        for (Nodo n : destinos) {
            if (n.equals(e.getNodoActual())) {
                e.getDestinos().remove(n);
                if (e.getDestinos().isEmpty()) {
                    r = true;
                }
            }
        }
        return r;
    }

    /**
     *
     * @param destinos
     * @return
     */
    protected Vector<Nodo> cargaObjetivos(Vector<Long> destinos) {
        Vector<Nodo> r = new Vector();
        for (long l : destinos) {
            r.add(espacioEstados.getNodo(l));
        }
        return r;
    }
}
