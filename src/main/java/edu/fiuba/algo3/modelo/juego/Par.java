package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;

public class Par extends Juego{
    public Puntaje puntuarMano(ArrayList<Carta> cartas) {
        return new Puntaje(0,1);
    }

    public boolean sosJuego(ArrayList<Carta> cartas) {
        return false;
    }
}
