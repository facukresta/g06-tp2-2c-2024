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

public class  DescartarManoEventHandler implements EventHandler<ActionEvent> {

    private ArrayList<Carta> seleccionadas;
    private Comodinera comodinera;
    private ArrayList<Ronda> rondas;
    private int descartes;
    private Runnable pasarDeRonda;
    private Runnable seguirJugando;


    public  DescartarManoEventHandler(ArrayList<Carta> seleccionadas, Comodinera comodinera, ArrayList<Ronda> rondas, int descartes, Runnable pasarDeRonda, Runnable seguirJugando ) {
        this.seleccionadas = seleccionadas;
        this.comodinera = comodinera;
        this.rondas = rondas;
        this.descartes = descartes;
        this.pasarDeRonda = pasarDeRonda;
        this.seguirJugando = seguirJugando;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!seleccionadas.isEmpty() && this.descartes > 0) {
            try {
                this.rondas.get(0).descartarMano(seleccionadas, Juego.chequearJuego(seleccionadas), comodinera);
            } catch (PasoLaRondaException e) {
                this.pasarDeRonda.run();
                return;
            }
            this.seguirJugando.run();
        }
    }
}

