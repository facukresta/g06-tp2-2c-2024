package edu.fiuba.algo3.modelo.naipes;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.comodin.Comodin;

import java.util.ArrayList;

public class Mano {
    protected ArrayList<Carta> cartas;
    protected int maxCartas;
    protected int maxCartasSeleccionadas;
    private ArrayList<Carta> cartasSeleccionadas;
    private Juego juego;
    private ArrayList<Comodin> comodines;

    public Mano(int cantidadDeCartas) {
        this.cartasSeleccionadas = new ArrayList<Carta>();
        this.cartas = new ArrayList<Carta>();
        this.comodines = new ArrayList<Comodin>();
        this.maxCartas = cantidadDeCartas;
        this.maxCartasSeleccionadas = 5;
    }

    public void agregarComodin(Comodin comodin) {
        comodines.add(comodin);
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

    public Puntaje descartarMano() {
        if (this.cartasSeleccionadas.isEmpty()) {
            throw new SinCartasSeleccionadasException();
        }
        for (int i = this.cartasSeleccionadas.size() - 1; i >= 0; i--) {
            Carta carta = this.cartasSeleccionadas.get(i);
            this.elegirCarta(carta);
            this.quitarCarta(carta);
        }
        Puntaje puntaje = this.juego.puntuarMano(this.cartasSeleccionadas);
        for (Comodin comodin : comodines) {
            comodin.aplicarModificador(puntaje, this.juego);
        }
        return puntaje;
    }

    public Puntaje jugarMano(){
        if (this.cartasSeleccionadas.isEmpty()) {
            throw new SinCartasSeleccionadasException();
        }
        Puntaje puntaje = this.juego.puntuarMano(this.cartasSeleccionadas);
        for (Comodin comodin : comodines) {
            comodin.aplicarModificador(puntaje, this.juego);
        }
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

    public void modificarCarta(Carta carta, Tarot tarot){
        Carta cartaAModificar = obtenerCarta(carta);
        cartaAModificar.aplicarModificador(tarot);
    }
}

