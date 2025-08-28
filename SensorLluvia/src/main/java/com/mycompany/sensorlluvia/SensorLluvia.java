package com.mycompany.sensorlluvia;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SensorLluvia {

    public static void main(String[] args) {
        InetAddress ipServidor;
        PrintWriter pw;
        try {
            ipServidor = InetAddress.getByName("localhost");
            Socket cliente = new Socket(ipServidor, 20000);
            System.out.println(cliente);
            // PrintWriter con autoflush activado
            pw = new PrintWriter(cliente.getOutputStream(), true);
            // Identifica el sensor al servidor
            pw.println("lluvia");
            // Arranca el hilo de sensado
            HiloSensado sensor = new HiloSensado(cliente, pw);
            sensor.start();
        } catch (IOException ex) {
            Logger.getLogger(SensorLluvia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
