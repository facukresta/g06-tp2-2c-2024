package edu.fiuba.algo3.repositorios;

import java.util.HashMap;

public class ConversorNumero {
    HashMap<String, Integer> mapaDeNumeros = new HashMap<>();

    public ConversorNumero(){
        mapaDeNumeros.put("As", 1);
        mapaDeNumeros.put("Rey", 13);
        mapaDeNumeros.put("Reina", 12);
        mapaDeNumeros.put("Jota", 11);
        mapaDeNumeros.put("2", 2);
        mapaDeNumeros.put("3", 3);
        mapaDeNumeros.put("4", 4);
        mapaDeNumeros.put("5", 5);
        mapaDeNumeros.put("6", 6);
        mapaDeNumeros.put("7", 7);
        mapaDeNumeros.put("8", 8);
        mapaDeNumeros.put("9", 9);
        mapaDeNumeros.put("10", 10);
    }

    public int convertirNumero(String numero) {
        return this.mapaDeNumeros.get(numero);
    }
}
