package edu.fiuba.algo3.modeloTest.naipesTest.cartaTest;

import edu.fiuba.algo3.modelo.naipes.carta.Pica;
import edu.fiuba.algo3.modelo.naipes.carta.Trebol;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PicaTest {
    @Test
    public void test01UnaInstaciaDePicaSabeRecocerSiOtraInstaciaEsDePica(){
        //Arrange
        Pica corazon = new Pica();
        //Act
        boolean resultadoComparacion = corazon.esDeEstePalo(new Pica());
        //Assert
        assertTrue(resultadoComparacion);
    }

    @Test
    public void test02UnaInstaciaDePicaSabeRecocerSiOtraInstaciaNoEsDePica(){
        //Arrange
        Pica corazon = new Pica();
        //Act
        boolean resultadoComparacion = corazon.esDeEstePalo(new Trebol());
        //Assert
        assertFalse(resultadoComparacion);
    }
}