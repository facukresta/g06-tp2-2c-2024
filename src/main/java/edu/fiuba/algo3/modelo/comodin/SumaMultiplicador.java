package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class SumaMultiplicador extends Comodin {
    public SumaMultiplicador(int cantidad) {
        super(cantidad);
    }
    @Override
    public void aplicarModificador(Puntaje puntaje, Juego juego) {
        puntaje.sumar(new Puntaje(0, cantidad));
    }
}
