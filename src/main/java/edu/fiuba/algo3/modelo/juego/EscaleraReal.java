package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EscaleraReal extends Juego{
    public Puntaje obtenerPuntaje() {
        return this.puntuarJuego(new Puntaje(100, 8));
    }

    public boolean sosJuego(ArrayList<Carta> cartas) {
        if (cartas.size() != 5) {
            return false;
        }
        Carta cartaPalo = cartas.get(0);
        for (Carta carta : cartas) {
            if (!carta.esDelMismoPalo(cartaPalo)) {
                return false;
            }
        }
        Collections.sort(cartas, Comparator.comparingInt(Carta::obtenerNumero));
        int[] secuenciaEsperada = {1, 10, 11, 12, 13};
        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i).obtenerNumero() != secuenciaEsperada[i]) {
                return false;
            }
        }
        return true;
    }


}
