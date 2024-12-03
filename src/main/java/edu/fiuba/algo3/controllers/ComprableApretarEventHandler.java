package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.tienda.Comprable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class ComprableApretarEventHandler implements EventHandler<ActionEvent> {
    private Comprable productoAComprar;
    private ArrayList<Comprable> productoSeleccionado;
    private ArrayList<Button> botonSeleccionado;
    Button botonComprable;

    public ComprableApretarEventHandler(Comprable productoAComprar, ArrayList<Comprable> productoSeleccionado, ArrayList<Button> botonSeleccionado, Button botonComprable) {
        this.productoAComprar = productoAComprar;
        this.productoSeleccionado = productoSeleccionado;
        this.botonComprable = botonComprable;
        this.botonSeleccionado = botonSeleccionado;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (productoSeleccionado.contains(productoAComprar)) {
            this.botonComprable.setTranslateY(0);
            productoSeleccionado.remove(productoAComprar);
            botonSeleccionado.remove(botonComprable);
        } else if (productoSeleccionado.isEmpty()) {
            this.botonComprable.setTranslateY(-20);
            productoSeleccionado.add(productoAComprar);
            botonSeleccionado.add(botonComprable);
        } else {
            this.botonSeleccionado.get(0).fire();
            this.botonComprable.fire();
        }
    }
}
