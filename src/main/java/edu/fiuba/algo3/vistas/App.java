package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CartaApretarEventHandler;
import edu.fiuba.algo3.controllers.DescartarManoEventHandler;
import edu.fiuba.algo3.controllers.IniciarJuegoEventHandler;
import edu.fiuba.algo3.controllers.JugarManoEventHandler;
import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.naipes.Mazo;
import edu.fiuba.algo3.modelo.naipes.carta.Carta;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.util.ArrayList;
import javafx.scene.media;

/*
        ///////////////////////
        BOTONES
        5 Comodines (arriba a la izquierda)
        2 Tarots (arriba a la izquierda a la derecha de los comodines)
        ///////////////////////
        1 Abrir Tienda -> 6 comprables
        LABELS
        1 Ronda Actual ( /de Total)
        1 Juego Actual
        ///////////////////////
         */
// Botones escalables


public class App extends Application {

    private Stage StagePrincipal;

    private Mazo mazo = new Mazo();
    private Comodinera comodinera = new Comodinera();
    private ArrayList<Carta> seleccionadas = new ArrayList<>();
    private ArrayList<Ronda> rondas = new ArrayList<>();
    private ArrayList<Tarot> tarots = new ArrayList<>();
    private int posRondaActual = 0;
    private int maxRondas;
    private Ronda rondaActual;

    @Override
    public void start(Stage stage) {
        this.StagePrincipal = stage;
        Scene escenaMenu = crearEscenaDelMenu();
        StagePrincipal.setScene(escenaMenu);
        StagePrincipal.setTitle("Balatro G_06");
        StagePrincipal.show();
    }

    private Button crearBoton(String nombre, Color color, EventHandler<ActionEvent> handler, Pane contenedorPrincipal, double ancho, double altura) {
        Button boton = new Button();
        Image playImage = new Image("/"+nombre+".png");
        ImageView imageView = new ImageView(playImage);
        // Escalar el ImageView
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(contenedorPrincipal.widthProperty().multiply(ancho));
        imageView.fitHeightProperty().bind(contenedorPrincipal.heightProperty().multiply(altura));

        boton.setGraphic(imageView);
        boton.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        boton.setOnAction(handler);
        boton.setOnMouseEntered(e -> {
            ScaleTransition hoverScale = new ScaleTransition(Duration.millis(200), boton);
            hoverScale.setToX(1.1);
            hoverScale.setToY(1.1);
            hoverScale.play();
        });
        boton.setOnMouseExited(e -> {
            ScaleTransition hoverScale = new ScaleTransition(Duration.millis(200), boton);
            hoverScale.setToX(1.0);
            hoverScale.setToY(1.0);
            hoverScale.play();
        });
        return boton;
    }

    private Scene crearEscenaDelMenu() {
        BorderPane contenedorPrincipal = new BorderPane();

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("/fondoMenus.png", true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        contenedorPrincipal.setBackground(new Background(backgroundImage));


        VBox medio = new VBox();

        ImageView logo = new ImageView(new Image("/logoBalatro.png"));
        logo.setPreserveRatio(true);
        logo.fitWidthProperty().bind(contenedorPrincipal.widthProperty().multiply(0.5));
        logo.fitHeightProperty().bind(contenedorPrincipal.heightProperty().multiply(0.3));

        Button playButton = this.crearBoton("Jugar", Color.DARKRED, (new IniciarJuegoEventHandler(this.rondas, this.mazo ,() -> iniciarJuego())), contenedorPrincipal, 0.2, 0.1);
        medio.getChildren().add(logo);
        medio.getChildren().add(playButton);
        medio.setAlignment(Pos.CENTER);

        HBox abajo = new HBox();

        Button exitButton = this.crearBoton("Salir", Color.DARKGREEN, (e -> {
            Platform.exit();
        }), contenedorPrincipal, 0.2, 0.1);
        abajo.getChildren().add(exitButton);
        abajo.setAlignment(Pos.BOTTOM_LEFT);
        HBox.setMargin(exitButton, new Insets(0,0,20,20));

        contenedorPrincipal.setCenter(medio);
        contenedorPrincipal.setBottom(abajo);

        return new Scene(contenedorPrincipal, 1280, 720);
    }

    private Scene crearEscenaDeJuego() {
        BorderPane contenedorPrincipal = new BorderPane();

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("/fondo2.png", true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        contenedorPrincipal.setBackground(new Background(backgroundImage));

        Button rendirseBoton = this.crearBoton("rendirse", Color.DARKRED, (e -> {
            Platform.exit();
        }), contenedorPrincipal, 0.1, 0.75);


        Button jugarManoBoton = this.crearBoton("jugarMano", Color.DARKRED,
                (new JugarManoEventHandler(this.seleccionadas, this.comodinera, this.rondaActual,
                        () -> pasarDeRonda(),
                        () -> cambiarDeEscena(crearEscenaDeJuego()),
                        () -> cambiarDeEscena(crearEscenaDePerdiste()))), contenedorPrincipal, 0.2, 0.1);

        Button descartarManoBoton = this.crearBoton("descartar", Color.DARKRED, (
                new DescartarManoEventHandler(this.seleccionadas, this.comodinera, this.rondaActual, this.rondaActual.obtenerDescartesRestantes(),
                        () -> pasarDeRonda(),
                        () -> cambiarDeEscena(crearEscenaDeJuego()))
        ), contenedorPrincipal, 0.2, 0.1);

        HBox contenedorDeCartas = new HBox();
        ArrayList<Carta> cartas = this.rondaActual.obtenerCartas();
        for (Carta carta : cartas) {
            Button cartaBoton = this.crearBoton(carta.obtenerNombre(), Color.DARKBLUE, (null), contenedorPrincipal, 0.08, 0.2);
            cartaBoton.setOnAction(new CartaApretarEventHandler(this.seleccionadas, carta, cartaBoton));

            contenedorDeCartas.getChildren().add(cartaBoton);
            HBox.setMargin(cartaBoton, new Insets(0,0,30,0));
        }
        contenedorDeCartas.setAlignment(Pos.BOTTOM_CENTER);
        contenedorDeCartas.setSpacing(20);

        VBox comodines = new VBox();

        ImageView comodinLogo = new ImageView(new Image("/comodines.png"));
        comodinLogo.setPreserveRatio(true);
        comodinLogo.fitWidthProperty().bind(contenedorPrincipal.widthProperty().multiply(0.07));
        comodinLogo.fitHeightProperty().bind(contenedorPrincipal.heightProperty().multiply(0.04));
        VBox.setMargin(comodinLogo, new Insets(10,0,0,10));
        comodines.getChildren().add(comodinLogo);

        for (int i = 1; i <= 5; i ++) {
            Button comodinBoton = this.crearBoton("comodinVacio", Color.DARKBLUE, (null), contenedorPrincipal, 0.04, 0.1);
//            comodinBoton.setOnAction(new CartaApretarEventHandler(this.seleccionadas, carta, comodinBoton));

            comodines.getChildren().add(comodinBoton);
            VBox.setMargin(comodinBoton, new Insets(10,0,0,20));
        }
        comodines.setAlignment(Pos.TOP_LEFT);
        comodines.setSpacing(10);

        VBox tarots = new VBox();

        ImageView tarotLogo = new ImageView(new Image("/tarots.png"));
        tarotLogo.setPreserveRatio(true);
        tarotLogo.fitWidthProperty().bind(contenedorPrincipal.widthProperty().multiply(0.065));
        tarotLogo.fitHeightProperty().bind(contenedorPrincipal.heightProperty().multiply(0.035));
        VBox.setMargin(tarotLogo, new Insets(10,0,0,10));
        tarots.getChildren().add(tarotLogo);

        for (int i = 1; i <= 2; i ++) {
            Button tarotBoton = this.crearBoton("comodinVacio", Color.DARKBLUE, (null), contenedorPrincipal, 0.04, 0.1);
//            tarotBoton.setOnAction(new CartaApretarEventHandler(this.seleccionadas, carta, tarotBoton));

            tarots.getChildren().add(tarotBoton);
            VBox.setMargin(tarotBoton, new Insets(10,0,0,20));
        }
        tarots.setAlignment(Pos.TOP_LEFT);
        tarots.setSpacing(10);

        HBox comodinesYTarots = new HBox(comodines, tarots);


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



        ImageView stringPuntajeASuperar = new ImageView(new Image("/puntajeASuperar.png"));
        stringPuntajeASuperar.setPreserveRatio(true);
        stringPuntajeASuperar.fitWidthProperty().bind(contenedorPrincipal.widthProperty().multiply(0.08));
        stringPuntajeASuperar.fitHeightProperty().bind(contenedorPrincipal.heightProperty().multiply(0.04));

        Label puntajeASuperarMostrado = new Label();
        puntajeASuperarMostrado.setText(String.valueOf(this.rondaActual.obtenerPuntajeASuperar()));
        puntajeASuperarMostrado.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        HBox puntajeASuperarBox = new HBox(stringPuntajeASuperar, puntajeASuperarMostrado);
        HBox.setMargin(stringPuntajeASuperar, new Insets(5,5,0,0));
        HBox.setMargin(puntajeASuperarMostrado, new Insets(5,5,0,0));
        puntajeASuperarBox.setSpacing(5);


        ImageView stringpuntajeObtenidos = new ImageView(new Image("/puntajeObtenido.png"));
        stringpuntajeObtenidos.setPreserveRatio(true);
        stringpuntajeObtenidos.fitWidthProperty().bind(contenedorPrincipal.widthProperty().multiply(0.08));
        stringpuntajeObtenidos.fitHeightProperty().bind(contenedorPrincipal.heightProperty().multiply(0.04));

        Label puntajesObtenidosMostrados = new Label();
        puntajesObtenidosMostrados.setText(String.valueOf(this.rondaActual.obtenerPuntajesObtenidos()));
        puntajesObtenidosMostrados.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        HBox puntajesObtenidosBox = new HBox(stringpuntajeObtenidos, puntajesObtenidosMostrados);
        HBox.setMargin(stringpuntajeObtenidos, new Insets(5,5,0,0));
        HBox.setMargin(puntajesObtenidosMostrados, new Insets(5,5,0,0));
        puntajesObtenidosBox.setSpacing(5);


        ImageView stringJugadasRestantes = new ImageView(new Image("/jugadasRestantes.png"));
        stringJugadasRestantes.setPreserveRatio(true);
        stringJugadasRestantes.fitWidthProperty().bind(contenedorPrincipal.widthProperty().multiply(0.08));
        stringJugadasRestantes.fitHeightProperty().bind(contenedorPrincipal.heightProperty().multiply(0.04));

        Label jugadasRestantesMostrados = new Label();
        jugadasRestantesMostrados.setText(String.valueOf(this.rondaActual.obtenerJugadasRestantes()));
        jugadasRestantesMostrados.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        HBox jugadasRestantesBox = new HBox(stringJugadasRestantes, jugadasRestantesMostrados);
        HBox.setMargin(stringJugadasRestantes, new Insets(5,5,0,0));
        HBox.setMargin(stringJugadasRestantes, new Insets(5,5,0,0));
        jugadasRestantesBox.setSpacing(5);


        ImageView stringDescartesRestantes = new ImageView(new Image("/descartesRestantes.png"));
        stringDescartesRestantes.setPreserveRatio(true);
        stringDescartesRestantes.fitWidthProperty().bind(contenedorPrincipal.widthProperty().multiply(0.08));
        stringDescartesRestantes.fitHeightProperty().bind(contenedorPrincipal.heightProperty().multiply(0.04));

        Label descartesRestantesMostrados = new Label();
        descartesRestantesMostrados.setText(String.valueOf(this.rondaActual.obtenerDescartesRestantes()));
        descartesRestantesMostrados.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        HBox descartesRestantesBox = new HBox(stringDescartesRestantes, descartesRestantesMostrados);
        HBox.setMargin(stringDescartesRestantes, new Insets(5,5,0,0));
        HBox.setMargin(descartesRestantesMostrados, new Insets(5,5,0,0));
        descartesRestantesBox.setSpacing(5);


        ImageView stringRondaActual = new ImageView(new Image("/rondaActual.png"));
        stringRondaActual.setPreserveRatio(true);
        stringRondaActual.fitWidthProperty().bind(contenedorPrincipal.widthProperty().multiply(0.08));
        stringRondaActual.fitHeightProperty().bind(contenedorPrincipal.heightProperty().multiply(0.04));

        Label rondaActualMostrada = new Label();
        rondaActualMostrada.setText(String.valueOf(this.posRondaActual+1));
        rondaActualMostrada.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        HBox rondaActualBox = new HBox(stringRondaActual, rondaActualMostrada);
        HBox.setMargin(stringRondaActual, new Insets(5,5,0,0));
        HBox.setMargin(rondaActualMostrada, new Insets(5,5,0,0));
        rondaActualBox.setSpacing(5);


        VBox datosRonda = new VBox(puntajeASuperarBox, puntajesObtenidosBox, jugadasRestantesBox, descartesRestantesBox, rondaActualBox);
        datosRonda.setSpacing(20);

        contenedorPrincipal.setBottom(layout);
        contenedorPrincipal.setCenter(contenedorDeCartas);
        contenedorPrincipal.setLeft(comodinesYTarots);
        contenedorPrincipal.setRight(datosRonda);

        return new Scene(contenedorPrincipal, 1280, 720);
    }

    private void iniciarJuego() {
        this.maxRondas = this.rondas.size();
        this.posRondaActual = 0;
        this.rondaActual = this.rondas.get(this.posRondaActual);
        this.mazo.mezclar();
        cambiarDeEscena(crearEscenaDeJuego());
    }

    private void pasarDeRonda() {
        this.posRondaActual++;
        if (posRondaActual >= maxRondas) {
            cambiarDeEscena(crearEscenaDeGanaste());
            return;
        }
        this.seleccionadas.clear();
        this.rondaActual = this.rondas.get(this.posRondaActual);
        this.mazo.mezclar();
        cambiarDeEscena(crearEscenaDeJuego());
    }

    private Scene crearEscenaDeGanaste() {
        BorderPane contenedorPrincipal = new BorderPane();

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("/ganasteFondo.png", true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, false)
        );

        contenedorPrincipal.setBackground(new Background(backgroundImage));

        VBox medio = new VBox();

        ImageView logo = new ImageView(new Image("/ganasteNombre.png"));
        logo.setPreserveRatio(true);
        logo.fitWidthProperty().bind(contenedorPrincipal.widthProperty().multiply(0.5));
        logo.fitHeightProperty().bind(contenedorPrincipal.heightProperty().multiply(0.3));

        Button playButton = this.crearBoton("jugar2", Color.DARKRED, (new IniciarJuegoEventHandler(this.rondas, this.mazo ,() -> iniciarJuego())), contenedorPrincipal, 0.2, 0.1);
        medio.getChildren().add(logo);
        medio.getChildren().add(playButton);
        medio.setAlignment(Pos.CENTER);

        HBox abajo = new HBox();

        Button exitButton = this.crearBoton("salir2", Color.DARKGREEN, (e -> {
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

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("/perdiste.png", true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        contenedorPrincipal.setBackground(new Background(backgroundImage));

        VBox medio = new VBox();

        ImageView logo = new ImageView(new Image("/perdisteNombre.png"));
        logo.setPreserveRatio(true);
        logo.fitWidthProperty().bind(contenedorPrincipal.widthProperty().multiply(0.5));
        logo.fitHeightProperty().bind(contenedorPrincipal.heightProperty().multiply(0.3));

        Button playButton = this.crearBoton("jugar2", Color.DARKRED, (new IniciarJuegoEventHandler(this.rondas, this.mazo ,() -> iniciarJuego())), contenedorPrincipal, 0.2, 0.1);
        medio.getChildren().add(logo);
        medio.getChildren().add(playButton);
        medio.setAlignment(Pos.CENTER);

        HBox abajo = new HBox();

        Button exitButton = this.crearBoton("salir2", Color.DARKGREEN, (e -> {
            Platform.exit();
        }), contenedorPrincipal, 0.2, 0.1);
        abajo.getChildren().add(exitButton);
        abajo.setAlignment(Pos.BOTTOM_LEFT);
        HBox.setMargin(exitButton, new Insets(0,0,20,20));


        contenedorPrincipal.setCenter(medio);
        contenedorPrincipal.setBottom(abajo);

        return new Scene(contenedorPrincipal, 1280, 720);
    }


//    private void swapView(StackPane centerArea) {
//        VBox newView = new VBox(10);
//        newView.setAlignment(Pos.CENTER);
//
//        Button backButton = new Button("Volver");
//        backButton.setOnAction(e -> centerArea.getChildren().clear()); // Limpia el área al volver
//
//        newView.getChildren().addAll(backButton, new Button("Otra acción"));
//        centerArea.getChildren().add(newView);
//    }

    private void cambiarDeEscena(Scene newScene) {
        StagePrincipal.setScene(newScene);
    }

    public static void main(String[] args) {
        launch();
    }
}