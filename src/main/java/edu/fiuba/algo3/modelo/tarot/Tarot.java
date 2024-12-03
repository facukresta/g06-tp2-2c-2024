package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.tienda.Comprable;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.puntaje.Puntuador;

public abstract class Tarot implements Comprable, Puntuador {
    protected Juego juego;
    protected String nombre;

    public abstract Puntaje obtenerPuntaje(Puntaje puntajeBase);

    public boolean sosParaEsteJuego(Juego juego) {
        return this.juego.getClass().equals(juego.getClass());
    }

    public void comprar() {
        // A implementar si se agrega dinero
    }

    public String obtenerNombre() {
        return this.nombre;
    }

    public String obtenerRutaBase() {
        return "tarots/";
    }

    public boolean sosTarotDeJuego() {
        return !this.juego.getClass().equals(SinJuego.class);
    }
}
