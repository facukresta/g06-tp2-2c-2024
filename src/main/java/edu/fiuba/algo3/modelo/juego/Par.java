package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

public class Par extends Juego {

    public Puntaje puntuarMano(ArrayList<Carta> cartas) {
        return puntuarCartas(cartas, new Puntaje(10, 2));
    }

    public boolean sosJuego(ArrayList<Carta> cartas) {
        if (cartas.size() < 2) {
            return false;
        }
        Map<Integer, Integer> frecuenciaValores = new HashMap<>();
        for (Carta carta : cartas) {
            int valor = carta.obtenerNumero();
            frecuenciaValores.put(valor, frecuenciaValores.getOrDefault(valor, 0) + 1);
        }
        int pares = 0;
        for (int frecuencia : frecuenciaValores.values()) {
            if (frecuencia >= 2) {
                pares++;
            }
        }
       return (pares >= 1);
    }
}