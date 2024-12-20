package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.aleatorio.Ejecucion;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

public abstract class Comodin  implements Modificador {
    protected final double valor;
    protected final Ejecucion probabilidad;
    protected final Juego juego;
    private final String nombre;

    public Comodin(double valor, Ejecucion probabilidad, Juego juego, String nombre) {
        if (valor <= 0) {
            throw new CantidadComodinInvalida();
        }
        this.juego = juego;
        this.probabilidad = probabilidad;
        this.valor = valor;
        this.nombre = nombre;
    }

    public abstract void aplicarModificador(Puntaje puntaje, Juego juego);

    public void comprar() {
        // A implementar si se agrega dinero
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerRutaBase() {
        return "comodines/";
    }
}
