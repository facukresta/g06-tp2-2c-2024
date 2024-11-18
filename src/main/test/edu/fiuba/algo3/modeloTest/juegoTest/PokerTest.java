package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PokerTest {
    @Test
    public void test01UnaInstanciaDePokerSiUnaListaDeCartasConCuatroCartasEsPoker() {
        // Arrange
        Poker juegoPoker = new Poker();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Trebol()), new Carta(10, new Pica()),
                new Carta(10, new Trebol())));
        // Act
        boolean resulatado = juegoPoker.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test02UnaInstanciaDePokerSiUnaListaDeCartasConCincoCartasEsPoker() {
        // Arrange
        Poker juegoPoker = new Poker();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(1, new Diamante()),
                new Carta(1, new Trebol()), new Carta(5, new Corazon())));
        // Act
        boolean resulatado = juegoPoker.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test03UnaInstanciaDePokerReconoceQueNoEsPokerAunqueHayaCuatroPares() {
        // Arrange
        Poker juegoPoker = new Poker();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(3, new Corazon()),
                new Carta(3, new Diamante()), new Carta(2, new Trebol()),
                new Carta(2, new Diamante()), new Carta(9, new Diamante()),
                new Carta(9, new Corazon())));
        // Act
        boolean resulatado = juegoPoker.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test04UnaInstanciaDePokerSiUnaListaDeCartasVaciaNoEsPoker() {
        // Arrange
        Poker juegoPoker = new Poker();
        ArrayList<Carta> cartas = new ArrayList<>();
        // Act
        boolean resulatado = juegoPoker.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test05UnaInstanciaDePokerSiUnaListaDeCartasConTresCartaNoEsPoker() {
        // Arrange
        Poker juegoPoker = new Poker();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(4, new Corazon()),
                new Carta(4, new Trebol()), new Carta(4, new Diamante())));
        // Act
        boolean resulatado = juegoPoker.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test06UnaInstanciaDePokerSiUnaListaDeCartasConMasDeCincoCartasEsPoker() {
        // Arrange
        Poker juegoPoker = new Poker();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Diamante()), new Carta(10, new Pica()),
                new Carta(10, new Trebol()), new Carta(12, new Trebol()),
                new Carta(6, new Corazon()), new Carta(7, new Corazon())));
        // Act
        boolean resulatado = juegoPoker.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test07UnaInstanciaDePokerDeUnaListaConCincoCartasDeDiferenteValorNoEsPoker() {
        // Arrange
        Poker juegoPoker = new Poker();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Pica()), new Carta(3, new Corazon()),
                new Carta(4, new Diamante()), new Carta(5, new Trebol())));
        // Act
        boolean resulatado = juegoPoker.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test08UnaInstanciaDePokerDeUnaListaDeCartasConCuatroCartaDevuelveElValorCorrecto() {
        // Arrange
        Poker juegoPoker = new Poker();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Trebol()), new Carta(10, new Pica()),
                new Carta(10, new Diamante())));
        Puntaje puntajeEsperado = new Puntaje(100,7);
        // Act
        Puntaje puntajeObtenido = juegoPoker.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test09UnaInstanciaDePokerDeUnaListaDeCartasConCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        Poker juegoPoker = new Poker();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(2, new Diamante()),
                new Carta(2, new Pica()), new Carta(2, new Corazon()),
                new Carta(2, new Trebol()), new Carta(5, new Corazon())));
        Puntaje puntajeEsperado = new Puntaje(73,7);
        // Act
        Puntaje puntajeObtenido = juegoPoker.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test10UnaInstanciaDePokerSiUnaListaDeCartasVaciaDevueleElValorBaseDePoker() {
        // Arrange
        Poker juegoPoker = new Poker();
        ArrayList<Carta> cartas = new ArrayList<>();
        Puntaje puntajeEsperado = new Puntaje(60,7);
        // Act
        Puntaje puntajeObtenido = juegoPoker.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test11UnaInstanciaDePokerDeUnaListaDeCartasMasDeCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        Poker juegoPoker = new Poker();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Corazon()), new Carta(5, new Trebol()),
                new Carta(5, new Diamante()), new Carta(5, new Corazon()),
                new Carta(5, new Pica()), new Carta(7, new Trebol())));
        Puntaje puntajeEsperado = new Puntaje(99,7);
        // Act
        Puntaje puntajeObtenido = juegoPoker.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test07AUnJuegoPokerSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Poker());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(76,8);
        // Act
        Juego Poker = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4,
                new Corazon()), new Carta(4, new Diamante()), new Carta(4, new Trebol()))));
        Puntaje puntajeObtenido = Poker.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()),
                new Carta(4, new Corazon()), new Carta(4, new Diamante()), new Carta(4, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new Poker());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test08AUnJuegoPokerSeLeAplicaUnTarotCambiadorDeMultiplicadorPokeraPokerNoLoModifica() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Escalera());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(76,7);
        // Act
        Juego Poker = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()),
                new Carta(4, new Corazon()), new Carta(4, new Diamante()), new Carta(4, new Trebol()))));
        Puntaje puntajeObtenido = Poker.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()),
                new Carta(4, new Corazon()), new Carta(4, new Diamante()), new Carta(4, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test09AUnJuegoPokerSeLeAplicaUnTarotCambiadorDePuntosDePokerDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(12, new Poker());
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(28,7);
        // Act
        Juego Poker = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()),
                new Carta(12, new Pica()), new Carta(4, new Diamante()), new Carta(4, new Trebol()))));
        Puntaje puntajeObtenido = Poker.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()),
                new Carta(4, new Corazon()), new Carta(4, new Diamante()), new Carta(4, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new Poker());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test10AUnJuegoPokerSeLeAplicaUnTarotCambiadorDePuntosDeDoblePokerDeOchoPuntosNoDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new DoblePar());
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(76,7);
        // Act
        Juego Poker = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()),
                new Carta(4, new Diamante()), new Carta(4, new Trebol()))));
        Puntaje puntajeObtenido = Poker.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()),
                new Carta(4, new Diamante()), new Carta(4, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new DoblePar());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11AUnJuegoPokerSeLeAplicaUnTarotSumadorDePokerDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(2,2, new Poker());
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(78,9);
        // Act
        Juego Poker = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()),
                new Carta(4, new Diamante()), new Carta(4, new Trebol()))));
        Puntaje puntajeObtenido = Poker.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()),
                new Carta(4, new Diamante()), new Carta(4, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new Poker());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test12AUnJuegoPokerSeLeAplicaUnTarotSumadorDePokerDeOchoPuntosNoSeDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new Escalera());
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(76,7);
        // Act
        Juego Poker = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()),
                new Carta(4, new Diamante()), new Carta(4, new Trebol()))));
        Puntaje puntajeObtenido = Poker.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()),
                new Carta(4, new Diamante()), new Carta(4, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
