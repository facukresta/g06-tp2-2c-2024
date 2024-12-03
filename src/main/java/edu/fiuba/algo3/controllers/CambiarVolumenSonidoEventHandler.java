package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.CreadorDeBotones;
import edu.fiuba.algo3.vistas.CreadorVisual;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;

public class CambiarVolumenSonidoEventHandler implements EventHandler<ActionEvent> {
    private boolean apagado = false;
    private CreadorVisual creador = new CreadorVisual();
    MediaPlayer musica;
    private Pane contenedorPrincipal;
    private Button boton;
    private double volumen;


    public CambiarVolumenSonidoEventHandler(MediaPlayer musica, Pane contenedorPrincipal, Button boton, double volumen) {
        this.musica = musica;
        this.contenedorPrincipal = contenedorPrincipal;
        this.boton = boton;
        this.volumen = volumen;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!apagado) {
            boton.setGraphic(creador.crearImageView("sinVolumen",contenedorPrincipal,0.05,0.05));
        } else {
            boton.setGraphic(creador.crearImageView("conVolumen",contenedorPrincipal,0.05,0.05));
        }
        apagado = !apagado;
        this.musica.setVolume(Math.abs(volumen-this.musica.getVolume()));
    }
}
