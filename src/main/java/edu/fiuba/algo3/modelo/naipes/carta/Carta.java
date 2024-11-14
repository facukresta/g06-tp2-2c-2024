package edu.fiuba.algo3.modelo.naipes.carta;

import edu.fiuba.algo3.modelo.tarot.SinTarot;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class Carta {
    private int numero;
    private Palo palo;
    private Tarot modificador;

    public Carta(int numero, Palo palo) {
        if (numero > 13 || numero <= 0) {
            throw new NumeroInvalidoException() ;
        }
        this.numero = numero;
        this.palo = palo;
        this.modificador = new SinTarot();
    }

    public void aplicarModificador(Tarot tarot) {
        this.modificador = tarot;
    }

    public Puntaje obtenerPuntaje() {
        return modificador.obtenerPuntaje(new Puntaje(this.numero, 1));
    }

    public int obtenerValor() {
        return this.numero;
    }

    private Palo obtenerPalo() {
        return this.palo;
    }

    public boolean esDelMismoPalo(Carta carta) {
        return this.palo.esDeEstePalo(carta.obtenerPalo());
    }

    public boolean sos(Carta carta) {
        return (this.esDelMismoPalo(carta) && this.numero == carta.obtenerValor());
    }
}