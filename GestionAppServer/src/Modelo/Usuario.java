package Modelo;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class Usuario implements Cloneable, Serializable {

	private int id;
	private String documento;
	private String nombre_completo;
	private String telefono;
	private String email;
	private String nombre_restaurante;
	private String nombre_rol;

	public Usuario() {

	}

	public Usuario(String documento) {

		this.documento = documento;

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

	public String getNombre_restaurante() {
		return nombre_restaurante;
	}

	public void setNombre_restaurante(String nombre_restaurante) {
		this.nombre_restaurante = nombre_restaurante;
	}

	public String getNombre_rol() {
		return nombre_rol;
	}

	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}

	public void setAll(int idIn, String documentoIn, String nombre_completoIn, String telefonoIn, String emailIn,
			String nombre_restaurante, String nombre_rol) {
		this.id = idIn;
		this.documento = documentoIn;
		this.nombre_completo = nombre_completoIn;
		this.telefono = telefonoIn;
		this.email = emailIn;
		this.nombre_restaurante = nombre_restaurante;
		this.nombre_rol = nombre_rol;
	}

	public boolean hasEqualMapping(Usuario valueObject) {

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
		if (this.nombre_restaurante == null) {
			if (valueObject.getNombre_restaurante() != null)
				return (false);
		} else if (!this.nombre_restaurante.equals(valueObject.getNombre_restaurante())) {
			return (false);
		}
		if (this.nombre_rol == null) {
			if (valueObject.getNombre_rol() != null)
				return (false);
		} else if (!this.nombre_rol.equals(valueObject.getNombre_rol())) {
			return (false);
		}

		return true;
	}

	public String toString() {
		StringBuffer out = new StringBuffer(this.getDaogenVersion());
		out.append("\nclass Usuario, mapping to table usuario\n");
		out.append("Persistent attributes: \n");
		out.append("id = " + this.id + "\n");
		out.append("documento = " + this.documento + "\n");
		out.append("nombre_completo = " + this.nombre_completo + "\n");
		out.append("telefono = " + this.telefono + "\n");
		out.append("email = " + this.email + "\n");
		out.append("nombre_restaurante = " + this.nombre_restaurante + "\n");
		out.append("nombre_rol = " + this.nombre_rol + "\n");
		return out.toString();
	}

	public Object clone() {
		Usuario cloned = new Usuario();

		cloned.setId(this.id);
		if (this.documento != null)
			cloned.setDocumento(new String(this.documento));
		if (this.nombre_completo != null)
			cloned.setNombre_completo(new String(this.nombre_completo));
		if (this.telefono != null)
			cloned.setTelefono(new String(this.telefono));
		if (this.email != null)
			cloned.setEmail(new String(this.email));
		if (this.nombre_restaurante != null)
			cloned.setEmail(new String(this.nombre_restaurante));
		if (this.nombre_rol != null)
			cloned.setEmail(new String(this.nombre_rol));

		return cloned;
	}

	public String getDaogenVersion() {
		return "DaoGen version 2.4.1";
	}

}
