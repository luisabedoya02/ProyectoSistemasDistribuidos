package Interfaces;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import Modelo.Cliente;
import Modelo.Pedido;

public interface IPedido extends Remote {
	
	//Pedido buscarPedido(String documento, Date fecha) throws RemoteException;
	Pedido buscarPedidoId(int id)throws RemoteException;
	List<Pedido> listarPedidos() throws RemoteException;
	boolean actualizarPedido(Pedido pedido) throws RemoteException;
	boolean eliminarPedido(int idPedido) throws RemoteException;
	boolean crearPedido(Pedido pedido) throws RemoteException;
}
