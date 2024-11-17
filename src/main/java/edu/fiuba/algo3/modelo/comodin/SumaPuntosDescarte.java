package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class SumaPuntosDescarte extends Comodin {
    public SumaPuntosDescarte(int cantidad, Aleatorio probabilidad) {
        super(cantidad, probabilidad, new SinJuego());
    }

    public SumaPuntosDescarte(int cantidad) {
        super(cantidad, new Aleatorio(1), new SinJuego());
    }

    @Override
    public void aplicarModificador(Puntaje puntaje, Juego juego) {
        if (this.juego.getClass().equals(juego.getClass()) && puedeAplicarse() )
            puntaje.sumar(new Puntaje((int)cantidad, 1));
    }
}