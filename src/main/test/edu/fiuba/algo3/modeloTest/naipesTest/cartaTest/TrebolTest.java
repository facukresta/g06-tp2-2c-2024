package edu.fiuba.algo3.modeloTest.naipesTest.cartaTest;

import edu.fiuba.algo3.modelo.naipes.carta.Diamante;
import edu.fiuba.algo3.modelo.naipes.carta.Trebol;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrebolTest {
    @Test
    public void test01UnaInstaciaDeTrebolSabeRecocerSiOtraInstaciaEsDeTrebol(){
        //Arrange
        Trebol corazon = new Trebol();
        //Act
        boolean resultadoComparacion = corazon.esDeEstePalo(new Trebol());
        //Assert
        assertTrue(resultadoComparacion);
    }

    @Test
    public void test02UnaInstaciaDeTrebolSabeRecocerSiOtraInstaciaNoEsDeTrebol(){
        //Arrange
        Trebol corazon = new Trebol();
        //Act
        boolean resultadoComparacion = corazon.esDeEstePalo(new Diamante());
        //Assert
        assertFalse(resultadoComparacion);
    }
}