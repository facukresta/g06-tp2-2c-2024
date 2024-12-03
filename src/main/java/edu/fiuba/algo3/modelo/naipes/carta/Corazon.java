package edu.fiuba.algo3.modelo.naipes.carta;

public class Corazon implements Palo {
    public boolean esDeEstePalo(Palo palo){
        return (palo instanceof Corazon);
    }
}
