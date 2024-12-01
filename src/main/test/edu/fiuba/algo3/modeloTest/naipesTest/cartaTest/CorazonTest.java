package edu.fiuba.algo3.modeloTest.naipesTest.cartaTest;

import edu.fiuba.algo3.modelo.naipes.carta.Corazon;
import edu.fiuba.algo3.modelo.naipes.carta.Pica;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CorazonTest {
    @Test
    public void test01UnaInstaciaDeCorazonSabeRecocerSiOtraInstaciaEsDeCorazon(){
        //Arrange
        Corazon corazon = new Corazon();
        //Act
        boolean resultadoComparacion = corazon.esDeEstePalo(new Corazon());
        //Assert
        assertTrue(resultadoComparacion);
    }

    @Test
    public void test02UnaInstaciaDeCorazonSabeRecocerSiOtraInstaciaNoEsDeCorazon(){
        //Arrange
        Corazon corazon = new Corazon();
        //Act
        boolean resultadoComparacion = corazon.esDeEstePalo(new Pica());
        //Assert
        assertFalse(resultadoComparacion);
    }
}

