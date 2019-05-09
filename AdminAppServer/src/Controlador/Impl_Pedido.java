package Controlador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import Interfaces.ICliente;
import Interfaces.IPedido;
import Interfaces.IProducto;
import Modelo.Cliente;
import Modelo.ClienteDao;
import Modelo.NotFoundException;
import Modelo.Pedido;
import Modelo.PedidoDao;
import Modelo.Producto;
import Modelo.ProductoDao;

public class Impl_Pedido extends UnicastRemoteObject implements IPedido {
	
	public Impl_Pedido() throws RemoteException {
		super();
	}

	public Pedido buscarPedido(int idCliente, java.util.Date fecha) throws RemoteException {
		// crear el value object a retornar
		Pedido pedido = new Pedido(idCliente, fecha);
		// crear los DAO a manipular
		PedidoDao pedidoDao = new PedidoDao();

		try {
			pedidoDao.load(getConnection(), pedido);
		} catch (SQLException e) {
			pedido = null;
			e.printStackTrace();
		} catch (NotFoundException e) {
			pedido = null;
			e.printStackTrace();
		}
		return pedido;
	}

	
	public Pedido buscarPedidoId(int id) throws RemoteException {
		// crear el value object a retornar
				Pedido pedido = new Pedido(id);
				// crear los DAO a manipular
				PedidoDao pedidoDao = new PedidoDao();

				try {
					pedidoDao.loadId(getConnection(), pedido);
				} catch (SQLException e) {
					pedido = null;
					e.printStackTrace();
				} catch (NotFoundException e) {
					pedido = null;
					e.printStackTrace();
				}
				return pedido;

	}
	public List<Pedido> listarPedidos() throws RemoteException {
		List l = null;
		// crear los DAO a manipular
		PedidoDao pedidoDao = new PedidoDao();

		try {
			l = pedidoDao.loadAll(getConnection());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public boolean actualizarPedido(Pedido pedido) throws RemoteException {
		boolean update = true;
		// crear los DAO a manipular
		PedidoDao pedidoDao = new PedidoDao();

		try {
			pedidoDao.save(getConnection(), pedido);
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

	public boolean eliminarPedido(int id) throws RemoteException {
		boolean del = true;
		// crear el value object a eliminar
		Pedido pedido = new Pedido();
		// crear los DAO a manipular
		PedidoDao pedidoDao = new PedidoDao();

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

	public boolean crearPedido(Pedido pedido) throws RemoteException {
		boolean add = true;

		// crear el DAO a manipular
		PedidoDao pedidoDao = new PedidoDao();

		try {
			pedidoDao.create(getConnection(), pedido);
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

}
