package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public abstract class Comodin {
    protected int cantidad;
    public Comodin(int cantidad) {
        if (cantidad <= 0) {
            throw new CantidadComodinInvalida();
        }
        this.cantidad = cantidad;;
    }

    public abstract void aplicarModificador(Puntaje puntaje, Juego juego);
}
