package com.example.chatmulticliente.cliente;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.StageStyle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorClienteChat implements Initializable {
    @FXML
    private Button botonEnviar;
    @FXML
    private TextField campoNick;
    @FXML
    private TextField mensaje;
    @FXML
    private TextFlow panelInfo;
    @FXML
    private Text textoNick;
    @FXML
    private ScrollPane scrollPane;


    @FXML
    void enviarMensaje(ActionEvent event) {
        enviar();
    }

    private Socket conexion;
    private InetSocketAddress direccion;
    private BufferedReader flujoEntrada;
    private PrintWriter flujoSalida;
    private Color[] listaColores = new Color[]{Color.rgb(149,128,255),Color.PINK,Color.LIGHTGREEN,Color.PEACHPUFF};
    private Color colo;

    // ---- METODOS ----
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conectar();
        recibir();
    }

    private void conectar() {
        while (this.campoNick.getText().equals("")) {
            //se crea el tipo de dialogo que necesitamos
            TextInputDialog dialogoText = new TextInputDialog();
            dialogoText.setTitle("Entrada");
            dialogoText.setContentText("Introduce tu apodo: ");
            dialogoText.initStyle(StageStyle.UTILITY);

            //se recibe la respuesta del usuario
            Optional<String> respuesta = dialogoText.showAndWait();

            //obtenemos el valor del campo de texto
            respuesta.ifPresent((s)->this.campoNick.setText(s));

            if(respuesta.isEmpty()){
                System.exit(0);
            }

        }

        conexion = new Socket();
        direccion = new InetSocketAddress("localhost", 9876);

        try {
            conexion.connect(direccion);
            flujoEntrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            flujoSalida = new PrintWriter(conexion.getOutputStream());
            textoNick.setStyle("-fx-text-fill: WHITE");

            escribirColor("Conexión establecida", Color.SKYBLUE);
            flujoSalida.println("CON " + this.campoNick.getText());
            flujoSalida.flush();

            //-- COLORES --
            String lectura = flujoEntrada.readLine();
            int numUser= Integer.parseInt(lectura);

            colo = listaColores[numUser%4];

            System.out.println("NUMERO " + numUser + " COLOR "+ colo.toString());

        } catch (IOException e) {
            System.out.println("Se ha producido algún error en la conexión");
            escribirColor("Se ha producido algún error en la conexión", Color.RED);
            return;
        }
    }

    private void recibir() {
        new Thread(new HiloRecibirCliente(this)).start();
    }

//    public void escribir(String mensaje) {
//            System.out.println(colo);
//
//            escribirColor(mensaje, colo);
//
//            scrollPane.setVvalue(1.0);
//
//    }

    private void enviar() {
        flujoSalida.println("MSG "+ "("+campoNick.getText()+") "+ mensaje.getText());
        flujoSalida.flush();

        flujoSalida.println(colo);
        flujoSalida.flush();

        this.mensaje.setText("");
    }

    public BufferedReader getEntrada() {
        return flujoEntrada;
    }

    public void escribirColor(String mensaje, Color c) {
        Platform.runLater(() -> {
            Text text = new Text(mensaje + "\n");

            text.setFill(c);

            panelInfo.getChildren().add(text);

            scrollPane.setVvalue(1.0);
        });
    }


}
