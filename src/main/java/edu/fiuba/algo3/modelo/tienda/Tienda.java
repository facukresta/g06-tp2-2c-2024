package edu.fiuba.algo3.modelo.tienda;

import edu.fiuba.algo3.modelo.Comprable;

import java.util.ArrayList;

public interface Tienda {
    void comprar(Comprable compra);

    ArrayList<Comprable> mostrarProductos();
}
