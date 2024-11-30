package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.tarot.CambiadorDePuntos;
import edu.fiuba.algo3.modelo.tarot.SinTarot;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.tarot.Tarotera;
import edu.fiuba.algo3.vistas.CreadorDeBotones;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class TarotApretarEventHandler implements EventHandler<ActionEvent> {

    private ArrayList<Carta> seleccionadas;
    private Tarot tarot;
    private Button boton;
    private VBox tarots;
    private Pane contenedor;
    private CreadorDeBotones creadorDeBotones = new CreadorDeBotones();
    private Tarotera tarotera;

    public TarotApretarEventHandler(ArrayList<Carta> seleccionadas, Tarot tarot, Button tarotBoton, VBox tarots, Pane contenedorPrincipal, Tarotera tarotera) {
        this.seleccionadas = seleccionadas;
        this.tarot = tarot;
        this.boton = tarotBoton;
        this.tarots = tarots;
        this.contenedor = contenedorPrincipal;
        this.tarotera = tarotera;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (this.seleccionadas.size() == 1) {
            Carta cartaAModificar = this.seleccionadas.get(0);
            cartaAModificar.aplicarModificador(this.tarot);
            Button tarotBoton = this.creadorDeBotones.crearBoton("comodinVacio", (null), this.contenedor, 0.05, 0.13);
            tarotBoton.setOnAction(new TarotApretarEventHandler(this.seleccionadas, new SinTarot(), tarotBoton, this.tarots, this.contenedor, this.tarotera));
//            tarots.getChildren().remove(this.boton);
            this.tarots.getChildren().set(this.tarots.getChildren().indexOf(this.boton),tarotBoton);
            VBox.setMargin(tarotBoton, new Insets(10,0,0,20));
            this.tarotera.quitarTarot(this.tarot);
        }
    }
}