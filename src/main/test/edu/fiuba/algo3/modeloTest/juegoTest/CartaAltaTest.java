package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.Corazon;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CartaAltaTest {
    @Test
    public void test01UnaInstanciaDeCartaAltaSiUnaListaDeCartasConUnaCartaEsCartaAlta() {
        // Arrange
        CartaAlta juegoCartaAlta = new CartaAlta();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon())));
        // Act
        boolean resulatado = juegoCartaAlta.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test02UnaInstanciaDeCartaAltaSiUnaListaDeCartasConCincoCartasEsCartaAlta() {
        // Arrange
        CartaAlta juegoCartaAlta = new CartaAlta();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Corazon()), new Carta(3, new Corazon()),
                        new Carta(4, new Corazon()), new Carta(5, new Corazon())));
        // Act
        boolean resulatado = juegoCartaAlta.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test03UnaInstanciaDeCartaAltaSiUnaListaDeCartasVaciaNoEsCartaAlta() {
        // Arrange
        CartaAlta juegoCartaAlta = new CartaAlta();
        ArrayList<Carta> cartas = new ArrayList<>();
        // Act
        boolean resulatado = juegoCartaAlta.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test04UnaInstanciaDeCartaAltaSiUnaListaDeCartasConMasDeCincoCartasEsCartaAlta() {
        // Arrange
        CartaAlta juegoCartaAlta = new CartaAlta();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Corazon()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon()),
                new Carta(6, new Corazon()), new Carta(7, new Corazon())));
        // Act
        boolean resulatado = juegoCartaAlta.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test05UnaInstanciaDeCartaAltaDeUnaListaDeCartasConUnaCartaDevuelveElValorCorrecto() {
        // Arrange
        CartaAlta juegoCartaAlta = new CartaAlta();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon())));
        Puntaje valorEsperado = new Puntaje(15, 1);
        // Act
        Puntaje valorObtenido = juegoCartaAlta.puntuarMano(cartas);
        // Assert
        assertTrue(valorObtenido.tenesMismoPuntaje(valorEsperado));
    }
    @Test
    public void test06UnaInstanciaDeCartaAltaDeUnaListaDeCartasConCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        CartaAlta juegoCartaAlta = new CartaAlta();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Corazon()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon())));
        Puntaje valorEsperado = new Puntaje(29, 1);
        // Act
        Puntaje valorObtenido = juegoCartaAlta.puntuarMano(cartas);
        // Assert
        assertTrue(valorObtenido.tenesMismoPuntaje(valorEsperado));
    }
    @Test
    public void test07UnaInstanciaDeCartaAltaSiUnaListaDeCartasVaciaDevueleElValorBaseDeCartaAlta() {
        // Arrange
        CartaAlta juegoCartaAlta = new CartaAlta();
        ArrayList<Carta> cartas = new ArrayList<>();
        Puntaje valorEsperado = new Puntaje(5, 1);
        // Act
        Puntaje valorObtenido = juegoCartaAlta.puntuarMano(cartas);
        // Assert
        assertTrue(valorObtenido.tenesMismoPuntaje(valorEsperado));
    }
    @Test
    public void test08UnaInstanciaDeCartaAltaDeUnaListaDeCartasMasDeCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        CartaAlta juegoCartaAlta = new CartaAlta();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Corazon()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon()),
                new Carta(6, new Corazon()), new Carta(7, new Corazon())));
        Puntaje valorEsperado = new Puntaje(42, 1);
        // Act
        Puntaje valorObtenido = juegoCartaAlta.puntuarMano(cartas);
        // Assert
        assertTrue(valorObtenido.tenesMismoPuntaje(valorEsperado));
    }
}
