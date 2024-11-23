package edu.fiuba.algo3.modeloTest;

import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.comodin.Modificador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.naipes.carta.CartaInglesa;
import edu.fiuba.algo3.modelo.naipes.carta.Corazon;
import edu.fiuba.algo3.modelo.naipes.carta.Trebol;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.ronda.*;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.tienda.Tienda;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RondaTest {
    @Test
    public void test01UnaRondaNoSePuedeInstanciarConManosMenoresACero() {
        // Arrange / Act /  Assert
        assertThrows(CantidadDeManosInvalidaException.class, () -> {
            new Ronda(new Puntaje(1000, 1), -1, 3, new Mazo(), mock(Tienda.class));;
        });
    }
    @Test
    public void test02UnaRondaNoSePuedeInstanciarConManosIgualesACero() {
        // Arrange / Act /  Assert
        assertThrows(CantidadDeManosInvalidaException.class, () -> {
            new Ronda(new Puntaje(1000, 1), 0, 3, new Mazo(), mock(Tienda.class));;
        });
    }
    @Test
    public void test03UnaRondaNoSePuedeInstanciarConDescartesMenoresACero() {
        // Arrange / Act /  Assert
        assertThrows(CantidadDeDescartesInvalidaException.class, () -> {
            new Ronda(new Puntaje(1000, 1), 3, -1, new Mazo(), mock(Tienda.class));;
        });
    }
    @Test
    public void test04UnaRondaNoSePuedeInstanciarConDescartesIgualesACero() {
        // Arrange / Act /  Assert
        assertThrows(CantidadDeDescartesInvalidaException.class, () -> {
            new Ronda(new Puntaje(1000, 1), 3, 0, new Mazo(), mock(Tienda.class));;
        });
    }
    @Test
    public void test05UnaRondaRecienInstanciadaNoSePasa() {
        // Arrange
        Mazo mazo = new Mazo();
        mazo.agregarCartas(new ArrayList<Carta>(List.of(mock(Carta.class), mock(Carta.class), mock(Carta.class),
                mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class))));
        // Act /  Assert
        assertDoesNotThrow(() -> {
            new Ronda(new Puntaje(1000, 1), 3, 3, mazo, mock(Tienda.class));;
        });
    }
    @Test
    public void test07UnaRondaRecienInstanciadaNoSePierde() {
        // Arrange
        Mazo mazo = new Mazo();
        mazo.agregarCartas(new ArrayList<Carta>(List.of(mock(Carta.class), mock(Carta.class), mock(Carta.class),
                mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class))));
        // Act /  Assert
        assertDoesNotThrow(() -> {
            new Ronda(new Puntaje(1000, 1), 3, 3, mazo, mock(Tienda.class));;
        });
    }
    @Test
    public void test08SiUnaRondaSeCreaConUnPuntajeBaseDe1AlJugarUnaManoDeCualquierCartaDebePasarLaRonda() {
        // Arrange
        Mazo mazo = new Mazo();
        ArrayList<Carta> cartasSeleccionadas = new ArrayList<>(List.of(new CartaInglesa(2, new Corazon()),
                new CartaInglesa(3, new Corazon()), new CartaInglesa(4, new Corazon()),
                new CartaInglesa(5, new Corazon()), new CartaInglesa(6, new Corazon())));
        Juego juego = Juego.chequearJuego(cartasSeleccionadas);
        Comodinera comodinera = new Comodinera();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Trebol()),
                new CartaInglesa(3, new Trebol()), new CartaInglesa(5, new Trebol())));
        cartas.addAll(cartasSeleccionadas);
        mazo.agregarCartas(cartas);
        Ronda ronda = new Ronda(new Puntaje(1, 1), 3, 3, mazo, mock(Tienda.class));
        // Act /  Assert
        assertThrows(PasoLaRondaException.class, () -> {
            ronda.jugarMano(cartasSeleccionadas, juego, comodinera);
        });
    }
    @Test
    public void test09SiUnaRondaSeQuedaSinManosYNoPasoElPuntajeDebePerderLaRonda() {
        // Arrange
        Mazo mazo = new Mazo();
        ArrayList<Carta> cartasSeleccionadas = new ArrayList<>(List.of(new CartaInglesa(2, new Corazon()),
                new CartaInglesa(3, new Corazon()), new CartaInglesa(4, new Corazon()),
                new CartaInglesa(5, new Corazon()), new CartaInglesa(6, new Corazon())));
        Juego juego = Juego.chequearJuego(cartasSeleccionadas);
        Comodinera comodinera = new Comodinera();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Trebol()),
                new CartaInglesa(3, new Trebol()), new CartaInglesa(5, new Trebol())));
        cartas.addAll(cartasSeleccionadas);
        mazo.agregarCartas(cartas);
        Ronda ronda = new Ronda(new Puntaje(1000, 1), 1, 3, mazo, mock(Tienda.class));
        // Act /  Assert
        assertThrows(PerdioLaRondaException.class, () -> {
            ronda.jugarMano(cartasSeleccionadas, juego, comodinera);
        });
    }
    @Test
    public void test10SiUnaRondaSeQuedaSinManoPeroPasoElPuntajeDebePasarLaRonda() {
        // Arrange
        Mazo mazo = new Mazo();
        ArrayList<Carta> cartasSeleccionadas = new ArrayList<>(List.of(new CartaInglesa(2, new Corazon()),
                new CartaInglesa(3, new Corazon()), new CartaInglesa(4, new Corazon()),
                new CartaInglesa(5, new Corazon()), new CartaInglesa(6, new Corazon())));
        Juego juego = Juego.chequearJuego(cartasSeleccionadas);
        Comodinera comodinera = new Comodinera();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Trebol()),
                new CartaInglesa(3, new Trebol()), new CartaInglesa(5, new Trebol())));
        cartas.addAll(cartasSeleccionadas);
        mazo.agregarCartas(cartas);
        Ronda ronda = new Ronda(new Puntaje(1, 1), 1, 3, mazo, mock(Tienda.class));
        // Act /  Assert
        assertThrows(PasoLaRondaException.class, () -> {
            ronda.jugarMano(cartasSeleccionadas, juego, comodinera);
        });
    }
    @Test
    public void test11SiUnaRondaSeQuedaSinDescartesNoPuedeDescartarMasPorLoPeroNoPierdeLaRonda() {
        // Arrange
        Mazo mazo = new Mazo();
        ArrayList<Carta> cartasSeleccionadas = new ArrayList<>(List.of(new CartaInglesa(2, new Corazon()),
                new CartaInglesa(3, new Corazon()), new CartaInglesa(4, new Corazon()),
                new CartaInglesa(5, new Corazon()), new CartaInglesa(6, new Corazon())));
        Juego juego = Juego.chequearJuego(cartasSeleccionadas);
        Comodinera comodinera = new Comodinera();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Trebol()),
                new CartaInglesa(3, new Trebol()), new CartaInglesa(5, new Trebol())));
        mazo.agregarCartas(cartas);
        mazo.agregarCartas(cartasSeleccionadas);
        Ronda ronda = new Ronda(new Puntaje(1, 1), 3, 1, mazo, mock(Tienda.class));
        // Act
        mazo.agregarCartas(new ArrayList<>(List.of(mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class))));
        ronda.descartarMano(cartasSeleccionadas, juego, comodinera);
        // Assert
        juego = Juego.chequearJuego(cartas);
        Juego finalJuego = juego;
        assertDoesNotThrow(() -> {
            ronda.descartarMano(cartas, finalJuego, comodinera);
        });
    }
    @Test
    public void test12UnaRondaPuedeComprarUnaCartaDeSuTienda() {
        // Arrange
        Mazo mazo = new Mazo();
        mazo.agregarCartas(new ArrayList<>(List.of(mock(Carta.class), mock(Carta.class), mock(Carta.class),
                mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class))));
        Tienda tiendaMock = mock(Tienda.class);
        doNothing().when(tiendaMock).comprar(any());
        // Act
        Ronda ronda = new Ronda(new Puntaje(1, 1), 3, 1, mazo, tiendaMock);
        // Act / Assert
        assertDoesNotThrow(() -> {
            ronda.comprar((Carta) any());
        });
    }
    @Test
    public void test13UnaRondaPuedeComprarUnComodinDeSuTienda() {
        // Arrange
        Mazo mazo = new Mazo();
        mazo.agregarCartas(new ArrayList<>(List.of(mock(Carta.class), mock(Carta.class), mock(Carta.class),
                mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class))));
        Tienda tiendaMock = mock(Tienda.class);
        doNothing().when(tiendaMock).comprar(any());
        // Act
        Ronda ronda = new Ronda(new Puntaje(1, 1), 3, 1, mazo, tiendaMock);
        // Act / Assert
        assertDoesNotThrow(() -> {
            ronda.comprar((Modificador) any());
        });
    }
    @Test
    public void test14UnaRondaPuedeComprarUnTarotDeSuTienda() {
        // Arrange
        Mazo mazo = new Mazo();
        mazo.agregarCartas(new ArrayList<>(List.of(mock(Carta.class), mock(Carta.class), mock(Carta.class),
                mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class))));
        Tienda tiendaMock = mock(Tienda.class);
        doNothing().when(tiendaMock).comprar(any());
        // Act
        Ronda ronda = new Ronda(new Puntaje(1, 1), 3, 1, mazo, tiendaMock);
        // Act / Assert
        assertDoesNotThrow(() -> {
            ronda.comprar((Tarot) any());
        });
    }
}
