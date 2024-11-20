package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.Corazon;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.CambiadorDeMultiplicador;
import edu.fiuba.algo3.modelo.tarot.SinTarot;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import org.mockito.internal.util.JavaEightUtil;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CartaAltaTest {
    @Test
    public void test01UnaInstanciaDeCartaAltaSiUnaListaDeCartasConUnaCartaEsCartaAlta() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(15, 1);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test02UnaInstanciaDeCartaAltaSiUnaListaDeCartasConCincoCartasEsCartaAlta() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Corazon()), new Carta(3, new Corazon()),
                        new Carta(4, new Corazon()), new Carta(5, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueseCartaAlta = new Puntaje(20, 1);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertFalse(esperadoSiFueseCartaAlta.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test03UnaInstanciaDeCartaAltaSiUnaListaDeCartasVaciaNoEsCartaAlta() {
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
    public void test04UnaInstanciaDeCartaAltaSiUnaListaDeCartasConMasDeCincoCartasEsCartaAlta() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Corazon()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon()),
                new Carta(6, new Corazon()), new Carta(7, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueseCartaAlta = new Puntaje(33, 1);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertFalse(esperadoSiFueseCartaAlta.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test05UnaInstanciaDeCartaAltaDeUnaListaDeCartasConUnaCartaDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon())));
        Puntaje valorEsperado = new Puntaje(15, 1);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje valorObtenido = juego.puntuarMano();
        // Assert
        assertTrue(valorObtenido.tenesMismoPuntaje(valorEsperado));
    }
    @Test
    public void test06UnaInstanciaDeCartaAltaDeUnaListaDeCartasConCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Corazon()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon())));
        Puntaje EsperadoSiFueseCartaAlta = new Puntaje(24, 1);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje Obtenido = juego.puntuarMano();
        // Assert
        assertFalse(Obtenido.tenesMismoPuntaje(EsperadoSiFueseCartaAlta));
    }
    @Test
    public void test07UnaInstanciaDeCartaAltaSiUnaListaDeCartasVaciaDevueleCero() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>();
        Puntaje valorEsperado = new Puntaje(0, 1);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje valorObtenido = juego.puntuarMano();
        // Assert
        assertTrue(valorObtenido.tenesMismoPuntaje(valorEsperado));
    }
    @Test
    public void test08UnaInstanciaDeCartaAltaDeUnaListaDeCartasMasDeCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        CartaAlta juegoCartaAlta = new CartaAlta();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Corazon()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon()),
                new Carta(6, new Corazon()), new Carta(7, new Corazon())));
        Puntaje EsperadoSiFueseCartaAlta = new Puntaje(42, 1);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje Obtenido = juegoCartaAlta.puntuarMano();
        // Assert
        assertFalse(Obtenido.tenesMismoPuntaje(EsperadoSiFueseCartaAlta));
    }
//    @Test
//    public void test09AUnJuegoCartaAltaSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
//        // Arrange
//        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new CartaAlta());
//        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
//        Puntaje puntajeEsperado = new Puntaje(10,8);
//        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(5, new Corazon())));
//        // Act
//        Juego cartaAlta = Juego.chequearJuego(cartas);
//        Puntaje puntajeObtenido = cartaAlta.puntuarMano();
//        // Assert
//        Tarot tarot2 = new SinTarot(new CartaAlta());
//        Juego.aplicarTarot(tarot2);
//        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
//    }
//    @Test
//    public void test10AUnJuegoCartaAltaSeLeAplicaUnTarotCambiadorDeMultiplicadorParaParNoLoModifica() {
//        // Arrange
//        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Par());
//        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
//        Puntaje puntajeEsperado = new Puntaje(10,1);
//        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(5, new Corazon())));
//        // Act
//        Juego cartaAlta = Juego.chequearJuego(cartas);
//        Puntaje puntajeObtenido = cartaAlta.puntuarMano();
//        // Assert
//        Tarot tarot2 = new SinTarot(new Par());
//        Juego.aplicarTarot(tarot2);
//        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
//    }
//    @Test
//    public void test11AUnJuegoCartaAltaSeLeAplicaUnTarotCambiadorDePuntosDeCartaAltaDeOchoPuntosDevuelveEsePuntajeModificado() {
//        // Arrange
//        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new CartaAlta());
//        Juego.aplicarTarot(tarotCambiadorDePuntos);
//        Puntaje puntajeEsperado = new Puntaje(13,1);
//        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(5, new Corazon())));
//        // Act
//        Juego cartaAlta = Juego.chequearJuego(cartas);
//        Puntaje puntajeObtenido = cartaAlta.puntuarMano();
//        // Assert
//        Tarot tarot2 = new SinTarot(new CartaAlta());
//        Juego.aplicarTarot(tarot2);
//        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
//    }
//
//    @Test
//    public void test12AUnJuegoCartaAltaSeLeAplicaUnTarotCambiadorDePuntosDeParDeOchoPuntosNoDevuelveEsePuntajeModificado() {
//        // Arrange
//        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new Par());
//        Juego.aplicarTarot(tarotCambiadorDePuntos);
//        Puntaje puntajeEsperado = new Puntaje(10,1);
//        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(5, new Corazon())));
//        // Act
//        Juego cartaAlta = Juego.chequearJuego(cartas);
//        Puntaje puntajeObtenido = cartaAlta.puntuarMano();
//        // Assert
//        Tarot tarot2 = new SinTarot(new Par());
//        Juego.aplicarTarot(tarot2);
//        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
//    }
//
//    @Test
//    public void test13AUnJuegoCartaAltaSeLeAplicaUnTarotSumadorDeCartaAltaDeOchoPuntosDevuelveEsePuntajeModificado() {
//        // Arrange
//        Tarot tarotSumador = new Sumador(8,2, new CartaAlta());
//        Juego.aplicarTarot(tarotSumador);
//        Puntaje puntajeEsperado = new Puntaje(18,2);
//        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(5, new Corazon())));
//        // Act
//        Juego cartaAlta = Juego.chequearJuego(cartas);
//        Puntaje puntajeObtenido = cartaAlta.puntuarMano();
//        // Assert
//        Tarot tarot2 = new SinTarot(new CartaAlta());
//        Juego.aplicarTarot(tarot2);
//        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
//    }
//
//    @Test
//    public void test14AUnJuegoCartaAltaSeLeAplicaUnTarotSumadorDeParDeOchoPuntosNoSeDevuelveEsePuntajeModificado() {
//        // Arrange
//        Tarot tarotSumador = new Sumador(8,2, new Par());
//        Juego.aplicarTarot(tarotSumador);
//        Puntaje puntajeEsperado = new Puntaje(10,1);
//        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(5, new Corazon())));
//        // Act
//        Juego cartaAlta = Juego.chequearJuego(cartas);
//        Puntaje puntajeObtenido = cartaAlta.puntuarMano();
//        // Assert
//        Tarot tarot2 = new SinTarot(new Par());
//        Juego.aplicarTarot(tarot2);
//        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
//    }
}
