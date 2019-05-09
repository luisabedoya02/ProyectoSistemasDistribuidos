
package Modelo;

import java.sql.*;
import java.util.*;
import java.math.*;

public class UsuarioDao {

	public Usuario createValueObject() {
		return new Usuario();
	}

	public Usuario getObject(Connection conn, int id) throws NotFoundException, SQLException {

		Usuario valueObject = createValueObject();
		valueObject.setId(id);
		load(conn, valueObject);
		return valueObject;
	}

	public void load(Connection conn, Usuario valueObject) throws NotFoundException, SQLException {

		String sql = "SELECT * FROM usuario WHERE (documento = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, valueObject.getDocumento());

			singleQuery(conn, stmt, valueObject);

		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public List loadAll(Connection conn) throws SQLException {

		String sql = "SELECT * FROM usuario ORDER BY id ASC ";
		List searchResults = listQuery(conn, conn.prepareStatement(sql));

		return searchResults;
	}

	public synchronized void create(Connection conn, Usuario valueObject) throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			sql = "INSERT INTO usuario ( id, documento, nombre_completo, " + "telefono, email, nombre_rol, "
					+ "nombre_restaurante) VALUES (?, ?, ?, ?, ?, ?, ?) ";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, valueObject.getId());
			stmt.setString(2, valueObject.getDocumento());
			stmt.setString(3, valueObject.getNombre_completo());
			stmt.setString(4, valueObject.getTelefono());					
			stmt.setString(5, valueObject.getNombre_restaurante());
			stmt.setString(6, valueObject.getNombre_rol());

			int rowcount = databaseUpdate(conn, stmt);
			if (rowcount != 1) {
				// System.out.println("PrimaryKey Error when updating DB!");
				throw new SQLException("PrimaryKey Error when updating DB!");
			}

		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

	public void save(Connection conn, Usuario valueObject) throws NotFoundException, SQLException {

		String sql = "UPDATE usuario SET documento = ?, nombre_completo = ?, telefono = ?, "
				+ "email = ?, nombre_rol = ? ,nombre_restaurante = ?  WHERE (id = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, valueObject.getDocumento());
			stmt.setString(2, valueObject.getNombre_completo());
			stmt.setString(3, valueObject.getTelefono());
			stmt.setString(4, valueObject.getEmail());
			stmt.setString(5, valueObject.getNombre_rol());
			stmt.setString(6, valueObject.getNombre_restaurante());
			
			stmt.setInt(7, valueObject.getId());

			int rowcount = databaseUpdate(conn, stmt);
			if (rowcount == 0) {
				// System.out.println("Object could not be saved! (PrimaryKey not found)");
				throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were
				// affected!)");
				throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public void delete(Connection conn, Usuario valueObject) throws NotFoundException, SQLException {

		String sql = "DELETE FROM usuario WHERE (documento = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, valueObject.getDocumento());

			int rowcount = databaseUpdate(conn, stmt);
			if (rowcount == 0) {
				// System.out.println("Object could not be deleted (PrimaryKey not found)");
				throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were
				// deleted!)");
				throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public void deleteAll(Connection conn) throws SQLException {

		String sql = "DELETE FROM usuario";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			int rowcount = databaseUpdate(conn, stmt);
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public int countAll(Connection conn) throws SQLException {

		String sql = "SELECT count(*) FROM usuario";
		PreparedStatement stmt = null;
		ResultSet result = null;
		int allRows = 0;

		try {
			stmt = conn.prepareStatement(sql);
			result = stmt.executeQuery();

			if (result.next())
				allRows = result.getInt(1);
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}
		return allRows;
	}

	public List searchMatching(Connection conn, Usuario valueObject) throws SQLException {

		List searchResults;

		boolean first = true;
		StringBuffer sql = new StringBuffer("SELECT * FROM usuario WHERE 1=1 ");

		if (valueObject.getId() != 0) {
			if (first) {
				first = false;
			}
			sql.append("AND id = ").append(valueObject.getId()).append(" ");
		}

		if (valueObject.getDocumento() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND documento LIKE '").append(valueObject.getDocumento()).append("%' ");
		}

		if (valueObject.getNombre_completo() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND nombre_completo LIKE '").append(valueObject.getNombre_completo()).append("%' ");
		}

		if (valueObject.getTelefono() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND telefono LIKE '").append(valueObject.getTelefono()).append("%' ");
		}

		if (valueObject.getEmail() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND email LIKE '").append(valueObject.getEmail()).append("%' ");
		}
		if (valueObject.getNombre_rol() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND nombre_rol LIKE '").append(valueObject.getEmail()).append("%' ");
		}
		if (valueObject.getNombre_restaurante() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND nombre_restaurante LIKE '").append(valueObject.getEmail()).append("%' ");
		}

		

		sql.append("ORDER BY id ASC ");

		// Prevent accidential full table results.
		// Use loadAll if all rows must be returned.
		if (first)
			searchResults = new ArrayList();
		else
			searchResults = listQuery(conn, conn.prepareStatement(sql.toString()));

		return searchResults;
	}

	public String getDaogenVersion() {
		return "DaoGen version 2.4.1";
	}

	protected int databaseUpdate(Connection conn, PreparedStatement stmt) throws SQLException {

		int result = stmt.executeUpdate();

		return result;
	}

	protected void singleQuery(Connection conn, PreparedStatement stmt, Usuario valueObject)
			throws NotFoundException, SQLException {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			if (result.next()) {

				valueObject.setId(result.getInt("id"));
				valueObject.setDocumento(result.getString("documento"));
				valueObject.setNombre_completo(result.getString("nombre_completo"));
				valueObject.setTelefono(result.getString("telefono"));
				valueObject.setEmail(result.getString("email"));
				valueObject.setNombre_rol(result.getString("nombre_rol"));
				valueObject.setNombre_restaurante(result.getString("nombre_restaurante"));
				
			} else {
				// System.out.println("Usuario Object Not Found!");
				throw new NotFoundException("Usuario Object Not Found!");
			}
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}
	}

	protected List listQuery(Connection conn, PreparedStatement stmt) throws SQLException {

		ArrayList searchResults = new ArrayList();
		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			while (result.next()) {
				Usuario temp = createValueObject();

				temp.setId(result.getInt("id"));
				temp.setDocumento(result.getString("documento"));
				temp.setNombre_completo(result.getString("nombre_completo"));
				temp.setTelefono(result.getString("telefono"));
				temp.setEmail(result.getString("email"));
				temp.setNombre_rol(result.getString("nombre_rol"));
				temp.setNombre_restaurante(result.getString("nombre_restaurante"));

				searchResults.add(temp);
			}

		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}

		return (List) searchResults;
	}

}