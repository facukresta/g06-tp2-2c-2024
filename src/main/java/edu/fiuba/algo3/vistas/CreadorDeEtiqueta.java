package edu.fiuba.algo3.vistas;
import javafx.scene.control.Label;

public class CreadorDeEtiqueta {
    public Label crearEtiquetaConEstilo(String texto) {
        Label etiqueta = new Label(texto);
        return etiqueta;
    }
}
