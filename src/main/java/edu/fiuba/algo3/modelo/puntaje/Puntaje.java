package edu.fiuba.algo3.modelo.puntaje;

import java.util.ArrayList;

public class Puntaje {
    private int puntos;
    private double multiplicador;

    public Puntaje(int puntos, double multiplicador) {
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

    public double obtenerMultiplicador() {
        return multiplicador;
    }

    public double calcularPuntaje() {
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
            double valor = 0;
            for (Puntaje puntaje : puntajes) {
                valor += puntaje.calcularPuntaje();
            }
        return valor > this.calcularPuntaje();
    }

    private void multiplicarMultiplicador(double multiplicador) {
        this.multiplicador *= multiplicador;
    }

    public void multiplicar(double multiplicador) {
        this.multiplicarMultiplicador(multiplicador);
    }
}
