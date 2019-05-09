package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Modelo.Cliente;

public interface ICliente extends Remote {
	
	Cliente buscarCliente(String documento) throws RemoteException;
	Cliente buscarClienteId(int id)throws RemoteException;
	List<Cliente> listarClientes() throws RemoteException;
	boolean actualizarCliente(Cliente cliente) throws RemoteException;
	boolean eliminarCliente(String documento) throws RemoteException;
	boolean crearCliente(Cliente cliente) throws RemoteException;
	boolean actualizarValoracion(Cliente cliente) throws RemoteException;
	
}
