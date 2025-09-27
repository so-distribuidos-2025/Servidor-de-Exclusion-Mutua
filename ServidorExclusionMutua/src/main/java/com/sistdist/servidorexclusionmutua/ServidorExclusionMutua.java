package com.sistdist.servidorexclusionmutua;

import com.sistdist.interfaces.IDetectorFalla;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servidor maestro que combina:
 * - Registro RMI en un puerto
 * - Servicio de exclusión mutua
 * - Servicio de detección de fallos
 */
public class ServidorExclusionMutua {

    public static void main(String[] args) {
        String nombreServidor = "ServidorMaestro";
        int nroPuerto = 9000;
        Timer planificador = new Timer();

        try {
            // Crear registro RMI
            LocateRegistry.createRegistry(nroPuerto);

            // 1) Registrar el detector de fallos
            DetectorFallo detector = new DetectorFallo();
            detector.setNombre(nombreServidor);
            Naming.rebind("rmi://localhost:" + nroPuerto + "/" + nombreServidor, (IDetectorFalla) detector);

            // 2) Registrar el servicio de exclusión mutua
            ServidorExclusionImpl exclusion = new ServidorExclusionImpl();
            Naming.rebind("rmi://localhost:" + nroPuerto + "/ExclusionMutua", exclusion);

            // 3) Tareas periódicas de control de fallos
            AveriguarEstado tareaAveriguarEstado = new AveriguarEstado(detector);
            ControlRespuesta tareaControlRespuesta = new ControlRespuesta(detector);
            planificador.schedule(tareaAveriguarEstado, 0, 5000);
            planificador.schedule(tareaControlRespuesta, 0, 8000);

            System.out.println("Servidor de exclusión mutua y detector de fallos iniciado en puerto " + nroPuerto);

        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(ServidorExclusionMutua.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

