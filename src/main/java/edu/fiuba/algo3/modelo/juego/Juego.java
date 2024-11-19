package edu.fiuba.algo3.modelo.juego;

import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.tarot.*;

import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public abstract class Juego {

    protected Tarot modificador = new SinTarot();

    private static final ArrayList<Juego> juegos = new ArrayList<>(List.of(new CartaAlta(),
            new Par(), new DoblePar(), new Trio(), new Escalera(), new Color(),
            new FullHouse(), new Poker(), new EscaleraDeColor(), new EscaleraReal()));

    public static Juego chequearJuego(ArrayList<Carta> cartas) {
        Juego juegoSeleccionado = new SinJuego();
        for (Juego juegoActual : juegos) {
            if (juegoActual.sosJuego(cartas)) {
                Puntaje puntajeJuegoActual = juegoActual.puntuarMano(cartas);
                Puntaje puntajeJuegoSeleccionado = juegoSeleccionado.puntuarMano(cartas);
                if (puntajeJuegoActual.esMayor(puntajeJuegoSeleccionado)) {
                    juegoSeleccionado = juegoActual;
                }
            }
        }
        return juegoSeleccionado;
    }

    public static void aplicarTarot(Tarot tarot) {
        for (Juego juegoActual : juegos) {
            juegoActual.aplicarTarotALaIntancia(tarot);
        }
    }
    private void aplicarTarotALaIntancia(Tarot tarot) {
        if (tarot.sosParaEsteJuego(this)) {
            this.modificador = tarot;
        }
    }

    protected Puntaje puntuarCartas(ArrayList<Carta> cartas, Puntaje puntajeBase) {
        Puntaje puntaje = this.modificador.obtenerPuntaje(puntajeBase);
        for (Carta carta : cartas) {
            puntaje.sumar(carta.obtenerPuntaje());
        }
        return puntaje;
    }

    abstract public boolean sosJuego(ArrayList<Carta> cartas);

    abstract public Puntaje puntuarMano(ArrayList<Carta> cartas);
}
