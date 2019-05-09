package Controlador;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import Interfaces.IProducto;
import Modelo.Producto;

public class ControlProducto {
	private Producto p = null;;
	private IProducto ip = null;

	public ControlProducto() throws RemoteException, NotBoundException {
		Registry r = LocateRegistry.getRegistry(10000);
		ip = (IProducto) r.lookup("Producto");
	}

	public Producto searchProducto(int codigoProducto) {
		try {
			p = ip.buscarProducto(codigoProducto);
		} catch (RemoteException e) {
			e.printStackTrace();
			p = null;
		}
		return p;
	}

	public boolean updateProducto(Producto producto) {
		boolean update = true;
		try {
			if (!ip.actualizarProducto(producto))
				update = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			update = false;
		}
		return update;
	}

	public boolean delProducto(int codigoProducto) {
		boolean del = true;
		try {
			if (!ip.eliminarProducto(codigoProducto))
				del = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			del = false;
		}
		return del;
	}

	public boolean addProducto(Producto producto) {
		boolean add = true;
		try {
			if (!ip.crearProducto(producto))
				add = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			add = false;
		}
		return add;
	}
	
	public List loadAll() {
		List productos = null;
		try {
			productos = ip.listarProductos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productos;
	}
}

