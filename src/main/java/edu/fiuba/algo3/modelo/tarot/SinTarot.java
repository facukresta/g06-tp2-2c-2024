package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class SinTarot extends Tarot {
    public SinTarot() {
        this.nombre = "tarotVacio";
    }
    public SinTarot(Juego juego) {
        this.juego = juego;
        this.nombre = "tarotVacio";
    }
    public Puntaje obtenerPuntaje(Puntaje puntajeBase) {
        return puntajeBase;
    }

}
