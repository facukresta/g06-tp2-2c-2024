package edu.fiuba.algo3.entrega_2;



import com.sun.jdi.request.ModificationWatchpointRequest;
import edu.fiuba.algo3.LectorComodines;
import edu.fiuba.algo3.LectorDeCartas;
import edu.fiuba.algo3.LectorDeTarots;
import edu.fiuba.algo3.modelo.aleatorio.Aleatorio;
import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.naipes.Mano;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.tarot.*;
import edu.fiuba.algo3.modeloTest.comodinTest.CartaComodinTest;
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

public class casosDeUsoEntrega2Test {
    // Verificar que al tener un comodín que sume 8 al multiplicador se aplique correctamente
    @Test
    public void test01AlTenerUnComodinQueSuma8AlMultiplicadorEsteSeAplicaCorrectamente() {
        //Arrange
        Comodin comodin = new SumaMultiplicador(8);
        Puntaje puntajeEsperado = new Puntaje(10,8);
        Puntaje puntajeAModificar = new Puntaje(10,1);
        //Act
        comodin.aplicarModificador(puntajeAModificar, new CartaAlta());
        //Assert
        assertTrue(puntajeEsperado.tenesMismoPuntaje(puntajeAModificar));
    }

    // Verificar que el jugador recibe un aumento correspondiente si tiene el comodín que aumenta el multiplicador por 3 si juega una escalera
    @Test
    public void test02ElJugadorRecibeUnAumentoSiTieneElComodinQueAumentaElMultiplicadorPor3PorSiJuegoUnaEscalera() {
        //Arrange
        Puntaje puntajeEsperado = new Puntaje(54,7);
        Comodin comodin = new SumaMultiplicador(3, new Escalera());
        Mano mano = new Mano(8);
        Carta carta1 = new Carta(1, new Trebol());
        Carta carta2 = new Carta(2, new Trebol());
        Carta carta3 = new Carta(3, new Pica());
        Carta carta4 = new Carta(4, new Corazon());
        Carta carta5 = new Carta(5, new Trebol());
        mano.agregarCartas(new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5)));
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        mano.elegirCarta(carta3);
        mano.elegirCarta(carta4);
        mano.elegirCarta(carta5);
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
        Carta carta1 = new Carta(1, new Trebol());
        Carta carta2 = new Carta(2, new Trebol());
        Carta carta3 = new Carta(3, new Pica());
        Carta carta4 = new Carta(4, new Corazon());
        Carta carta5 = new Carta(5, new Trebol());
        mano.agregarCartas(new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5)));
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
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
        Carta carta1 = new Carta(7, new Trebol());
        Carta carta2 = new Carta(7, new Corazon());
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        mano.elegirCarta(carta1);
        mano.agregarComodin(comodin);
        Puntaje puntajeEsperado1 = new Puntaje(12,1);
        Puntaje puntajeEsperado2 = new Puntaje(12,13);
        // Act
        Puntaje puntajeObtenido1 = mano.jugarMano();
        when(aleatorioMock.sucede()).thenReturn(true);
        mano.elegirCarta(carta2);
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
        Carta carta1 = new Carta(7, new Trebol());
        Carta carta2 = new Carta(7, new Corazon());
        mano.agregarComodin(comodin);
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        // Act
        Puntaje puntajeObtenido1 = mano.jugarMano();
        when(aleatorioMock.sucede()).thenReturn(true);
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        mano.elegirCarta(carta1);
        mano.elegirCarta(carta2);
        Puntaje puntajeObtenido2 = mano.jugarMano();
        // Assert
        assertTrue(puntajeEsperado1.tenesMismoPuntaje(puntajeObtenido1));
        assertTrue(puntajeEsperado2.tenesMismoPuntaje(puntajeObtenido2));
    }
    // Verificar la lectura y posterior conversión a unidades del modelo de dominio del JSON

    @Test
    public void test06LecturaDelMazoJSONYConversionAlModeloDeDominio() {
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
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("json/mazo.json");
            if (inputStream == null) {
                throw new FileNotFoundException("Archivo mazo.json no encontrado en src/test/resources/json");
            }
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(inputStream));
            JSONArray mazo = (JSONArray) jsonObject.get("mazo");
            for (Object obj : mazo) {
                JSONObject cartaJson = (JSONObject) obj;
                cartaJson.get("nombre");
                String palo = (String) cartaJson.get("palo");
                String numero = (String) cartaJson.get("numero");
                cartaJson.get("puntos");
                cartaJson.get("multiplicador");
                mazo1.agregarCarta(new Carta(mapaDeNumeros.get(numero), mapaDePalos.get(palo)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test06p2LecturaDelMazoJSONYConversionAlModeloDeDominio() {
        LectorDeCartas lectorDeCartas = new LectorDeCartas();
        ArrayList<Carta> cartas = lectorDeCartas.leerCartas();
        boolean resultadoComparacion = true;

        //Mazo mazo = new Mazo();
        //mazo.agregarCartas(cartas);

        ArrayList<Carta> cartasQueDeberianEstar = new ArrayList<>(List.of(
                new Carta(1, new Trebol()), new Carta(2, new Trebol()), new Carta(3, new Trebol()),
                new Carta(4, new Trebol()), new Carta(5, new Trebol()), new Carta(6, new Trebol()),
                new Carta(7, new Trebol()), new Carta(8, new Trebol()), new Carta(9, new Trebol()),
                new Carta(10, new Trebol()), new Carta(11, new Trebol()), new Carta(12, new Trebol()),
                new Carta(13, new Trebol()),
                new Carta(1, new Corazon()), new Carta(2, new Corazon()), new Carta(3, new Corazon()),
                new Carta(4, new Corazon()), new Carta(5, new Corazon()), new Carta(6, new Corazon()),
                new Carta(7, new Corazon()), new Carta(8, new Corazon()), new Carta(9, new Corazon()),
                new Carta(10, new Corazon()), new Carta(11, new Corazon()), new Carta(12, new Corazon()),
                new Carta(13, new Corazon()),
                new Carta(1, new Pica()), new Carta(2, new Pica()), new Carta(3, new Pica()),
                new Carta(4, new Pica()), new Carta(5, new Pica()), new Carta(6, new Pica()),
                new Carta(7, new Pica()), new Carta(8, new Pica()), new Carta(9, new Pica()),
                new Carta(10, new Pica()), new Carta(11, new Pica()), new Carta(12, new Pica()),
                new Carta(13, new Pica()),
                new Carta(1, new Diamante()), new Carta(2, new Diamante()), new Carta(3, new Diamante()),
                new Carta(4, new Diamante()), new Carta(5, new Diamante()), new Carta(6, new Diamante()),
                new Carta(7, new Diamante()), new Carta(8, new Diamante()), new Carta(9, new Diamante()),
                new Carta(10, new Diamante()), new Carta(11, new Diamante()), new Carta(12, new Diamante()),
                new Carta(13, new Diamante())
        ));

        if(cartas.size() != cartasQueDeberianEstar.size()){
            resultadoComparacion = false;
        }
        for (int i = 0; i < cartas.size(); i++) {
            Carta carta1 = cartas.get(i);
            Carta carta2 = cartasQueDeberianEstar.get(i);
            if (carta1.obtenerNumero() != carta2.obtenerNumero() || !carta1.esDelMismoPalo(carta2)) {
                resultadoComparacion = false;
            }
        }
        assertTrue(resultadoComparacion);
    }

    @Test
    public void test07LecturaDeTarotsJSONYConversionAlModeloDeDominio() {
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
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("json/tarots.json");
            if (inputStream == null) {
                throw new FileNotFoundException("Archivo tarots.json no encontrado en src/test/resources/json");
            }
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(inputStream));
            JSONArray tarots = (JSONArray) jsonObject.get("tarots");
            for (Object obj : tarots) {
                JSONObject cartaJson = (JSONObject) obj;
                JSONObject efectosJson = (JSONObject) cartaJson.get("efecto");
                int puntos = ((Long) efectosJson.get("puntos")).intValue();
                double multiplicador = ((Number) efectosJson.get("multiplicador")).doubleValue();
                String sobre = (String) cartaJson.get("sobre");
                String ejemplar = (String)cartaJson.get("ejemplar");
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
    }
    @Test
    public void test07p2LecturaDeTarotsJSONYConversionAlModeloDeDominio() {
        LectorDeTarots lectorDeTarots = new LectorDeTarots();
        ArrayList<Tarot> tarots = lectorDeTarots.leerTarots();

        boolean resultadoComparacion = true;

        ArrayList<Tarot> tarotsQueDeberianEstar = new ArrayList<>(List.of(
            new Sumador(10, 2, new CartaAlta()),new Sumador(15, 2, new Par()),new Sumador(20, 2, new DoblePar()),
                new CambiadorDeMultiplicador(4) ,new Sumador(20, 2, new Trio()), new CambiadorDePuntos(40),
                new Sumador(30, 3, new Escalera()), new CambiadorDeMultiplicador(1.5), new CambiadorDeMultiplicador(2),
                new Sumador(15,2, new Color()), new Sumador(25,2, new FullHouse()), new Sumador(30 ,3,new Poker()),
                new Sumador(40, 4, new EscaleraDeColor()), new Sumador(50,5, new EscaleraReal()), new CambiadorDePuntos(50)
        ));

        if(tarots.size() != tarotsQueDeberianEstar.size()){
            resultadoComparacion = false;
        }
        for (int i = 0; i < tarots.size(); i++) {
            Puntaje puntajebase = new Puntaje(10,10);
            Tarot tarot1 = tarots.get(i);
            Tarot tarot2 = tarotsQueDeberianEstar.get(i);
            if (!tarot1.obtenerPuntaje(puntajebase).tenesMismoPuntaje(tarot2.obtenerPuntaje(puntajebase))) {
                resultadoComparacion = false;
            }
        }
        assertTrue(resultadoComparacion);
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
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("json/comodines.json");
            if (inputStream == null) {
                throw new FileNotFoundException("Archivo comodines.json no encontrado en src/test/resources/json");
            }
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(inputStream));
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

        boolean resultadoComparacion = true;

        ArrayList<Modificador> modificadoresQueDeberianEstar = new ArrayList<>(List.of(
                new SumaMultiplicador(4), new SumaPuntos(100), new SumaPuntos(50, new Par()), new SumaPuntos(100, new Trio()),
                new SumaMultiplicador(12, new Escalera()), new SumaPuntos(100, new Trio()), new SumaPuntos(200, new Color()), new SumaMultiplicador(8, new FullHouse()),
                new SumaPuntos(300, new Poker()), new SumaMultiplicador(15, new EscaleraDeColor()), new SumaPuntos(500, new EscaleraReal()), new SumaMultiplicador(20, new EscaleraReal()),
                new SumaPuntosDescarte(30), new SumaMultiplicadorDescarte(15), new SumaPuntosDescarte(50), new SumaMultiplicadorDescarte(10),
                new SumaPuntosDescarte(75), new SumaMultiplicadorDescarte(20), new SumaPuntosDescarte(100), new SumaMultiplicadorDescarte(25),
                new SumaMultiplicador(15, new Aleatorio(6)), new SumaPuntos(1000, new Aleatorio(1000)), new SumaPuntos(500, new Aleatorio(20)), new SumaMultiplicador(10, new Aleatorio(50)),
                new SumaPuntos(2000, new Aleatorio(500)), new SumaMultiplicador(30, new Aleatorio(100)), new SumaPuntos(1500, new Aleatorio(250)), new SumaMultiplicador(50, new Aleatorio(500)),
                new CartaComodin(new ArrayList<>(List.of(new SumaMultiplicador(15, new Aleatorio(6)), new SumaPuntos(100, new Trio())))),
                new CartaComodin(new ArrayList<>(List.of(new SumaMultiplicador(15, new Aleatorio(6)), new SumaPuntos(1000, new Aleatorio(1000))))),
                new CartaComodin(new ArrayList<>(List.of(new SumaPuntos(500, new Color()), new SumaMultiplicador(10, new Aleatorio(50))))),
                new CartaComodin(new ArrayList<>(List.of(new SumaPuntos(2000, new Aleatorio(500)), new SumaPuntosDescarte(30)))),
                new CartaComodin(new ArrayList<>(List.of(new SumaPuntos(300, new Poker()), new SumaMultiplicador(20, new Aleatorio(500))))
                )));

        if(listaDeComodines.size() != modificadoresQueDeberianEstar.size()){
            resultadoComparacion = false;
        }
        for (int i = 0; i < listaDeComodines.size(); i++) {
            Puntaje puntajeModificador1= new Puntaje(10,10);
            Puntaje puntajeModificador2 = new Puntaje(10,10);
            Modificador modificador1 = listaDeComodines.get(i);
            Modificador modificador2 = modificadoresQueDeberianEstar.get(i);
            modificador1.aplicarModificador(puntajeModificador1, new SinJuego());
            modificador2.aplicarModificador(puntajeModificador2, new SinJuego());
            if (!puntajeModificador1.tenesMismoPuntaje(puntajeModificador2)) {
                resultadoComparacion = false;
            }
        }
        assertTrue(resultadoComparacion);

    }

    @Test
    public void test08p2LecturaDeComodinesJSONYConversionAlModeloDeDominio() {
        LectorComodines lectorDeComodines = new LectorComodines();
        ArrayList<Modificador> modificadores = lectorDeComodines.leerComodines();

//        boolean resultadoComparacion = true;
//
//        ArrayList<Modificador> modificadoresQueDeberianEstar = new ArrayList<>(List.of(
//            new SumaMultiplicador(4), new SumaPuntos(100), new SumaPuntos(50, new Par()), new SumaPuntos(100, new Trio()),
//            new SumaMultiplicador(12, new Escalera()), new SumaPuntos(100, new Trio()), new SumaPuntos(200, new Color()), new SumaMultiplicador(8, new FullHouse()),
//            new SumaPuntos(300, new Poker()), new SumaMultiplicador(15, new EscaleraDeColor()), new SumaPuntos(500, new EscaleraReal()), new SumaMultiplicador(20, new EscaleraReal()),
//            new SumaPuntosDescarte(30), new SumaMultiplicadorDescarte(15), new SumaPuntosDescarte(50), new SumaMultiplicadorDescarte(10),
//            new SumaPuntosDescarte(75), new SumaMultiplicadorDescarte(20), new SumaPuntosDescarte(100), new SumaMultiplicadorDescarte(25),
//            new SumaMultiplicador(15, new Aleatorio(6)), new SumaPuntos(1000, new Aleatorio(1000)), new SumaPuntos(500, new Aleatorio(20)), new SumaMultiplicador(10, new Aleatorio(50)),
//            new SumaPuntos(2000, new Aleatorio(500)), new SumaMultiplicador(30, new Aleatorio(100)), new SumaPuntos(1500, new Aleatorio(250)), new SumaMultiplicador(50, new Aleatorio(500)),
//            new CartaComodin(new ArrayList<>(List.of(new SumaMultiplicador(15, new Aleatorio(6)), new SumaPuntos(100, new Trio())))),
//            new CartaComodin(new ArrayList<>(List.of(new SumaMultiplicador(15, new Aleatorio(6)), new SumaPuntos(1000, new Aleatorio(1000))))),
//            new CartaComodin(new ArrayList<>(List.of(new SumaPuntos(500, new Color()), new SumaMultiplicador(10, new Aleatorio(50))))),
//            new CartaComodin(new ArrayList<>(List.of(new SumaPuntos(2000, new Aleatorio(500)), new SumaPuntosDescarte(30)))),
//            new CartaComodin(new ArrayList<>(List.of(new SumaPuntos(300, new Poker()), new SumaMultiplicador(20, new Aleatorio(500))))
//        )));
//
//        if(modificadores.size() != modificadoresQueDeberianEstar.size()){
//            resultadoComparacion = false;
//        }
//        for (int i = 0; i < modificadores.size(); i++) {
//            Puntaje puntajeModificador1= new Puntaje(10,10);
//            Puntaje puntajeModificador2 = new Puntaje(10,10);
//            Modificador modificador1 = modificadores.get(i);
//            Modificador modificador2 = modificadoresQueDeberianEstar.get(i);
//            modificador1.aplicarModificador(puntajeModificador1, new SinJuego());
//            modificador2.aplicarModificador(puntajeModificador2, new SinJuego());
//            if (!puntajeModificador1.tenesMismoPuntaje(puntajeModificador2)) {
//                resultadoComparacion = false;
//            }
//        }
//        assertTrue(resultadoComparacion);
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
