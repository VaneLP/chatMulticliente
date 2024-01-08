package com.example.chatmulticliente;

import com.example.chatmulticliente.servidor.LanzarServidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloConexion implements Runnable{
    private Socket conexion;
    private LanzarServidor servidor;
    private BufferedReader flujoEntrada;
    private PrintWriter flujoSalida;

    public HiloConexion(LanzarServidor servidor, Socket conexion) {
        this.conexion = conexion;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try {
            flujoEntrada = new BufferedReader(new
                    InputStreamReader(conexion.getInputStream()));
            flujoSalida = new PrintWriter(conexion.getOutputStream());
            while (true) {
                String lectura = flujoEntrada.readLine();
                String comando = lectura.substring(0, 3);
                if (comando.equals("MSG")) {
                    servidor.enviarMsg(lectura.substring(4));
                }
                if (comando.equals("CON")) {
                    servidor.interfaz.escribirTexto("Se ha conectado " + lectura.substring(4));
                }
                if (comando.equals("EXI")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo crear algún flujo");
            return;
        } finally {
            try {
                flujoEntrada.close();
                flujoSalida.close();
                conexion.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void enviar(String texto){
        flujoSalida.println(texto);
    }

}
