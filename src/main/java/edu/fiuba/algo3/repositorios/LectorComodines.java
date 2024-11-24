package edu.fiuba.algo3.repositorios;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.comodin.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class LectorComodines {
    private final ConversorJuego conversorJuego = new ConversorJuego();
    private final AbridorDeJson abridorDeJson = new AbridorDeJson();

    private ArrayList<Modificador> leerComodin(JSONArray comodines) {
        ArrayList<Modificador> listaDeComodines = new ArrayList<>();
        for (Object obj : comodines) {
            JSONObject comodin = (JSONObject) obj;
            String activador = comodin.get("activacion").toString();
            int probabilidad = 1;
            String juego = "sin juego";
            if (activador.equals("1 en")) {
                JSONObject activacion = (JSONObject) comodin.get("activacion");
                probabilidad = ((Long) activacion.get("1 en")).intValue();
            }
            if (activador.equals("Mano Jugada")) {
                JSONObject activacion = (JSONObject) comodin.get("activacion");
                juego = (String) activacion.get("Mano Jugada");
            }
            JSONObject efectosJson = (JSONObject) comodin.get("efecto");
            int puntos = ((Long) efectosJson.get("puntos")).intValue();
            double multiplicador = ((Number) efectosJson.get("multiplicador")).doubleValue();
            if (activador.equals("descarte")) {
                if (puntos == 1) {
                    listaDeComodines.add(new SumaMultiplicadorDescarte(multiplicador, new Aleatorio(probabilidad)));
                } else {
                    listaDeComodines.add(new SumaPuntosDescarte(puntos, new Aleatorio(probabilidad)));
                }
            } else {
                if (puntos == 1) {
                    listaDeComodines.add(new SumaMultiplicador(multiplicador, conversorJuego.convertirJuego(juego), new Aleatorio(probabilidad)));
                } else {
                    listaDeComodines.add(new SumaPuntos(puntos, conversorJuego.convertirJuego(juego), new Aleatorio(probabilidad)));
                }
            }
        }
        return listaDeComodines;
    }

    public ArrayList<Modificador> leerComodines(){
        ArrayList<Modificador> listaDeComodines = new ArrayList<>();
        try {
            JSONObject jsonObject = abridorDeJson.abrirJson("comodines");
            JSONArray comodinesPuntaje = (JSONArray) ((JSONObject) jsonObject.get("Al Puntaje")).get("comodines");
            JSONArray comodinesJuego = (JSONArray) ((JSONObject) jsonObject.get("Bonus por Mano Jugada")).get("comodines");
            JSONArray comodinesDescarte = (JSONArray) ((JSONObject) jsonObject.get("Bonus por Descarte")).get("comodines");
            JSONArray comodinesAleatorio = (JSONArray) ((JSONObject) jsonObject.get("Chance de activarse aleatoriamente")).get("comodines");
            JSONArray comodinesCombinacion = (JSONArray) ((JSONObject) jsonObject.get("Combinación")).get("comodines");
            listaDeComodines.addAll(leerComodin(comodinesPuntaje));
            listaDeComodines.addAll(leerComodin(comodinesJuego));
            listaDeComodines.addAll(leerComodin(comodinesDescarte));
            listaDeComodines.addAll(leerComodin(comodinesAleatorio));
            for (Object obj : comodinesCombinacion) {
                JSONObject comodin = (JSONObject) obj;
                JSONArray comodinesDentro = (JSONArray) comodin.get("comodines");
                ArrayList<Modificador> comodinesPequenios = leerComodin(comodinesDentro);
                MultiComodin multiComodin = new MultiComodin();
                for (Modificador modificador : comodinesPequenios) {
                    multiComodin.componerComodin((Comodin) modificador);
                }
                listaDeComodines.add(multiComodin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeComodines;
    }
    
}
