package edu.fiuba.algo3.modelo.juego;

import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.tarot.SinTarot;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public abstract class Juego {
    protected Tarot modificador = new SinTarot();

    public static Juego chequearJuego(ArrayList<Carta> cartas) {
        ArrayList<Juego> juegos = new ArrayList<>(List.of(new CartaAlta(), new Par(), new DoblePar(),
                new Trio(), new Escalera(), new Color(), new FullHouse(),
                new Poker(), new EscaleraDeColor(), new EscaleraReal()));
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

    public void aplicarTarot(Tarot tarot) {
        this.modificador = tarot;
    }

    abstract public boolean sosJuego(ArrayList<Carta> cartas);

    abstract public Puntaje puntuarMano(ArrayList<Carta> cartas);
}
