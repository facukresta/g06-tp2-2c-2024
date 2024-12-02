package edu.fiuba.algo3.modelo.naipes;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;

import java.util.ArrayList;

public interface Mazo {
    void agregarCarta(Carta carta);

    void agregarCartas(ArrayList<Carta> cartas);

    ArrayList<Carta> repartirCartas(int cantidad);

    void mezclar();

    void vaciarMazo();
}
