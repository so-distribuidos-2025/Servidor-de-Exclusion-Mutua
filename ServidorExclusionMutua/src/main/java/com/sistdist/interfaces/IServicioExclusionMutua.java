/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sistdist.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz del servicio de exclusión mutua con soporte para múltiples recursos.
 */
public interface IServicioExclusionMutua extends Remote {
    
    /**
     * Solicita acceso a un recurso identificado por nombreRecurso.
     * Si el recurso está libre, el servidor entrega el token al cliente;
     * si está ocupado, el cliente queda en espera.
     */
    public void ObtenerRecurso(String nombreRecurso, IClienteEM cliente) throws RemoteException;

    /**
     * Libera el recurso identificado por nombreRecurso.
     * Si hay clientes esperando, el token se entrega al siguiente en la cola.
     */
    public void DevolverRecurso(String nombreRecurso) throws RemoteException;
}
