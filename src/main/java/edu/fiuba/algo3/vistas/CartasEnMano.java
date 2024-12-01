package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CartaApretarEventHandler;
import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.naipes.Seleccionadas;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class CartasEnMano implements Observador {
    private HBox contenedor;
    private Pane contenedorPrincipal;
    private CreadorDeBotones creadorDeBotones = new CreadorDeBotones();
    private Seleccionadas seleccionadas;

    public CartasEnMano(HBox contenedor, Seleccionadas seleccionadas, Pane contenedorPrincipal) {
        this.contenedor = contenedor;
        this.contenedorPrincipal = contenedorPrincipal;
        this.seleccionadas = seleccionadas;
    }

    @Override
    public void actualizar(Object notificador) {
        Ronda ronda = (Ronda) notificador;
        this.contenedor.getChildren().clear();
        for (Carta carta : ronda.obtenerCartas()) {
            Button cartaBoton = this.creadorDeBotones.crearBoton(carta.obtenerNombre(), (null), this.contenedorPrincipal, 0.08, 0.2);
            cartaBoton.setOnAction(new CartaApretarEventHandler(this.seleccionadas, carta, cartaBoton));
            this.contenedor.getChildren().add(cartaBoton);
            HBox.setMargin(cartaBoton, new Insets(0,0,30,0));
        }
    }
}
