package com.example.chatmulticliente.entrada;

import com.example.chatmulticliente.cliente.AplicacionClienteChat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AplicacionEntrada extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //para agregar el css
        Application.setUserAgentStylesheet(String.valueOf(AplicacionClienteChat.class.getResource("dracula.css")));
        //leemos el FXML
        FXMLLoader fxmlLoader = new FXMLLoader(AplicacionClienteChat.class.getResource("entrada.fxml"));
        //redimensionamos la ventana
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        //agregamos un titulo a la ventana
        stage.setTitle("Entrada");
        //asignamos la ventana
        stage.setScene(scene);
        //mostramos la ventana
        stage.show();
    }

    // ---- MAIN ----
    public static void main(String[] args) {
        //Lanzamos la la aplicacion
        launch();
    }
}