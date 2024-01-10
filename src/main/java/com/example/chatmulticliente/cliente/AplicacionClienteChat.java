package com.example.chatmulticliente.cliente;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AplicacionClienteChat extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //para agregar el css
        Application.setUserAgentStylesheet(String.valueOf(AplicacionClienteChat.class.getResource("/com/example/chatmulticliente/dracula.css")));
        //leemos el FXML
        FXMLLoader fxmlLoader = new FXMLLoader(AplicacionClienteChat.class.getResource("clienteChat.fxml"));
        //redimensionamos la ventana
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        //agregamos un titulo a la ventana
        stage.setTitle("Cliente chat");
        //asignamos la ventana
        stage.setScene(scene);
        //mostramos la ventana
        stage.show();

        controlador = fxmlLoader.getController();
    }
    private ControladorClienteChat controlador;

    @Override
    public void stop() throws Exception {
        controlador.salir();
        Platform.exit();
    }

    // ---- MAIN ----
    public static void main(String[] args) {
        //Lanzamos la la aplicacion
        launch();
    }
}