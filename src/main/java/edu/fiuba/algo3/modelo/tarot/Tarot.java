package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.Comprable;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public abstract class Tarot implements Comprable {
    protected Juego juego;
    public abstract Puntaje obtenerPuntaje(Puntaje puntajeBase);

    public boolean sosParaEsteJuego(Juego juego) {
        return this.juego.getClass().equals(juego.getClass());
    }

    public void comprar() {
        // Recibe Monto y le manda a otra clase ese monto total y el precio de la carta
    }
}
