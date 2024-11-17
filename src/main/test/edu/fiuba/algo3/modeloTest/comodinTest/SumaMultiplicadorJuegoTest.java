package edu.fiuba.algo3.modeloTest.comodinTest;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SumaMultiplicadorJuegoTest {
    @Test
    public void test01AlAplicarUnComodinDeSumaMultiplciadorConJuegoCartaAltaEsteSumaElMultplicadorSiElJuegoEsCartaAlta(){
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
    public void test02AlAplicarUnComodinDeSumaMultiplciadorConJuegoParEsteSumaElMultplicadorSiElJuegoEsPar(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(Par.class), new Aleatorio(1));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Par.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test03AlAplicarUnComodinDeSumaMultiplciadorConJuegoDobleParEsteSumaElMultplicadorSiElJuegoEsDoblePar(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(DoblePar.class), new Aleatorio(1));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(DoblePar.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test04AlAplicarUnComodinDeSumaMultiplciadorConJuegoTrioEsteSumaElMultplicadorSiElJuegoEsTrio(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(Trio.class), new Aleatorio(1));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Trio.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test05AlAplicarUnComodinDeSumaMultiplciadorConJuegoEscaleraEsteSumaElMultplicadorSiElJuegoEsEscalera(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(Escalera.class), new Aleatorio(1));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Escalera.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test06AlAplicarUnComodinDeSumaMultiplciadorConJuegoColorEsteSumaElMultplicadorSiElJuegoEsColor(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(Color.class), new Aleatorio(1));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Color.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test07AlAplicarUnComodinDeSumaMultiplciadorConJuegoEscaleraDeColorEsteSumaElMultplicadorSiElJuegoEsEscaleraDeColor(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(EscaleraDeColor.class), new Aleatorio(1));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(EscaleraDeColor.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test08AlAplicarUnComodinDeSumaMultiplciadorConJuegoEscaleraRealEsteSumaElMultplicadorSiElJuegoEsEscaleraReal(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(EscaleraReal.class), new Aleatorio(1));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(EscaleraReal.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test09AlAplicarUnComodinDeSumaMultiplciadorConJuegoFullHouseEsteSumaElMultplicadorSiElJuegoEsFullHouse(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(FullHouse.class), new Aleatorio(1));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(FullHouse.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test10AlAplicarUnComodinDeSumaMultiplciadorConJuegoPokerEsteSumaElMultplicadorSiElJuegoEsPoker(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(5, mock(Poker.class), new Aleatorio(1));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Poker.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test11AlAplicarDosComodinesDeSumaMultiplicadorDeDistintosJuegosSeSumanCorrectamente(){
        // Arrange
        Comodin comodin1 = new SumaMultiplicador(5, mock(CartaAlta.class), new Aleatorio(1));
        Comodin comodin2 = new SumaMultiplicador(6, mock(DoblePar.class), new Aleatorio(1));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 13);
        // Act
        comodin1.aplicarModificador(puntajeAModificar, mock(CartaAlta.class));
        comodin2.aplicarModificador(puntajeAModificar, mock(DoblePar.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test11AlAplicarUnComodinDeUnJuegoQueNoEsElDelComodinElPuntajeNoSeVeMofificado(){
        // Arrange
        Comodin comodin = new SumaMultiplicador(32130, mock(CartaAlta.class), new Aleatorio(1));
        Puntaje puntajeAModificar = new Puntaje(220, 232);
        Puntaje puntajeEsperado = new Puntaje(220, 232);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(DoblePar.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
}
