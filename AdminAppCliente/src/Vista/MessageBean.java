
package Vista;

import java.awt.Container;
import javax.swing.JOptionPane;

public class MessageBean implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Contenedor en el que se desplegará el mensaje
     */
    private Container cPadre;

    /**
     * Representa el diálogo de error
     */
    private String titError ="Error";

    /**
     * Representa el diálogo de advertencia
     */
    private String titAdvertencia ="Cuidado";

    /**
     * Representa el diálogo de información con icono
     */
    private String titInformacion ="Información";

    

    /**
     * Representa el diálogo de texto plano sin icono
     */
    private String titTextoPlano ="Información";

    /**
     * Representa el diálogo de elección Si, No, Cancelar
     */
    private String titSiNoCancelar="Seleccione una opción";

    /**
     * Representa el diálogo de elección Si, No
     */
    private String titSiNo="Escoja una opción";

    /**
     * Representa el diálogo OK, Cancel
     */
    private String titOKCancel="Atención";

    /** Creates new BeanMensajes */
    public MessageBean() {
    }

    /** Creates new BeanMensajes */
    public void setCFather(Container C) {
        cPadre=C;
    }

    /**
     * Método para desplegar un mensaje, sin ícono
     * @param mensaje, el mensaje a desplegar
     */
    public void message(String mensaje){
      mensaje="<html><font color=blue><b>"+mensaje+"</b></font></html>";
      JOptionPane.showMessageDialog(cPadre,mensaje);
      return;
    }

    /**
     * Método para desplegar un mensaje de error, con icono
     * @param mensaje, el mensaje a desplegar
     */
    public void error(String mensaje){
      mensaje="<html><font color=red><b>"+mensaje+"</b></font></html>";
      JOptionPane.showMessageDialog(cPadre,mensaje,titError,JOptionPane.ERROR_MESSAGE);
      return;
    }

    /**
     * Metodo para desplegar un mensaje de avertencia, con ìcono
     * @param mensaje, el mensaje a desplegar.
     */
    public void warning(String mensaje){
      mensaje="<html><font color=red><b>"+mensaje+"</b></font></html>";
      JOptionPane.showMessageDialog(cPadre,mensaje,titAdvertencia,JOptionPane.WARNING_MESSAGE);
      return;
    }

    /**
     * Método para desplegar un mensaje de información
     * @param mensaje, el mensaje a desplegar
     */
    public void information(String mensaje){
      mensaje="<html><font color=blue><b>"+mensaje+"</b></font></html>";
      JOptionPane.showMessageDialog(cPadre,mensaje,titInformacion,JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    /**
     * Mètod para desplegar un mensaje plano
     * @param mensaje, el mensaje a desplegar
     */
    public void text(String mensaje){
      mensaje="<html><font color=blue><b>"+mensaje+"</b></font></html>";
      JOptionPane.showMessageDialog(cPadre,mensaje,titTextoPlano,JOptionPane.PLAIN_MESSAGE);
      return;
    }

    /**
     * Método para desplegar un mensaje con las opciones Si,No,Cancelar
     * @param mensaje, el mensaje a desplegar
     * @return la opción escogida Si=0, No=1, Cancelar=2
     */
    public int IfNoCancel(String mensaje){
      Object[] options = {"Si","No","Cancelar"};
      mensaje="<html><font color=green><b>"+mensaje+"</b></font></html>";
      int n = JOptionPane.showOptionDialog(cPadre,mensaje,titSiNoCancelar,JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
      return n;
    }

    /**
     * Método para desplegar un mensaje con las opciones Si, No
     * @param mensaje, el mensaje a desplegar
     * @return n, la opción escogida. Si=0, No=1
     */
    public int IfNo(String mensaje){
      Object[] options = {"Si","No"};
      mensaje="<html><font color=green><b>"+mensaje+"</b></font></html>";
      int n = JOptionPane.showOptionDialog(cPadre,mensaje,titSiNo,JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[1]);
      return n;
    }

    /**
     * Método para desplegar un mensaje con opciones Aceptar, Cancelar
     * @param mensaje, el mensaje a desplegar
     * @return la opción escogida Aceptar=0, Cancelar=1;
     */
    public int AceptCancel(String mensaje){
      Object[] options = {"Aceptar","Cancelar"};
      mensaje="<html><font color=green><b>"+mensaje+"</b></font></html>";
      int n = JOptionPane.showOptionDialog(cPadre,mensaje,titOKCancel,JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[1]);
      return n;
    }
}