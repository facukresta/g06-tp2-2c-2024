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
        Trio juegoTrio = new Trio();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Trebol()), new Carta(10, new Pica())));
        // Act
        boolean resulatado = juegoTrio.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test02UnaInstanciaDeTrioSiUnaListaDeCartasConCincoCartasEsTrio() {
        // Arrange
        Trio juegoTrio = new Trio();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(1, new Diamante()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon())));
        // Act
        boolean resulatado = juegoTrio.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test03UnaInstanciaDeTrioSiUnaListaDeCartasConCincoCartasEsTrioAunqueHayaCuatroIguales() {
        // Arrange
        Trio juegoTrio = new Trio();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(4, new Corazon()),
                new Carta(4, new Pica()), new Carta(4, new Diamante()),
                new Carta(4, new Trebol()), new Carta(5, new Corazon())));
        // Act
        boolean resulatado = juegoTrio.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test04UnaInstanciaDeTrioSiUnaListaDeCartasConCincoCartasEsTrioAunqueHayaFullHouse() {
        // Arrange
        Trio juegoTrio = new Trio();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(3, new Corazon()),
                new Carta(3, new Diamante()), new Carta(3, new Trebol())));
        // Act
        boolean resulatado = juegoTrio.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test05UnaInstanciaDeTrioReconoceQueNoEsTrioAunqueHayaTresPares() {
        // Arrange
        Trio juegoTrio = new Trio();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(3, new Corazon()),
                new Carta(3, new Diamante()), new Carta(4, new Trebol()),
                new Carta(4, new Diamante())));
        // Act
        boolean resulatado = juegoTrio.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test06UnaInstanciaDeTrioSiUnaListaDeCartasVaciaNoEsTrio() {
        // Arrange
        Trio juegoTrio = new Trio();
        ArrayList<Carta> cartas = new ArrayList<>();
        // Act
        boolean resulatado = juegoTrio.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test07UnaInstanciaDeTrioSiUnaListaDeCartasConDosCartaNoEsTrio() {
        // Arrange
        Trio juegoTrio = new Trio();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(4, new Corazon()),
                new Carta(4, new Corazon())));
        // Act
        boolean resulatado = juegoTrio.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test08UnaInstanciaDeTrioSiUnaListaDeCartasConMasDeCincoCartasEsTrio() {
        // Arrange
        Trio juegoTrio = new Trio();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Diamante()), new Carta(12, new Pica()),
                new Carta(12, new Corazon()), new Carta(12, new Trebol()),
                new Carta(6, new Corazon()), new Carta(7, new Corazon())));
        // Act
        boolean resulatado = juegoTrio.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test09UnaInstanciaDeTrioDeUnaListaConCincoCartasDeDiferenteValorNoEsTrio() {
        // Arrange
        Trio juegoTrio = new Trio();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Pica()), new Carta(3, new Corazon()),
                new Carta(4, new Diamante()), new Carta(5, new Trebol())));
        // Act
        boolean resulatado = juegoTrio.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test10UnaInstanciaDeTrioDeUnaListaDeCartasConTresCartaDevuelveElValorCorrecto() {
        // Arrange
        Trio juegoTrio = new Trio();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Trebol()), new Carta(10, new Pica())));
        Puntaje puntajeEsperado = new Puntaje(60, 3);
        // Act
        Puntaje puntajeObtenido = juegoTrio.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test11UnaInstanciaDeTrioDeUnaListaDeCartasConCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        Trio juegoTrio = new Trio();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(2, new Diamante()),
                new Carta(2, new Pica()), new Carta(2, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon())));
        Puntaje puntajeEsperado = new Puntaje(45, 3);
        // Act
        Puntaje puntajeObtenido = juegoTrio.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test12UnaInstanciaDeTrioSiUnaListaDeCartasVaciaNDevueleElValorBaseDeTrio() {
        // Arrange
        Trio juegoTrio = new Trio();
        ArrayList<Carta> cartas = new ArrayList<>();
        Puntaje puntajeEsperado = new Puntaje(30, 3);
        // Act
        Puntaje puntajeObtenido = juegoTrio.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test13UnaInstanciaDeTrioDeUnaListaDeCartasMasDeCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        Trio juegoTrio = new Trio();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Corazon()), new Carta(5, new Trebol()),
                new Carta(5, new Diamante()), new Carta(5, new Corazon()),
                new Carta(7, new Diamante()), new Carta(7, new Trebol())));
        Puntaje puntajeEsperado = new Puntaje(71, 3);
        // Act
        Puntaje puntajeObtenido = juegoTrio.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test07AUnJuegoTrioSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Trio());
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(42,8);
        // Act
        Juego Trio = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4,
                new Corazon()), new Carta(4, new Trebol()))));
        Puntaje puntajeObtenido = Trio.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()),
                new Carta(4, new Corazon()), new Carta(4, new Trebol()))));
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
        Juego Trio = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()),
                new Carta(4, new Corazon()), new Carta(4, new Trebol()))));
        Puntaje puntajeObtenido = Trio.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()),
                new Carta(4, new Corazon()), new Carta(4, new Trebol()))));
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
        Juego Trio = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()),
                new Carta(12, new Pica()), new Carta(4, new Trebol()))));
        Puntaje puntajeObtenido = Trio.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()),
                new Carta(4, new Corazon()), new Carta(4, new Trebol()))));
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
        Juego Trio = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()), new Carta(4, new Trebol()))));
        Puntaje puntajeObtenido = Trio.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()), new Carta(4, new Trebol()))));
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
        Juego Trio = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()), new Carta(4, new Trebol()))));
        Puntaje puntajeObtenido = Trio.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()), new Carta(4, new Trebol()))));
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
        Juego Trio = Juego.chequearJuego(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()), new Carta(4, new Trebol()))));
        Puntaje puntajeObtenido = Trio.puntuarMano(new ArrayList<>(List.of(new Carta(4, new Pica()), new Carta(4, new Corazon()), new Carta(4, new Trebol()))));
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

}
