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
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(10, new Pica()),
                new CartaInglesa(10, new Trebol())));
        // Arrange
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(100, 7);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test02UnaInstanciaDePokerSiUnaListaDeCartasConCincoCartasEsPoker() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(1, new Pica()), new CartaInglesa(1, new Diamante()),
                new CartaInglesa(1, new Trebol()), new CartaInglesa(5, new Corazon())));
        // Arrange
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(105, 7);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test03UnaInstanciaDePokerReconoceQueNoEsPokerAunqueHayaCuatroPares() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(1, new Pica()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(3, new Diamante()), new CartaInglesa(2, new Trebol()),
                new CartaInglesa(2, new Diamante()), new CartaInglesa(9, new Diamante()),
                new CartaInglesa(9, new Corazon())));
        // Arrange
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraPoker = new Puntaje(108, 7);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertFalse(esperadoSiFueraPoker.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test04UnaInstanciaDePokerSiUnaListaDeCartasConMasDeCincoCartasEsPoker() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Diamante()), new CartaInglesa(10, new Pica()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(12, new Trebol()),
                new CartaInglesa(6, new Corazon()), new CartaInglesa(7, new Corazon())));
        // Arrange
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraPoker = new Puntaje(123, 7);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperadoSiFueraPoker.tenesMismoPuntaje(obtenido));    }
    @Test
    public void test05UnaInstanciaDePokerDeUnaListaConCincoCartasDeDiferenteValorNoEsPoker() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(1, new Pica()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(3, new Diamante()), new CartaInglesa(2, new Trebol())));
        // Arrange
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraPoker = new Puntaje(88, 7);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertFalse(esperadoSiFueraPoker.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test06UnaInstanciaDePokerDeUnaListaDeCartasConCuatroCartaDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(10, new Pica()),
                new CartaInglesa(10, new Diamante())));
        // Arrange
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraPoker = new Puntaje(100, 7);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperadoSiFueraPoker.tenesMismoPuntaje(obtenido));    }
    @Test
    public void test07UnaInstanciaDePokerDeUnaListaDeCartasConCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(2, new Diamante()),
                new CartaInglesa(2, new Pica()), new CartaInglesa(2, new Corazon()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(5, new Corazon())));
        // Arrange
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraPoker = new Puntaje(73, 7);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperadoSiFueraPoker.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test08UnaInstanciaDePokerDeUnaListaDeCartasMasDeCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(2, new Corazon()), new CartaInglesa(5, new Trebol()),
                new CartaInglesa(5, new Diamante()), new CartaInglesa(5, new Corazon()),
                new CartaInglesa(5, new Pica()), new CartaInglesa(7, new Trebol())));
        // Arrange
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraPoker = new Puntaje(99, 7);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperadoSiFueraPoker.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test07AUnJuegoPokerSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Poker());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(76,8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4,
                new Corazon()), new CartaInglesa(4, new Diamante()), new CartaInglesa(4, new Trebol())));
        Juego Poker = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Poker.puntuarMano();
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()),
                new CartaInglesa(4, new Corazon()), new CartaInglesa(4, new Diamante()), new CartaInglesa(4, new Trebol())));
        Juego Poker = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Poker.puntuarMano();
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()),
                new CartaInglesa(4, new Diamante()), new CartaInglesa(4, new Trebol())));
        Juego Poker = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Poker.puntuarMano();
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()),
                new CartaInglesa(4, new Diamante()), new CartaInglesa(4, new Trebol())));
        Juego Poker = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Poker.puntuarMano();
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()),
                new CartaInglesa(4, new Diamante()), new CartaInglesa(4, new Trebol())));
        Juego Poker = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Poker.puntuarMano();
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()),
                new CartaInglesa(4, new Diamante()), new CartaInglesa(4, new Trebol())));
        Juego Poker = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Poker.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
