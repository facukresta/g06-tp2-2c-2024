//package edu.fiuba.algo3.modelo.ronda;
//
//import edu.fiuba.algo3.modelo.comodin.Modificador;
//import edu.fiuba.algo3.modelo.juego.Juego;
//import edu.fiuba.algo3.modelo.naipes.Mano;
//import edu.fiuba.algo3.modelo.naipes.Mazo;
//import edu.fiuba.algo3.modelo.naipes.carta.Carta;
//import edu.fiuba.algo3.modelo.puntaje.Puntaje;
//import edu.fiuba.algo3.modelo.tarot.Tarot;
//
//import java.util.ArrayList;
//
//public class Ronda {
//    private Mano mano;
//    private Mazo mazo;
//    private Puntaje puntajeASuperar;
//    private ArrayList<Puntaje> puntajesObtenidos;
//    private int descartes;
//    private int manos;
//
//    public Ronda(Puntaje puntajeASuperar, int manos, int descartes, Mazo mazo) {
//        this.puntajeASuperar = puntajeASuperar;
//        this.manos = manos;
//        this.descartes = descartes;
//        this.puntajesObtenidos = new ArrayList<>();
//        this.mano = new Mano();
//        this.mazo.mezclar();
//        this.mazo = mazo;
//        this.mano.agregarCartas(this.mazo.repartirCartas(8));
//    }
//
//    private boolean pasoLaRonda(){
//        return (puntajeASuperar.esMenorAPuntajes(puntajesObtenidos));
//    }
//
//    private boolean puedeSeguirJugando(){
//        return (!pasoLaRonda() && manos > 0);
//    }
//
//    public void jugarMano() {
//        puntajesObtenidos.add(mano.jugarMano());
//        manos--;
//        if (pasoLaRonda()) {
//            throw new PasoLaRondaException();
//        } else if (!puedeSeguirJugando()) {
//            throw new PerdioLaRondaException();
//        }
//        mano.agregarCartas(mazo.repartirCartas(8-mano.obtenerCantidadDeCartas()));
//    }
//    public void descartarMano() {
//        puntajesObtenidos.add(mano.descartarMano());
//        this.descartes = descartes--;
//        if (pasoLaRonda()) {
//            throw new PasoLaRondaException();
//        }
//        mano.agregarCartas(mazo.repartirCartas(8-mano.obtenerCantidadDeCartas()));
//    }
//
//    public void elegirCarta(Carta carta) {
//        mano.elegirCarta(carta);
//    }
//
//    public void modificarCarta(Carta carta, Tarot tarot){
//        mano.modificarCarta(carta, tarot);
//    }
//
//    public void modificarJuego(Tarot tarot){
//        mano.modificarJuego(tarot);
//    }
//
//    public void agregarComodin(Modificador comodin) {
//        mano.agregarComodin(comodin);
//    }
//}