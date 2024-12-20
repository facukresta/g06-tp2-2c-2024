package edu.fiuba.algo3.modelo.naipes.carta;

import edu.fiuba.algo3.modelo.tienda.Comprable;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.puntaje.Puntuable;
import edu.fiuba.algo3.modelo.tarot.Tarot;

public interface Carta extends Comprable, Puntuable {
    void aplicarModificador(Tarot tarot);

    Puntaje obtenerPuntaje();

    boolean esDelMismoPalo(Carta carta);

    int obtenerNumero();

    boolean sos(Carta carta);

    String obtenerNombre();

    String obtenerRutaBase();

    void comprar();
}
