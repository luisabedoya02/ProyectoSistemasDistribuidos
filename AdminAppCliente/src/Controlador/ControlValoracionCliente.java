package Controlador;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Interfaces.IValoracionCliente;
import Modelo.Valoracion_cliente;

public class ControlValoracionCliente {
	private Valoracion_cliente vc = null;;
	private IValoracionCliente ivc = null;
	
	public ControlValoracionCliente() throws RemoteException, NotBoundException{
		Registry r = LocateRegistry.getRegistry(10000);
		ivc = (IValoracionCliente) r.lookup("Valoracion_cliente");
		
        }
	
	public Valoracion_cliente searchValoracionCliente(int idCliente){
		try{
			vc=ivc.buscarValoracionCliente(idCliente);
		}
		catch(RemoteException e){
			e.printStackTrace();
			vc=null;
		}
		return vc;
	}
	
	public boolean updateValoracionCliente(Valoracion_cliente valoracion){
		boolean update=true;
		try{
			if(!ivc.actualizarValoracionCliente(valoracion))
				update=false;
		}
		catch(RemoteException e){
			e.printStackTrace();
			update=false;
		}
		return update;
	}
	public boolean delValoracionCliente(int idCliente){
		boolean del=true;
		try{
			if(!ivc.eliminarValoracionCliente(idCliente))
				del=false;
		}
		catch(RemoteException e){
			e.printStackTrace();
			del=false;
		}
		return del;
	}
	public boolean addValoracionCliente(Valoracion_cliente valoracion){
		boolean add=true;
		try{
			if(!ivc.crearValoracionCliente(valoracion))
				add=false;
		}
		catch(RemoteException e){
			e.printStackTrace();
			add=false;
		}
		return add;
	}
}
