package edu.fiuba.algo3.test_de_aceptacion;

import edu.fiuba.algo3.modelo.Comprable;
import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.comodin.SumaPuntos;
import edu.fiuba.algo3.modelo.comodin.SumaPuntosDescarte;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Par;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.Seleccionadas;
import edu.fiuba.algo3.modelo.naipes.carta.*;
import edu.fiuba.algo3.modelo.puntaje.Puntaje;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import edu.fiuba.algo3.modelo.tienda.Tienda;
import edu.fiuba.algo3.modelo.tienda.TiendaBalatro;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class test_de_aceptacion_01 {

    @Test
    public void test01(){
        Mazo mazo = new Mazo();
        Seleccionadas seleccionadas = new Seleccionadas();
        Carta carta1 = new CartaInglesa(2, new Corazon());
        Carta carta2 = new CartaInglesa(2, new Pica());

        ArrayList<Carta> cartasSeleccionadas = new ArrayList<>(List.of(carta1, carta2));
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Trebol()),
                new CartaInglesa(9, new Corazon()), new CartaInglesa(5, new Trebol()), new CartaInglesa(10, new Pica()),
                new CartaInglesa(13, new Diamante()), new CartaInglesa(12, new Diamante())));
        mazo.agregarCartas(cartas);
        mazo.agregarCartas(cartasSeleccionadas);

        Tienda tienda = new TiendaBalatro(new ArrayList<Comprable>());
        Ronda ronda = new Ronda(new Puntaje(1000,1), 3, 3, mazo,  tienda);

        ronda.iniciarRonda();
        mazo.agregarCartas(new ArrayList<>(List.of(mock(Carta.class), mock(Carta.class))));

        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(new SumaPuntos(100, new Par(), ""));
        comodinera.agregarComodin(new SumaPuntosDescarte(50, "" ));


        assertEquals(ronda.obtenerPuntajesObtenidos(), 0);

        seleccionadas.seleccionarCarta(carta1);
        seleccionadas.seleccionarCarta(carta2);

        ronda.jugarMano(seleccionadas, comodinera);

        assertEquals(ronda.obtenerPuntajesObtenidos(), 228);
    }


    @Test
    public void test02(){

        Mazo mazo = new Mazo();

        Seleccionadas seleccionadas = new Seleccionadas();
        Carta carta1 = new CartaInglesa(2, new Corazon());

        ArrayList<Carta> cartasSeleccionadas = new ArrayList<>(List.of(carta1));
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Trebol()),
                new CartaInglesa(9, new Corazon()), new CartaInglesa(5, new Trebol()), new CartaInglesa(10, new Pica()),
                new CartaInglesa(13, new Diamante()), new CartaInglesa(12, new Diamante()), new CartaInglesa(2, new Pica())));
        mazo.agregarCartas(cartas);
        mazo.agregarCartas(cartasSeleccionadas);

        Tienda tienda = new TiendaBalatro(new ArrayList<Comprable>());
        Ronda ronda = new Ronda(new Puntaje(1000,1), 3, 3, mazo,  tienda);

        ronda.iniciarRonda();
        mazo.agregarCartas(new ArrayList<>(List.of(mock(Carta.class), mock(Carta.class))));

        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(new SumaPuntos(100, new Par(), ""));
        comodinera.agregarComodin(new SumaPuntosDescarte(50, "" ));

        assertEquals(ronda.obtenerPuntajesObtenidos(), 0);
        seleccionadas.seleccionarCarta(carta1);
        ronda.jugarMano(seleccionadas, comodinera);

        assertEquals(ronda.obtenerPuntajesObtenidos(), 7);
    }

    @Test
    public void test03(){

        Mazo mazo = new Mazo();
        Seleccionadas seleccionadas = new Seleccionadas();
        Carta carta1 = new CartaInglesa(2, new Corazon());

        ArrayList<Carta> cartasSeleccionadas = new ArrayList<>(List.of(carta1));
        ArrayList<Carta> cartas = new ArrayList<>(List.of(new CartaInglesa(4, new Trebol()),
                new CartaInglesa(9, new Corazon()), new CartaInglesa(5, new Trebol()), new CartaInglesa(10, new Pica()),
                new CartaInglesa(13, new Diamante()), new CartaInglesa(12, new Diamante()), new CartaInglesa(2, new Pica())));
        mazo.agregarCartas(cartas);
        mazo.agregarCartas(cartasSeleccionadas);

        Tienda tienda = new TiendaBalatro(new ArrayList<Comprable>());
        Ronda ronda = new Ronda(new Puntaje(1000,1), 3, 3, mazo,  tienda);

        ronda.iniciarRonda();
        mazo.agregarCartas(new ArrayList<>(List.of(mock(Carta.class), mock(Carta.class))));

        Comodinera comodinera = new Comodinera();
        comodinera.agregarComodin(new SumaPuntos(100, new Par(), ""));
        comodinera.agregarComodin(new SumaPuntosDescarte(50, "" ));

        assertEquals(ronda.obtenerPuntajesObtenidos(), 0);

        seleccionadas.seleccionarCarta(carta1);

        ronda.descartarMano(seleccionadas, comodinera);

        assertEquals(ronda.obtenerPuntajesObtenidos(), 50);
    }
}
