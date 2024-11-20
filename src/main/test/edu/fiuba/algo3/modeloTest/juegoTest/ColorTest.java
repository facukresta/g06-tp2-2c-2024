package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.Corazon;
import edu.fiuba.algo3.modelo.naipes.carta.Pica;
import edu.fiuba.algo3.modelo.naipes.carta.Trebol;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ColorTest {
    @Test
    public void test01UnaInstanciaDeColorSiUnaListaDeCartasConCincoCartasEsColor() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(6, new Trebol())));
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Trebol()),
                new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()),
                new Carta(6, new Trebol())));
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Corazon()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Trebol())));
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

        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Pica()),
                new Carta(11, new Pica()), new Carta(12, new Pica()),
                new Carta(13, new Pica()), new Carta(4, new Pica())));
        Puntaje puntajeEsperado = new Puntaje(79,4);
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
        Puntaje puntajeEsperado = new Puntaje(0,1);
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
        Puntaje puntajeEsperado = new Puntaje(72,8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(1, new Pica()), new Carta(4, new Pica()), new Carta(5, new Pica()), new Carta(12, new Pica()), new Carta(8, new Pica())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = juego.puntuarMano();
        // Assert
        Tarot tarot2 = new SinTarot(new Color());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

//    @Test
//    public void test08AUnJuegoColorSeLeAplicaUnTarotCambiadorDeMultiplicadorParaParNoLoModifica() {
//        // Arrange
//        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Par());
//        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
//        Puntaje puntajeEsperado = new Puntaje(72,4);
//        // Act
//        Juego Color = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(1, new Pica()), new Carta(4, new Pica()), new Carta(5, new Pica()), new Carta(12, new Pica()), new Carta(8, new Pica()))));
//        Puntaje puntajeObtenido = Color.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Pica()), new Carta(4, new Pica()), new Carta(5, new Pica()), new Carta(12, new Pica()), new Carta(8, new Pica()))));
//        // Assert
//        Tarot tarot2 = new SinTarot(new Par());
//        Juego.aplicarTarot(tarot2);
//        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
//    }
//    @Test
//    public void test09AUnJuegoColorSeLeAplicaUnTarotCambiadorDePuntosDeColorDeOchoPuntosDevuelveEsePuntajeModificado() {
//        // Arrange
//        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new Color());
//        Juego.aplicarTarot(tarotCambiadorDePuntos);
//        Puntaje puntajeEsperado = new Puntaje(45,4);
//        // Act
//        Juego Color = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(1, new Pica()), new Carta(4, new Pica()), new Carta(5, new Pica()), new Carta(12, new Pica()), new Carta(8, new Pica()))));
//        Puntaje puntajeObtenido = Color.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Pica()), new Carta(4, new Pica()), new Carta(5, new Pica()), new Carta(12, new Pica()), new Carta(8, new Pica()))));
//        // Assert
//        Tarot tarot2 = new SinTarot(new Color());
//        Juego.aplicarTarot(tarot2);
//        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
//    }
//
//    @Test
//    public void test10AUnJuegoColorSeLeAplicaUnTarotCambiadorDePuntosDeParDeOchoPuntosNoDevuelveEsePuntajeModificado() {
//        // Arrange
//        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new Par());
//        Juego.aplicarTarot(tarotCambiadorDePuntos);
//        Puntaje puntajeEsperado = new Puntaje(72,4);
//        // Act
//        Juego Color = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(1, new Pica()), new Carta(4, new Pica()), new Carta(5, new Pica()), new Carta(12, new Pica()), new Carta(8, new Pica()))));
//        Puntaje puntajeObtenido = Color.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Pica()), new Carta(4, new Pica()), new Carta(5, new Pica()), new Carta(12, new Pica()), new Carta(8, new Pica()))));
//        // Assert
//        Tarot tarot2 = new SinTarot(new Par());
//        Juego.aplicarTarot(tarot2);
//        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
//    }
//
//    @Test
//    public void test11AUnJuegoColorSeLeAplicaUnTarotSumadorDeColorDeOchoPuntosDevuelveEsePuntajeModificado() {
//        // Arrange
//        Tarot tarotSumador = new Sumador(8,2, new Color());
//        Juego.aplicarTarot(tarotSumador);
//        Puntaje puntajeEsperado = new Puntaje(80,6);
//        // Act
//        Juego Color = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(1, new Pica()), new Carta(4, new Pica()), new Carta(5, new Pica()), new Carta(12, new Pica()), new Carta(8, new Pica()))));
//        Puntaje puntajeObtenido = Color.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Pica()), new Carta(4, new Pica()), new Carta(5, new Pica()), new Carta(12, new Pica()), new Carta(8, new Pica()))));
//        // Assert
//        Tarot tarot2 = new SinTarot(new Color());
//        Juego.aplicarTarot(tarot2);
//        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
//    }
//
//    @Test
//    public void test12AUnJuegoColorSeLeAplicaUnTarotSumadorDeParDeOchoPuntosNoSeDevuelveEsePuntajeModificado() {
//        // Arrange
//        Tarot tarotSumador = new Sumador(8,2, new Par());
//        Juego.aplicarTarot(tarotSumador);
//        Puntaje puntajeEsperado = new Puntaje(72,4);
//        // Act
//        Juego Color = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(1, new Pica()), new Carta(4, new Pica()), new Carta(5, new Pica()), new Carta(12, new Pica()), new Carta(8, new Pica()))));
//        Puntaje puntajeObtenido = Color.puntuarMano(new ArrayList<>(List.of(new Carta(1, new Pica()), new Carta(4, new Pica()), new Carta(5, new Pica()), new Carta(12, new Pica()), new Carta(8, new Pica()))));
//        // Assert
//        Tarot tarot2 = new SinTarot(new Par());
//        Juego.aplicarTarot(tarot2);
//        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
//    }
}
