package edu.fiuba.algo3.modeloTest.rondaTest;

import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.comodin.Modificador;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.MazoBalatro;
import edu.fiuba.algo3.modelo.naipes.Seleccionadas;
import edu.fiuba.algo3.modelo.naipes.SeleccionadasBalatro;
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
            new Ronda(new Puntaje(1000, 1), -1, 3, mock(Mazo.class), mock(Tienda.class), 1);;
        });
    }
    @Test
    public void test02UnaRondaNoSePuedeInstanciarConManosIgualesACero() {
        // Arrange / Act /  Assert
        assertThrows(CantidadDeManosInvalidaException.class, () -> {
            new Ronda(new Puntaje(1000, 1), 0, 3, mock(Mazo.class), mock(Tienda.class), 1);;
        });
    }
    @Test
    public void test03UnaRondaNoSePuedeInstanciarConDescartesMenoresACero() {
        // Arrange / Act /  Assert
        assertThrows(CantidadDeDescartesInvalidaException.class, () -> {
            new Ronda(new Puntaje(1000, 1), 3, -1, mock(Mazo.class), mock(Tienda.class), 1);;
        });
    }
    @Test
    public void test04UnaRondaNoSePuedeInstanciarConDescartesIgualesACero() {
        // Arrange / Act /  Assert
        assertThrows(CantidadDeDescartesInvalidaException.class, () -> {
            new Ronda(new Puntaje(1000, 1), 3, 0, mock(Mazo.class), mock(Tienda.class), 1);;
        });
    }
    @Test
    public void test05UnaRondaRecienInstanciadaNoSePasa() {
        // Arrange
        Mazo mazo = new MazoBalatro();
        mazo.agregarCartas(new ArrayList<Carta>(List.of(mock(Carta.class), mock(Carta.class), mock(Carta.class),
                mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class))));
        // Act /  Assert
        assertDoesNotThrow(() -> {
            new Ronda(new Puntaje(1000, 1), 3, 3, mazo, mock(Tienda.class), 1);;
        });
    }
    @Test
    public void test07UnaRondaRecienInstanciadaNoSePierde() {
        // Arrange
        Mazo mazo = new MazoBalatro();
        mazo.agregarCartas(new ArrayList<Carta>(List.of(mock(Carta.class), mock(Carta.class), mock(Carta.class),
                mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class))));
        // Act /  Assert
        assertDoesNotThrow(() -> {
            new Ronda(new Puntaje(1000, 1), 3, 3, mazo, mock(Tienda.class), 1);;
        });
    }
    @Test
    public void test08SiUnaRondaSeCreaConUnPuntajeBaseDe1AlJugarUnaManoDeCualquierCartaDebePasarLaRonda() {
        // Arrange
        Mazo mazo = new MazoBalatro();
        Seleccionadas seleccionadas = new SeleccionadasBalatro();
        Carta carta1 = new CartaInglesa(2, new Corazon());
        Carta carta2 = new CartaInglesa(3, new Corazon());
        Carta carta3 = new CartaInglesa(4, new Corazon());
        Carta carta4 = new CartaInglesa(5, new Corazon());
        Carta carta5 = new CartaInglesa(6, new Corazon());
        ArrayList<Carta> cartasSeleccionadas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5));
        Comodinera comodinera = new Comodinera();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Trebol()),
                new CartaInglesa(3, new Trebol()), new CartaInglesa(5, new Trebol())));
        cartas.addAll(cartasSeleccionadas);
        mazo.agregarCartas(cartas);
        Ronda ronda = new Ronda(new Puntaje(1, 1), 3, 3, mazo, mock(Tienda.class), 1);
        // Act /  Assert
        ronda.iniciarRonda();
        seleccionadas.seleccionarCarta(carta1);
        assertThrows(PasoLaRondaException.class, () -> {
            ronda.jugarMano(seleccionadas, comodinera);
        });
    }
    @Test
    public void test09SiUnaRondaSeQuedaSinManosYNoPasoElPuntajeDebePerderLaRonda() {
        // Arrange
        Mazo mazo = new MazoBalatro();
        Seleccionadas seleccionadas = new SeleccionadasBalatro();
        Carta carta1 = new CartaInglesa(2, new Corazon());
        Carta carta2 = new CartaInglesa(3, new Corazon());
        Carta carta3 = new CartaInglesa(4, new Corazon());
        Carta carta4 = new CartaInglesa(5, new Corazon());
        Carta carta5 = new CartaInglesa(6, new Corazon());
        ArrayList<Carta> cartasSeleccionadas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5));
        Comodinera comodinera = new Comodinera();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Trebol()),
                new CartaInglesa(3, new Trebol()), new CartaInglesa(5, new Trebol())));
        cartas.addAll(cartasSeleccionadas);
        mazo.agregarCartas(cartas);
        Ronda ronda = new Ronda(new Puntaje(1000, 1), 1, 3, mazo, mock(Tienda.class), 1);
        // Act /  Assert
        ronda.iniciarRonda();
        seleccionadas.seleccionarCarta(carta1);
        assertThrows(PerdioLaRondaException.class, () -> {
            ronda.jugarMano(seleccionadas, comodinera);
        });
    }
    @Test
    public void test10SiUnaRondaSeQuedaSinManoPeroPasoElPuntajeDebePasarLaRonda() {
        // Arrange
        Mazo mazo = new MazoBalatro();
        Seleccionadas seleccionadas = new SeleccionadasBalatro();
        Carta carta1 = new CartaInglesa(2, new Corazon());
        Carta carta2 = new CartaInglesa(3, new Corazon());
        Carta carta3 = new CartaInglesa(4, new Corazon());
        Carta carta4 = new CartaInglesa(5, new Corazon());
        Carta carta5 = new CartaInglesa(6, new Corazon());
        ArrayList<Carta> cartasSeleccionadas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5));
        Comodinera comodinera = new Comodinera();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Trebol()),
                new CartaInglesa(3, new Trebol()), new CartaInglesa(5, new Trebol())));
        cartas.addAll(cartasSeleccionadas);
        mazo.agregarCartas(cartas);
        Ronda ronda = new Ronda(new Puntaje(1, 1), 1, 3, mazo, mock(Tienda.class), 1);
        // Act /  Assert
        ronda.iniciarRonda();
        seleccionadas.seleccionarCarta(carta1);
        assertThrows(PasoLaRondaException.class, () -> {
            ronda.jugarMano(seleccionadas, comodinera);
        });
    }
    @Test
    public void test11SiUnaRondaSeQuedaSinDescartesNoPuedeDescartarMasPorLoPeroNoPierdeLaRonda() {
        // Arrange
        Mazo mazo = new MazoBalatro();
        Seleccionadas seleccionadas = new SeleccionadasBalatro();
        Carta carta1 = new CartaInglesa(2, new Corazon());
        Carta carta2 = new CartaInglesa(3, new Corazon());
        Carta carta3 = new CartaInglesa(4, new Corazon());
        Carta carta4 = new CartaInglesa(5, new Corazon());
        Carta carta5 = new CartaInglesa(6, new Corazon());
        ArrayList<Carta> cartasSeleccionadas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5));
        Comodinera comodinera = new Comodinera();
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Trebol()),
                new CartaInglesa(3, new Trebol()), new CartaInglesa(5, new Trebol())));
        mazo.agregarCartas(cartas);
        mazo.agregarCartas(cartasSeleccionadas);
        Ronda ronda = new Ronda(new Puntaje(1, 1), 3, 1, mazo, mock(Tienda.class), 1);
        // Act
        ronda.iniciarRonda();
        mazo.agregarCartas(new ArrayList<>(List.of(mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class))));
        seleccionadas.seleccionarCarta(carta1);
        ronda.descartarMano(seleccionadas, comodinera);
        // Assert
        seleccionadas.seleccionarCarta(carta2);
        assertDoesNotThrow(() -> {
            ronda.descartarMano(seleccionadas, comodinera);
        });
    }
    @Test
    public void test12UnaRondaPuedeComprarUnaCartaDeSuTienda() {
        // Arrange
        Mazo mazo = new MazoBalatro();
        mazo.agregarCartas(new ArrayList<>(List.of(mock(Carta.class), mock(Carta.class), mock(Carta.class),
                mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class))));
        Tienda tiendaMock = mock(Tienda.class);
        doNothing().when(tiendaMock).comprar(any());
        // Act
        Ronda ronda = new Ronda(new Puntaje(1, 1), 3, 1, mazo, tiendaMock, 1);
        // Act / Assert
        assertDoesNotThrow(() -> {
            ronda.comprar((Carta) any());
        });
    }
    @Test
    public void test13UnaRondaPuedeComprarUnComodinDeSuTienda() {
        // Arrange
        Mazo mazo = new MazoBalatro();
        mazo.agregarCartas(new ArrayList<>(List.of(mock(Carta.class), mock(Carta.class), mock(Carta.class),
                mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class))));
        Tienda tiendaMock = mock(Tienda.class);
        doNothing().when(tiendaMock).comprar(any());
        // Act
        Ronda ronda = new Ronda(new Puntaje(1, 1), 3, 1, mazo, tiendaMock, 1);
        // Act / Assert
        assertDoesNotThrow(() -> {
            ronda.comprar((Modificador) any());
        });
    }
    @Test
    public void test14UnaRondaPuedeComprarUnTarotDeSuTienda() {
        // Arrange
        Mazo mazo = new MazoBalatro();
        mazo.agregarCartas(new ArrayList<>(List.of(mock(Carta.class), mock(Carta.class), mock(Carta.class),
                mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class), mock(Carta.class))));
        Tienda tiendaMock = mock(Tienda.class);
        doNothing().when(tiendaMock).comprar(any());
        // Act
        Ronda ronda = new Ronda(new Puntaje(1, 1), 3, 1, mazo, tiendaMock, 1);
        // Act / Assert
        assertDoesNotThrow(() -> {
            ronda.comprar((Tarot) any());
        });
    }
}
