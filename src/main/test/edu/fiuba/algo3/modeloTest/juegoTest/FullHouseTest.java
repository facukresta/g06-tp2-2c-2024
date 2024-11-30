package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FullHouseTest {
    @Test
    public void test01UnaInstanciaDeFullHouseSiUnaListaDeCartasConCincoCartasEsFullHouse() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(10, new Pica()),
                new CartaInglesa(8, new Trebol()), new CartaInglesa(8, new Pica())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(86, 4);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test02UnaInstanciaDeFullHouseReconoceQueEsFullHouseSiHay5Cartas() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Corazon()),
                new CartaInglesa(1, new Pica()), new CartaInglesa(1, new Diamante()),
                new CartaInglesa(3, new Diamante()), new CartaInglesa(3, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(76, 4);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test03UnaInstanciaDeFullHouseSiUnaListaDeCartasVaciaNoEsFullHouse() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>();
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(0, 1);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test04UnaInstanciaDeFullHouseSiUnaListaDeCartasConCuatroCartaNoEsFullHouse() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Corazon()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(7, new Corazon()),
                new CartaInglesa(7, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraFullHouse = new Puntaje(62, 4);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertFalse(esperadoSiFueraFullHouse.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test05UnaInstanciaDeFullHouseDeUnaListaConCincoCartasDeDiferenteValorNoEsFullHouse() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Corazon()),
                new CartaInglesa(2, new Pica()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Diamante()), new CartaInglesa(5, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraFullHouse = new Puntaje(64, 4);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertFalse(esperadoSiFueraFullHouse.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test06UnaInstanciaDeFullHouseDeUnaListaDeCartasConCincoCartasQueFormanFullHouseDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(10, new Pica()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(4, new Pica())));
        Puntaje puntajeEsperado = new Puntaje(78, 4);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje puntajeObtenido = juego.puntuarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test07UnaInstanciaDeFullHouseSiUnaListaDeCartasVaciaNoDevueleElValorBaseDeFullHouse() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>();
        Puntaje puntajeEsperado = new Puntaje(0, 1);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje puntajeObtenido = juego.puntuarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }


    @Test
    public void test07AUnJuegoFullHouseSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new FullHouse(), "");
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(78, 8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4,
                        new Corazon()), new CartaInglesa(12, new Pica()), new CartaInglesa(12, new Diamante()), new CartaInglesa(12, new Corazon())));
        Juego FullHouse = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = FullHouse.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new FullHouse());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test08AUnJuegoFullHouseSeLeAplicaUnTarotCambiadorDeMultiplicadorParaParNoLoModifica() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Escalera(), "");
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(78, 4);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()),
                new CartaInglesa(4, new Corazon()), new CartaInglesa(12, new Pica()),
                new CartaInglesa(12, new Diamante()), new CartaInglesa(12, new Corazon())));
        Juego FullHouse = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = FullHouse.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test09AUnJuegoFullHouseSeLeAplicaUnTarotCambiadorDePuntosDeFullHouseDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(100, new FullHouse(), "");
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(138, 4);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()),
                new CartaInglesa(4, new Corazon()), new CartaInglesa(12, new Pica()),
                new CartaInglesa(12, new Diamante()), new CartaInglesa(12, new Corazon())));
        Juego FullHouse = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = FullHouse.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new FullHouse());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test10AUnJuegoFullHouseSeLeAplicaUnTarotCambiadorDePuntosDeParDeOchoPuntosNoDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new Par(), "");
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(78, 4);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()), new CartaInglesa(12, new Pica()),
                new CartaInglesa(12, new Diamante()), new CartaInglesa(12, new Corazon())));
        Juego FullHouse = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = FullHouse.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11AUnJuegoFullHouseSeLeAplicaUnTarotSumadorDeFullHouseDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8, 2, new FullHouse(), "");
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(86, 6);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()), new CartaInglesa(12, new Pica()),
                new CartaInglesa(12, new Diamante()), new CartaInglesa(12, new Corazon())));
        Juego FullHouse = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = FullHouse.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new FullHouse());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test12AUnJuegoFullHouseSeLeAplicaUnTarotSumadorDeParDeOchoPuntosNoSeDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8, 2, new Escalera(), "");
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(78, 4);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()), new CartaInglesa(12, new Pica()),
                new CartaInglesa(12, new Diamante()), new CartaInglesa(12, new Corazon())));
        Juego FullHouse = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = FullHouse.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
