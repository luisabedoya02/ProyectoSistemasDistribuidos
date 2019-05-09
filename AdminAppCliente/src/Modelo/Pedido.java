package Modelo;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.math.*;



public class Pedido implements Cloneable, Serializable {

    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int id;
    private java.sql.Date fecha;
    private double precio_total;
    private int id_cliente;
    private int id_mesa;
    private String documento;


    public Pedido () {

    }

    public Pedido (int idCliente, Date fecha) {

          this.id_cliente = idCliente;
          this.fecha = new java.sql.Date(fecha.getDate());

    }

    
	public Pedido(int id) {

		this.id = id;

	}

    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

    public int getId() {
          return this.id;
    }
    public void setId(int idIn) {
          this.id = idIn;
    }

    public java.sql.Date getFecha() {
          return this.fecha;
    }
    public void setFecha(java.sql.Date fechaIn) {
          this.fecha = fechaIn;
    }

    public double getPrecio_total() {
          return this.precio_total;
    }
    public void setPrecio_total(double precio_totalIn) {
          this.precio_total = precio_totalIn;
    }

    public int getId_cliente() {
          return this.id_cliente;
    }
    public void setId_cliente(int id_clienteIn) {
          this.id_cliente = id_clienteIn;
    }

    public int getId_mesa() {
          return this.id_mesa;
    }
    public void setId_mesa(int id_mesaIn) {
          this.id_mesa = id_mesaIn;
    }



    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variales, without going trough the 
     * individual set-methods.
     */

    public void setAll(int idIn,
          java.sql.Date fechaIn,
          double precio_totalIn,
          int id_clienteIn,
          int id_mesaIn) {
          this.id = idIn;
          this.fecha = fechaIn;
          this.precio_total = precio_totalIn;
          this.id_cliente = id_clienteIn;
          this.id_mesa = id_mesaIn;
    }


    /** 
     * hasEqualMapping-method will compare two Pedido instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     */
    public boolean hasEqualMapping(Pedido valueObject) {

          if (valueObject.getId() != this.id) {
                    return(false);
          }
          if (this.fecha == null) {
                    if (valueObject.getFecha() != null)
                           return(false);
          } else if (!this.fecha.equals(valueObject.getFecha())) {
                    return(false);
          }
          if (valueObject.getPrecio_total() != this.precio_total) {
                    return(false);
          }
          if (valueObject.getId_cliente() != this.id_cliente) {
                    return(false);
          }
          if (valueObject.getId_mesa() != this.id_mesa) {
                    return(false);
          }

          return true;
    }



    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in textlog.
     */
    public String toString() {
        StringBuffer out = new StringBuffer(this.getDaogenVersion());
        out.append("\nclass Pedido, mapping to table pedido\n");
        out.append("Persistent attributes: \n"); 
        out.append("id = " + this.id + "\n"); 
        out.append("fecha = " + this.fecha + "\n"); 
        out.append("precio_total = " + this.precio_total + "\n"); 
        out.append("id_cliente = " + this.id_cliente + "\n"); 
        out.append("id_mesa = " + this.id_mesa + "\n"); 
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the retuned cloned object
     * will also have all its attributes cloned.
     */
    public Object clone() {
        Pedido cloned = new Pedido();

        cloned.setId(this.id); 
        if (this.fecha != null)
             cloned.setFecha((java.sql.Date)this.fecha.clone()); 
        cloned.setPrecio_total(this.precio_total); 
        cloned.setId_cliente(this.id_cliente); 
        cloned.setId_mesa(this.id_mesa); 
        return cloned;
    }



    /** 
     * getDaogenVersion will return information about
     * generator which created these sources.
     */
    public String getDaogenVersion() {
        return "DaoGen version 2.4.1";
    }

}
