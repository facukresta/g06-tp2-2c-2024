package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.comodin.Modificador;
import edu.fiuba.algo3.vistas.CambiadorDeComodines;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ComodinApretarEventHandler implements EventHandler<ActionEvent> {

    private Modificador modificador;
    private Button boton;
    private boolean apretado = false;
    private final CambiadorDeComodines cambiador;

    public ComodinApretarEventHandler(Modificador modificador, Button modificadorBoton, CambiadorDeComodines cambiador) {
        this.modificador = modificador;
        this.boton = modificadorBoton;
        this.cambiador = cambiador;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!apretado) {
            apretado = true;
            cambiador.agregar(this.boton, this.modificador);
        } else {
            apretado = false;
            cambiador.quitar(this.boton, this.modificador);
        }
    }
}

