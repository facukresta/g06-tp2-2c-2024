package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ComodinApretarEventHandler;
import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.comodin.Modificador;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CambiadorDeComodines {
    private ArrayList<Modificador> modificadores = new ArrayList<Modificador>();
    private ArrayList<Button> botones = new ArrayList<>();
    private Comodinera comodinera;
    private VBox vbox;
    private Pane contenedorPrincipal;
    private CreadorDeBotones creador = new CreadorDeBotones();

    public CambiadorDeComodines(Comodinera comodinera, VBox vbox, Pane contenedorPrincipal) {
        this.comodinera = comodinera;
        this.vbox = vbox;
        this.contenedorPrincipal = contenedorPrincipal;
    }

    public void agregar(Button boton, Modificador modificador) {
        modificadores.add(modificador);
        botones.add(boton);
        boton.setTranslateX(10);
        this.cambiar();
    }

    public void quitar(Button boton, Modificador modificador) {
        modificadores.remove(modificador);
        botones.remove(boton);
        boton.setTranslateX(0);
    }

    private void cambiar() {
        if (this.botones.size() == 2) {
            comodinera.cambiarOrden(this.modificadores.get(0), this.modificadores.get(1));
            ArrayList<Button> aux = (ArrayList<Button>) botones.clone();
            for (Button boton : aux) {
                boton.fire();
            }
            ArrayList<Button> botonesNuevos = creador.crearBotonesComodines(this.comodinera, contenedorPrincipal, this);
            this.vbox.getChildren().setAll(botonesNuevos);
        }
    }
}
