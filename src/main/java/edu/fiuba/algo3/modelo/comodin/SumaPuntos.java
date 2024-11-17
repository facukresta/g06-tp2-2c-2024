package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class SumaPuntos extends Comodin {
    public SumaPuntos(int cantidad, Juego juego, Aleatorio probabilidad) {
        super(cantidad, probabilidad, juego);
    }

    public SumaPuntos(int cantidad, Aleatorio probabilidad) {
        super(cantidad, probabilidad, new SinJuego());
    }

    public SumaPuntos(int cantidad, Juego juego) {
        super(cantidad, new Aleatorio(1), juego);
    }

    public SumaPuntos(int cantidad) {
        super(cantidad, new Aleatorio(1), new SinJuego());
    }

    @Override
    public void aplicarModificador(Puntaje puntaje, Juego juego) {
        if ((this.juego.getClass() == SinJuego.class || this.juego.getClass().equals(juego.getClass())) && this.puedeAplicarse())
            puntaje.sumar(new Puntaje(cantidad, 1));
    }
}
