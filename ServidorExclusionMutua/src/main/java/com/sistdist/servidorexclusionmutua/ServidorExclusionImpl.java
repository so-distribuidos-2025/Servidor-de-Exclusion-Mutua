package com.sistdist.servidorexclusionmutua;

import com.sistdist.interfaces.IExclusionMutua;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementación del servidor de exclusión mutua.
 * Mantiene un mapa de recursos y asegura que solo un cliente a la vez tenga acceso.
 */
public class ServidorExclusionImpl extends UnicastRemoteObject implements IExclusionMutua {

    private final Map<String, String> recursos;

    public ServidorExclusionImpl() throws RemoteException {
        super();
        this.recursos = new HashMap<>();
    }

    @Override
    public synchronized boolean solicitarAcceso(String recurso, String clienteId) throws RemoteException {
        if (!recursos.containsKey(recurso)) {
            recursos.put(recurso, clienteId);
            System.out.println("Acceso concedido a " + clienteId + " sobre " + recurso);
            return true;
        }
        System.out.println("Acceso denegado a " + clienteId + " sobre " + recurso 
                + " (ocupado por " + recursos.get(recurso) + ")");
        return false;
    }

    @Override
    public synchronized void liberarAcceso(String recurso, String clienteId) throws RemoteException {
        if (clienteId.equals(recursos.get(recurso))) {
            recursos.remove(recurso);
            System.out.println("Recurso " + recurso + " liberado por " + clienteId);
        } else {
            System.out.println("Cliente " + clienteId + " intentó liberar " + recurso 
                    + " pero no lo poseía");
        }
    }

    @Override
    public synchronized String consultarEstado(String recurso) throws RemoteException {
        return recursos.getOrDefault(recurso, "LIBRE");
    }
}
