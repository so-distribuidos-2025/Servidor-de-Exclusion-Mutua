package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloReceptorHumedad extends Thread{
    private Socket clienteHumedad;
    private final BufferedReader br;
    private double humedad;

    public double getHumedad() {
        return humedad;
    }

    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }

    public HiloReceptorHumedad(Socket clienteHumedad) {
        this.clienteHumedad = clienteHumedad;
        try {
            this.br = new BufferedReader(new InputStreamReader(clienteHumedad.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(){
        while (true){
            try {
                String entrada = br.readLine();
                humedad = Double.parseDouble(entrada);
                System.out.println(humedad);
                sleep(1000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}