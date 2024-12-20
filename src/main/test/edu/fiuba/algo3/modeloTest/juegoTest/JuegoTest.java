package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class JuegoTest {
    @Test
    public void test01UnJuegoDeUnaListaConUnaCartaSeraCartaAlta() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        boolean resulatado = juego.getClass() == CartaAlta.class;
        // Assert
        assertTrue(resulatado);
    }

    @Test
    public void test02UnJuegoDeUnaListaConCincoCartasSinOtroJuegoPosibleSeraCartaAlta() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon()),
                new CartaInglesa(9, new Pica()), new CartaInglesa(3, new Diamante()),
                new CartaInglesa(11, new Corazon()), new CartaInglesa(2, new Trebol())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        boolean resulatado = juego.getClass() == CartaAlta.class;
        // Assert
        assertTrue(resulatado);
    }

    @Test
    public void test03UnJuegoDeUnaListaConDosCartasDelMismoValorSeraPar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Pica())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        boolean resulatado = juego.getClass() == Par.class;
        // Assert
        assertTrue(resulatado);
    }

    @Test
    public void test04UnJuegoDeUnaListaConCincoCartasYUnParSinOtroJuegoPosibleSeraPar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Pica()), new CartaInglesa(3, new Diamante()),
                new CartaInglesa(11, new Corazon()), new CartaInglesa(2, new Trebol())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        boolean resulatado = juego.getClass() == Par.class;
        // Assert
        assertTrue(resulatado);
    }

    @Test
    public void test05UnJuegoDeUnaListaConTresCartasYTrioSinOtroJuegoPosibleSeraTrio() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Pica()), new CartaInglesa(10, new Diamante())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        boolean resulatado = juego.getClass() == Trio.class;
        // Assert
        assertTrue(resulatado);
    }

    @Test
    public void test06UnJuegoDeUnaListaConCincoCartasYUnTrioSinOtroJuegoPosibleSeraTrio() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Pica()), new CartaInglesa(10, new Diamante()),
                new CartaInglesa(11, new Corazon()), new CartaInglesa(2, new Trebol())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        boolean resulatado = juego.getClass() == Trio.class;
        // Assert
        assertTrue(resulatado);
    }

    @Test
    public void test07UnJuegoDeUnaListaConCuatroCartasYDosParesSinOtroJuegoPosibleSeraDoblePar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Pica()), new CartaInglesa(11, new Corazon()),
                new CartaInglesa(11, new Trebol())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        boolean resulatado = juego.getClass() == DoblePar.class;
        // Assert
        assertTrue(resulatado);
    }

    @Test
    public void test08UnJuegoDeUnaListaConCincoCartasYDosParesSinOtroJuegoPosibleSeraDoblePar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Pica()), new CartaInglesa(11, new Corazon()),
                new CartaInglesa(11, new Trebol()), new CartaInglesa(2, new Trebol())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        boolean resulatado = juego.getClass() == DoblePar.class;
        // Assert
        assertTrue(resulatado);
    }

    @Test
    public void test09UnJuegoDeUnaListaConCuatroCartasYPokerSinOtroJuegoPosibleSeraPoker() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Pica()), new CartaInglesa(10, new Diamante()),
                new CartaInglesa(10, new Trebol())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        boolean resulatado = juego.getClass() == Poker.class;
        // Assert
        assertTrue(resulatado);
    }

    @Test
    public void test10UnJuegoDeUnaListaConCincoCartasYPokerSinOtroJuegoPosibleSeraPoker() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Pica()), new CartaInglesa(10, new Diamante()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(2, new Trebol())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        boolean resulatado = juego.getClass() == Poker.class;
        // Assert
        assertTrue(resulatado);
    }

    @Test
    public void test11UnJuegoDeUnaListaConCincoCartasYColorSinOtroJuegoPosibleSeraColor() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon()),
                new CartaInglesa(4, new Corazon()), new CartaInglesa(5, new Corazon()),
                new CartaInglesa(6, new Corazon()), new CartaInglesa(2, new Corazon())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        boolean resulatado = juego.getClass() == Color.class;
        // Assert
        assertTrue(resulatado);
    }

    @Test
    public void test12UnJuegoDeUnaListaConCincoCartasYFullHouseSinOtroJuegoPosibleSeraFullHouse() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(10, new Corazon()),
                new CartaInglesa(2, new Corazon()), new CartaInglesa(10, new Pica()),
                new CartaInglesa(2, new Pica()), new CartaInglesa(2, new Diamante())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        boolean resulatado = juego.getClass() == FullHouse.class;
        // Assert
        assertTrue(resulatado);
    }

    @Test
    public void test13UnJuegoDeUnaListaConCincoCartasYEscaleraSinOtroJuegoPosibleSeraEscalera() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Corazon()),
                new CartaInglesa(2, new Corazon()), new CartaInglesa(3, new Pica()),
                new CartaInglesa(4, new Pica()), new CartaInglesa(5, new Diamante())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        boolean resulatado = juego.getClass() == Escalera.class;
        // Assert
        assertTrue(resulatado);
    }

    @Test
    public void test14UnJuegoDeUnaListaConCincoCartasYEscaleraDeColorSinOtroJuegoPosibleSeraEscaleraDeColor() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Pica()),
                new CartaInglesa(2, new Pica()), new CartaInglesa(3, new Pica()),
                new CartaInglesa(4, new Pica()), new CartaInglesa(5, new Pica())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        boolean resulatado = juego.getClass() == EscaleraDeColor.class;
        // Assert
        assertTrue(resulatado);
    }

    @Test
    public void test16UnJuegoDeUnaListaConCuatroCartasDevolveraElValorMaximoDeJuego() {
        // Arrange
        Puntaje puntajeEsperado = new Puntaje(100, 7);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(13, new Pica()),
                new CartaInglesa(13, new Corazon()), new CartaInglesa(13, new Diamante()),
                new CartaInglesa(13, new Trebol())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test17UnJuegoDeUnaListaConCincoCartasDevolveraElValorMaximoDeJuego() {
        // Arrange
        Puntaje puntajeEsperado = new Puntaje(150, 8);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Pica()),
                new CartaInglesa(11, new Pica()), new CartaInglesa(12, new Pica()),
                new CartaInglesa(13, new Pica()), new CartaInglesa(10, new Pica())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test18UnJuegoDeUnaListaConTresCartasDevolveraElValorMaximoDeJuego() {
        // Arrange
        Puntaje puntajeEsperado = new Puntaje(60, 3);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(13, new Pica()),
                new CartaInglesa(13, new Corazon()), new CartaInglesa(13, new Diamante())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test19UnJuegoDeUnaListaConDosCartasDevolveraElValorMaximoDeJuego() {
        // Arrange
        Puntaje puntajeEsperado = new Puntaje(30, 2);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(13, new Pica()),
                new CartaInglesa(13, new Corazon())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test20UnJuegoDeUnaListaConUnaCartasDevolveraElValorMaximoDeJuego() {
        // Arrange
        Puntaje puntajeEsperado = new Puntaje(15, 1);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(13, new Pica())));
        // Act
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
