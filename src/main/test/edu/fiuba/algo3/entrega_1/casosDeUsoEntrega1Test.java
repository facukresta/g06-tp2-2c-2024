package edu.fiuba.algo3.entrega_1;

//import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.naipes.Mano;
import edu.fiuba.algo3.modelo.naipes.ManoDe8;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class casosDeUsoEntrega1Test {
    //Verificar que un mano posea cartas suficientes para empezar el juego en su mazo.
    @Test
    public void test01UnJugadorTieneQueTenerCartasSuficientesParaArrancarElJuego() {
        //Arrange
        Mano mano = new ManoDe8();
        mano.agregarCarta(new CartaInglesa(2, new Corazon()));
        //Act
        boolean tieneCartas = (mano.obtenerCantidadDeCartas() > 0);
        //Assert
        assertTrue(tieneCartas);
    }
    //Verificar que a un mano se le reparten 8 cartas de su mazo.
    @Test
    public void test02AUnJugadorSeLeRepartenOchoCartasDeSuMazo() {
        //Arrange
        Mano mano = new ManoDe8();
        mano.agregarCartas(new ArrayList<Carta>(List.of(new CartaInglesa(2, new Corazon()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Corazon()),  new CartaInglesa(5, new Corazon()),  new CartaInglesa(6, new Corazon()),
                new CartaInglesa(7, new Corazon()),  new CartaInglesa(8, new Corazon()),  new CartaInglesa(9, new Corazon()))));
        int cantidadDeCartasEsperadas = 8;
        //Act
        int cantidadDeCartasObtenidas = mano.obtenerCantidadDeCartas();
        //Assert
        assertEquals(cantidadDeCartasEsperadas, cantidadDeCartasObtenidas);
    }
    //Verificar que se puede jugar una mano de un mazo.
    @Test
    public void test03SePuedeJugarUnaManoDeUnMazo(){
        //Arrange
        Mano mano = new ManoDe8();
        Carta carta1 = new CartaInglesa(1, new Pica());
        Carta carta2 = new CartaInglesa(2, new Pica());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2));
        mano.agregarCartas(cartas);
        Juego juego = Juego.chequearJuego(cartas);
        //Assert y Act
        assertDoesNotThrow(() -> mano.jugarMano(cartas, juego, new Comodinera()));
    }
    //Verificar que al jugar una mano, se aplique el valor correspondiente.
    @Test
    public void test04AlJugarUnaManoSeLeAplicaSuValorCorrespondiente(){
        //Arrange
        Mano mano = new ManoDe8();
        Carta carta1 = new CartaInglesa(1, new Pica());
        Carta carta2 = new CartaInglesa(2, new Pica());
        Carta carta3 = new CartaInglesa(3, new Pica());
        Carta carta4 = new CartaInglesa(4, new Pica());
        Carta carta5 = new CartaInglesa(5, new Pica());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5));
        mano.agregarCartas(cartas);
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje puntajeEsperado = new Puntaje(124,8);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego, new Comodinera());
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    //Verificar que importe el orden en la puntuación de las cartas.
    @Test
    public void test05AlJugarUnaManoTrioElValorDebeSerDistintoAPrimeroCalcularElValorDelTrioYLaSumaDeLasCartasYLuegoSumarAmbosValores(){
        //Arrange
        Mano mano = new ManoDe8();
        Carta carta1 = new CartaInglesa(2, new Trebol());
        Carta carta2 = new CartaInglesa(2, new Corazon());
        Carta carta3 = new CartaInglesa(2, new Diamante());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3));
        mano.agregarCartas(cartas);
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje puntajeMaximo = mano.jugarMano(cartas, juego, new Comodinera());
        // Act
        ArrayList<Puntaje> puntajesSeparados = new ArrayList<>(List.of(new Puntaje(30, 3), new Puntaje(6,2)));
        // Assert
        assertFalse(puntajeMaximo.esMenorAPuntajes(puntajesSeparados));
    }
    //Verificar que al modificar una carta al utilizar un tarot que cambia sus puntos por 10, se aplique el puntaje correcto en el mazo.
    @Test
    public void test06UnaCartaModificadaPorUnaCartaDeTarotDePuntosDeMas10ModificaComoPuntuaLaCarta(){
        // Arrange
        Puntaje puntajeEsperado = new Puntaje(44, 3);
        Mano mano = new ManoDe8();
        Carta carta1 = new CartaInglesa(2, new Trebol());
        Carta carta2 = new CartaInglesa(2, new Corazon());
        Carta carta3 = new CartaInglesa(2, new Diamante());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3));
        mano.agregarCartas(cartas);
        Juego juego = Juego.chequearJuego(cartas);
        Tarot tarot = new CambiadorDePuntos(10, "");
        mano.modificarCarta(carta1, tarot);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego, new Comodinera());
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    //Verificar que al modificar una carta utilizando un tarot que cambia su multiplicador a un x6 se aplique el valor correspondiente.
    @Test
    public void test07UnaCartaModificadaPorUnaCartaDeTarotDeMultiplicadorDePor6ModificaComoPunutuaLaCarta() {
        // Arrange
        Puntaje puntajeEsperado = new Puntaje(36, 9);
        Mano mano = new ManoDe8();
        Carta carta1 = new CartaInglesa(2, new Corazon());
        Carta carta2 = new CartaInglesa(2, new Diamante());
        Carta carta3 = new CartaInglesa(2, new Trebol());
        ArrayList<Carta> cartas = new ArrayList<>(List.of(carta1, carta2, carta3));
        mano.agregarCartas(cartas);
        Juego juego = Juego.chequearJuego(cartas);
        Tarot tarot = new CambiadorDeMultiplicador(6, "");
        mano.modificarCarta(carta3, tarot);
        // Act
        Puntaje puntajeObtenido = mano.jugarMano(cartas, juego, new Comodinera());
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
