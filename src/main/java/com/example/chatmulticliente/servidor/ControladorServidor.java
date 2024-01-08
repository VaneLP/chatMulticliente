package com.example.chatmulticliente.servidor;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ControladorServidor {
    @FXML
    private TextArea info;

    public TextArea getInfo(){
        return info;
    }
    public void escribirTexto(String s) {
        info.appendText(s+"\n");
    }
}
