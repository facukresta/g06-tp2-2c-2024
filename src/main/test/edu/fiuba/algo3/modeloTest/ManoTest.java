package edu.fiuba.algo3.modeloTest;

import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.*;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.SinTarot;
import edu.fiuba.algo3.modelo.tarot.Sumador;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class ManoTest {
    @Test
    public void test01AlCrearUnaManoEstaDebeEstarVacia(){
        // Arrange
        Mano mano = new Mano(8);
        int cantidadDeCartasEsperadas = 0;
        // Act
        int cantidadDeCartasObtenidas = mano.obtenerCantidadDeCartas();
        // Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }
    @Test
    public void test02AlAgregarOchoCartasALaManoDeberiaTenerOchoCartas(){
        // Arrange
        Mano mano = new Mano(8);
        int cantidadDeCartasEsperadas = 8;
        // Act
        for(int i = 1; i <= 8; i++) {
            mano.agregarCarta(mock(Carta.class));
        }
        int cantidadDeCartasObtenidas = mano.obtenerCantidadDeCartas();
        // Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }
    @Test
    public void test03AlAgregarNueveCartasALaManoDeberiaLanzarError(){
        // Arrange
        Mano mano = new Mano(8);
        for(int i = 1; i <= 8; i++) {
            mano.agregarCarta(mock(Carta.class));
        }
        // Act / Assert
        assertThrows(ManoLlenaException.class, () -> {
            mano.agregarCarta(mock(Carta.class));
        });
    }
    @Test
    public void test04AlAgregarOchoCartasALaManoYQuitarUnaDeberianHaber7(){
        // Arrange
        Mano mano = new Mano(8);
        int cantidadDeCartasEsperadas = 7;
        for(int i = 1; i <= 7; i++) {
            mano.agregarCarta(mock(Carta.class));
        }
        // Act
        Carta cartaMock = mock(Carta.class);
        when(cartaMock.sos(any())).thenReturn(true);
        mano.agregarCarta(cartaMock);
        mano.quitarCarta(cartaMock);
        int cantidadDeCartasObtenidas = mano.obtenerCantidadDeCartas();
        // Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }
    @Test
    public void test05LanzaErrorSiSeQuiereQuitarUnaCartaQueNoEstaEnLaMano(){
        // Arrange
        Mano mano = new Mano(8);
        Carta cartaEnLaManoMock = mock(Carta.class);
        Carta cartaNoEnLaManoMock = mock(Carta.class);
        when(cartaEnLaManoMock.sos(cartaNoEnLaManoMock)).thenReturn(false);
        mano.agregarCarta(cartaEnLaManoMock);
        // Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            mano.quitarCarta(cartaNoEnLaManoMock);
        });
    }
    @Test
    public void test06LanzaErrorSiSeQuiereQuitarUnaCartaEnUnaManoVacia(){
        // Arrange
        Mano mano = new Mano(8);
        // Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            mano.quitarCarta(mock(Carta.class));
        });
    }
    @Test
    public void test07LanzaErrorSiSeQuiereQuitarUnaCartaQueYaSeQuito(){
        // Arrange
        Mano mano = new Mano(8);
        Carta cartaEnLaMano = new Carta(8, new Trebol());
        mano.agregarCarta(cartaEnLaMano);
        mano.quitarCarta(cartaEnLaMano);
        // Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            mano.quitarCarta(cartaEnLaMano);
        });
    }
    @Test
    public void test08UnaManoPuedeQuitarUnaCartaSoloSabiendoSuNumeroYPalo() {
        // Arrange
        Mano mano = new Mano(8);
        int cantidadDeCartasEsperadas = 0;
        Carta cartaMock = mock(Carta.class);
        when(cartaMock.sos(any())).thenReturn(true);
        mano.agregarCarta(cartaMock);
        // Act
        mano.quitarCarta(cartaMock);
        int cantidadDeCartasObtenidas = mano.obtenerCantidadDeCartas();
        // Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }
    @Test
    public void test09AlAgregarleOchoCartasIgualesDeberianHaberOchoCartasEnLaMano() {
        // Arrange
        Mano mano = new Mano(8);
        int cantidadDeCartasEsperadas = 8;
        // Act
        for (int i = 1; i <= 8; i++) {
            mano.agregarCarta(mock(Carta.class));
        }
        int cantidadDeCartasObtenidas = mano.obtenerCantidadDeCartas();
        // Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }

    @Test
    public void test10AlElegirSeisCartasLaManoDeberiaLanzarError(){
        // Arrange
        Mano mano = new Mano(8);
        Carta cartaMock1 = new Carta(2,new Corazon());
        Carta cartaMock2 = new Carta(3,new Corazon());
        Carta cartaMock3 = new Carta(4,new Corazon());
        Carta cartaMock4 = new Carta(6,new Corazon());
        Carta cartaMock5 = new Carta(8,new Corazon());
        Carta cartaMock6 = new Carta(11,new Corazon());
        mano.agregarCarta(cartaMock1);
        mano.agregarCarta(cartaMock2);
        mano.agregarCarta(cartaMock3);
        mano.agregarCarta(cartaMock4);
        mano.agregarCarta(cartaMock5);
        mano.agregarCarta(cartaMock6);
        // Act / Assert
        mano.elegirCarta(cartaMock1);
        mano.elegirCarta(cartaMock2);
        mano.elegirCarta(cartaMock3);
        mano.elegirCarta(cartaMock4);
        mano.elegirCarta(cartaMock5);
        assertThrows(MaximoCartasSeleccionadasException.class, () -> {
            mano.elegirCarta(cartaMock6);
        });
    }

    @Test
    public void test11LanzaErrorSiSeQuiereElegirUnaCartaQueNoEstaEnLaMano(){
        // Arrange
        Mano mano = new Mano(8);
        Carta cartaEnLaMano = new Carta(8, new Trebol());
        Carta cartaNoEstaEnLaManoDe5 = new Carta(7, new Trebol());
        mano.agregarCarta(cartaEnLaMano);
        // Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            mano.elegirCarta(cartaNoEstaEnLaManoDe5);
        });
    }

    @Test
    public void test12AlJugarUnaManoConUnaCartaDevuelveElValorDelPuntajeDeLaCarta() {
        // Arrange
        Mano mano = new Mano(8);
        Puntaje puntajeEsperado = new Puntaje(10,1);
        Carta cartaMock = mock(Carta.class);
        when(cartaMock.obtenerPuntaje()).thenReturn(new Puntaje(5,1));
        when(cartaMock.sos(any())).thenReturn(true);
        mano.agregarCarta(cartaMock);
        mano.elegirCarta(cartaMock);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test13AlJugarUnaManoConCincoCartasDevuelveElValorDelPuntajeDeLaMano() {
        // Arrange
        Mano mano = new Mano(8);
        Puntaje puntajeEsperado = new Puntaje(39,1);
        Carta carta1 = new Carta(5, new Trebol());
        Carta carta2 = new Carta(7, new Diamante());
        Carta carta3 = new Carta(3, new Corazon());
        Carta carta4 = new Carta(1, new Pica());
        Carta carta5 = new Carta(9, new Trebol());
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta3);
        mano.agregarCarta(carta4);
        mano.agregarCarta(carta5);
        mano.agregarCarta(carta2);
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        mano.elegirCarta(carta3);
        mano.elegirCarta(carta4);
        mano.elegirCarta(carta5);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test14AlJugarUnaManoConDosCartasYUnComodinDeSumaMultiplicadorDevuelveElValorDelPuntajeDeLaManoMasEseMultiplicador() {
        // Arrange
        Mano mano = new Mano(8);
        Comodin comodin = new SumaMultiplicador(4, new Aleatorio(1));
        mano.agregarComodin(comodin);
        Puntaje puntajeEsperado = new Puntaje(17,4);
        Carta carta1 = new Carta(5, new Trebol());
        Carta carta2 = new Carta(7, new Diamante());
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test15AlJugarUnaManoConTresCartasYDosComodinDeSumaMultiplicadorDevuelveElValorDelPuntajeDeLaManoMasEsosMultiplicador() {
        // Arrange
        Mano mano = new Mano(8);
        Comodin comodin1 = new SumaMultiplicador(4, new Aleatorio(1));
        Comodin comodin2 = new SumaMultiplicador(12, new Aleatorio(1));
        mano.agregarComodin(comodin1);
        Puntaje puntajeEsperado = new Puntaje(36,19);
        Carta carta1 = new Carta(2, new Trebol());
        Carta carta2 = new Carta(2, new Diamante());
        Carta carta3 = new Carta(2, new Corazon());
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        mano.agregarCarta(carta3);
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        mano.elegirCarta(carta3);
        mano.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test16AlJugarUnaManoConTresCartasYUnComodinDeSumaMultiplicadorYOtroDeSumaMultiplicadorJuegoDevuelveElValorDelPuntajeDeLaManoMasEsosMultiplicador() {
        // Arrange
        Mano mano = new Mano(8);
        Comodin comodin1 = new SumaMultiplicador(4, new Aleatorio(1));
        Comodin comodin2 = new SumaMultiplicador(12, new Trio(), new Aleatorio(1));
        mano.agregarComodin(comodin1);
        Puntaje puntajeEsperado = new Puntaje(36,19);
        Carta carta1 = new Carta(2, new Trebol());
        Carta carta2 = new Carta(2, new Diamante());
        Carta carta3 = new Carta(2, new Corazon());
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        mano.agregarCarta(carta3);
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        mano.elegirCarta(carta3);
        mano.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test17AlDescartaUnaManoConTresCartasYUnComodinDeSumaPuntosDescarteDevuelveElValorDelPuntajeDelMultiplicador() {
        // Arrange
        Mano mano = new Mano(8);
        Comodin comodin = new SumaPuntosDescarte(4, new Aleatorio(1));
        mano.agregarComodin(comodin);
        Puntaje puntajeEsperado = new Puntaje(4,1);
        Carta carta1 = new Carta(2, new Trebol());
        Carta carta2 = new Carta(2, new Diamante());
        Carta carta3 = new Carta(2, new Corazon());
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        mano.agregarCarta(carta3);
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        mano.elegirCarta(carta3);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test18AlJugarUnaManoConCuatroCartasYDosComodinesDeSumaPuntosDescarteDevuelveElValorDelPuntajeDeEseMultiplicador() {
        // Arrange
        Mano mano = new Mano(8);
        Comodin comodin1 = new SumaPuntosDescarte(4, new Aleatorio(1));
        Comodin comodin2 = new SumaPuntosDescarte(12, new Aleatorio(1));
        mano.agregarComodin(comodin1);
        Puntaje puntajeEsperado = new Puntaje(16,1);
        Carta carta1 = new Carta(2, new Trebol());
        Carta carta2 = new Carta(2, new Diamante());
        Carta carta3 = new Carta(2, new Corazon());
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        mano.agregarCarta(carta3);
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        mano.elegirCarta(carta3);
        mano.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test19AlJugarUnaManoConCuatroCartasYUnComodinDeSumaPuntosNoSumaPuntosCuandoSeDescarta() {
        // Arrange
        Mano mano = new Mano(8);
        Comodin comodin = new SumaPuntos(4);
        mano.agregarComodin(comodin);
        Puntaje puntajeEsperado = new Puntaje(0,1);
        Carta carta1 = new Carta(2, new Trebol());
        Carta carta2 = new Carta(2, new Diamante());
        Carta carta3 = new Carta(2, new Corazon());
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        mano.agregarCarta(carta3);
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        mano.elegirCarta(carta3);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test20AlJugarUnaManoConCuatroCartasYUnComodinDeSumaPuntosDescarteNoSumaCuandoSeJuegaLaMano() {
        // Arrange
        Mano mano = new Mano(8);
        Comodin comodin = new SumaPuntosDescarte(100);
        mano.agregarComodin(comodin);
        Puntaje puntajeEsperado = new Puntaje(36,3);
        Carta carta1 = new Carta(2, new Trebol());
        Carta carta2 = new Carta(2, new Diamante());
        Carta carta3 = new Carta(2, new Corazon());
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        mano.agregarCarta(carta3);
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        mano.elegirCarta(carta3);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test21AlModificarUnJuegoConTarotSumadorEsteDevulveElValorCorrepondiente() {
        // Arrange
        Mano mano = new Mano(8);
        Tarot tarot = new Sumador(40, 15,new Par());
        Puntaje puntajeEsperado = new Puntaje(54,17);
        Carta carta1 = new Carta(2, new Trebol());
        Carta carta2 = new Carta(2, new Diamante());
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        mano.modificarJuego(tarot);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano();
        // Assert
        Tarot tarot1 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot1);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test21AlModificarUnJuegoConTarotSumadorYNoJugarEsteJuegoEsteNoDevulveElValorModificado() {
        // Arrange
        Mano mano = new Mano(8);
        Tarot tarot = new Sumador(40, 15,new Par());
        Puntaje puntajeEsperado = new Puntaje(36,3);
        Carta carta1 = new Carta(2, new Trebol());
        Carta carta2 = new Carta(2, new Diamante());
        Carta carta3 = new Carta(2, new Corazon());
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        mano.agregarCarta(carta3);
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        mano.elegirCarta(carta3);
        mano.modificarJuego(tarot);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano();
        // Assert
        Tarot tarot1 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot1);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test22AlModificarUnJuegoConTarotSumadorDeDescarteYOtroDeNoDescarteAlJugarLaManoSoloDevuleveELPuntajeModificadoPorNoDescarte() {
        // Arrange
        Mano mano = new Mano(8);
        Comodin comodin1 = new SumaPuntos(40);
        Comodin comodin2 = new SumaPuntosDescarte(50);
        Puntaje puntajeEsperado = new Puntaje(76,3);
        Carta carta1 = new Carta(2, new Trebol());
        Carta carta2 = new Carta(2, new Diamante());
        Carta carta3 = new Carta(2, new Corazon());
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        mano.agregarCarta(carta3);
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        mano.elegirCarta(carta3);
        mano.agregarComodin(comodin1);
        mano.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test23AlModificarUnJuegoConTarotSumadorDeDescarteYOtroDeNoDescarteAlDescartarLaManoSoloDevuleveELPuntajeModificadoPorDescarte() {
        // Arrange
        Mano mano = new Mano(8);
        Comodin comodin1 = new SumaPuntos(40);
        Comodin comodin2 = new SumaPuntosDescarte(50);
        Puntaje puntajeEsperado = new Puntaje(50,1);
        Carta carta1 = new Carta(2, new Trebol());
        Carta carta2 = new Carta(2, new Diamante());
        Carta carta3 = new Carta(2, new Corazon());
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        mano.agregarCarta(carta3);
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        mano.elegirCarta(carta3);
        mano.agregarComodin(comodin1);
        mano.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

}
