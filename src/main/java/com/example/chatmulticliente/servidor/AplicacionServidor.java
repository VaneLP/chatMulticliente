package com.example.chatmulticliente.servidor;

import com.example.chatmulticliente.cliente.AplicacionClienteChat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AplicacionServidor extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //para agregar el css
        Application.setUserAgentStylesheet(String.valueOf(AplicacionClienteChat.class.getResource("dracula.css")));
        //leemos el FXML
        FXMLLoader fxmlLoader = new FXMLLoader(AplicacionClienteChat.class.getResource("servidor.fxml"));
        //redimensionamos la ventana
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        //agregamos un titulo a la ventana
        stage.setTitle("Servidor chat");
        //asignamos la ventana
        stage.setScene(scene);
        //mostramos la ventana
        stage.show();

        //lanzamos un hilo del servidor, usamos el fxmlLoader para que nos coja el mismo controlador
        new Thread(new LanzarServidor(fxmlLoader.getController())).start();
    }

    // ---- MAIN ----
    public static void main(String[] args) {
        //Lanzamos la la aplicacion
        launch();
    }
}