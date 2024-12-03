package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public interface Observado {
    void agregarObservador(Observador observador);

    void agregarObservadores(ArrayList<Observador> observadores);

    void  quitarObservador(Observador observador);

    void  quitarObservadores(ArrayList<Observador> observadores);

    void  notificarObservadores();
}
