package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class PuntajeObtenido implements Observador {
    private HBox contenedor;
    private CreadorDeEtiqueta creadorDeEtiqueta = new CreadorDeEtiqueta();

    public PuntajeObtenido(HBox contenedor) {
        this.contenedor = contenedor;
        Label puntajeASuperarMostrado = this.creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(0), "-fx-font-size: 24px; -fx-text-fill: white;");
        if (this.contenedor.getChildren().size() == 1) {
            this.contenedor.getChildren().add(puntajeASuperarMostrado);
            HBox.setMargin(puntajeASuperarMostrado, new Insets(5,5,0,0));
        }
    }

    @Override
    public void actualizar(Object notificador) {
        Ronda ronda = (Ronda) notificador;
        Label puntajesObtenidosMostrados = creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(ronda.obtenerPuntajesObtenidos()), "-fx-font-size: 24px; -fx-text-fill: white;");
        this.contenedor.getChildren().set(1, puntajesObtenidosMostrados);
        HBox.setMargin(puntajesObtenidosMostrados, new Insets(5,5,0,0));
    }
}
