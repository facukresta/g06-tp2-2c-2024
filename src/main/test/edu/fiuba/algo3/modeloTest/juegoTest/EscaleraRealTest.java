package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EscaleraRealTest {
    @Test
    public void test01UnaInstanciaDeEscaleraRealSiUnaListaDeCartasConCincoCartasEsEscaleraReal() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Trebol()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(11, new Trebol()),
                new CartaInglesa(12, new Trebol()), new CartaInglesa(13, new Trebol())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(150, 8);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test02UnaInstanciaDeEscaleraRealSiUnaListaDeCartasConSeisCartasNoEsEscaleraReal() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Trebol()),
                new CartaInglesa(9, new Trebol()), new CartaInglesa(10, new Trebol()),
                new CartaInglesa(11, new Trebol()), new CartaInglesa(12, new Trebol()),
                new CartaInglesa(13, new Trebol())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraEscaleraReal = new Puntaje(159, 8);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertFalse(esperadoSiFueraEscaleraReal.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test03UnaInstanciaDeEscaleraRealReconoceQueNoEsEscaleraReal() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(10, new Corazon()), new CartaInglesa(11, new Corazon()),
                new CartaInglesa(12, new Corazon()), new CartaInglesa(13, new Trebol())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraEscaleraReal = new Puntaje(150, 8);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertFalse(esperadoSiFueraEscaleraReal.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test04UnaInstanciaDeEscaleraRealDeUnaListaDeCartasConCincoCartasDelMismoColorYEnEscaleraDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Pica()),
                new CartaInglesa(13, new Pica()), new CartaInglesa(11, new Pica()),
                new CartaInglesa(12, new Pica()), new CartaInglesa(1, new Pica())));
        Puntaje puntajeEsperado = new Puntaje(150,8);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje puntajeObtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test05UnaInstanciaDeEscaleraRealSiUnaListaDeCartasVaciaDevueleElValorBase() {
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
    public void test06UnaInstanciaDeEscaleraRealDeUnaListaDeCartasConCincoYNoEnEscaleraRealSabeReconocerQueNoEsEscaleraReal() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Pica()),
                new CartaInglesa(11, new Pica()), new CartaInglesa(12, new Trebol()),
                new CartaInglesa(13, new Pica()), new CartaInglesa(9, new Pica())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraEscaleraReal = new Puntaje(149, 8);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertFalse(esperadoSiFueraEscaleraReal.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test07AUnJuegoEscaleraRealSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(9, new EscaleraReal(), "");
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(150,9);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Trebol()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(11, new Trebol()),
                new CartaInglesa(12, new Trebol()), new CartaInglesa(13, new Trebol()) ));
        Juego EscaleraReal = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = EscaleraReal.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new EscaleraReal());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test08AUnJuegoEscaleraRealSeLeAplicaUnTarotCambiadorDeMultiplicadorParaParNoLoModifica() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(5, new Escalera(), "");
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(150,8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Trebol()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(11, new Trebol()),
                new CartaInglesa(12, new Trebol()), new CartaInglesa(13, new Trebol()) ));
        Juego EscaleraReal = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = EscaleraReal.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test09AUnJuegoEscaleraRealSeLeAplicaUnTarotCambiadorDePuntosDeEscaleraRealDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(110, new EscaleraReal(), "");
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(160,8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Trebol()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(11, new Trebol()),
                new CartaInglesa(12, new Trebol()), new CartaInglesa(13, new Trebol())));
        Juego EscaleraReal = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = EscaleraReal.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new EscaleraReal());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test10AUnJuegoEscaleraRealSeLeAplicaUnTarotCambiadorDePuntosDeParDeOchoPuntosNoDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new Par(), "");
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(150,8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Trebol()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(11, new Trebol()),
                new CartaInglesa(12, new Trebol()), new CartaInglesa(13, new Trebol())));
        Juego EscaleraReal = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = EscaleraReal.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11AUnJuegoEscaleraRealSeLeAplicaUnTarotSumadorDeEscaleraRealDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new EscaleraReal(), "");
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(158,10);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Trebol()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(11, new Trebol()),
                new CartaInglesa(12, new Trebol()), new CartaInglesa(13, new Trebol())));
        Juego EscaleraReal = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = EscaleraReal.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new EscaleraReal());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test12AUnJuegoEscaleraRealSeLeAplicaUnTarotSumadorDeParDeOchoPuntosNoSeDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new Escalera(), "");
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(150,8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Trebol()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(11, new Trebol()),
                new CartaInglesa(12, new Trebol()), new CartaInglesa(13, new Trebol())));
        Juego EscaleraReal = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = EscaleraReal.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
