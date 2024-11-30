package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class CambiadorDeMultiplicador extends Tarot {
    private final double multiplicador;
    public CambiadorDeMultiplicador(double multiplicadorNuevo, String nombre) {
        if (multiplicadorNuevo <= 0) {
            throw new MultiplicadorInvalidoTarotException();
        }
        this.multiplicador = multiplicadorNuevo;
        this.juego = new SinJuego();
        this.nombre = nombre;
    }
    public CambiadorDeMultiplicador(double multiplicadorNuevo, Juego juego, String nombre) {
        if (multiplicadorNuevo <= 0) {
            throw new MultiplicadorInvalidoTarotException();
        }
        this.multiplicador = multiplicadorNuevo;
        this.juego = juego;
        this.nombre = nombre;
    }
    public Puntaje obtenerPuntaje(Puntaje puntajeBase) {
        return new Puntaje(puntajeBase.obtenerPuntos(), multiplicador);
    }
}
