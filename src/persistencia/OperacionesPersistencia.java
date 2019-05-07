/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Estado;
import dominio.Nodo;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author ordenador
 */
public class OperacionesPersistencia {
    
    
    
    
    public static void guardarenFichero(String nombre,String contenido){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(nombre);
            pw = new PrintWriter(fichero);
            pw.println(contenido);
 
        } catch (Exception e) {
           System.out.println("ERROR AL ESCRIBIR EL MAPA EN UN FICHERO");
            e.printStackTrace();
        } 
        finally {
           try {
               if (null != fichero)
                  fichero.close();
            } catch (Exception e2) {
               System.out.println("ERROR AL COMPROBAR EL ESTADO DEL MAPA GUARDADO");
               e2.printStackTrace();
            }
        }
    }
    public static void generarGPX(Vector<Estado> solucion ){
        java.util.Date fecha = new Date();
        String res="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                + "<gpx version=\"1.0\">"
                + "\t<name>Posicion Inicial</name>"
                + "\t<wpt lat=\""+solucion.get(0).getNodoActual().getLatitud()
                +"\" lon=\""+solucion.get(0).getNodoActual().getLongitud()+"\">"
                + "\t\t<ele>"+solucion.get(0).getNodoActual().getId()+"</ele>"
                + "\t\t<name>Solucion</name>"
                + "\t</wpt>"
                + "\t<trk><name>Ruta</name><number>1</number><trkseg>";
        Nodo actual;
        for(Estado e:solucion){
            actual=e.getNodoActual();
            res+="\t\t<trkpt lat=\""+actual.getLatitud()+"\""
                    +" lon=\""+actual.getLongitud()+"\">"
                    + "\t\t\t<ele>"+actual.getId()+"</ele>"
                    + "\t\t\t<time>"+fecha+"</time>"
                    + "\t\t</trkpt>";
        }
        res+="\t</trkseg>\t</trk></gpx>";
        guardarenFichero("rutas/ruta"+fecha+".gpx",res);
        
    }
}





















