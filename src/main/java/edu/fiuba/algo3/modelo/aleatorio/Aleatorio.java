package edu.fiuba.algo3.modelo.aleatorio;

public class Aleatorio {
    private int numero;
    public Aleatorio(int probabilidad) {
        if (probabilidad <= 0) {
            throw new ProbabilidadInvalidaException();
        }
        this.numero = probabilidad;
    }
    public boolean sucede() {
        return (((int) (Math.random() * numero) == 0));
    }
}