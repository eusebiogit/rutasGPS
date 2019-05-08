package dominio;

import conexion.Analiza;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Esta clase se encarga de utilizar las herramientas del paquete conexión para
 * obtener la información del área seleccionada y mapearla a un grafo para
 * resolver el problema desde el punto de vista de la arquitectura seleccionada
 *
 * @author ordenador
 */
public class ConstruyeGrafo {

    private ArrayList<String> filtroHighway;
    private Grafo g;
    private Analiza a;

    /**
     *
     * @param coor
     * @throws IOException
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public ConstruyeGrafo(Coordenadas coor) throws IOException, MalformedURLException, ParserConfigurationException, SAXException {
        descargar(coor);
        g = new Grafo();
        inicializarFiltroVia();
        construir();
    }

    /**
     * Comprueba si el mapa ha sido descargado previamente y si no lo descarga
     * mediante la api de openstreetmap utilizando el constructor de Analiza
     * correspondiente en cada caso
     *
     * @param coor
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private void descargar(Coordenadas coor) throws SAXException, IOException, ParserConfigurationException {
        try {
            a = new Analiza("mapas/" + coor.getCoordenadasString() + ".osm");
        } catch (FileNotFoundException e) {
            //No lo encuentra entonces se lo descarga y lo guarda
            System.out.println("Descargando...");
            a = new Analiza(coor);
        }
    }

    /**
     *
     * @return
     */
    public Grafo getGrafo() {
        return g;
    }

    /**
     * Analiza la información descargada y construye un grafo equivalente
     */
    private void construir() {
        //Para meter los nodos que hacen falta
        ArrayList<String> idNodos = new ArrayList<String>();
        String idNDaux;
        Vector<Long> vND;   //vector de nodos

        //Se obtienen todos los tag "node" del osm
        NodeList nodos = a.getTags("node");
        //Se obtienen todos los tag "tag" del osm
        NodeList listaTags = a.getTags("tag");
        for (int i = 0; i < listaTags.getLength(); i++) {
            //Filtra los "tag" que interesan, los tags son 
            //hijos de "way" entonces se recogen los "way" de aquellos "tag"
            Node auxTgs = listaTags.item(i);

            if (Analiza.getValorAtributoNode(auxTgs, "k").equals("highway")
                    && filtroHighway.contains(Analiza.getValorAtributoNode(auxTgs, "v"))) {
                Node way = auxTgs.getParentNode();
                //De los way seleccionados se obtienen sus hijos 
                //Lista en la que se encuentran los "nd", ids de nodos enlazados en ese "way"
                NodeList hijosWay = way.getChildNodes();

                Node auxND;
                vND = new Vector<Long>();
                //Se recogen todos los nodos asociados a un "way"
                for (int j = 0; j < hijosWay.getLength(); j++) {
                    String id;
                    auxND = hijosWay.item(j);
                    if (auxND.getNodeName() == "nd") {
                        id = Analiza.getValorAtributoNode(auxND, "ref");
                        vND.add(Long.parseLong(id));

                        idNDaux = Analiza.getValorAtributoNode(auxND, "ref");
                        if (!idNodos.contains(idNDaux)) {
                            idNodos.add(idNDaux);
                        }
                    }
                }
                g.putWay(vND);
            }
        }

        //Se annaden al diccionario
        Nodo aux;
        for (int i = 0; i < nodos.getLength(); i++) {
            if (idNodos.contains(Analiza.getValorAtributoNode(nodos.item(i), "id"))) {
                aux = new Nodo(Long.parseLong(Analiza.getValorAtributoNode(nodos.item(i), "id")),
                        Double.parseDouble(Analiza.getValorAtributoNode(nodos.item(i), "lon")),
                        Double.parseDouble(Analiza.getValorAtributoNode(nodos.item(i), "lat")));
                g.putNodo(aux);
            }
        }
    }

    /**
     * Determinar que tags nos interesan de cada camino
     */
    private void inicializarFiltroVia() {
        filtroHighway = new ArrayList<String>();
        String filtro[] = {"motorway", "trunk", "primary", "secondary", "tertiary",
            "unclassified", "residential", "service", "pedestrian"};
        for (String i : filtro) {
            filtroHighway.add((String) i);
        }
    }
}
