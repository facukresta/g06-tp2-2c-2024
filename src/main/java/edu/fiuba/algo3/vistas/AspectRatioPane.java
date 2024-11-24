package edu.fiuba.algo3.vistas;

import javafx.scene.layout.Pane;

public class AspectRatioPane extends Pane {
    private final double aspectRatio; // Relación de aspecto deseada (ancho/alto)

    public AspectRatioPane(double aspectRatio) {
        this.aspectRatio = aspectRatio;

        // Escucha los cambios de tamaño y ajusta el contenedor
        widthProperty().addListener((obs, oldVal, newVal) -> layoutChildren());
        heightProperty().addListener((obs, oldVal, newVal) -> layoutChildren());
    }

    @Override
    protected void layoutChildren() {
        double width = getWidth();
        double height = getHeight();
        double scale = Math.min(width / aspectRatio, height);
        double scaledWidth = scale * aspectRatio;
        double scaledHeight = scale;

        // Centrar el contenido escalado
        double xOffset = (width - scaledWidth) / 2;
        double yOffset = (height - scaledHeight) / 2;

        // Redimensionar los hijos
        for (var child : getChildren()) {
            child.resizeRelocate(xOffset, yOffset, scaledWidth, scaledHeight);
        }
    }
}