package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class Sumador extends Tarot {
    private final int puntos;
    private final double multiplicador;
    public Sumador(int puntos, double multiplicador, Juego juego, String nombre) {
        if (puntos < 0) {
            throw new PuntosNegativosTarotException();
        }
        if (multiplicador <= 0) {
            throw new MultiplicadorInvalidoTarotException();
        }
        this.juego = juego;
        this.puntos = puntos;
        this.multiplicador = multiplicador;
        this.nombre = nombre;
    }

    public Sumador(int puntos, double multiplicador, String nombre) {
        if (puntos < 0) {
            throw new PuntosNegativosTarotException();
        }
        if (multiplicador <= 0) {
            throw new MultiplicadorInvalidoTarotException();
        }
        this.juego = new SinJuego();
        this.puntos = puntos;
        this.multiplicador = multiplicador;
        this.nombre = nombre;
    }

    public Puntaje obtenerPuntaje(Puntaje puntajeBase) {
        Puntaje puntajeNuevo = new Puntaje(this.puntos, this.multiplicador);
        puntajeNuevo.sumar(puntajeBase);
        return puntajeNuevo;
    }
}
