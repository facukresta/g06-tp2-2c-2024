package edu.fiuba.algo3.modelo.ronda;

import edu.fiuba.algo3.modelo.Comprable;
import edu.fiuba.algo3.modelo.Observado;
import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.comodin.Modificador;
import edu.fiuba.algo3.modelo.naipes.Mano;
import edu.fiuba.algo3.modelo.naipes.ManoDe8;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.Seleccionadas;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.tienda.Tienda;
import javafx.beans.binding.ObjectBinding;

import java.util.ArrayList;

public class Ronda implements Observado {
    private Mano mano = new ManoDe8();
    private ArrayList<Puntaje> puntajesObtenidos = new ArrayList<>();
    private Puntaje puntajeASuperar;
    private Mazo mazo;
    private int descartes;
    private int manos;
    private Tienda tienda;
    private ArrayList<Observador> observadores = new ArrayList<>();
    private int numeroRonda;
    private static int contadorRondas = 1;

    public Ronda(Puntaje puntajeASuperar, int manos, int descartes, Mazo mazo, Tienda tienda ) {
        if (manos <= 0) {
            throw new CantidadDeManosInvalidaException();
        }
        if (descartes <= 0) {
            throw new CantidadDeDescartesInvalidaException();
        }
        this.puntajeASuperar = puntajeASuperar;
        this.manos = manos;
        this.descartes = descartes;
        this.mazo = mazo;
        this.tienda = tienda;
        this.numeroRonda = contadorRondas++;
    }

    public int obtenerNumeroRonda() {
        return this.numeroRonda;
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

    private boolean pasoLaRonda(){
        return (puntajeASuperar.esMenorAPuntajes(puntajesObtenidos));
    }

    private boolean puedeSeguirJugando(){
        return (!pasoLaRonda() && manos > 0);
    }

    public void jugarMano(Seleccionadas seleccionadas, Comodinera comodinera) {
        puntajesObtenidos.add(mano.jugarMano(seleccionadas, comodinera));
        this.manos--;
        if (pasoLaRonda()) {
            this.notificarObservadores();
            throw new PasoLaRondaException();
        } else if (!puedeSeguirJugando()) {
            this.notificarObservadores();
            contadorRondas = 1;
            throw new PerdioLaRondaException();
        }
        mano.agregarCartas(mazo.repartirCartas(8-mano.obtenerCantidadDeCartas()));
        this.notificarObservadores();
    }

    public void descartarMano(Seleccionadas seleccionadas, Comodinera comodinera) {
        if (this.descartes != 0) {
            puntajesObtenidos.add(mano.descartarMano(seleccionadas, comodinera));
            this.descartes--;
            if (pasoLaRonda()) {
                this.notificarObservadores();
                throw new PasoLaRondaException();
            }
            mano.agregarCartas(mazo.repartirCartas(8-mano.obtenerCantidadDeCartas()));
            this.notificarObservadores();
        }
    }

    public void iniciarRonda() {
        this.mano.agregarCartas(this.mazo.repartirCartas(8));
        this.notificarObservadores();
    }

    public ArrayList<Carta> obtenerCartas() {
        return mano.obtenerCartas();
    }

    public void comprar (Modificador comodin) {
        tienda.comprar(comodin);
    }

    public void comprar (Carta carta) {
        tienda.comprar(carta);
    }

    public void comprar (Tarot tarot) {
        tienda.comprar(tarot);
    }

    public double obtenerPuntajeASuperar() {
        return this.puntajeASuperar.calcularPuntaje();
    }

    public double obtenerPuntajesObtenidos() {
        double puntajesObtenidos = 0;
        for (Puntaje puntaje: this.puntajesObtenidos){
            puntajesObtenidos += puntaje.calcularPuntaje();
        }
        return puntajesObtenidos;
    }

    public int obtenerJugadasRestantes() {
        return this.manos;
    }

    public int obtenerDescartesRestantes() {
        return this.descartes;
    }

    public ArrayList<Comprable> mostrarProductos() {
        return this.tienda.mostrarProductos();
    }

}