package dominio;

/**
 * Particularización de la búsquedavoraz para aplicar la heurística A
 *
 *
 * @author ordenador
 */
public class BusquedaA extends BusquedaVoraz {

    protected BusquedaA(Problema problema, boolean poda) {
        super(problema, poda);
    }

}
