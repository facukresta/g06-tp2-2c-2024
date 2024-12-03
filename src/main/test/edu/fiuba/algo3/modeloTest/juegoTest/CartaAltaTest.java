package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.CartaInglesa;
import edu.fiuba.algo3.modelo.naipes.carta.Corazon;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.CambiadorDeMultiplicador;
import edu.fiuba.algo3.modelo.tarot.SinTarot;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CartaAltaTest {
    @Test
    public void test01UnaInstanciaDeCartaAltaSiUnaListaDeCartasConUnaCartaEsCartaAlta() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(15, 1);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test02UnaInstanciaDeCartaAltaSiUnaListaDeCartasConCincoCartasEsCartaAlta() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(2, new Corazon()), new CartaInglesa(3, new Corazon()),
                        new CartaInglesa(4, new Corazon()), new CartaInglesa(5, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueseCartaAlta = new Puntaje(20, 1);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
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
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test04UnaInstanciaDeCartaAltaSiUnaListaDeCartasConMasDeCincoCartasEsCartaAlta() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(2, new Corazon()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Corazon()), new CartaInglesa(5, new Corazon()),
                new CartaInglesa(6, new Corazon()), new CartaInglesa(7, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueseCartaAlta = new Puntaje(33, 1);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertFalse(esperadoSiFueseCartaAlta.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test05UnaInstanciaDeCartaAltaDeUnaListaDeCartasConUnaCartaDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Corazon())));
        Puntaje valorEsperado = new Puntaje(15, 1);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje valorObtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(valorObtenido.tenesMismoPuntaje(valorEsperado));
    }
    @Test
    public void test06UnaInstanciaDeCartaAltaDeUnaListaDeCartasConCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(2, new Corazon()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Corazon()), new CartaInglesa(5, new Corazon())));
        Puntaje EsperadoSiFueseCartaAlta = new Puntaje(24, 1);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje Obtenido = juego.obtenerPuntaje();
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
        Puntaje valorObtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(valorObtenido.tenesMismoPuntaje(valorEsperado));
    }
    @Test
    public void test08UnaInstanciaDeCartaAltaDeUnaListaDeCartasMasDeCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(2, new Corazon()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Corazon()), new CartaInglesa(5, new Corazon()),
                new CartaInglesa(6, new Corazon()), new CartaInglesa(7, new Corazon())));
        Puntaje EsperadoSiFueseCartaAlta = new Puntaje(42, 1);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje Obtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(Obtenido.tenesMismoPuntaje(EsperadoSiFueseCartaAlta));
    }
    @Test
    public void test09AUnJuegoCartaAltaSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new CartaAlta(), "");
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(10,8);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(5, new Corazon())));
        // Act
        Juego cartaAlta = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = cartaAlta.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new CartaAlta());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test10AUnJuegoCartaAltaSeLeAplicaUnTarotCambiadorDeMultiplicadorParaParNoLoModifica() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Par(), "");
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(10,1);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(5, new Corazon())));
        // Act
        Juego cartaAlta = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = cartaAlta.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test11AUnJuegoCartaAltaSeLeAplicaUnTarotCambiadorDePuntosDeCartaAltaDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new CartaAlta(), "");
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(13,1);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(5, new Corazon())));
        // Act
        Juego cartaAlta = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = cartaAlta.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new CartaAlta());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test12AUnJuegoCartaAltaSeLeAplicaUnTarotCambiadorDePuntosDeParDeOchoPuntosNoDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new Par(), "");
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(10,1);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(5, new Corazon())));
        // Act
        Juego cartaAlta = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = cartaAlta.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test13AUnJuegoCartaAltaSeLeAplicaUnTarotSumadorDeCartaAltaDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new CartaAlta(), "");
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(18,2);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(5, new Corazon())));
        // Act
        Juego cartaAlta = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = cartaAlta.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new CartaAlta());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test14AUnJuegoCartaAltaSeLeAplicaUnTarotSumadorDeParDeOchoPuntosNoSeDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new Par(), "");
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(10,1);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(5, new Corazon())));
        // Act
        Juego cartaAlta = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = cartaAlta.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
