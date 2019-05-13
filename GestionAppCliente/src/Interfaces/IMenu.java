package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Modelo.Menu;
import Modelo.Mesa;

public interface IMenu extends Remote {
	Menu buscarMenu(int idMenu) throws RemoteException;
	boolean buscarMenuID(int id) throws RemoteException;
	List<Menu> listarMenus() throws RemoteException;
	boolean actualizarMenu(Menu menu) throws RemoteException;
	boolean eliminarMenu(int idMenu) throws RemoteException;
	boolean crearMenu(Menu menu) throws RemoteException;
	int conteo() throws RemoteException;
}