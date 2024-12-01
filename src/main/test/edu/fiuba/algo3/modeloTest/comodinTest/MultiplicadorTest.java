package edu.fiuba.algo3.modeloTest.comodinTest;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.aleatorio.Ejecucion;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.Multiplicador;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class MultiplicadorTest {
    @Test
    public void test01AlAplicarUnComodinDeMultiplicadorEsteMultiplicaElTotal(){
        // Arrange
        Comodin comodin = new Multiplicador(5, "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2*5);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test02AlAplicarDosComodinesDeMultiplicadorEstosMultiplicanLosPuntos(){
        // Arrange
        Comodin comodin1 = new Multiplicador(5, "");
        Comodin comodin2 = new Multiplicador(10, "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2*5*10);
        // Act
        comodin1.aplicarModificador(puntajeAModificar, new CartaAlta());
        comodin2.aplicarModificador(puntajeAModificar, new Escalera());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test03AlAplicarUnComodinDeMultiplicadorAUnJuegoEspecificoEsteMultiplicaLosPuntosYaQueEsElJuego(){
        // Arrange
        Comodin comodin = new Multiplicador(5,new CartaAlta(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2*5);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test04AlAplicarUnComodinDeMultiplicadorAUnJuegoEspecificoEsteNoMultiplicaLosPuntosYaQueNoEsElJuego(){
        // Arrange
        Comodin comodin = new Multiplicador(5,new CartaAlta(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Escalera());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test05AlAplicarUnComodinDeMultiplicadorConProbablidadEsteMultiplicaLosPuntosYaQueSeDaLaProbabilidad(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            accion.run();
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new Multiplicador(5, aleatorioMock, "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2*5);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test06AlAplicarUnComodinDeMultiplicadorConProbablidadEsteNoMultiplicaLosPuntosYaQueNoSeDaLaProbabilidad(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new Multiplicador(5, aleatorioMock, "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test07AlAplicarUnComodinDeMultiplicadorConUnaProbabilidadQueSeDaYUnJuegoEspecificoQueTambienSeDaSeAplicaCorrectamente(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            accion.run();
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new Multiplicador(6, new Par(), aleatorioMock, "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2*6);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Par());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test08AlAplicarUnComodinDeMultiplicadorConJuegoCartaAltaEsteMultiplicaElMultplicadorSiElJuegoEsCartaAlta(){
        // Arrange
        Comodin comodin = new Multiplicador(5, new CartaAlta(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2*5);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test09AlAplicarDosComodinesDeMultiplicadorDeDistintosJuegosSeMultiplicanCorrectamente(){
        // Arrange
        Comodin comodin1 = new Multiplicador(5, new CartaAlta(), "");
        Comodin comodin2 = new Multiplicador(6, new DoblePar(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2*5*6);

        // Act
        comodin1.aplicarModificador(puntajeAModificar, new CartaAlta());
        comodin2.aplicarModificador(puntajeAModificar, new DoblePar());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test22AlAplicarUnComodinDeMultiplicadorConJuegoPokerEsteNoMultiplicaSiElJuegoNoEsPoker(){
        // Arrange
        Comodin comodin = new Multiplicador(5, new Poker(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);

        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
}
