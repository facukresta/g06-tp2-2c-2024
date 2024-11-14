package edu.fiuba.algo3.modeloTest.tarotTest;

import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.SinTarot;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SinTarotTest {
    @Test
    public void test01UnTarotSinTarotConUnPuntajeBaseDevuelveEsePuntaje() {
        // Arrange
        Tarot tarotSinTarot = new SinTarot();
        Puntaje puntajeEsperado = new Puntaje(100,1);
        // Act
        Puntaje puntajeBase = new Puntaje(100, 1);
        Puntaje puntajeObtenido = tarotSinTarot.obtenerPuntaje(puntajeBase);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
