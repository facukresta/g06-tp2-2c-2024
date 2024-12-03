package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class FullHouse extends Juego{
    public Puntaje obtenerPuntaje() {
        return this.puntuarJuego(new Puntaje(40, 4));
    }

    public boolean sosJuego(ArrayList<Carta> cartas) {
        if (cartas.size() != 5) {
            return false;
        }
        Map<Integer, Integer> frecuenciaValores = new HashMap<>();
        for (Carta carta : cartas) {
            int valor = carta.obtenerNumero();
            frecuenciaValores.put(valor, frecuenciaValores.getOrDefault(valor, 0) + 1);
        }
        boolean tieneTrio = false;
        boolean tienePar = false;
        for (int frecuencia : frecuenciaValores.values()) {
            if (frecuencia == 3) {
                tieneTrio = true;
            } else if (frecuencia == 2) {
                tienePar = true;
            }
        }
        return tieneTrio && tienePar;
    }
}
