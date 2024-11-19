package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;

public class CartaAlta extends Juego{
    public Puntaje puntuarMano(ArrayList<Carta> cartas) {
        return puntuarCartas(cartas, new Puntaje(5, 1));
    }

    public boolean sosJuego(ArrayList<Carta> cartas) {
        return !cartas.isEmpty();
    }
}
