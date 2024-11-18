package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class SumaMultiplicadorDescarte extends Comodin {

    public SumaMultiplicadorDescarte(double cantidad, Aleatorio probabilidad) {
        super(cantidad, probabilidad, new SinJuego());
    }

    public SumaMultiplicadorDescarte(double cantidad) {
        super(cantidad, new Aleatorio(1), new SinJuego());
    }

    @Override
    public void aplicarModificador(Puntaje puntaje, Juego juego) {
        if (this.juego.getClass().equals(juego.getClass()) && this.puedeAplicarse())
            puntaje.sumar(new Puntaje(0, cantidad));
    }
}