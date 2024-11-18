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
        Par juegoPar = new Par();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Trebol())));
        // Act
        boolean resulatado = juegoPar.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test02UnaInstanciaDeParSiUnaListaDeCartasConCincoCartasEsPar() {
        // Arrange
        Par juegoPar = new Par();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon())));
        // Act
        boolean resulatado = juegoPar.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test03UnaInstanciaDeParSiUnaListaDeCartasConCincoCartasEsParAunqueHayaTrio() {
        // Arrange
        Par juegoPar = new Par();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(1, new Diamante()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon())));
        // Act
        boolean resulatado = juegoPar.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test04UnaInstanciaDeParSiUnaListaDeCartasConCincoCartasEsParAunqueHayaFullHouse() {
        // Arrange
        Par juegoPar = new Par();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(3, new Corazon()),
                new Carta(3, new Diamante()), new Carta(3, new Trebol())));
        // Act
        boolean resulatado = juegoPar.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test05UnaInstanciaDeParSiUnaListaDeCartasConCincoCartasEsParAunqueHayaDoblePar() {
        // Arrange
        Par juegoPar = new Par();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(3, new Corazon()),
                new Carta(3, new Diamante()), new Carta(6, new Trebol())));
        // Act
        boolean resulatado = juegoPar.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test06UnaInstanciaDeParSiUnaListaDeCartasVaciaNoEsPar() {
        // Arrange
        Par juegoPar = new Par();
        ArrayList<Carta> cartas = new ArrayList<>();
        // Act
        boolean resulatado = juegoPar.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test07UnaInstanciaDeParSiUnaListaDeCartasConUnaCartaNoEsPar() {
        // Arrange
        Par juegoPar = new Par();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(1, new Corazon())));
        // Act
        boolean resulatado = juegoPar.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test08UnaInstanciaDeParSiUnaListaDeCartasConMasDeCincoCartasEsPar() {
        // Arrange
        Par juegoPar = new Par();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Diamante()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon()),
                new Carta(6, new Corazon()), new Carta(7, new Corazon())));
        // Act
        boolean resulatado = juegoPar.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test09UnaInstanciaDeParDeUnaListaConCincoCartasDeDiferenteValorNoEsPar() {
        // Arrange
        Par juegoPar = new Par();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Pica()), new Carta(3, new Corazon()),
                new Carta(4, new Diamante()), new Carta(5, new Trebol())));
        // Act
        boolean resulatado = juegoPar.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test10UnaInstanciaDeParDeUnaListaDeCartasConDosCartaDevuelveElValorCorrecto() {
        // Arrange
        Par juegoPar = new Par();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Trebol())));
        Puntaje puntajeEsperado = new Puntaje(30,2);
        // Act
        Puntaje puntajeObtenido = juegoPar.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test11UnaInstanciaDeParDeUnaListaDeCartasConCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        Par juegoPar = new Par();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(2, new Diamante()),
                new Carta(2, new Pica()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon())));
        Puntaje puntajeEsperado = new Puntaje(26,2);
        // Act
        Puntaje puntajeObtenido = juegoPar.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test12UnaInstanciaDeParSiUnaListaDeCartasVaciaNDevueleElValorBaseDePar() {
        // Arrange
        Par juegoPar = new Par();
        ArrayList<Carta> cartas = new ArrayList<>();
        Puntaje puntajeEsperado = new Puntaje(10,2);
        // Act
        Puntaje puntajeObtenido = juegoPar.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test13UnaInstanciaDeParDeUnaListaDeCartasMasDeCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        Par juegoPar = new Par();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Corazon()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon()),
                new Carta(7, new Diamante()), new Carta(7, new Trebol())));
        Puntaje puntajeEsperado = new Puntaje(48,2);
        // Act
        Puntaje puntajeObtenido = juegoPar.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test07AUnJuegoParSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Par());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(18,8);
        // Act
        Juego Par = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4,
                new Corazon()))));
        Puntaje puntajeObtenido = Par.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()),
                new Carta(4, new Corazon()))));
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
        Juego Par = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()),
                new Carta(4, new Corazon()))));
        Puntaje puntajeObtenido = Par.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()),
                new Carta(4, new Corazon()))));
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
        Juego Par = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()),
                new Carta(12, new Pica()))));
        Puntaje puntajeObtenido = Par.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()),
                new Carta(4, new Corazon()))));
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
        Juego Par = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()))));
        Puntaje puntajeObtenido = Par.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()))));
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
        Juego Par = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()))));
        Puntaje puntajeObtenido = Par.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()))));
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
        Juego Par = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()))));
        Puntaje puntajeObtenido = Par.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()))));
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

}
