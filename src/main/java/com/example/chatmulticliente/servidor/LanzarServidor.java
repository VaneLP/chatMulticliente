package com.example.chatmulticliente.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class LanzarServidor implements Runnable{
    final public ControladorServidor interfaz;
    ArrayList<HiloConexion> conexiones = new ArrayList<>();

    LanzarServidor(ControladorServidor interfaz) {
        this.interfaz = interfaz;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        ServerSocket serverSocket;
        final int PUERTO = 9876;

        try {
            serverSocket = new ServerSocket(PUERTO);
            interfaz.escribirTexto("### Servidor inciado");

            while (true) {
                Socket conexion;
                conexion = serverSocket.accept();
                interfaz.escribirTexto("--- Petición de conexión recibida");
                HiloConexion hc = new HiloConexion(this, conexion);
                conexiones.add(hc);
                new Thread(hc).start();
            }
        } catch (IOException e) {
            System.out.println("Se ha producido algún error y no se ha iniciado el servidor.");
            return;
        }
    }


    public void enviarMsg(String msg) {
        interfaz.escribirTexto("Envio msg:" + msg);

        for (HiloConexion hc: conexiones) {
            hc.enviar(msg);
        }
    }

    public void enviarColor(String color) {
        for (HiloConexion hc: conexiones) {
            hc.enviar(color);
        }
    }


}
