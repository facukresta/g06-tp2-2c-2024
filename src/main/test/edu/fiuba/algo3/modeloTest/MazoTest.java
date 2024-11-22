package edu.fiuba.algo3.modeloTest;

import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.naipes.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MazoTest {
    @Test
    public void test01UnMazoAlInstanciarseDebeEstarVacio(){
        //Arrange
        Mazo mazo = new Mazo();
        //Act
        Mano manoMock = mock(Mano.class);
        doNothing().when(manoMock).agregarCarta(any());
        //Assert
        assertThrows(CartasInsuficientesException.class, () -> {
            manoMock.agregarCartas(mazo.repartirCartas(1));
        });
    }
    @Test
    public void test02UnMazoAlPonerleCuatroCartasYPedirleCincoDebeLanzarError(){
        //Arrange
        Mazo mazo = new Mazo();
        mazo.agregarCartas(new ArrayList<>(List.of( mock(Carta.class),
                mock(Carta.class), mock(Carta.class), mock(Carta.class))));
        //Act
        Mano manoMock = mock(Mano.class);
        doNothing().when(manoMock).agregarCarta(any());
        manoMock.agregarCartas(mazo.repartirCartas(4));
        //Act / Assert
        assertThrows(CartasInsuficientesException.class, () -> {
            mazo.repartirCartas(1);
        });
    }
    @Test
    public void test03UnMazoAlPonerleCuarentaCartasYAlSacarleVeintiochoLeQuedanDoceCartas(){
        //Arrange
        Mazo mazo = new Mazo();
        Mano manoMock = mock(Mano.class);
        doNothing().when(manoMock).agregarCarta(any());
        for (int i = 1; i <= 40; i++) {
            mazo.agregarCarta(mock(Carta.class));
        }
        manoMock.agregarCartas(mazo.repartirCartas(28));
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
    public void test04UnMazoAlPonerleCincuentaYDosCartasYSacarleTreintaYCuatroAlMezclarTieneCincuentaYDosCartasParaUsar(){
        //Arrange
        Mazo mazo = new Mazo();
        Mano manoMock = mock(Mano.class);
        doNothing().when(manoMock).agregarCarta(any());
        for (int i = 1; i <= 52; i++) {
            mazo.agregarCarta(mock(Carta.class));
        }
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