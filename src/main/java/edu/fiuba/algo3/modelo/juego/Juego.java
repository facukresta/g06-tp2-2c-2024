package edu.fiuba.algo3.modelo.juego;

import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.tarot.*;

import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public abstract class Juego {
    private static ArrayList<Juego> juegos = new ArrayList<>(List.of(new CartaAlta(),
            new Par(), new DoblePar(), new Trio(), new Escalera(), new Color(),
            new FullHouse(), new Poker(), new EscaleraDeColor(), new EscaleraReal()));
    protected ArrayList<Carta> cartas = new ArrayList<>();
    protected Tarot modificador = new SinTarot();

    private void aplicarTarotALaIntancia(Tarot tarot) {
        if (tarot.sosParaEsteJuego(this)) {
            this.modificador = tarot;
        }
    }

    public static Juego chequearJuego(ArrayList<Carta> cartas) {
        Juego juegoSeleccionado = new SinJuego();

        for (Juego juegoActual : juegos) {
            juegoActual.colocarCartas(cartas);
            juegoSeleccionado = juegoActual.obtenerMayor(juegoSeleccionado);
        }
        return juegoSeleccionado;
    }

    public static void aplicarTarot(Tarot tarot) {
        for (Juego juegoActual : juegos) {
            juegoActual.aplicarTarotALaIntancia(tarot);
        }
    }

    protected void colocarCartas(ArrayList<Carta> cartas){
        this.cartas = cartas;
    }

    protected Juego obtenerMayor(Juego juegoSeleccionado) {
        if (this.puntuarMano().esMayor(juegoSeleccionado.puntuarMano())) {
            return this;
        }
        return juegoSeleccionado;
    }

    protected Puntaje puntuarCartas(Puntaje puntajeBase) {
        for (Carta carta : this.cartas) {
            puntajeBase.sumar(carta.obtenerPuntaje());
        }
        return puntajeBase;
    }

    protected Puntaje puntuarJuego(Puntaje puntajeBase) {
        if (this.sosJuego(this.cartas)) {
            return puntuarCartas(this.modificador.obtenerPuntaje(puntajeBase));
        }
        return new Puntaje(0, 1);
    }

    abstract public Puntaje puntuarMano();

    public boolean sosElMismoJuego(Juego juego) {
        return this.getClass().equals(juego.getClass());
    }

    abstract protected boolean sosJuego(ArrayList<Carta> cartas);
}

