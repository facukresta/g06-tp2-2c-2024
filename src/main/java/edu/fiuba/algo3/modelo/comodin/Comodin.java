package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public abstract class Comodin  implements Modificador {
    protected final double cantidad;
    private final Aleatorio probabilidad;
    protected final Juego juego;
    public Comodin(double cantidad, Aleatorio probabilidad, Juego juego) {
        if (cantidad <= 0) {
            throw new CantidadComodinInvalida();
        }
        this.juego = juego;
        this.probabilidad = probabilidad;
        this.cantidad = cantidad;
    }

    protected boolean puedeAplicarse() {
        return probabilidad.sucede();
    }

    public abstract void aplicarModificador(Puntaje puntaje, Juego juego);
}
