package edu.fiuba.algo3.modelo.ronda;

import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.comodin.Modificador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.naipes.Mano;
import edu.fiuba.algo3.modelo.naipes.ManoDe8;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.tienda.Tienda;

import java.util.ArrayList;

public class Ronda {
    private Mano mano = new ManoDe8();
    private ArrayList<Puntaje> puntajesObtenidos = new ArrayList<>();
    private Puntaje puntajeASuperar;
    private Mazo mazo;
    private int descartes;
    private int manos;
    private Tienda tienda;

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
        this.mazo.mezclar();
        this.mano.agregarCartas(this.mazo.repartirCartas(8));
        this.tienda = tienda;
    }

    private boolean pasoLaRonda(){
        return (puntajeASuperar.esMenorAPuntajes(puntajesObtenidos));
    }

    private boolean puedeSeguirJugando(){
        return (!pasoLaRonda() && manos > 0);
    }

    public void jugarMano(ArrayList<Carta> cartasSeleccionadas, Juego juego, Comodinera comodinera) {
        puntajesObtenidos.add(mano.jugarMano(cartasSeleccionadas, juego, comodinera));
        this.manos--;
        if (pasoLaRonda()) {
            throw new PasoLaRondaException();
        } else if (!puedeSeguirJugando()) {
            throw new PerdioLaRondaException();
        }
        mano.agregarCartas(mazo.repartirCartas(8-mano.obtenerCantidadDeCartas()));
    }
    public void descartarMano(ArrayList<Carta> cartasSeleccionadas, Juego juego, Comodinera comodinera) {
        puntajesObtenidos.add(mano.descartarMano(cartasSeleccionadas, juego, comodinera));
        this.descartes--;
        if (pasoLaRonda()) {
            throw new PasoLaRondaException();
        }
        mano.agregarCartas(mazo.repartirCartas(8-mano.obtenerCantidadDeCartas()));
    }

    public void comprar (Comodin comodin) {
        tienda.comprar(comodin);
    }

    public void comprar (Carta carta) {
        tienda.comprar(carta);
    }

    public void comprar (Tarot tarot) {
        tienda.comprar(tarot);
    }
}


//      ESTO ES PARA EL CONTROLADOR!!!
//
//    public void modificarCarta(Carta carta, Tarot tarot){
//        mano.modificarCarta(carta, tarot);
//    }
//
//    public void modificarJuego(Tarot tarot){
//        mano.modificarJuego(tarot);
//    }
//
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
