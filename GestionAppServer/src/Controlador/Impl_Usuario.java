package Controlador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import Interfaces.IUsuario;
import Modelo.Usuario;
import Modelo.UsuarioDao;
import Modelo.NotFoundException;

@SuppressWarnings("serial")
public class Impl_Usuario extends UnicastRemoteObject implements IUsuario {

	public Impl_Usuario() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario buscarUsuario(String documento) throws RemoteException {
		// crear el value object a retornar
		Usuario usuario = new Usuario(documento);
		// crear los DAO a manipular
		UsuarioDao usuarioDao = new UsuarioDao();

		try {
			usuarioDao.load(getConnection(), usuario);
		} catch (SQLException e) {
			usuario = null;
			e.printStackTrace();
		} catch (NotFoundException e) {
			usuario = null;
			e.printStackTrace();
		}
		return usuario;
	}
	
	@Override
	public boolean buscarUsuarioDoc(String documento) throws RemoteException {
		boolean buscar = true;

		Usuario usuario = new Usuario(documento);
		// crear los DAO a manipular
		UsuarioDao clienteDao = new UsuarioDao();

		try {
			clienteDao.load(getConnection(), usuario);
		} catch (SQLException e) {
			usuario = null;
			e.printStackTrace();
		} catch (NotFoundException e) {
			usuario = null;
			e.printStackTrace();
		}
		return buscar;

	}
	
	

	public List<Usuario> listarUsuarios() throws RemoteException {
		List l = null;
		// crear los DAO a manipular
		UsuarioDao usuarioDao = new UsuarioDao();

		try {
			l = usuarioDao.loadAll(getConnection());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public boolean actualizarUsuario(Usuario usuario) throws RemoteException {
		boolean update = true;
		// crear los DAO a manipular
		UsuarioDao usuarioDao = new UsuarioDao();

		try {
			usuarioDao.save(getConnection(), usuario);
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

	public boolean eliminarUsuario(String documento) throws RemoteException {
		boolean del = true;
		// crear el value object a eliminar
		Usuario usuario = new Usuario(documento);
		// crear los DAO a manipular
		UsuarioDao usuarioDao = new UsuarioDao();

		try {
			usuarioDao.delete(getConnection(), usuario);
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

	public boolean crearUsuario(Usuario usuario) throws RemoteException {
		boolean add = true;

		// crear el DAO a manipular
		UsuarioDao usuarioDao = new UsuarioDao();

		try {
			usuarioDao.create(getConnection(), usuario);
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
