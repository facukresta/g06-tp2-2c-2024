package edu.fiuba.algo3.modeloTest.comodinTest;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
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
        Comodin comodin = new SumaPuntos(5);
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(CartaAlta.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test02AlAplicarDosComodinesDeSumaPuntosEstosSumanLosPuntos(){
        // Arrange
        Comodin comodin1 = new SumaPuntos(5);
        Comodin comodin2 = new SumaPuntos(10);
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(25, 2);
        // Act
        comodin1.aplicarModificador(puntajeAModificar, mock(CartaAlta.class));
        comodin2.aplicarModificador(puntajeAModificar, mock(SinJuego.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test03AlAplicarUnComodinDeSumaPuntosAUnJuegoEspecificoEsteSumaLosPuntosYaQueEsElJuego(){
        // Arrange
        Comodin comodin = new SumaPuntos(5,mock(CartaAlta.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(CartaAlta.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test04AlAplicarUnComodinDeSumaPuntosAUnJuegoEspecificoEsteNoSumaLosPuntosYaQueNoEsElJuego(){
        // Arrange
        Comodin comodin = new SumaPuntos(5,mock(CartaAlta.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Escalera.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test05AlAplicarUnComodinDeSumaPuntosConProbablidadEsteSumaLosPuntosYaQueSeDaLaProbabilidad(){
        // Arrange
        Aleatorio aleatorioMock = mock(Aleatorio.class);
        when(aleatorioMock.sucede()).thenReturn(true);
        Comodin comodin = new SumaPuntos(5, aleatorioMock);
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(CartaAlta.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test06AlAplicarUnComodinDeSumaPuntosConProbablidadEsteNoSumaLosPuntosYaQueNoSeDaLaProbabilidad(){
        // Arrange
        Aleatorio aleatorioMock = mock(Aleatorio.class);
        when(aleatorioMock.sucede()).thenReturn(false);
        Comodin comodin = new SumaPuntos(5, aleatorioMock);
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(CartaAlta.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test07AlAplicarUnComodinDeSumaPuntosConUnaProbabilidadQueSeDaYUnJuegoEspecificoQueTambienSeDaSeAplicaCorrectamente(){
        // Arrange
        Aleatorio aleatorioMock = mock(Aleatorio.class);
        when(aleatorioMock.sucede()).thenReturn(true);
        Comodin comodin = new SumaPuntos(6, new Par(), aleatorioMock);
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
        Aleatorio aleatorioMock = mock(Aleatorio.class);
        when(aleatorioMock.sucede()).thenReturn(true);
        Comodin comodin = new SumaPuntos(6, new CartaAlta(), aleatorioMock);
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
        Aleatorio aleatorioMock = mock(Aleatorio.class);
        when(aleatorioMock.sucede()).thenReturn(false);
        Comodin comodin = new SumaPuntos(6, new Par(), aleatorioMock);
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
        Aleatorio aleatorioMock = mock(Aleatorio.class);
        when(aleatorioMock.sucede()).thenReturn(false);
        Comodin comodin = new SumaPuntos(6, new CartaAlta(), aleatorioMock);
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
        Comodin comodin = new SumaPuntos(5, mock(CartaAlta.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(CartaAlta.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test12AlAplicarUnComodinDeSumaPuntosConJuegoParEsteSumaElMultplicadorSiElJuegoEsPar(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, mock(Par.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Par.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test13AlAplicarUnComodinDeSumaPuntosConJuegoDobleParEsteSumaElMultplicadorSiElJuegoEsDoblePar(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, mock(DoblePar.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(DoblePar.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test14AlAplicarUnComodinDeSumaPuntosConJuegoTrioEsteSumaElMultplicadorSiElJuegoEsTrio(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, mock(Trio.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Trio.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test15AlAplicarUnComodinDeSumaPuntosConJuegoEscaleraEsteSumaElMultplicadorSiElJuegoEsEscalera(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, mock(Escalera.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Escalera.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test16AlAplicarUnComodinDeSumaPuntosConJuegoColorEsteSumaElMultplicadorSiElJuegoEsColor(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, mock(Color.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Color.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test17AlAplicarUnComodinDeSumaPuntosConJuegoEscaleraDeColorEsteSumaElMultplicadorSiElJuegoEsEscaleraDeColor(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, mock(EscaleraDeColor.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);

        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(EscaleraDeColor.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test18AlAplicarUnComodinDeSumaPuntosConJuegoEscaleraRealEsteSumaElMultplicadorSiElJuegoEsEscaleraReal(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, mock(EscaleraReal.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);

        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(EscaleraReal.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test19AlAplicarUnComodinDeSumaPuntosConJuegoFullHouseEsteSumaElMultplicadorSiElJuegoEsFullHouse(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, mock(FullHouse.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);

        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(FullHouse.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test20AlAplicarUnComodinDeSumaPuntosConJuegoPokerEsteSumaElMultplicadorSiElJuegoEsPoker(){
        // Arrange
        Comodin comodin = new SumaPuntos(5, mock(Poker.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 2);

        // Act
        comodin.aplicarModificador(puntajeAModificar, mock(Poker.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test21AlAplicarDosComodinesDeSumaPuntosDeDistintosJuegosSeSumanCorrectamente(){
        // Arrange
        Comodin comodin1 = new SumaPuntos(5, mock(CartaAlta.class));
        Comodin comodin2 = new SumaPuntos(6, mock(DoblePar.class));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(21, 2);

        // Act
        comodin1.aplicarModificador(puntajeAModificar, mock(CartaAlta.class));
        comodin2.aplicarModificador(puntajeAModificar, mock(DoblePar.class));
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
}
