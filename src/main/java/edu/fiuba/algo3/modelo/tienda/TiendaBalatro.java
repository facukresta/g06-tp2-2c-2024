package edu.fiuba.algo3.modelo.tienda;

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
            throw new MaximoProductosCompradosExcepction();
        }
        this.comprado = true;
        this.productos.clear();
    }

    public ArrayList<Comprable> mostrarProductos() {
        return this.productos;
    }
}
