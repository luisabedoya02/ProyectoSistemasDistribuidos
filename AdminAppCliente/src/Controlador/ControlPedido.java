package Controlador;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.util.List;

import Interfaces.IPedido;
import Modelo.Pedido;

public class ControlPedido {
	private Pedido p = null;;
	private IPedido ip = null;

	public ControlPedido() throws RemoteException, NotBoundException {
		Registry r = LocateRegistry.getRegistry(10000);
		ip = (IPedido) r.lookup("Pedido");
	}

	/*public Pedido searchPedido(String codigoCliente, Date fecha) {
		try {
			p = ip.buscarPedido(codigoCliente, fecha);
		} catch (RemoteException e) {
			e.printStackTrace();
			p = null;
		}
		return p;
	}*/
	
	public Pedido searchPedidoId(int idPedido) {
		try {
			p = ip.buscarPedidoId(idPedido);
		} catch (RemoteException e) {
			e.printStackTrace();
			p = null;
		}
		return p;
	}

	public boolean updatePedido(Pedido pedido) {
		boolean update = true;
		try {
			if (!ip.actualizarPedido(pedido))
				update = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			update = false;
		}
		return update;
	}

	public boolean delPedido(int codigoPedido) {
		boolean del = true;
		try {
			if (!ip.eliminarPedido(codigoPedido))
				del = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			del = false;
		}
		return del;
	}

	public boolean addPedido(Pedido pedido) {
		boolean add = true;
		try {
			if (!ip.crearPedido(pedido))
				add = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			add = false;
		}
		return add;
	}

	public List loadAll() {
		List pedidos = null;
		try {
			pedidos = ip.listarPedidos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedidos;
	}

}
