package edu.fiuba.algo3.repositorios;

import edu.fiuba.algo3.modelo.naipes.carta.*;

import java.util.HashMap;

public class ConversorPalo {
    HashMap<String, Palo> mapaDePalos = new HashMap<>();

    public ConversorPalo(){
        this.mapaDePalos.put("Trebol", new Trebol());
        this.mapaDePalos.put("Diamantes", new Diamante());
        this.mapaDePalos.put("Picas", new Pica());
        this.mapaDePalos.put("Corazones", new Corazon());
    }

    public Palo convertirPalo(String palo) {
        return this.mapaDePalos.get(palo);
    }
}
