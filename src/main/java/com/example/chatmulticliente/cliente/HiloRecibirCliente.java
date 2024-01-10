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
                System.out.println("esperando...");
                String lectura = cliente.getEntrada().readLine();
                System.out.println("recibido msg: "+lectura);
                Color c = Color.valueOf(cliente.getEntrada().readLine());
                System.out.println("recibido colo: "+c);

                cliente.escribirColor(lectura, c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (NullPointerException nu){
            System.err.println("Color nulo");
        }
    }

}
