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
        this.puntos += puntajeASumar.obtenerPuntos();
        if (puntajeASumar.obtenerMultiplicador() != 1) {
            this.multiplicador += puntajeASumar.obtenerMultiplicador();
        }
    }

    public void sumarPuntos(Puntaje puntaje) {
        this.puntos += puntaje.obtenerPuntos();
    }

    public void sumarMultiplicador(Puntaje puntaje) {
        this.multiplicador += puntaje.obtenerMultiplicador();
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
