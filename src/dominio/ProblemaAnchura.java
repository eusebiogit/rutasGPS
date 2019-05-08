package dominio;

import java.util.Vector;

/**
 * El problema en anchura utiliza las características del problema general
 * @author ordenador
 */
public class ProblemaAnchura extends Problema{

    public ProblemaAnchura(EspacioDeEstados espacio, long inicial, Vector<Long> destinos) {
        super(espacio, inicial, destinos);
    }
}