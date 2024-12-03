package edu.fiuba.algo3.repositorios;

import edu.fiuba.algo3.modelo.tarot.CambiadorDeMultiplicador;
import edu.fiuba.algo3.modelo.tarot.CambiadorDePuntos;
import edu.fiuba.algo3.modelo.tarot.Sumador;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class LectorDeTarots {
    private final ConversorJuego conversorJuego = new ConversorJuego();
    private final AbridorDeJson abridorDeJson = new AbridorDeJson();

    public ArrayList<Tarot> leerTarots(){
        ArrayList<Tarot> listaDeTarots = new ArrayList<>();
        try {
            JSONObject jsonObject = abridorDeJson.abrirJson("tarots");
            JSONArray tarots = (JSONArray) jsonObject.get("tarots");
            for (Object obj : tarots) {
                JSONObject cartaJson = (JSONObject) obj;
                String nombre = (String) cartaJson.get("nombre");
                JSONObject efectosJson = (JSONObject) cartaJson.get("efecto");
                int puntos = ((Long) efectosJson.get("puntos")).intValue();
                double multiplicador = ((Number) efectosJson.get("multiplicador")).doubleValue();
                String sobre = (String) cartaJson.get("sobre");
                String juego = (String) cartaJson.get("ejemplar");
                if (sobre.equals("carta")) {
                    if (puntos == 1) {
                        listaDeTarots.add(new CambiadorDeMultiplicador(multiplicador, nombre));
                    } else {
                        listaDeTarots.add(new CambiadorDePuntos(puntos, nombre));
                    }
                } else if (sobre.equals("mano")) {
                    listaDeTarots.add(new Sumador(puntos, multiplicador, conversorJuego.convertirJuego(juego), nombre));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeTarots;
    }

    public ArrayList<Tarot> leerTarotsSinJson(JSONArray tarots){
        ArrayList<Tarot> listaDeTarots = new ArrayList<>();
        for (Object obj : tarots) {
            JSONObject cartaJson = (JSONObject) obj;
            JSONObject efectosJson = (JSONObject) cartaJson.get("efecto");
            int puntos = ((Long) efectosJson.get("puntos")).intValue();
            double multiplicador = ((Number) efectosJson.get("multiplicador")).doubleValue();
            String nombre = (String) cartaJson.get("nombre");
            String sobre = (String) cartaJson.get("sobre");
            String juego = (String)cartaJson.get("ejemplar");
            if (sobre.equals("carta")) {
                if (puntos == 1) {
                    listaDeTarots.add(new CambiadorDeMultiplicador(multiplicador, nombre));
                } else {
                    listaDeTarots.add(new CambiadorDePuntos(puntos, nombre));
                }
            } else if (sobre.equals("mano")) {
                listaDeTarots.add(new Sumador(puntos, multiplicador, conversorJuego.convertirJuego(juego), nombre));
            }
        }
        return listaDeTarots;
    }
}
