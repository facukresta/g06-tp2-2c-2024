package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Escalera extends Juego {

    public Puntaje puntuarMano(ArrayList<Carta> cartas) {
        return puntuarCartas(cartas, new Puntaje(30, 4));
    }

    public boolean sosJuego(ArrayList<Carta> cartas) {
        if (cartas.size() != 5) {
            return false;
        }
        Collections.sort(cartas, Comparator.comparingInt(Carta::obtenerNumero));
        for (int i = 1; i < cartas.size(); i++) {
            int valorActual = cartas.get(i).obtenerNumero();
            int valorAnterior = cartas.get(i - 1).obtenerNumero();
            if (valorActual != valorAnterior + 1) {
                return false;
            }
        }
        return true;
    }
}
