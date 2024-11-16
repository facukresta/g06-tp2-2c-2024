package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class SumaPuntosDescarte extends Comodin {
    private Juego juego;
    public SumaPuntosDescarte(int cantidad) {
        super(cantidad);
        this.juego = new SinJuego();
    }

    @Override
    public void aplicarModificador(Puntaje puntaje, Juego juego) {
        if (this.juego.getClass().equals(juego.getClass()))
            puntaje.sumar(new Puntaje(cantidad, 1));
    }
}
