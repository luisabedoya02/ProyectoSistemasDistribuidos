package Controlador;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import Interfaces.ICliente;
import Modelo.Cliente;

public class ControlCliente {
	private Cliente c = null;;
	private ICliente ic = null;

	public ControlCliente() throws RemoteException, NotBoundException {
		Registry r = LocateRegistry.getRegistry(10000);
		ic = (ICliente) r.lookup("Cliente");
	}

	public Cliente searchCliente(String codigoCliente) {
		try {
			c = ic.buscarCliente(codigoCliente);
		} catch (RemoteException e) {
			e.printStackTrace();
			c = null;
		}
		return c;
	}
	
	public boolean searchClienteDoc(String codigoCliente) {
		boolean buscar = true;
		try {
			if (!ic.buscarClienteDoc(codigoCliente))
				buscar = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			buscar = false;
		}
		return buscar;
	}
	
	public Cliente searchCliente2(int idCliente) {
		try {
			c = ic.buscarClienteId(idCliente);
		} catch (RemoteException e) {
			e.printStackTrace();
			c = null;
		}
		return c;
	}


	public boolean updateCliente(Cliente cliente) {
		boolean update = true;
		try {
			if (!ic.actualizarCliente(cliente))
				update = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			update = false;
		}
		return update;
	}
	
	public boolean updateValoracionCliente(Cliente cliente) {
		boolean update=true;
		try{
			if(!ic.actualizarValoracion(cliente))
				update=false;
		}
		catch(RemoteException e){
			e.printStackTrace();
			update=false;
		}
		return update;
	}

	public boolean delCliente(String codigoCliente) {
		boolean del = true;
		try {
			if (!ic.eliminarCliente(codigoCliente))
				del = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			del = false;
		}
		return del;
	}

	public boolean addCliente(Cliente cliente) {
		boolean add = true;
		try {
			if (!ic.crearCliente(cliente))
				add = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			add = false;
		}
		return add;
	}

	public List loadAll() {
		List clientes = null;
		try {
			clientes = ic.listarClientes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
}
