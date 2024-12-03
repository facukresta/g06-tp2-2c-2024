package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RondaActual implements Observador {
    private HBox contenedor;
    private CreadorDeEtiqueta creadorDeEtiqueta = new CreadorDeEtiqueta();
    private CreadorVisual creadorVisual = new CreadorVisual();

    public RondaActual(HBox contenedor, Pane contenedorPrincipal) {
        this.contenedor = contenedor;
        contenedor.setSpacing(5);
        if (contenedor.getChildren().size() == 0) {
            ImageView stringRondaActual = creadorVisual.crearImageView("rondaActual",contenedorPrincipal, 0.08, 0.04);
            HBox.setMargin(stringRondaActual, new Insets(5,5,0,0));
            contenedor.getChildren().add(stringRondaActual);
        }
        if (contenedor.getChildren().size() == 1) {
            Label rondaActualMostrada = this.creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(0));
            rondaActualMostrada.setTextFill(Color.WHITE);
            rondaActualMostrada.setFont(Font.font(22));
            HBox.setMargin(rondaActualMostrada, new Insets(5,5,0,0));
            contenedor.getChildren().add(rondaActualMostrada);
        }
    }

    @Override
    public void actualizar(Object notificador) {
        Ronda ronda = (Ronda) notificador;
        Label rondaActualMostrada = creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(ronda.obtenerNumeroRonda()));
        rondaActualMostrada.setTextFill(Color.WHITE);
        rondaActualMostrada.setFont(Font.font(22));
        this.contenedor.getChildren().set(1, rondaActualMostrada);
        HBox.setMargin(rondaActualMostrada, new Insets(5,5,0,0));
    }
}