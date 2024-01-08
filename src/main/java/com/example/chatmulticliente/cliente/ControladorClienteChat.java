package com.example.chatmulticliente.cliente;

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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.layout.Pane;

public class ControladorClienteChat implements Initializable {
    @FXML
    private Button botonEnviar;
    @FXML
    private TextField campoNick;
    @FXML
    private TextField mensaje;
    @FXML
    private Pane panelInfo;
    @FXML
    private TextArea textArea;

    @FXML
    void enviarMensaje(ActionEvent event) {
        enviar();
    }

    private Socket conexion;
    private InetSocketAddress direccion;
    private BufferedReader flujoEntrada;
    private PrintWriter flujoSalida;

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


            textArea.setStyle("-fx-text-fill: skyblue;");
            textArea.setText("Conexión establecida\n");



            //escribirColor("Conexión establecida", Color.BLUE);
            flujoSalida.println("CON " + this.campoNick.getText());
            flujoSalida.flush();
        } catch (IOException e) {
            System.out.println("Se ha producido algún error en la conexión");
            //escribirColor("Se ha producido algún error en la conexión", Color.RED);
            return;
        }
    }

    private void recibir() {
        new Thread(new HiloRecibirCliente(this)).start();
    }

    public void escribir(String mensaje) {
        //escribirColor(mensaje, Color.PINK);

        //letras
        textArea.setStyle("-fx-text-fill: #9580ff;");

        textArea.appendText("("+campoNick.getText()+") "+mensaje+"\n");
    }
    private void enviar() {
        flujoSalida.println(mensaje);
        flujoSalida.flush();

        escribir(mensaje.getText());

        this.mensaje.setText("");
    }

    public BufferedReader getEntrada() {
        return flujoEntrada;
    }

//    private void escribirColor(String s, Color c) {
//        Document doc = this.panelInfo.getDocument();
//        StyleContext sc = StyleContext.getDefaultStyleContext();
//        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
//                StyleConstants.Foreground, c);
//        try {
//            doc.insertString(doc.getLength(), s + "\n", aset);
//        } catch (BadLocationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

//    private void escribirColor(String mensaje, Color c) {
//        TextFlow textFlow = new TextFlow();
//        Text text = new Text(mensaje + "\n");
//
//        text.setFill(c);
//        textFlow.getChildren().add(text);
//        //panelInfo=new Pane();
//        //this.panelInfo.getChildren().add(textFlow);
//
//        if (panelInfo != null) {
//            this.panelInfo.getChildren().add(textFlow);
//            System.out.println("no soy nulo");
//
//        } else {
//            // Si panelInfo es null, puedes imprimir un mensaje para depuración
//            System.out.println("panelInfo es null");
//        }
//    }


}
