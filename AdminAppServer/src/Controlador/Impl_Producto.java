package Controlador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import Interfaces.ICliente;
import Interfaces.IProducto;
import Modelo.Cliente;
import Modelo.ClienteDao;
import Modelo.NotFoundException;
import Modelo.Producto;
import Modelo.ProductoDao;

public class Impl_Producto extends UnicastRemoteObject implements IProducto {
	
	public Impl_Producto() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public Producto buscarProducto(int id) throws RemoteException {
		// crear el value object a retornar
		Producto producto = new Producto(id);
		// crear los DAO a manipular
		ProductoDao productoDao = new ProductoDao();

		try {
			productoDao.load(getConnection(), producto);
		} catch (SQLException e) {
			producto = null;
			e.printStackTrace();
		} catch (NotFoundException e) {
			producto = null;
			e.printStackTrace();
		}
		return producto;
	}

	public List<Producto> listarProductos() throws RemoteException {
		List l = null;
		// crear los DAO a manipular
		ProductoDao productoDao = new ProductoDao();

		try {
			l = productoDao.loadAll(getConnection());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public boolean actualizarProducto(Producto producto) throws RemoteException {
		boolean update = true;
		// crear los DAO a manipular
		ProductoDao productoDao = new ProductoDao();

		try {
			productoDao.save(getConnection(), producto);
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

	public boolean eliminarProducto(int id) throws RemoteException {
		boolean del = true;
		// crear el value object a eliminar
		Producto producto = new Producto(id);
		// crear los DAO a manipular
		ProductoDao productoDao = new ProductoDao();

		try {
			productoDao.delete(getConnection(), producto);
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

	public boolean crearProducto(Producto producto) throws RemoteException {
		boolean add = true;

		// crear el DAO a manipular
		ProductoDao productoDao = new ProductoDao();

		try {
			productoDao.create(getConnection(), producto);
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
