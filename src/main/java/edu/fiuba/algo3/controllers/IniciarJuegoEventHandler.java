package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import edu.fiuba.algo3.repositorios.LectorDeBalatro;
import edu.fiuba.algo3.repositorios.LectorDeCartas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class IniciarJuegoEventHandler implements EventHandler<ActionEvent>  {
    private Mazo mazo;
    private ArrayList<Ronda> rondas;
    private Runnable action;
    private Ronda rondaActual;
    public IniciarJuegoEventHandler (ArrayList<Ronda> rondas, Mazo mazo, Ronda rondaActual, Runnable action) {
        this.mazo = mazo;
        this.rondas = rondas;
        this.action = action;
        this.rondaActual = rondaActual;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        LectorDeCartas lectorDeCartas = new LectorDeCartas();
        ArrayList<Carta> cartas = lectorDeCartas.leerCartas();
        this.mazo.agregarCartas(cartas);

        LectorDeBalatro lectorDeBalatro = new LectorDeBalatro();
        ArrayList<Ronda> rondas2 = lectorDeBalatro.leerBalatro(this.mazo);

        rondas.addAll(rondas2);
        this.rondaActual = this.rondas.get(0);
        action.run();
    }
}
