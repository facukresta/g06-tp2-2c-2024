package edu.fiuba.algo3.modelo.juego;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class Color extends Juego{
    public Puntaje obtenerPuntaje() {
        return puntuarJuego(new Puntaje(35, 4));
    }

    public boolean sosJuego(ArrayList<Carta> cartas) {
        if (cartas.size() != 5) {
            return false;
        }
        Carta cartaPalo = cartas.get(0);
        for (Carta carta : cartas.subList(1, cartas.size())) {
            if (!carta.esDelMismoPalo(cartaPalo)) {
                return false;
            }
        }
        return true;
    }
}
