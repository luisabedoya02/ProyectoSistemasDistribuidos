package Modelo;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class Cliente implements Cloneable, Serializable {

	private int id;
	private String documento;
	private String nombre_completo;
	private String telefono;
	private String email;
	private Integer valoracion;

	public Cliente() {

	}

	public Cliente(String documento) {

		this.documento = documento;

	}
	public Cliente(int id) {

		this.id = id;

	}


	public int getId() {
		return this.id;
	}

	public void setId(int idIn) {
		this.id = idIn;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documentoIn) {
		this.documento = documentoIn;
	}

	public String getNombre_completo() {
		return this.nombre_completo;
	}

	public void setNombre_completo(String nombre_completoIn) {
		this.nombre_completo = nombre_completoIn;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefonoIn) {
		this.telefono = telefonoIn;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String emailIn) {
		this.email = emailIn;
	}

	public Integer getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public void setAll(int idIn, String documentoIn, String nombre_completoIn, String telefonoIn, String emailIn, Integer valoracion) {
		this.id = idIn;
		this.documento = documentoIn;
		this.nombre_completo = nombre_completoIn;
		this.telefono = telefonoIn;
		this.email = emailIn;
		this.valoracion = valoracion;
	}

	public boolean hasEqualMapping(Cliente valueObject) {

		if (valueObject.getId() != this.id) {
			return (false);
		}
		if (this.documento == null) {
			if (valueObject.getDocumento() != null)
				return (false);
		} else if (!this.documento.equals(valueObject.getDocumento())) {
			return (false);
		}
		if (this.nombre_completo == null) {
			if (valueObject.getNombre_completo() != null)
				return (false);
		} else if (!this.nombre_completo.equals(valueObject.getNombre_completo())) {
			return (false);
		}
		if (this.telefono == null) {
			if (valueObject.getTelefono() != null)
				return (false);
		} else if (!this.telefono.equals(valueObject.getTelefono())) {
			return (false);
		}
		if (this.email == null) {
			if (valueObject.getEmail() != null)
				return (false);
		} else if (!this.email.equals(valueObject.getEmail())) {
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
		out.append("\nclass Cliente, mapping to table cliente\n");
		out.append("Persistent attributes: \n");
		out.append("id = " + this.id + "\n");
		out.append("documento = " + this.documento + "\n");
		out.append("nombre_completo = " + this.nombre_completo + "\n");
		out.append("telefono = " + this.telefono + "\n");
		out.append("email = " + this.email + "\n");
		out.append("valoración = " + this.valoracion + "\n");
		return out.toString();
	}

	public Object clone() {
		Cliente cloned = new Cliente();

		cloned.setId(this.id);
		if (this.documento != null)
			cloned.setDocumento(new String(this.documento));
		if (this.nombre_completo != null)
			cloned.setNombre_completo(new String(this.nombre_completo));
		if (this.telefono != null)
			cloned.setTelefono(new String(this.telefono));
		if (this.email != null)
			cloned.setEmail(new String(this.email));
		if (this.valoracion != null)
			cloned.setValoracion(new Integer(this.valoracion));
		return cloned;
	}

	public String getDaogenVersion() {
		return "DaoGen version 2.4.1";
	}

}