package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Modelo.Producto;

public interface IProducto extends Remote {
	Producto buscarProducto(int id) throws RemoteException;
	boolean buscarProductoID(int id) throws RemoteException;
	List<Producto> listarProductos() throws RemoteException;
	boolean actualizarProducto(Producto producto) throws RemoteException;
	boolean eliminarProducto(int id) throws RemoteException;
	boolean crearProducto(Producto producto) throws RemoteException;
	int conteo() throws RemoteException;
}