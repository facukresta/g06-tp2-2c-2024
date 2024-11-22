package edu.fiuba.algo3.modeloTest.tarotTest;

import edu.fiuba.algo3.modelo.juego.CartaAlta;
import edu.fiuba.algo3.modelo.juego.Escalera;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SumadorTest {
    @Test
    public void test01UnTarotSumadorParaUnaCartaSumaUnPuntajeBaseDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8, 1);
        Puntaje puntajeEsperado = new Puntaje(11,2);
        // Act
        Puntaje puntajeBase = new Puntaje(3, 2);
        Puntaje puntajeObtenido = tarotSumador.obtenerPuntaje(puntajeBase);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test02UnTarotSumadorParaUnaCartaNoPuedeSerInstanciadoConMultiplicadorNegativos() {
        // Arrange / Act / Assert
        assertThrows(MultiplicadorInvalidoTarotException.class, () -> new Sumador(10, -1));
    }
    @Test
    public void test03UnTarotSumadorParaUnaCartaNoPuedeSerInstanciadoConMultiplicadorIgualA0() {
        // Arrange / Act / Assert
        assertThrows(MultiplicadorInvalidoTarotException.class, () -> new Sumador(10, 0));
    }

    @Test
    public void test04UnTarotSumadorParaUnaCartaNoPuedeSerInstanciadoConPuntosNegativos() {
        // Arrange / Act / Assert
        assertThrows(PuntosNegativosTarotException.class, () -> new Sumador(-1, 10));
    }

    @Test
    public void test05UnTarotSumadorSabeReconocerSiEsParaUnJuegoEspecifico() {
        // Arrange
        Tarot tarotSumador = new Sumador(10, 1,new CartaAlta());
        // Act
        boolean resultadoComparacion = tarotSumador.sosParaEsteJuego(new CartaAlta());
        // Assert
        assertTrue(resultadoComparacion);
    }

    @Test
    public void test06UnTarotSumadorSabeReconocerSiNoEsParaUnJuegoEspecifico() {
        // Arrange
        Tarot tarotSumador = new Sumador(10, 1,new Escalera());
        // Act
        boolean resultadoComparacion = tarotSumador.sosParaEsteJuego(new CartaAlta());
        // Assert
        assertFalse(resultadoComparacion);
    }

}
