package org.example;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloReceptorTemperatura extends Thread{
    private Socket clienteTemperatura;
    private final BufferedReader br;
    private float temperatura;

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public HiloReceptorTemperatura(Socket clienteTemperatura) {
        this.clienteTemperatura = clienteTemperatura;
        try {
            this.br = new BufferedReader(new InputStreamReader(clienteTemperatura.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(){
        while (true){
            try {
                String entrada = br.readLine();
                temperatura = Float.parseFloat(entrada);
                System.out.println(temperatura);
                sleep(1000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}