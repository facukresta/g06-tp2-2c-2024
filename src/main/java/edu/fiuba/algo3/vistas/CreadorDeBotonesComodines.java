package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ComodinApretarEventHandler;
import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.comodin.Modificador;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class CreadorDeBotonesComodines {
    public ArrayList<Button> crearBotonesComodines(Comodinera comodinera, Pane contenedorPrincipal, CambiadorDeComodines cambiadorDeComodines) {
        ArrayList<Button> botones = new ArrayList<>();
        ArrayList<Modificador> modificadores = comodinera.obtenerComodines();
        for (Modificador comodin : modificadores) {
            Button comodinBoton = this.crearBoton(comodin.obtenerNombre(), Color.DARKBLUE, (null), contenedorPrincipal, 0.05, 0.13);
            comodinBoton.setOnAction(new ComodinApretarEventHandler(comodin, comodinBoton, cambiadorDeComodines));
            VBox.setMargin(comodinBoton, new Insets(10,0,0,20));
            botones.add(comodinBoton);
        }
        return botones;
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
