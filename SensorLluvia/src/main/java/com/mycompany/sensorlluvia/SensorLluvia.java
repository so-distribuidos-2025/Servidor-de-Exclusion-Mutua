package com.mycompany.sensorlluvia;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SensorLluvia {

    public static void main(String[] args) {
        InetAddress IPServidor;
        PrintWriter pw;
        try {
            IPServidor = InetAddress.getByName("localhost");
            Socket cliente = new Socket(IPServidor,20000);
            pw = new PrintWriter(cliente.getOutputStream());
            pw.println("sensorLluvia");
            
            HiloSensado sensor = new HiloSensado(cliente,pw);
            sensor.start();
        } catch (IOException ex) {
            Logger.getLogger(SensorLluvia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
