package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.naipes.carta.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class LectorDeCartas {
    public ArrayList<Carta> leerCartas(){
        ArrayList<Carta> cartas = new ArrayList<>();
        HashMap<String, Palo> mapaDePalos = new HashMap<>();
        mapaDePalos.put("Trebol", new Trebol());
        mapaDePalos.put("Diamantes", new Diamante());
        mapaDePalos.put("Picas", new Pica());
        mapaDePalos.put("Corazones", new Corazon());

        HashMap<String, Integer> mapaDeNumeros = new HashMap<>();
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
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("json/mazo.json");
            if (inputStream == null) {
                throw new FileNotFoundException("Archivo mazo.json no encontrado en src/test/resources/json");
            }

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(inputStream));
            JSONArray mazo = (JSONArray) jsonObject.get("mazo");

            for (Object obj : mazo) {
                JSONObject cartaJson = (JSONObject) obj;

                cartaJson.get("nombre");
                String palo = (String) cartaJson.get("palo");
                String numero = (String) cartaJson.get("numero");
                cartaJson.get("puntos");
                cartaJson.get("multiplicador");

                cartas.add(new Carta(mapaDeNumeros.get(numero), mapaDePalos.get(palo)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartas;
    }
}
