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

public class ConstruyeGrafo {
    
    private ArrayList<String> filtroHighway;
    private Grafo g;
    private Analiza a;
    
    public ConstruyeGrafo(Coordenadas coor) throws IOException, MalformedURLException, ParserConfigurationException, SAXException {
        descargar(coor);
        g=new Grafo();
        inicializarFiltroVia();
        construir();
    }

    private void descargar(Coordenadas coor) throws SAXException, IOException, ParserConfigurationException{
        try{
            a=new Analiza("mapas/"+coor.getCoordenadasString()+".osm");
        }catch(FileNotFoundException e){
            //No lo encuentra entonces se lo descarga y lo guarda
            System.out.println("Descargando...");
            a=new Analiza(coor);
        }
    }
    
 
    public Grafo getGrafo(){
        return g;
    }
        
    private void construir(){
        //Para meter los nodos que hacen falta
    	ArrayList<String> idNodos=new ArrayList<String>();
        String idNDaux;
        Vector<Long> vND;   //vector de nodos

        //Se obtienen todos los tag "node" del osm
        NodeList nodos=a.getTags("node");
        //Se obtienen todos los tag "tag" del osm
        NodeList listaTags=a.getTags("tag");
        for(int i=0;i<listaTags.getLength();i++){
            //Filtra los "tag" que interesan, los tags son 
            //hijos de "way" entonces se recogen los "way" de aquellos "tag"
            Node auxTgs=listaTags.item(i);
            
            if(Analiza.getValorAtributoNode(auxTgs, "k").equals("highway")&&
                    filtroHighway.contains(Analiza.getValorAtributoNode(auxTgs,"v"))){
                Node way=auxTgs.getParentNode();
                //De los way seleccionados se obtienen sus hijos 
                //Lista en la que se encuentran los "nd", ids de nodos enlazados en ese "way"
                NodeList hijosWay=way.getChildNodes();
                
                Node auxND;
                vND=new Vector<Long>();
                //Se recogen todos los nodos asociados a un "way"
                for(int j=0;j<hijosWay.getLength();j++){                    
                    String id;
                    auxND=hijosWay.item(j);
                    if(auxND.getNodeName()=="nd"){
                        id=Analiza.getValorAtributoNode(auxND, "ref");
                        vND.add(Long.parseLong(id));
                    
                        idNDaux=Analiza.getValorAtributoNode(auxND, "ref");
                        if(!idNodos.contains(idNDaux)){
                            idNodos.add(idNDaux);
                        }
                    }
                }
                g.putWay(vND);
            }
        }

        //Se annaden al diccionario
        Nodo aux;
        for(int i=0;i<nodos.getLength();i++){
            if(idNodos.contains(Analiza.getValorAtributoNode(nodos.item(i), "id"))){
                aux=new Nodo(Long.parseLong(Analiza.getValorAtributoNode(nodos.item(i), "id")),
                			 Double.parseDouble(Analiza.getValorAtributoNode(nodos.item(i), "lon")),
                			 Double.parseDouble(Analiza.getValorAtributoNode(nodos.item(i), "lat")));
                g.putNodo(aux);
            }
        }
    }

    //Determinar que tags nos interesan de cada camino
    private void inicializarFiltroVia() {
        filtroHighway=new ArrayList<String>();
        String filtro[]={"motorway","trunk","primary","secondary","tertiary",
            "unclassified","residential","service","pedestrian"};
        for(String i:filtro){
            filtroHighway.add((String)i);
        }
    }
}
