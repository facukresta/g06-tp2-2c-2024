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
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Trebol()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(124, 8);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test02UnaInstanciaDeEscaleraDeColorSiUnaListaDeCartasConSeisCartasNoEsEscaleraDeColor() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Trebol()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol()),
                new CartaInglesa(6, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraEscaleraDeColor = new Puntaje(130, 8);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertFalse(esperadoSiFueraEscaleraDeColor.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test03UnaInstanciaDeEscaleraDeColorReconoceQueNoEsEscaleraDeColor() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(2, new Corazon()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Corazon()), new CartaInglesa(5, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraEscaleraDeColor = new Puntaje(124, 8);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertFalse(esperadoSiFueraEscaleraDeColor.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test04UnaInstanciaDeEscaleraDeColorSiUnaListaDeCartasVaciaNoEsEscaleraDeColor() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>();
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(0, 1);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test05UnaInstanciaDeEscaleraDeColorDeUnaListaDeCartasConCincoCartasDelMismoColorYEnEscaleraDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Pica()),
                new CartaInglesa(11, new Pica()), new CartaInglesa(12, new Pica()),
                new CartaInglesa(13, new Pica()), new CartaInglesa(9, new Pica())));
        Puntaje puntajeEsperado = new Puntaje(149,8);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje puntajeObtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test06UnaInstanciaDeEscaleraDeColorSiUnaListaDeCartasVaciaDevueleElValorBaseDeEscaleraDeColor() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>();
        Puntaje puntajeEsperado = new Puntaje(0,1);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje puntajeObtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test07UnaInstanciaDeEscaleraDeColorDeUnaListaDeCartasConCincoCartasDelMismoColorYNoEnEscaleraSabeReconocerQueNoEsEscaleraDeColor() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Pica()),
                new CartaInglesa(11, new Pica()), new CartaInglesa(12, new Pica()),
                new CartaInglesa(13, new Pica()), new CartaInglesa(8, new Pica())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraEscaleraDeColor = new Puntaje(148, 8);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertFalse(esperadoSiFueraEscaleraDeColor.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test07AUnJuegoEscaleraDeColorSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(9, new EscaleraDeColor(), "");
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(124,9);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Trebol()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol()) ));
        Juego EscaleraDeColor = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = EscaleraDeColor.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new EscaleraDeColor());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test08AUnJuegoEscaleraDeColorSeLeAplicaUnTarotCambiadorDeMultiplicadorParaParNoLoModifica() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(5, new Escalera(), "");
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(124,8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Trebol()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol()) ));
        Juego EscaleraDeColor = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = EscaleraDeColor.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test09AUnJuegoEscaleraDeColorSeLeAplicaUnTarotCambiadorDePuntosDeEscaleraDeColorDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(12, new EscaleraDeColor(), "");
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(36,8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Trebol()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol())));
        Juego EscaleraDeColor = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = EscaleraDeColor.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new EscaleraDeColor());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test10AUnJuegoEscaleraDeColorSeLeAplicaUnTarotCambiadorDePuntosDeParDeOchoPuntosNoDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new Par(), "");
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(124,8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Trebol()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol())));
        Juego EscaleraDeColor = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = EscaleraDeColor.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11AUnJuegoEscaleraDeColorSeLeAplicaUnTarotSumadorDeEscaleraDeColorDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new EscaleraDeColor(), "");
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(132,10);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Trebol()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol())));
        Juego EscaleraDeColor = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = EscaleraDeColor.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new EscaleraDeColor());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test12AUnJuegoEscaleraDeColorSeLeAplicaUnTarotSumadorDeParDeOchoPuntosNoSeDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new Escalera(), "");
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(124,8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Trebol()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol())));
        Juego EscaleraDeColor = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = EscaleraDeColor.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test13UnaInstanciaDeEscaleraDeColorSiUnaListaDeCartasConCincoCartasEsEscaleraSiendo10JQKAS() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Trebol()),
                new CartaInglesa(11, new Trebol()), new CartaInglesa(12, new Trebol()),
                new CartaInglesa(13, new Trebol()), new CartaInglesa(10, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(150, 8);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
}
