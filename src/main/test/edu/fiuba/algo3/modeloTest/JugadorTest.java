//Pruebas anteriores de Jugador. Falta trasladarlas a ManoTest.java

/*package edu.fiuba.algo3.modeloTest;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.naipes.CartaNoEnManoException;
import edu.fiuba.algo3.modelo.naipes.Mano;
import edu.fiuba.algo3.modelo.naipes.MaximoCartasSeleccionadasException;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.naipes.carta.Trebol;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


public class JugadorTest {
    @Test
    public void test01UnJugadorConUnaCartaEnSuManoPuedeSeleccionarla(){
        //Arrange
        Jugador jugador = new Jugador();
        Mano mano = new Mano(8);
        Carta cartaMock = mock(Carta.class);
        when(cartaMock.sos(any())).thenReturn(true);
        mano.agregarCarta(cartaMock);
        jugador.establecerMano(mano);
        //Act / Assert
        assertDoesNotThrow(() -> jugador.elegirCarta(cartaMock));
    }
    @Test
    public void test02UnJugadorConUnaCartaEnSuManoPuedeSeleccionarlaYDeseleccionarla(){
        //Arrange
        Jugador jugador = new Jugador();
        Mano mano = new Mano(8);
        Carta cartaMock = mock(Carta.class);
        when(cartaMock.sos(any())).thenReturn(true);
        mano.agregarCarta(cartaMock);
        jugador.establecerMano(mano);
        jugador.elegirCarta(cartaMock);
        //Act / Assert
        assertDoesNotThrow(() -> jugador.deseleccionarCarta(cartaMock));
    }
    @Test
    public void test03UnJugadorConUnaManoVaciaLanzaErrorSiSeleccionaUnaCarta(){
        //Arrange
        Jugador jugador = new Jugador();
        Mano mano = new Mano(8);
        jugador.establecerMano(mano);
        //Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            jugador.elegirCarta(mock(Carta.class));
        });
    }
    @Test
    public void test04UnJugadorConUnaManoVaciaLanzaErrorSiDeseleccionaUnaCarta(){
        //Arrange
        Jugador jugador = new Jugador();
        Mano mano = new Mano(8);
        jugador.establecerMano(mano);
        //Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            jugador.deseleccionarCarta(mock(Carta.class));
        });
    }
    @Test
    public void test05UnJugadorQueSeleccionoCincoCartasNoPuedeSeleccionarlUnaMas(){
        //Arrange
        Jugador jugador = new Jugador();
        Mano mano = new Mano(8);
        for (int i = 1; i <= 6; i++) {
            mano.agregarCarta(new Carta(i, new Trebol()));
        }
        jugador.establecerMano(mano);
        //Act
        for (int i = 1; i <= 5; i++) {
            jugador.elegirCarta(new Carta(i, new Trebol()));
        }
        // Assert
        assertThrows(MaximoCartasSeleccionadasException.class, () -> {
            jugador.elegirCarta(new Carta(6, new Trebol()));;
        });
    }
    @Test
    public void test06UnJugadorQueSeleccionoUnaCartaSeleccioanadaAlVolverlaASeleccionarLaDeselecciona(){
        //Arrange
        Jugador jugador = new Jugador();
        Mano mano = new Mano(8);
        Carta cartaMock = mock(Carta.class);
        when(cartaMock.sos(any())).thenReturn(true);
        int cantidadDeCartasEsperadas = 0;
        mano.agregarCarta(cartaMock);
        jugador.establecerMano(mano);
        jugador.elegirCarta(cartaMock);
        // Act
        jugador.elegirCarta(cartaMock);
        int cantidadDeCartasObtenidas = jugador.obtenerCantidadDeCartasSeleccionadas();
        // Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }
    @Test
    public void test07UnJugadorQueSeleccionaUnCincoYJuegaLaManoTieneDiezDePuntaje(){
        //Arrange
        Jugador jugador = new Jugador();
        Mano mano = new Mano(8);
        Carta cartaMock = mock(Carta.class);
        when(cartaMock.sos(any())).thenReturn(true);
        when(cartaMock.obtenerPuntaje()).thenReturn(new Puntaje(5,1));
        int puntajeEsperado = 10;
        mano.agregarCarta(cartaMock);
        jugador.establecerMano(mano);
        jugador.elegirCarta(cartaMock);
        // Act
        int puntajeObtenido = jugador.jugarMano();
        // Assert
        assertEquals(puntajeEsperado, puntajeObtenido);
    }
    @Test
    public void test08UnJugadorQueSeleccionaUnDiezYJuegaLaManoTieneQuinceDePuntaje(){
        //Arrange
        Jugador jugador = new Jugador();
        Mano mano = new Mano(8);
        Carta cartaMock = mock(Carta.class);
        when(cartaMock.sos(any())).thenReturn(true);
        when(cartaMock.obtenerPuntaje()).thenReturn(new Puntaje(10,1));
        int puntajeEsperado = 15;
        mano.agregarCarta(cartaMock);
        jugador.establecerMano(mano);
        jugador.elegirCarta(cartaMock);
        // Act
        int puntajeObtenido = jugador.jugarMano();
        // Assert
        assertEquals(puntajeEsperado, puntajeObtenido);
    }
    @Test
    public void test09UnJugadorQueSeleccionaColorYJuegaLaManoTieneDoscientosYCuatroDePuntaje(){
        //Arrange
        Jugador jugador = new Jugador();
        Mano mano = new Mano(8);
        int puntajeEsperado = 204;
        mano.agregarCarta(new Carta(1, new Trebol()));
        mano.agregarCarta(new Carta(2, new Trebol()));
        mano.agregarCarta(new Carta(3, new Trebol()));
        mano.agregarCarta(new Carta(4, new Trebol()));
        mano.agregarCarta(new Carta(6, new Trebol()));
        jugador.establecerMano(mano);
        jugador.elegirCarta(new Carta(1, new Trebol()));
        jugador.elegirCarta(new Carta(2, new Trebol()));
        jugador.elegirCarta(new Carta(3, new Trebol()));
        jugador.elegirCarta(new Carta(4, new Trebol()));
        jugador.elegirCarta(new Carta(6, new Trebol()));
        // Act
        int puntajeObtenido = jugador.jugarMano();
        // Assert
        assertEquals(puntajeEsperado, puntajeObtenido);
    }
    @Test
    public void test10UnJugadorQueSeLeEstableceUnaManoTieneSoloEsaMano(){
        //Arrange
        Jugador jugador = new Jugador();
        Mano mano = new Mano(8);
        mano.agregarCarta(new Carta(1, new Trebol()));
        mano.agregarCarta(new Carta(2, new Trebol()));
        jugador.establecerMano(mano);
        Mano mano2 = new Mano(8);
        mano2.agregarCarta(new Carta(3, new Trebol()));
        jugador.elegirCarta(new Carta(1, new Trebol()));
        jugador.elegirCarta(new Carta(2, new Trebol()));
        // Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            jugador.elegirCarta(new Carta(3, new Trebol()));
        });
    }

}
 */