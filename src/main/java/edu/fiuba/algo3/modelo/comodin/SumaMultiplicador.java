package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.aleatorio.Ejecucion;
import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.juego.Juego;

public class SumaMultiplicador extends Comodin {
    public SumaMultiplicador(double cantidad, Juego juego, Ejecucion probabilidad, String nombre) {
        super(cantidad, probabilidad, juego, nombre);
    }

    public SumaMultiplicador(double cantidad, Ejecucion probabilidad, String nombre) {
        super(cantidad, probabilidad, new SinJuego(), nombre);
    }

    public SumaMultiplicador(double cantidad, Juego juego, String nombre) {
        super(cantidad, new Aleatorio(1), juego, nombre);
    }

    public SumaMultiplicador(double cantidad, String nombre) {
        super(cantidad, new Aleatorio(1), new SinJuego(), nombre);
    }

    @Override
    public void aplicarModificador(Puntaje puntaje, Juego juego) {
        if ((this.juego.sosElMismoJuego(new SinJuego()) ^ this.juego.sosElMismoJuego(juego)))
            this.probabilidad.ejecuta(() -> puntaje.sumar(new Puntaje(0, valor)));
    }
}


