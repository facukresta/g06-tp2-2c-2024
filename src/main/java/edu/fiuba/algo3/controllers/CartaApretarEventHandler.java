package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class CartaApretarEventHandler implements EventHandler<ActionEvent> {

    private ArrayList<Carta> seleccionadas;
    private Carta carta;
    private Button boton;

    public CartaApretarEventHandler(ArrayList<Carta> seleccionadas, Carta carta, Button cartaBoton) {
        this.seleccionadas = seleccionadas;
        this.carta = carta;
        this.boton = cartaBoton;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (this.seleccionadas.contains(this.carta)) {
            this.seleccionadas.remove(this.carta);
            this.boton.setTranslateY(0);
        } else {
            if (this.seleccionadas.size() != 5) {
                this.seleccionadas.add(this.carta);
                this.boton.setTranslateY(-30);
            }
        }
    }
}