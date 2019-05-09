package Controlador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import Interfaces.IMenu;
import Modelo.Menu;
import Modelo.MenuDao;
import Modelo.NotFoundException;

@SuppressWarnings("serial")
public class Impl_Menu extends UnicastRemoteObject implements IMenu {

	public Impl_Menu() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public Menu buscarMenu(int codigoMenu) throws RemoteException {
		// crear el value object a retornar
		Menu menu = new Menu(codigoMenu);
		// crear los DAO a manipular
		MenuDao menuDao = new MenuDao();

		try {
			menuDao.load(getConnection(), menu);
		} catch (SQLException e) {
			menu = null;
			e.printStackTrace();
		} catch (NotFoundException e) {
			menu = null;
			e.printStackTrace();
		}
		return menu;
	}

	public List<Menu> listarMenus() throws RemoteException {
		List l = null;
		// crear los DAO a manipular
		MenuDao menuDao = new MenuDao();

		try {
			l = menuDao.loadAll(getConnection());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public boolean actualizarMenu(Menu menu) throws RemoteException {
		boolean update = true;
		// crear los DAO a manipular
		MenuDao menuDao = new MenuDao();

		try {
			menuDao.save(getConnection(), menu);
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

	public boolean eliminarMenu(int codigoMenu) throws RemoteException {
		boolean del = true;
		// crear el value object a eliminar
		Menu menu = new Menu(codigoMenu);
		// crear los DAO a manipular
		MenuDao menuDao = new MenuDao();

		try {
			menuDao.delete(getConnection(), menu);
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

	public boolean crearMenu(Menu menu) throws RemoteException {
		boolean add = true;

		// crear el DAO a manipular
		MenuDao menuDao = new MenuDao();

		try {
			menuDao.create(getConnection(), menu);
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
