package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.aleatorio.Ejecucion;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class SumaPuntosDescarte extends Comodin {
    public SumaPuntosDescarte(int cantidad, Ejecucion probabilidad, String nombre) {
        super(cantidad, probabilidad, new SinJuego(), nombre);
    }

    public SumaPuntosDescarte(int cantidad, String nombre) {
        super(cantidad, new Aleatorio(1), new SinJuego(), nombre);
    }

    @Override
    public void aplicarModificador(Puntaje puntaje, Juego juego) {
        if (this.juego.sosElMismoJuego(juego))
            this.probabilidad.ejecuta(() -> puntaje.sumar(new Puntaje((int)valor, 1)));
    }
}