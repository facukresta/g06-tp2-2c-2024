package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.naipes.Seleccionadas;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.ronda.PasoLaRondaException;
import edu.fiuba.algo3.modelo.ronda.PerdioLaRondaException;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;

public class JugarManoEventHandler implements EventHandler<ActionEvent> {

    private Seleccionadas seleccionadas;
    private Comodinera comodinera;
    private ArrayList<Ronda> rondas;
    private Runnable pasarDeRonda;
    private Runnable perder;
    private AudioClip sonidoError = new AudioClip(getClass().getResource("/sonido/error.mp3").toExternalForm());


    public JugarManoEventHandler(Seleccionadas seleccionadas, Comodinera comodinera, ArrayList<Ronda> rondas, Runnable pasarDeRonda, Runnable perder) {
        this.seleccionadas = seleccionadas;
        this.comodinera = comodinera;
        this.rondas = rondas;
        this.pasarDeRonda = pasarDeRonda;
        this.perder = perder;

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!this.seleccionadas.estaVacio()) {
            try {
                this.rondas.get(0).jugarMano(this.seleccionadas, comodinera);
            } catch (PasoLaRondaException e) {
                pasarDeRonda.run();
                return;
            } catch (PerdioLaRondaException e2) {
                this.perder.run();
                return;
            }
        } else {
            sonidoError.play();
        }
    }
}
