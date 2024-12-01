package edu.fiuba.algo3.modelo.naipes;
import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.Tarot;

import java.util.ArrayList;

public class ManoDe8 implements Mano {
    private ArrayList<Carta> cartas;
    private final int maxCartas;

    public ManoDe8() {
        this.cartas = new ArrayList<>();
        this.maxCartas = 8;
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
        this.cartas.addAll(cartas);
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

    public Puntaje descartarMano(Seleccionadas cartasSeleccionadas, Comodinera comodinera) {
        if (cartasSeleccionadas.estaVacio()) {
            throw new SinCartasSeleccionadasException();
        }

        this.quitarCartas(cartasSeleccionadas.obtenerCartasSeleccionadas());
        cartasSeleccionadas.vaciarCartas();
        Juego juego = cartasSeleccionadas.obtenerJuego();
        Puntaje puntaje = juego.puntuarMano();
        comodinera.aplicarComodines(puntaje, juego);
        return puntaje;
    }

    public Puntaje jugarMano(Seleccionadas cartasSeleccionadas, Comodinera comodinera){
        if (cartasSeleccionadas.estaVacio()) {
            throw new SinCartasSeleccionadasException();
        }
        if (cartasSeleccionadas.obtenerCartasSeleccionadas().size() > 5) {
            throw new MaximoCartasSeleccionadasException();
        }
        for (Carta carta : cartasSeleccionadas.obtenerCartasSeleccionadas()) {
            this.obtenerCarta(carta);
        }
        Juego juego = cartasSeleccionadas.obtenerJuego();
        Puntaje puntaje = juego.puntuarMano();
        comodinera.aplicarComodines(puntaje, juego);
        this.quitarCartas(cartasSeleccionadas.obtenerCartasSeleccionadas());
        cartasSeleccionadas.vaciarCartas();
        return puntaje;
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

    public ArrayList<Carta> obtenerCartas() {
        return cartas;
    }

    public void modificarCarta(Carta carta, Tarot tarot){
        Carta cartaAModificar = this.obtenerCarta(carta);
        cartaAModificar.aplicarModificador(tarot);
    }

    public void modificarJuego(Tarot tarot){
        Juego.aplicarTarot(tarot);
    }
}
