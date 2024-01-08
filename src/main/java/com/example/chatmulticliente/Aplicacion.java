package com.example.chatmulticliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Aplicacion extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //para agregar el css
        Application.setUserAgentStylesheet(String.valueOf(Aplicacion.class.getResource("dracula.css")));

        FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("vista-chat.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Chat Multicliente");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}