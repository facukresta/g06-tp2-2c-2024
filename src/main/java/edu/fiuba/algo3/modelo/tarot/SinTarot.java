package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class SinTarot extends Tarot {
    public SinTarot() {
        this.nombre = "comodinVacio";
    }
    public SinTarot(Juego juego) {
        this.juego = juego;
        this.nombre = "comodinVacio";
    }
    public Puntaje obtenerPuntaje(Puntaje puntajeBase) {
        return puntajeBase;
    }

}
