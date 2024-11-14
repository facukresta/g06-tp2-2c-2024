package edu.fiuba.algo3.modeloTest.tarotTest;

import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.CambiadorDeMultiplicador;
import edu.fiuba.algo3.modelo.tarot.MultiplicadorInvalidoTarotException;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CambiadorDeMultiplicadorTest {
    @Test
    public void test01UnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorConUnPuntajeBaseDeDosMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8);
        Puntaje puntajeEsperado = new Puntaje(3,8);
        // Act
        Puntaje puntajeBase = new Puntaje(3, 2);
        Puntaje puntajeObtenido = tarotCambiadorDeMultiplicador.obtenerPuntaje(puntajeBase);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test02UnTarotCambiadorDeMultiplicadorDeTresMultiplicadorConUnPuntajeBaseDeTresMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(3);
        Puntaje puntajeEsperado = new Puntaje(4,3);
        // Act
        Puntaje puntajeBase = new Puntaje(4, 3);
        Puntaje puntajeObtenido = tarotCambiadorDeMultiplicador.obtenerPuntaje(puntajeBase);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test03UnTarotCambiadorDeMultiplicadorDeSieteMultiplicadorConUnPuntajeBaseDeCuatroMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(4);
        Puntaje puntajeEsperado = new Puntaje(2,4);
        // Act
        Puntaje puntajeBase = new Puntaje(2, 7);
        Puntaje puntajeObtenido = tarotCambiadorDeMultiplicador.obtenerPuntaje(puntajeBase);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test04UnTarotCambiadorDeMultiplicadorNoPuedeSerInstanciadoConMultiplicadorNegativos() {
        // Arrange / Act / Assert
        assertThrows(MultiplicadorInvalidoTarotException.class, () -> new CambiadorDeMultiplicador(-1));
    }
    @Test
    public void test05UnTarotCambiadorDeMultiplicadorNoPuedeSerInstanciadoIgualA0() {
        // Arrange / Act / Assert
        assertThrows(MultiplicadorInvalidoTarotException.class, () -> new CambiadorDeMultiplicador(0));
    }
}
