package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;

public class SinJuego extends Juego{
    public Puntaje obtenerPuntaje() {
        return this.puntuarJuego(new Puntaje(0, 1));
    }

    public boolean sosJuego(ArrayList<Carta> cartas) {
        return cartas.isEmpty();
    }
}
