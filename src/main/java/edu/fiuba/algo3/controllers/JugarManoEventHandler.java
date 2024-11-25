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
    private Runnable action1;
    private Runnable action2;


    public JugarManoEventHandler(ArrayList<Carta> seleccionadas, Comodinera comodinera, Ronda ronda, Runnable action1, Runnable action2) {
        this.seleccionadas = seleccionadas;
        this.comodinera = comodinera;
        this.ronda = ronda;
        this.action1 = action1;
        this.action2 = action2;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!seleccionadas.isEmpty()) {
            try {
                ronda.jugarMano(seleccionadas, Juego.chequearJuego(seleccionadas), comodinera);
            } catch (PasoLaRondaException e) {
                // cambio de scena o layout
            } catch (PerdioLaRondaException e2) {
                this.action2.run();
                return;
            }
            // volver a hacer la escena
            action1.run();
        }
    }
}
