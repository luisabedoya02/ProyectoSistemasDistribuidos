package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ClienteDao {

    public Cliente createValueObject() {
          return new Cliente();
    }


    public Cliente getObject(Connection conn, int id) throws NotFoundException, SQLException {

          Cliente valueObject = createValueObject();
          valueObject.setId(id);
          load(conn, valueObject);
          return valueObject;
    }


    public void load(Connection conn, Cliente valueObject) throws NotFoundException, SQLException {

          String sql = "SELECT * FROM cliente WHERE (documento = ? ) "; 
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

    public void loadId(Connection conn, Cliente valueObject) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM cliente WHERE (id = ? ) "; 
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

          String sql = "SELECT * FROM cliente ORDER BY id ASC ";
          List searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }


    public synchronized void create(Connection conn, Cliente valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO cliente ( id, documento, nombre_completo, "
               + "telefono, email, valoracion) VALUES (?, ?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getId()); 
               stmt.setString(2, valueObject.getDocumento()); 
               stmt.setString(3, valueObject.getNombre_completo()); 
               stmt.setString(4, valueObject.getTelefono()); 
               stmt.setString(5, valueObject.getEmail()); 
               stmt.setInt(6, valueObject.getValoracion());

               int rowcount = databaseUpdate(conn, stmt);
               if (rowcount != 1) {
                    //System.out.println("PrimaryKey Error when updating DB!");
                    throw new SQLException("PrimaryKey Error when updating DB!");
               }

          } finally {
              if (stmt != null)
                  stmt.close();
          }


    }


 
    public void save(Connection conn, Cliente valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE cliente SET documento = ?, nombre_completo = ?, telefono = ?, "
               + "email = ?, valoracion = ? WHERE (id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getDocumento()); 
              stmt.setString(2, valueObject.getNombre_completo()); 
              stmt.setString(3, valueObject.getTelefono()); 
              stmt.setString(4, valueObject.getEmail()); 
              stmt.setInt(5, valueObject.getValoracion()); 

              stmt.setInt(6, valueObject.getId()); 

              int rowcount = databaseUpdate(conn, stmt);
              if (rowcount == 0) {
                   //System.out.println("Object could not be saved! (PrimaryKey not found)");
                   throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
              }
              if (rowcount > 1) {
                   //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                   throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
              }
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }
    
    public void updateValoracion(Connection conn, Cliente valueObject) 
            throws NotFoundException, SQLException {

    		//UPDATE `cliente` SET `valoracion` = '5' WHERE `cliente`.`id` = 1
            String sql = "UPDATE cliente SET valoracion = ? WHERE (documento = ? ) ";
            PreparedStatement stmt = null;

            try {
                stmt = conn.prepareStatement(sql);              
                stmt.setInt(1, valueObject.getValoracion()); 
                
                stmt.setString(2, valueObject.getDocumento()); 

                int rowcount = databaseUpdate(conn, stmt);
                if (rowcount == 0) {
                     //System.out.println("Object could not be saved! (PrimaryKey not found)");
                     throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
                }
                if (rowcount > 1) {
                     //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                     throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
                }
            } finally {
                if (stmt != null)
                    stmt.close();
            }
      }


    public void delete(Connection conn, Cliente valueObject) 
          throws NotFoundException, SQLException {

          String sql = "DELETE FROM cliente WHERE (documento = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getDocumento()); 

              int rowcount = databaseUpdate(conn, stmt);
              if (rowcount == 0) {
                   //System.out.println("Object could not be deleted (PrimaryKey not found)");
                   throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
              }
              if (rowcount > 1) {
                   //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                   throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
              }
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    public void deleteAll(Connection conn) throws SQLException {

          String sql = "DELETE FROM cliente";
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

          String sql = "SELECT count(*) FROM cliente";
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


    public List searchMatching(Connection conn, Cliente valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM cliente WHERE 1=1 ");

          if (valueObject.getId() != 0) {
              if (first) { first = false; }
              sql.append("AND id = ").append(valueObject.getId()).append(" ");
          }

          if (valueObject.getDocumento() != null) {
              if (first) { first = false; }
              sql.append("AND documento LIKE '").append(valueObject.getDocumento()).append("%' ");
          }

          if (valueObject.getNombre_completo() != null) {
              if (first) { first = false; }
              sql.append("AND nombre_completo LIKE '").append(valueObject.getNombre_completo()).append("%' ");
          }

          if (valueObject.getTelefono() != null) {
              if (first) { first = false; }
              sql.append("AND telefono LIKE '").append(valueObject.getTelefono()).append("%' ");
          }

          if (valueObject.getEmail() != null) {
              if (first) { first = false; }
              sql.append("AND email LIKE '").append(valueObject.getEmail()).append("%' ");
          }
          if (valueObject.getValoracion() != null) {
              if (first) { first = false; }
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

    protected void singleQuery(Connection conn, PreparedStatement stmt, Cliente valueObject) 
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
                   valueObject.setValoracion(result.getInt("valoracion"));

              } else {
                    //System.out.println("Cliente Object Not Found!");
                    throw new NotFoundException("Cliente Object Not Found!");
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
                   Cliente temp = createValueObject();

                   temp.setId(result.getInt("id")); 
                   temp.setDocumento(result.getString("documento")); 
                   temp.setNombre_completo(result.getString("nombre_completo")); 
                   temp.setTelefono(result.getString("telefono")); 
                   temp.setEmail(result.getString("email")); 
                   temp.setValoracion(result.getInt("valoracion")); 

                   searchResults.add(temp);
              }

          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }

          return (List)searchResults;
    }


}