package Modelo;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class Mesa implements Cloneable, Serializable {

	private int id;
	private String codigo_mesa;
	private String estado;
	private String nombre_restaurante;

	public Mesa() {

	}

	public Mesa(String codigo_mesa) {

		this.codigo_mesa = codigo_mesa;

	}


	public int getId() {
		return this.id;
	}

	public void setId(int idIn) {
		this.id = idIn;
	}

	public String getCodigo_mesa() {
		return this.codigo_mesa;
	}

	public void setCodigo_mesa(String codigo_mesaIn) {
		this.codigo_mesa = codigo_mesaIn;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estadoIn) {
		this.estado = estadoIn;
	}

	
	public String getNombre_restaurante() {
		return nombre_restaurante;
	}

	public void setNombre_restaurante(String nombre_restaurante) {
		this.nombre_restaurante = nombre_restaurante;
	}

	public void setAll(int idIn, String codigo_mesaIn, String estadoIn, String nombre_restaurante) {
		this.id = idIn;
		this.codigo_mesa = codigo_mesaIn;
		this.estado = estadoIn;
		this.nombre_restaurante = nombre_restaurante;
	}
	public boolean hasEqualMapping(Mesa valueObject) {

		if (valueObject.getId() != this.id) {
			return (false);
		}
		if (this.codigo_mesa == null) {
			if (valueObject.getCodigo_mesa() != null)
				return (false);
		} else if (!this.codigo_mesa.equals(valueObject.getCodigo_mesa())) {
			return (false);
		}
		if (this.estado == null) {
			if (valueObject.getEstado() != null)
				return (false);
		} else if (!this.estado.equals(valueObject.getEstado())) {
			return (false);
		}
		if (this.nombre_restaurante == null) {
			if (valueObject.getEstado() != null)
				return (false);
		} else if (!this.estado.equals(valueObject.getEstado())) {
			return (false);
		}

		return true;
	}

	public String toString() {
		StringBuffer out = new StringBuffer(this.getDaogenVersion());
		out.append("\nclass Mesa, mapping to table mesa\n");
		out.append("Persistent attributes: \n");
		out.append("id = " + this.id + "\n");
		out.append("codigo_mesa = " + this.codigo_mesa + "\n");
		out.append("estado = " + this.estado + "\n");
		out.append("nombre_restaurante = " + this.nombre_restaurante + "\n");
		return out.toString();
	}
	public Object clone() {
		Mesa cloned = new Mesa();

		cloned.setId(this.id);
		if (this.codigo_mesa != null)
			cloned.setCodigo_mesa(new String(this.codigo_mesa));
		if (this.estado != null)
			cloned.setEstado(new String(this.estado));
		if (this.nombre_restaurante != null)
			cloned.setEstado(new String(this.nombre_restaurante));
		return cloned;
	}
	public String getDaogenVersion() {
		return "DaoGen version 2.4.1";
	}

}
