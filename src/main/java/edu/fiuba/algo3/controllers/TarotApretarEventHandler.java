package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.tarot.CambiadorDePuntos;
import edu.fiuba.algo3.modelo.tarot.SinTarot;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class TarotApretarEventHandler implements EventHandler<ActionEvent> {

    private ArrayList<Carta> seleccionadas;
    private Tarot tarot;
    private Button boton;
    private VBox tarots;
    private Pane contenedor;

    public TarotApretarEventHandler(ArrayList<Carta> seleccionadas, Tarot tarot, Button tarotBoton, VBox tarots, Pane contenedorPrincipal) {
        this.seleccionadas = seleccionadas;
        this.tarot = tarot;
        this.boton = tarotBoton;
        this.tarots = tarots;
        this.contenedor = contenedorPrincipal;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (this.seleccionadas.size() == 1) {
            Carta cartaAModificar = this.seleccionadas.get(0);
            cartaAModificar.aplicarModificador(this.tarot);
            tarots.getChildren().remove(this.boton);
            Button tarotBoton = this.crearBoton("ComodinVacio", Color.DARKBLUE, (null), this.contenedor, 0.05, 0.13);
            tarotBoton.setOnAction(new TarotApretarEventHandler(this.seleccionadas, new SinTarot(), tarotBoton, tarots, contenedor));
            tarots.getChildren().add(tarotBoton);
            VBox.setMargin(tarotBoton, new Insets(10,0,0,20));
        }
    }

    private Button crearBoton(String nombre, Color color, EventHandler<ActionEvent> handler, Pane contenedorPrincipal, double ancho, double altura) {
        Button boton = new Button();
        Image playImage = new Image("/"+nombre+".png");
        ImageView imageView = new ImageView(playImage);
        // Escalar el ImageView
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(contenedorPrincipal.widthProperty().multiply(ancho));
        imageView.fitHeightProperty().bind(contenedorPrincipal.heightProperty().multiply(altura));

        boton.setGraphic(imageView);
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