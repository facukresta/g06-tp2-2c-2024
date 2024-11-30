package edu.fiuba.algo3.vistas;
import javafx.scene.control.Label;

public class CreadorDeEtiqueta {
    public Label crearEtiquetaConEstilo(String texto, String estilo) {
        Label etiqueta = new Label(texto);
        etiqueta.setStyle(estilo);
        return etiqueta;
    }
}
