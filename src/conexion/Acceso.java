package conexion;

import dominio.Coordenadas;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import persistencia.OperacionesPersistencia;

/**
 * Se encarga de descargar de openstreetmap la información necesaria para
 * resolver el problema
 *
 * @author ordenador
 */
public class Acceso {

    private static String dir = "https://www.openstreetmap.org/api/0.6/map?"
            + "bbox=";

    private URL url;
    private Document doc;

    /**
     * A partir de las Coordenadas proporcionadass se descarga la información
     * del área, mediante la api de openstreemap y la guarda en un archivo
     *
     * @param coor
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public Acceso(Coordenadas coor) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        url = new URL(dir + coor.getCoordenadasString());
        String contenidoArchivo;
        InputSource archivo = new InputSource();
        //Se abre la dirección que contiene el mapa y se guarda en un archivo

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        contenidoArchivo = leerBR(br);
        archivo.setCharacterStream(new StringReader(contenidoArchivo));

        //Se crea y se guarda un documento tratable con la información del mapa
        crearDoc(archivo);
        guardarDoc(contenidoArchivo, coor);
    }

    /**
     * Si el mapa pedido ya ha sido descargado se accederá mediante archivo
     *
     * @param dir
     * @throws FileNotFoundException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public Acceso(String dir) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
        BufferedReader br = new BufferedReader(new FileReader(dir));
        InputSource archivo = new InputSource();

        //Se lee el archivo ya guardado y se crea un documento tratable
        archivo.setCharacterStream(new StringReader(leerBR(br)));
        crearDoc(archivo);

    }

    /**
     * Crea un archivo para en el que guarda la información de un área
     * descargada
     *
     * @param archivo
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private void crearDoc(InputSource archivo) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(archivo);
        doc.getDocumentElement().normalize();
    }

    /**
     * Realiza el registro de la información descargada en un archivo en el
     * direcctorio mapas
     *
     * @param contenido
     * @param nombre
     */
    private void guardarDoc(String contenido, Coordenadas nombre) {
        OperacionesPersistencia.guardarenFichero("mapas/" + nombre.getCoordenadasString() + ".osm", contenido);
    }

    /**
     * get de la información en formato Document
     *
     * @return
     */
    public Document getDocumento() {
        return doc;
    }

    /**
     * Lectura de archivo en formato br
     *
     * @param br
     * @return
     * @throws IOException
     */
    public String leerBR(BufferedReader br) throws IOException {
        String res = "";
        String entrada;
        while ((entrada = br.readLine()) != null) {
            res = res + entrada;
        }
        try {
            br.close();
        } catch (Exception ex) {
        }
        return res;
    }
}
