package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class CambiadorDePuntos extends Tarot{
    private final int puntos;

    public CambiadorDePuntos(int puntosNuevos, String nombre) {
        if (puntosNuevos < 0) {
            throw new PuntosNegativosTarotException();
        }
        this.puntos = puntosNuevos;
        this.juego = new SinJuego();
        this.nombre = nombre;
    }

    public CambiadorDePuntos(int puntosNuevos, Juego juego, String nombre) {
        if (puntosNuevos <= 0) {
            throw new MultiplicadorInvalidoTarotException();
        }
        this.puntos = puntosNuevos;
        this.juego = juego;
        this.nombre = nombre;
    }

    public Puntaje obtenerPuntaje(Puntaje puntajeBase) {
        return new Puntaje(puntos, puntajeBase.obtenerMultiplicador());
    }
}
