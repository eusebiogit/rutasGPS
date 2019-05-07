package dominio;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Grafo {
	private Hashtable<Long,Nodo> indice;
    private Hashtable<Long,Vector<Ady>> tablaGrafo;
    
    public Grafo(){
        tablaGrafo=new Hashtable<Long,Vector<Ady>>();
        indice=new Hashtable<Long,Nodo>();
    }

    public Hashtable<Long, Nodo> getTablaIndice(){
        return indice;
    }
    
    //Inserta un nodo en el grafo
    public void putVertice(Long id,Vector<Ady> v){
        //se controla si el nodo ha sido annadido antes
        
        Vector<Ady> ady=tablaGrafo.get(id);
        if(ady!=null){
            v.addAll(ady);
        }
        tablaGrafo.put(id, v);
    }
    
    //lista de nodos adyacentes
    public Vector<Ady> adyacentes(Long id){
        return tablaGrafo.get((Long)id);
    }
    
    public Vector<Nodo> adyacentesNodo(Nodo n){
        return adyacentesNodo(n.getId());
    }
    
    public Vector<Nodo> adyacentesNodo(Long id){
        Vector<Ady> ady=adyacentes(id);
        Vector<Nodo> r = new Vector<Nodo>();
        Nodo aux;
        for(Ady a:ady){
            aux=new Nodo(a.getId(), indice.get(a.getId()).getLongitud(), indice.get(a.getId()).getLatitud());
            r.add(aux);
        }
        return r;
    }
    
    public boolean esVacio(){
        return tablaGrafo.isEmpty();
    }
    
    public void putWay(Vector<Long> v){
        if(v!=null){
            Vector<Ady> r;
            Ady auxAdy;
            for(int i=0;i<v.size();i++){
                r=new Vector<Ady>();
                if(i>0){
                    auxAdy=new Ady(v.get(i-1),0,v.get(i));
                    r.add(auxAdy);
                }
                if(i<v.size()-1){
                    auxAdy=new Ady(v.get(i+1),0,v.get(i));
                    r.add(auxAdy);
                }
                putVertice(v.get(i),r);
            }
        }
    }
    
    //coloca un nodo en la tabla indice
    public void putNodo(Nodo n){
        indice.put(n.getId(), n);
    }
    
    //Devuelve el objeto nodo de la tabla indice de nodos pasandole un id
    public Nodo getNodo(Long id){
        return indice.get((Long)id);
    }
   
    public boolean esta(Nodo n){
        return getNodo(n.getId())!=null;
    }
    
    //Devuelve un string con una representacion del contenido del grafo
    public String toString(){
        String r="{";
        Enumeration<Long> e=tablaGrafo.keys();
        Long auxLong=e.nextElement();
        Vector<Ady> auxAdyV;
        Ady auxAdy;
        Nodo auxNodo;
        for(;e.hasMoreElements();auxLong=e.nextElement()){
            auxNodo=indice.get(auxLong);
            r+=auxLong+" : { "+auxNodo+", 'Ady': [";
            auxAdyV=tablaGrafo.get(auxLong);
            for(int i=0;i<auxAdyV.size();i++){
                auxAdy=auxAdyV.get(i);
                if(auxAdy.getPeso()==0){
                    putPeso(auxNodo,auxAdy);
                }
                r+=auxAdy+", ";
            }
            r+="]}, ";
        }
        return r;
    }

    //registra el peso entre un nodo y su adyacente
    private void putPeso(Nodo n1, Ady ady) {
        Nodo n2= indice.get((Long)ady.getId());
        double peso=n1.getDistancia(n2);
        ady.setPeso(peso);
    }
    
    //devuelve el peso entre un nodo y su adyacente
    private double getPeso(Nodo n1, Ady ady){
        if(n1.equals(indice.get(ady.getIdPredecesor()))&&ady.getPeso()==0){
            putPeso(n1,ady);
        }
        return ady.getPeso();
    }
    
    //devuelve el peso entre dos nodos
    public double getPeso(Nodo n1,Nodo n2){
        double peso=0;
        if(esNodoAdyacente(n1,n2)){
            peso=getPeso(n1,devolverAdy(n1,n2));
        }else{
            peso=getPesoExploracion(n1,n2);
        }
        return peso;
    }
    
    //distancia en linea recta
    public double getDistancia(Nodo n1,Nodo n2){ 
        double r=0;
        if(esNodoAdyacente(n1,n2)){
            r=getPeso(n1,devolverAdy(n1,n2));
        }else{
            r=n1.getDistancia(n2);

        }
        return r;
    }
    
    public boolean esNodoAdyacente(Nodo n1,Nodo n2){
        return devolverAdy(n1,n2)!=null;
    }
 
    //Devuelve el ady asociado con n2 si n2 es adyacente de n1
    //null en caso contrario
    public Ady devolverAdy(Nodo n1,Nodo n2){
        Vector<Ady> va=tablaGrafo.get(n1.getId());
        Ady a=va.get(0);
        for(int i=1;a.getId()!=n2.getId()&&i<va.size();i++){
            a=va.get(i);
        }
        return a.getId()==n2.getId()?a:null;
    }

    private double getPesoExploracion(Nodo n1,Nodo n2) {
        //Explora los adyacentes recursivamente sumando los pesos
        //hasta encontrar el objetivo
        
        return 0;
    }
}