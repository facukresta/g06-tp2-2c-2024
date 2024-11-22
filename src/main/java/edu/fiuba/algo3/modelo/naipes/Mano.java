package edu.fiuba.algo3.modelo.naipes;

import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.Tarot;

import java.util.ArrayList;

public interface Mano {
    void agregarCarta(Carta carta);

    void agregarCartas(ArrayList<Carta> cartas);

    void quitarCarta(Carta carta);

    void quitarCartas(ArrayList<Carta> cartas);

    Puntaje descartarMano(ArrayList<Carta> cartasSeleccionadas, Juego juego, Comodinera comodinera);

    Puntaje jugarMano(ArrayList<Carta> cartasSeleccionadas, Juego juego, Comodinera comodinera);

    int obtenerCantidadDeCartas();

    Carta obtenerCarta(Carta cartaBuscada);

    void modificarCarta(Carta carta, Tarot tarot);

    void modificarJuego(Tarot tarot);
}
