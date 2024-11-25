package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CartaApretarEventHandler;
import edu.fiuba.algo3.controllers.IniciarJuegoEventHandler;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.util.Duration;

import java.util.ArrayList;

/*
        ///////////////////////
        BOTONES
        8 Cartas (abajo en el medio)
        1 JugarMano (abajo en el medio izquierda)
        1 DescartarMano (abajo en el medio derecha)
        1 Rendirse (abajo bien izquierda)

        5 Comodines (arriba a la izquierda)
        2 Tarots (arriba a la izquierda a la derecha de los comodines)
        ///////////////////////
        1 Abrir Tienda -> 6 comprables
        LABELS
        1 Puntaje Obtenido
        1 Puntaje a Superar
        1 Manos Restantes
        1 Descartes Restantes
        1 Ronda Actual ( /de Total)
        1 Juego Actual
        ///////////////////////
         */
// Botones escalables


public class BalatroGame extends Application {

    private Stage primaryStage;

    private Mazo mazo = new Mazo();
    private Comodinera comodinera = new Comodinera();
    private ArrayList<Carta> seleccionadas = new ArrayList<>();
    private ArrayList<Ronda> rondas = new ArrayList<>();
    private ArrayList<Tarot> tarots = new ArrayList<>();
    private Ronda rondaActual;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        Scene menuScene = createMenuScene();
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Balatro G_06");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("Cambio De pantalla completa: F11");
        menuScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F11) {
                // Alternar el modo de pantalla completa
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            }
        });
        primaryStage.show();
    }

    private Button crearBoton(String nombre, Color color, EventHandler<ActionEvent> handler, AspectRatioPane root, double ancho, double altura) {
        Button boton = new Button();
        Image playImage = new Image("/"+nombre+".png");
        ImageView imageView = new ImageView(playImage);
        // Escalar el ImageView
        imageView.setPreserveRatio(true); // Mantener relación de aspecto
        imageView.fitWidthProperty().bind(root.widthProperty().multiply(ancho)); // Escalar al 20% del ancho de la ventana
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(altura)); // Escalar al 10% de la altura de la ventana
        // Asignar el ImageView al botón
        boton.setGraphic(imageView);
        // Remover el padding adicional del botón
        boton.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        // Acción del botón
        boton.setOnAction(e -> {
            handler.handle(e);
        });
        DropShadow hoverEffect = new DropShadow();
        hoverEffect.setColor(color);
        hoverEffect.setRadius(15);
        // Evento al pasar el mouse
        boton.setOnMouseEntered(e -> {
            boton.setEffect(hoverEffect);
            ScaleTransition hoverScale = new ScaleTransition(Duration.millis(200), boton);
            hoverScale.setToX(1.1);
            hoverScale.setToY(1.1);
            hoverScale.play();
        });
        // Evento al salir el mouse
        boton.setOnMouseExited(e -> {
            boton.setEffect(null); // Quitar sombra
            ScaleTransition hoverScale = new ScaleTransition(Duration.millis(200), boton);
            hoverScale.setToX(1.0);
            hoverScale.setToY(1.0);
            hoverScale.play();
        });
        return boton;
    }

    private Scene createMenuScene() {
        AspectRatioPane root = new AspectRatioPane(16.0 / 9.0); // Relación de aspecto 16:9

        // Fondo del menú
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("/fondoMenus.png", true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        root.setBackground(new Background(backgroundImage));

        // Layout principal del menú
        VBox medio = new VBox();

        // Logo
        ImageView logo = new ImageView(new Image("/logoBalatro.png"));
        logo.setPreserveRatio(true); // Mantener relación de aspecto
        logo.fitWidthProperty().bind(root.widthProperty().multiply(0.5)); // Escalar al 30% del ancho de la ventana
        logo.fitHeightProperty().bind(root.heightProperty().multiply(0.3));

        Button playButton = this.crearBoton("Jugar", Color.DARKRED, (new IniciarJuegoEventHandler(this.rondas, this.mazo,this.rondaActual ,() -> switchScene(createGameScene()))), root, 0.2, 0.1);
        medio.getChildren().add(logo);
        medio.getChildren().add(playButton);
        medio.setAlignment(Pos.CENTER);

        HBox abajo = new HBox();

        Button exitButton = this.crearBoton("Salir", Color.DARKGREEN, (e -> {
            Platform.exit();
            }), root, 0.2, 0.1);
        abajo.getChildren().add(exitButton);
        abajo.setAlignment(Pos.BOTTOM_LEFT);
        abajo.setMargin(exitButton, new Insets(0,0,20,20));

        BorderPane pane = new BorderPane();
        pane.setCenter(medio);
        pane.setBottom(abajo);
        root.getChildren().add(pane);

        return new Scene(root, 1280, 720);
    }

    // 2. Escena del juego con View Swapping
    private Scene createGameScene() {
        AspectRatioPane root = new AspectRatioPane(16.0 / 9.0); // Relación de aspecto 16:9

        // Fondo escalable
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("/fondo2.png", true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        root.setBackground(new Background(backgroundImage));

        // Botón para rendirse
        Button rendirseBoton = this.crearBoton("rendirse", Color.DARKRED, (e -> {
            Platform.exit();
        }), root, 0.1, 0.75);

        // Botón para jugarMano
        Button jugarManoBoton = this.crearBoton("jugarMano", Color.DARKRED, (e -> {
            Platform.exit();
        }), root, 0.2, 0.1);

        // Botón para descartarMano
        Button descartarManoBoton = this.crearBoton("descartar", Color.DARKRED, (e -> {
            Platform.exit();
        }), root, 0.2, 0.1);

        // CREAR Y REPARTIR LAS CARTAS
        HBox cartasDisplay = new HBox();

        System.out.println(rondas);
        this.rondaActual = rondas.get(0);
        ArrayList<Carta> cartas = this.rondaActual.obtenerCartas();
        for (Carta carta : cartas) {
            Button cartaBoton = this.crearBoton(carta.obtenerNombre(), Color.DARKBLUE, (new CartaApretarEventHandler(this.seleccionadas,carta)), root, 0.04, 0.1);
            cartasDisplay.getChildren().add(cartaBoton);
        }

        cartasDisplay.setAlignment(Pos.BOTTOM_CENTER);
        cartasDisplay.setSpacing(20);



//        // Escalar el botón dinámicamente
//        switchViewButton.styleProperty().bind(
//                Bindings.createStringBinding(() -> {
//                    double scale = gameLayout.getWidth() / 100; // Escalado basado en el ancho
//                    return String.format("-fx-font-size: %.1fpx;", scale * 1.5);
//                }, gameLayout.widthProperty())
//        );
////
////        VBox topArea = new VBox(10, switchViewButton);
////        topArea.setAlignment(Pos.CENTER);
////        content.setTop(topArea);
////
////        gameLayout.getChildren().add(content);
//
//
        HBox rendirse = new HBox();
        rendirse.getChildren().add(rendirseBoton);
        rendirse.setAlignment(Pos.BOTTOM_LEFT);
        rendirse.setMargin(rendirseBoton, new Insets(0,0,25,20));

        HBox juegoYDescartar = new HBox();
        juegoYDescartar.getChildren().addAll(descartarManoBoton, jugarManoBoton);
        juegoYDescartar.setAlignment(Pos.BOTTOM_CENTER);
        juegoYDescartar.setSpacing(40);
        juegoYDescartar.setMargin(jugarManoBoton, new Insets(0,0,20,0));
        juegoYDescartar.setMargin(descartarManoBoton, new Insets(0,0,20,0));


        HBox layout = new HBox(rendirse, juegoYDescartar);
        HBox.setHgrow(rendirse, Priority.SOMETIMES);
        HBox.setHgrow(juegoYDescartar, Priority.ALWAYS);

        BorderPane pane = new BorderPane();
        pane.setBottom(layout);
        pane.setCenter(cartasDisplay);


        root.getChildren().add(pane);
        return new Scene(root, 1280, 720);
    }

    // Contenedor con botones (ejemplo: cartas en la parte inferior)
    private HBox createButtonsArea() {
        HBox buttonsArea = new HBox(10);
        buttonsArea.setAlignment(Pos.CENTER);



        // Crear botones con imágenes
        for (int i = 1; i <= 8; i++) {
            ImageView cardImage = new ImageView(new Image("/carta" + i + ".png", 80, 120, false, true));
            Button cardButton = new Button();
            cardButton.setGraphic(cardImage);
            cardButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;"); // Sin bordes ni relleno
            int finalI = i;
            cardButton.setOnAction(e -> System.out.println("Carta " + finalI + " seleccionada"));
            buttonsArea.getChildren().add(cardButton);
        }

        return buttonsArea;
    }

    // Método para cambiar el contenido del área central dinámicamente
    private void swapView(StackPane centerArea) {
        // Crear una nueva vista (puede ser un texto, un grid, etc.)
        VBox newView = new VBox(10);
        newView.setAlignment(Pos.CENTER);

        Button backButton = new Button("Volver");
        backButton.setOnAction(e -> centerArea.getChildren().clear()); // Limpia el área al volver

        newView.getChildren().addAll(backButton, new Button("Otra acción"));
        centerArea.getChildren().add(newView);
    }

    // Método auxiliar para cambiar de escena manteniendo el estado de la ventana
    private void switchScene(Scene newScene) {
        boolean wasFullScreen = primaryStage.isFullScreen(); // Estado de pantalla completa
        double currentWidth = primaryStage.getWidth(); // Ancho actual
        double currentHeight = primaryStage.getHeight(); // Altura actual

        primaryStage.setScene(newScene); // Cambiar la escena
        newScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F11) {
                // Alternar el modo de pantalla completa
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            }
        });
        primaryStage.setFullScreen(wasFullScreen); // Restaurar pantalla completa si estaba activa
        primaryStage.setWidth(currentWidth); // Restaurar el ancho actual
        primaryStage.setHeight(currentHeight); // Restaurar la altura actual
    }

    public static void main(String[] args) {
        launch();
    }
}