package Controlador;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import Interfaces.IMenu;
import Modelo.Menu;

public class ControlMenu {
	private Menu menu = null;;
	private IMenu imenu = null;

	public ControlMenu() throws RemoteException, NotBoundException {
		Registry r = LocateRegistry.getRegistry(10000);
		imenu = (IMenu) r.lookup("Menu");
	}

	public Menu searchMenu(int codigoMenu) {
		try {
			menu = imenu.buscarMenu(codigoMenu);
		} catch (RemoteException e) {
			e.printStackTrace();
			menu = null;
		}
		return menu;
	}
	
	public int conteo() {
		int cont = 0;
		try {
			cont = imenu.conteo();
		} catch (RemoteException e) {
			System.out.println("usuarios 0");
		}
		return cont;
		
	}
	
	public boolean searchMenuId(int id) {
		boolean buscar = true;
		try {
			if (!imenu.buscarMenuID(id))
				buscar = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			buscar = false;
		}
		return buscar;
	}
	

	public boolean updateMenu(Menu menu) {
		boolean update = true;
		try {
			if (!imenu.actualizarMenu(menu))
				update = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			update = false;
		}
		return update;
	}

	public boolean delMenu(int codigoMenu) {
		boolean del = true;
		try {
			if (!imenu.eliminarMenu(codigoMenu))
				del = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			del = false;
		}
		return del;
	}

	public boolean addMenu(Menu menu) {
		boolean add = true;
		try {
			if (!imenu.crearMenu(menu))
				add = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			add = false;
		}
		return add;
	}
	
	public List loadAll() {
		List menus = null;
		try {
			menus = imenu.listarMenus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menus;
	}
}

