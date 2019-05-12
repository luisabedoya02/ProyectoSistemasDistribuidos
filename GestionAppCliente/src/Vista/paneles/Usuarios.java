/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.paneles;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controlador.ControlMesa;
import Controlador.ControlUsuario;
import Modelo.Mesa;
import Modelo.Usuario;

/**
 *
 * @author RojeruSan
 */
public class Usuarios extends javax.swing.JPanel {

	DefaultTableModel modeloUsuarios = new DefaultTableModel();

	ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	Usuario u = new Usuario();
	
	
	public Usuarios() {
		initComponents();

		setModeloTablaUsuarios();
		try {
			llenarListaUsuarios();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDatosUsuarios();

		setEventoMouseClicked(JTableUsuarios);
	}

	public void setModeloTablaUsuarios() {
		String cabeceras[] = { "id", "Documento", "Nombre", "Telefono", "E-mail", "Rol", "Restaurante" };
		modeloUsuarios.setColumnIdentifiers(cabeceras);
		JTableUsuarios.setModel(modeloUsuarios);
	}

	public void llenarListaUsuarios() throws RemoteException, NotBoundException {
		ControlUsuario cu = new ControlUsuario();
		listaUsuarios = (ArrayList<Usuario>) cu.loadAll();
	}

	public void setDatosUsuarios() {
		Object[] usuarios = new Object[modeloUsuarios.getColumnCount()];
		for (Usuario usuario : listaUsuarios) {
			usuarios[0] = usuario.getId();
			usuarios[1] = usuario.getDocumento();
			usuarios[2] = usuario.getNombre_completo();
			usuarios[3] = usuario.getTelefono();
			usuarios[4] = usuario.getEmail();
			usuarios[5] = usuario.getNombre_rol();
			usuarios[6] = usuario.getNombre_restaurante();
			modeloUsuarios.addRow(usuarios);
		}
		JTableUsuarios.setModel(modeloUsuarios);
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
		int fila = JTableUsuarios.rowAtPoint(evt.getPoint());
		if (fila < 0) {
			JOptionPane.showMessageDialog(null, "Selecciona una fila");
		} else {
			String id = JTableUsuarios.getValueAt(fila, 0).toString();
			String documento = JTableUsuarios.getValueAt(fila, 1).toString();
			String nombre = JTableUsuarios.getValueAt(fila, 2).toString();
			String telefono = JTableUsuarios.getValueAt(fila, 3).toString();
			String email = JTableUsuarios.getValueAt(fila, 4).toString();
			String rol = JTableUsuarios.getValueAt(fila, 5).toString();
			String restaurante = JTableUsuarios.getValueAt(fila, 6).toString();

			jTextFieldDocumento.setText(documento);
			jTextFieldNombre.setText(nombre);
			jTextFieldTelefono.setText(telefono);
			jTextFieldEmail.setText(email);
			jTextFieldId.setText(id);
			jComboBoxRestaurante.setSelectedItem(rol);
			jComboBoxRol.setSelectedItem(restaurante);

		}
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel7 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jTextFieldId = new javax.swing.JTextField();
		jTextFieldNombre = new javax.swing.JTextField();
		jTextFieldTelefono = new javax.swing.JTextField();
		jTextFieldEmail = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		JTableUsuarios = new javax.swing.JTable();
		jTextFieldDocumento = new javax.swing.JTextField();
		jButtonBuscar = new javax.swing.JButton();
		jButtonGuardar = new javax.swing.JButton();
		jButtonActualizar = new javax.swing.JButton();
		jButtonEliminar = new javax.swing.JButton();
		jComboBoxRestaurante = new javax.swing.JComboBox<>();
		jLabel5 = new javax.swing.JLabel();
		jComboBoxRol = new javax.swing.JComboBox<>();

		setBackground(new java.awt.Color(255, 255, 255));

		jLabel7.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
		jLabel7.setForeground(new java.awt.Color(128, 128, 131));
		jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		// jLabel7.setIcon(new
		// javax.swing.ImageIcon(getClass().getResource("/img1/home.png"))); // NOI18N
		jLabel7.setText("Usuarios");

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel1.setText("Nombre");

		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel2.setText("Tel�fono");

		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel3.setText("E-Mail");

		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel4.setText("Rol");

		jTextFieldNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// jTextField1ActionPerformed(evt);
			}
		});

		jTextFieldTelefono.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

		jTextFieldEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

		JTableUsuarios.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane1.setViewportView(JTableUsuarios);

		jTextFieldDocumento.setText("");

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

		jButtonGuardar.setText("Guardar");

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
		jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				try {
				jButtonEliminarActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		jComboBoxRestaurante
				.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Mesero" }));

		jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel5.setText("Restaurante");

		jComboBoxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Restaurante 1" }));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(
												jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(layout.createSequentialGroup().addGap(71, 71, 71).addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING, false)
																.addGroup(layout.createSequentialGroup().addGroup(layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(jLabel1).addComponent(jLabel2))
																		.addGap(44, 44, 44).addGroup(
																				layout.createParallelGroup(
																						javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jTextFieldTelefono)
																						.addComponent(
																								jTextFieldNombre)))
																.addGroup(layout.createSequentialGroup()
																		.addGroup(layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(jLabel3)
																				.addComponent(jLabel4)
																				.addComponent(jLabel5))
																		.addGap(41, 41, 41)
																		.addGroup(layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(jTextFieldEmail,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						340,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(jComboBoxRestaurante,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						340,
																						javax.swing.GroupLayout.PREFERRED_SIZE)))))
												.addGroup(layout.createSequentialGroup().addGap(205, 205, 205)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(jComboBoxRol, 0,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addGroup(layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		false)
																		.addComponent(jButtonEliminar,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				166, Short.MAX_VALUE)
																		.addComponent(jButtonGuardar,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))))
												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
														.createSequentialGroup().addContainerGap()
														.addComponent(jButtonActualizar,
																javax.swing.GroupLayout.PREFERRED_SIZE, 155,
																javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(layout.createSequentialGroup().addGap(18, 18, 18)
																.addComponent(
																		jTextFieldDocumento,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 264,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(jButtonBuscar,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 150,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(layout.createSequentialGroup().addGap(18, 18, 18)
																.addComponent(jScrollPane1,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 421,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGap(0, 200, Short.MAX_VALUE)))
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel7).addGap(36, 36, 36)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1)
								.addComponent(jTextFieldDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(
										jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(20, 20, 20)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
								.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 48,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel2))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel3))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel4)
										.addComponent(jComboBoxRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24,
										Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel5)
										.addComponent(jComboBoxRol, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
						.addGap(65, 65, 65)));
	}// </editor-fold>

	private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt)
			throws RemoteException, NotBoundException {

		String documento = jTextFieldDocumento.getText();
		u.setDocumento(documento);
		ControlUsuario cu = new ControlUsuario();
		
		if (cu.searchUsuarioDoc(documento)) {
			System.out.println(u.toString());
			
			modeloUsuarios.setRowCount(0);
			u = cu.searchUsuario(documento);

			Object[] usuarios = new Object[modeloUsuarios.getColumnCount()];
			
			usuarios[0] = u.getId();
			usuarios[1] = u.getDocumento();
			usuarios[2] = u.getNombre_completo();
			if(usuarios[2] == null) {
				setModeloTablaUsuarios();
				llenarListaUsuarios();
				setDatosUsuarios();
				return;
			}
			usuarios[3] = u.getTelefono();
			usuarios[4] = u.getEmail();
			usuarios[5] = u.getNombre_rol();
			usuarios[6] = u.getNombre_restaurante();
			modeloUsuarios.addRow(usuarios);
			JTableUsuarios.setModel(modeloUsuarios);
		}

	}

	private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt)
			throws RemoteException, NotBoundException {


		String id2 = jTextFieldId.getText();
		int id = Integer.parseInt(id2);
		String documento = jTextFieldDocumento.getText();
		String nombre = jTextFieldNombre.getText();
		String telefono = jTextFieldTelefono.getText();
		String email = jTextFieldEmail.getText();
		String rol = jComboBoxRol.getSelectedItem().toString();
		String restaurante = jComboBoxRestaurante.getSelectedItem().toString();

		u.setAll(id, documento, nombre, telefono, email, rol, restaurante);

		System.out.println(u.toString());
		ControlUsuario cu = new ControlUsuario();
		if (cu.updateUsuario(u)) {

			modeloUsuarios.setRowCount(0);

			try {
				llenarListaUsuarios();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			setDatosUsuarios();
			JTableUsuarios.setEnabled(false);
			jTextFieldDocumento.setText("");
			jTextFieldNombre.setText("");
			jTextFieldTelefono.setText("");
			jTextFieldEmail.setText("");
			jComboBoxRestaurante.setSelectedIndex(-1);
			jComboBoxRol.setSelectedIndex(-1);
		}

	}
	
	private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt)
			throws RemoteException, NotBoundException {
		ControlUsuario cu = new ControlUsuario();

		String documento = jTextFieldDocumento.getText();

		u.setDocumento(documento);

		if (cu.delUsuario(documento)) {

			modeloUsuarios.setRowCount(0);

			try {
				llenarListaUsuarios();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			setDatosUsuarios();
			JTableUsuarios.setEnabled(false);
			jTextFieldDocumento.setText("");
			jTextFieldNombre.setText("");
			jTextFieldTelefono.setText("");
			jTextFieldEmail.setText("");
			jComboBoxRestaurante.setSelectedIndex(-1);
			jComboBoxRol.setSelectedIndex(-1);
		}
	}

	// Variables declaration - do not modify
	private javax.swing.JButton jButtonGuardar;
	private javax.swing.JButton jButtonBuscar;
	private javax.swing.JButton jButtonActualizar;
	private javax.swing.JButton jButtonEliminar;
	private javax.swing.JComboBox<String> jComboBoxRestaurante;
	private javax.swing.JComboBox<String> jComboBoxRol;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable JTableUsuarios;
	private javax.swing.JTextField jTextFieldNombre;
	private javax.swing.JTextField jTextFieldTelefono;
	private javax.swing.JTextField jTextFieldEmail;
	private javax.swing.JTextField jTextFieldDocumento;
	private javax.swing.JTextField jTextFieldId;
	// End of variables declaration
}
