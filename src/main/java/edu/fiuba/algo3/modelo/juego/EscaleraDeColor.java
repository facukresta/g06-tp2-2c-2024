package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class EscaleraDeColor extends Juego {
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
        boolean escaleraNormal = true;
        for (int i = 1; i < cartas.size(); i++) {
            int valorActual = cartas.get(i).obtenerNumero();
            int valorAnterior = cartas.get(i - 1).obtenerNumero();
            if (valorActual != valorAnterior + 1) {
                escaleraNormal = false;
                break;
            }
        }
        if (!escaleraNormal) {
            ArrayList<Integer> valores = new ArrayList<>();
            for (Carta carta : cartas) {
                int valor = carta.obtenerNumero();
                if (valor == 1) {
                    valor = 14;
                }
                valores.add(valor);
            }
            Collections.sort(valores);
            for (int i = 1; i < valores.size(); i++) {
                if (valores.get(i) != valores.get(i - 1) + 1) {
                    return false;
                }
            }
        }
        return true;
    }
}

