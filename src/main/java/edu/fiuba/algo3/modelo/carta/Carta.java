package edu.fiuba.algo3.modelo.carta;

import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class Carta {
    private final int id;
    private int numero;
    private Palo palo;

    public Carta(int numero, Palo palo) {
        if (numero > 13 || numero <= 0) {
            throw new NumeroInvalidoException() ;
        }
        this.id = numero;
        this.numero = numero;
        this.palo = palo;
    }

    public Puntaje obtenerPuntaje() {
        return new Puntaje(this.numero, 1);
    }

    public int obtenerValor() {
        return this.numero;
    }

    private Palo obtenerPalo() {
        return this.palo;
    }

    private int obtenerId() {
        return this.id;
    }

    public boolean esDelMismoPalo(Carta carta) {
        return this.palo.esDeEstePalo(carta.obtenerPalo());
    }

    public boolean sos(Carta carta) {
        return (this.esDelMismoPalo(carta) && this.id == carta.obtenerId());
    }
}