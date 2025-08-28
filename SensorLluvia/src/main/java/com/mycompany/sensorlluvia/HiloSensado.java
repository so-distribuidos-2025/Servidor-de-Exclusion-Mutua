package com.mycompany.sensorlluvia;

import java.io.PrintWriter;
import java.net.Socket;

public class HiloSensado extends Thread {
    private boolean on;
    private int lluvia; // 1 si llueve, 0 si no
    private float probabilidad;
    private Socket cnxServidor;
    PrintWriter pw;

    public HiloSensado(Socket s, PrintWriter pw) {
        this.on = true;
        this.lluvia = 0;
        this.probabilidad = 0;
        this.cnxServidor = s;
        this.pw = pw;
    }

    public int getLluvia() {
        return lluvia;
    }

    private void generarLluvia() {
        probabilidad = (float) (Math.random() * 100);

        if (probabilidad > 50) {
            lluvia = 1;
        } else {
            lluvia = 0;
        }
    }

    public void encender() {
        on = true;
    }

    public void apagar() {
        on = false;
    }

    public void run() {
        while (on) {
            generarLluvia();
            pw.println("L=" + this.lluvia);
            System.out.println("L=" + this.lluvia);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

