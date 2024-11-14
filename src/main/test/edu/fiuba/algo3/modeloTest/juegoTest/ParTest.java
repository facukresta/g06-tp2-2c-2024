package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
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
        Puntaje puntajeEsperado = new Puntaje(39,2);
        // Act
        Puntaje puntajeObtenido = juegoPar.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
