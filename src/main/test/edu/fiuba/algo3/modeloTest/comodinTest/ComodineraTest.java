package edu.fiuba.algo3.modeloTest.comodinTest;

import edu.fiuba.algo3.modelo.comodin.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComodineraTest {
    @Test
    public void test01UnaComodineraAlCrearseTieneCincoComodinesDeTipoSinComodin(){
        //Arrange
        Comodinera comodinera = new Comodinera();

        //Act
        ArrayList<Modificador> comodinesObtenidos = comodinera.obtenerComodines();

        //Assert
        for (Modificador comodin : comodinesObtenidos) {
            assertTrue(comodin instanceof SinComodin);
        }
    }

    @Test
    public void test02SiSeAgregaUnComodinReemplazaraAlPrimerComodinDeTipoSinComodinYSeMantienenLosPosteriores(){
        //Arrange
        Comodinera comodinera = new Comodinera();

        //Act
        comodinera.agregarComodin(new SumaPuntos(10, ""));
        ArrayList<Modificador> comodinesObtenidos = comodinera.obtenerComodines();

        //Assert
        assertTrue(comodinesObtenidos.remove(0) instanceof SumaPuntos);
        for (Modificador comodin : comodinesObtenidos) {
            assertTrue(comodin instanceof SinComodin);
        }
    }

    @Test
    public void test03AlPedirLaCantidadDeComodinesSoloSeContaranLosQueNoSonDeTipoSinComodin(){
        //Arrange
        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(new SumaPuntos(10, ""));
        comodinera.agregarComodin(new SumaPuntos(10, ""));
        comodinera.agregarComodin(new SumaPuntos(10, ""));

        //Act / Assert
        assertEquals(3, comodinera.cantidadDeComodines());
    }

    @Test
    public void test04SePuedenIntencambiarLaPosicionDeDosComodines(){
        //Arrange
        Comodinera comodinera = new Comodinera();
        Modificador comodin1 = new SumaPuntos(10, "");
        Modificador comodin2 = (new SumaPuntosDescarte(10, ""));
        comodinera.agregarComodin(comodin1);
        comodinera.agregarComodin(comodin2);

        //Act
        comodinera.cambiarOrden(comodin1, comodin2);
        ArrayList<Modificador> comodinesObtenidos = comodinera.obtenerComodines();

        //Assert
        assertTrue(comodinesObtenidos.get(0) instanceof SumaPuntosDescarte);
        assertTrue(comodinesObtenidos.get(1) instanceof SumaPuntos);
    }

    @Test
    public void test05AlAniadirSeisComodinesEsteUltimoNoSeAgrega(){
        //Arrange
        Comodinera comodinera = new Comodinera();
        Modificador comodinMock = mock(Modificador.class);
        when(comodinMock.obtenerNombre()).thenReturn("Superman");
        comodinera.agregarComodin(comodinMock);
        comodinera.agregarComodin(comodinMock);
        comodinera.agregarComodin(comodinMock);
        comodinera.agregarComodin(comodinMock);
        comodinera.agregarComodin(comodinMock);
        int cantidadDeComodinesSinAniadirElSexto = comodinera.obtenerComodines().size();

        //Act
        comodinera.agregarComodin(comodinMock);
        int cantidadDeComodinesAlAniadirElSexto = comodinera.obtenerComodines().size();
        //Assert
        assertEquals(cantidadDeComodinesAlAniadirElSexto, cantidadDeComodinesSinAniadirElSexto);
    }
}
