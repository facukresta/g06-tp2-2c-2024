package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.Corazon;
import edu.fiuba.algo3.modelo.naipes.carta.Pica;
import edu.fiuba.algo3.modelo.naipes.carta.Trebol;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
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


    @Test
    public void test07AUnJuegoEscaleraRealSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(9, new EscaleraReal());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(150,9);
        // Act
        Juego EscaleraReal = Juego.chequearJuego(new ArrayList<>(List.of( new Carta(1, new Trebol()),
                new Carta(10, new Trebol()), new Carta(11, new Trebol()),
                new Carta(12, new Trebol()), new Carta(13, new Trebol()) )));
        Puntaje puntajeObtenido = EscaleraReal.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(10, new Trebol()), new Carta(11, new Trebol()),
                new Carta(12, new Trebol()), new Carta(13, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new EscaleraReal());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test08AUnJuegoEscaleraRealSeLeAplicaUnTarotCambiadorDeMultiplicadorParaParNoLoModifica() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(5, new Escalera());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(150,8);
        // Act
        Juego EscaleraReal = Juego.chequearJuego(new ArrayList<>(List.of( new Carta(1, new Trebol()),
                new Carta(10, new Trebol()), new Carta(11, new Trebol()),
                new Carta(12, new Trebol()), new Carta(13, new Trebol()) )));
        Puntaje puntajeObtenido = EscaleraReal.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(10, new Trebol()), new Carta(11, new Trebol()),
                new Carta(12, new Trebol()), new Carta(13, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test09AUnJuegoEscaleraRealSeLeAplicaUnTarotCambiadorDePuntosDeEscaleraRealDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(110, new EscaleraReal());
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(160,8);
        // Act
        Juego EscaleraReal = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(10, new Trebol()), new Carta(11, new Trebol()),
                new Carta(12, new Trebol()), new Carta(13, new Trebol()))));
        Puntaje puntajeObtenido = EscaleraReal.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(10, new Trebol()), new Carta(11, new Trebol()),
                new Carta(12, new Trebol()), new Carta(13, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new EscaleraReal());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test10AUnJuegoEscaleraRealSeLeAplicaUnTarotCambiadorDePuntosDeParDeOchoPuntosNoDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new Par());
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(150,8);
        // Act
        Juego EscaleraReal = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(10, new Trebol()), new Carta(11, new Trebol()),
                new Carta(12, new Trebol()), new Carta(13, new Trebol()))));
        Puntaje puntajeObtenido = EscaleraReal.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(10, new Trebol()), new Carta(11, new Trebol()),
                new Carta(12, new Trebol()), new Carta(13, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11AUnJuegoEscaleraRealSeLeAplicaUnTarotSumadorDeEscaleraRealDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new EscaleraReal());
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(158,10);
        // Act
        Juego EscaleraReal = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(10, new Trebol()), new Carta(11, new Trebol()),
                new Carta(12, new Trebol()), new Carta(13, new Trebol()))));
        Puntaje puntajeObtenido = EscaleraReal.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(10, new Trebol()), new Carta(11, new Trebol()),
                new Carta(12, new Trebol()), new Carta(13, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new EscaleraReal());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test12AUnJuegoEscaleraRealSeLeAplicaUnTarotSumadorDeParDeOchoPuntosNoSeDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new Escalera());
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(150,8);
        // Act
        Juego EscaleraReal = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(10, new Trebol()), new Carta(11, new Trebol()),
                new Carta(12, new Trebol()), new Carta(13, new Trebol()))));
        Puntaje puntajeObtenido = EscaleraReal.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Trebol()),
                new Carta(10, new Trebol()), new Carta(11, new Trebol()),
                new Carta(12, new Trebol()), new Carta(13, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
