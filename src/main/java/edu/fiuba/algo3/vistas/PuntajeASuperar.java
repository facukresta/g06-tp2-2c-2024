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

public class PuntajeASuperar implements Observador {
    private HBox contenedor;
    private CreadorDeEtiqueta creadorDeEtiqueta = new CreadorDeEtiqueta();
    private CreadorVisual creadorVisual = new CreadorVisual();

    public PuntajeASuperar(HBox contenedor, Pane contenedorPrincipal) {
        this.contenedor = contenedor;
        contenedor.setSpacing(5);
        if (contenedor.getChildren().size() == 0) {
            ImageView stringPuntajeASuperar = creadorVisual.crearImageView("puntajeASuperar",contenedorPrincipal, 0.08, 0.04);
            HBox.setMargin(stringPuntajeASuperar, new Insets(5,5,0,0));
            contenedor.getChildren().add(stringPuntajeASuperar);
        }
        if (contenedor.getChildren().size() == 1) {
            Label puntajeASuperarMostrado = this.creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(0));
            puntajeASuperarMostrado.setTextFill(Color.WHITE);
            puntajeASuperarMostrado.setFont(Font.font(22));

            HBox.setMargin(puntajeASuperarMostrado, new Insets(5,5,0,0));
            contenedor.getChildren().add(puntajeASuperarMostrado);
        }
    }

    @Override
    public void actualizar(Object notificador) {
        Ronda ronda = (Ronda) notificador;
        Label puntajeASuperarMostrado = this.creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(ronda.obtenerPuntajeASuperar()));
        puntajeASuperarMostrado.setTextFill(Color.WHITE);
        puntajeASuperarMostrado.setFont(Font.font(22));
        this.contenedor.getChildren().set(1, puntajeASuperarMostrado);
        HBox.setMargin(puntajeASuperarMostrado, new Insets(5,5,0,0));
    }
}
