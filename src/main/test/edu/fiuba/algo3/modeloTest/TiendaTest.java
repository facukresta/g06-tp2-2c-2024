package edu.fiuba.algo3.modeloTest;

import edu.fiuba.algo3.modelo.comodin.Modificador;
import edu.fiuba.algo3.modelo.comodin.SumaPuntos;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.tienda.ProductoNoEnTiendaException;
import edu.fiuba.algo3.modelo.tienda.Tienda;
import edu.fiuba.algo3.modelo.tienda.TiendaBalatro;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class TiendaTest {
    @Test
    public void test01UnaTiendaSinNingunObjetoNoSeLePuedeComprarNada() {
        // Arrange
        Tienda tienda = new TiendaBalatro(new ArrayList<>());
        // Act / Assert
        assertThrows(ProductoNoEnTiendaException.class, () -> {
            tienda.comprar(mock(Carta.class));
        });
    }
    @Test
    public void test02UnaTiendaConUnComodinAgregadoPuedeComprarseEseComodin(){
        // Arrange
        Modificador modificadorMock = mock(Modificador.class);
        Tienda tienda = new TiendaBalatro(new ArrayList<>(List.of(modificadorMock)));
        // Act / Assert
        assertDoesNotThrow(() -> { tienda.comprar(modificadorMock);});
    }
    @Test
    public void test03UnaTiendaConUnTarotAgregadoPuedeComprarseEseTarot(){
        // Arrange
        Tarot tarotMock = mock(Tarot.class);
        Tienda tienda = new TiendaBalatro(new ArrayList<>(List.of(tarotMock)));
        // Act / Assert
        assertDoesNotThrow(() -> { tienda.comprar(tarotMock);});
    }
    @Test
    public void test04UnaTiendaConUnCartaAgregadoPuedeComprarseEseCarta(){
        // Arrange
        Carta cartaMock = mock(Carta.class);
        Tienda tienda = new TiendaBalatro(new ArrayList<>(List.of(cartaMock)));
        // Act / Assert
        assertDoesNotThrow(() -> { tienda.comprar(cartaMock);});
    }
    @Test
    public void test05UnaTiendaConDosComodinesAgregadoPuedeComprarseUnComodinSolamente(){
        // Arrange
        Modificador modificadorMock1 = mock(Modificador.class);
        Modificador modificadorMock2 = mock(Modificador.class);
        Tienda tienda = new TiendaBalatro(new ArrayList<>(List.of(modificadorMock1, modificadorMock2)));
        // Act / Assert
        assertDoesNotThrow(() -> { tienda.comprar(modificadorMock1);});
        assertThrows(ProductoNoEnTiendaException.class, () -> {
            tienda.comprar(modificadorMock2);
        });
    }
    @Test
    public void test06UnaTiendaConDosCartasAgregadoPuedeComprarseUnaCartaSolamente(){
        // Arrange
        Carta cartaMock1 = mock(Carta.class);
        Carta cartaMock2 = mock(Carta.class);
        Tienda tienda = new TiendaBalatro(new ArrayList<>(List.of(cartaMock1, cartaMock2)));
        // Act / Assert
        assertDoesNotThrow(() -> { tienda.comprar(cartaMock2);});
        assertThrows(ProductoNoEnTiendaException.class, () -> {
            tienda.comprar(cartaMock1);
        });
    }
    @Test
    public void test07UnaTiendaConDosTarotsAgregadoPuedeComprarseUnTarotSolamente(){
        // Arrange
        Tarot tarotMock1 = mock(Tarot.class);
        Tarot tarotMock2 = mock(Tarot.class);
        Tienda tienda = new TiendaBalatro(new ArrayList<>(List.of(tarotMock2, tarotMock1)));
        // Act / Assert
        assertDoesNotThrow(() -> { tienda.comprar(tarotMock1);});
        assertThrows(ProductoNoEnTiendaException.class, () -> {
            tienda.comprar(tarotMock2);
        });
    }
    @Test
    public void test08UnaTiendaUnaCartaUnaComodinYUnTarotPuedeComprarUnSoloTarot(){
        // Arrange
        Tarot tarotMock = mock(Tarot.class);
        Modificador modificadorMock = mock(Modificador.class);
        Carta cartaMock = mock(Carta.class);
        Tienda tienda = new TiendaBalatro(new ArrayList<>(List.of(cartaMock, modificadorMock, tarotMock)));
        // Act / Assert
        assertDoesNotThrow(() -> { tienda.comprar(tarotMock);});
        assertThrows(ProductoNoEnTiendaException.class, () -> {
            tienda.comprar(cartaMock);
        });
        assertThrows(ProductoNoEnTiendaException.class, () -> {
            tienda.comprar(modificadorMock);
        });
    }
    @Test
    public void test08UnaTiendaUnaCartaUnaComodinYUnTarotPuedeComprarUnoSoloModificador(){
        // Arrange
        Tarot tarotMock = mock(Tarot.class);
        Modificador modificadorMock = mock(Modificador.class);
        Carta cartaMock = mock(Carta.class);
        Tienda tienda = new TiendaBalatro(new ArrayList<>(List.of(cartaMock, modificadorMock, tarotMock)));
        // Act / Assert
        assertDoesNotThrow(() -> { tienda.comprar(modificadorMock);});
        assertThrows(ProductoNoEnTiendaException.class, () -> {
            tienda.comprar(cartaMock);
        });
        assertThrows(ProductoNoEnTiendaException.class, () -> {
            tienda.comprar(tarotMock);
        });
    }
    @Test
    public void test08UnaTiendaUnaCartaUnaComodinYUnTarotPuedeComprarUnaSolaCarta(){
        // Arrange
        Tarot tarotMock = mock(Tarot.class);
        Modificador modificadorMock = mock(Modificador.class);
        Carta cartaMock = mock(Carta.class);
        Tienda tienda = new TiendaBalatro(new ArrayList<>(List.of(cartaMock, modificadorMock, tarotMock)));
        // Act / Assert
        assertDoesNotThrow(() -> { tienda.comprar(cartaMock);});
        assertThrows(ProductoNoEnTiendaException.class, () -> {
            tienda.comprar(tarotMock);
        });
        assertThrows(ProductoNoEnTiendaException.class, () -> {
            tienda.comprar(modificadorMock);
        });
    }

}
