package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TrioTest {

    @Test
    public void test01UnaInstanciaDeTrioSiUnaListaDeCartasConTresCartasEsTrio() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(10, new Pica())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(60, 3);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test02UnaInstanciaDeTrioSiUnaListaDeCartasConCincoCartasEsTrio() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(1, new Pica()), new CartaInglesa(1, new Diamante()),
                new CartaInglesa(4, new Corazon()), new CartaInglesa(5, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(69, 3);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test03UnaInstanciaDeTrioSiUnaListaDeCartasVaciaNoEsTrio() {
        ArrayList<Carta> cartas = new ArrayList<>(List.of());
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(0, 1);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));

    }
    @Test
    public void test04UnaInstanciaDeTrioSiUnaListaDeCartasConMasDeCincoCartasEsTrio() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Diamante()), new CartaInglesa(12, new Pica()),
                new CartaInglesa(12, new Corazon()), new CartaInglesa(12, new Trebol()),
                new CartaInglesa(6, new Corazon()), new CartaInglesa(7, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(93, 3);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test05UnaInstanciaDeTrioDeUnaListaDeCartasConTresCartaDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(10, new Pica())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(60, 3);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test06UnaInstanciaDeTrioDeUnaListaDeCartasConCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(2, new Diamante()),
                new CartaInglesa(2, new Pica()), new CartaInglesa(2, new Corazon()),
                new CartaInglesa(4, new Corazon()), new CartaInglesa(5, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(45, 3);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test07UnaInstanciaDeTrioSiUnaListaDeCartasVaciaNDevueleElValorBaseDeTrio() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of());
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(0, 1);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test08UnaInstanciaDeTrioDeUnaListaDeCartasMasDeCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(2, new Corazon()), new CartaInglesa(5, new Trebol()),
                new CartaInglesa(5, new Diamante()), new CartaInglesa(5, new Corazon()),
                new CartaInglesa(7, new Diamante()), new CartaInglesa(7, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(71, 3);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }

    @Test
    public void test07AUnJuegoTrioSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Trio());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(42,8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4,
                new Corazon()), new CartaInglesa(4, new Trebol())));
        Juego Trio = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Trio.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Trio());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test08AUnJuegoTrioSeLeAplicaUnTarotCambiadorDeMultiplicadorTrioaTrioNoLoModifica() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Escalera());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(42,3);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()),
                new CartaInglesa(4, new Corazon()), new CartaInglesa(4, new Trebol())));
        Juego Trio = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Trio.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test09AUnJuegoTrioSeLeAplicaUnTarotCambiadorDePuntosDeTrioDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(12, new Trio());
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(24,3);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()),
                new CartaInglesa(4, new Trebol())));
        Juego Trio = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Trio.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Trio());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test10AUnJuegoTrioSeLeAplicaUnTarotCambiadorDePuntosDeDobleTrioDeOchoPuntosNoDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new DoblePar());
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(42,3);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()), new CartaInglesa(4, new Trebol())));
        Juego Trio = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Trio.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new DoblePar());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11AUnJuegoTrioSeLeAplicaUnTarotSumadorDeTrioDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(2,2, new Trio());
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(44,5);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()), new CartaInglesa(4, new Trebol())));
        Juego Trio = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Trio.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Trio());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test12AUnJuegoTrioSeLeAplicaUnTarotSumadorDeTrioDeOchoPuntosNoSeDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new Escalera());
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(42,3);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()), new CartaInglesa(4, new Trebol())));
        Juego Trio = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Trio.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
