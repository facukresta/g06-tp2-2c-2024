package edu.fiuba.algo3.modeloTest.tarotTest;

import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaroteraTest {

    @Test
    public void test01UnaTaroteraAlCrearseTieneCincoTarotsDeTipoSinTarot(){
        //Arrange
        Tarotera tarotera = new Tarotera();

        //Act
        ArrayList<Tarot> tarotsObtenidos = tarotera.obtenerTarots();

        //Assert
        for (Tarot comodin : tarotsObtenidos) {
            assertTrue(comodin instanceof SinTarot);
        }
    }

    @Test
    public void test02SiSeAgregaUnTarotReemplazaraAlPrimerTarotDeTipoSinTarotYSeMantienenLosPosteriores(){
        //Arrange
        Tarotera tarotera = new Tarotera();

        //Act
        tarotera.agregarTarot(new CambiadorDePuntos(10, ""));
        ArrayList<Tarot> tarotsObtenidos = tarotera.obtenerTarots();

        //Assert
        assertTrue(tarotsObtenidos.remove(0) instanceof CambiadorDePuntos);
        for (Tarot comodin : tarotsObtenidos) {
            assertTrue(comodin instanceof SinTarot);
        }
    }

    @Test
    public void test03AlPedirLaCantidadDeTarotsSoloSeContaranLosQueNoSonDeTipoSinTarot(){
        //Arrange
        Tarotera tarotera = new Tarotera();
        tarotera.agregarTarot(new CambiadorDePuntos(10, ""));

        //Act / Assert
        assertEquals(1, tarotera.cantidadDeTarots());
    }

    @Test
    public void test04SePuedeQuitarUnTarot(){
        //Arrange
        Tarotera tarotera = new Tarotera();
        Tarot tarotAQuitar = new CambiadorDePuntos(10, "");
        Tarot tarotAQuedarse = (new CambiadorDeMultiplicador(10, ""));
        tarotera.agregarTarot(tarotAQuitar);
        tarotera.agregarTarot(tarotAQuedarse);

        //Act
        tarotera.quitarTarot(tarotAQuitar);
        ArrayList<Tarot> tarotsObtenidos = tarotera.obtenerTarots();

        //Assert
        assertTrue(tarotsObtenidos.get(0) instanceof SinTarot);
        assertTrue(tarotsObtenidos.get(1) instanceof CambiadorDeMultiplicador);
    }

    @Test
    public void test05AlAniadirTresTarotsEsteUltimoNoSeAgrega(){
        //Arrange
        Tarotera tarotera = new Tarotera();

        tarotera.agregarTarot(new CambiadorDePuntos(10, ""));
        tarotera.agregarTarot(new CambiadorDePuntos(10, ""));
        int cantidadDeTarotsSinAniadirElTercero = tarotera.obtenerTarots().size();

        //Act
        tarotera.agregarTarot(new CambiadorDePuntos(10, ""));
        int cantidadDeTarotsAlAniadirElTercero = tarotera.obtenerTarots().size();
        //Assert
        assertEquals(cantidadDeTarotsSinAniadirElTercero, cantidadDeTarotsAlAniadirElTercero);
    }
}
