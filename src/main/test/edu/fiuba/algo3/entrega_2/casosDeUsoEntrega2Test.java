package edu.fiuba.algo3.entrega_2;


import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.Mano;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Long;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.io.FileReader;

public class casosDeUsoEntrega2Test {
    // Verificar que al tener un comodín que sume 8 al multiplicador se aplique correctamente
    @Test
    public void test01AlTenerUnComodinQueSuma8AlMultiplicadorEsteSeAplicaCorrectamente() {
        //Arrange
        Comodin comodin = new SumaMultiplicador(8, new Aleatorio(1));
        Puntaje puntajeEsperado = new Puntaje(10,8);
        Puntaje puntajeObtenido = new Puntaje(10,1);
        //Act
        comodin.aplicarModificador(puntajeObtenido, new CartaAlta());
        //Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    // Verificar que el jugador recibe un aumento correspondiente si tiene el comodín que aumenta el multiplicador por 3 si juega una escalera
    @Test
    public void test02ElJugadorRecibeUnAumentoSiTieneElComodinQueAumentaElMultiplicadorPor3PorSiJuegoUnaEscalera() {
        //Arrange
        Puntaje puntajeEsperado = new Puntaje(54,7);
        Comodin comodin = new SumaMultiplicador(3, new Escalera());
        Mano mano = new Mano(8);
        mano.agregarCartas(new ArrayList<>(List.of(new Carta(1, new Trebol()), new Carta(2, new Trebol()),
                new Carta(3, new Pica()), new Carta(4, new Corazon()),
                new Carta(5, new Trebol()))));
        mano.elegirCarta(new Carta(1, new Trebol()));
        mano.elegirCarta(new Carta(2, new Trebol()));
        mano.elegirCarta(new Carta(3, new Pica()));
        mano.elegirCarta(new Carta(4, new Corazon()));
        mano.elegirCarta(new Carta(5, new Trebol()));
        mano.agregarComodin(comodin);
        //Act
        Puntaje puntajeObtenido = mano.jugarMano();
        //Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    // Verificar que el jugador si posee un comodin que suma 10 puntos por descarte, al descartar sume la cantidad correcta.
    @Test
    public void test03ElJugadorSiPoseeUnComodinQueSuma10PuntosPorDescarteAlDescartarSumeLaCantidadCorrecta() {
        //Arrange
        Puntaje puntajeEsperado = new Puntaje(10,1);
        Comodin comodin = new SumaPuntosDescarte(10, new Aleatorio(1));
        Mano mano = new Mano(8);
        mano.agregarCartas(new ArrayList<>(List.of(new Carta(1, new Trebol()), new Carta(2, new Trebol()),
                new Carta(3, new Pica()), new Carta(4, new Corazon()),
                new Carta(5, new Trebol()))));
        mano.elegirCarta(new Carta(1, new Trebol()));
        mano.elegirCarta(new Carta(2, new Trebol()));
        mano.agregarComodin(comodin);
        // Act
        Puntaje puntajeObtenido = mano.descartarMano();
        //Assert
        assertTrue(puntajeObtenido.tenesMismoPuntaje(puntajeEsperado));
    }
    // Verificar que si el jugador posee un comodin que tiene chance 1 sobre 1000 de aplicarse correctamente.
    @Test
    public void test04ElJugadorConUnComodinConChancesDeAplicarUnModificadorDe1Sobre1000ConEfectoDeSumaDeMultiplicadorAplicaEseEfectoCuandoLasProbabilidadesEstanDadas(){
        //Arrange
        Aleatorio aleatorioMock = mock(Aleatorio.class);
        when(aleatorioMock.sucede()).thenReturn(false);
        Comodin comodin = new SumaMultiplicador(13, aleatorioMock);
        Mano mano = new Mano(8);
        mano.agregarCarta(new Carta(7, new Trebol()));
        mano.agregarCarta(new Carta(7, new Corazon()));
        mano.elegirCarta(new Carta(7, new Trebol()));
        mano.agregarComodin(comodin);
        Puntaje puntajeEsperado1 = new Puntaje(12,1);
        Puntaje puntajeEsperado2 = new Puntaje(12,13);
        // Act
        Puntaje puntajeObtenido1 = mano.jugarMano();
        when(aleatorioMock.sucede()).thenReturn(true);
        mano.elegirCarta(new Carta(7, new Corazon()));
        Puntaje puntajeObtenido2 = mano.jugarMano();
        // Assert
        assertTrue(puntajeEsperado1.tenesMismoPuntaje(puntajeObtenido1));
        assertTrue(puntajeEsperado2.tenesMismoPuntaje(puntajeObtenido2));
    }
//     El jugador activa un comodín con una combinación de efectos  bonus de mano jugada + puntaje aumentado + activación aleatoria
    @Test
    public void test05UnComodinConCombinacionDeEfectosBonus(){
        //Arrange
        Mano mano = new Mano(8);
        Aleatorio aleatorioMock = mock(Aleatorio.class);
        when(aleatorioMock.sucede()).thenReturn(false);
        Puntaje puntajeEsperado1 = new Puntaje(24,2);
        Puntaje puntajeEsperado2 = new Puntaje(37,2);
        Comodin comodin = new SumaPuntos(13, new Par(), aleatorioMock);
        mano.agregarComodin(comodin);
        mano.agregarCarta(new Carta(7, new Trebol()));
        mano.agregarCarta(new Carta(7, new Corazon()));
        mano.elegirCarta(new Carta(7, new Trebol()));
        mano.elegirCarta(new Carta(7, new Corazon()));
        // Act
        Puntaje puntajeObtenido1 = mano.jugarMano();
        when(aleatorioMock.sucede()).thenReturn(true);
        mano.agregarCarta(new Carta(7, new Trebol()));
        mano.agregarCarta(new Carta(7, new Corazon()));
        mano.elegirCarta(new Carta(7, new Trebol()));
        mano.elegirCarta(new Carta(7, new Corazon()));
        Puntaje puntajeObtenido2 = mano.jugarMano();
        // Assert
        assertTrue(puntajeEsperado1.tenesMismoPuntaje(puntajeObtenido1));
        assertTrue(puntajeEsperado2.tenesMismoPuntaje(puntajeObtenido2));
    }
    // Verificar la lectura y posterior conversión a unidades del modelo de dominio del JSON

    @Test
    public void test06() {
        // Arrange
        // Mapas para traducir palos y números desde el JSON
        HashMap<String, Palo> mapaDePalos = new HashMap<>();
        mapaDePalos.put("Trebol", new Trebol());
        mapaDePalos.put("Diamantes", new Diamante());
        mapaDePalos.put("Picas", new Pica());
        mapaDePalos.put("Corazones", new Corazon());

        HashMap<String, Integer> mapaDeNumeros = new HashMap<>();
        mapaDeNumeros.put("As", 1);
        mapaDeNumeros.put("Rey", 13);
        mapaDeNumeros.put("Reina", 12);
        mapaDeNumeros.put("Jota", 11);
        mapaDeNumeros.put("2", 2);
        mapaDeNumeros.put("3", 3);
        mapaDeNumeros.put("4", 4);
        mapaDeNumeros.put("5", 5);
        mapaDeNumeros.put("6", 6);
        mapaDeNumeros.put("7", 7);
        mapaDeNumeros.put("8", 8);
        mapaDeNumeros.put("9", 9);
        mapaDeNumeros.put("10", 10);

        Mazo mazo1 = new Mazo();
        // Act
        try {
            // Leer el archivo JSON desde la carpeta resources
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("json/mazo.json");

            if (inputStream == null) {
                throw new FileNotFoundException("Archivo mazo.json no encontrado en src/test/resources/json");
            }

            // Parsear el JSON
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(inputStream));
            
            // Extraer el arreglo de cartas
            JSONArray mazo = (JSONArray) jsonObject.get("mazo");

            for (Object obj : mazo) {
                JSONObject cartaJson = (JSONObject) obj;

                cartaJson.get("nombre");
                String palo = (String) cartaJson.get("palo");
                String numero = (String) cartaJson.get("numero");
                cartaJson.get("puntos");
                cartaJson.get("multiplicador");

                // Crear y agregar la carta al mazo
                mazo1.agregarCarta(new Carta(mapaDeNumeros.get(numero), mapaDePalos.get(palo)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Assert
    }

//    // Planteo inicial de interfaz gráfica (mockups/dibujos), pantalla donde se muestra una ronda
//    @Test
//    public void test07() {
//        // Arrange
//
//        // Act
//
//        // Assert
//
//    }
}
