package Controlador;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import Interfaces.IMesa;
import Modelo.Mesa;

public class ControlMesa {
	private Mesa m = null;;
	private IMesa im = null;

	public ControlMesa() throws RemoteException, NotBoundException {
		Registry r = LocateRegistry.getRegistry(10000);
		im = (IMesa) r.lookup("Mesa");
	}

	
	public Mesa searchMesa(String codigoMesa) {
		try {
			m = im.buscarMesa(codigoMesa);
		} catch (RemoteException e) {
			e.printStackTrace();
			m = null;
		}
		return m;
	}

	public boolean updateMesa(Mesa mesa) {
		boolean update = true;
		try {
			if (!im.actualizarMesa(mesa))
				update = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			update = false;
		}
		return update;
	}

	public boolean delMesa(String codigoMesa) {
		boolean del = true;
		try {
			if (!im.eliminarMesa(codigoMesa))
				del = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			del = false;
		}
		return del;
	}

	public boolean addMesa(Mesa mesa) {
		boolean add = true;
		try {
			if (!im.crearMesa(mesa))
				add = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			add = false;
		}
		return add;
	}
	
	public List loadAll() {
		List mesas = null;
		try {
			mesas = im.listarMesas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mesas;
	}
}
