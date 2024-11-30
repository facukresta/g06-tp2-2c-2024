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
    public void test10AlElegirSeisCartasLaManoDeberiaLanzarError(){
        // Arrange
        Mano mano = new ManoDe8();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(2,new Corazon()), new CartaInglesa(3,new Corazon()),
                new CartaInglesa(4,new Corazon()), new CartaInglesa(6,new Corazon()), new CartaInglesa(8,new Corazon()),
                new CartaInglesa(11,new Corazon())));
        mano.agregarCartas(cartas);
        Juego juego = Juego.chequearJuego(cartas);
        // Act / Assert
        assertThrows(MaximoCartasSeleccionadasException.class, () -> {
            mano.jugarMano(cartas, juego, new Comodinera());
        });
    }

    @Test
    public void test11LanzaErrorSiSeJugarUnaCartaQueNoEstaEnLaMano(){
        // Arrange
        Mano mano = new ManoDe8();
        Carta cartaEnLaMano = mock(Carta.class);
        ArrayList<Carta> cartaNoEstaEnLaManoDe5 = new ArrayList<>(List.of(new CartaInglesa(7, new Trebol())));
        mano.agregarCarta(cartaEnLaMano);
        Juego juego = Juego.chequearJuego(cartaNoEstaEnLaManoDe5);
        // Act / Assert
        assertThrows(CartaNoEnManoException.class, () -> {
            mano.jugarMano(cartaNoEstaEnLaManoDe5, juego, new Comodinera());
        });
    }

    @Test
    public void test12AlJugarUnaManoConUnaCartaDevuelveElValorDelPuntajeDeLaCarta() {
        // Arrange
        Mano mano = new ManoDe8();
        Puntaje puntajeEsperado = new Puntaje(10,1);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(5, new Trebol())));
        mano.agregarCartas(cartas);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego, new Comodinera());
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test13AlJugarUnaManoConCincoCartasDevuelveElValorDelPuntajeDeLaMano() {
        // Arrange
        Mano mano = new ManoDe8();
        Puntaje puntajeEsperado = new Puntaje(39,1);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(5, new Trebol()), new CartaInglesa(7, new Diamante())
        , new CartaInglesa(3, new Corazon()), new CartaInglesa(1, new Pica()), new CartaInglesa(9, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego, new Comodinera());
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(5, new Trebol()), new CartaInglesa(7, new Diamante())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego, comodinera);
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(2, new Trebol()), new CartaInglesa(2, new Diamante()),
                new CartaInglesa(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        comodinera.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego, comodinera);
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(2, new Trebol()), new CartaInglesa(2, new Diamante()),
                new CartaInglesa(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        comodinera.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego,comodinera);
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(2, new Trebol()), new CartaInglesa(2, new Diamante()),
                new CartaInglesa(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano(cartas, juego, comodinera);
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(2, new Trebol()), new CartaInglesa(2, new Diamante()),
                new CartaInglesa(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        comodinera.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano(cartas, juego, comodinera);
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(2, new Trebol()), new CartaInglesa(2, new Diamante()),
                new CartaInglesa(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano(cartas, juego, comodinera);
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(2, new Trebol()), new CartaInglesa(2, new Diamante()),
                new CartaInglesa(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego, comodinera);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test21AlModificarUnJuegoConTarotSumadorEsteDevulveElValorCorrepondiente() {
        // Arrange
        Mano mano = new ManoDe8();
        Tarot tarot = new Sumador(40, 15,new Par(), "");
        Puntaje puntajeEsperado = new Puntaje(54,17);
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(2, new Trebol()), new CartaInglesa(2, new Diamante())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        mano.modificarJuego(tarot);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego, new Comodinera());
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(2, new Trebol()), new CartaInglesa(2, new Diamante()),
                new CartaInglesa(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        mano.modificarJuego(tarot);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego, new Comodinera());
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(2, new Trebol()), new CartaInglesa(2, new Diamante()),
                new CartaInglesa(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(comodin1);
        comodinera.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego, comodinera);
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
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(2, new Trebol()), new CartaInglesa(2, new Diamante()),
                new CartaInglesa(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        mano.agregarCartas(cartas);
        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(comodin1);
        comodinera.agregarComodin(comodin2);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano(cartas, juego, comodinera);
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

}
