package presentacion;

import dominio.Ady;
import dominio.Busqueda;
import dominio.Estado;
import dominio.ConstruyeGrafo;
import dominio.Coordenadas;
import dominio.EspacioDeEstados;
import dominio.Frontera;
import dominio.Grafo;
import dominio.Nodo;
import dominio.NodoBusqueda;
import dominio.Problema;
import dominio.ProblemaA;
import dominio.ProblemaAnchura;
import dominio.ProblemaCostoUniforme;
import dominio.ProblemaProfundidad;
import dominio.ProblemaVoraz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import persistencia.OperacionesPersistencia;

public class InterfazUsuario {

    private int profundidad;
    private int distancia;

    private Grafo g;

    private EspacioDeEstados espacioDeEstados = null;
    private Problema problema = null;
    private long posicionInicial;
    private Vector<Long> destinos = null;
    private Busqueda busqueda;

    public void menu2() throws IOException, MalformedURLException, ParserConfigurationException, SAXException {

        double longitud1 = -2.14612;
        double latitud1 = 40.06529;
        double longitud2 = -2.13185;
        double latitud2 = 40.05771;

        Coordenadas coordenadas
                = new Coordenadas(longitud1, latitud1, longitud2, latitud2);
        espacioDeEstados = new EspacioDeEstados(coordenadas);

        destinos = new Vector();
        posicionInicial = (long) 1174994918;
        destinos.add(Long.parseLong("1174996512"));

        int profundidadMaxima = 23;

        boolean poda = true;

        // problema=new ProblemaAnchura(espacioDeEstados,posicionInicial,destinos);
        //problema=new ProblemaCostoUniforme(espacioDeEstados,posicionInicial,destinos);
        problema = new ProblemaProfundidad(espacioDeEstados, posicionInicial, destinos);
        //problema=new ProblemaVoraz(espacioDeEstados,posicionInicial,destinos); //6
        // problema=new ProblemaA(espacioDeEstados,posicionInicial,destinos); //7

        // busqueda=Busqueda.getBusqueda(problema,poda);
        busqueda = Busqueda.getBusqueda(problema, profundidadMaxima, poda);  //profundidad acotada
        //busqueda=Busqueda.getBusqueda(problema, profundidadMaxima, iteracion,poda);  //profundidad iterativa

        Vector<Estado> solucion = busqueda.buscar();

        if (solucion != null) {
            for (Estado e : solucion) {
                System.out.println(e);
            }
            OperacionesPersistencia.generarGPX(solucion);
        } else {
            System.out.println("No hay solucion");
        }
        System.out.println(busqueda);

    }

}
