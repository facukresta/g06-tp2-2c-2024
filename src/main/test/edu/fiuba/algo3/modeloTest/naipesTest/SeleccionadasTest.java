package edu.fiuba.algo3.modeloTest.naipesTest;

import edu.fiuba.algo3.modelo.juego.Par;
import edu.fiuba.algo3.modelo.naipes.*;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.naipes.carta.CartaInglesa;
import edu.fiuba.algo3.modelo.naipes.carta.Corazon;
import edu.fiuba.algo3.modelo.naipes.carta.Pica;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class SeleccionadasTest {

    @Test
    public void test01AlCrearUnasSeleccionadasEstanVacias(){
        // Arrange / Act
        Seleccionadas seleccionadas = new SeleccionadasBalatro();

        // Assert
        assertEquals(0, seleccionadas.obtenerCartasSeleccionadas().size());
    }

    @Test
    public void test02UnasSeleccionadasSabenDetectarSiEstanVacias(){
        // Arrange / Act
        Seleccionadas seleccionadas = new SeleccionadasBalatro();

        // Assert
        assertTrue(seleccionadas.estaVacio());
    }

    @Test
    public void test03UnasSeleccionadasSabenDetectarSiNoEstanVacias(){
        // Arrange
        Seleccionadas seleccionadas = new SeleccionadasBalatro();

        // Act
        seleccionadas.seleccionarCarta(mock(Carta.class));
        // Assert
        assertFalse(seleccionadas.estaVacio());
    }

    @Test
    public void test04SiSeAniadenMasDe5CartasEstasSimplementeNoSeAniaden(){
        // Arrange
        Seleccionadas seleccionadas = new SeleccionadasBalatro();
        seleccionadas.seleccionarCarta(mock(Carta.class));
        seleccionadas.seleccionarCarta(mock(Carta.class));
        seleccionadas.seleccionarCarta(mock(Carta.class));
        seleccionadas.seleccionarCarta(mock(Carta.class));
        seleccionadas.seleccionarCarta(mock(Carta.class));
        // Act
        int cantidadDeCartas = seleccionadas.obtenerCartasSeleccionadas().size();
        seleccionadas.seleccionarCarta(mock(Carta.class));
        int cantidadDeCartasLuegoDeAniadirLaSexta = seleccionadas.obtenerCartasSeleccionadas().size();
        // Assert
        assertEquals(cantidadDeCartas, cantidadDeCartasLuegoDeAniadirLaSexta);
    }

    @Test
    public void test05LasSeleccionadasSabenQueJuegoForman(){
        // Arrange
        Seleccionadas seleccionadas = new SeleccionadasBalatro();
        seleccionadas.seleccionarCarta(new CartaInglesa(2, new Corazon()));
        seleccionadas.seleccionarCarta(new CartaInglesa(2, new Pica()));
        // Act / Assert
        assertTrue(seleccionadas.obtenerJuego() instanceof Par);
    }
}
