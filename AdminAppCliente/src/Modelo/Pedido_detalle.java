package Modelo;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class Pedido_detalle implements Cloneable, Serializable {

	private int id;
	private int id_pedido;
	private int id_producto;
	private int cantidad;
	private BigDecimal precio;
	private String especificaciones;
	private Integer valoracion;

	public Pedido_detalle() {

	}

	public Pedido_detalle(int idPedido) {

		this.id_pedido = idPedido;

	}

	public int getId() {
		return this.id;
	}

	public void setId(int idIn) {
		this.id = idIn;
	}

	public int getId_pedido() {
		return this.id_pedido;
	}

	public void setId_pedido(int id_pedidoIn) {
		this.id_pedido = id_pedidoIn;
	}

	public int getId_producto() {
		return this.id_producto;
	}

	public void setId_producto(int id_productoIn) {
		this.id_producto = id_productoIn;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidadIn) {
		this.cantidad = cantidadIn;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precioIn) {
		this.precio = precioIn;
	}

	public String getEspecificaciones() {
		return this.especificaciones;
	}

	public void setEspecificaciones(String especificacionesIn) {
		this.especificaciones = especificacionesIn;
	}

	public Integer getValoracion() {
		return this.valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public void setAll(int idIn, int id_pedidoIn, int id_productoIn, int cantidadIn, BigDecimal precioIn,
			String especificacionesIn, int valoracion) {
		this.id = idIn;
		this.id_pedido = id_pedidoIn;
		this.id_producto = id_productoIn;
		this.cantidad = cantidadIn;
		this.precio = precioIn;
		this.especificaciones = especificacionesIn;
		this.valoracion = valoracion;
	}

	public boolean hasEqualMapping(Pedido_detalle valueObject) {

		if (valueObject.getId() != this.id) {
			return (false);
		}
		if (valueObject.getId_pedido() != this.id_pedido) {
			return (false);
		}
		if (valueObject.getId_producto() != this.id_producto) {
			return (false);
		}
		if (valueObject.getCantidad() != this.cantidad) {
			return (false);
		}
		if (this.precio == null) {
			if (valueObject.getPrecio() != null)
				return (false);
		} else if (!this.precio.equals(valueObject.getPrecio())) {
			return (false);
		}
		if (this.especificaciones == null) {
			if (valueObject.getEspecificaciones() != null)
				return (false);
		} else if (!this.especificaciones.equals(valueObject.getEspecificaciones())) {
			return (false);
		}
		if (this.valoracion == null) {
			if (valueObject.getValoracion() != null)
				return (false);
		} else if (!this.valoracion.equals(valueObject.getValoracion())) {
			return (false);
		}
		return true;
	}

	public String toString() {
		StringBuffer out = new StringBuffer(this.getDaogenVersion());
		out.append("\nclass Pedido_detalle, mapping to table pedido_detalle\n");
		out.append("Persistent attributes: \n");
		out.append("id = " + this.id + "\n");
		out.append("id_pedido = " + this.id_pedido + "\n");
		out.append("id_producto = " + this.id_producto + "\n");
		out.append("cantidad = " + this.cantidad + "\n");
		out.append("precio = " + this.precio + "\n");
		out.append("especificaciones = " + this.especificaciones + "\n");
		out.append("valoración = " + this.valoracion + "\n");
		return out.toString();
	}

	public Object clone() {
		Pedido_detalle cloned = new Pedido_detalle();

		cloned.setId(this.id);
		cloned.setId_pedido(this.id_pedido);
		cloned.setId_producto(this.id_producto);
		cloned.setCantidad(this.cantidad);
		if (this.precio != null)
			cloned.setPrecio(new BigDecimal(this.precio.doubleValue()));
		if (this.especificaciones != null)
			cloned.setEspecificaciones(new String(this.especificaciones));
		if (this.valoracion != null)
			cloned.setValoracion(new Integer(this.valoracion));

		return cloned;
	}

	public String getDaogenVersion() {
		return "DaoGen version 2.4.1";
	}

}