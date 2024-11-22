package edu.fiuba.algo3.modeloTest.comodinTest;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.comodin.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComodinTest {
    @Test
    public void test01NoSePuedeInstanciarUnComodinConCantidadNegativa(){
        // Arrange / Act / Assert
        assertThrows(CantidadComodinInvalida.class, () -> new SumaMultiplicador(-1));
    }
    @Test
    public void test02NoSePuedeInstanciarUnComodinConCantidadIgualA0(){
        // Arrange / Act / Assert
        assertThrows(CantidadComodinInvalida.class, () -> new SumaMultiplicadorDescarte(0));
    }
}
