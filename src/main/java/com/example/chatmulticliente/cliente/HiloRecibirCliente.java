package com.example.chatmulticliente.cliente;

public class HiloRecibirCliente implements Runnable {
    private ControladorClienteChat cliente;
    public HiloRecibirCliente(ControladorClienteChat cliente) {
        this.cliente = cliente;
    }
    @Override
    public void run() {
        while (true) {
            // Leer del flujo y escribir en el chat
        }
    }

}
