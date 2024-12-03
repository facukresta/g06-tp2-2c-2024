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

public class DescartesRestantes implements Observador {
    private HBox contenedor;
    private CreadorDeEtiqueta creadorDeEtiqueta = new CreadorDeEtiqueta();
    private CreadorVisual creadorVisual = new CreadorVisual();

    public DescartesRestantes(HBox contenedor, Pane contenedorPrincipal) {
        this.contenedor = contenedor;
        contenedor.setSpacing(5);
        if (contenedor.getChildren().size() == 0) {
            ImageView stringDescartesRestantes = creadorVisual.crearImageView("descartesRestantes",contenedorPrincipal, 0.08, 0.04);
            HBox.setMargin(stringDescartesRestantes, new Insets(5,5,0,0));
            contenedor.getChildren().add(stringDescartesRestantes);
        }
        if (contenedor.getChildren().size() == 1) {
            Label jugadasRestantesMostrados = this.creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(0));
            jugadasRestantesMostrados.setTextFill(Color.WHITE);
            jugadasRestantesMostrados.setFont(Font.font(22));
            HBox.setMargin(jugadasRestantesMostrados, new Insets(5,5,0,0));
            contenedor.getChildren().add(jugadasRestantesMostrados);
        }
    }

    @Override
    public void actualizar(Object notificador) {
        Ronda ronda = (Ronda) notificador;
        Label jugadasRestantesMostrados = creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(ronda.obtenerDescartesRestantes()));
        jugadasRestantesMostrados.setTextFill(Color.WHITE);
        jugadasRestantesMostrados.setFont(Font.font(22));
        this.contenedor.getChildren().set(1, jugadasRestantesMostrados);
        HBox.setMargin(jugadasRestantesMostrados, new Insets(5,5,0,0));
    }
}