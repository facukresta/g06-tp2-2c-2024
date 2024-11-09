package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Poker extends Juego{
    public Puntaje puntuarMano(ArrayList<Carta> cartas) {
        Puntaje puntaje = new Puntaje(60, 7); // Puntaje específico para Poker
        for (Carta carta : cartas) {
            puntaje.sumarPuntos(carta.obtenerPuntaje());
        }
        return puntaje;
    }

    public boolean sosJuego(ArrayList<Carta> cartas) {
        if (cartas.size() < 4) {
            return false;
        }

        Map<Integer, Integer> frecuenciaValores = new HashMap<>();

        for (Carta carta : cartas) {
            int valor = carta.obtenerValor();
            frecuenciaValores.put(valor, frecuenciaValores.getOrDefault(valor, 0) + 1);
        }

        boolean tienePoker = false;

        for (int frecuencia : frecuenciaValores.values()) {
            if (frecuencia == 4) {
                tienePoker = true;
            }
        }

        return tienePoker;
    }
}