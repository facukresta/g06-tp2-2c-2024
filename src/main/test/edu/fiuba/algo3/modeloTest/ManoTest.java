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
        Mano mano = new Mano();
        int cantidadDeCartasEsperadas = 0;
        // Act
        int cantidadDeCartasObtenidas = mano.obtenerCantidadDeCartas();
        // Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }
    @Test
    public void test02AlAgregarOchoCartasALaManoDeberiaTenerOchoCartas(){
        // Arrange
        Mano mano = new Mano();
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
        Mano mano = new Mano();
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
        Mano mano = new Mano();
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
        Mano mano = new Mano();
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
        Mano mano = new Mano();
        // Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            mano.quitarCarta(new Carta(8, new Trebol()));
        });
    }
    @Test
    public void test07LanzaErrorSiSeQuiereQuitarUnaCartaQueYaSeQuito(){
        // Arrange
        Mano mano = new Mano();
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
        Mano mano = new Mano();
        int cantidadDeCartasEsperadas = 0;
        Carta carta = new Carta(8, new Trebol());
        mano.agregarCarta(carta);
        // Act
        mano.quitarCarta(carta);
        int cantidadDeCartasObtenidas = mano.obtenerCantidadDeCartas();
        // Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }
    @Test
    public void test09AlAgregarleOchoCartasIgualesDeberianHaberOchoCartasEnLaMano() {
        // Arrange
        Mano mano = new Mano();
        int cantidadDeCartasEsperadas = 8;
        // Act
        for (int i = 1; i <= 8; i++) {
            mano.agregarCarta(new Carta(i, new Corazon()));
        }
        int cantidadDeCartasObtenidas = mano.obtenerCantidadDeCartas();
        // Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }

    @Test
    public void test10AlElegirSeisCartasLaManoDeberiaLanzarError(){
        // Arrange
        Mano mano = new Mano();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(2,new Corazon()), new Carta(3,new Corazon()),
                new Carta(4,new Corazon()), new Carta(6,new Corazon()), new Carta(8,new Corazon()),
                new Carta(11,new Corazon())));
        mano.agregarCartas(cartas);
        Juego juego = Juego.chequearJuego(cartas);
        // Act / Assert
        assertThrows(MaximoCartasSeleccionadasException.class, () -> {
            mano.jugarMano(cartas, juego);
        });
    }

    @Test
    public void test11LanzaErrorSiSeJugarUnaCartaQueNoEstaEnLaMano(){
        // Arrange
        Mano mano = new Mano();
        Carta cartaEnLaMano = new Carta(8, new Trebol());
        ArrayList<Carta> cartaNoEstaEnLaManoDe5 = new ArrayList<>(List.of(new Carta(7, new Trebol())));
        mano.agregarCarta(cartaEnLaMano);
        Juego juego = Juego.chequearJuego(cartaNoEstaEnLaManoDe5);
        // Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            mano.jugarMano(cartaNoEstaEnLaManoDe5, juego);
        });
    }

    @Test
    public void test12AlJugarUnaManoConUnaCartaDevuelveElValorDelPuntajeDeLaCarta() {
        // Arrange
        Mano mano = new Mano();
        Puntaje puntajeEsperado = new Puntaje(10,1);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(5, new Trebol())));
        mano.agregarCartas(cartas);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test13AlJugarUnaManoConCincoCartasDevuelveElValorDelPuntajeDeLaMano() {
        // Arrange
        Mano mano = new Mano();
        Puntaje puntajeEsperado = new Puntaje(39,1);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(5, new Trebol()), new Carta(7, new Diamante())
        , new Carta(3, new Corazon()), new Carta(1, new Pica()), new Carta(9, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test14AlJugarUnaManoConDosCartasYUnComodinDeSumaMultiplicadorDevuelveElValorDelPuntajeDeLaManoMasEseMultiplicador() {
        // Arrange
        Mano mano = new Mano();
        Comodin comodin = new SumaMultiplicador(4, new Aleatorio(1));
        mano.agregarComodin(comodin);
        Puntaje puntajeEsperado = new Puntaje(17,4);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(5, new Trebol()), new Carta(7, new Diamante())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test15AlJugarUnaManoConTresCartasYDosComodinDeSumaMultiplicadorDevuelveElValorDelPuntajeDeLaManoMasEsosMultiplicador() {
        // Arrange
        Mano mano = new Mano();
        Comodin comodin1 = new SumaMultiplicador(4, new Aleatorio(1));
        Comodin comodin2 = new SumaMultiplicador(12, new Aleatorio(1));
        mano.agregarComodin(comodin1);
        Puntaje puntajeEsperado = new Puntaje(36,19);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(2, new Trebol()), new Carta(2, new Diamante()),
                new Carta(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        mano.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test16AlJugarUnaManoConTresCartasYUnComodinDeSumaMultiplicadorYOtroDeSumaMultiplicadorJuegoDevuelveElValorDelPuntajeDeLaManoMasEsosMultiplicador() {
        // Arrange
        Mano mano = new Mano();
        Comodin comodin1 = new SumaMultiplicador(4, new Aleatorio(1));
        Comodin comodin2 = new SumaMultiplicador(12, new Trio(), new Aleatorio(1));
        mano.agregarComodin(comodin1);
        Puntaje puntajeEsperado = new Puntaje(36,19);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(2, new Trebol()), new Carta(2, new Diamante()),
                new Carta(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        mano.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test17AlDescartaUnaManoConTresCartasYUnComodinDeSumaPuntosDescarteDevuelveElValorDelPuntajeDelMultiplicador() {
        // Arrange
        Mano mano = new Mano();
        Comodin comodin = new SumaPuntosDescarte(4, new Aleatorio(1));
        mano.agregarComodin(comodin);
        Puntaje puntajeEsperado = new Puntaje(4,1);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(2, new Trebol()), new Carta(2, new Diamante()),
                new Carta(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano(cartas, juego);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test18AlJugarUnaManoConCuatroCartasYDosComodinesDeSumaPuntosDescarteDevuelveElValorDelPuntajeDeEseMultiplicador() {
        // Arrange
        Mano mano = new Mano();
        Comodin comodin1 = new SumaPuntosDescarte(4, new Aleatorio(1));
        Comodin comodin2 = new SumaPuntosDescarte(12, new Aleatorio(1));
        mano.agregarComodin(comodin1);
        Puntaje puntajeEsperado = new Puntaje(16,1);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(2, new Trebol()), new Carta(2, new Diamante()),
                new Carta(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        mano.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano(cartas, juego);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test19AlJugarUnaManoConCuatroCartasYUnComodinDeSumaPuntosNoSumaPuntosCuandoSeDescarta() {
        // Arrange
        Mano mano = new Mano();
        Comodin comodin = new SumaPuntos(4);
        mano.agregarComodin(comodin);
        Puntaje puntajeEsperado = new Puntaje(0,1);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(2, new Trebol()), new Carta(2, new Diamante()),
                new Carta(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano(cartas, juego);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test20AlJugarUnaManoConCuatroCartasYUnComodinDeSumaPuntosDescarteNoSumaCuandoSeJuegaLaMano() {
        // Arrange
        Mano mano = new Mano();
        Comodin comodin = new SumaPuntosDescarte(100);
        mano.agregarComodin(comodin);
        Puntaje puntajeEsperado = new Puntaje(36,3);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(2, new Trebol()), new Carta(2, new Diamante()),
                new Carta(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test21AlModificarUnJuegoConTarotSumadorEsteDevulveElValorCorrepondiente() {
        // Arrange
        Mano mano = new Mano();
        Tarot tarot = new Sumador(40, 15,new Par());
        Puntaje puntajeEsperado = new Puntaje(54,17);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(2, new Trebol()), new Carta(2, new Diamante())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        mano.modificarJuego(tarot);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego);
        // Assert
        Tarot tarot1 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot1);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test21AlModificarUnJuegoConTarotSumadorYNoJugarEsteJuegoEsteNoDevulveElValorModificado() {
        // Arrange
        Mano mano = new Mano();
        Tarot tarot = new Sumador(40, 15,new Par());
        Puntaje puntajeEsperado = new Puntaje(36,3);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(2, new Trebol()), new Carta(2, new Diamante()),
                new Carta(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        mano.modificarJuego(tarot);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego);
        // Assert
        Tarot tarot1 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot1);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test22AlModificarUnJuegoConTarotSumadorDeDescarteYOtroDeNoDescarteAlJugarLaManoSoloDevuleveELPuntajeModificadoPorNoDescarte() {
        // Arrange
        Mano mano = new Mano();
        Comodin comodin1 = new SumaPuntos(40);
        Comodin comodin2 = new SumaPuntosDescarte(50);
        Puntaje puntajeEsperado = new Puntaje(76,3);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(2, new Trebol()), new Carta(2, new Diamante()),
                new Carta(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        mano.agregarComodin(comodin1);
        mano.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test23AlModificarUnJuegoConTarotSumadorDeDescarteYOtroDeNoDescarteAlDescartarLaManoSoloDevuleveELPuntajeModificadoPorDescarte() {
        // Arrange
        Mano mano = new Mano();
        Comodin comodin1 = new SumaPuntos(40);
        Comodin comodin2 = new SumaPuntosDescarte(50);
        Puntaje puntajeEsperado = new Puntaje(50,1);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new Carta(2, new Trebol()), new Carta(2, new Diamante()),
                new Carta(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        mano.agregarComodin(comodin1);
        mano.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano(cartas, juego);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

}
