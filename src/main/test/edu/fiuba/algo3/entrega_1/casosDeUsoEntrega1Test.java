package edu.fiuba.algo3.entrega_1;

//import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.naipes.Mano;
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
        Mano mano = new Mano(8);
        mano.agregarCarta(new Carta(2, new Corazon()));
        //Act
        boolean tieneCartas = (mano.obtenerCantidadDeCartas() > 0);
        //Assert
        assertTrue(tieneCartas);
    }
    //Verificar que a un mano se le reparten 8 cartas de su mazo.
    @Test
    public void test02AUnJugadorSeLeRepartenOchoCartasDeSuMazo() {
        //Arrange
        Mano mano = new Mano(8);
        mano.agregarCartas(new ArrayList<Carta>(List.of(new Carta(2, new Corazon()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()),  new Carta(5, new Corazon()),  new Carta(6, new Corazon()),
                new Carta(7, new Corazon()),  new Carta(8, new Corazon()),  new Carta(9, new Corazon()))));
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
        Mano mano = new Mano(8);
        for (int i = 1; i <= 8; i++) {
            mano.agregarCarta(new Carta(i, new Trebol()));
        }
        mano.elegirCarta(new Carta(5, new Trebol()));

        //Assert y Act
        assertDoesNotThrow(() -> mano.jugarMano());
    }
    //Verificar que al jugar una mano, se aplique el valor correspondiente.
    @Test
    public void test04AlJugarUnaManoSeLeAplicaSuValorCorrespondiente(){
        //Arrange
        Mano mano = new Mano(8);
        for (int i = 1; i <= 8; i++) {
            mano.agregarCarta(new Carta(i, new Trebol()));
        }
        Puntaje puntajeEsperado = new Puntaje(124, 8);
        for (int i = 1; i <= 5; i++) {
            mano.elegirCarta(new Carta(i, new Trebol()));
        }
        // Act
        Puntaje puntajeObtenido = mano.jugarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    //Verificar que importe el orden en la puntuación de las cartas.
    @Test
    public void test05AlJugarUnaManoTrioElValorDebeSerDistintoAPrimeroCalcularElValorDelTrioYLaSumaDeLasCartasYLuegoSumarAmbosValores(){
        //Arrange
        Mano mano = new Mano(8);
        mano.agregarCartas(new ArrayList<>(List.of(new Carta(2, new Trebol()), new Carta(2, new Corazon()), new Carta(2, new Diamante()))));
        mano.elegirCarta(new Carta(2, new Trebol()));
        mano.elegirCarta(new Carta(2, new Corazon()));
        mano.elegirCarta(new Carta(2, new Diamante()));
        Puntaje puntajeMaximo = mano.jugarMano();
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
        Mano mano = new Mano(8);
        mano.agregarCartas(new ArrayList<>(List.of(new Carta(2, new Corazon()), new Carta(2, new Diamante()),  new Carta(2, new Trebol()))));
        Tarot tarot = new CambiadorDePuntos(10);
        mano.modificarCarta(new Carta(2, new Trebol()), tarot);
        mano.elegirCarta(new Carta(2, new Trebol()));
        mano.elegirCarta(new Carta(2, new Corazon()));
        mano.elegirCarta(new Carta(2, new Diamante()));
        // Act
        Puntaje puntajeObtenido = mano.jugarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    //Verificar que al modificar una carta utilizando un tarot que cambia su multiplicador a un x6 se aplique el valor correspondiente.
    @Test
    public void test07UnaCartaModificadaPorUnaCartaDeTarotDeMultiplicadorDePor6ModificaComoPunutuaLaCarta() {
        // Arrange
        Puntaje puntajeEsperado = new Puntaje(36, 9);
        Mano mano = new Mano(8);
        mano.agregarCartas(new ArrayList<>(List.of(new Carta(2, new Corazon()), new Carta(2, new Diamante()), new Carta(2, new Trebol()))));
        Tarot tarot = new CambiadorDeMultiplicador(6);
        mano.modificarCarta(new Carta(2, new Trebol()), tarot);
        mano.elegirCarta(new Carta(2, new Trebol()));
        mano.elegirCarta(new Carta(2, new Corazon()));
        mano.elegirCarta(new Carta(2, new Diamante()));
        // Act
        Puntaje puntajeObtenido = mano.jugarMano();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
