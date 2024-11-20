package edu.fiuba.algo3.modelo.naipes;
import edu.fiuba.algo3.modelo.comodin.Modificador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.comodin.Comodin;

import java.lang.module.ModuleFinder;
import java.util.ArrayList;

public class Mano {
    private ArrayList<Carta> cartas;
    private final int maxCartas;
    private ArrayList<Modificador> comodines;

    public Mano() {
        this.cartas = new ArrayList<>();
        this.comodines = new ArrayList<>();
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

    public void agregarComodin(Modificador comodin) {
        comodines.add(comodin);
    }

//    public void elegirCarta(Carta carta) {
//        Carta cartaAElegir = this.obtenerCarta(carta);
//        if (cartasSeleccionadas.contains(cartaAElegir)) {
//            this.cartasSeleccionadas.remove(cartaAElegir);
//        } else {
//            if (this.cartasSeleccionadas.size() == this.maxCartasSeleccionadas) {
//                throw new MaximoCartasSeleccionadasException();
//            }
//            this.cartasSeleccionadas.add(cartaAElegir);
//        }
//        this.juego = Juego.chequearJuego(this.cartasSeleccionadas);
//    }

    public Puntaje descartarMano(ArrayList<Carta> cartasSeleccionadas, Juego juego) {
        if (cartasSeleccionadas.isEmpty()) {
            throw new SinCartasSeleccionadasException();
        }
        quitarCartas(cartasSeleccionadas);
        cartasSeleccionadas.clear();
        juego = Juego.chequearJuego(cartasSeleccionadas);
        Puntaje puntaje = juego.puntuarMano();
        for (Modificador comodin : comodines) {
            comodin.aplicarModificador(puntaje, juego);
        }
        return puntaje;
    }

    public Puntaje jugarMano(ArrayList<Carta> cartasSeleccionadas, Juego juego){
        if (cartasSeleccionadas.isEmpty()) {
            throw new SinCartasSeleccionadasException();
        }
        if (cartasSeleccionadas.size() > 5) {
            throw new MaximoCartasSeleccionadasException();
        }
        for (Carta carta : cartasSeleccionadas ) {
            this.obtenerCarta(carta);
        }
        Puntaje puntaje = juego.puntuarMano();
        for (Modificador comodin : comodines) {
            comodin.aplicarModificador(puntaje, juego);
        }
        this.quitarCartas(cartasSeleccionadas);
        return puntaje;
    }

    public int obtenerCantidadDeCartas() {
        return this.cartas.size();
    }

    public ArrayList<Carta> obtenerCartas() {
        return this.cartas;
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
        Carta cartaAModificar = this.obtenerCarta(carta);
        cartaAModificar.aplicarModificador(tarot);
    }

    public void modificarJuego(Tarot tarot){
        Juego.aplicarTarot(tarot);
    }

    // Esto seria para un aplicar un tarot de aplicacion :"Mano Jugada", "Cualquiera", en un Juego en especifico
//    public void modificarJuegoEnParticular(Tarot tarot, Juego juego){
//        tarot.establecerJuego(juego);
//        this.modificarJuego(tarot);
//    }
}

