package edu.fiuba.algo3.modelo.puntaje;

import java.util.ArrayList;

public class Puntaje {
    private int puntos;
    private int multiplicador;

    public Puntaje(int puntos, int multiplicador) {
        if (puntos < 0) {
            throw new PuntosInvalidosException();
        }
        if (multiplicador <= 0) {
            throw new MultiplicadorInvalidosException();
        }
        this.puntos = puntos;
        this.multiplicador = multiplicador;
    }

    public int obtenerPuntos() {
        return puntos;
    }

    public int obtenerMultiplicador() {
        return multiplicador;
    }

    private int calcularPuntaje() {
        return puntos * multiplicador;
    }

    public boolean tenesMismoPuntaje(Puntaje puntaje) {
        return (this.calcularPuntaje() == puntaje.calcularPuntaje());
    }

    public void sumar(Puntaje puntajeASumar) {
        this.sumarPuntos(puntajeASumar);
        this.sumarMultiplicador(puntajeASumar);
    }

    private void sumarPuntos(Puntaje puntaje) {
        this.puntos += puntaje.obtenerPuntos();
    }

    private void sumarMultiplicador(Puntaje puntaje) {
        if (puntaje.obtenerMultiplicador() != 1 && this.multiplicador != 1) {
            this.multiplicador += puntaje.obtenerMultiplicador();
        } else if (puntaje.obtenerMultiplicador() != 1 || this.multiplicador != 1) {
            this.multiplicador += puntaje.obtenerMultiplicador()-1;
        }
    }

    public boolean esMayor(Puntaje puntaje) {
        return this.calcularPuntaje() > puntaje.calcularPuntaje();
    }

    public boolean esMenorAPuntajes(ArrayList<Puntaje> puntajes) {
            int valor = 0;
            for (Puntaje puntaje : puntajes) {
                valor += puntaje.calcularPuntaje();
            }
        return valor > this.calcularPuntaje();
    }
}
