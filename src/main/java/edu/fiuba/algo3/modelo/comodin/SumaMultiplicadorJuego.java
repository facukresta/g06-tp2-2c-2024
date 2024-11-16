package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.juego.Juego;

import java.util.ArrayList;

public class SumaMultiplicadorJuego extends Comodin {
    private Juego juego;
    public SumaMultiplicadorJuego(int cantidad, Juego juego) {
        super(cantidad);
        this.juego = juego;
    }

    @Override
    public void aplicarModificador(Puntaje puntaje, Juego juego) {
        if (this.juego.getClass().equals(juego.getClass()))
            puntaje.sumar(new Puntaje(0, cantidad));
    }
}


