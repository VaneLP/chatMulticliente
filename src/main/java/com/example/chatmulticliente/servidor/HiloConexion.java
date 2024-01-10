package com.example.chatmulticliente.servidor;

import com.example.chatmulticliente.servidor.LanzarServidor;
import javafx.scene.paint.Color;

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
    private static int userCont=0;

    public HiloConexion(LanzarServidor servidor, Socket conexion) {
        this.conexion = conexion;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try {
            flujoEntrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            flujoSalida = new PrintWriter(conexion.getOutputStream());

            while (true) {
                String lectura = flujoEntrada.readLine();
                String comando = lectura.substring(0, 3);

                if (comando.equals("MSG")) {
                    servidor.enviarMsg(lectura.substring(4));

                    Color c = Color.valueOf(flujoEntrada.readLine());
                    System.out.println("enviando color: "+c);
                    servidor.enviarColor(String.valueOf(c));

                }
                if (comando.equals("CON")) {
                    servidor.interfaz.escribirTexto("Se ha conectado " + lectura.substring(4));
                    flujoSalida.println(userCont++);
                    flujoSalida.flush();
                }
                if (comando.equals("EXI")) {
                    servidor.interfaz.escribirTexto("Se ha desconectado " + lectura.substring(4));
                    flujoEntrada.close();
                    flujoSalida.close();
                    conexion.close();
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
        System.out.println("reenviando "+texto);
        flujoSalida.println(texto);
        flujoSalida.flush();
    }

}
