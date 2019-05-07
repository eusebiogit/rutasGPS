package conexion;

import dominio.Coordenadas;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Analiza {

    Acceso a;

    //Creación de una clase Acceso que analizará el mapa a partir de sus coordenadas
    public Analiza(Coordenadas coor) throws IOException, MalformedURLException, ParserConfigurationException, SAXException {
        a = new Acceso(coor);
    }

    //Creación de una clase Acceso que analizará el mapa a partir de un mapa ya guardado
    public Analiza(String dir) throws SAXException, IOException, FileNotFoundException, ParserConfigurationException {
        a = new Acceso(dir);
    }

    //Obtener todos los elementos con un tag concreto del mapa
    public NodeList getTags(String nombreTag) {
        return a.getDocumento().getElementsByTagName(nombreTag);
    }

    //Obtención de todos los valores de un atributo. EJ: todos los "tags" de un "node"
    public static String getValorAtributoNode(Node n, String atributo) {
        String r = "";
        if (!n.getNodeName().equals("#text")) {
            r = n.getAttributes().getNamedItem(atributo).getNodeValue();
        }
        return r;
    }
}
