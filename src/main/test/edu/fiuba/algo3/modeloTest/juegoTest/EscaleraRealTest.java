package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.Corazon;
import edu.fiuba.algo3.modelo.naipes.carta.Pica;
import edu.fiuba.algo3.modelo.naipes.carta.Trebol;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EscaleraRealTest {
    @Test
    public void test01UnaInstanciaDeEscaleraRealSiUnaListaDeCartasConCincoCartasEsEscaleraReal() {
        // Arrange
        EscaleraReal juegoEscaleraReal = new EscaleraReal();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Trebol()),
                new Carta(10, new Trebol()), new Carta(11, new Trebol()),
                new Carta(12, new Trebol()), new Carta(13, new Trebol())));
        // Act
        boolean resulatado = juegoEscaleraReal.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test02UnaInstanciaDeEscaleraRealSiUnaListaDeCartasConSeisCartasNoEsEscaleraReal() {
        // Arrange
        EscaleraReal juegoEscaleraReal = new EscaleraReal();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Trebol()),
                new Carta(9, new Trebol()), new Carta(10, new Trebol()),
                new Carta(11, new Trebol()), new Carta(12, new Trebol()),
                new Carta(13, new Trebol())));
        // Act
        boolean resulatado = juegoEscaleraReal.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test03UnaInstanciaDeEscaleraRealReconoceQueNoEsEscaleraReal() {
        // Arrange
        EscaleraReal juegoEscaleraReal = new EscaleraReal();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(10, new Corazon()), new Carta(11, new Corazon()),
                new Carta(12, new Corazon()), new Carta(13, new Trebol())));
        // Act
        boolean resulatado = juegoEscaleraReal.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test04UnaInstanciaDeEscaleraRealSiUnaListaDeCartasVaciaNoEsEscaleraReal() {
        // Arrange
        EscaleraReal juegoEscaleraReal = new EscaleraReal();
        ArrayList<Carta> cartas = new ArrayList<>();
        // Act
        boolean resulatado = juegoEscaleraReal.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test05UnaInstanciaDeEscaleraRealDeUnaListaDeCartasConCincoCartasDelMismoColorYEnEscaleraDevuelveElValorCorrecto() {
        // Arrange
        EscaleraReal juegoEscaleraReal = new EscaleraReal();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Pica()),
                new Carta(13, new Pica()), new Carta(11, new Pica()),
                new Carta(12, new Pica()), new Carta(1, new Pica())));
        Puntaje puntajeEsperado = new Puntaje(150,8);
        // Act
        Puntaje puntajeObtenido = juegoEscaleraReal.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test06UnaInstanciaDeEscaleraRealSiUnaListaDeCartasVaciaDevueleElValorBaseDeEscaleraReal() {
        // Arrange
        EscaleraReal juegoEscaleraReal = new EscaleraReal();
        ArrayList<Carta> cartas = new ArrayList<>();
        Puntaje puntajeEsperado = new Puntaje(100,8);
        // Act
        Puntaje puntajeObtenido = juegoEscaleraReal.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test07UnaInstanciaDeEscaleraRealDeUnaListaDeCartasConCincoCartasDelMismoColorYNoEnEscaleraRealSabeReconocerQueNoEsEscaleraReal() {
        // Arrange
        EscaleraReal juegoEscaleraReal = new EscaleraReal();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Pica()),
                new Carta(11, new Pica()), new Carta(12, new Pica()),
                new Carta(13, new Pica()), new Carta(9, new Pica())));
        // Act
        boolean resulatado = juegoEscaleraReal.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
}
