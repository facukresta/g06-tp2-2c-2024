package edu.fiuba.algo3.modeloTest.comodinTest;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.SumaMultiplicadorDescarte;
import edu.fiuba.algo3.modelo.comodin.SumaPuntosDescarte;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class SumaMultiplicadorDescarteTest {
    @Test
    public void test01AlAplicarUnComodinDeSumaMultiplicadorDescarteEsteSumaLosMultiplicadores(){
        // Arrange
        Comodin comodin = new SumaMultiplicadorDescarte(5);
        Puntaje puntajeAModificar = new Puntaje(5, 4);
        Puntaje puntajeEsperado = new Puntaje(5, 9);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new SinJuego());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test02AlAplicarDosComodinesDeSumaPuntosDescarteSeSumanCorrectamente(){
        // Arrange
        Comodin comodin1 = new SumaMultiplicadorDescarte(5);
        Comodin comodin2 = new SumaMultiplicadorDescarte(6);
        Puntaje puntajeAModificar = new Puntaje(4, 2);
        Puntaje puntajeEsperado = new Puntaje(4, 13);
        // Act
        comodin1.aplicarModificador(puntajeAModificar, new SinJuego());
        comodin2.aplicarModificador(puntajeAModificar, new SinJuego());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test03AlAplicarUnComodinDeSumaPuntosDescarteConUnaProbabilidadQueSeDaraEsteSumaLosPuntos(){
        // Arrange
        Aleatorio aleatorioMock = mock(Aleatorio.class);
        when(aleatorioMock.sucede()).thenReturn(true);
        Comodin comodin = new SumaMultiplicadorDescarte(5, aleatorioMock);
        Puntaje puntajeAModificar = new Puntaje(5, 4);
        Puntaje puntajeEsperado = new Puntaje(5, 9);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new SinJuego());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test04AlAplicarUnComodinDeSumaPuntosDescarteConUnaProbabilidadQueNoSeDaraEsteNoSumaLosPuntos(){
        // Arrange
        Aleatorio aleatorioMock = mock(Aleatorio.class);
        when(aleatorioMock.sucede()).thenReturn(false);
        Comodin comodin = new SumaMultiplicadorDescarte(5, aleatorioMock);
        Puntaje puntajeAModificar = new Puntaje(5, 4);
        Puntaje puntajeEsperado = new Puntaje(5, 4);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new SinJuego());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test05AlAplicarUnComodinDeSumaPuntosDescarteConUnaProbabilidadQueSeDaraYOtroQueNoEsteSumaLosPuntosDelPrimero(){
        // Arrange
        Aleatorio aleatorioMock1 = mock(Aleatorio.class);
        when(aleatorioMock1.sucede()).thenReturn(true);
        Comodin comodin1 = new SumaMultiplicadorDescarte(5, aleatorioMock1);
        Aleatorio aleatorioMock2 = mock(Aleatorio.class);
        when(aleatorioMock2.sucede()).thenReturn(false);
        Comodin comodin2 = new SumaMultiplicadorDescarte(2000, aleatorioMock2);
        Puntaje puntajeAModificar = new Puntaje(5, 4);
        Puntaje puntajeEsperado = new Puntaje(5, 9);
        // Act
        comodin1.aplicarModificador(puntajeAModificar, new SinJuego());
        comodin2.aplicarModificador(puntajeAModificar, new SinJuego());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

}