package com.example.chatmulticliente.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ControladorClienteChat {
    private Socket conexion;
    private InetSocketAddress direccion;
    private BufferedReader flujoEntrada;
    private PrintWriter flujoSalida;

    @FXML
    private Button botonEnviar;

    @FXML
    private TextArea mensaje;

    @FXML
    void enviarMensaje(ActionEvent event) {

    }

    /**
     * Create the application.
     */
    public ClienteChat() {
        initialize();
        conectar();
        recibir();
    }

    private void initialize() {}
    private void conectar() {}
    private void recibir() {}

}
