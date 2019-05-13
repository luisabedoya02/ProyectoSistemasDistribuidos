package Modelo;

import java.sql.*;
import java.util.*;
import java.math.*;

public class MenuDao {

	public Menu createValueObject() {
		return new Menu();
	}

	public Menu getObject(Connection conn, int id) throws NotFoundException, SQLException {

		Menu valueObject = createValueObject();
		valueObject.setId(id);
		load(conn, valueObject);
		return valueObject;
	}
	

	public void load(Connection conn, Menu valueObject) throws NotFoundException, SQLException {

		String sql = "SELECT * FROM menu WHERE (id = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, valueObject.getId());

			singleQuery(conn, stmt, valueObject);

		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public List loadAll(Connection conn) throws SQLException {
		String sql = "SELECT * FROM menu ORDER BY id ASC ";
		List searchResults = listQuery(conn, conn.prepareStatement(sql));

		return searchResults;
	}

	public synchronized void create(Connection conn, Menu valueObject) throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			sql = "INSERT INTO menu ( id, nombre_restaurante) VALUES (?, ?) ";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, valueObject.getId());
			stmt.setString(2, valueObject.getNombre_restaurante());

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

	public void save(Connection conn, Menu valueObject) throws NotFoundException, SQLException {

		String sql = "UPDATE menu SET nombre_restaurante = ? WHERE (id = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, valueObject.getNombre_restaurante());

			stmt.setInt(2, valueObject.getId());

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

	public void delete(Connection conn, Menu valueObject) throws NotFoundException, SQLException {

		String sql = "DELETE FROM menu WHERE (id = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, valueObject.getId());

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

		String sql = "DELETE FROM menu";
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

		String sql = "SELECT id FROM menu ORDER BY id desc limit 1";
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

	public List searchMatching(Connection conn, Menu valueObject) throws SQLException {

		List searchResults;

		boolean first = true;
		StringBuffer sql = new StringBuffer("SELECT * FROM menu WHERE 1=1 ");

		if (valueObject.getId() != 0) {
			if (first) {
				first = false;
			}
			sql.append("AND id = ").append(valueObject.getId()).append(" ");
		}

		if (valueObject.getNombre_restaurante() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND nombre_restaurante LIKE '").append(valueObject.getNombre_restaurante()).append("%' ");
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

	protected void singleQuery(Connection conn, PreparedStatement stmt, Menu valueObject)
			throws NotFoundException, SQLException {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			if (result.next()) {

				valueObject.setId(result.getInt("id"));
				valueObject.setNombre_restaurante(result.getString("nombre_restaurante"));
			} else {
				 System.out.println("Menu no encontrado");
				
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
				Menu temp = createValueObject();

				temp.setId(result.getInt("id"));
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