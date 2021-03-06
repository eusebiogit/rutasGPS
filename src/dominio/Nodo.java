package dominio;

/**
 * Abstrae la información de los node para formar un nodo del grafo
 *
 * @author ordenador
 */
public class Nodo {

    private long id;
    private double longitud;
    private double latitud;

    /**
     *
     * @param id
     * @param longitud
     * @param latitud
     */
    public Nodo(long id, double longitud, double latitud) {
        this.id = id;
        this.longitud = longitud;
        this.latitud = latitud;
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
    public double getLongitud() {
        return longitud;
    }

    /**
     *
     * @return
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     *
     * @param n
     * @return
     */
    public double getDistancia(Nodo n) {

        double long1 = getLongitud();

        double long2 = n.getLongitud();

        double lat1 = getLatitud();
        double lat2 = n.getLatitud();

        Mercator me = new Mercator();
        double[] punto1 = new double[2];
        double[] punto2 = new double[2];

        punto1 = me.merc(long1, lat1);
        punto2 = me.merc(long2, lat2);

        return Math.sqrt(((punto1[0] - punto2[0]) * (punto1[0] - punto2[0]))
                + (punto1[1] - punto2[1]) * (punto1[1] - punto2[1]));
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.longitud) ^ (Double.doubleToLongBits(this.longitud) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.latitud) ^ (Double.doubleToLongBits(this.latitud) >>> 32));
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nodo other = (Nodo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return "(id : " + id + ", lat : " + latitud + ", long : " + longitud + ")";
    }
}
