package edu.fiuba.algo3.modelo.naipes.carta;

import edu.fiuba.algo3.modelo.tarot.SinTarot;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public class CartaInglesa implements Carta {
    private int numero;
    private int valor;
    private Palo palo;
    private Tarot modificador;

    public CartaInglesa(int numero, Palo palo) {
        if (numero > 13 || numero <= 0) {
            throw new NumeroInvalidoException();
        }
        this.numero = numero;
        if (numero == 1 || numero >= 10) {
            this.valor = 10;
        } else {
            this.valor = numero;
        }
        this.palo = palo;
        this.modificador = new SinTarot();
    }

    public void aplicarModificador(Tarot tarot) {
        this.modificador = tarot;
    }

    public Puntaje obtenerPuntaje() {
        return modificador.obtenerPuntaje(new Puntaje(this.valor, 1));
    }

    private Palo obtenerPalo() {
        return this.palo;
    }

    public boolean esDelMismoPalo(Carta carta) {
        CartaInglesa cartaInglesa = (CartaInglesa) carta;
        return this.palo.esDeEstePalo(cartaInglesa.obtenerPalo());
    }

    public int obtenerNumero() {
        return this.numero;
    }

    public boolean sos(Carta carta) {
        return (this == carta);
    }
}