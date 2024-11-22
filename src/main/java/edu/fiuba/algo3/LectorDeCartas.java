package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.naipes.carta.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;


public class LectorDeCartas {
    private final ConversorPalo conversorPalo = new ConversorPalo();
    private final ConversorNumero conversorNumero = new ConversorNumero();
    private final AbridorDeJson abridorDeJson = new AbridorDeJson();
    public ArrayList<Carta> leerCartas(){
        ArrayList<Carta> cartas = new ArrayList<>();
        try {
            JSONObject jsonObject = abridorDeJson.abrirJson("mazo");
            JSONArray mazo = (JSONArray) jsonObject.get("mazo");
            for (Object obj : mazo) {
                JSONObject cartaJson = (JSONObject) obj;
                String palo = (String) cartaJson.get("palo");
                String numero = (String) cartaJson.get("numero");
                cartas.add(new CartaInglesa(conversorNumero.convertirNumero(numero), conversorPalo.convertirPalo(palo)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartas;
    }
}
