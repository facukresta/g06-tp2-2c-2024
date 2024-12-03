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

public class PuntajeObtenido implements Observador {
    private HBox contenedor;
    private CreadorDeEtiqueta creadorDeEtiqueta = new CreadorDeEtiqueta();
    private CreadorVisual creadorVisual = new CreadorVisual();

    public PuntajeObtenido(HBox contenedor, Pane contenedorPrincipal) {
        this.contenedor = contenedor;
        contenedor.setSpacing(5);
        if (contenedor.getChildren().size() == 0) {
            ImageView puntajesObtenidosMostrados = creadorVisual.crearImageView("puntajeObtenido",contenedorPrincipal, 0.08, 0.04);
            HBox.setMargin(puntajesObtenidosMostrados, new Insets(5,5,0,0));
            contenedor.getChildren().add(puntajesObtenidosMostrados);
        }
        if (contenedor.getChildren().size() == 1) {
            Label puntajesObtenidosMostrados = this.creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(0));
            puntajesObtenidosMostrados.setTextFill(Color.WHITE);
            puntajesObtenidosMostrados.setFont(Font.font(22));
            HBox.setMargin(puntajesObtenidosMostrados, new Insets(5,5,0,0));
            contenedor.getChildren().add(puntajesObtenidosMostrados);
        }
    }

    @Override
    public void actualizar(Object notificador) {
        Ronda ronda = (Ronda) notificador;
        Label puntajesObtenidosMostrados = creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(ronda.obtenerPuntajesObtenidos().calcularPuntaje()));
        puntajesObtenidosMostrados.setTextFill(Color.WHITE);
        puntajesObtenidosMostrados.setFont(Font.font(22));
        this.contenedor.getChildren().set(1, puntajesObtenidosMostrados);
        HBox.setMargin(puntajesObtenidosMostrados, new Insets(5,5,0,0));
    }
}
