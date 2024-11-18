package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public abstract class Comodin  implements Modificador {
    protected final double valor;
    private final Aleatorio probabilidad;
    protected final Juego juego;
    public Comodin(double valor, Aleatorio probabilidad, Juego juego) {
        if (valor <= 0) {
            throw new CantidadComodinInvalida();
        }
        this.juego = juego;
        this.probabilidad = probabilidad;
        this.valor = valor;
    }

    protected boolean puedeAplicarse() {
        return probabilidad.sucede();
    }

    public abstract void aplicarModificador(Puntaje puntaje, Juego juego);
}
