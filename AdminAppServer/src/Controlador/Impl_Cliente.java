package Controlador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import Interfaces.ICliente;
import Modelo.Cliente;
import Modelo.ClienteDao;
import Modelo.NotFoundException;

public class Impl_Cliente extends UnicastRemoteObject implements ICliente {
	
	public Impl_Cliente() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente buscarCliente(String documento) throws RemoteException {
		// crear el value object a retornar
		Cliente cliente = new Cliente(documento);
		// crear los DAO a manipular
		ClienteDao clienteDao = new ClienteDao();

		try {
			clienteDao.load(getConnection(), cliente);
		} catch (SQLException e) {
			cliente = null;
			e.printStackTrace();
		} catch (NotFoundException e) {
			cliente = null;
			e.printStackTrace();
		}
		return cliente;
	}
	
	public boolean buscarClienteDoc(String documento) throws RemoteException {
		boolean buscar = true;
		
		Cliente cliente = new Cliente(documento);
		// crear los DAO a manipular
		ClienteDao clienteDao = new ClienteDao();

		try {
			clienteDao.load(getConnection(), cliente);
		} catch (SQLException e) {
			cliente = null;
			e.printStackTrace();
		} catch (NotFoundException e) {
			cliente = null;
			e.printStackTrace();
		}
		return buscar;
	}
	

	@Override
	public Cliente buscarClienteId(int id) throws RemoteException {
		// crear el value object a retornar
				Cliente cliente = new Cliente(id);
				// crear los DAO a manipular
				ClienteDao clienteDao = new ClienteDao();

				try {
					clienteDao.loadId(getConnection(), cliente);
				} catch (SQLException e) {
					cliente = null;
					e.printStackTrace();
				} catch (NotFoundException e) {
					cliente = null;
					e.printStackTrace();
				}
				return cliente;

	}

	public List<Cliente> listarClientes() throws RemoteException {
		List l = null;
		// crear los DAO a manipular
		ClienteDao clienteDao = new ClienteDao();

		try {
			l = clienteDao.loadAll(getConnection());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public boolean actualizarCliente(Cliente cliente) throws RemoteException {
		boolean update = true;
		// crear los DAO a manipular
		ClienteDao clienteDao = new ClienteDao();

		try {
			clienteDao.save(getConnection(), cliente);
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
	
	public boolean actualizarValoracion(Cliente cliente) throws RemoteException {		
		boolean update = true;
	
		ClienteDao clienteDao = new ClienteDao();

		try {
			clienteDao.updateValoracion(getConnection(), cliente);
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

	public boolean eliminarCliente(String documento) throws RemoteException {
		boolean del = true;
		// crear el value object a eliminar
		Cliente cliente = new Cliente(documento);
		// crear los DAO a manipular
		ClienteDao clienteDao = new ClienteDao();

		try {
			clienteDao.delete(getConnection(), cliente);
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

	public boolean crearCliente(Cliente cliente) throws RemoteException {
		boolean add = true;

		// crear el DAO a manipular
		ClienteDao clienteDao = new ClienteDao();

		try {
			clienteDao.create(getConnection(), cliente);
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
