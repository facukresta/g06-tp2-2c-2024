package edu.fiuba.algo3.modelo.tienda;

import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.Modificador;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.tarot.Tarot;

import java.util.ArrayList;

public class TiendaBalatro implements Tienda {
    private ArrayList<Carta> cartas;
    private ArrayList<Modificador> comodines;
    private ArrayList<Tarot> tarots;

    public TiendaBalatro(ArrayList<Carta> cartas, ArrayList<Modificador> comodines, ArrayList<Tarot> tarots) {
        this.cartas = cartas;
        this.comodines = comodines;
        this.tarots = tarots;
    }

    public void comprar(Modificador compra) {
        if (!this.comodines.contains(compra)) {
            throw new ProductoNoEnTiendaException();
        }
        this.comodines.clear();
    }

    public void comprar(Tarot compra) {
        if (!this.tarots.contains(compra)) {
            throw new ProductoNoEnTiendaException();
        }
        this.tarots.clear();
    }

    public void comprar(Carta compra) {
        if (!this.cartas.contains(compra)) {
            throw new ProductoNoEnTiendaException();
        }
        this.cartas.clear();
    }

//    public void comprar (Comprable comprable) {
//        try {
//            this.comprar((Tarot) comprable);
//        } catch () {
//
//        }
//
//        try {
//
//        } catch () {
//
//        }
//
//        try {
//
//        } catch () {
//
//        }
//
//        try {
//
//        } catch () {
//
//        }
//    }
}
