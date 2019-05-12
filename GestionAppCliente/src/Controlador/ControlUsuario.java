package Controlador;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import Interfaces.IUsuario;
import Modelo.Usuario;

public class ControlUsuario {
	private Usuario u = null;;
	private IUsuario iu = null;

	public ControlUsuario() throws RemoteException, NotBoundException {
		Registry r = LocateRegistry.getRegistry(10000);
		iu = (IUsuario) r.lookup("Usuario");
	}

	public Usuario searchUsuario(String codigoUsuario) {
		try {
			u = iu.buscarUsuario(codigoUsuario);
		} catch (RemoteException e) {
			System.out.println("usuario no econtrado");
			u = null;
		}
		return u;
	}
	
	public boolean searchUsuarioDoc(String codigoCliente) {
		boolean buscar = true;
		try {
			if (!iu.buscarUsuarioDoc(codigoCliente))
				buscar = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			buscar = false;
		}
		return buscar;
	}

	public boolean updateUsuario(Usuario usuario) {
		boolean update = true;
		try {
			if (!iu.actualizarUsuario(usuario))
				update = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			update = false;
		}
		return update;
	}

	public boolean delUsuario(String codigoUsuario) {
		boolean del = true;
		try {
			if (!iu.eliminarUsuario(codigoUsuario))
				del = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			del = false;
		}
		return del;
	}

	public boolean addUsuario(Usuario usuario) {
		boolean add = true;
		try {
			if (!iu.crearUsuario(usuario))
				add = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			add = false;
		}
		return add;
	}
	
	public List loadAll() {
		List usuarios = null;
		try {
			usuarios = iu.listarUsuarios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}
}
