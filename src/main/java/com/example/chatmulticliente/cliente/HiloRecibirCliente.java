package com.example.chatmulticliente.cliente;

import javafx.scene.paint.Color;

import java.io.IOException;

public class HiloRecibirCliente implements Runnable {
    private ControladorClienteChat cliente;

    public HiloRecibirCliente(ControladorClienteChat cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Leer del flujo y escribir en el chat
                String lectura = cliente.getEntrada().readLine();
                Color c = Color.valueOf(cliente.getEntrada().readLine());

                cliente.escribirColor(lectura, c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
