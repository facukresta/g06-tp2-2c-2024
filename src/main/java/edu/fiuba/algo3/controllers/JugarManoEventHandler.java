package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.ronda.PasoLaRondaException;
import edu.fiuba.algo3.modelo.ronda.PerdioLaRondaException;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class JugarManoEventHandler implements EventHandler<ActionEvent> {

    private ArrayList<Carta> seleccionadas;
    private Comodinera comodinera;
    private Ronda ronda;
    private Runnable pasarDeRonda;
    private Runnable seguirJugando;
    private Runnable perder;


    public JugarManoEventHandler(ArrayList<Carta> seleccionadas, Comodinera comodinera, Ronda ronda, Runnable pasarDeRonda, Runnable seguirJugando, Runnable perder) {
        this.seleccionadas = seleccionadas;
        this.comodinera = comodinera;
        this.ronda = ronda;
        this.pasarDeRonda = pasarDeRonda;
        this.seguirJugando = seguirJugando;
        this.perder = perder;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!seleccionadas.isEmpty()) {
            try {
                ronda.jugarMano(seleccionadas, Juego.chequearJuego(seleccionadas), comodinera);
            } catch (PasoLaRondaException e) {
                pasarDeRonda.run();
                return;
            } catch (PerdioLaRondaException e2) {
                this.perder.run();
                return;
            }
            this.seguirJugando.run();
        }
    }
}
