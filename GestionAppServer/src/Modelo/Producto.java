package Modelo;


import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;


public class Producto implements Cloneable, Serializable {
    private int id;
    private String nombre;
    private String precio;
    private String descripcion;
    private String imagen;
    private String nombre_categoria;
    private int id_menu;

    public Producto () {

    }

    public Producto (int idIn) {

          this.id = idIn;

    }

    public int getId() {
          return this.id;
    }
    public void setId(int idIn) {
          this.id = idIn;
    }

    public String getNombre() {
          return this.nombre;
    }
    public void setNombre(String nombreIn) {
          this.nombre = nombreIn;
    }

    public String getPrecio() {
          return this.precio;
    }
    public void setPrecio(String precioIn) {
          this.precio = precioIn;
    }

    public String getDescripcion() {
          return this.descripcion;
    }
    public void setDescripcion(String descripcionIn) {
          this.descripcion = descripcionIn;
    }

    public String getImagen() {
          return this.imagen;
    }
    public void setImagen(String imagenIn) {
          this.imagen = imagenIn;
    }

    public String getNombre_categoria() {
          return this.nombre_categoria;
    }
    public void setNombre_categoria(String nombre_categoria) {
          this.nombre_categoria = nombre_categoria;
    }

    public int getId_menu() {
          return this.id_menu;
    }
    public void setId_menu(int id_menuIn) {
          this.id_menu = id_menuIn;
    }

    public void setAll(int idIn,
          String nombreIn,
          String precioIn,
          String descripcionIn,
          String imagenIn,
          String nombre_categoriaIn,
          int id_menuIn) {
          this.id = idIn;
          this.nombre = nombreIn;
          this.precio = precioIn;
          this.descripcion = descripcionIn;
          this.imagen = imagenIn;
          this.nombre_categoria = nombre_categoriaIn;
          this.id_menu = id_menuIn;
    }
    public boolean hasEqualMapping(Producto valueObject) {

          if (valueObject.getId() != this.id) {
                    return(false);
          }
          if (this.nombre == null) {
                    if (valueObject.getNombre() != null)
                           return(false);
          } else if (!this.nombre.equals(valueObject.getNombre())) {
                    return(false);
          }
          if (this.precio == null) {
                    if (valueObject.getPrecio() != null)
                           return(false);
          } else if (!this.precio.equals(valueObject.getPrecio())) {
                    return(false);
          }
          if (this.descripcion == null) {
                    if (valueObject.getDescripcion() != null)
                           return(false);
          } else if (!this.descripcion.equals(valueObject.getDescripcion())) {
                    return(false);
          }
          if (this.imagen == null) {
                    if (valueObject.getImagen() == null)
                           return(false);
          } else if (!this.imagen.equals(valueObject.getImagen())) {
                    return(false);
          }
          if (valueObject.getNombre_categoria() != this.nombre_categoria) {
                    return(false);
          }
          if (valueObject.getId_menu() != this.id_menu) {
                    return(false);
          }

          return true;
    }

    public String toString() {
        StringBuffer out = new StringBuffer(this.getDaogenVersion());
        out.append("\nclass Producto, mapping to table producto\n");
        out.append("Persistent attributes: \n"); 
        out.append("id = " + this.id + "\n"); 
        out.append("nombre = " + this.nombre + "\n"); 
        out.append("precio = " + this.precio + "\n"); 
        out.append("descripcion = " + this.descripcion + "\n"); 
        out.append("imagen = " + this.imagen + "\n"); 
        out.append("nombre_categoria = " + this.nombre_categoria + "\n"); 
        out.append("id_menu = " + this.id_menu + "\n"); 
        return out.toString();
    }
    
    public Object clone() {
        Producto cloned = new Producto();

        cloned.setId(this.id); 
        if (this.nombre != null)
             cloned.setNombre(new String(this.nombre)); 
        if (this.precio != null)
             cloned.setPrecio(new String(this.precio)); 
        if (this.descripcion != null)
             cloned.setDescripcion(new String(this.descripcion)); 
        if (this.imagen != null)
             cloned.setImagen(new String(this.imagen)); 
        cloned.setNombre_categoria(this.nombre_categoria); 
        cloned.setId_menu(this.id_menu); 
        return cloned;
    }

    public String getDaogenVersion() {
        return "DaoGen version 2.4.1";
    }

}
