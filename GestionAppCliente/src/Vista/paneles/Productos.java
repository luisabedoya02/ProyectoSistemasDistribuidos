/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.paneles;

import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.ControlProducto;
import Controlador.ControlUsuario;
import Modelo.Producto;
import Modelo.Usuario;

public class Productos extends javax.swing.JPanel {

	DefaultTableModel modeloProductos = new DefaultTableModel();

	ArrayList<Producto> listaProductos = new ArrayList<Producto>();
	Producto p = new Producto();

	public Productos() {
		initComponents();
		jButtonEliminar.setEnabled(false);

		setModeloTablaProductos();
		try {
			llenarListaProductos();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDatosProductos();

		setEventoMouseClicked(jTableProductos);
	}

	public void setModeloTablaProductos() {
		String cabeceras[] = { "Id", "Nombre", "Precio", "Descripci�n", "Categor�a", "Men�" };
		modeloProductos.setColumnIdentifiers(cabeceras);
		jTableProductos.setModel(modeloProductos);
	}

	public void llenarListaProductos() throws RemoteException, NotBoundException {
		ControlProducto cp = new ControlProducto();
		listaProductos = (ArrayList<Producto>) cp.loadAll();
	}

	public void setDatosProductos() {
		Object[] productos = new Object[modeloProductos.getColumnCount()];
		for (Producto producto : listaProductos) {
			productos[0] = producto.getId();
			productos[1] = producto.getNombre();
			productos[2] = producto.getPrecio();
			productos[3] = producto.getDescripcion();
			productos[4] = producto.getNombre_categoria();
			productos[5] = producto.getId_menu();
			modeloProductos.addRow(productos);
		}
		jTableProductos.setModel(modeloProductos);
	}

	private void setEventoMouseClicked(JTable tbl) {
		tbl.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				jTableUsuariosMouseClicked(e);
			}
		});
	}

	private void jTableUsuariosMouseClicked(java.awt.event.MouseEvent evt) {
		int fila = jTableProductos.rowAtPoint(evt.getPoint());
		if (fila < 0) {
			JOptionPane.showMessageDialog(null, "Selecciona una fila");
		} else {
			String id = jTableProductos.getValueAt(fila, 0).toString();
			String nombre = jTableProductos.getValueAt(fila, 1).toString();
			String precio = jTableProductos.getValueAt(fila, 2).toString();
			String descripcion = jTableProductos.getValueAt(fila, 3).toString();
			String categoria = jTableProductos.getValueAt(fila, 4).toString();
			String menu = jTableProductos.getValueAt(fila, 5).toString();

			jTextFieldId.setText(id);
			jTextFieldNombre.setText(nombre);
			jTextFieldPrecio.setText(precio);
			jTextFieldDescripcion.setText(descripcion);
			jComboBoxCategoria.setSelectedItem(categoria);
			jComboBoxMenu.setSelectedItem(menu);

		}
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel7 = new javax.swing.JLabel();
		Nombre = new javax.swing.JLabel();
		jTextFieldNombre = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jTextFieldPrecio = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jTextFieldDescripcion = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jComboBoxCategoria = new javax.swing.JComboBox<>();
		jComboBoxMenu = new javax.swing.JComboBox<>();
		jLabel8 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTableProductos = new javax.swing.JTable();
		jButtonGuardar = new javax.swing.JButton();
		jTextFieldId = new javax.swing.JTextField();
		jButtonBuscar = new javax.swing.JButton();
		jButtonActualizar = new javax.swing.JButton();
		jButtonEliminar = new javax.swing.JButton();

		setBackground(new java.awt.Color(255, 255, 255));

		jLabel7.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
		jLabel7.setForeground(new java.awt.Color(128, 128, 131));
		jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		// jLabel7.setIcon(new
		// javax.swing.ImageIcon(getClass().getResource("/img1/collection.png"))); //
		// NOI18N
		jLabel7.setText("Productos");

		Nombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		Nombre.setText("Nombre");

		jTextFieldNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel2.setText("Precio");

		jTextFieldPrecio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel3.setText("Descripci�n");

		jTextFieldDescripcion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel4.setText("Categor�a");

		jComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Bebidas", "Entradas", "Plato Fuerte", "Hamburguesas" }));

		jComboBoxMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));

		jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel8.setText("Menú");

		jTableProductos
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane1.setViewportView(jTableProductos);

		jButtonGuardar.setText("Guardar");

		jTextFieldId.setText("");

		jButtonBuscar.setText("Buscar");
		jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButtonBuscarActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		jButtonActualizar.setText("Actualizar");
		jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButtonActualizarActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		jButtonEliminar.setText("Eliminar");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel7,
								javax.swing.GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addGap(76, 76, 76).addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(Nombre).addComponent(jLabel2))
										.addGap(44, 44, 44)
										.addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jTextFieldNombre).addComponent(jTextFieldPrecio,
														javax.swing.GroupLayout.PREFERRED_SIZE, 289,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel3).addComponent(jLabel4).addComponent(jLabel8))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(jComboBoxMenu, 0,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jTextFieldDescripcion,
																javax.swing.GroupLayout.DEFAULT_SIZE, 289,
																Short.MAX_VALUE)
														.addComponent(jComboBoxCategoria,
																javax.swing.GroupLayout.Alignment.TRAILING, 0,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGroup(layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(jButtonEliminar,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE, 142,
																Short.MAX_VALUE)
														.addComponent(jButtonGuardar,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addGap(18, 18, 18).addComponent(jButtonActualizar,
																javax.swing.GroupLayout.PREFERRED_SIZE, 135,
																javax.swing.GroupLayout.PREFERRED_SIZE)))))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
												.createSequentialGroup()
												.addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 378,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE,
														151, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(0, 0, Short.MAX_VALUE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addComponent(
						jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(58, 58, 58)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(Nombre)
						.addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(21, 21, 21)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 48,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel2))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jTextFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel3))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel4))
								.addGap(26, 26, 26)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jComboBoxMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel8))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addContainerGap(153, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField1ActionPerformed
	
	
	private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt)
			throws RemoteException, NotBoundException {

		String id = jTextFieldId.getText();
		int Id = Integer.parseInt(id);
		
		ControlProducto cp = new ControlProducto();
		
		if (cp.searchProductoId(Id)) {
			System.out.println(p.toString());
			
			
			modeloProductos.setRowCount(0);
			p = cp.searchProducto(Id);

			Object[] productos = new Object[modeloProductos.getColumnCount()];
			
			productos[0] = p.getId();
			
			productos[1] = p.getNombre();
			if(productos[1] == null) {
				setModeloTablaProductos();
				llenarListaProductos();
				setDatosProductos();
				return;
			}
			
			productos[2] = p.getPrecio();
			
			productos[3] = p.getNombre_categoria();
			productos[4] = p.getId_menu();
			modeloProductos.addRow(productos);
			jTableProductos.setModel(modeloProductos);
		}

	}
	
	

	private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt)
			throws RemoteException, NotBoundException {

		ControlProducto cp = new ControlProducto();
		

		String id2 = jTextFieldId.getText();
		int id = Integer.parseInt(id2);
		String nombre = jTextFieldNombre.getText();
		String precio = jTextFieldPrecio.getText();
		String descripcion = jTextFieldDescripcion.getText();
		String categoria = jComboBoxCategoria.getSelectedItem().toString();
		String menu1 = jComboBoxMenu.getSelectedItem().toString();
		int menu = Integer.parseInt(menu1);

		p.setAll(id, nombre, precio, descripcion, null, categoria, menu);

		System.out.println(p.toString());

		if (cp.updateProducto(p)) {

			modeloProductos.setRowCount(0);

			try {
				llenarListaProductos();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			setDatosProductos();
			jTableProductos.setEnabled(false);
			jTextFieldId.setText("");
			jTextFieldNombre.setText("");
			jTextFieldPrecio.setText("");
			jTextFieldDescripcion.setText("");
			jComboBoxCategoria.setSelectedIndex(-1);
			jComboBoxMenu.setSelectedIndex(-1);

		}

	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel Nombre;
	private javax.swing.JButton jButtonGuardar;
	private javax.swing.JButton jButtonBuscar;
	private javax.swing.JButton jButtonActualizar;
	private javax.swing.JButton jButtonEliminar;
	private javax.swing.JComboBox<String> jComboBoxCategoria;
	private javax.swing.JComboBox<String> jComboBoxMenu;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTableProductos;
	private javax.swing.JTextField jTextFieldNombre;
	private javax.swing.JTextField jTextFieldPrecio;
	private javax.swing.JTextField jTextFieldDescripcion;
	private javax.swing.JTextField jTextFieldId;
	// End of variables declaration//GEN-END:variables
}
