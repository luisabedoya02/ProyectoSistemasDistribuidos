package Modelo;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class Menu implements Cloneable, Serializable {

	private int id;
	private String nombre_restaurante;

	public Menu() {

	}

	public Menu(int idIn) {
		this.id = idIn;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int idIn) {
		this.id = idIn;
	}

	public String getNombre_restaurante() {
		return this.nombre_restaurante;
	}

	public void setNombre_restaurante(String nombre_restaurante) {
		this.nombre_restaurante = nombre_restaurante;
	}

	public void setAll(int idIn, String nombre_restaurante) {
		this.id = idIn;
		this.nombre_restaurante = nombre_restaurante;
	}

	public boolean hasEqualMapping(Menu valueObject) {

		if (valueObject.getId() != this.id) {
			return (false);
		}
		if (valueObject.getNombre_restaurante() != this.nombre_restaurante) {
			return (false);
		}

		return true;
	}

	public String toString() {
		StringBuffer out = new StringBuffer(this.getDaogenVersion());
		out.append("\nclass Menu, mapping to table menu\n");
		out.append("Persistent attributes: \n");
		out.append("id = " + this.id + "\n");
		out.append("nombre_restaurante = " + this.nombre_restaurante + "\n");
		return out.toString();
	}

	public Object clone() {
		Menu cloned = new Menu();

		cloned.setId(this.id);
		cloned.setNombre_restaurante(nombre_restaurante);;
		return cloned;
	}

	public String getDaogenVersion() {
		return "DaoGen version 2.4.1";
	}

}