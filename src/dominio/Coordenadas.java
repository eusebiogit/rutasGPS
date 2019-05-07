package dominio;

public class Coordenadas {

    private double latitudNorte;
    private double latitudSur;

    private double longitudEste;
    private double longitudOeste;

    public Coordenadas(double longitud1,
            double latitud2, double longitud2, double latitud1) {
        //Se realiza una comprobacion para asegurar que los datos se han annadido correctamente
        latitudNorte = latitud1 > latitud2 ? latitud1 : latitud2;
        latitudSur = latitud1 > latitud2 ? latitud2 : latitud1;

        longitudOeste = longitud1 > longitud2 ? longitud2 : longitud1;
        longitudEste = longitud1 > longitud2 ? longitud1 : longitud2;
    }

    public double getLatitudNorte() {
        return latitudNorte;
    }

    public double getLatitudSur() {
        return latitudSur;
    }

    public double[] getLatitud() {
        double latitud[] = {getLatitudNorte(), getLatitudSur()};
        return latitud;
    }

    public double getLongitudOeste() {
        return longitudOeste;
    }

    public double getLongitudEste() {
        return longitudEste;
    }

    public double[] getLongitud() {
        double longitud[] = {getLongitudOeste(), getLongitudEste()};
        return longitud;
    }

    public double[] getCoordenadas() {
        //[izq],[ab],[der],[ar]
        double coordenadas[] = {getLongitudOeste(), getLatitudSur(),
            getLongitudEste(), getLatitudNorte()};
        return coordenadas;
    }

    //Devolver las coordenadas en formato textual 
    public String getCoordenadasString() {
        String res = "";
        double v[] = getCoordenadas();
        for (double i : v) {
            res += i + ",";
        }
        return res.substring(0, res.length() - 1);
    }
}
