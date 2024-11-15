package edu.fiuba.algo3.modeloTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.puntaje.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PuntajeTest {
    @Test
    public void test01AUnaInstaciaDePuntajeSeLePuedePreguntarSiOtroPuntajeTieneElMismoValor() {
        // Arrange
        Puntaje puntaje1 = new Puntaje(5, 1);
        Puntaje puntaje2 = new Puntaje(5, 1);
        //Act / Assert
        assertTrue(puntaje1.tenesMismoPuntaje(puntaje2));
    }

    @Test
    public void test02AUnaInstaciaDePuntajeSeLePuedePreguntarSiOtroPuntajeTieneElMismoValorAunqueSusPuntosYMultiplicadorSeanDistintos() {
        // Arrange
        Puntaje puntaje1 = new Puntaje(10, 1);
        Puntaje puntaje2 = new Puntaje(5, 2);
        //Act / Assert
        assertTrue(puntaje1.tenesMismoPuntaje(puntaje2));
    }

    @Test
    public void test03AUnaInstaciaDePuntajeSeLePuedeSumarPuntosOtraInstaciaDePuntaje() {
        // Arrange
        Puntaje puntajeBase = new Puntaje(100, 1);
        Puntaje puntajeASumar = new Puntaje(50, 2);
        Puntaje puntajeEsperado = new Puntaje(150, 1);
        //Act
        puntajeBase.sumarPuntos(puntajeASumar);
        //Assert
        assertTrue(puntajeBase.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test04UnPuntajeNoPuedeTenerPuntosNegativosAlInstanciarse() {
        //Arrange /Act / Assert
        assertThrows(PuntosInvalidosException.class, () -> {
            Puntaje puntaje = new Puntaje(-1, 1);
        });
    }
    @Test
    public void test05UnPuntajePuedeTenerPuntosIgualA0AlInstanciarse() {
        //Arrange /Act / Assert
        assertDoesNotThrow(() -> {
            Puntaje puntaje = new Puntaje(0, 1);
        });
    }
    @Test
    public void test06UnPuntajeNoPuedeTenerMultiplicadorNegativosAlInstanciarse() {
        //Arrange /Act / Assert
        assertThrows(MultiplicadorInvalidosException.class, () -> {
            Puntaje puntaje = new Puntaje(1, -1);
        });
    }
    @Test
    public void test07UnPuntajeNoPuedeTenerMultiplicadorIgualA0AlInstanciarse() {
        //Arrange /Act / Assert
        assertThrows(MultiplicadorInvalidosException.class, () -> {
            Puntaje puntaje = new Puntaje(1, 0);
        });
    }
    @Test
    public void test08AUnaInstaciaDePuntajeSeLePuedeSumarMultiplicadorOtraInstaciaDePuntaje() {
        // Arrange
        Puntaje puntajeBase = new Puntaje(1, 20);
        Puntaje puntajeASumar = new Puntaje(1, 30);
        Puntaje puntajeEsperado = new Puntaje(1, 50);
        //Act
        puntajeBase.sumarMultiplicador(puntajeASumar);
        //Assert
        assertTrue(puntajeBase.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test09AUnaInstaciaDePuntajeSeLePuedeSumarOtroPuntaje() {
        // Arrange
        Puntaje puntajeBase = new Puntaje(5, 1);
        Puntaje puntajeASumar = new Puntaje(3, 2);
        Puntaje puntajeEsperado = new Puntaje(8, 3);
        //Act
        puntajeBase.sumar(puntajeASumar);
        //Assert
        assertTrue(puntajeBase.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test10SiAUnaInstaciaDePuntajeSeLeSumaOtroPuntajeConMultiplicador1EsteNoSeSuma() {
        // Arrange
        Puntaje puntajeBase = new Puntaje(5, 2);
        Puntaje puntajeASumar = new Puntaje(3, 1);
        Puntaje puntajeEsperado = new Puntaje(8, 2);
        //Act
        puntajeBase.sumar(puntajeASumar);
        //Assert
        assertTrue(puntajeBase.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11UnaInstaciaDePuntajeSabeReconocerSiEsMenorALaSumaDeUnaListaDePuntajes() {
        // Arrange
        Puntaje puntajeBase = new Puntaje(10, 2);
        Puntaje puntajeASumar1 = new Puntaje(3, 1);
        Puntaje puntajeASumar2 = new Puntaje(4, 1);
        //Act
        boolean esMenorAPuntajes = puntajeBase.esMenorAPuntajes(new ArrayList<>(List.of(puntajeASumar1, puntajeASumar2)));
        //Assert
        assertFalse(esMenorAPuntajes);
    }
}
