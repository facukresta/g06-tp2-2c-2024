package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.naipes.Seleccionadas;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;

public class CartaApretarEventHandler implements EventHandler<ActionEvent> {

    private Seleccionadas seleccionadas;
    private Carta carta;
    private Button boton;
    private AudioClip sonidoError = new AudioClip(getClass().getResource("/sonido/error.mp3").toExternalForm());

    public CartaApretarEventHandler(Seleccionadas seleccionadas, Carta carta, Button cartaBoton) {
        this.seleccionadas = seleccionadas;
        this.carta = carta;
        this.boton = cartaBoton;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (this.seleccionadas.seleccionarCarta(this.carta)) {
            this.boton.setTranslateY(-30);
        } else {
            if (this.seleccionadas.obtenerCartasSeleccionadas().size() == 5) {
                this.sonidoError.play();
            }
            this.boton.setTranslateY(0);
        }
    }
}