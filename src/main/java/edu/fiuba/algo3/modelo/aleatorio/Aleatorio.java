package edu.fiuba.algo3.modelo.aleatorio;

public class Aleatorio {
    private int probabilidad;
    public Aleatorio(int probabilidad) {
        if (probabilidad <= 0) {
            throw new ProbabilidadInvalidaException();
        }
        this.probabilidad = probabilidad;
    }
    public boolean sucede() {
        return (((int) (Math.random() * probabilidad) == 0));
    }
}