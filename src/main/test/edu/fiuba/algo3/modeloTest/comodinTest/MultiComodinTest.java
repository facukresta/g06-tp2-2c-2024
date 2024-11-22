package edu.fiuba.algo3.modeloTest.comodinTest;

import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.juego.CartaAlta;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiComodinTest {
    @Test
    public void test01AlTenerUnComodinSimpleSeAplicaCorrectamente(){
        // Arrange
        MultiComodin multiComodin = new MultiComodin();
        multiComodin.componerComodin(new SumaMultiplicador(5));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        multiComodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test02AlTenerUnComodinDobleSeAplicaCorrectamente(){
        // Arrange
        MultiComodin multiComodin = new MultiComodin();
        multiComodin.componerComodin(new SumaMultiplicador(5));
        multiComodin.componerComodin(new SumaPuntos(5));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 7);
        // Act
        multiComodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test03AlTenerUnComodinVacioNoModificaElPuntaje(){
        // Arrange
        MultiComodin multiComodin = new MultiComodin();
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        multiComodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
}
