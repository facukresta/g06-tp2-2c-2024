package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class CartaApretarEventHandler implements EventHandler<ActionEvent> {

    private ArrayList<Carta> seleccionadas;
    private Carta carta;

    public CartaApretarEventHandler(ArrayList<Carta> seleccionadas, Carta carta) {
        this.seleccionadas = seleccionadas;
        this.carta = carta;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (this.seleccionadas.contains(this.carta)) {
            this.seleccionadas.remove(this.carta);
        } else {
            if (this.seleccionadas.size() != 5) {
                this.seleccionadas.add(this.carta);
                // Logica de levantarse
            }
        }

    }
}