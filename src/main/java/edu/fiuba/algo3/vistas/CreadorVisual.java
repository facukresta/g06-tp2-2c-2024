package edu.fiuba.algo3.vistas;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.InputStream;

public class CreadorVisual {

    public ImageView crearImageView(String ruta, Pane contenedorPrincipal, double ancho, double altura) {
        InputStream inputStream = getClass().getResourceAsStream("/"+ruta+".png");
        ImageView imageView = new ImageView(new Image(inputStream));
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(contenedorPrincipal.widthProperty().multiply(ancho));
        imageView.fitHeightProperty().bind(contenedorPrincipal.heightProperty().multiply(altura));
        return imageView;
    }

    public Background crearBackground(String ruta, Pane contenedorPrincipal) {
        InputStream inputStream = getClass().getResourceAsStream("/"+ruta+".png");
        Image imagen = new Image(inputStream);
        BackgroundImage backgroundImage = new BackgroundImage(
                imagen,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        return new Background(backgroundImage);
    }




}
