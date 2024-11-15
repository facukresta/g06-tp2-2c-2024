package edu.fiuba.algo3.modelo.naipes.carta;

import edu.fiuba.algo3.modelo.tarot.SinTarot;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.HashMap;

public class Carta {
    private int numero;
    private int valor;
    private Palo palo;
    private Tarot modificador;

    public Carta(int numero, Palo palo) {
        if (numero > 13 || numero <= 0) {
            throw new NumeroInvalidoException();
        }
        this.numero = numero;
        HashMap<Integer, Integer> valores = new HashMap<>();
        valores.put(1, 10);
        valores.put(11, 10);
        valores.put(12, 10);
        valores.put(13, 10);
        this.valor = valores.getOrDefault(numero, numero);
        this.palo = palo;
        this.modificador = new SinTarot();
    }

    public void aplicarModificador(Tarot tarot) {
        this.modificador = tarot;
    }

    public Puntaje obtenerPuntaje() {
        return modificador.obtenerPuntaje(new Puntaje(this.valor, 1));
    }

    public int obtenerValor() {
        return this.valor;
    }

    private Palo obtenerPalo() {
        return this.palo;
    }

    public boolean esDelMismoPalo(Carta carta) {
        return this.palo.esDeEstePalo(carta.obtenerPalo());
    }

    private int obtenerNumero() {
        return this.numero;
    }

    public boolean sos(Carta carta) {
        return (this.esDelMismoPalo(carta) && this.numero == carta.obtenerNumero());
    }
}