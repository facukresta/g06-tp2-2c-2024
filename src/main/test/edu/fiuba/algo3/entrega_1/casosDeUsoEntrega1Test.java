package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.naipes.CartasSeleccionadas;
import edu.fiuba.algo3.modelo.naipes.Mano;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.naipes.carta.Corazon;
import edu.fiuba.algo3.modelo.naipes.carta.Diamante;
import edu.fiuba.algo3.modelo.naipes.carta.Trebol;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.CambiadorDeMultiplicador;
import edu.fiuba.algo3.modelo.tarot.CambiadorDePuntos;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class casosDeUsoEntrega1Test {
    //Verificar que un jugador posea cartas suficientes para empezar el juego en su mazo.
    @Test
    public void test01UnaManoTieneQueTenerCartasSuficientesParaArrancarElJuego() {
        //Arrange
        Mazo mazo = new Mazo();
        Mano mano = new Mano(8);
        mazo.repartirCartas(8, mano);

        //Act
        boolean cantidaddeCartas = (mano.obtenerCantidadDeCartas() > 0);

        //Assert
        assertTrue(cantidaddeCartas);
    }
    //Verificar que a un jugador se le reparten 8 cartas de su mazo.
    @Test
    public void test02UnaManoSeLeRepartenOchoCartasDeSuMazo() {
        //Arrange
        Mazo mazo = new Mazo();
        Mano mano = new Mano(8);
        mazo.repartirCartas(8, mano);
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
        CartasSeleccionadas mano = new CartasSeleccionadas();
        for (int i = 1; i <= 5; i++) {
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
        CartasSeleccionadas mano = new CartasSeleccionadas();
        for (int i = 1; i <= 5; i++) {
            mano.agregarCarta(new Carta(i, new Trebol()));
        }
        int puntajeEsperado = 920;
        // Act
        int puntajeObtenido = mano.jugarMano();
        // Assert
        assertEquals(puntajeEsperado, puntajeObtenido);
    }
    //Verificar que importe el orden en la puntuación de las cartas.
    @Test
    public void test05AlJugarUnaManoTrioElValorDebeSerDistintoAPrimeroCalcularElValorDelTrioYLaSumaDeLasCartasYLuegoSumarAmbosValores(){
        //Arrange
        CartasSeleccionadas mano = new CartasSeleccionadas();
        mano.agregarCarta(new Carta(2, new Trebol()));
        mano.agregarCarta(new Carta(2, new Corazon()));
        mano.agregarCarta(new Carta(2, new Diamante()));
        int puntajeEsperado = 108;
        // Act
        Puntaje puntajeJuegoTrio = new Puntaje(30, 3);
        Puntaje puntajeCartasSuma = new Puntaje(6,1);
        int puntajeObtenido = puntajeJuegoTrio.calcularValor() + puntajeCartasSuma.calcularValor();
        // Assert
        assertNotEquals(puntajeObtenido, puntajeEsperado);
    }
    //Verificar que al modificar una carta al utilizar un tarot que cambia sus puntos por 10, se aplique el puntaje correcto en el mazo.
    @Test
    public void test06UnaCartaModificadaPorUnaCartaDeTarotDePuntosDeMas10ModificaComoPuntuaLaCarta(){
        // Arrange
        int valorEsperado = 132;
        CartasSeleccionadas mano = new CartasSeleccionadas();
        mano.agregarCarta(new Carta(2, new Diamante()));
        mano.agregarCarta(new Carta(2, new Corazon()));
        Carta cartaModificada = new Carta(2, new Trebol());
        Tarot tarot = new CambiadorDePuntos(10);
        cartaModificada.aplicarModificador(tarot);
        mano.agregarCarta(cartaModificada);
        // Act
        int valorObtenido = mano.jugarMano();
        // Assert
        assertEquals(valorEsperado, valorObtenido);
    }
    //Verificar que al modificar una carta utilizando un tarot que cambia su multiplicador a un x6 se aplique el valor correspondiente.
    @Test
    public void test07UnaCartaModificadaPorUnaCartaDeTarotDeMultiplicadorDePor6ModificaComoPunutuaLaCarta() {
        // Arrange
        int valorEsperado = 324;
        CartasSeleccionadas mano = new CartasSeleccionadas();
        mano.agregarCarta(new Carta(2, new Diamante()));
        mano.agregarCarta(new Carta(2, new Corazon()));
        Carta cartaModificada = new Carta(2, new Trebol());
        Tarot tarot = new CambiadorDeMultiplicador(6);
        cartaModificada.aplicarModificador(tarot);
        mano.agregarCarta(cartaModificada);
        // Act
        int valorObtenido = mano.jugarMano();
        // Assert
        assertEquals(valorEsperado, valorObtenido);
    }
}
