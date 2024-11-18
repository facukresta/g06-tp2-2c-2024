package edu.fiuba.algo3.modeloTest.comodinTest;

import edu.fiuba.algo3.modelo.comodin.CartaComodin;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.SumaMultiplicador;
import edu.fiuba.algo3.modelo.comodin.SumaPuntos;
import edu.fiuba.algo3.modelo.juego.CartaAlta;
import edu.fiuba.algo3.modelo.juego.SinJuego;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartaComodinTest {
    @Test
    public void test01AlTenerUnComodinSimpleSeAplicaCorrectamente(){
        // Arrange
        CartaComodin cartaComodin = new CartaComodin(new ArrayList<>(List.of(new SumaMultiplicador(5))));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 7);
        // Act
        cartaComodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test02AlTenerUnComodinDobleSeAplicaCorrectamente(){
        // Arrange
        CartaComodin cartaComodin = new CartaComodin(new ArrayList<>(List.of(new SumaMultiplicador(5), new SumaPuntos(5))));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(15, 7);
        // Act
        cartaComodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test03AlTenerUnComodinVacioNoModificaElPuntaje(){
        // Arrange
        CartaComodin cartaComodin = new CartaComodin(new ArrayList<>(List.of()));
        Puntaje puntajeAModificar = new Puntaje(10, 2);
        Puntaje puntajeEsperado = new Puntaje(10, 2);
        // Act
        cartaComodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        // Assert
        assertTrue(puntajeAModificar.tenesMismoPuntaje(puntajeEsperado));
    }
}
