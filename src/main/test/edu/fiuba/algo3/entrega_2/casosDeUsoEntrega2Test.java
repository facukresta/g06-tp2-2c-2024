package edu.fiuba.algo3.entrega_2;


import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.Mano;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
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
        Comodin comodin = new SumaMultiplicador(8);
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
        Comodin comodin = new SumaPuntosDescarte(10);
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
    public void test06LecturaDelMazoJSONYConversionAlModeloDeDominio() {
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
    @Test
    public void test07LecturaDeTarotsJSONYConversionAlModeloDeDominio() {
        // Arrange
        // Mapas para traducir palos y números desde el JSON
        HashMap<String, Juego> mapaDeManos = new HashMap<>();
        mapaDeManos.put("carta alta", new CartaAlta());
        mapaDeManos.put("par", new Par());
        mapaDeManos.put("doble par", new DoblePar());
        mapaDeManos.put("trio", new Trio());
        mapaDeManos.put("full", new FullHouse());
        mapaDeManos.put("color", new Color());
        mapaDeManos.put("poker", new Poker());
        mapaDeManos.put("escalera", new Escalera());
        mapaDeManos.put("escalera de color", new EscaleraDeColor());
        mapaDeManos.put("escalera real", new EscaleraReal());


        ArrayList<Tarot> listaDeTarots = new ArrayList<>();
        // Act
        try {
            // Leer el archivo JSON desde la carpeta resources
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("json/tarots.json");

            if (inputStream == null) {
                throw new FileNotFoundException("Archivo tarots.json no encontrado en src/test/resources/json");
            }

            // Parsear el JSON
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(inputStream));

            // Extraer el arreglo de tarots
            JSONArray tarots = (JSONArray) jsonObject.get("tarots");

            for (Object obj : tarots) {
                JSONObject cartaJson = (JSONObject) obj;

                JSONObject efectosJson = (JSONObject) cartaJson.get("efecto");
                int puntos = ((Long) efectosJson.get("puntos")).intValue();
                double multiplicador = ((Number) efectosJson.get("multiplicador")).doubleValue();
                String sobre = (String) cartaJson.get("sobre");
                String ejemplar = (String)cartaJson.get("ejemplar");

                // Crear y agregar el tarot la listaDeTarots
                if (sobre.equals("carta")) {
                    if (puntos == 1) {
                        listaDeTarots.add(new CambiadorDeMultiplicador(multiplicador));
                    } else {
                        listaDeTarots.add(new CambiadorDePuntos(puntos));
                    }
                } else if (sobre.equals("mano")) {
                    listaDeTarots.add(new Sumador(puntos, multiplicador, mapaDeManos.get(ejemplar)));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Assert

    }
    @Test
    public void test08LecturaDeComodinesJSONYConversionAlModeloDeDominio() {
        // Arrange
        // Mapas para traducir palos y números desde el JSON
        HashMap<String, Juego> mapaDeManos = new HashMap<>();
        mapaDeManos.put("carta alta", new CartaAlta());
        mapaDeManos.put("par", new Par());
        mapaDeManos.put("doble par", new DoblePar());
        mapaDeManos.put("trio", new Trio());
        mapaDeManos.put("full", new FullHouse());
        mapaDeManos.put("color", new Color());
        mapaDeManos.put("poker", new Poker());
        mapaDeManos.put("escalera", new Escalera());
        mapaDeManos.put("escalera de color", new EscaleraDeColor());
        mapaDeManos.put("escalera real", new EscaleraReal());

        ArrayList<Modificador> listaDeComodines = new ArrayList<>();
        // Act
        try {
            // Leer el archivo JSON desde la carpeta resources
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("json/comodines.json");

            if (inputStream == null) {
                throw new FileNotFoundException("Archivo comodines.json no encontrado en src/test/resources/json");
            }

            // Parsear el JSON
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(inputStream));

            // Extraer el arreglo de comodines


            JSONArray comodinesPuntaje = (JSONArray) ((JSONObject) jsonObject.get("Al Puntaje")).get("comodines");
            JSONArray comodinesJuego = (JSONArray) ((JSONObject) jsonObject.get("Bonus por Mano Jugada")).get("comodines");
            JSONArray comodinesDescarte = (JSONArray) ((JSONObject) jsonObject.get("Bonus por Descarte")).get("comodines");
            JSONArray comodinesAleatorio = (JSONArray) ((JSONObject) jsonObject.get("Chance de activarse aleatoriamente")).get("comodines");
            JSONArray comodinesCombinacion =(JSONArray) ((JSONObject) jsonObject.get("Combinación")).get("comodines");

            for (Object obj : comodinesPuntaje) {
                JSONObject comodin = (JSONObject) obj;

                JSONObject efectosJson = (JSONObject) comodin.get("efecto");
                int puntos = ((Long) efectosJson.get("puntos")).intValue();
                double multiplicador = ((Number) efectosJson.get("multiplicador")).doubleValue();

                if (puntos == 1) {
                    listaDeComodines.add(new SumaMultiplicador(multiplicador));
                } else {
                    listaDeComodines.add(new SumaPuntos(puntos));
                }
            }

            for (Object obj : comodinesJuego) {
                JSONObject comodin = (JSONObject) obj;

                JSONObject activacion = (JSONObject) comodin.get("activacion");
                String juego = (String) activacion.get("Mano Jugada");
                JSONObject efectosJson = (JSONObject) comodin.get("efecto");
                int puntos = ((Long) efectosJson.get("puntos")).intValue();
                double multiplicador = ((Number) efectosJson.get("multiplicador")).doubleValue();

                if (puntos == 1) {
                    listaDeComodines.add(new SumaMultiplicador(multiplicador, mapaDeManos.get(juego)));
                } else {
                    listaDeComodines.add(new SumaPuntos(puntos, mapaDeManos.get(juego)));
                }
            }

            for (Object obj : comodinesDescarte) {
                JSONObject comodin = (JSONObject) obj;

                JSONObject efectosJson = (JSONObject) comodin.get("efecto");
                int puntos = ((Long) efectosJson.get("puntos")).intValue();
                double multiplicador = ((Number) efectosJson.get("multiplicador")).doubleValue();

                if (puntos == 1) {
                    listaDeComodines.add(new SumaMultiplicadorDescarte(multiplicador));
                } else {
                    listaDeComodines.add(new SumaPuntosDescarte(puntos));
                }
            }

            for (Object obj : comodinesAleatorio) {
                JSONObject comodin = (JSONObject) obj;

                JSONObject activacion = (JSONObject) comodin.get("activacion");
                int probabilidad = ((Long) activacion.get("1 en")).intValue();
                JSONObject efectosJson = (JSONObject) comodin.get("efecto");
                int puntos = ((Long) efectosJson.get("puntos")).intValue();
                double multiplicador = ((Number) efectosJson.get("multiplicador")).doubleValue();

                if (puntos == 1) {
                    listaDeComodines.add(new SumaMultiplicador(multiplicador, new Aleatorio(probabilidad)));
                } else {
                    listaDeComodines.add(new SumaPuntos(puntos, new Aleatorio(probabilidad)));
                }
            }

            for (Object obj : comodinesCombinacion) {
                JSONObject comodin = (JSONObject) obj;
                JSONArray comodinesDentro = (JSONArray) comodin.get("comodines");
                ArrayList<Comodin> comodinesPequenios = new ArrayList<>();
                for (Object obj2 : comodinesDentro) {
                    JSONObject comodinDentro = (JSONObject) obj2;

                    String activador = comodinDentro.get("activacion").toString();
                    if (!activador.equals("Descarte")) {
                        JSONObject activacion = (JSONObject) comodinDentro.get("activacion");
                        for (String clave : List.of("1 en", "Mano Jugada")) {
                            if (activacion.get(clave) != null) {
                                activador = clave;
                            }
                        }
                    }
                    JSONObject efectosJson = (JSONObject) comodinDentro.get("efecto");
                    int puntos = ((Long) efectosJson.get("puntos")).intValue();
                    double multiplicador = ((Number) efectosJson.get("multiplicador")).doubleValue();

                    if (activador.equals("Descarte")) {
                        if (puntos == 1) {
                            comodinesPequenios.add(new SumaMultiplicadorDescarte(multiplicador));
                        } else {
                            comodinesPequenios.add(new SumaPuntosDescarte(puntos));
                        }
                    }

                    if (activador.equals("1 en")) {
                        JSONObject activacion = (JSONObject) comodinDentro.get("activacion");
                        int probabilidad = ((Long) activacion.get("1 en")).intValue();
                        if (puntos == 1) {
                            comodinesPequenios.add(new SumaMultiplicador(multiplicador, new Aleatorio(probabilidad)));
                        } else {
                            comodinesPequenios.add(new SumaPuntos(puntos, new Aleatorio(probabilidad)));
                        }
                    }

                    if (activador.equals("Mano Jugada")) {
                        JSONObject activacion = (JSONObject) comodinDentro.get("activacion");
                        String juego = (String) activacion.get("Mano Jugada");
                        if (puntos == 1) {
                            comodinesPequenios.add(new SumaMultiplicador(multiplicador, mapaDeManos.get(juego)));
                        } else {
                            comodinesPequenios.add(new SumaPuntos(puntos, mapaDeManos.get(juego)));
                        }
                    }
                }
                listaDeComodines.add(new CartaComodin(comodinesPequenios));
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
