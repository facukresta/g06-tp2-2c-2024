package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;

import java.util.ArrayList;

public class Comodinera {
    private ArrayList<Modificador> comodines = new ArrayList<>();

    public Comodinera() {
        for (int i = 1; i <= 5; i++) {
            this.comodines.add(new SinComodin());
        }
    }

    public void agregarComodin(Modificador comodin) {
        for (Modificador modificador: comodines) {
            if (modificador.obtenerNombre().equals("comodinVacio")) {
                this.comodines.set(this.comodines.indexOf(modificador), comodin);
                return;
            }
        }
    }

    public void cambiarOrden(Modificador comodin1, Modificador comodin2) {
        int posComodin1 = this.comodines.indexOf(comodin1);
        int posComodin2 = this.comodines.indexOf(comodin2);
        this.comodines.set(posComodin2, comodin1);
        this.comodines.set(posComodin1, comodin2);
    }

    public void quitaComodin(Modificador comodin) {
        if (!this.comodines.contains(comodin))
            throw new ComodinNoEnComodineraException();
        this.comodines.remove(comodin);
    }

    public void aplicarComodines(Puntaje puntajeBase, Juego juego) {
        for (Modificador comodin : comodines) {
            comodin.aplicarModificador(puntajeBase, juego);
        }
    }

    public int cantidadDeComodines(){
        return this.comodines.size();
    }

    public ArrayList<Modificador> obtenerComodines() {
        return this.comodines;
    }

}
