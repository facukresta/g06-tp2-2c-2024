package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ComodinApretarEventHandler;
import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.comodin.Modificador;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class CreadorDeBotones {
    private CreadorVisual creadorVisual = new CreadorVisual();

    public Button crearBoton(String nombre, EventHandler<ActionEvent> handler, Pane contenedorPrincipal, double ancho, double altura) {
        Button boton = new Button();
        boton.getStyleClass().clear();
        boton.setGraphic(this.creadorVisual.crearImageView(nombre, contenedorPrincipal, ancho, altura));
        boton.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        boton.setOnAction(handler);
        boton.setOnMouseEntered(e -> {
            ScaleTransition hoverScale = new ScaleTransition(Duration.millis(200), boton);
            hoverScale.setToX(1.1);
            hoverScale.setToY(1.1);
            hoverScale.play();
        });
        boton.setOnMouseExited(e -> {
            ScaleTransition hoverScale = new ScaleTransition(Duration.millis(200), boton);
            hoverScale.setToX(1.0);
            hoverScale.setToY(1.0);
            hoverScale.play();
        });
        return boton;
    }
}
