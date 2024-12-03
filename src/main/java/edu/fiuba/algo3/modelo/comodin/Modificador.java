package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.tienda.Comprable;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public interface Modificador extends Comprable {
    void aplicarModificador(Puntaje puntaje, Juego juego);

    @Override
    String obtenerNombre();

    @Override
    String obtenerRutaBase();

    @Override
    void comprar(/*Monto monto*/);
}
