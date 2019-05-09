package Controlador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import Interfaces.IPedidoDetalle;
import Modelo.NotFoundException;
import Modelo.Pedido;
import Modelo.PedidoDao;
import Modelo.Pedido_detalle;
import Modelo.Pedido_detalleDao;
import Modelo.Producto;
import Modelo.ProductoDao;

public class Impl_PedidoDetalle extends UnicastRemoteObject implements IPedidoDetalle{

	public Impl_PedidoDetalle() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Pedido_detalle buscarPedidoDetalle(int idPedido) throws RemoteException {
		// crear el value object a retornar
		Pedido_detalle pedidoDetalle = new Pedido_detalle(idPedido);
		// crear los DAO a manipular
		Pedido_detalleDao pedidoDetalleDao = new Pedido_detalleDao();

		try {
			pedidoDetalleDao.load(getConnection(), pedidoDetalle);
		} catch (SQLException e) {
			pedidoDetalle = null;
			e.printStackTrace();
		} catch (NotFoundException e) {
			pedidoDetalle = null;
			e.printStackTrace();
		}
		return pedidoDetalle;
	}

	@Override
	public boolean actualizarPedidoDetalle(Pedido_detalle pedidoDetalle) throws RemoteException {
		boolean update = true;
		// crear los DAO a manipular
		Pedido_detalleDao pedidoDetalleDao = new Pedido_detalleDao();

		try {
			pedidoDetalleDao.save(getConnection(), pedidoDetalle);
		} catch (SQLException e) {
			update = false;
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			update = false;
			e.printStackTrace();

		}
		return update;
	}

	@Override
	public boolean eliminarPedidoDetalle(int idPedido) throws RemoteException {
		boolean del = true;
		// crear el value object a eliminar
		Pedido_detalle pedido = new Pedido_detalle(idPedido);
		// crear los DAO a manipular
		Pedido_detalleDao pedidoDao = new Pedido_detalleDao();

		try {
			pedidoDao.delete(getConnection(), pedido);
		} catch (SQLException e) {
			del = false;
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			del = false;
			e.printStackTrace();
		}
		return del;
	}

	@Override
	public boolean crearPedidoDetalle(Pedido_detalle pedidoDetalle) throws RemoteException {
		boolean add = true;

		// crear el DAO a manipular
		Pedido_detalleDao pedido_detalleDao = new Pedido_detalleDao();

		try {
			pedido_detalleDao.create(getConnection(), pedidoDetalle);
		} catch (SQLException e) {
			add = false;
			e.printStackTrace();
		}
		return add;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			// registrar la clase del driver
			Class.forName("com.mysql.jdbc.Driver");
			// obtener el objeto de conexion
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/orderFood", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}
	
	public List<Pedido_detalle> listarPedidosDetalles() throws RemoteException {
		List l = null;
		// crear los DAO a manipular
		Pedido_detalleDao pedidoDetalleDao = new Pedido_detalleDao();

		try {
			l = pedidoDetalleDao.loadAll(getConnection());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	public List<Pedido_detalle> listarPedidosDetallesId(int idPedido) throws RemoteException {
		Pedido_detalle pedidoDetalle = new Pedido_detalle(idPedido);
		List l = null;
		// crear los DAO a manipular
		Pedido_detalleDao pedidoDetalleDao = new Pedido_detalleDao();

		try {
			try {
				System.out.println("IMPL " + pedidoDetalle.getId_pedido() );
				l = pedidoDetalleDao.loadListaId(getConnection(), pedidoDetalle);
				
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

}
