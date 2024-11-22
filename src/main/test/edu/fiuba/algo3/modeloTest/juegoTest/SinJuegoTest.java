package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SinJuegoTest {
    @Test
    public void test01UnaInstanciaDeSinJuegoSiUnaListaDeCartasConNingunaCartaEsSinJuego() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of());
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(0, 1);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test02UnaInstanciaDeSinJuegoSiUnaListaDeCartasConUnaCartaNoEsSinJuego() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(15, 1);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test03UnaInstanciaDeSinJuegoDeUnaListaDeCartasVaciaDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of());
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(0, 1);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
}
