package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Modelo.Pedido_detalle;

public interface IPedidoDetalle extends Remote {
	Pedido_detalle buscarPedidoDetalle(int idPedido) throws RemoteException;
	List<Pedido_detalle> listarPedidosDetalles() throws RemoteException;
	List<Pedido_detalle> listarPedidosDetallesId(int idPedido) throws RemoteException;
	boolean actualizarPedidoDetalle(Pedido_detalle pedidoDetalle) throws RemoteException;
	boolean eliminarPedidoDetalle(int idPedido) throws RemoteException;
	boolean crearPedidoDetalle(Pedido_detalle pedidoDetalle) throws RemoteException;
}
