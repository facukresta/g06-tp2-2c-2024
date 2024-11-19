package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;

public class ComodinCombinacion implements Modificador {
    private final ArrayList<Comodin> comodines;
    public ComodinCombinacion(ArrayList<Comodin> comodines) {
        this.comodines = comodines;
    }

    public void aplicarModificador(Puntaje puntaje, Juego juego) {
        for (Comodin comodin : comodines) {
            comodin.aplicarModificador(puntaje, juego);
        }
    }
}
