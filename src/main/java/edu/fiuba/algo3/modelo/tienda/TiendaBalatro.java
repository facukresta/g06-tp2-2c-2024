package edu.fiuba.algo3.modelo.tienda;

import edu.fiuba.algo3.modelo.Comprable;
import edu.fiuba.algo3.modelo.naipes.MaximoCartasSeleccionadasException;

import java.util.ArrayList;

public class TiendaBalatro implements Tienda {
    private ArrayList<Comprable> productos;
    private boolean comprado = false;

    public TiendaBalatro(ArrayList<Comprable> productos) {
        this.productos = productos;
    }

    public void comprar(Comprable compra) {
        if (!this.productos.contains(compra)) {
            throw new ProductoNoEnTiendaException();
        }
        if (this.comprado) {
            throw new MaximoCartasSeleccionadasException();
        }
        this.comprado = true;
        this.productos.clear();
    }

    public ArrayList<Comprable> mostrarProductos() {
        return this.productos;
    }
}
