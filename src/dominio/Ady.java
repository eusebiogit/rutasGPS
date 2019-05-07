package dominio;

/**
 * Empaqueta 
 * @author ordenador
 */
public class Ady {

    private long id;
    private long idPred;
    private double peso;

    public Ady(long id, double peso, long idPredecesor) {
        this.id = id;
        this.peso = peso;
        this.idPred = idPredecesor;
    }

    public long getId() {
        return id;
    }

    public double getPeso() {
        return peso;
    }

    public long getIdPredecesor() {
        return idPred;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean equals(Ady a) {
        return a.getId() == id;
    }

    public String toString() {
        return "('" + idPred + "->" + id + "', " + id + ", " + peso + ")";
    }
}
