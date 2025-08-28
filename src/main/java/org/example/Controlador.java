package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Controlador {
    public static void main(String[] args) {
        String tipoDispositivo = "";

        try {
            ServerSocket server = new ServerSocket(20000);
            while (true) {
                Socket s = server.accept();
                HiloConexion handler = new HiloConexion(s);
                handler.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}