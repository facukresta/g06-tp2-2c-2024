package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public abstract class Tarot {
    protected Juego juego;
    public abstract Puntaje obtenerPuntaje(Puntaje puntajeBase);

    public boolean sosParaEsteJuego(Juego juego) {
        return this.juego.getClass().equals(juego.getClass());
    }
}
