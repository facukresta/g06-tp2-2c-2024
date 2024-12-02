package edu.fiuba.algo3.modeloTest.juegoTest;

import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DobleParTest {
    @Test
    public void test01UnaInstanciaDeDobleParSiUnaListaDeCartasConCuatroCartasEsDoblePar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Corazon()),
            new CartaInglesa(10, new Trebol()), new CartaInglesa(12, new Corazon()),
            new CartaInglesa(12, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(60, 2);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test02UnaInstanciaDeDobleParSiUnaListaDeCartasConCincoCartasEsDoblePar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(1, new Pica()), new CartaInglesa(3, new Diamante()),
                new CartaInglesa(3, new Trebol()), new CartaInglesa(5, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(51, 2);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test03UnaInstanciaDeDobleParSiUnaListaDeCartasConCincoCartasEsDobleParAunqueHayaTrio() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(1, new Pica()), new CartaInglesa(1, new Diamante()),
                new CartaInglesa(5, new Diamante()), new CartaInglesa(5, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraDoblePar = new Puntaje(51, 2);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertFalse(esperadoSiFueraDoblePar.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test04UnaInstanciaDeDobleParSiUnaListaDeCartasConCincoCartasEsDobleParAunqueHayaFullHouse() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(1, new Pica()), new CartaInglesa(1, new Diamante()),
                new CartaInglesa(5, new Diamante()), new CartaInglesa(5, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraDoblePar = new Puntaje(51, 2);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertFalse(esperadoSiFueraDoblePar.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test05UnaInstanciaDeDobleParSiUnaListaDeCartasVaciaNoEsDoblePar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>();
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperado = new Puntaje(0, 1);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(esperado.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test06UnaInstanciaDeDobleParSiUnaListaDeCartasConTresCartaDistintasYUnParNoEsDoblePar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(1, new Corazon()),
                new CartaInglesa(1, new Trebol()), new CartaInglesa(1, new Pica()),
                new CartaInglesa(3, new Trebol()), new CartaInglesa(2, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueseDoblePar = new Puntaje(55, 2);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertFalse(esperadoSiFueseDoblePar.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test07UnaInstanciaDeDobleParSiUnaListaDeCartasConTresCartaNoEsDoblePar() {
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(3, new Trebol()),
                new CartaInglesa(3, new Corazon()), new CartaInglesa(3, new Pica())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueseDoblePar = new Puntaje(29, 2);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertFalse(esperadoSiFueseDoblePar.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test08UnaInstanciaDeDobleParSiUnaListaDeCartasCuatroCartasIgualesEsDoblePar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(10, new Pica()),
                new CartaInglesa(10, new Diamante())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraDoblePar = new Puntaje(60, 2);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertFalse(esperadoSiFueraDoblePar.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test09UnaInstanciaDeDobleParSiUnaListaDeCartasConMasDeCincoCartasEsDoblePar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Diamante()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(3, new Pica()), new CartaInglesa(5, new Corazon()),
                new CartaInglesa(6, new Corazon()), new CartaInglesa(7, new Corazon())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueraDoblePar = new Puntaje(64, 2);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(esperadoSiFueraDoblePar.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test10UnaInstanciaDeDobleParDeUnaListaConCincoCartasDeDiferenteValorNoEsDoblePar() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(2, new Pica()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(4, new Diamante()), new CartaInglesa(5, new Trebol())));
        Juego juego = Juego.chequearJuego(cartas);
        Puntaje esperadoSiFueseDoblePar = new Puntaje(44, 2);
        // Act
        Puntaje obtenido = juego.obtenerPuntaje();
        // Assert
        assertFalse(esperadoSiFueseDoblePar.tenesMismoPuntaje(obtenido));
    }
    @Test
    public void test11UnaInstanciaDeDobleParDeUnaListaDeCartasConCuatrCartaDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(10, new Corazon()),
                new CartaInglesa(10, new Trebol()), new CartaInglesa(10, new Pica()),
                new CartaInglesa(10, new Diamante())));
        Puntaje puntajeEsperado = new Puntaje(60, 2);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje puntajeObtenidoSiFueseDoblePar = juego.obtenerPuntaje();
        // Assert
        assertFalse(puntajeObtenidoSiFueseDoblePar.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test12UnaInstanciaDeDobleParDeUnaListaDeCartasConCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(2, new Diamante()),
                new CartaInglesa(2, new Pica()), new CartaInglesa(3, new Corazon()),
                new CartaInglesa(3, new Trebol()), new CartaInglesa(5, new Corazon())));
        Puntaje puntajeEsperado = new Puntaje(35, 2);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje puntajeObtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test13UnaInstanciaDeDobleParSiUnaListaDeCartasVaciaNDevueleCeero() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>();
        Puntaje puntajeEsperado = new Puntaje(0, 1);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje puntajeObtenido = juego.obtenerPuntaje();
        // Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test14UnaInstanciaDeDobleParDeUnaListaDeCartasMasDeCincoCartasDevuelveElValorCorrecto() {
        // Arrange
        ArrayList<Carta> cartas = new ArrayList<>(List.of( new CartaInglesa(1, new Corazon()),
                new CartaInglesa(1, new Diamante()), new CartaInglesa(1, new Pica()),
                new CartaInglesa(7, new Pica()), new CartaInglesa(7, new Corazon()),
                new CartaInglesa(7, new Diamante()), new CartaInglesa(7, new Trebol())));
        Puntaje puntajeEsperadoSiFueseDoblePar = new Puntaje(78, 2);
        Juego juego = Juego.chequearJuego(cartas);
        // Act
        Puntaje puntajeObtenido = juego.obtenerPuntaje();
        // Assert
        assertFalse(puntajeObtenido.tenesMismoPuntaje(puntajeEsperadoSiFueseDoblePar));
    }

    @Test
    public void test07AUnJuegoDobleParSeLeAplicaUnTarotCambiadorDeMultiplicadorDeOchoMultiplicadorDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new DoblePar(), "");
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(48,8);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4,
                new Corazon()), new CartaInglesa(12, new Pica()), new CartaInglesa(12, new Diamante())));
        Juego DoblePar = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = DoblePar.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new DoblePar());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test08AUnJuegoDobleParSeLeAplicaUnTarotCambiadorDeMultiplicadorParaParNoLoModifica() {
        // Arrange
        Tarot tarotCambiadorDeMultiplicador = new CambiadorDeMultiplicador(8, new Escalera(), "");
        Juego.aplicarTarot(tarotCambiadorDeMultiplicador);
        Puntaje puntajeEsperado = new Puntaje(48,2);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()),
                new CartaInglesa(4, new Corazon()), new CartaInglesa(12, new Pica()), new CartaInglesa(12, new Diamante())));
        Juego DoblePar = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = DoblePar.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    @Test
    public void test09AUnJuegoDobleParSeLeAplicaUnTarotCambiadorDePuntosDeDobleParDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(12, new DoblePar(), "");
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(40,2);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()),
                new CartaInglesa(12, new Pica()), new CartaInglesa(12, new Diamante())));
        Juego DoblePar = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = DoblePar.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new DoblePar());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test10AUnJuegoDobleParSeLeAplicaUnTarotCambiadorDePuntosDeParDeOchoPuntosNoDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotCambiadorDePuntos = new CambiadorDePuntos(8, new Par(), "");
        Juego.aplicarTarot(tarotCambiadorDePuntos);
        Puntaje puntajeEsperado = new Puntaje(48,2);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()), new CartaInglesa(12, new Pica()), new CartaInglesa(12, new Diamante())));
        Juego DoblePar = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = DoblePar.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new Par());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test11AUnJuegoDobleParSeLeAplicaUnTarotSumadorDeDobleParDeOchoPuntosDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new DoblePar(), "");
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(56,4);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()), new CartaInglesa(12, new Pica()), new CartaInglesa(12, new Diamante())));
        Juego DoblePar = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = DoblePar.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new DoblePar());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }

    @Test
    public void test12AUnJuegoDobleParSeLeAplicaUnTarotSumadorDeParDeOchoPuntosNoSeDevuelveEsePuntajeModificado() {
        // Arrange
        Tarot tarotSumador = new Sumador(8,2, new Escalera(), "");
        Juego.aplicarTarot(tarotSumador);
        Puntaje puntajeEsperado = new Puntaje(48,2);
        // Act
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Pica()), new CartaInglesa(4, new Corazon()), new CartaInglesa(12, new Pica()), new CartaInglesa(12, new Diamante())));
        Juego DoblePar = Juego.chequearJuego(cartas);
        Puntaje puntajeObtenido = DoblePar.obtenerPuntaje();
        // Assert
        Tarot tarot2 = new SinTarot(new Escalera());
        Juego.aplicarTarot(tarot2);
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
}
