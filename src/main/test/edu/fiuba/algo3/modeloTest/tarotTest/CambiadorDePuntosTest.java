package edu.fiuba.algo3.modeloTest.tarotTest;

import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.CambiadorDePuntos;
import edu.fiuba.algo3.modelo.tarot.PuntosNegativosTarotException;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CambiadorDePuntosTest {
    @Test
    public void test01UnTarotCambiadorDePuntosDeOchoPuntosConUnPuntajeBaseDeDosPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8);
        Puntaje puntajeEsperado = new Puntaje(8, 2);
        // Act
        Puntaje puntajeBase = new Puntaje(2, 2);
        Puntaje puntajeObtenido = tarotCambiadorDePuntos.obtenerPuntaje(puntajeBase);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test02UnTarotCambiadorDePuntosDeTresPuntosConUnPuntajeBaseDeTresPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(3);
        Puntaje puntajeEsperado = new Puntaje(3, 1);
        // Act
        Puntaje puntajeBase = new Puntaje(3, 1);
        Puntaje puntajeObtenido = tarotCambiadorDePuntos.obtenerPuntaje(puntajeBase);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test03UnTarotCambiadorDePuntosDeSietePuntosConUnPuntajeBaseDeCuatroPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(4);
        Puntaje puntajeEsperado = new Puntaje(4, 1);
        // Act
        Puntaje puntajeBase = new Puntaje(7, 1);
        Puntaje puntajeObtenido = tarotCambiadorDePuntos.obtenerPuntaje(puntajeBase);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test04UnTarotCambiadorDePuntosNoPuedeSerInstanciadoConPuntosNegativos() {
        // Arrange / Act / Assert
        assertThrows(PuntosNegativosTarotException.class, () -> new CambiadorDePuntos(-1));
    }
    @Test
    public void test05UnTarotCambiadorDePuntosPuedeSerInstanciadoIgualA0() {
        // Arrange / Act / Assert
        assertDoesNotThrow(() -> new CambiadorDePuntos(0));
    }
}
