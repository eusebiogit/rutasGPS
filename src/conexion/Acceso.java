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

    //Acceso al mapa para su descarga por las coordenadas
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

    //Acceso a la dirección en la que se encuentra un mapa ya descargado0
    public Acceso(String dir) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
        BufferedReader br = new BufferedReader(new FileReader(dir));
        InputSource archivo = new InputSource();

        //Se lee el archivo ya guardado y se crea un documento tratable
        archivo.setCharacterStream(new StringReader(leerBR(br)));
        crearDoc(archivo);

    }

    private void crearDoc(InputSource archivo) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(archivo);
        doc.getDocumentElement().normalize();
    }

    private void guardarDoc(String contenido, Coordenadas nombre) {
        OperacionesPersistencia.guardarenFichero("mapas/" + nombre.getCoordenadasString() + ".osm", contenido);

    }

    public Document getDocumento() {
        return doc;
    }

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
