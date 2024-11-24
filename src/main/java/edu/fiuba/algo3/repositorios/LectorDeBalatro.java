package edu.fiuba.algo3.repositorios;

import edu.fiuba.algo3.modelo.Comprable;
import edu.fiuba.algo3.modelo.comodin.Modificador;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.tienda.Tienda;
import edu.fiuba.algo3.modelo.tienda.TiendaBalatro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class LectorDeBalatro {
    private final AbridorDeJson abridorDeJson = new AbridorDeJson();
    private final LectorComodines lectorComodines = new LectorComodines();
    private final LectorDeCartas lectorDeCartas = new LectorDeCartas();
    private final LectorDeTarots lectorDeTarots = new LectorDeTarots();
    public ArrayList<Ronda> leerBalatro(Mazo mazo){
        ArrayList<Ronda> listaDeRondas = new ArrayList<>();
        try {
            JSONObject jsonObject = abridorDeJson.abrirJson("balatro");
            JSONArray rondas = (JSONArray) jsonObject.get("rondas");
            for (Object obj : rondas) {
                JSONObject rondaJson = (JSONObject) obj;
                int manos = ((Long) rondaJson.get("manos")).intValue();
                int descartes = ((Long) rondaJson.get("descartes")).intValue();
                int puntajeABatir = ((Long) rondaJson.get("puntajeABatir")).intValue();
                JSONObject tiendaJson = (JSONObject) rondaJson.get("tienda");
                JSONArray comodinesJson = (JSONArray) tiendaJson.get("comodines");
//                ArrayList<Modificador> comodines = lectorComodines.leerComodinesSinJson((JSONArray) tiendaJson.get("comodines"));
                ArrayList<Tarot> tarots = lectorDeTarots.leerTarotsSinJson((JSONArray) tiendaJson.get("tarots"));
                ArrayList<Carta> cartas = lectorDeCartas.leerCartaSinJson((JSONObject) tiendaJson.get("cartas"));
                ArrayList<Comprable> productos = new ArrayList<>();
                productos.addAll(tarots);
                productos.addAll(cartas);
//                prodcutos.addAll(tarots);
                rondas.add(new Ronda(new Puntaje(puntajeABatir, 1), manos, descartes, mazo, new TiendaBalatro(productos)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeRondas;
    }
}
