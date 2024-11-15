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

public class EscaleraDeColorTest {
    @Test
    public void test01UnaInstanciaDeEscaleraDeColorSiUnaListaDeCartasConCincoCartasEsEscaleraDeColor() {
        // Arrange
        EscaleraDeColor juegoEscaleraDeColor = new EscaleraDeColor();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol())));
        // Act
        boolean resulatado = juegoEscaleraDeColor.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test02UnaInstanciaDeEscaleraDeColorSiUnaListaDeCartasConSeisCartasNoEsEscaleraDeColor() {
        // Arrange
        EscaleraDeColor juegoEscaleraDeColor = new EscaleraDeColor();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()),
                new Carta(6, new Trebol())));
        // Act
        boolean resulatado = juegoEscaleraDeColor.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test03UnaInstanciaDeEscaleraDeColorReconoceQueNoEsEscaleraDeColor() {
        // Arrange
        EscaleraDeColor juegoEscaleraDeColor = new EscaleraDeColor();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Corazon()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Trebol())));
        // Act
        boolean resulatado = juegoEscaleraDeColor.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test04UnaInstanciaDeEscaleraDeColorSiUnaListaDeCartasVaciaNoEsEscaleraDeColor() {
        // Arrange
        EscaleraDeColor juegoEscaleraDeColor = new EscaleraDeColor();
        ArrayList<Carta> cartas = new ArrayList<>();
        // Act
        boolean resulatado = juegoEscaleraDeColor.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test05UnaInstanciaDeEscaleraDeColorDeUnaListaDeCartasConCincoCartasDelMismoColorYEnEscaleraDevuelveElValorCorrecto() {
        // Arrange
        EscaleraDeColor juegoEscaleraDeColor = new EscaleraDeColor();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Pica()),
                new Carta(11, new Pica()), new Carta(12, new Pica()),
                new Carta(13, new Pica()), new Carta(9, new Pica())));
        Puntaje puntajeEsperado = new Puntaje(149,8);
        // Act
        Puntaje puntajeObtenido = juegoEscaleraDeColor.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test06UnaInstanciaDeEscaleraDeColorSiUnaListaDeCartasVaciaDevueleElValorBaseDeEscaleraDeColor() {
        // Arrange
        EscaleraDeColor juegoEscaleraDeColor = new EscaleraDeColor();
        ArrayList<Carta> cartas = new ArrayList<>();
        Puntaje puntajeEsperado = new Puntaje(100,8);
        // Act
        Puntaje puntajeObtenido = juegoEscaleraDeColor.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test07UnaInstanciaDeEscaleraDeColorDeUnaListaDeCartasConCincoCartasDelMismoColorYNoEnEscaleraSabeReconocerQueNoEsEscaleraDeColor() {
        // Arrange
        EscaleraDeColor juegoEscaleraDeColor = new EscaleraDeColor();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Pica()),
                new Carta(11, new Pica()), new Carta(12, new Pica()),
                new Carta(13, new Pica()), new Carta(8, new Pica())));
        // Act
        boolean resulatado = juegoEscaleraDeColor.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
}
