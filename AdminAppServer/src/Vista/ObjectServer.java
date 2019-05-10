package Vista;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import Controlador.*;

public class ObjectServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException {

		Impl_Cliente implCliente = new Impl_Cliente();
		Impl_Mesa implMesa = new Impl_Mesa();
		Impl_Pedido implPedido = new Impl_Pedido();
		Impl_PedidoDetalle implPedidoDetalle = new Impl_PedidoDetalle();
		Impl_Producto implProducto = new Impl_Producto();
		Impl_ValoracionCliente implValoracionCliente = new Impl_ValoracionCliente();
		
		
		Registry r = LocateRegistry.createRegistry(10000);
		r.rebind("Cliente", implCliente);
		r.rebind("Mesa", implMesa);
		r.rebind("Pedido", implPedido);
		r.rebind("PedidoDetalle", implPedidoDetalle);
		r.rebind("Producto", implProducto);
		r.rebind("ValoracionCliente", implValoracionCliente);

		System.out.println("Objetos Publicado");

	}

}
