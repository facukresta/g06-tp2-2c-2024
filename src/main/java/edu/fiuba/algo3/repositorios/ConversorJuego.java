package edu.fiuba.algo3.repositorios;

import edu.fiuba.algo3.modelo.juego.*;

import java.util.HashMap;

public class ConversorJuego {
    HashMap<String, Juego> mapaDeJuegos = new HashMap<>();

    public ConversorJuego(){
        mapaDeJuegos.put("carta alta", new CartaAlta());
        mapaDeJuegos.put("par", new Par());
        mapaDeJuegos.put("doble par", new DoblePar());
        mapaDeJuegos.put("trio", new Trio());
        mapaDeJuegos.put("full", new FullHouse());
        mapaDeJuegos.put("color", new Color());
        mapaDeJuegos.put("poker", new Poker());
        mapaDeJuegos.put("escalera", new Escalera());
        mapaDeJuegos.put("escalera de color", new EscaleraDeColor());
        mapaDeJuegos.put("escalera real", new EscaleraReal());
        mapaDeJuegos.put("sin juego", new SinJuego());
    }

    public Juego convertirJuego(String juego) {
        return this.mapaDeJuegos.get(juego);
    }
}
