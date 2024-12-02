package edu.fiuba.algo3.modelo.naipes;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;

import java.util.ArrayList;

public interface Seleccionadas {
    boolean seleccionarCarta(Carta carta);

    Juego obtenerJuego();

    ArrayList<Carta> obtenerCartasSeleccionadas();

    boolean estaVacio();

    void vaciarCartas();
}
