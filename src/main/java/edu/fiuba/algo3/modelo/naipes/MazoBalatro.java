package edu.fiuba.algo3.modelo.naipes;
import edu.fiuba.algo3.modelo.naipes.carta.*;

import java.util.ArrayList;


public class MazoBalatro implements Mazo {
    private ArrayList<Carta> cartas;
    private ArrayList<Carta> cartasDescartadas;

    public MazoBalatro() {
        this.cartas = new ArrayList<>();
        this.cartasDescartadas = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        this.cartas.add(carta);
    }

    public void agregarCartas(ArrayList<Carta> cartas) {
        this.cartas.addAll(cartas);
    }

    public ArrayList<Carta> repartirCartas(int cantidad) {
        if (cantidad > this.cartas.size()) {
            throw new CartasInsuficientesException();
        }
        ArrayList<Carta> cartasRepartidas = new ArrayList<>();
        for(int i = 1; i <= cantidad; i++) {
            int indiceAleatorio = (int) (Math.random() * this.cartas.size());
            Carta carta = this.cartas.remove(indiceAleatorio);
            cartasRepartidas.add(carta);
            this.cartasDescartadas.add(carta);
        }
        return cartasRepartidas;
    }

    public void mezclar() {
        this.cartas.addAll(this.cartasDescartadas);
        this.cartasDescartadas.clear();
    }

    public void vaciarMazo() {
        this.cartas.clear();
        this.cartasDescartadas.clear();
    }
}
