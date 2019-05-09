package Modelo;

import java.sql.*;
import java.util.*;
import java.io.Serializable;
import java.math.*;

public class MesaDao implements Serializable {

	public Mesa createValueObject() {
		return new Mesa();
	}

	public Mesa getObject(Connection conn, int id) throws NotFoundException, SQLException {

		Mesa valueObject = createValueObject();
		valueObject.setId(id);
		load(conn, valueObject);
		return valueObject;
	}

	public void load(Connection conn, Mesa valueObject) throws NotFoundException, SQLException {

		String sql = "SELECT * FROM mesa WHERE (codigo_mesa = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, valueObject.getCodigo_mesa());

			singleQuery(conn, stmt, valueObject);

		} finally {
			if (stmt != null)
				stmt.close();
		}
	}
	
	public List loadAll(Connection conn) throws SQLException {

		String sql = "SELECT * FROM mesa ORDER BY id ASC ";
		List searchResults = listQuery(conn, conn.prepareStatement(sql));

		return searchResults;
	}

	public synchronized void create(Connection conn, Mesa valueObject) throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			sql = "INSERT INTO mesa ( id, codigo_mesa, estado, " + "id_restaurante) VALUES (?, ?, ?, ?) ";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, valueObject.getId());
			stmt.setString(2, valueObject.getCodigo_mesa());
			stmt.setString(3, valueObject.getEstado());
			stmt.setInt(4, valueObject.getId_restaurante());

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

	public void save(Connection conn, Mesa valueObject) throws NotFoundException, SQLException {

		String sql = "UPDATE mesa SET codigo_mesa = ?, estado = ?, id_restaurante = ? WHERE (id = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, valueObject.getCodigo_mesa());
			stmt.setString(2, valueObject.getEstado());
			stmt.setInt(3, valueObject.getId_restaurante());

			stmt.setInt(4, valueObject.getId());

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

	public void delete(Connection conn, Mesa valueObject) throws NotFoundException, SQLException {

		String sql = "DELETE FROM mesa WHERE (codigo_mesa = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, valueObject.getCodigo_mesa());

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

		String sql = "DELETE FROM mesa";
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

		String sql = "SELECT count(*) FROM mesa";
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

	public List searchMatching(Connection conn, Mesa valueObject) throws SQLException {

		List searchResults;

		boolean first = true;
		StringBuffer sql = new StringBuffer("SELECT * FROM mesa WHERE 1=1 ");

		if (valueObject.getId() != 0) {
			if (first) {
				first = false;
			}
			sql.append("AND id = ").append(valueObject.getId()).append(" ");
		}

		if (valueObject.getCodigo_mesa() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND codigo_mesa LIKE '").append(valueObject.getCodigo_mesa()).append("%' ");
		}

		if (valueObject.getEstado() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND estado LIKE '").append(valueObject.getEstado()).append("%' ");
		}

		if (valueObject.getId_restaurante() != 0) {
			if (first) {
				first = false;
			}
			sql.append("AND id_restaurante = ").append(valueObject.getId_restaurante()).append(" ");
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

	protected void singleQuery(Connection conn, PreparedStatement stmt, Mesa valueObject)
			throws NotFoundException, SQLException {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			if (result.next()) {

				valueObject.setId(result.getInt("id"));
				valueObject.setCodigo_mesa(result.getString("codigo_mesa"));
				valueObject.setEstado(result.getString("estado"));
				valueObject.setId_restaurante(result.getInt("id_restaurante"));

			} else {
				// System.out.println("Mesa Object Not Found!");
				throw new NotFoundException("Mesa Object Not Found!");
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
				Mesa temp = createValueObject();

				temp.setId(result.getInt("id"));
				temp.setCodigo_mesa(result.getString("codigo_mesa"));
				temp.setEstado(result.getString("estado"));
				temp.setId_restaurante(result.getInt("id_restaurante"));

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