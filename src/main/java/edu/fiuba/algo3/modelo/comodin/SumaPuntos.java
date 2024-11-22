package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.aleatorio.Ejecucion;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import javax.swing.*;

public class SumaPuntos extends Comodin {
    public SumaPuntos(double cantidad, Juego juego, Ejecucion probabilidad) {
        super(cantidad, probabilidad, juego);
    }

    public SumaPuntos(double cantidad, Ejecucion probabilidad) {
        super(cantidad, probabilidad, new SinJuego());
    }

    public SumaPuntos(double cantidad, Juego juego) {
        super(cantidad, new Aleatorio(1), juego);
    }

    public SumaPuntos(double cantidad) {
        super(cantidad, new Aleatorio(1), new SinJuego());
    }

    @Override
    public void aplicarModificador(Puntaje puntaje, Juego juego) {
        if ((this.juego.sosElMismoJuego(new SinJuego()) ^ this.juego.sosElMismoJuego(juego)))
            this.probabilidad.ejecuta(() -> puntaje.sumar(new Puntaje((int)valor, 1)));
    }
}
