/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
Heuristica
voraz a
poda
GPX costo solucion, profundidad solucion, nodos frontera maximo, coste solucion
tiempo proporcional de distancia entre nodos, estrategia
 */
package dominio;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author ordenador
 */
public class Problema {
    protected EspacioDeEstados espacioEstados;
    protected Estado inicial;
    
    
    
    public Problema(EspacioDeEstados espacio,long inicial,Vector<Long> destinos){
        
        espacioEstados=espacio;
        this.inicial=new Estado(espacioEstados.getNodo(inicial),cargaObjetivos(destinos));
    }
    
    public Problema(double longitud1,
            double latitud2,double longitud2,double latitud1, long inicial,Vector<Long> objetivos) 
            throws IOException, MalformedURLException, ParserConfigurationException, SAXException{
    
        this(new EspacioDeEstados(new Coordenadas(longitud1,latitud1,longitud2,latitud2)),inicial, objetivos);
    }
    
    public Problema(Coordenadas coor,long inicial,Vector<Long> objetivos)
            throws IOException, MalformedURLException, ParserConfigurationException, SAXException{
        this(new EspacioDeEstados(coor),inicial,objetivos);
    }

    
    
    public Estado getInicial() {
        return inicial;
    }

    public void setInicial(Estado inicial) {
        this.inicial = inicial;
    }
    
    public Vector<NodoBusqueda> sucesores(NodoBusqueda e){  
        Vector<Estado> estadosSucesores=espacioEstados.sucesores(e.getActual());
        Vector<NodoBusqueda> r=new Vector();
        double costoaux=0;
        for(Estado aux:estadosSucesores){
            costoaux=espacioEstados.peso(e.getActual(), aux);
            r.add(new NodoBusqueda(e,aux,e.getProfundidad()+1,
               costoaux+e.getCosto(),costoaux+e.getValor()));
        }
        return r;
    }
    
    
    public boolean testObjetivo(Estado e){      
        boolean r=false;
        Vector<Nodo> destinos=(Vector<Nodo>)e.getDestinos().clone();
        for(Nodo n:destinos){
            if(n.equals(e.getNodoActual())){
                e.getDestinos().remove(n);
                if(e.getDestinos().isEmpty()){
                    r=true;
                }
            }
        }
        return r;
    }

    protected Vector<Nodo> cargaObjetivos(Vector<Long> destinos) {
        Vector<Nodo> r=new Vector();
        for(long l:destinos){
            r.add(espacioEstados.getNodo(l));
        }
        return r;
    }    
}