package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.Comprable;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public interface Modificador extends Comprable {
    void aplicarModificador(Puntaje puntaje, Juego juego);

    String obtenerNombre();

    String obtenerRutaBase();
}
