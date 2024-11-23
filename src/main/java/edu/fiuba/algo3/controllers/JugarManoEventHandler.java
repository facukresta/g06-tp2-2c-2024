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
    private Juego juego;
    private Comodinera comodinera;
    private Ronda ronda;


    public JugarManoEventHandler(ArrayList<Carta> seleccionadas, Juego juego, Comodinera comodinera, Ronda ronda) {
        this.seleccionadas = seleccionadas;
        this.juego = juego;
        this.comodinera = comodinera;
        this.ronda = ronda;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!seleccionadas.isEmpty()) {
            ArrayList<Carta> seleccionadasAntes = this.seleccionadas;
            try {
                ronda.jugarMano(seleccionadas, juego, comodinera);
            } catch (PasoLaRondaException e) {
                // cambio de scena o layout
            } catch (PerdioLaRondaException e2) {
                // cambio de scena o layout a escena final
            }
            for (Carta carta : seleccionadasAntes) {
                // carta.handlerRemove.fireEvent()
            }
        }

    }
}
