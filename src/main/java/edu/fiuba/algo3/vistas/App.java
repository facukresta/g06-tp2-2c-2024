package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.*;
import edu.fiuba.algo3.modelo.Comprable;
import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import edu.fiuba.algo3.modelo.tarot.CambiadorDePuntos;
import edu.fiuba.algo3.modelo.tarot.SinTarot;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.tarot.Tarotera;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;


import java.util.ArrayList;


public class App extends Application {

    private Stage StagePrincipal;

    private Mazo mazo = new Mazo();
    private Comodinera comodinera = new Comodinera();
    private Tarotera tarotera = new Tarotera();
    private ArrayList<Carta> seleccionadas = new ArrayList<>();
    private ArrayList<Ronda> rondas = new ArrayList<>();
    private int maxRondas;
    private Ronda rondaActual;
    private CreadorDeBotones creadorDeBotones = new CreadorDeBotones();
    private CreadorVisual creadorVisual = new CreadorVisual();
    private CreadorDeEtiqueta creadorDeEtiqueta = new CreadorDeEtiqueta();

    @Override
    public void start(Stage stage) {
        this.StagePrincipal = stage;
        Scene escenaMenu = crearEscenaDelMenu();
        StagePrincipal.setScene(escenaMenu);
        StagePrincipal.setTitle("Balatro G_06");
        StagePrincipal.show();
    }

    private Scene crearEscenaDelMenu() {
        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setBackground(creadorVisual.crearBackground("fondoMenus", contenedorPrincipal));

        VBox medio = new VBox();

        ImageView logo = creadorVisual.crearImageView("logoBalatro",contenedorPrincipal, 0.5, 0.3);

        Button playButton = creadorDeBotones.crearBoton("Jugar",
                (new IniciarJuegoEventHandler(this.rondas, this.mazo ,() -> iniciarJuego())), contenedorPrincipal, 0.2, 0.1);
        medio.getChildren().add(logo);
        medio.getChildren().add(playButton);
        medio.setAlignment(Pos.CENTER);

        HBox abajo = new HBox();

        Button exitButton = creadorDeBotones.crearBoton("Salir", (e -> {
            Platform.exit();
        }), contenedorPrincipal, 0.2, 0.1);
        abajo.getChildren().add(exitButton);
        abajo.setAlignment(Pos.BOTTOM_LEFT);
        HBox.setMargin(exitButton, new Insets(0,0,20,20));

        contenedorPrincipal.setCenter(medio);
        contenedorPrincipal.setBottom(abajo);

        return new Scene(contenedorPrincipal, 1280, 720);
    }

    private void actualizarTablero(HBox contenedorDeCartas, Pane contenedorPrincipal, HBox puntajeASuperarBox, HBox puntajesObtenidosBox, HBox jugadasRestantesBox, HBox descartesRestantesBox, HBox rondaActualBox, VBox comodines, VBox tarots) {
        this.actualizarCartas(contenedorDeCartas, contenedorPrincipal);
        this.actualizarEstadisticas(puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox);
        this.actualizarComodinera(contenedorPrincipal, comodines);
        this.actualizarTarotera(contenedorPrincipal, tarots);
    }

    private void actualizarCartas (HBox contenedorDeCartas, Pane contenedorPrincipal) {
        contenedorDeCartas.getChildren().clear();
        ArrayList<Carta> cartas = this.rondaActual.obtenerCartas();
        for (Carta carta : cartas) {
            Button cartaBoton = creadorDeBotones.crearBoton(carta.obtenerNombre(), (null), contenedorPrincipal, 0.08, 0.2);
            cartaBoton.setOnAction(new CartaApretarEventHandler(this.seleccionadas, carta, cartaBoton));

            contenedorDeCartas.getChildren().add(cartaBoton);
            HBox.setMargin(cartaBoton, new Insets(0,0,30,0));
        }
    }

    private void actualizarEstadisticas(HBox puntajeASuperarBox, HBox puntajesObtenidosBox, HBox jugadasRestantesBox, HBox descartesRestantesBox, HBox rondaActualBox) {
        Label puntajeASuperarMostrado = creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(this.rondaActual.obtenerPuntajeASuperar()), "-fx-font-size: 24px; -fx-text-fill: white;");
        puntajeASuperarBox.getChildren().set(1, puntajeASuperarMostrado);
        HBox.setMargin(puntajeASuperarMostrado, new Insets(5,5,0,0));

        Label puntajesObtenidosMostrados = creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(this.rondaActual.obtenerPuntajesObtenidos()), "-fx-font-size: 24px; -fx-text-fill: white;");
        puntajesObtenidosBox.getChildren().set(1, puntajesObtenidosMostrados);
        HBox.setMargin(puntajesObtenidosMostrados, new Insets(5,5,0,0));

        Label jugadasRestantesMostrados = creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(this.rondaActual.obtenerJugadasRestantes()), "-fx-font-size: 24px; -fx-text-fill: white;");
        jugadasRestantesBox.getChildren().set(1, jugadasRestantesMostrados);
        HBox.setMargin(jugadasRestantesMostrados, new Insets(5,5,0,0));

        Label descartesRestantesMostrados = creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(this.rondaActual.obtenerDescartesRestantes()), "-fx-font-size: 24px; -fx-text-fill: white;");
        descartesRestantesBox.getChildren().set(1, descartesRestantesMostrados);
        HBox.setMargin(descartesRestantesMostrados, new Insets(5,5,0,0));

        Label rondaActualMostrada = creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(9 - this.rondas.size()), "-fx-font-size: 24px; -fx-text-fill: white;");
        rondaActualBox.getChildren().set(1, rondaActualMostrada);
        HBox.setMargin(rondaActualMostrada, new Insets(5,5,0,0));

    }

    private void actualizarTarotera(Pane contenedorPrincipal, VBox tarots) {
        tarots.getChildren().clear();
        for (Tarot tarot : this.tarotera.obtenerTarots()) {
            Button tarotBoton = creadorDeBotones.crearBoton(tarot.obtenerNombre(), (null), contenedorPrincipal, 0.05, 0.13);
            tarotBoton.setOnAction(new TarotApretarEventHandler(this.seleccionadas, tarot, tarotBoton, tarots, contenedorPrincipal, this.tarotera));
            tarots.getChildren().add(tarotBoton);
            VBox.setMargin(tarotBoton, new Insets(10,0,0,20));
        }
    }


    private void actualizarComodinera(Pane contenedorPrincipal, VBox comodines) {
        comodines.getChildren().clear();
        CambiadorDeComodines cambiadorDeComodines = new CambiadorDeComodines(this.comodinera, comodines, contenedorPrincipal);
        for (Modificador comodin : this.comodinera.obtenerComodines()) {
            Button comodinBoton = creadorDeBotones.crearBoton(comodin.obtenerNombre(), (null), contenedorPrincipal, 0.05, 0.13);
            comodinBoton.setOnAction(new ComodinApretarEventHandler(comodin, comodinBoton, cambiadorDeComodines));
            comodines.getChildren().add(comodinBoton);
            VBox.setMargin(comodinBoton, new Insets(10,0,0,20));
        }
    }

    private Scene crearEscenaDeJuego() {
        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setBackground(creadorVisual.crearBackground("fondo2", contenedorPrincipal));

        HBox puntajeASuperarBox = new HBox();
        HBox puntajesObtenidosBox = new HBox();
        HBox jugadasRestantesBox = new HBox();
        HBox descartesRestantesBox = new HBox();
        HBox rondaActualBox = new HBox();

        VBox comodines = new VBox();
        VBox tarots = new VBox();


        Button rendirseBoton = creadorDeBotones.crearBoton("rendirse", (e -> {
            Platform.exit();
        }), contenedorPrincipal, 0.1, 0.75);
        HBox contenedorDeCartas = new HBox();

        Button jugarManoBoton = creadorDeBotones.crearBoton("jugarMano",
                (new JugarManoEventHandler(this.seleccionadas, this.comodinera, this.rondas,
                        () -> pasarDeRonda(contenedorDeCartas, contenedorPrincipal, puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox, comodines, tarots),
                        () -> actualizarTablero(contenedorDeCartas, contenedorPrincipal, puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox, comodines, tarots),
                        () -> cambiarDeEscena(crearEscenaDePerdiste()))), contenedorPrincipal, 0.2, 0.1);

        Button descartarManoBoton = creadorDeBotones.crearBoton("descartar", (
                new DescartarManoEventHandler(this.seleccionadas, this.comodinera,  this.rondas, this.rondaActual.obtenerDescartesRestantes(),
                        () -> pasarDeRonda(contenedorDeCartas, contenedorPrincipal, puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox, comodines, tarots),
                        () -> actualizarTablero(contenedorDeCartas, contenedorPrincipal, puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox, comodines, tarots))
        ), contenedorPrincipal, 0.2, 0.1);

        ArrayList<Carta> cartas = this.rondaActual.obtenerCartas();
        for (Carta carta : cartas) {
            Button cartaBoton = creadorDeBotones.crearBoton(carta.obtenerNombre(), (null), contenedorPrincipal, 0.08, 0.2);
            cartaBoton.setOnAction(new CartaApretarEventHandler(this.seleccionadas, carta, cartaBoton));

            contenedorDeCartas.getChildren().add(cartaBoton);
            HBox.setMargin(cartaBoton, new Insets(0,0,30,0));
        }
        contenedorDeCartas.setAlignment(Pos.BOTTOM_CENTER);
        contenedorDeCartas.setSpacing(20);

        VBox comodinesLetrero = new VBox();

        ImageView comodinLogo = creadorVisual.crearImageView("comodines",contenedorPrincipal, 0.07, 0.04);

        VBox.setMargin(comodinLogo, new Insets(10,0,0,10));
        comodinesLetrero.getChildren().add(comodinLogo);

        CambiadorDeComodines cambiadorDeComodines = new CambiadorDeComodines(this.comodinera, comodines, contenedorPrincipal);
        ArrayList<Modificador> modificadores = this.comodinera.obtenerComodines();
        for (Modificador comodin : modificadores) {
            Button comodinBoton = creadorDeBotones.crearBoton(comodin.obtenerNombre(), (null), contenedorPrincipal, 0.05, 0.13);
            comodinBoton.setOnAction(new ComodinApretarEventHandler(comodin, comodinBoton, cambiadorDeComodines));
            comodines.getChildren().add(comodinBoton);
            VBox.setMargin(comodinBoton, new Insets(10,0,0,20));
        }

        VBox comodinesYLetrero = new VBox(comodinesLetrero, comodines);

        comodinesYLetrero.setAlignment(Pos.TOP_LEFT);
        comodinesYLetrero.setSpacing(10);

        VBox tarotsLetrero = new VBox();

        ImageView tarotLogo = creadorVisual.crearImageView("tarots",contenedorPrincipal, 0.065, 0.035);

        VBox.setMargin(tarotLogo, new Insets(10,0,0,10));
        tarotsLetrero.getChildren().add(tarotLogo);


        for (Tarot tarot : this.tarotera.obtenerTarots()) {
            Button tarotBoton = creadorDeBotones.crearBoton(tarot.obtenerNombre(), (null), contenedorPrincipal, 0.05, 0.13);
            tarotBoton.setOnAction(new TarotApretarEventHandler(this.seleccionadas, tarot, tarotBoton, tarots, contenedorPrincipal, this.tarotera));
            tarots.getChildren().add(tarotBoton);
            VBox.setMargin(tarotBoton, new Insets(10,0,0,20));
        }

        VBox tarotsYLetrero = new VBox(tarotsLetrero, tarots);

        tarotsYLetrero.setAlignment(Pos.TOP_LEFT);
        tarotsYLetrero.setSpacing(10);

        HBox comodinesYTarots = new HBox(comodinesYLetrero, tarotsYLetrero);


        HBox rendirse = new HBox();
        rendirse.getChildren().add(rendirseBoton);
        rendirse.setAlignment(Pos.BOTTOM_LEFT);
        HBox.setMargin(rendirseBoton, new Insets(0,0,25,20));

        HBox juegoYDescartar = new HBox();
        juegoYDescartar.getChildren().addAll(descartarManoBoton, jugarManoBoton);
        juegoYDescartar.setAlignment(Pos.BOTTOM_CENTER);
        juegoYDescartar.setSpacing(40);
        HBox.setMargin(jugarManoBoton, new Insets(0,0,20,0));
        HBox.setMargin(descartarManoBoton, new Insets(0,0,20,0));


        HBox layout = new HBox(rendirse, juegoYDescartar);
        HBox.setHgrow(rendirse, Priority.SOMETIMES);
        HBox.setHgrow(juegoYDescartar, Priority.ALWAYS);



        ImageView stringPuntajeASuperar = creadorVisual.crearImageView("puntajeASuperar",contenedorPrincipal, 0.08, 0.04);
        Label puntajeASuperarMostrado = creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(this.rondaActual.obtenerPuntajeASuperar()), "-fx-font-size: 24px; -fx-text-fill: white;");

        puntajeASuperarBox.setSpacing(5);
        puntajeASuperarBox.getChildren().addAll(stringPuntajeASuperar, puntajeASuperarMostrado);
        HBox.setMargin(stringPuntajeASuperar, new Insets(5,5,0,0));
        HBox.setMargin(puntajeASuperarMostrado, new Insets(5,5,0,0));


        ImageView stringpuntajeObtenidos = creadorVisual.crearImageView("puntajeObtenido",contenedorPrincipal, 0.08, 0.04);
        Label puntajesObtenidosMostrados = creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(this.rondaActual.obtenerPuntajesObtenidos()), "-fx-font-size: 24px; -fx-text-fill: white;");

        puntajesObtenidosBox.setSpacing(5);
        puntajesObtenidosBox.getChildren().addAll(stringpuntajeObtenidos, puntajesObtenidosMostrados);
        HBox.setMargin(stringpuntajeObtenidos, new Insets(5,5,0,0));
        HBox.setMargin(puntajesObtenidosMostrados, new Insets(5,5,0,0));


        ImageView stringJugadasRestantes = creadorVisual.crearImageView("jugadasRestantes",contenedorPrincipal, 0.08, 0.04);
        Label jugadasRestantesMostrados = creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(this.rondaActual.obtenerJugadasRestantes()), "-fx-font-size: 24px; -fx-text-fill: white;");

        jugadasRestantesBox.getChildren().addAll(stringJugadasRestantes, jugadasRestantesMostrados);
        HBox.setMargin(stringJugadasRestantes, new Insets(5,5,0,0));
        HBox.setMargin(jugadasRestantesMostrados, new Insets(5,5,0,0));
        jugadasRestantesBox.setSpacing(5);


        ImageView stringDescartesRestantes = creadorVisual.crearImageView("descartesRestantes",contenedorPrincipal, 0.08, 0.04);
        Label descartesRestantesMostrados = creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(this.rondaActual.obtenerDescartesRestantes()), "-fx-font-size: 24px; -fx-text-fill: white;");

        descartesRestantesBox.getChildren().addAll(stringDescartesRestantes, descartesRestantesMostrados);
        HBox.setMargin(stringDescartesRestantes, new Insets(5,5,0,0));
        HBox.setMargin(descartesRestantesMostrados, new Insets(5,5,0,0));
        descartesRestantesBox.setSpacing(5);


        ImageView stringRondaActual = creadorVisual.crearImageView("rondaActual",contenedorPrincipal, 0.08, 0.04);
        Label rondaActualMostrada = creadorDeEtiqueta.crearEtiquetaConEstilo(String.valueOf(9 - this.rondas.size()), "-fx-font-size: 24px; -fx-text-fill: white;");


        rondaActualBox.getChildren().addAll(stringRondaActual, rondaActualMostrada);
        HBox.setMargin(stringRondaActual, new Insets(5,5,0,0));
        HBox.setMargin(rondaActualMostrada, new Insets(5,5,0,0));
        rondaActualBox.setSpacing(5);



        VBox datosRonda = new VBox(puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox);
        datosRonda.setSpacing(20);

        contenedorPrincipal.setBottom(layout);
        contenedorPrincipal.setCenter(contenedorDeCartas);
        contenedorPrincipal.setLeft(comodinesYTarots);
        contenedorPrincipal.setRight(datosRonda);
        this.crearTienda(contenedorPrincipal, contenedorDeCartas, contenedorPrincipal, puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox, comodines, tarots);
        return new Scene(contenedorPrincipal, 1280, 720);
    }

    private void iniciarJuego() {
        this.maxRondas = this.rondas.size();
        this.comodinera = new Comodinera();
        this.tarotera = new Tarotera();
        this.rondaActual = this.rondas.get(0);
        this.mazo.mezclar();
        cambiarDeEscena(crearEscenaDeJuego());
//        this.crearTienda(contenedorPrincipal, contenedorDeCartas, contenedorPrincipal, puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox, comodines);
    }

    private void pasarDeRonda(HBox contenedorDeCartas, Pane contenedorPrincipal, HBox puntajeASuperarBox, HBox puntajesObtenidosBox, HBox jugadasRestantesBox, HBox descartesRestantesBox, HBox rondaActualBox, VBox comodines, VBox tarots) {
        if (rondas.isEmpty()) {
            cambiarDeEscena(crearEscenaDeGanaste());
            return;
        }
        this.seleccionadas.clear();
        this.rondas.remove(0);
        this.rondaActual = this.rondas.get(0);
        this.crearTienda(contenedorPrincipal, contenedorDeCartas, contenedorPrincipal, puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox, comodines, tarots);
        this.mazo.mezclar();
        actualizarTablero(contenedorDeCartas, contenedorPrincipal, puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox, comodines, tarots);
    }

    private Scene crearEscenaDeGanaste() {
        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setBackground(creadorVisual.crearBackground("ganasteFodno", contenedorPrincipal));

        VBox medio = new VBox();

        ImageView logo = creadorVisual.crearImageView("ganasteNombre",contenedorPrincipal, 0.5, 0.3);

        Button playButton = creadorDeBotones.crearBoton("jugar2", (new IniciarJuegoEventHandler(this.rondas, this.mazo ,() -> iniciarJuego())), contenedorPrincipal, 0.2, 0.1);
        medio.getChildren().add(logo);
        medio.getChildren().add(playButton);
        medio.setAlignment(Pos.CENTER);

        HBox abajo = new HBox();

        Button exitButton = creadorDeBotones.crearBoton("salir2", (e -> {
            Platform.exit();
        }), contenedorPrincipal, 0.2, 0.1);
        abajo.getChildren().add(exitButton);
        abajo.setAlignment(Pos.BOTTOM_LEFT);
        HBox.setMargin(exitButton, new Insets(0,0,20,20));


        contenedorPrincipal.setCenter(medio);
        contenedorPrincipal.setBottom(abajo);

        return new Scene(contenedorPrincipal, 1280, 720);
    }

    private Scene crearEscenaDePerdiste() {
        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setBackground(creadorVisual.crearBackground("perdiste", contenedorPrincipal));

        VBox medio = new VBox();


        ImageView logo = creadorVisual.crearImageView("perdisteNombre",contenedorPrincipal, 0.5, 0.3);

        Button playButton = creadorDeBotones.crearBoton("jugar2",
                (new IniciarJuegoEventHandler(this.rondas, this.mazo ,() -> iniciarJuego())), contenedorPrincipal, 0.2, 0.1);
        medio.getChildren().add(logo);
        medio.getChildren().add(playButton);
        medio.setAlignment(Pos.CENTER);

        HBox abajo = new HBox();

        Button exitButton = creadorDeBotones.crearBoton("salir2", (e -> {
            Platform.exit();
        }), contenedorPrincipal, 0.2, 0.1);
        abajo.getChildren().add(exitButton);
        abajo.setAlignment(Pos.BOTTOM_LEFT);
        HBox.setMargin(exitButton, new Insets(0,0,20,20));


        contenedorPrincipal.setCenter(medio);
        contenedorPrincipal.setBottom(abajo);

        return new Scene(contenedorPrincipal, 1280, 720);
    }

    private void salirTienda(VBox tienda, HBox contenedorDeCartas, Pane contenedorPrincipal, HBox puntajeASuperarBox, HBox puntajesObtenidosBox, HBox jugadasRestantesBox, HBox descartesRestantesBox, HBox rondaActualBox, VBox comodines, VBox tarots) {
        tienda.getChildren().clear();
        tienda.setStyle("");
        actualizarTablero(contenedorDeCartas, contenedorPrincipal, puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox, comodines, tarots);
    }

    private void crearTienda(Pane contenedorPricipal, HBox contenedorDeCartas, Pane contenedorPrincipal, HBox puntajeASuperarBox, HBox puntajesObtenidosBox, HBox jugadasRestantesBox, HBox descartesRestantesBox, HBox rondaActualBox, VBox comodines, VBox tarots) {
        VBox tienda = new VBox();
        tienda.setBackground(creadorVisual.crearBackground("fondoMenus", tienda));


        ArrayList<Comprable> producto = new ArrayList<>();
        ArrayList<Button> boton = new ArrayList<>();

        HBox botonesTienda = new HBox();
        Button cerrarTienda = this.creadorDeBotones.crearBoton("Salir", (e -> {
            this.salirTienda(tienda, contenedorDeCartas, contenedorPrincipal, puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox, comodines, tarots);
        }), contenedorPricipal, 0.5, 0.2);
        Button comprarTienda = this.creadorDeBotones.crearBoton("Jugar",
                new TiendaComprarEventHandler(this.comodinera, this.tarotera, this.mazo, producto, () -> salirTienda(tienda, contenedorDeCartas, contenedorPrincipal, puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox, comodines, tarots)), contenedorPricipal, 0.5, 0.2);

        botonesTienda.getChildren().addAll(cerrarTienda, comprarTienda);

        HBox comprables = new HBox();
        ArrayList<Comprable> productos = this.rondaActual.mostrarProductos();
        for (Comprable productoAComprar : productos) {
            Button comprable = this.creadorDeBotones.crearBoton(productoAComprar.obtenerNombre(),
                    null, contenedorPricipal, 0.5, 0.2);
            comprables.getChildren().add(comprable);
            comprable.setOnAction(new ComprableApretarEventHandler(productoAComprar, producto, boton,comprable));
        }
        tienda.getChildren().addAll(botonesTienda, comprables);
        contenedorPricipal.getChildren().add(tienda);
    }

    private void cambiarDeEscena(Scene newScene) {
        StagePrincipal.setScene(newScene);
    }

    public static void main(String[] args) {
        launch();
    }
}