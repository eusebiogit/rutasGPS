package dominio;

import java.util.Vector;

public class Frontera {
    private Vector<NodoBusqueda> frontera;
    
    public Frontera(){
    	frontera = new Vector<NodoBusqueda>();
    }
    public NodoBusqueda elimina() {

        NodoBusqueda r=frontera.firstElement();
        frontera.remove(r);
        return r;
    }
    public void insertar(NodoBusqueda es) {
        frontera.add(es);		
    }
    public void insertarOrdenado(NodoBusqueda es){
        boolean dentro=false;
        if(!frontera.isEmpty()){
            for(int i=0;i<frontera.size()&&!dentro;i++){
                if(es.compareTo(frontera.get(i))<0){
                    frontera.add(i, es);
                    dentro=true;
                }
            }
        }
        if(!dentro){
            insertar(es);
        }
    }
    public void insertarPrimero(NodoBusqueda primero){
        frontera.add(0, primero);
    }
    public boolean esVacia() {
        return frontera.isEmpty();
    }
    public Vector<NodoBusqueda> getFrontera() {
        return frontera;
    }
    public void setFrontera(Vector<NodoBusqueda> frontera) {
        this.frontera = frontera;
    }
}