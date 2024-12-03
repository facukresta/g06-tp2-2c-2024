package edu.fiuba.algo3.modeloTest.comodinTest;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.aleatorio.Ejecucion;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;


public class SumaPuntosDescarteTest {
    @Test
    public void test01AlAplicarUnComodinDeSumaPuntosDescarteEsteSumaLosPuntos(){
        // Arrange
        Comodin comodin = new SumaPuntosDescarte(5, "");
        Puntaje puntajeAModificar = new Puntaje(5, 4);
        Puntaje puntajeEsperado = new Puntaje(10, 4);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new SinJuego());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test02AlAplicarDosComodinesDeSumaPuntosDescarteSeSumanCorrectamente(){
        // Arrange
        Comodin comodin1 = new SumaPuntosDescarte(5, "");
        Comodin comodin2 = new SumaPuntosDescarte(6, "");
        Puntaje puntajeAModificar = new Puntaje(4, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin1.aplicarModificador(puntajeAModificar, new SinJuego());
        comodin2.aplicarModificador(puntajeAModificar, new SinJuego());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test03AlAplicarUnComodinDeSumaPuntosDescarteConUnaProbabilidadQueSeDaraEsteSumaLosPuntos(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            accion.run();
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new SumaPuntosDescarte(5, aleatorioMock, "");
        Puntaje puntajeAModificar = new Puntaje(5, 4);
        Puntaje puntajeEsperado = new Puntaje(10, 4);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new SinJuego());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test04AlAplicarUnComodinDeSumaPuntosDescarteConUnaProbabilidadQueNoSeDaraEsteNoSumaLosPuntos(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new SumaPuntosDescarte(5, aleatorioMock, "");
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
        Ejecucion aleatorioMock1 = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            accion.run();
            return null;
        }).when(aleatorioMock1).ejecuta(any(Runnable.class));
        Comodin comodin1 = new SumaPuntosDescarte(5, aleatorioMock1, "");
        Ejecucion aleatorioMock2 = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            return null;
        }).when(aleatorioMock2).ejecuta(any(Runnable.class));
        Comodin comodin2 = new SumaPuntosDescarte(2000, aleatorioMock2, "");
        Puntaje puntajeAModificar = new Puntaje(5, 4);
        Puntaje puntajeEsperado = new Puntaje(10, 4);
        // Act
        comodin1.aplicarModificador(puntajeAModificar, new SinJuego());
        comodin2.aplicarModificador(puntajeAModificar, new SinJuego());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

}
