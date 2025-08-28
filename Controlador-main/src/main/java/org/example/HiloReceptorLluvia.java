package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.net.Socket;

public class HiloReceptorLluvia extends Thread{
    private Socket clienteLluvia;
    private final BufferedReader br;
    private int lluvia;

    public int lluvia() {
        return lluvia;
    }

    public void setLluvia(int lluvia) {
        this.lluvia = lluvia;
    }

    public HiloReceptorLluvia(Socket clienteLluvia) {
        this.clienteLluvia = clienteLluvia;
        try {
            this.br = new BufferedReader(new InputStreamReader(clienteLluvia.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(){
        while (true){
            try {
                int entrada = br.read();
                lluvia = entrada;
                System.out.println(lluvia);
                sleep(1000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}