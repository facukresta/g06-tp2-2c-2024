package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DobleParTest {
    @Test
    public void test01UnaInstanciaDeDobleParSiUnaListaDeCartasConCuatroCartasEsDoblePar() {
        // Arrange
        DoblePar juegoDoblePar = new DoblePar();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Trebol()), new Carta(12, new Corazon()),
                new Carta(12, new Trebol())));
        // Act
        boolean resulatado = juegoDoblePar.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test02UnaInstanciaDeDobleParSiUnaListaDeCartasConCincoCartasEsDoblePar() {
        // Arrange
        DoblePar juegoDoblePar = new DoblePar();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(3, new Diamante()),
                new Carta(3, new Trebol()), new Carta(5, new Corazon())));
        // Act
        boolean resulatado = juegoDoblePar.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test03UnaInstanciaDeDobleParSiUnaListaDeCartasConCincoCartasEsDobleParAunqueHayaTrio() {
        // Arrange
        DoblePar juegoDoblePar = new DoblePar();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(1, new Diamante()),
                new Carta(5, new Diamante()), new Carta(5, new Corazon())));
        // Act
        boolean resulatado = juegoDoblePar.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test04UnaInstanciaDeDobleParSiUnaListaDeCartasConCincoCartasEsDobleParAunqueHayaFullHouse() {
        // Arrange
        DoblePar juegoDoblePar = new DoblePar();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(1, new Diamante()),
                new Carta(5, new Diamante()), new Carta(5, new Corazon())));
        // Act
        boolean resulatado = juegoDoblePar.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test05UnaInstanciaDeDobleParSiUnaListaDeCartasVaciaNoEsDoblePar() {
        // Arrange
        DoblePar juegoDoblePar = new DoblePar();
        ArrayList<Carta> cartas = new ArrayList<>();
        // Act
        boolean resulatado = juegoDoblePar.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test06UnaInstanciaDeDobleParSiUnaListaDeCartasConTresCartaDistintasYUnParNoEsDoblePar() {
        // Arrange
        DoblePar juegoDoblePar = new DoblePar();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(1, new Corazon()),
                new Carta(1, new Trebol()), new Carta(1, new Pica()),
                new Carta(3, new Trebol()), new Carta(2, new Corazon())));
        // Act
        boolean resulatado = juegoDoblePar.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test07UnaInstanciaDeDobleParSiUnaListaDeCartasConTresCartaNoEsDoblePar() {
        // Arrange
        DoblePar juegoDoblePar = new DoblePar();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(3, new Trebol()),
                new Carta(3, new Corazon()), new Carta(3, new Pica())));
        // Act
        boolean resulatado = juegoDoblePar.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test08UnaInstanciaDeDobleParSiUnaListaDeCartasCuatroCartasIgualesEsDoblePar() {
        // Arrange
        DoblePar juegoDoblePar = new DoblePar();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Trebol()), new Carta(10, new Pica()),
                new Carta(10, new Diamante())));
        // Act
        boolean resulatado = juegoDoblePar.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test09UnaInstanciaDeDobleParSiUnaListaDeCartasConMasDeCincoCartasEsDoblePar() {
        // Arrange
        DoblePar juegoDoblePar = new DoblePar();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Diamante()), new Carta(3, new Corazon()),
                new Carta(3, new Pica()), new Carta(5, new Corazon()),
                new Carta(6, new Corazon()), new Carta(7, new Corazon())));
        // Act
        boolean resulatado = juegoDoblePar.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test10UnaInstanciaDeDobleParDeUnaListaConCincoCartasDeDiferenteValorNoEsDoblePar() {
        // Arrange
        DoblePar juegoDoblePar = new DoblePar();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Pica()), new Carta(3, new Corazon()),
                new Carta(4, new Diamante()), new Carta(5, new Trebol())));
        // Act
        boolean resulatado = juegoDoblePar.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test11UnaInstanciaDeDobleParDeUnaListaDeCartasConCuatrCartaDevuelveElValorCorrecto() {
        // Arrange
        DoblePar juegoDoblePar = new DoblePar();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Trebol()), new Carta(10, new Pica()),
                new Carta(10, new Diamante())));
        Puntaje puntajeEsperado = new Puntaje(60, 2);
        // Act
        Puntaje puntajeObtenido = juegoDoblePar.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test12UnaInstanciaDeDobleParDeUnaListaDeCartasConCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        DoblePar juegoDoblePar = new DoblePar();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(2, new Diamante()),
                new Carta(2, new Pica()), new Carta(3, new Corazon()),
                new Carta(3, new Trebol()), new Carta(5, new Corazon())));
        Puntaje puntajeEsperado = new Puntaje(35, 2);
        // Act
        Puntaje puntajeObtenido = juegoDoblePar.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test13UnaInstanciaDeDobleParSiUnaListaDeCartasVaciaNDevueleElValorBaseDeDoblePar() {
        // Arrange
        DoblePar juegoDoblePar = new DoblePar();
        ArrayList<Carta> cartas = new ArrayList<>();
        Puntaje puntajeEsperado = new Puntaje(20, 2);
        // Act
        Puntaje puntajeObtenido = juegoDoblePar.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test14UnaInstanciaDeDobleParDeUnaListaDeCartasMasDeCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        DoblePar juegoDoblePar = new DoblePar();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Diamante()), new Carta(1, new Pica()),
                new Carta(7, new Pica()), new Carta(7, new Corazon()),
                new Carta(7, new Diamante()), new Carta(7, new Trebol())));
        Puntaje puntajeEsperado = new Puntaje(78, 2);
        // Act
        Puntaje puntajeObtenido = juegoDoblePar.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
