package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FullHouseTest {
    @Test
    public void test01UnaInstanciaDeFullHouseSiUnaListaDeCartasConCincopCartasEsFullHouse() {
        // Arrange
        FullHouse juegoFullHouse = new FullHouse();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Trebol()), new Carta(10, new Pica()),
                new Carta(8, new Trebol()), new Carta(8, new Pica())));
        // Act
        boolean resulatado = juegoFullHouse.sosJuego(cartas);
        // Assert
        assertTrue(resulatado);
    }
    @Test
    public void test02UnaInstanciaDeFullHouseReconoceQueEsFullHouseAunqueHayaTresTrios() {
        // Arrange
        FullHouse juegoFullHouse = new FullHouse();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(1, new Pica()), new Carta(1, new Diamante()),
                new Carta(3, new Diamante()), new Carta(3, new Trebol()),
                new Carta(3, new Pica()), new Carta(7, new Diamante()),
                new Carta(7, new Trebol()), new Carta(7, new Pica())));
        // Act
        boolean resulatado = juegoFullHouse.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test03UnaInstanciaDeFullHouseSiUnaListaDeCartasVaciaNoEsFullHouse() {
        // Arrange
        FullHouse juegoFullHouse = new FullHouse();
        ArrayList<Carta> cartas = new ArrayList<>();
        // Act
        boolean resulatado = juegoFullHouse.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test04UnaInstanciaDeFullHouseSiUnaListaDeCartasConCuatroCartaNoEsFullHouse() {
        // Arrange
        FullHouse juegoFullHouse = new FullHouse();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(4, new Corazon()),
                new Carta(4, new Trebol()), new Carta(7, new Corazon()),
                new Carta(7, new Trebol())));
        // Act
        boolean resulatado = juegoFullHouse.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test05UnaInstanciaDeFullHouseDeUnaListaConCincoCartasDeDiferenteValorNoEsFullHouse() {
        // Arrange
        FullHouse juegoFullHouse = new FullHouse();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(1, new Corazon()),
                new Carta(2, new Pica()), new Carta(3, new Corazon()),
                new Carta(4, new Diamante()), new Carta(5, new Trebol())));
        // Act
        boolean resulatado = juegoFullHouse.sosJuego(cartas);
        // Assert
        assertFalse(resulatado);
    }
    @Test
    public void test06UnaInstanciaDeFullHouseDeUnaListaDeCartasConCincoCartasQueFormanFullHouseDevuelveElValorCorrecto() {
        // Arrange
        FullHouse juegoFullHouse = new FullHouse();
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new Carta(10, new Corazon()),
                new Carta(10, new Trebol()), new Carta(10, new Pica()),
                new Carta(4, new Trebol()), new Carta(4, new Pica())));
        Puntaje puntajeEsperado = new Puntaje(78,4);
        // Act
        Puntaje puntajeObtenido = juegoFullHouse.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test07UnaInstanciaDeFullHouseSiUnaListaDeCartasVaciaNDevueleElValorBaseDeFullHouse() {
        // Arrange
        FullHouse juegoFullHouse = new FullHouse();
        ArrayList<Carta> cartas = new ArrayList<>();
        Puntaje puntajeEsperado = new Puntaje(40,4);
        // Act
        Puntaje puntajeObtenido = juegoFullHouse.puntuarMano(cartas);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
