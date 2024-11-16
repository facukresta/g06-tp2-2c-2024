package edu.fiuba.algo3.modeloTest.comodinTest;

import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SumaMultiplicadorTest {
    @Test
    public void test01AlAplicarUnComodinDeSumaMultiplciadorEsteSumaElMultplicador(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5);
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Juego.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test02AlAplicarDosComodinesDeSumaMultiplicadorSeSumanCorrectamente(){
        // Arrange
        Comodin comodin1 = new SumaMultiplicador(5);
        Comodin comodin2 = new SumaMultiplicador(6);
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 13);
        // Act
        comodin1.aplicarModificador(puntajeAModificar, mock(Juego.class));
        comodin2.aplicarModificador(puntajeAModificar, mock(Juego.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
}
