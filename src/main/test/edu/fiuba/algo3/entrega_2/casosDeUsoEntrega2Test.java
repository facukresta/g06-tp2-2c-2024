package edu.fiuba.algo3.entrega_2;


import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.juego.CartaAlta;
import edu.fiuba.algo3.modelo.juego.Escalera;
import edu.fiuba.algo3.modelo.naipes.Mano;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class casosDeUsoEntrega2Test {
    // Verificar que al tener un comodín que sume 8 al multiplicador se aplique correctamente
    @Test
    public void test01AlTenerUnComodinQueSuma8AlMultiplicadorEsteSeAplicaCorrectamente() {
        //Arrange
        Comodin comodin = new SumaMultiplicador(8);
        Puntaje puntajeEsperado = new Puntaje(10,8);
        Puntaje puntajeObtenido = new Puntaje(10,1);
        //Act
        comodin.aplicarModificador(puntajeObtenido, new CartaAlta());
        //Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    // Verificar que el jugador recibe un aumento correspondiente si tiene el comodín que aumenta el multiplicador por 3 si juega una escalera
    @Test
    public void test02ElJugadorRecibeUnAumentoSiTieneElComodinQueAumentaElMultiplicadorPor3PorSiJuegoUnaEscalera() {
        //Arrange
        Puntaje puntajeEsperado = new Puntaje(54,7);
        Comodin comodin = new SumaMultiplicadorJuego(3, new Escalera());
        Mano mano = new Mano(8);
        mano.agregarCartas(new ArrayList<>(List.of(new Carta(1, new Trebol()), new Carta(2, new Trebol()),
                new Carta(3, new Pica()), new Carta(4, new Corazon()),
                new Carta(5, new Trebol()))));
        mano.elegirCarta(new Carta(1, new Trebol()));
        mano.elegirCarta(new Carta(2, new Trebol()));
        mano.elegirCarta(new Carta(3, new Pica()));
        mano.elegirCarta(new Carta(4, new Corazon()));
        mano.elegirCarta(new Carta(5, new Trebol()));
        mano.agregarComodin(comodin);
        //Act
        Puntaje puntajeObtenido = mano.jugarMano();
        //Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    // Verificar que el jugador si posee un comodin que suma 10 puntos por descarte, al descartar sume la cantidad correcta.
    @Test
    public void test03ElJugadorSiPoseeUnComodinQueSuma10PuntosPorDescarteAlDescartarSumeLaCantidadCorrecta() {
        //Arrange
        Puntaje puntajeEsperado = new Puntaje(10,1);
        Comodin comodin = new SumaPuntosDescarte(10);
        Mano mano = new Mano(8);
        mano.agregarCartas(new ArrayList<>(List.of(new Carta(1, new Trebol()), new Carta(2, new Trebol()),
                new Carta(3, new Pica()), new Carta(4, new Corazon()),
                new Carta(5, new Trebol()))));
        mano.elegirCarta(new Carta(1, new Trebol()));
        mano.elegirCarta(new Carta(2, new Trebol()));
        mano.agregarComodin(comodin);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano();
        //Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    // Verificar que si el jugador posee un comodin que tiene chance 1 sobre 1000 de romperse se rompa correctamente.
//    @Test
//    public void test04ElJugadorConUnComodinConChancesDeRomperseDe1Sobre1000ConEfectoDeSumaDeMultiplicadorSiSeRompeYaNoAplicaEseEfecto(){
//        //Arrange
//        Aleatorio aleatorioMock = mock(Aleatorio.class);
//        when(aleatorioMock.sucede()).thenReturn(false);
//        Comodin comodin = new ComodinAleatorio( new Aleatorio(1000), new SumaMultiplicador(4));
//        Mano mano = new Mano(8);
//        mano.agregarCarta(new Carta(7, new Trebol()));
//        mano.agregarCarta(new Carta(7, new Corazon()));
//        mano.elegirCarta(new Carta(7, new Trebol()));
//        mano.agregarComodin(comodin);
//        Puntaje puntajeEsperado1 = new Puntaje(12,4);
//        Puntaje puntajeEsperado2 = new Puntaje(7,1);
//        // Act
//        Puntaje puntajeObtenido1 = mano.jugarMano();
//        when(aleatorioMock.sucede()).thenReturn(true);
//        mano.elegirCarta(new Carta(7, new Corazon()));
//        Puntaje puntajeObtenido2 = mano.jugarMano();
//        // Assert
//        assertEquals(puntajeEsperado1, puntajeObtenido1);
//        assertEquals(puntajeEsperado2, puntajeObtenido2);
//
//    }
    // El jugador activa un comodín con una combinación de efectos  bonus de mano jugada + puntaje aumentado + activación aleatoria
//    @Test
//    public void test05(){
//        //Arrange
//        // Act
//
//        // Assert
//
//    }
//    // Verificar la lectura y posterior conversión a unidades del modelo de dominio del JSON
//    @Test
//    public void test06(){
//        // Arrange
//
//        // Act
//
//        // Assert
//
//    }
//    // Planteo inicial de interfaz gráfica (mockups/dibujos), pantalla donde se muestra una ronda
//    @Test
//    public void test07() {
//        // Arrange
//
//        // Act
//
//        // Assert
//
//    }
}
