package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloReceptorTiempo implements Runnable{

    private Socket clienteTiempo;
    private final BufferedReader br;
    private int minutos;
    private int segundos;
    private RuntimeException runtimeException;

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public HiloReceptorTiempo(Socket clienteTemporizador) {
        this.clienteTiempo = clienteTemporizador;
        try {
            this.br = new BufferedReader(new InputStreamReader(clienteTemporizador.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                String entrada = br.readLine();
            } catch (IOException e) {
                throw runtimeException;
        }
    }
}
}
