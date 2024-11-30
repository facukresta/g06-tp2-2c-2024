package edu.fiuba.algo3.vistas;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class CreadorVisual {

    public ImageView crearImageView(String ruta, Pane contenedorPrincipal, double ancho, double altura) {
        ImageView imageView = new ImageView(new Image("/"+ruta+".png"));
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(contenedorPrincipal.widthProperty().multiply(ancho));
        imageView.fitHeightProperty().bind(contenedorPrincipal.heightProperty().multiply(altura));
        return imageView;
    }

    public Background crearBackground(String ruta, Pane contenedorPrincipal) {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("/"+ruta+".png", true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        return new Background(backgroundImage);
    }




}
