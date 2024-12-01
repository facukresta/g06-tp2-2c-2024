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


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class ManoTest {
    @Test
    public void test01AlCrearUnaManoEstaDebeEstarVacia(){
        // Arrange
        Mano mano = new ManoDe8();
        int cantidadDeCartasEsperadas = 0;
        // Act
        int cantidadDeCartasObtenidas = mano.obtenerCantidadDeCartas();
        // Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }
    @Test
    public void test02AlAgregarOchoCartasALaManoDeberiaTenerOchoCartas(){
        // Arrange
        Mano mano = new ManoDe8();
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
        Mano mano = new ManoDe8();
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
        Mano mano = new ManoDe8();
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
        Mano mano = new ManoDe8();
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
        Mano mano = new ManoDe8();
        // Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            mano.quitarCarta(mock(Carta.class));
        });
    }
    @Test
    public void test07LanzaErrorSiSeQuiereQuitarUnaCartaQueYaSeQuito(){
        // Arrange
        Mano mano = new ManoDe8();
        Carta cartaMock = mock(Carta.class);
        when(cartaMock.sos(any())).thenReturn(true);
        mano.agregarCarta(cartaMock);
        mano.quitarCarta(cartaMock);
        // Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            mano.quitarCarta(cartaMock);
        });
    }
    @Test
    public void test08UnaManoPuedeQuitarUnaCartaSoloSabiendoSuNumeroYPalo() {
        // Arrange
        Mano mano = new ManoDe8();
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
        Mano mano = new ManoDe8();
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
    public void test10AlElegir6CartasYJugarLaManoSoloSeJueganLasCincoPrimeras() {
        // Arrange
        Mano mano = new ManoDe8();
        Puntaje puntajeEsperado = new Puntaje(39,1);
        Carta carta1 = new CartaInglesa(5, new Trebol());
        Carta carta2 = new CartaInglesa(7, new Diamante());
        Carta carta3 = new CartaInglesa(3, new Corazon());
        Carta carta4 = new CartaInglesa(1, new Pica());
        Carta carta5 = new CartaInglesa(9, new Trebol());
        Carta carta6 = new CartaInglesa(10, new Trebol());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5, carta6));
        mano.agregarCartas(cartas);
        mano.seleccionarCarta(carta1);
        mano.seleccionarCarta(carta2);
        mano.seleccionarCarta(carta3);
        mano.seleccionarCarta(carta4);
        mano.seleccionarCarta(carta5);
        mano.seleccionarCarta(carta6);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(new Comodinera());
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11LanzaErrorSiSeAgregaUnaCartaQueNoEstaEnLaMano(){
        // Arrange
        Mano mano = new ManoDe8();
        Carta cartaEnLaMano = mock(Carta.class);
        Carta cartaNoEstaEnLaMano = new CartaInglesa(7, new Trebol());
        mano.agregarCarta(cartaEnLaMano);

        // Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            mano.seleccionarCarta(cartaNoEstaEnLaMano);
        });
    }

    @Test
    public void test12AlJugarUnaManoConUnaCartaDevuelveElValorDelPuntajeDeLaCarta() {
        // Arrange
        Mano mano = new ManoDe8();
        Puntaje puntajeEsperado = new Puntaje(10,1);
        Carta carta1 = new CartaInglesa(5, new Trebol());
        mano.agregarCartas(new ArrayList<Carta>(List.of(carta1)));
        mano.seleccionarCarta(carta1);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(new Comodinera());
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test13AlJugarUnaManoConCincoCartasDevuelveElValorDelPuntajeDeLaMano() {
        // Arrange
        Mano mano = new ManoDe8();
        Puntaje puntajeEsperado = new Puntaje(39,1);
        Carta carta1 = new CartaInglesa(5, new Trebol());
        Carta carta2 = new CartaInglesa(7, new Diamante());
        Carta carta3 = new CartaInglesa(3, new Corazon());
        Carta carta4 = new CartaInglesa(1, new Pica());
        Carta carta5 = new CartaInglesa(9, new Trebol());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5));
        mano.agregarCartas(cartas);
        mano.seleccionarCarta(carta1);
        mano.seleccionarCarta(carta2);
        mano.seleccionarCarta(carta3);
        mano.seleccionarCarta(carta4);
        mano.seleccionarCarta(carta5);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(new Comodinera());
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test14AlJugarUnaManoConDosCartasYUnComodinDeSumaMultiplicadorDevuelveElValorDelPuntajeDeLaManoMasEseMultiplicador() {
        // Arrange
        Mano mano = new ManoDe8();
        Comodin comodin = new SumaMultiplicador(4, new Aleatorio(1), "");
        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(comodin);
        Puntaje puntajeEsperado = new Puntaje(17,4);
        Carta carta1 = new CartaInglesa(5, new Trebol());
        Carta carta2 = new CartaInglesa(7, new Diamante());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2));
        mano.agregarCartas(cartas);
        mano.seleccionarCarta(carta1);
        mano.seleccionarCarta(carta2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(comodinera);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test15AlJugarUnaManoConTresCartasYDosComodinDeSumaMultiplicadorDevuelveElValorDelPuntajeDeLaManoMasEsosMultiplicador() {
        // Arrange
        Mano mano = new ManoDe8();
        Comodin comodin1 = new SumaMultiplicador(4, new Aleatorio(1), "");
        Comodin comodin2 = new SumaMultiplicador(12, new Aleatorio(1), "");
        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(comodin1);
        Puntaje puntajeEsperado = new Puntaje(36,19);
        Carta carta1 = new CartaInglesa(2, new Trebol());
        Carta carta2 = new CartaInglesa(2, new Diamante());
        Carta carta3 = new CartaInglesa(2, new Corazon());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3));
        mano.agregarCartas(cartas);
        mano.seleccionarCarta(carta1);
        mano.seleccionarCarta(carta2);
        mano.seleccionarCarta(carta3);
        comodinera.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(comodinera);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test16AlJugarUnaManoConTresCartasYUnComodinDeSumaMultiplicadorYOtroDeSumaMultiplicadorJuegoDevuelveElValorDelPuntajeDeLaManoMasEsosMultiplicador() {
        // Arrange
        Mano mano = new ManoDe8();
        Comodin comodin1 = new SumaMultiplicador(4, new Aleatorio(1), "");
        Comodin comodin2 = new SumaMultiplicador(12, new Trio(), new Aleatorio(1), "");
        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(comodin1);
        Puntaje puntajeEsperado = new Puntaje(36,19);
        Carta carta1 = new CartaInglesa(2, new Trebol());
        Carta carta2 = new CartaInglesa(2, new Diamante());
        Carta carta3 = new CartaInglesa(2, new Corazon());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3));
        mano.agregarCartas(cartas);
        mano.seleccionarCarta(carta1);
        mano.seleccionarCarta(carta2);
        mano.seleccionarCarta(carta3);
        comodinera.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(comodinera);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test17AlDescartaUnaManoConTresCartasYUnComodinDeSumaPuntosDescarteDevuelveElValorDelPuntajeDelMultiplicador() {
        // Arrange
        Mano mano = new ManoDe8();
        Comodin comodin = new SumaPuntosDescarte(4, new Aleatorio(1), "");
        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(comodin);
        Puntaje puntajeEsperado = new Puntaje(4,1);
        Carta carta1 = new CartaInglesa(2, new Trebol());
        Carta carta2 = new CartaInglesa(2, new Diamante());
        Carta carta3 = new CartaInglesa(2, new Corazon());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3));
        mano.agregarCartas(cartas);
        mano.seleccionarCarta(carta1);
        mano.seleccionarCarta(carta2);
        mano.seleccionarCarta(carta3);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano(comodinera);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test18AlJugarUnaManoConCuatroCartasYDosComodinesDeSumaPuntosDescarteDevuelveElValorDelPuntajeDeEseMultiplicador() {
        // Arrange
        Mano mano = new ManoDe8();
        Comodin comodin1 = new SumaPuntosDescarte(4, new Aleatorio(1), "");
        Comodin comodin2 = new SumaPuntosDescarte(12, new Aleatorio(1), "");
        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(comodin1);
        Puntaje puntajeEsperado = new Puntaje(16,1);
        Carta carta1 = new CartaInglesa(2, new Trebol());
        Carta carta2 = new CartaInglesa(2, new Diamante());
        Carta carta3 = new CartaInglesa(2, new Corazon());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3));
        mano.agregarCartas(cartas);
        mano.seleccionarCarta(carta1);
        mano.seleccionarCarta(carta2);
        mano.seleccionarCarta(carta3);
        comodinera.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano(comodinera);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test19AlJugarUnaManoConCuatroCartasYUnComodinDeSumaPuntosNoSumaPuntosCuandoSeDescarta() {
        // Arrange
        Mano mano = new ManoDe8();
        Comodin comodin = new SumaPuntos(4, "");
        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(comodin);
        Puntaje puntajeEsperado = new Puntaje(0,1);
        Carta carta1 = new CartaInglesa(2, new Trebol());
        Carta carta2 = new CartaInglesa(2, new Diamante());
        Carta carta3 = new CartaInglesa(2, new Corazon());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3));
        mano.agregarCartas(cartas);
        mano.seleccionarCarta(carta1);
        mano.seleccionarCarta(carta2);
        mano.seleccionarCarta(carta3);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano(comodinera);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test20AlJugarUnaManoConCuatroCartasYUnComodinDeSumaPuntosDescarteNoSumaCuandoSeJuegaLaMano() {
        // Arrange
        Mano mano = new ManoDe8();
        Comodin comodin = new SumaPuntosDescarte(100, "");
        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(comodin);
        Puntaje puntajeEsperado = new Puntaje(36,3);
        Carta carta1 = new CartaInglesa(2, new Trebol());
        Carta carta2 = new CartaInglesa(2, new Diamante());
        Carta carta3 = new CartaInglesa(2, new Corazon());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3));
        mano.agregarCartas(cartas);
        mano.seleccionarCarta(carta1);
        mano.seleccionarCarta(carta2);
        mano.seleccionarCarta(carta3);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(comodinera);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test21AlModificarUnJuegoConTarotSumadorEsteDevulveElValorCorrepondiente() {
        // Arrange
        Mano mano = new ManoDe8();
        Tarot tarot = new Sumador(40, 15,new Par(), "");
        Puntaje puntajeEsperado = new Puntaje(54,17);
        Carta carta1 = new CartaInglesa(2, new Trebol());
        Carta carta2 = new CartaInglesa(2, new Diamante());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2));
        mano.agregarCartas(cartas);
        mano.modificarJuego(tarot);
        mano.seleccionarCarta(carta1);
        mano.seleccionarCarta(carta2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(new Comodinera());
        // Assert
        Tarot tarot1 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot1);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test21AlModificarUnJuegoConTarotSumadorYNoJugarEsteJuegoEsteNoDevulveElValorModificado() {
        // Arrange
        Mano mano = new ManoDe8();
        Tarot tarot = new Sumador(40, 15,new Par(), "");
        Puntaje puntajeEsperado = new Puntaje(36,3);
        Carta carta1 = new CartaInglesa(2, new Trebol());
        Carta carta2 = new CartaInglesa(2, new Diamante());
        Carta carta3 = new CartaInglesa(2, new Corazon());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3));
        mano.agregarCartas(cartas);
        mano.seleccionarCarta(carta1);
        mano.seleccionarCarta(carta2);
        mano.seleccionarCarta(carta3);
        mano.modificarJuego(tarot);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(new Comodinera());
        // Assert
        Tarot tarot1 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot1);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test22AlModificarUnJuegoConTarotSumadorDeDescarteYOtroDeNoDescarteAlJugarLaManoSoloDevuleveELPuntajeModificadoPorNoDescarte() {
        // Arrange
        Mano mano = new ManoDe8();
        Comodin comodin1 = new SumaPuntos(40, "");
        Comodin comodin2 = new SumaPuntosDescarte(50, "");
        Puntaje puntajeEsperado = new Puntaje(76,3);
        Carta carta1 = new CartaInglesa(2, new Trebol());
        Carta carta2 = new CartaInglesa(2, new Diamante());
        Carta carta3 = new CartaInglesa(2, new Corazon());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3));
        mano.agregarCartas(cartas);
        mano.seleccionarCarta(carta1);
        mano.seleccionarCarta(carta2);
        mano.seleccionarCarta(carta3);
        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(comodin1);
        comodinera.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(comodinera);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test23AlModificarUnJuegoConTarotSumadorDeDescarteYOtroDeNoDescarteAlDescartarLaManoSoloDevuleveELPuntajeModificadoPorDescarte() {
        // Arrange
        Mano mano = new ManoDe8();
        Comodin comodin1 = new SumaPuntos(40, "");
        Comodin comodin2 = new SumaPuntosDescarte(50, "");
        Puntaje puntajeEsperado = new Puntaje(50,1);
        Carta carta1 = new CartaInglesa(2, new Trebol());
        Carta carta2 = new CartaInglesa(2, new Diamante());
        Carta carta3 = new CartaInglesa(2, new Corazon());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3));
        mano.agregarCartas(cartas);
        mano.seleccionarCarta(carta1);
        mano.seleccionarCarta(carta2);
        mano.seleccionarCarta(carta3);
        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(comodin1);
        comodinera.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano(comodinera);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
