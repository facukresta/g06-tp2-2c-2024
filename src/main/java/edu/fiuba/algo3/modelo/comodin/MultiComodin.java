package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.juego.EscaleraReal;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;

public class MultiComodin implements Modificador {
    private final ArrayList<Modificador> comodines = new ArrayList<>();
    private final String nombre;

    public MultiComodin(String nombre) {
        this.nombre = nombre;
    }

    public void componerComodin(Modificador comodin) {
        comodines.add(comodin);
    }

    @Override
    public void aplicarModificador(Puntaje puntaje, Juego juego) {
        for (Modificador comodin : comodines) {
            comodin.aplicarModificador(puntaje, juego);
        }
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerRutaBase() {
        return "comodines/";
    }

    public void comprar() {
        // A implementar si se agrega dinero
    }
}
