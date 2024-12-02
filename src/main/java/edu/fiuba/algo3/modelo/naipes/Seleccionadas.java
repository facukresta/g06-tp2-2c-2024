package edu.fiuba.algo3.modelo.naipes;

import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import java.util.ArrayList;
import edu.fiuba.algo3.modelo.juego.*;

public class Seleccionadas {
    private ArrayList<Carta> cartasSeleccionadas;

    public Seleccionadas() {
        this.cartasSeleccionadas = new ArrayList<>();
    }

    public boolean seleccionarCarta(Carta carta) {
        if (!this.cartasSeleccionadas.remove(carta)) {
            if (this.cartasSeleccionadas.size() < 5) {
                this.cartasSeleccionadas.add(carta);
                return true;
            }
        }
        return false;
    }

    public Juego obtenerJuego(){
        return Juego.chequearJuego(cartasSeleccionadas);
    }

    public ArrayList<Carta> obtenerCartasSeleccionadas(){
        return this.cartasSeleccionadas;
    }

    public boolean estaVacio(){
        return this.cartasSeleccionadas.isEmpty();
    }

    public void vaciarCartas(){
        this.cartasSeleccionadas = new ArrayList<>();
    }
}
