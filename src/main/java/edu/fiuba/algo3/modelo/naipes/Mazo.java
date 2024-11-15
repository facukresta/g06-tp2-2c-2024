package edu.fiuba.algo3.modelo.naipes;
import edu.fiuba.algo3.modelo.naipes.carta.*;

import java.util.ArrayList;


public class Mazo {
    private ArrayList<Carta> cartas;
    private ArrayList<Carta> cartasDescartadas;

    public Mazo() {
        this.cartas = new ArrayList<Carta>();
        this.cartasDescartadas = new ArrayList<Carta>();
        for(int i = 1; i <= 13; i++) {
            this.cartas.add(new Carta(i, new Corazon()));
        }
        for(int i = 1; i <= 13; i++) {
            this.cartas.add(new Carta(i, new Pica()));
        }
        for(int i = 1; i <= 13; i++) {
            this.cartas.add(new Carta(i, new Diamante()));
        }

        for(int i = 1; i <= 13; i++) {
            this.cartas.add(new Carta(i, new Trebol()));
        }
    }

    public void agregarCarta(Carta carta) {
        this.cartas.add(carta);
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
}
