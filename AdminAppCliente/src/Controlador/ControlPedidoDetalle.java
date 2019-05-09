package Controlador;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import Interfaces.IPedidoDetalle;
import Modelo.Pedido_detalle;

public class ControlPedidoDetalle {
	private Pedido_detalle c = null;;
	private IPedidoDetalle ipd = null;

	public ControlPedidoDetalle() throws RemoteException, NotBoundException {
		Registry r = LocateRegistry.getRegistry(10000);
		ipd = (IPedidoDetalle) r.lookup("PedidoDetalle");
	}

	public Pedido_detalle searchPedidoDetalle(int codigoPedidoDetalle) {
		try {
			c = ipd.buscarPedidoDetalle(codigoPedidoDetalle);
		} catch (RemoteException e) {
			e.printStackTrace();
			c = null;
		}
		return c;
	}

	public boolean updatePedidoDetalle(Pedido_detalle pedido_detalle) {
		boolean update = true;
		try {
			if (!ipd.actualizarPedidoDetalle(pedido_detalle))
				update = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			update = false;
		}
		return update;
	}

	public boolean delPedidoDetalle(int codigoPedidoDetalle) {
		boolean del = true;
		try {
			if (!ipd.eliminarPedidoDetalle(codigoPedidoDetalle))
				del = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			del = false;
		}
		return del;
	}

	public boolean addPedidoDetalle(Pedido_detalle pedido_detalle) {
		boolean add = true;
		try {
			if (!ipd.crearPedidoDetalle(pedido_detalle))
				add = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			add = false;
		}
		return add;
	}

	public List loadAll() {
		List pedidos_detalles = null;
		try {
			pedidos_detalles = ipd.listarPedidosDetalles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedidos_detalles;
	}
	
	public List loadAllId(int idPedido) {
		List pedidos_detalles = null;
		try {
			pedidos_detalles = ipd.listarPedidosDetallesId(idPedido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedidos_detalles;
	}
}
