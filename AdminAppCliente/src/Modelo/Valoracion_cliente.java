package Modelo;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class Valoracion_cliente implements Cloneable, Serializable {

    private int id;
    private int id_usuario;
    private int id_cliente;
    private BigDecimal puntaje;

    public Valoracion_cliente () {

    }

    public Valoracion_cliente (int idCliente) {
          this.id_cliente = idCliente;
    }

    public int getId() {
          return this.id;
    }

    public void setId(int idIn) {
          this.id = idIn;
    }

    public int getId_usuario() {
          return this.id_usuario;
    }
    
    public void setId_usuario(int id_usuarioIn) {
          this.id_usuario = id_usuarioIn;
    }

    public int getId_cliente() {
          return this.id_cliente;
    }
    
    public void setId_cliente(int id_clienteIn) {
          this.id_cliente = id_clienteIn;
    }

    public BigDecimal getPuntaje() {
          return this.puntaje;
    }
    
    public void setPuntaje(BigDecimal puntajeIn) {
          this.puntaje = puntajeIn;
    }

    public void setAll(int idIn,
          int id_usuarioIn,
          int id_clienteIn,
          BigDecimal puntajeIn) {
          this.id = idIn;
          this.id_usuario = id_usuarioIn;
          this.id_cliente = id_clienteIn;
          this.puntaje = puntajeIn;
    }

    public boolean hasEqualMapping(Valoracion_cliente valueObject) {

          if (valueObject.getId() != this.id) {
        	  return(false);
          }

          if (valueObject.getId_usuario() != this.id_usuario) {
        	  return(false);
          }
          
          if (valueObject.getId_cliente() != this.id_cliente) {
        	  return(false);
          }
          
          if (this.puntaje == null) {
	    	  if (valueObject.getPuntaje() != null)
	    		  return(false);
          } else if (!this.puntaje.equals(valueObject.getPuntaje())) {
                return(false);
          }

          return true;
    }

    public String toString() {
        StringBuffer out = new StringBuffer(this.getDaogenVersion());
        out.append("\nclass Valoracion_cliente, mapping to table valoracion_cliente\n");
        out.append("Persistent attributes: \n"); 
        out.append("id = " + this.id + "\n"); 
        out.append("id_usuario = " + this.id_usuario + "\n"); 
        out.append("id_cliente = " + this.id_cliente + "\n"); 
        out.append("puntaje = " + this.puntaje + "\n"); 
        return out.toString();
    }

    public Object clone() {
        Valoracion_cliente cloned = new Valoracion_cliente();

        cloned.setId(this.id); 
        cloned.setId_usuario(this.id_usuario); 
        cloned.setId_cliente(this.id_cliente); 
        if (this.puntaje != null)
             cloned.setPuntaje(new BigDecimal(this.puntaje.doubleValue())); 
        return cloned;
    }

    public String getDaogenVersion() {
        return "DaoGen version 2.4.1";
    }

}
