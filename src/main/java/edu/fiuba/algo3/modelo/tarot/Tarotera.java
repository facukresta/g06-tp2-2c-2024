package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.comodin.ComodinNoEnComodineraException;
import edu.fiuba.algo3.modelo.comodin.Modificador;
import edu.fiuba.algo3.modelo.juego.Juego;

import java.util.ArrayList;

public class Tarotera {
    private ArrayList<Tarot> tarots = new ArrayList<>();

    public Tarotera() {
        tarots.add(new SinTarot());
        tarots.add(new SinTarot());
    }

    public void agregarTarot(Tarot tarotAAgregar) {
        for (Tarot tarot: this.tarots) {
            if (tarot.obtenerNombre().equals("comodinVacio")) {
                this.tarots.set(this.tarots.indexOf(tarot), tarotAAgregar);
                return;
            }
        }
    }

    public void quitarTarot(Tarot tarotAQuitar) {
        if (!this.tarots.contains(tarotAQuitar))
            throw new ComodinNoEnComodineraException();
        this.tarots.set(this.tarots.indexOf(tarotAQuitar), new SinTarot());
    }

    public void aplicarTarotsJuego() {
        for (Tarot tarot: this.tarots) {
            if (!tarot.obtenerNombre().equals("comodinVacio")) {
                if (tarot.sosTarotDeJuego()) {
                    Juego.aplicarTarot(tarot);
                    this.quitarTarot(tarot);
                }
            }
        }
    }

    public ArrayList<Tarot> obtenerTarots() {
        return this.tarots;
    }

    public int cantidadDeTarots() {
        int cantidad = 0;
        for (Tarot tarot : this.tarots) {
            if (!tarot.obtenerNombre().equals("comodinVacio")) {
                cantidad++;
            }
        }
        return cantidad;
    }

}
