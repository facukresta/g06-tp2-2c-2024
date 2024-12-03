package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.aleatorio.Ejecucion;
import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.juego.Juego;

public class SinComodin extends Comodin {

    public SinComodin(){
        super (1, new Aleatorio(1), new SinJuego(), "comodinVacio");
    }

    @Override
    public void aplicarModificador(Puntaje puntaje, Juego juego) {
        this.probabilidad.ejecuta(() -> puntaje.sumar(new Puntaje(0, valor)));
    }
}


