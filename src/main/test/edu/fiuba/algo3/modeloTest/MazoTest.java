package edu.fiuba.algo3.modeloTest;

import edu.fiuba.algo3.modelo.naipes.CartasInsuficientesException;
import edu.fiuba.algo3.modelo.naipes.Mano;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MazoTest {
    @Test
    public void test01UnMazoAlInstanciarseDebeTenerCicuentaYDosCartas(){
        //Arrange
        Mazo mazo = new Mazo();
        //Act
        Mano manoMock = mock(Mano.class);
        doNothing().when(manoMock).agregarCarta(any());
        manoMock.agregarCartas(mazo.repartirCartas(52));
        //Assert
        assertThrows(CartasInsuficientesException.class, () -> {
            manoMock.agregarCartas(mazo.repartirCartas(1));
        });
    }
    @Test
    public void test02UnMazoAlSacarleCuarentaYOchoCartasYPedirleCincoDebeLanzarError(){
        //Arrange
        Mazo mazo = new Mazo();
        //Act
        Mano manoMock = mock(Mano.class);
        doNothing().when(manoMock).agregarCarta(any());
        manoMock.agregarCartas(mazo.repartirCartas(48));
        //Act / Assert
        assertThrows(CartasInsuficientesException.class, () -> {
            mazo.repartirCartas(5);
        });
    }
    @Test
    public void test03UnMazoAlSacarleCuarentaLeQuedanDoceCartas(){
        //Arrange
        Mazo mazo = new Mazo();
        Mano manoMock = mock(Mano.class);
        doNothing().when(manoMock).agregarCarta(any());
        manoMock.agregarCartas(mazo.repartirCartas(40));
        // Act
        assertDoesNotThrow(() -> {
            manoMock.agregarCartas(mazo.repartirCartas(12));
        });
        //Assert
        assertThrows(CartasInsuficientesException.class, () -> {
            mazo.repartirCartas(1);
        });
    }
    @Test
    public void test04UnMazoAlSacarleTreitaYCuatroCartasYMezclarTieneCincuentaYDosCartasParaUsar(){
        //Arrange
        Mazo mazo = new Mazo();
        Mano manoMock = mock(Mano.class);
        doNothing().when(manoMock).agregarCarta(any());
        manoMock.agregarCartas(mazo.repartirCartas(34));
        mazo.mezclar();
        // Act
        assertDoesNotThrow(() -> {
            manoMock.agregarCartas(mazo.repartirCartas(52));
        });
        //Assert
        assertThrows(CartasInsuficientesException.class, () -> {
            mazo.repartirCartas(1);
        });
    }
}