package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;

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


    @Test
    public void test07AUnJuegoEscaleraDeColorSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(9, new EscaleraDeColor());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(124,9);
        // Act
        Juego EscaleraDeColor = Juego.chequearJuego(new ArrayList<>(List.of( new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()) )));
        Puntaje puntajeObtenido = EscaleraDeColor.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new EscaleraDeColor());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test08AUnJuegoEscaleraDeColorSeLeAplicaUnTarotCambiadorDeMultiplicadorParaParNoLoModifica() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(5, new Escalera());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(124,8);
        // Act
        Juego EscaleraDeColor = Juego.chequearJuego(new ArrayList<>(List.of( new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()) )));
        Puntaje puntajeObtenido = EscaleraDeColor.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test09AUnJuegoEscaleraDeColorSeLeAplicaUnTarotCambiadorDePuntosDeEscaleraDeColorDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(12, new EscaleraDeColor());
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(36,8);
        // Act
        Juego EscaleraDeColor = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()))));
        Puntaje puntajeObtenido = EscaleraDeColor.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new EscaleraDeColor());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test10AUnJuegoEscaleraDeColorSeLeAplicaUnTarotCambiadorDePuntosDeParDeOchoPuntosNoDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new Par());
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(124,8);
        // Act
        Juego EscaleraDeColor = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()))));
        Puntaje puntajeObtenido = EscaleraDeColor.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11AUnJuegoEscaleraDeColorSeLeAplicaUnTarotSumadorDeEscaleraDeColorDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new EscaleraDeColor());
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(132,10);
        // Act
        Juego EscaleraDeColor = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()))));
        Puntaje puntajeObtenido = EscaleraDeColor.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new EscaleraDeColor());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test12AUnJuegoEscaleraDeColorSeLeAplicaUnTarotSumadorDeParDeOchoPuntosNoSeDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new Escalera());
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(124,8);
        // Act
        Juego EscaleraDeColor = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()))));
        Puntaje puntajeObtenido = EscaleraDeColor.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
