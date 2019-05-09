package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Modelo.Valoracion_cliente;

public interface IValoracionCliente extends Remote {
	Valoracion_cliente buscarValoracionCliente (int idCliente) throws RemoteException;
	boolean actualizarValoracionCliente (Valoracion_cliente valoracionCliente) throws RemoteException;
	boolean eliminarValoracionCliente (int idCliente) throws RemoteException;
	boolean crearValoracionCliente (Valoracion_cliente valoracionCliente) throws RemoteException;
}
