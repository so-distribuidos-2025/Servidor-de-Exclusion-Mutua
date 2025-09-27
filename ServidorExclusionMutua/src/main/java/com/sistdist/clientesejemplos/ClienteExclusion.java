package com.sistdist.clientesejemplos;

import com.sistdist.interfaces.IExclusionMutua;

import java.rmi.Naming;

/**
 * Cliente de prueba para el servidor de exclusión mutua.
 * Intenta obtener acceso exclusivo a un recurso, lo mantiene unos segundos y luego lo libera.
 */
public class ClienteExclusion {
    public static void main(String[] args) {
        try {
            String clienteId = args.length > 0 ? args[0] : "Cliente1";
            String recurso = "valvula1";

            IExclusionMutua exclusion = (IExclusionMutua) Naming.lookup("rmi://localhost:9000/ExclusionMutua");

            // Solicitar acceso
            if (exclusion.solicitarAcceso(recurso, clienteId)) {
                System.out.println(clienteId + " obtuvo acceso a " + recurso);

                // Simular trabajo con el recurso
                Thread.sleep(5000);

                // Liberar acceso
                exclusion.liberarAcceso(recurso, clienteId);
                System.out.println(clienteId + " liberó el recurso " + recurso);
            } else {
                System.out.println(clienteId + " NO pudo acceder al recurso " + recurso);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
