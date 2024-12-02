package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.*;
import edu.fiuba.algo3.modelo.Comprable;
import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.MazoBalatro;
import edu.fiuba.algo3.modelo.naipes.Seleccionadas;
import edu.fiuba.algo3.modelo.naipes.SeleccionadasBalatro;
import edu.fiuba.algo3.modelo.ronda.Ronda;
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

import javafx.scene.media.*;


import java.nio.file.Paths;
import java.util.ArrayList;

public class App extends Application {

    private Stage StagePrincipal;

    private Mazo mazo = new MazoBalatro();
    private Comodinera comodinera = new Comodinera();
    private Tarotera tarotera = new Tarotera();
    private Seleccionadas seleccionadas = new SeleccionadasBalatro();
    private ArrayList<Ronda> rondas = new ArrayList<>();
    private Ronda rondaActual;
    private CreadorDeBotones creadorDeBotones = new CreadorDeBotones();
    private CreadorVisual creadorVisual = new CreadorVisual();

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
        Media media = new Media(getClass().getResource("/musica/musicaMenu.mp3").toExternalForm());
        MediaPlayer musicaMenu = new MediaPlayer(media);
        musicaMenu.setVolume(0.1);
        musicaMenu.setCycleCount(MediaPlayer.INDEFINITE);
        musicaMenu.play();
        contenedorPrincipal.setBackground(creadorVisual.crearBackground("fondoMenus"));



        VBox medio = new VBox();

        ImageView logo = creadorVisual.crearImageView("logoBalatro",contenedorPrincipal, 0.5, 0.3);

        Button playButton = creadorDeBotones.crearBoton("Jugar", (new IniciarJuegoEventHandler(this.rondas, this.mazo ,() -> iniciarJuego(), () -> musicaMenu.stop())), contenedorPrincipal, 0.2, 0.1);
        medio.getChildren().addAll(logo, playButton);
        medio.setAlignment(Pos.CENTER);

        HBox botonesInferiores = new HBox();

        HBox sectorMusica = new HBox();
        Button botonDeMusica = creadorDeBotones.crearBoton("conVolumen", null, contenedorPrincipal, 0.05,0.05);
        botonDeMusica.setOnAction(new CambiarVolumenSonidoEventHandler(musicaMenu, contenedorPrincipal, botonDeMusica, 0.1));
        sectorMusica.setAlignment(Pos.BOTTOM_RIGHT);
        sectorMusica.getChildren().add(botonDeMusica);
        HBox.setMargin(botonDeMusica, new Insets(0,20,20,0));

        HBox sectorRendirse = new HBox();
        Button exitButton = creadorDeBotones.crearBoton("Salir", (e -> {Platform.exit();}), contenedorPrincipal, 0.2, 0.1);
        sectorRendirse.getChildren().add(exitButton);
        sectorRendirse.setAlignment(Pos.BOTTOM_LEFT);
        HBox.setMargin(exitButton, new Insets(0,0,20,20));

        botonesInferiores.getChildren().addAll(sectorRendirse, sectorMusica);

        HBox.setHgrow(sectorRendirse, Priority.SOMETIMES);
        HBox.setHgrow(sectorMusica, Priority.ALWAYS);

        contenedorPrincipal.setCenter(medio);
        contenedorPrincipal.setBottom(botonesInferiores);

        return new Scene(contenedorPrincipal, 1280, 720);
    }

    private void crearEscenaDeJuego() {
        BorderPane contenedorPrincipal = new BorderPane();

        Media media = new Media(getClass().getResource("/musica/musicaDeFondo.mp3").toExternalForm());
        MediaPlayer musicaJuego = new MediaPlayer(media);
        musicaJuego.setCycleCount(MediaPlayer.INDEFINITE);
        musicaJuego.setVolume(0.05);
        musicaJuego.play();

        VBox datosRonda = new VBox();

        Button botonDeMusica = creadorDeBotones.crearBoton("conVolumen", null, contenedorPrincipal, 0.05,0.05);
        botonDeMusica.setOnAction(new CambiarVolumenSonidoEventHandler(musicaJuego, contenedorPrincipal, botonDeMusica, 0.05));

        Scene escenaPrincipal = new Scene(contenedorPrincipal, 1280, 720);
        contenedorPrincipal.setBackground(creadorVisual.crearBackground("fondo2"));

        HBox puntajeASuperarBox = new HBox();
        HBox puntajesObtenidosBox = new HBox();
        HBox jugadasRestantesBox = new HBox();
        HBox descartesRestantesBox = new HBox();
        HBox rondaActualBox = new HBox();

        HBox contenedorDeCartas = new HBox();
        contenedorDeCartas.setAlignment(Pos.BOTTOM_CENTER);
        contenedorDeCartas.setSpacing(20);

        VBox comodines = new VBox();
        VBox tarots = new VBox();

        this.comodinera.agregarObservador(new ComodinesEnMano(comodines, contenedorPrincipal));
        this.tarotera.agregarObservador(new TarotsEnMano(tarots, this.seleccionadas, contenedorPrincipal));
        for (Ronda ronda: this.rondas) {
            ronda.agregarObservador(new PuntajeASuperar(puntajeASuperarBox, contenedorPrincipal));
            ronda.agregarObservador(new PuntajeObtenido(puntajesObtenidosBox, contenedorPrincipal));
            ronda.agregarObservador(new JugadasRestantes(jugadasRestantesBox, contenedorPrincipal));
            ronda.agregarObservador(new DescartesRestantes(descartesRestantesBox, contenedorPrincipal));
            ronda.agregarObservador(new RondaActual(rondaActualBox, contenedorPrincipal));
            ronda.agregarObservador(new CartasEnMano(contenedorDeCartas, this.seleccionadas, contenedorPrincipal));
        }
        rondaActual.notificarObservadores();

        Button rendirseBoton = creadorDeBotones.crearBoton("rendirse", (e -> {
            Platform.exit();
        }), contenedorPrincipal, 0.1, 0.75);

        Button jugarManoBoton = creadorDeBotones.crearBoton("jugarMano",
                (new JugarManoEventHandler(this.seleccionadas, this.comodinera, this.rondas,
                        () -> pasarDeRonda(escenaPrincipal, () -> musicaJuego.stop()),
                        () -> cambiarDeEscena(crearEscenaDePerdiste(() -> musicaJuego.stop())))), contenedorPrincipal, 0.2, 0.1);

        Button descartarManoBoton = creadorDeBotones.crearBoton("descartar", (
                new DescartarManoEventHandler(this.seleccionadas, this.comodinera,  this.rondas,
                        () -> pasarDeRonda(escenaPrincipal, () -> musicaJuego.stop()))), contenedorPrincipal, 0.2, 0.1);

        VBox comodinesLetrero = new VBox();
        ImageView comodinLogo = creadorVisual.crearImageView("comodines",contenedorPrincipal, 0.07, 0.04);
        VBox.setMargin(comodinLogo, new Insets(10,0,0,10));
        comodinesLetrero.getChildren().add(comodinLogo);

        VBox comodinesYLetrero = new VBox(comodinesLetrero, comodines);

        comodinesYLetrero.setAlignment(Pos.TOP_LEFT);
        comodinesYLetrero.setSpacing(10);

        VBox tarotsLetrero = new VBox();

        ImageView tarotLogo = creadorVisual.crearImageView("tarots",contenedorPrincipal, 0.065, 0.035);

        VBox.setMargin(tarotLogo, new Insets(10,0,0,10));
        tarotsLetrero.getChildren().add(tarotLogo);

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

        HBox botonesInferio = new HBox(rendirse, juegoYDescartar);
        HBox.setHgrow(rendirse, Priority.SOMETIMES);
        HBox.setHgrow(juegoYDescartar, Priority.ALWAYS);


        HBox botonSonido = new HBox(botonDeMusica);
        datosRonda.getChildren().addAll(puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox, botonSonido);
        datosRonda.setSpacing(20);

        contenedorPrincipal.setBottom(botonesInferio);
        contenedorPrincipal.setCenter(contenedorDeCartas);
        contenedorPrincipal.setLeft(comodinesYTarots);
        contenedorPrincipal.setRight(datosRonda);
        escenaPrincipal.setRoot(contenedorPrincipal);
        this.crearTienda(escenaPrincipal);
    }

    private void iniciarJuego() {
        this.comodinera = new Comodinera();
        this.tarotera = new Tarotera();
        this.rondaActual = this.rondas.get(0);
        this.mazo.mezclar();
        this.rondaActual.iniciarRonda();
        this.crearEscenaDeJuego();
    }

    private void pasarDeRonda(Scene contenedorPrincipal,Runnable pararMusica) {
        this.rondas.remove(0);
        if (rondas.isEmpty()) {
            cambiarDeEscena(crearEscenaDeGanaste(pararMusica));
            return;
        }
        this.seleccionadas.vaciarCartas();
        this.rondaActual = this.rondas.get(0);
        (new AudioClip(getClass().getResource("/sonido/pasarRonda.mp3").toExternalForm())).play();
        this.crearTienda(contenedorPrincipal);
        this.mazo.mezclar();
        this.rondaActual.iniciarRonda();
    }

    private Scene crearEscenaDeGanaste(Runnable pararMusica) {
        pararMusica.run();

        Media media = new Media(getClass().getResource("/musica/ganaste.mp3").toExternalForm());
        MediaPlayer musicaGanaste = new MediaPlayer(media);
        musicaGanaste.setVolume(0.3);
        musicaGanaste.play();

        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setBackground(creadorVisual.crearBackground("ganasteFondo"));

        VBox medio = new VBox();

        ImageView logo = creadorVisual.crearImageView("ganasteNombre",contenedorPrincipal, 0.5, 0.3);

        Button playButton = creadorDeBotones.crearBoton("jugar2", (new IniciarJuegoEventHandler(this.rondas, this.mazo ,() -> iniciarJuego(), () -> musicaGanaste.stop())), contenedorPrincipal, 0.2, 0.1);
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

    private Scene crearEscenaDePerdiste(Runnable musicaJuego) {
        musicaJuego.run();
        Media media = new Media(getClass().getResource("/sonido/perdiste.mp3").toExternalForm());
        MediaPlayer musicaPerdiste = new MediaPlayer(media);
        musicaPerdiste.setVolume(0.3);
        musicaPerdiste.play();

        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setBackground(creadorVisual.crearBackground("perdiste"));

        VBox medio = new VBox();

        ImageView logo = creadorVisual.crearImageView("perdisteNombre",contenedorPrincipal, 0.5, 0.3);

        Button playButton = creadorDeBotones.crearBoton("jugar2",
                (new IniciarJuegoEventHandler(this.rondas, this.mazo ,() -> iniciarJuego(), () -> musicaPerdiste.stop())), contenedorPrincipal, 0.2, 0.1);
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

    private void salirTienda(Scene layoutAnterior) {
        this.cambiarDeEscena(layoutAnterior);
    }

    private void cambiarDeEscena(Scene newScene) {
        StagePrincipal.setScene(newScene);
    }

    private void crearTienda(Scene layoutAnterior) {
        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setBackground(creadorVisual.crearBackground("tienda/fondoTienda"));

        VBox tienda = new VBox();

        ArrayList<Comprable> producto = new ArrayList<>();
        ArrayList<Button> boton = new ArrayList<>();

        HBox botonesTienda = new HBox();
        Button cerrarTienda = this.creadorDeBotones.crearBoton("tienda/botonSalirTienda", (e -> {
            this.salirTienda(layoutAnterior);
        }), contenedorPrincipal, 0.37, 0.15);
        Button comprarTienda = this.creadorDeBotones.crearBoton("tienda/botonComprar",
                new TiendaComprarEventHandler(this.comodinera, this.tarotera, this.mazo, producto,
                        () -> salirTienda(layoutAnterior)), contenedorPrincipal, 0.37, 0.15);
        botonesTienda.setSpacing(50);
        botonesTienda.setAlignment(Pos.CENTER);
        botonesTienda.getChildren().addAll(cerrarTienda, comprarTienda);

        HBox comprables = new HBox();
        ArrayList<Comprable> productos = this.rondaActual.mostrarProductos();
        for (Comprable productoAComprar : productos) {
            Button comprable = this.creadorDeBotones.crearBoton(productoAComprar.obtenerRutaBase()+productoAComprar.obtenerNombre(),
                    null, contenedorPrincipal, 0.612, 0.28);
            comprables.getChildren().add(comprable);
            comprable.setOnAction(new ComprableApretarEventHandler(productoAComprar, producto, boton,comprable));
            HBox.setMargin(comprable, new Insets(20,0,20,20));
        }
        comprables.setSpacing(20);
        comprables.setAlignment(Pos.CENTER);
        tienda.getChildren().addAll(botonesTienda, comprables);
        tienda.setAlignment(Pos.CENTER);

        contenedorPrincipal.setCenter(tienda);

        this.cambiarDeEscena(new Scene(contenedorPrincipal, 1280, 720));
    }

    public static void main(String[] args) {
        launch();
    }
}