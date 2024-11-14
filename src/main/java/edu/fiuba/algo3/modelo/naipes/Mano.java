package edu.fiuba.algo3.modelo.naipes;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;

public class Mano {
    protected ArrayList<Carta> cartas;
    protected int maxCartas;
    protected int maxCartasSeleccionadas;
    private ArrayList<Carta> cartasSeleccionadas;
    private Juego juego;

    public Mano(int cantidadDeCartas) {
        this.cartasSeleccionadas = new ArrayList<Carta>();
        this.cartas = new ArrayList<Carta>();
        this.maxCartas = cantidadDeCartas;
        this.maxCartasSeleccionadas = 5;
    }

    public void elegirCarta(Carta carta) {
        Carta cartaAElegir = this.obtenerCarta(carta);
        if (cartasSeleccionadas.contains(cartaAElegir)) {
            this.cartasSeleccionadas.remove(cartaAElegir);
        } else {
            if (this.cartasSeleccionadas.size() == this.maxCartasSeleccionadas) {
                throw new MaximoCartasSeleccionadasException();
            }
            this.cartasSeleccionadas.add(cartaAElegir);
        }
        this.juego = Juego.chequearJuego(this.cartasSeleccionadas);
    }

    public void descartarMano() {
        if (this.cartasSeleccionadas.isEmpty()) {
            throw new SinCartasSeleccionadasException();
        }
        this.quitarCartas(cartasSeleccionadas);
        this.cartasSeleccionadas.clear();
    }

    public Puntaje jugarMano(){
        if (this.cartasSeleccionadas.isEmpty()) {
            throw new SinCartasSeleccionadasException();
        }
        Puntaje puntaje = this.juego.puntuarMano(this.cartasSeleccionadas);
        this.descartarMano();
        return puntaje;
    }

    public void agregarCarta(Carta carta) {
        if (this.maxCartas == this.cartas.size()) {
            throw new ManoLlenaException();
        }
        this.cartas.add(carta);
    }

    public void agregarCartas(ArrayList<Carta> cartas) {
        if (this.maxCartas == this.cartas.size()) {
            throw new ManoLlenaException();
        }
        for (Carta carta : cartas) {
            this.cartas.add(carta);
        }

    }

    public void quitarCarta(Carta carta) {
        Carta cartaAQuitar = obtenerCarta(carta);
        this.cartas.remove(cartaAQuitar);
    }

    public void quitarCartas(ArrayList<Carta> cartas) {
        for (Carta carta : cartas) {
            Carta cartaAQuitar = obtenerCarta(carta);
            this.cartas.remove(cartaAQuitar);
        }
    }

    public int obtenerCantidadDeCartas() {
        return this.cartas.size();
    }

    public Carta obtenerCarta(Carta cartaBuscada) {
        for (Carta carta: this.cartas) {
            if (carta.sos(cartaBuscada)) {
                return carta;
            }
        }
        throw new CartaNoEnManoException();
    }
}

