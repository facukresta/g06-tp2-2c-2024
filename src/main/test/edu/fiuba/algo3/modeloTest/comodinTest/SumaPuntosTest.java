package edu.fiuba.algo3.modeloTest.comodinTest;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.aleatorio.Ejecucion;
import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.juego.CartaAlta;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SumaPuntosTest {
    @Test
    public void test01AlAplicarUnComodinDeSumaPuntosEsteSumaLosPuntos(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test02AlAplicarDosComodinesDeSumaPuntosEstosSumanLosPuntos(){
        // Arrange
        Comodin comodin1 = new SumaPuntos(5, "");
        Comodin comodin2 = new SumaPuntos(10, "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(25, 2);
        // Act
        comodin1.aplicarModificador(puntajeAModificar, new CartaAlta());
        comodin2.aplicarModificador(puntajeAModificar, new Escalera());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test03AlAplicarUnComodinDeSumaPuntosAUnJuegoEspecificoEsteSumaLosPuntosYaQueEsElJuego(){
        // Arrange
        Comodin comodin = new SumaPuntos(5,new CartaAlta(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test04AlAplicarUnComodinDeSumaPuntosAUnJuegoEspecificoEsteNoSumaLosPuntosYaQueNoEsElJuego(){
        // Arrange
        Comodin comodin = new SumaPuntos(5,new CartaAlta(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Escalera());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test05AlAplicarUnComodinDeSumaPuntosConProbablidadEsteSumaLosPuntosYaQueSeDaLaProbabilidad(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            accion.run();
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new SumaPuntos(5, aleatorioMock, "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test06AlAplicarUnComodinDeSumaPuntosConProbablidadEsteNoSumaLosPuntosYaQueNoSeDaLaProbabilidad(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new SumaPuntos(5, aleatorioMock, "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test07AlAplicarUnComodinDeSumaPuntosConUnaProbabilidadQueSeDaYUnJuegoEspecificoQueTambienSeDaSeAplicaCorrectamente(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            accion.run();
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new SumaPuntos(6, new Par(), aleatorioMock, "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(16, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Par());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test08AlAplicarUnComodinDeSumaPuntosConUnaProbabilidadQueSeDaYUnJuegoEspecificoQueNoSeDaNoSeAplicaCorrectamente(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            accion.run();
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new SumaPuntos(6, new CartaAlta(), aleatorioMock, "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Par());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test09AlAplicarUnComodinDeSumaPuntosConUnaProbabilidadQueNoSeDaYUnJuegoEspecificoQueSeDaNoSeAplicaCorrectamente(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new SumaPuntos(6, new Par(), aleatorioMock, "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Par());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test10AlAplicarUnComodinDeSumaPuntosConUnaProbabilidadQueNoSeDaYUnJuegoEspecificoQueTampocoSeDaNoSeAplicaCorrectamente(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new SumaPuntos(6, new CartaAlta(), aleatorioMock, "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Par());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11AlAplicarUnComodinDeSumaPuntosConJuegoCartaAltaEsteSumaElMultplicadorSiElJuegoEsCartaAlta(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, new CartaAlta(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test12AlAplicarUnComodinDeSumaPuntosConJuegoParEsteSumaElMultplicadorSiElJuegoEsPar(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, new Par(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Par());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test13AlAplicarUnComodinDeSumaPuntosConJuegoDobleParEsteSumaElMultplicadorSiElJuegoEsDoblePar(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, new DoblePar(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new DoblePar());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test14AlAplicarUnComodinDeSumaPuntosConJuegoTrioEsteSumaElMultplicadorSiElJuegoEsTrio(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, new Trio(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Trio());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test15AlAplicarUnComodinDeSumaPuntosConJuegoEscaleraEsteSumaElMultplicadorSiElJuegoEsEscalera(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, new Escalera(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Escalera());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test16AlAplicarUnComodinDeSumaPuntosConJuegoColorEsteSumaElMultplicadorSiElJuegoEsColor(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, new Color(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Color());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test17AlAplicarUnComodinDeSumaPuntosConJuegoEscaleraDeColorEsteSumaElMultplicadorSiElJuegoEsEscaleraDeColor(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, new EscaleraDeColor(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);

        // Act
        comodin.aplicarModificador(puntajeAModificar, new EscaleraDeColor());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test18AlAplicarUnComodinDeSumaPuntosConJuegoEscaleraRealEsteSumaElMultplicadorSiElJuegoEsEscaleraReal(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, new EscaleraReal(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);

        // Act
        comodin.aplicarModificador(puntajeAModificar, new EscaleraReal());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test19AlAplicarUnComodinDeSumaPuntosConJuegoFullHouseEsteSumaElMultplicadorSiElJuegoEsFullHouse(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, new FullHouse(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);

        // Act
        comodin.aplicarModificador(puntajeAModificar, new FullHouse());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test20AlAplicarUnComodinDeSumaPuntosConJuegoPokerEsteSumaElMultplicadorSiElJuegoEsPoker(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, new Poker(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);

        // Act
        comodin.aplicarModificador(puntajeAModificar, new Poker());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test21AlAplicarDosComodinesDeSumaPuntosDeDistintosJuegosSeSumanCorrectamente(){
        // Arrange
        Comodin comodin1 = new SumaPuntos(5, new CartaAlta(), "");
        Comodin comodin2 = new SumaPuntos(6, new DoblePar(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(21, 2);

        // Act
        comodin1.aplicarModificador(puntajeAModificar, new CartaAlta());
        comodin2.aplicarModificador(puntajeAModificar, new DoblePar());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test22AlAplicarUnComodinDeSumaPuntosConJuegoPokerEsteNoSumaSiElJuegoNoEsPoker(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, new Poker(), "");
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);

        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
}
