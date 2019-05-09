package Controlador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Interfaces.IValoracionCliente;
import Modelo.NotFoundException;
import Modelo.Valoracion_cliente;
import Modelo.Valoracion_clienteDao;

public class Impl_ValoracionCliente extends UnicastRemoteObject implements IValoracionCliente {
	
	public Impl_ValoracionCliente() throws RemoteException {
		super();
	}

	public Valoracion_cliente buscarValoracionCliente(int idCliente) throws RemoteException {
		// crear el value object a retornar
		Valoracion_cliente valoracion = new Valoracion_cliente(idCliente);
		// crear los DAO a manipular
		Valoracion_clienteDao valoracionDao = new Valoracion_clienteDao();

		try {
			valoracionDao.load(getConnection(), valoracion);
		} catch (SQLException e) {
			valoracion = null;
			e.printStackTrace();
		} catch (NotFoundException e) {
			valoracion = null;
			e.printStackTrace();
		}
		return valoracion;
	}

	public boolean actualizarValoracionCliente(Valoracion_cliente valoracion) throws RemoteException {
		boolean update = true;
		// crear los DAO a manipular
		Valoracion_clienteDao valoracionDao = new Valoracion_clienteDao();

		try {
			valoracionDao.save(getConnection(), valoracion);
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

	public boolean eliminarValoracionCliente(int idCliente) throws RemoteException {
		boolean del = true;
		// crear el value object a eliminar
		Valoracion_cliente valoracion = new Valoracion_cliente(idCliente);
		// crear los DAO a manipular
		Valoracion_clienteDao valoracionDao = new Valoracion_clienteDao();

		try {
			valoracionDao.delete(getConnection(), valoracion);
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

	public boolean crearValoracionCliente(Valoracion_cliente valoracion) throws RemoteException {
		boolean add = true;

		// crear el DAO a manipular
		Valoracion_clienteDao valoracionDao = new Valoracion_clienteDao();

		try {
			valoracionDao.create(getConnection(), valoracion);
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
