package edu.fiuba.algo3.modelo.tienda;

import edu.fiuba.algo3.modelo.comodin.Modificador;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.tarot.Tarot;

public interface Tienda {
    void comprar(Modificador compra);

    void comprar(Carta compra);

    void comprar(Tarot compra);
}
