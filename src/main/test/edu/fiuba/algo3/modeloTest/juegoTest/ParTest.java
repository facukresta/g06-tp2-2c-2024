package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ParTest {
    @Test
    public void test01UnaInstanciaDeParSiUnaListaDeCartasConDosCartasEsPar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(30, 2);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test02UnaInstanciaDeParSiUnaListaDeCartasConCincoCartasEsPar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(42, 2);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test03SiUnaListaDeCartasConCincoCartasEsTrioAunqueHayaParPuntuaraTrio() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(1, new Diamante()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraPar = new Puntaje(49, 2);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertFalse(esperadoSiFueraPar.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test04SiUnaListaDeCartasConCincoCartasEsDobleParAunqueHayaParPuntuaraDoblePar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(3, new Corazon()),
                new Carta(3, new Diamante()), new Carta(6, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraPar = new Puntaje(42, 2);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertFalse(esperadoSiFueraPar.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test05SiUnaListaDeCartasVaciaNoEsPar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>();
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(0, 1);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test06UnaInstanciaDeParSiUnaListaDeCartasConMasDeCincoCartasEsPar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Diamante()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon()),
                new Carta(6, new Corazon()), new Carta(7, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(55, 2);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test07UnaInstanciaDeParDeUnaListaConCincoCartasDeDiferenteValorNoEsPar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Pica()), new Carta(3, new Corazon()),
                new Carta(4, new Diamante()), new Carta(5, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraPar = new Puntaje(34, 2);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertFalse(esperadoSiFueraPar.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test12UnaInstanciaDeParSiUnaListaDeCartasVaciaNoDevueleElValorBaseDePar() {
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
    public void test07AUnJuegoParSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Par());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(18,8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4,
                new Corazon())));
        Juego Par = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Par.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test08AUnJuegoParSeLeAplicaUnTarotCambiadorDeMultiplicadorParaParNoLoModifica() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Escalera());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(18,2);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(4, new Pica()),
                new Carta(4, new Corazon())));
        Juego Par = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Par.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test09AUnJuegoParSeLeAplicaUnTarotCambiadorDePuntosDeParDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(12, new Par());
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(20,2);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon())));
        Juego Par = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Par.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test10AUnJuegoParSeLeAplicaUnTarotCambiadorDePuntosDeDobleParDeOchoPuntosNoDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new DoblePar());
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(18,2);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon())));
        Juego Par = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Par.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new DoblePar());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11AUnJuegoParSeLeAplicaUnTarotSumadorDeParDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(2,2, new Par());
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(20,4);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon())));
        Juego Par = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Par.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test12AUnJuegoParSeLeAplicaUnTarotSumadorDeParDeOchoPuntosNoSeDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new Escalera());
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(18,2);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon())));
        Juego Par = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Par.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
