package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import edu.fiuba.algo3.repositorios.LectorDeBalatro;
import edu.fiuba.algo3.repositorios.LectorDeCartas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public class IniciarJuegoEventHandler implements EventHandler<ActionEvent>  {
    private Mazo mazo;
    private ArrayList<Ronda> rondas;
    private Runnable action;
    private LectorDeBalatro lectorDeBalatro = new LectorDeBalatro();
    private Runnable pararMusica;
    private AudioClip sonido = new AudioClip(getClass().getResource("/sonido/jugar.mp3").toExternalForm());

    public IniciarJuegoEventHandler (ArrayList<Ronda> rondas, Mazo mazo, Runnable action, Runnable pararMusica) {
        this.mazo = mazo;
        this.rondas = rondas;
        this.action = action;
        this.pararMusica = pararMusica;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        this.sonido.play();
        LectorDeCartas lectorDeCartas = new LectorDeCartas();
        ArrayList<Carta> cartas = lectorDeCartas.leerCartas();
        this.mazo.vaciarMazo();
        this.mazo.agregarCartas(cartas);

        rondas.clear();
        rondas.addAll(this.lectorDeBalatro.leerBalatro(this.mazo));
        this.pararMusica.run();
        action.run();
    }
}
