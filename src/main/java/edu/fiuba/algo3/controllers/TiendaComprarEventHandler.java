package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.tienda.Comprable;
import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.comodin.Modificador;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.tarot.Tarotera;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class TiendaComprarEventHandler implements EventHandler<ActionEvent> {
    private Comodinera comodinera;
    private Tarotera tarotera;
    private Mazo mazo;
    ArrayList<Comprable> productos;
    Runnable salir;

    public TiendaComprarEventHandler(Comodinera comodinera, Tarotera tarotera, Mazo mazo, ArrayList<Comprable> productos, Runnable salir) {
        this.comodinera = comodinera;
        this.tarotera = tarotera;
        this.mazo = mazo;
        this.productos = productos;
        this.salir = salir;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!this.productos.isEmpty()) {
            if (this.productos.get(0) instanceof Carta) {
                mazo.agregarCarta((Carta) this.productos.get(0));
                this.salir.run();
                return;
            } else if (this.productos.get(0) instanceof Tarot) {
                if (tarotera.cantidadDeTarots() < 2) {
                    tarotera.agregarTarot((Tarot) this.productos.get(0));
                    tarotera.aplicarTarotsJuego();
                    this.salir.run();
                    return;
                }
            } else {
                if (comodinera.cantidadDeComodines() < 5) {
                    comodinera.agregarComodin((Modificador) this.productos.get(0));
                    this.salir.run();
                    return;
                }
            }
        }

    }
}
