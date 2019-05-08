package dominio;

/**
 * Empaqueta los nodos adyacentes
 * @author ordenador
 */
public class Ady {

    private long id;
    private long idPred;
    private double peso;

    /**
     * 
     * @param id
     * @param peso
     * @param idPredecesor 
     */
    public Ady(long id, double peso, long idPredecesor) {
        this.id = id;
        this.peso = peso;
        this.idPred = idPredecesor;
    }

    /**
     * 
     * @return 
     */
    public long getId() {
        return id;
    }

    /**
     * 
     * @return 
     */
    public double getPeso() {
        return peso;
    }

    /**
     * 
     * @return 
     */
    public long getIdPredecesor() {
        return idPred;
    }

    /**
     * 
     * @param peso 
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * 
     * @param a
     * @return 
     */
    public boolean equals(Ady a) {
        return a.getId() == id;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "('" + idPred + "->" + id + "', " + id + ", " + peso + ")";
    }
}
