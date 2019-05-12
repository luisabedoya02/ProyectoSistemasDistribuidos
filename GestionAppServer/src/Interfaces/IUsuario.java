package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Modelo.Mesa;
import Modelo.Usuario;

public interface IUsuario extends Remote {
	Usuario buscarUsuario(String documento) throws RemoteException;
	boolean buscarUsuarioDoc(String documento) throws RemoteException;
	List<Usuario> listarUsuarios() throws RemoteException;
	boolean actualizarUsuario(Usuario usuario) throws RemoteException;
	boolean eliminarUsuario(String documento) throws RemoteException;
	boolean crearUsuario(Usuario usuario) throws RemoteException;
}
