package edu.fiuba.algo3.modelo.aleatorio;

public class Aleatorio implements Ejecucion {
    private int probabilidad;
    public Aleatorio(int probabilidad) {
        if (probabilidad <= 0) {
            throw new ProbabilidadInvalidaException();
        }
        this.probabilidad = probabilidad;
    }
    public void ejecuta(Runnable accion) {
        if (((int) (Math.random() * probabilidad) == 0))
            accion.run();
    }
}