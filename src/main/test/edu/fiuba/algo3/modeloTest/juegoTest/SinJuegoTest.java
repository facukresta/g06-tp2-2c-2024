package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.naipes.carta.Corazon;
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
        SinJuego juegoSinJuego = new SinJuego();
        ArrayList<Carta> cartas = new ArrayList<>(List.of());
        // Act
        boolean resulatado = juegoSinJuego.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test02UnaInstanciaDeSinJuegoSiUnaListaDeCartasConUnaCartaNoEsSinJuego() {
        // Arrange
        SinJuego juegoSinJuego = new SinJuego();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon())));
        // Act
        boolean resulatado = juegoSinJuego.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test03UnaInstanciaDeSinJuegoDeUnaListaDeCartasVaciaDevuelveElValorCorrecto() {
        // Arrange
        SinJuego juegoSinJuego = new SinJuego();
        ArrayList<Carta> cartas = new ArrayList<>(List.of());
        Puntaje valorEsperado = new Puntaje(0, 1);
        // Act
        Puntaje valorObtenido = juegoSinJuego.puntuarMano(cartas);
        // Assert
        assertTrue(valorObtenido.tenesMismoPuntaje(valorEsperado));
    }
    @Test
    public void test04UnaInstanciaDeSinJuegoSiUnaListaDeCartasVaciaDevueleElValorBaseDeSinJuego() {
        // Arrange
        SinJuego juegoSinJuego = new SinJuego();
        ArrayList<Carta> cartas = new ArrayList<>();
        Puntaje valorEsperado = new Puntaje(0, 1);
        // Act
        Puntaje valorObtenido = juegoSinJuego.puntuarMano(cartas);
        // Assert
        assertTrue(valorObtenido.tenesMismoPuntaje(valorEsperado));
    }
}
