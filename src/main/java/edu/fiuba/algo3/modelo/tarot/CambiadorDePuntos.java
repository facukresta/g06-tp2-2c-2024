package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class CambiadorDePuntos extends Tarot{
    private final int puntos;
    public CambiadorDePuntos(int puntosNuevos) {
        if (puntosNuevos < 0) {
            throw new PuntosNegativosTarotException();
        }
        this.puntos = puntosNuevos;
        this.juego = new SinJuego();
    }
    public CambiadorDePuntos(int puntosNuevos, Juego juego) {
        if (puntosNuevos <= 0) {
            throw new MultiplicadorInvalidoTarotException();
        }
        this.puntos = puntosNuevos;
        this.juego = juego;
    }
    public Puntaje obtenerPuntaje(Puntaje puntajeBase) {
        return new Puntaje(puntos, puntajeBase.obtenerMultiplicador());
    }
}
