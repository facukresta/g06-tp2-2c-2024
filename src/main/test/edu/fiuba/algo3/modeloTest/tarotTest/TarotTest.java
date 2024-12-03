package edu.fiuba.algo3.modeloTest.tarotTest;


import edu.fiuba.algo3.modelo.juego.CartaAlta;
import edu.fiuba.algo3.modelo.juego.Par;
import edu.fiuba.algo3.modelo.tarot.CambiadorDePuntos;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TarotTest {
    @Test
    public void test01UnTarotPuedeSeIdentificaConUnNombre(){
        // Arrange
        Tarot tarot = new CambiadorDePuntos(10, "Superman");

        //Act / Assert
        assertEquals("Superman", tarot.obtenerNombre());
    }

    @Test
    public void test02UnTarotPuedeReconocerSiEsParaUnJuegoEspecifico(){
        // Arrange
        Tarot tarot = new CambiadorDePuntos(10, new CartaAlta(), "");

        //Act / Assert
        assertTrue(tarot.sosParaEsteJuego(new CartaAlta()));
    }

    @Test
    public void test03UnTarotPuedeReconocerSiNoEsParaUnJuegoEspecifico(){
        // Arrange
        Tarot tarot = new CambiadorDePuntos(10, new Par(), "");

        //Act / Assert
        assertFalse(tarot.sosParaEsteJuego(new CartaAlta()));
    }

    @Test
    public void test04UnTarotCreadoSinJuegoNoEsTarotDeJuegos(){
        // Arrange
        Tarot tarot = new CambiadorDePuntos(10, "");

        //Act / Assert
        assertFalse(tarot.sosTarotDeJuego());
    }

}
