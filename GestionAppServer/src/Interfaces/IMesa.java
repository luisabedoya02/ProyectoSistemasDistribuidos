package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Modelo.Mesa;

public interface IMesa extends Remote {
	Mesa buscarMesa(String codigoMesa) throws RemoteException;
	List<Mesa> listarMesas() throws RemoteException;
	boolean actualizarMesa(Mesa mesa) throws RemoteException;
	boolean eliminarMesa(String codigoMesa) throws RemoteException;
	boolean crearMesa(Mesa mesa) throws RemoteException;
}