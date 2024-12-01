package edu.fiuba.algo3.modeloTest.naipesTest.cartaTest;

import edu.fiuba.algo3.modelo.naipes.carta.Corazon;
import edu.fiuba.algo3.modelo.naipes.carta.Diamante;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiamanteTest {
    @Test
    public void test01UnaInstaciaDeDiamanteSabeRecocerSiOtraInstaciaEsDeDiamante(){
        //Arrange
        Diamante corazon = new Diamante();
        //Act
        boolean resultadoComparacion = corazon.esDeEstePalo(new Diamante());
        //Assert
        assertTrue(resultadoComparacion);
    }

    @Test
    public void test02UnaInstaciaDeDiamanteSabeRecocerSiOtraInstaciaNoEsDeDiamante(){
        //Arrange
        Diamante corazon = new Diamante();
        //Act
        boolean resultadoComparacion = corazon.esDeEstePalo(new Corazon());
        //Assert
        assertFalse(resultadoComparacion);
    }
}