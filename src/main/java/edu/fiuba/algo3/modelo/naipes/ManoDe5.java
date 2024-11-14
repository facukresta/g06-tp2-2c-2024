package edu.fiuba.algo3.modelo.naipes;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.juego.Juego;

public class ManoDe5 extends Mano {
    private Juego juego;
    public ManoDe5() {
        super(5);
    }
    @Override
    public void agregarCarta(Carta carta) {
        if (this.maxCartas == this.cartas.size()) {
            throw new MaximoCartasSeleccionadasException();
        }
        if (this.cartas.contains(carta)) {
           this.quitarCarta(carta);
        } else {
            this.cartas.add(carta);
        }
        this.juego = Juego.chequearJuego(this.cartas);
    }
    @Override
    public void quitarCarta(Carta carta) {
        Carta cartaAQuitar = obtenerCarta(carta);
        this.cartas.remove(cartaAQuitar);
        this.juego = Juego.chequearJuego(this.cartas);
    }
    public int jugarMano(){
        if (this.cartas.isEmpty()) {
            throw new SinCartasSeleccionadasException();
        }
        return this.juego.puntuarMano(this.cartas).calcularValor();
    }
}
