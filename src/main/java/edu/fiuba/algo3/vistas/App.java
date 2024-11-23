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

    @Override
    public void start(Stage stage) {
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

        // Contenedor principal
        BorderPane layout = new BorderPane();

        // Contenedor para los botones
        HBox bottomButtons = new HBox();
        bottomButtons.setSpacing(10); // Espacio entre los botones
        bottomButtons.setStyle("-fx-padding: 20;"); // Estilo opcional
        bottomButtons.setAlignment(javafx.geometry.Pos.CENTER); // Centra los botones

        // Crear y añadir 8 botones
        for (int i = 1; i <= 8; i++) {
            Image cardImage = new Image("/AsDeCorazon.png");
            ImageView imageView = new ImageView(cardImage);
            imageView.setFitWidth(80); // Ancho de la imagen
            imageView.setFitHeight(120); // Alto de la imagen

            Button buttons = new Button("Carta" + i);
//            buttons.setGraphic(imageView);
            bottomButtons.getChildren().add(buttons);
        }


        bottomButtons.setSpacing(10); // Espacio entre los botones
        bottomButtons.setStyle("-fx-padding: 20;"); // Estilo opcional
        bottomButtons.setAlignment(javafx.geometry.Pos.CENTER); // Centra los botones

        Label puntajeASuperar = new Label();
        puntajeASuperar.setText("Puntaje a superar: "+ "1000");
        puntajeASuperar.setStyle("-fx-text-fill: white;");
        layout.getChildren().add(puntajeASuperar);
        HBox puntaje = new HBox(puntajeASuperar);
        // Añadir el HBox en la parte inferior del BorderPane
        layout.setTop(puntaje);
        layout.setBottom(bottomButtons);
        layout.setBackground(new Background(fondo));

        // Configurar y mostrar la escena
        Scene scene = new Scene(layout, 800, 600);
        stage.setScene(scene);
        stage.setTitle("BALATRO_G06");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}