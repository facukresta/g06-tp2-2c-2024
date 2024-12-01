package edu.fiuba.algo3.modeloTest.comodinTest;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.aleatorio.Ejecucion;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.SinComodin;
import edu.fiuba.algo3.modelo.comodin.SumaPuntos;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class SinComodinTest {

    @Test
    public void test01AlAplicarUnComodinSinComodinEsteDevuelveElMismoPuntajeSinModificar() {
        // Arrange
        Comodin comodin = new SinComodin();
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new SinJuego());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test02AlAplicarUnComodinSinComodinEsteDevuelveElMismoPuntajeSinModificarSeaCualSeaElJuego() {
        // Arrange
        Comodin comodin = new SinComodin();
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        comodin.aplicarModificador(puntajeAModificar, new Color());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
}
