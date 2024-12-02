package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.Observado;
import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.comodin.ComodinNoEnComodineraException;
import edu.fiuba.algo3.modelo.comodin.Modificador;
import edu.fiuba.algo3.modelo.juego.Juego;

import java.util.ArrayList;

public class Tarotera implements Observado {
    private ArrayList<Tarot> tarots = new ArrayList<>();
    private ArrayList<Observador> observadores = new ArrayList<>();

    public Tarotera() {
        tarots.add(new SinTarot());
        tarots.add(new SinTarot());
    }

    public void agregarTarot(Tarot tarotAAgregar) {
        for (Tarot tarot: this.tarots) {
            if (tarot.obtenerNombre().equals("tarotVacio")) {
                this.tarots.set(this.tarots.indexOf(tarot), tarotAAgregar);
                this.notificarObservadores();
                return;
            }
        }
    }

    public void quitarTarot(Tarot tarotAQuitar) {
        if (!this.tarots.contains(tarotAQuitar))
            throw new ComodinNoEnComodineraException();
        this.tarots.set(this.tarots.indexOf(tarotAQuitar), new SinTarot());
        this.notificarObservadores();
    }

    public void aplicarTarotsJuego() {
        for (Tarot tarot: this.tarots) {
            if (!tarot.obtenerNombre().equals("tarotVacio") && tarot.sosTarotDeJuego()) {
                Juego.aplicarTarot(tarot);
                this.quitarTarot(tarot);
            }
        }
    }

    public ArrayList<Tarot> obtenerTarots() {
        return this.tarots;
    }

    public int cantidadDeTarots() {
        int cantidad = 0;
        for (Tarot tarot : this.tarots) {
            if (!tarot.obtenerNombre().equals("tarotVacio")) {
                cantidad++;
            }
        }
        return cantidad;
    }

    @Override
    public void agregarObservador(Observador observador) {
        this.observadores.add(observador);
        observador.actualizar(this);
    }

    @Override
    public void agregarObservadores(ArrayList<Observador> observadores) {
        this.observadores.addAll(observadores);
        for (Observador observador : observadores) {
            observador.actualizar(this);
        }
    }

    @Override
    public void  quitarObservador(Observador observador) {
        this.observadores.remove(observador);
    }

    @Override
    public void  quitarObservadores(ArrayList<Observador> observadores) {
        this.observadores.removeAll(observadores);
    }

    @Override
    public void  notificarObservadores() {
        observadores.forEach(observador -> observador.actualizar(this));
    }
}
