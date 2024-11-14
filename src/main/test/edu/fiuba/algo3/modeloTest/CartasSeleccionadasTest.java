package edu.fiuba.algo3.modeloTest;

import edu.fiuba.algo3.modelo.naipes.CartaNoEnManoException;
import edu.fiuba.algo3.modelo.naipes.CartasSeleccionadas;
import edu.fiuba.algo3.modelo.naipes.MaximoCartasSeleccionadasException;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CartasSeleccionadasTest {
    @Test
    public void test01AlCrearUnaCartasSeleccionadasEstaDebeEstarVacia(){
        // Arrange
        CartasSeleccionadas mano = new CartasSeleccionadas();
        int cantidadDeCartasEsperadas = 0;
        // Act
        int cantidadDeCartasObtenidas = mano.obtenerCantidadDeCartas();
        // Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }
    @Test
    public void test02AlAgregarCincoCartasALaCartasSeleccionadasDeberiaTenerCincoCartas(){
        // Arrange
        CartasSeleccionadas mano = new CartasSeleccionadas();
        int cantidadDeCartasEsperadas = 5;
        // Act
        for(int i = 1; i <= 5; i++) {
            mano.agregarCarta(new Carta(i, new Trebol()));
        }
        int cantidadDeCartasObtenidas = mano.obtenerCantidadDeCartas();
        // Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }
    @Test
    public void test03AlAgregarSeisCartasALaCartasSeleccionadasDeberiaLanzarError(){
        // Arrange
        CartasSeleccionadas mano = new CartasSeleccionadas();
        for(int i = 1; i <= 5; i++) {
            mano.agregarCarta(new Carta(i, new Trebol()));
        }
        // Act / Assert
        assertThrows(MaximoCartasSeleccionadasException.class, () -> {
            mano.agregarCarta(new Carta(6, new Trebol()));
        });
    }
    @Test
    public void test04AlAgregarCincoCartasALaCartasSeleccionadasYQuitarUnaDeberianHaberCuatro(){
        // Arrange
        CartasSeleccionadas mano = new CartasSeleccionadas();
        int cantidadDeCartasEsperadas = 4;
        for(int i = 1; i <= 4; i++) {
            mano.agregarCarta(new Carta(i, new Trebol()));
        }
        // Act
        Carta carta = new Carta(5, new Trebol());
        mano.agregarCarta(carta);
        mano.quitarCarta(carta);
        int cantidadDeCartasObtenidas = mano.obtenerCantidadDeCartas();
        // Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }
    @Test
    public void test05LanzaErrorSiSeQuiereQuitarUnaCartaQueNoEstaEnLaCartasSeleccionadas(){
        // Arrange
        CartasSeleccionadas mano = new CartasSeleccionadas();
        Carta cartaEnLaCartasSeleccionadas = new Carta(8, new Trebol());
        Carta cartaNoEstaEnLaCartasSeleccionadas = new Carta(7, new Trebol());
        mano.agregarCarta(cartaEnLaCartasSeleccionadas);
        // Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            mano.quitarCarta(cartaNoEstaEnLaCartasSeleccionadas);
        });
    }
    @Test
    public void test06LanzaErrorSiSeQuiereQuitarUnaCartaEnUnaCartasSeleccionadasVacia(){
        // Arrange
        CartasSeleccionadas mano = new CartasSeleccionadas();
        // Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            mano.quitarCarta(new Carta(8, new Trebol()));
        });
    }
    @Test
    public void test07LanzaErrorSiSeQuiereQuitarUnaCartaQueYaSeQuito(){
        // Arrange
        CartasSeleccionadas mano = new CartasSeleccionadas();
        Carta cartaEnLaCartasSeleccionadasMock = mock(Carta.class);
        when(cartaEnLaCartasSeleccionadasMock.sos(any())).thenReturn(true);
        mano.agregarCarta(cartaEnLaCartasSeleccionadasMock);
        mano.quitarCarta(cartaEnLaCartasSeleccionadasMock);
        // Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            mano.quitarCarta(cartaEnLaCartasSeleccionadasMock);
        });
    }
    @Test
    public void test08UnaCartasSeleccionadasPuedeQuitarUnaCartaSoloSabiendoSuNumeroYPalo() {
        // Arrange
        CartasSeleccionadas mano = new CartasSeleccionadas();
        int cantidadDeCartasEsperadas = 0;
        Carta cartaEnLaCartasSeleccionadasMock = mock(Carta.class);
        when(cartaEnLaCartasSeleccionadasMock.sos(any())).thenReturn(true);
        mano.agregarCarta(cartaEnLaCartasSeleccionadasMock);
        // Act
        mano.quitarCarta(cartaEnLaCartasSeleccionadasMock);
        int cantidadDeCartasObtenidas = mano.obtenerCantidadDeCartas();
        // Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }
    @Test
    public void test09AlJugarUnaManoConUnaCartaDevuelveElValorDelPuntajeDeLaCarta() {
        // Arrange
        CartasSeleccionadas mano = new CartasSeleccionadas();
        int valorEsperado = 10;
        Carta cartaEnLaCartasSeleccionadasMock = mock(Carta.class);
        when(cartaEnLaCartasSeleccionadasMock.obtenerPuntaje()).thenReturn(new Puntaje(5, 1));
        mano.agregarCarta(cartaEnLaCartasSeleccionadasMock);
        // Act
        int valorObtenido = mano.jugarMano();
        // Assert
        assertEquals(valorEsperado, valorObtenido);
    }
    @Test
    public void test09AlJugarUnaManoConCincoCartasDevuelveElValorDelPuntajeDeLaMano() {
        // Arrange
        CartasSeleccionadas mano = new CartasSeleccionadas();
        int valorEsperado = 30;
        mano.agregarCarta(new Carta(5, new Trebol()));
        mano.agregarCarta(new Carta(7, new Diamante()));
        mano.agregarCarta(new Carta(3, new Corazon()));
        mano.agregarCarta(new Carta(1, new Pica()));
        mano.agregarCarta(new Carta(9, new Trebol()));
        // Act
        int valorObtenido = mano.jugarMano();
        // Assert
        assertEquals(valorEsperado, valorObtenido);
    }
}
