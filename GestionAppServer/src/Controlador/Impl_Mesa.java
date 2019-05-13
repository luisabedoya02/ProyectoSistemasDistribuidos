package Controlador;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import Interfaces.IMesa;
import Modelo.Mesa;
import Modelo.MesaDao;
import Modelo.NotFoundException;
import Modelo.Producto;
import Modelo.ProductoDao;

@SuppressWarnings("serial")
public class Impl_Mesa extends UnicastRemoteObject implements IMesa {
	
	public Impl_Mesa() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Mesa buscarMesa(String codigoMesa) throws RemoteException {
		// crear el value object a retornar
		Mesa mesa = new Mesa(codigoMesa);
		// crear los DAO a manipular
		MesaDao mesaDao = new MesaDao();
		

		try {
			mesaDao.load(getConnection(), mesa);
		} catch (SQLException e) {
			mesa = null;
			e.printStackTrace();
		} catch (NotFoundException e) {
			mesa = null;
			e.printStackTrace();
		}
		return mesa;
	}
	
	public int conteo() throws RemoteException {
		int cantidad = 0;
		// crear los DAO a manipular
		MesaDao mesaDao = new MesaDao();

		try {
			cantidad = mesaDao.countAll(getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cantidad;
	}
	

	@Override
	public boolean buscarMesaID(String id) throws RemoteException {
		boolean buscar = true;

		Mesa mesa = new Mesa(id);
		// crear los DAO a manipular
		MesaDao productoDao = new MesaDao();

		try {
			productoDao.load(getConnection(), mesa);
		} catch (SQLException e) {
			mesa = null;
			e.printStackTrace();
		} catch (NotFoundException e) {
			mesa = null;
			e.printStackTrace();
		}
		return buscar;
	}

	public List<Mesa> listarMesas() throws RemoteException {
		List l = null;
		// crear los DAO a manipular
		MesaDao mesaDao = new MesaDao();

		try {
			l = mesaDao.loadAll(getConnection());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public boolean actualizarMesa(Mesa mesa) throws RemoteException {
		boolean update = true;
		// crear los DAO a manipular
		MesaDao mesaDao = new MesaDao();

		try {
			mesaDao.save(getConnection(), mesa);
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

	public boolean eliminarMesa(String codigoMesa) throws RemoteException {
		boolean del = true;
		// crear el value object a eliminar
		Mesa mesa = new Mesa(codigoMesa);
		// crear los DAO a manipular
		MesaDao mesaDao = new MesaDao();

		try {
			mesaDao.delete(getConnection(), mesa);
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

	public boolean crearMesa(Mesa mesa) throws RemoteException {
		boolean add = true;

		// crear el DAO a manipular
		MesaDao mesaDao = new MesaDao();

		try {
			mesaDao.create(getConnection(), mesa);
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/orderfood", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}


}
