package edu.fiuba.algo3.modeloTest.cartaTest;

import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.CambiadorDePuntos;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

public class CartaTest {
    @Test
    public void test01UnaCartaCreadaConNumero10YPaloCorazonTienePuntaje10(){
        // Arrange
        Carta carta = new Carta(10, new Corazon());
        Puntaje puntajeEsperado = new Puntaje(10,1);
        // Act
        Puntaje puntajeObtenido = carta.obtenerPuntaje();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test02UnaCartaCreadaConNumero10YPaloBastoSabeReconocerOtraCartaDeSuMismoPalo(){
        // Arrange
        Palo paloMock = mock(Palo.class);
        when(paloMock.esDeEstePalo(any())).thenReturn(true);
        Carta cartaBase = new Carta(2, paloMock);
        Carta cartaAComparar = new Carta(12, new Corazon());
        // Act
        boolean resultadoComparacion = cartaBase.esDelMismoPalo(cartaAComparar);
        // Assert
        assertTrue(resultadoComparacion);
    }
    @Test
    public void test03UnaCartaNoPuedeInstanciadaConNumeroNegativo(){
        // Arrange / Act / Assert
        assertThrows(NumeroInvalidoException.class, () -> {
            new Carta(-1, new Trebol());
        });
    }
    @Test
    public void test04UnaCartaNoPuedeInstanciadaConNumeroIgualA0(){
        // Arrange / Act / Assert
        assertThrows(NumeroInvalidoException.class, () -> {
            new Carta(0, new Trebol());
        });
    }
    @Test
    public void test05UnaCartaNoPuedeSerInstanciadaConNumeroMayorA13(){
        // Arrange / Act / Assert
        assertThrows(NumeroInvalidoException.class, () -> {
            new Carta(14, new Trebol());
        });
    }
    @Test
    public void test06UnaInstaciaDeCartaDebePoderReconocerSiEsLaMismaCartaQueOtraCartaSiEsLaMismaInstancia(){
        // Arrange
        Palo paloMock = mock(Palo.class);
        when(paloMock.esDeEstePalo(any())).thenReturn(true);
        Carta carta = new Carta(10, paloMock);
        // Act
        boolean resultadoComparacion = carta.sos(carta);
        // Assert
        assertTrue(resultadoComparacion);
    }
    @Test
    public void test07UnaInstaciaDeCartaDebePoderReconocerSiNoEsLaMismaCartaSiNoTieneElMismoNumero(){
        // Arrange
        Palo paloMock = mock(Palo.class);
        when(paloMock.esDeEstePalo(any())).thenReturn(true);
        Carta carta = new Carta(10, paloMock);
        // Act
        boolean resultadoComparacion = carta.sos(new Carta(9, new Corazon()));
        // Assert
        assertFalse(resultadoComparacion);
    }
    @Test
    public void test08UnaInstaciaDeCartaDebePoderReconocerSiNoEsLaMismaCartaSiNoTieneElMismoPalo(){
        // Arrange
        Palo paloMock = mock(Palo.class);
        when(paloMock.esDeEstePalo(any())).thenReturn(false);
        Carta carta = new Carta(10, paloMock);
        // Act
        boolean resultadoComparacion = carta.sos(new Carta(10, new Trebol()));
        // Assert
        assertFalse(resultadoComparacion);
    }
    @Test
    public void test09UnaInstanciaDeCartaAlCrearseTieneNoTieneModificacionDeTarot(){
        // Arrange
        Carta carta = new Carta(10, new Corazon());
        Puntaje puntajeEsperado = new Puntaje(10,1);
        // Act
        Puntaje puntajeObtenido = carta.obtenerPuntaje();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test10UnaInstanciaDeCartaPuedeSerModificadaConUnTarot(){
        // Arrange
        Carta carta = new Carta(3, new Corazon());
        Puntaje puntajeEsperado = new Puntaje(7,1);
        Tarot tarot = new CambiadorDePuntos(7);
        // Act
        carta.aplicarModificador(tarot);
        Puntaje puntajeObtenido = carta.obtenerPuntaje();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
