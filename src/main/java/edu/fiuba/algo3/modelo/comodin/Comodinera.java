package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.Observado;
import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;
import java.util.Observer;

public class Comodinera implements Observado {
    private ArrayList<Modificador> comodines = new ArrayList<>();
    private ArrayList<Observador> observadores = new ArrayList<>();

    public Comodinera() {
        for (int i = 1; i <= 5; i++) {
            this.comodines.add(new SinComodin());
        }
    }

    public void agregarComodin(Modificador comodin) {
        for (Modificador modificador: comodines) {
            if (modificador.obtenerNombre().equals("comodinVacio")) {
                this.comodines.set(this.comodines.indexOf(modificador), comodin);
                this.notificarObservadores();
                return;
            }
        }
    }

    public void cambiarOrden(Modificador comodin1, Modificador comodin2) {
        int posComodin1 = this.comodines.indexOf(comodin1);
        int posComodin2 = this.comodines.indexOf(comodin2);
        this.comodines.set(posComodin2, comodin1);
        this.comodines.set(posComodin1, comodin2);
        this.notificarObservadores();
    }

    public void quitaComodin(Modificador comodin) {
        if (!this.comodines.contains(comodin))
            throw new ComodinNoEnComodineraException();
        this.comodines.set(this.comodines.indexOf(comodin), new SinComodin());
        this.notificarObservadores();
    }

    public void aplicarComodines(Puntaje puntajeBase, Juego juego) {
        for (Modificador comodin : comodines) {
            comodin.aplicarModificador(puntajeBase, juego);
        }
    }

    public int cantidadDeComodines(){
        int cantidad = 0;
        for (Modificador comodin : comodines) {
            if (!comodin.obtenerNombre().equals("comodinVacio")) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public ArrayList<Modificador> obtenerComodines() {
        return this.comodines;
    }

    @Override
    public void agregarObservador(Observador observador) {
        this.observadores.add(observador);
        observador.actualizar(this);
    }

    @Override
    public void agregarObservadores(ArrayList<Observador> observadores) {
        this.observadores.addAll(observadores);
        for (Observador observador : observadores) {
            observador.actualizar(this);
        }
    }

    @Override
    public void  quitarObservador(Observador observador) {
        this.observadores.remove(observador);
    }

    @Override
    public void  quitarObservadores(ArrayList<Observador> observadores) {
        this.observadores.removeAll(observadores);
    }

    @Override
    public void  notificarObservadores() {
        observadores.forEach(observador -> observador.actualizar(this));
    }
}
