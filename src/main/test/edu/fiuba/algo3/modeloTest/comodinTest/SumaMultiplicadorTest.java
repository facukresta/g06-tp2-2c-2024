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

public class SumaMultiplicadorTest {
    @Test
    public void test01AlAplicarUnComodinDeSumaMultiplciadorEsteSumaElMultplicador(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5);
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
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
        comodin1.aplicarModificador(puntajeAModificar, new CartaAlta());
        comodin2.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test03AlAplicarUnComodinDeSumaMultiplicadorAUnJuegoEspecificoSeSumanCorrectamenteYaQueEsElJuegoCorrecto(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, new CartaAlta());
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test04AlAplicarUnComodinDeSumaMultiplicadorAUnJuegoEspecificoNoSeSumanCorrectamenteYaQueNoEsElJuegoCorrecto(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, new Par());
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test05AlAplicarUnComodinDeSumaMultiplicadorConUnaProbabilidadDondeSeDaSeAplicaCorrectamente(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            accion.run();
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new SumaMultiplicador(6, aleatorioMock);
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 8);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test06AlAplicarUnComodinDeSumaMultiplicadorConUnaProbabilidadDondeNoSeDaNoSeAplicaCorrectamente(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new SumaMultiplicador(6, aleatorioMock);
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test07AlAplicarUnComodinDeSumaUnMultiplicadorConUnaProbabilidadQueSeDaYUnJuegoEspecificoQueTambienSeDaSeAplicaCorrectamente(){
        // Arrange
        Aleatorio aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            accion.run();
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new SumaMultiplicador(6, new Par(), aleatorioMock);
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 8);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Par());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test08AlAplicarUnComodinDeSumaUnMultiplicadorConUnaProbabilidadQueSeDaYUnJuegoEspecificoQueNoSeDaNoSeAplicaCorrectamente(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            accion.run();
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new SumaMultiplicador(6, new CartaAlta(), aleatorioMock);
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Par());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test09AlAplicarUnComodinDeSumaUnMultiplicadorConUnaProbabilidadQueNoSeDaYUnJuegoEspecificoQueSeDaNoSeAplicaCorrectamente(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new SumaMultiplicador(6, new Par(), aleatorioMock);
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Par());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test10AlAplicarUnComodinDeSumaUnMultiplicadorConUnaProbabilidadQueNoSeDaYUnJuegoEspecificoQueTampocoSeDaNoSeAplicaCorrectamente(){
        // Arrange
        Ejecucion aleatorioMock = mock(Aleatorio.class);
        doAnswer(invocation -> {
            Runnable accion = invocation.getArgument(0);
            return null;
        }).when(aleatorioMock).ejecuta(any(Runnable.class));
        Comodin comodin = new SumaMultiplicador(6, new CartaAlta(), aleatorioMock);
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Par());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11AlAplicarUnComodinDeSumaMultiplciadorConJuegoCartaAltaEsteSumaElMultplicadorSiElJuegoEsCartaAlta(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(CartaAlta.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(CartaAlta.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test12AlAplicarUnComodinDeSumaMultiplciadorConJuegoParEsteSumaElMultplicadorSiElJuegoEsPar(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(Par.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Par.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test13AlAplicarUnComodinDeSumaMultiplciadorConJuegoDobleParEsteSumaElMultplicadorSiElJuegoEsDoblePar(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(DoblePar.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(DoblePar.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test14AlAplicarUnComodinDeSumaMultiplciadorConJuegoTrioEsteSumaElMultplicadorSiElJuegoEsTrio(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(Trio.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Trio.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test15AlAplicarUnComodinDeSumaMultiplciadorConJuegoEscaleraEsteSumaElMultplicadorSiElJuegoEsEscalera(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(Escalera.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Escalera.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test16AlAplicarUnComodinDeSumaMultiplciadorConJuegoColorEsteSumaElMultplicadorSiElJuegoEsColor(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(Color.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Color.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test17AlAplicarUnComodinDeSumaMultiplciadorConJuegoEscaleraDeColorEsteSumaElMultplicadorSiElJuegoEsEscaleraDeColor(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(EscaleraDeColor.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(EscaleraDeColor.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test18AlAplicarUnComodinDeSumaMultiplciadorConJuegoEscaleraRealEsteSumaElMultplicadorSiElJuegoEsEscaleraReal(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(EscaleraReal.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(EscaleraReal.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test19AlAplicarUnComodinDeSumaMultiplciadorConJuegoFullHouseEsteSumaElMultplicadorSiElJuegoEsFullHouse(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(FullHouse.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(FullHouse.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test20AlAplicarUnComodinDeSumaMultiplciadorConJuegoPokerEsteSumaElMultplicadorSiElJuegoEsPoker(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(Poker.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Poker.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test21AlAplicarDosComodinesDeSumaMultiplicadorDeDistintosJuegosSeSumanCorrectamente(){
        // Arrange
        Comodin comodin1 = new SumaMultiplicador(5, mock(CartaAlta.class));
        Comodin comodin2 = new SumaMultiplicador(6, mock(DoblePar.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 13);
        // Act
        comodin1.aplicarModificador(puntajeAModificar, mock(CartaAlta.class));
        comodin2.aplicarModificador(puntajeAModificar, mock(DoblePar.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test22AlAplicarUnComodinDeUnJuegoQueNoEsElDelComodinElPuntajeNoSeVeMofificado(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(32130, mock(CartaAlta.class));
        Puntaje puntajeAModificar = new Puntaje(220, 232);
        Puntaje puntajeEsperado = new Puntaje(220, 232);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(DoblePar.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
}
