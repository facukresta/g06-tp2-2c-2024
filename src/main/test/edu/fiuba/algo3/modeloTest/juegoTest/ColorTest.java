package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ColorTest {
    @Test
    public void test01UnaInstanciaDeColorSiUnaListaDeCartasConCincoCartasEsColor() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Trebol()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(6, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(60, 4);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test02UnaInstanciaDeColorSiUnaListaDeCartasConSeisCartasNoEsColor() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Trebol()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol()),
                new CartaInglesa(6, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueseColor = new Puntaje(65, 4);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertFalse(esperadoSiFueseColor.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test03UnaInstanciaDeColorReconoceQueNoEsColor() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Corazon()),
                new CartaInglesa(2, new Corazon()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Corazon()), new CartaInglesa(5, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueseColor = new Puntaje(59, 4);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertFalse(esperadoSiFueseColor.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test04UnaInstanciaDeColorSiUnaListaDeCartasVaciaNoEsColor() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>();
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueseColor = new Puntaje(0, 1);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperadoSiFueseColor.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test05UnaInstanciaDeColorDeUnaListaDeCartasConCincoCartasDelMismoColorDevuelveElValorCorrecto() {
        // Arrange

        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Pica()),
                new CartaInglesa(11, new Pica()), new CartaInglesa(12, new Pica()),
                new CartaInglesa(13, new Pica()), new CartaInglesa(4, new Pica())));
        Puntaje puntajeEsperado = new Puntaje(79, 4);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje puntajeObtenido = juego.puntuarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test06UnaInstanciaDeColorSiUnaListaDeCartasVaciaDevueleElValorBaseDeColor() {
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
    public void test07AUnJuegoColorSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Color());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(72, 8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Pica()), new CartaInglesa(4, new Pica()), new CartaInglesa(5, new Pica()), new CartaInglesa(12, new Pica()), new CartaInglesa(8, new Pica())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = juego.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Color());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test08AUnJuegoColorSeLeAplicaUnTarotCambiadorDeMultiplicadorParaParNoLoModifica() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Par());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(72, 4);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Pica()), new CartaInglesa(4, new Pica()), new CartaInglesa(5, new Pica()), new CartaInglesa(12, new Pica()), new CartaInglesa(8, new Pica())));
        Juego Color = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Color.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test09AUnJuegoColorSeLeAplicaUnTarotCambiadorDePuntosDeColorDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new Color());
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(45, 4);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Pica()), new CartaInglesa(4, new Pica()), new CartaInglesa(5, new Pica()), new CartaInglesa(12, new Pica()), new CartaInglesa(8, new Pica())));
        Juego Color = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Color.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Color());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test10AUnJuegoColorSeLeAplicaUnTarotCambiadorDePuntosDeParDeOchoPuntosNoDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new Par());
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(72, 4);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Pica()), new CartaInglesa(4, new Pica()), new CartaInglesa(5, new Pica()), new CartaInglesa(12, new Pica()), new CartaInglesa(8, new Pica())));
        Juego Color = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Color.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11AUnJuegoColorSeLeAplicaUnTarotSumadorDeColorDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8, 2, new Color());
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(80, 6);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Pica()), new CartaInglesa(4, new Pica()), new CartaInglesa(5, new Pica()), new CartaInglesa(12, new Pica()), new CartaInglesa(8, new Pica())));
        Juego Color = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Color.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Color());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test12AUnJuegoColorSeLeAplicaUnTarotSumadorDeParDeOchoPuntosNoSeDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8, 2, new Par());
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(72, 4);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Pica()), new CartaInglesa(4, new Pica()), new CartaInglesa(5, new Pica()), new CartaInglesa(12, new Pica()), new CartaInglesa(8, new Pica())));
        Juego Color = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Color.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
