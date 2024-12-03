package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.TarotApretarEventHandler;
import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.naipes.Seleccionadas;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.tarot.Tarotera;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class TarotsEnMano implements Observador {
    private VBox contenedor;
    private Pane contenedorPrincipal;
    private CreadorDeBotones creadorDeBotones = new CreadorDeBotones();
    Seleccionadas seleccionadas;

    public TarotsEnMano(VBox contenedor, Seleccionadas seleccionadas, Pane contenedorPrincipal) {
        this.contenedor = contenedor;
        this.contenedorPrincipal = contenedorPrincipal;
        this.seleccionadas = seleccionadas;
    }

    @Override
    public void actualizar(Object notificador) {
        Tarotera tarotera = (Tarotera) notificador;
        contenedor.getChildren().clear();
        for (Tarot tarot : tarotera.obtenerTarots()) {
            Button tarotBoton = creadorDeBotones.crearBoton("tarots/"+tarot.obtenerNombre(), (null), contenedorPrincipal, 0.05, 0.13);
            tarotBoton.setOnAction(new TarotApretarEventHandler(this.seleccionadas, tarot, tarotBoton, contenedor, contenedorPrincipal, tarotera));
            contenedor.getChildren().add(tarotBoton);
            VBox.setMargin(tarotBoton, new Insets(10,0,0,20));
        }
    }
}
