package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EscaleraTest {
    @Test
    public void test01UnaInstanciaDeEscaleraSiUnaListaDeCartasConCincoCartasEsEscalera() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Trebol()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Pica())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(54, 4);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test02UnaInstanciaDeEscaleraSiUnaListaDeCartasConSeisCartasNoEsEscalera() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Trebol()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol()),
                new CartaInglesa(6, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraEscalera = new Puntaje(60, 4);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertFalse(esperadoSiFueraEscalera.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test03UnaInstanciaDeEscaleraReconoceQueNoEsEscalera() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(2, new Corazon()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Corazon()), new CartaInglesa(6, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraEscalera = new Puntaje(55, 4);
        // Act
        Puntaje obtenido = juego.puntuarMano();
        // Assert
        assertFalse(esperadoSiFueraEscalera.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test04UnaEscaleraDeUnaListaDeCartasConCincoCartasEnEscaleraNoEsEscaleraYaQueEsReal() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Pica()),
                new CartaInglesa(11, new Pica()), new CartaInglesa(12, new Pica()),
                new CartaInglesa(13, new Pica()), new CartaInglesa(9, new Pica())));
        Puntaje esperadoSiFueraEscaleraNoraml = new Puntaje(79, 4);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje puntajeObtenido = juego.puntuarMano();
        // Assert
        assertFalse(puntajeObtenido.tenesMismoPuntaje(esperadoSiFueraEscaleraNoraml));
    }

    @Test
    public void test07AUnJuegoEscaleraSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(9, new Escalera());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(54,9);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol()) ));
        Juego Escalera = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Escalera.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test08AUnJuegoEscaleraSeLeAplicaUnTarotCambiadorDeMultiplicadorParaParNoLoModifica() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(5, new Par());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(54,4);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol()) ));
        Juego Escalera = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Escalera.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test09AUnJuegoEscaleraSeLeAplicaUnTarotCambiadorDePuntosDeEscaleraDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(6, new Escalera());
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(30,4);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol())));
        Juego Escalera = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Escalera.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test10AUnJuegoEscaleraSeLeAplicaUnTarotCambiadorDePuntosDeParDeOchoPuntosNoDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new Par());
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(54,4);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol())));
        Juego Escalera = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Escalera.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11AUnJuegoEscaleraSeLeAplicaUnTarotSumadorDeEscaleraDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new Escalera());
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(62,6);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol())));
        Juego Escalera = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Escalera.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test12AUnJuegoEscaleraSeLeAplicaUnTarotSumadorDeParDeOchoPuntosNoSeDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new Par());
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(54,4);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Trebol()),
                new CartaInglesa(2, new Trebol()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Trebol()), new CartaInglesa(5, new Trebol())));
        Juego Escalera = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = Escalera.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
