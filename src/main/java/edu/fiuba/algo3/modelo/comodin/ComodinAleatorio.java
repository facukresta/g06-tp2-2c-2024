//package edu.fiuba.algo3.modelo.comodin;
//
//import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
//import edu.fiuba.algo3.modelo.juego.Juego;
//import edu.fiuba.algo3.modelo.puntaje.Puntaje;
//
//public class ComodinAleatorio extends Comodin {
//    Aleatorio probabilidad;
//    boolean roto = false;
//    Comodin comodin;
//    public ComodinAleatorio(Aleatorio aleatorio, Comodin comodin) {
//        this.comodin = comodin;
//        this.probabilidad = aleatorio;
//    }
//    @Override
//    public void aplicarModificador(Puntaje puntaje, Juego juego) {
//        if (probabilidad.sucede()) {
//            roto = true;
//        }
//        if (!roto) {
//            this.comodin.aplicarModificador(puntaje, juego);
//        }
//    }
//}
