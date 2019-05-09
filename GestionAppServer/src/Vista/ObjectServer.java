package Vista;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import Controlador.*;

public class ObjectServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException {

		Impl_Menu implMenu = new Impl_Menu();
		Impl_Mesa implMesa = new Impl_Mesa();
		Impl_Producto implProducto = new Impl_Producto();
		Impl_Usuario implUsuario = new Impl_Usuario();
		
		Registry r = LocateRegistry.createRegistry(10000);
		r.rebind("Menu", implMenu);
		r.rebind("Mesa", implMesa);
		r.rebind("Usuario", implUsuario);
		r.rebind("Producto", implProducto);

		System.out.println("Objeto Publicado");

	}

}
