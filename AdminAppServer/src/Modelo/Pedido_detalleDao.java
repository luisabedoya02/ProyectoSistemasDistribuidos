package Modelo;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.math.*;

public class Pedido_detalleDao {

	public Pedido_detalle createValueObject() {
		return new Pedido_detalle();
	}

	public Pedido_detalle getObject(Connection conn, int id) throws NotFoundException, SQLException {

		Pedido_detalle valueObject = createValueObject();
		valueObject.setId(id);
		load(conn, valueObject);
		return valueObject;
	}

	public void load(Connection conn, Pedido_detalle valueObject) throws NotFoundException, SQLException {

		String sql = "SELECT * FROM pedido_detalle WHERE (id_pedido = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, valueObject.getId_pedido());

			singleQuery(conn, stmt, valueObject);

		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public List loadListaId(Connection conn, Pedido_detalle valueObject) throws NotFoundException, SQLException {
		PreparedStatement stmt = null;
		List searchResults;
		String sql = "SELECT * FROM pedido_detalle WHERE (id_pedido = ?) ";
		System.out.println("Servidor " + valueObject.getId_pedido());
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, valueObject.getId_pedido());
			//System.out.println("Servidor " + stmt);
			searchResults = listQuery(conn, stmt);
			
			
		} finally {
			if (stmt != null)
				stmt.close();
		}
				

		return searchResults;

	}

	public List loadAll(Connection conn) throws SQLException {

		String sql = "SELECT * FROM pedido_detalle ORDER BY id ASC ";
		List searchResults = listQuery(conn, conn.prepareStatement(sql));

		return searchResults;
	}

	public synchronized void create(Connection conn, Pedido_detalle valueObject) throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			sql = "INSERT INTO pedido_detalle ( id, id_pedido, id_producto, "
					+ "cantidad, precio, especificaciones, valoracion) VALUES (?, ?, ?, ?, ?, ?, ?) ";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, valueObject.getId());
			stmt.setInt(2, valueObject.getId_pedido());
			stmt.setInt(3, valueObject.getId_producto());
			stmt.setInt(4, valueObject.getCantidad());
			stmt.setBigDecimal(5, valueObject.getPrecio());
			stmt.setString(6, valueObject.getEspecificaciones());
			stmt.setInt(7, valueObject.getValoracion());

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

	public void save(Connection conn, Pedido_detalle valueObject) throws NotFoundException, SQLException {

		String sql = "UPDATE pedido_detalle SET id_pedido = ?, id_producto = ?, cantidad = ?, "
				+ "precio = ?, especificaciones = ?, valoracion = ? WHERE (id = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, valueObject.getId_pedido());
			stmt.setInt(2, valueObject.getId_producto());
			stmt.setInt(3, valueObject.getCantidad());
			stmt.setBigDecimal(4, valueObject.getPrecio());
			stmt.setString(5, valueObject.getEspecificaciones());
			stmt.setInt(6, valueObject.getValoracion());

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

	public void delete(Connection conn, Pedido_detalle valueObject) throws NotFoundException, SQLException {

		String sql = "DELETE FROM pedido_detalle WHERE (id_pedido = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, valueObject.getId_pedido());

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

		String sql = "DELETE FROM pedido_detalle";
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

		String sql = "SELECT count(*) FROM pedido_detalle";
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

	public List searchMatching(Connection conn, Pedido_detalle valueObject) throws SQLException {

		List searchResults;

		boolean first = true;
		StringBuffer sql = new StringBuffer("SELECT * FROM pedido_detalle WHERE 1=1 ");

		if (valueObject.getId() != 0) {
			if (first) {
				first = false;
			}
			sql.append("AND id = ").append(valueObject.getId()).append(" ");
		}

		if (valueObject.getId_pedido() != 0) {
			if (first) {
				first = false;
			}
			sql.append("AND id_pedido = ").append(valueObject.getId_pedido()).append(" ");
		}

		if (valueObject.getId_producto() != 0) {
			if (first) {
				first = false;
			}
			sql.append("AND id_producto = ").append(valueObject.getId_producto()).append(" ");
		}

		if (valueObject.getCantidad() != 0) {
			if (first) {
				first = false;
			}
			sql.append("AND cantidad = ").append(valueObject.getCantidad()).append(" ");
		}

		if (valueObject.getPrecio() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND precio = '").append(valueObject.getPrecio()).append("' ");
		}

		if (valueObject.getEspecificaciones() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND especificaciones LIKE '").append(valueObject.getEspecificaciones()).append("%' ");
		}

		if (valueObject.getValoracion() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND valoracion LIKE '").append(valueObject.getValoracion()).append("%' ");
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

	protected void singleQuery(Connection conn, PreparedStatement stmt, Pedido_detalle valueObject)
			throws NotFoundException, SQLException {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			if (result.next()) {

				valueObject.setId(result.getInt("id"));
				valueObject.setId_pedido(result.getInt("id_pedido"));
				valueObject.setId_producto(result.getInt("id_producto"));
				valueObject.setCantidad(result.getInt("cantidad"));
				valueObject.setPrecio(result.getBigDecimal("precio"));
				valueObject.setEspecificaciones(result.getString("especificaciones"));
				valueObject.setValoracion(result.getInt("valoracion"));

			} else {
				// System.out.println("Pedido_detalle Object Not Found!");
				throw new NotFoundException("Pedido_detalle Object Not Found!");
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
				Pedido_detalle temp = createValueObject();

				temp.setId(result.getInt("id"));
				temp.setId_pedido(result.getInt("id_pedido"));
				temp.setId_producto(result.getInt("id_producto"));
				temp.setCantidad(result.getInt("cantidad"));
				temp.setPrecio(result.getBigDecimal("precio"));
				temp.setEspecificaciones(result.getString("especificaciones"));
				temp.setValoracion(result.getInt("valoracion"));

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