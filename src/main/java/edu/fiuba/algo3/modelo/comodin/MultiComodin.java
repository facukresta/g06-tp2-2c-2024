package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;

public class MultiComodin implements Modificador {
    private final ArrayList<Modificador> comodines = new ArrayList<>();

    public void componerComodin(Modificador comodin) {
        comodines.add(comodin);
    }

    @Override
    public void aplicarModificador(Puntaje puntaje, Juego juego) {
        for (Modificador comodin : comodines) {
            comodin.aplicarModificador(puntaje, juego);
        }
    }
}
