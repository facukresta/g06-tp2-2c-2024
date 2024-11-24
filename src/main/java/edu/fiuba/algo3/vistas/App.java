package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.SystemInfo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;

/**
 * JavaFX App
 */
public class App extends Application {

//    @Override
//    public void start(Stage stage) {
//
//        var javaVersion = SystemInfo.javaVersion();
//        var javafxVersion = SystemInfo.javafxVersion();
//
//        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
//        var scene = new Scene(new StackPane(label), 640, 480);
//        stage.setScene(scene);
//        stage.show();
//        stage.setTitle("BALATRO G_06");
//
//        StackPane layout = new StackPane();
//
        BackgroundImage fondo = new BackgroundImage(
                new Image("/fondo.png", true), // Ruta de la imagen
                BackgroundRepeat.NO_REPEAT, // Repetición horizontal
                BackgroundRepeat.NO_REPEAT, // Repetición vertical
                BackgroundPosition.CENTER,  // Posición de la imagen
                new BackgroundSize(1080, 720, false, false, false, false)   // Tamaño de la imagen
        );
//
//         // texto
////        Label label = new Label();
////        label.setText("BALATROOOO");
////        layout.getChildren().add(label);
////
////        // texto modificable (para escribir)
////        TextField textField = new TextField();
////        layout.getChildren().add(textField);
////
////        // texto modificable GRANDE!!! (para escribir)
////        TextArea textArea = new TextArea();
////        textArea.setPrefColumnCount(5);
////        textArea.setPrefRowCount(5);
////        layout.getChildren().add(textArea);
//
//        // boton
////        Button button  = new Button();
////        button.setText("GOLAA");
////        button.setOnAction(event -> {
////            ...
////        });
////        layout.getChildren().add(button);
//
//        layout.setBackground(new Background(fondo));
//        Scene scene = new Scene(layout, 1080, 720);
//        stage.setScene(scene);
//        stage.show();

//        // Contenedor principal
//        BorderPane layout = new BorderPane();
//
//        // Contenedor para los botones
//        HBox bottomButtons = new HBox();
//        bottomButtons.setSpacing(10); // Espacio entre los botones
//        bottomButtons.setStyle("-fx-padding: 20;"); // Estilo opcional
//        bottomButtons.setAlignment(javafx.geometry.Pos.CENTER); // Centra los botones
//
//        // Crear y añadir 8 botones
//        for (int i = 1; i <= 8; i++) {
//            Image cardImage = new Image("/AsDeCorazon.png");
//            ImageView imageView = new ImageView(cardImage);
//            imageView.setFitWidth(80); // Ancho de la imagen
//            imageView.setFitHeight(120); // Alto de la imagen
//
//            Button buttons = new Button("Carta" + i);
////            buttons.setGraphic(imageView);
//            bottomButtons.getChildren().add(buttons);
//        }
//
//
//        bottomButtons.setSpacing(10); // Espacio entre los botones
//        bottomButtons.setStyle("-fx-padding: 20;"); // Estilo opcional
//        bottomButtons.setAlignment(javafx.geometry.Pos.CENTER); // Centra los botones
//
//        Label puntajeASuperar = new Label();
//        puntajeASuperar.setText("Puntaje a superar: "+ "1000");
//        puntajeASuperar.setStyle("-fx-text-fill: white;");
//        layout.getChildren().add(puntajeASuperar);
//        HBox puntaje = new HBox(puntajeASuperar);
//        // Añadir el HBox en la parte inferior del BorderPane
//        layout.setTop(puntaje);
//        layout.setBottom(bottomButtons);
//        layout.setBackground(new Background(fondo));
//
//        // Configurar y mostrar la escena
//        Scene scene = new Scene(layout, 800, 600);
//        stage.setScene(scene);
//        stage.setTitle("BALATRO_G06");
//        stage.show();
//    }

    @Override
    public void start(Stage stage) {

        // Contenedor principal
        StackPane root = new StackPane();

        // Vista 1
        VBox view1 = new VBox(new Label("Pantalla 1"), new Button("Ir a Pantalla 2"));

        // Vista 2
        VBox view2 = new VBox(new Label("Pantalla 2"), new Button("Volver a Pantalla 1"));

        // Botones para cambiar vistas
        ((Button) view1.getChildren().get(1)).setOnAction(event -> root.getChildren().setAll(view2));
        ((Button) view2.getChildren().get(1)).setOnAction(event -> root.getChildren().setAll(view1));

        // Configurar la escena inicial
        root.getChildren().add(view1);
        Scene scene = new Scene(root, 400, 300);

        stage.setScene(scene);
        stage.setTitle("Patrón View Swapping");
        stage.show();

//    // Contenedor principal
//    BorderPane root = new BorderPane();
//
//    // --- Zona superior: Información del juego ---
//    Label gameInfo = new Label("Puntaje: Jugador 1: 10 | Jugador 2: 15 | Turno: Jugador 1");
//        gameInfo.setStyle("-fx-font-size: 18; -fx-padding: 10; -fx-background-color: #eeeeee;");
//        root.setTop(gameInfo);
//
//    // --- Zona central: Tablero de cartas jugadas ---
//    HBox playedCards = new HBox();
//        playedCards.setAlignment(javafx.geometry.Pos.CENTER);
//        playedCards.setSpacing(10);
//        playedCards.setStyle("-fx-background-color: #ffffff; -fx-padding: 20;");
//    // Cartas en el tablero
//        for (int i = 1; i <= 1; i++) { // Simulando 4 cartas en el tablero
//        ImageView cardImage = new ImageView(new Image("/AsDeCorazon.png"));
//        cardImage.setFitWidth(100);
//        cardImage.setFitHeight(150);
//        playedCards.getChildren().add(cardImage);
//    }
//        root.setCenter(playedCards);
//
//    // --- Zona inferior: Cartas de la mano del jugador ---
//    HBox playerHand = new HBox();
//        playerHand.setAlignment(javafx.geometry.Pos.CENTER);
//        playerHand.setSpacing(10);
//        playerHand.setStyle("-fx-padding: 10; -fx-background-color: #eeeeee;");
//
////     Añadir cartas (como botones con imágenes)
//        for (int i = 1; i < 1; i++) { // Simulando 8 cartas en la mano
//        ImageView cardImage = new ImageView(new Image("/AsDeCorazon.png"));
//        cardImage.setFitWidth(80);
//        cardImage.setFitHeight(120);
//
//        Button cardButton = new Button();
//        cardButton.setGraphic(cardImage);
//        cardButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
//
//        int cardIndex = i; // Para usarlo dentro del evento
//        cardButton.setOnAction(event -> {
//            System.out.println("Carta jugada: " + cardIndex);
//        });
//
//        playerHand.getChildren().add(cardButton);
//    }
//        root.setBottom(playerHand);
//
////     --- Zona derecha: Opciones adicionales (opcional) ---
//    VBox rightOptions = new VBox();
//        rightOptions.setAlignment(javafx.geometry.Pos.CENTER);
//        rightOptions.setSpacing(10);
//    Button passButton = new Button("Pasar");
//    Button endTurnButton = new Button("Finalizar Turno");
//        rightOptions.getChildren().addAll(passButton, endTurnButton);
//        root.setRight(rightOptions);
//
//    // Configurar escena
//    Scene scene = new Scene(root, 800, 600);
//        stage.setScene(scene);
//        stage.setTitle("Balatro - Pantalla de Juego");
//        stage.show();
}

    public static void main(String[] args) {
        launch();
    }
}