package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.aleatorio.Ejecucion;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class SumaPuntosDescarte extends Comodin {
    public SumaPuntosDescarte(int cantidad, Ejecucion probabilidad) {
        super(cantidad, probabilidad, new SinJuego());
    }

    public SumaPuntosDescarte(int cantidad) {
        super(cantidad, new Aleatorio(1), new SinJuego());
    }

    @Override
    public void aplicarModificador(Puntaje puntaje, Juego juego) {
        if (this.juego.getClass().equals(juego.getClass()))
            this.probabilidad.ejecuta(() -> puntaje.sumar(new Puntaje((int)valor, 1)));
    }
}