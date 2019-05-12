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

import Controlador.ControlMenu;
import Controlador.ControlMesa;
import Controlador.ControlUsuario;
import Modelo.Menu;
import Modelo.Mesa;
import Modelo.Usuario;


public class Menus extends javax.swing.JPanel {

	DefaultTableModel modeloMenus = new DefaultTableModel();

	ArrayList<Menu> listaMenus = new ArrayList<Menu>();
	Menu m = new Menu();

	public Menus() {
		initComponents();
		jButtonEliminar.setEnabled(false);

		setModeloTablaMenus();
		try {
			llenarListaMenus();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDatosMenus();

		setEventoMouseClicked(jTableMenus);
	}

	public void setModeloTablaMenus() {
		String cabeceras[] = { "ID","Restaurante" };
		modeloMenus.setColumnIdentifiers(cabeceras);
		jTableMenus.setModel(modeloMenus);
	}

	public void llenarListaMenus() throws RemoteException, NotBoundException {
		ControlMenu cmenu = new ControlMenu();
		listaMenus = (ArrayList<Menu>) cmenu.loadAll();
	}

	public void setDatosMenus() {
		Object[] menus = new Object[modeloMenus.getColumnCount()];
		for (Menu menu : listaMenus) {
			menus[0] = menu.getId();		
			menus[1] = menu.getNombre_restaurante();
			modeloMenus.addRow(menus);
		}
		jTableMenus.setModel(modeloMenus);
	}

	private void setEventoMouseClicked(JTable tbl) {
		tbl.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				jTableMenusMouseClicked(e);
			}
		});
	}

	private void jTableMenusMouseClicked(java.awt.event.MouseEvent evt) {
		int fila = jTableMenus.rowAtPoint(evt.getPoint());
		if (fila < 0) {
			JOptionPane.showMessageDialog(null, "Selecciona una fila");
		} else {
			String id = jTableMenus.getValueAt(fila, 0).toString();
			String restaurante = jTableMenus.getValueAt(fila, 1).toString();
			
			jTextFieldCodigo.setText(id);
			jTextFieldId.setText(id);
			jComboBoxRestaurante.setSelectedItem(restaurante);
			
		}
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel7 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTableMenus = new javax.swing.JTable();
		jButtonEliminar = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jButtonGuardar = new javax.swing.JButton();
		jButtonActualizar = new javax.swing.JButton();
		jTextFieldId = new javax.swing.JTextField();
		jTextFieldCodigo = new javax.swing.JTextField();
		jButtonBuscar = new javax.swing.JButton();
		jComboBoxRestaurante = new javax.swing.JComboBox<String>();
		jComboBoxRestaurante = new javax.swing.JComboBox<>();

		setBackground(new java.awt.Color(255, 255, 255));

		jLabel7.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
		jLabel7.setForeground(new java.awt.Color(128, 128, 131));
		jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		// jLabel7.setIcon(new
		// javax.swing.ImageIcon(getClass().getResource("/img1/feeedback.png"))); //
		// NOI18N
		jLabel7.setText("Men�");

		jTableMenus.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane1.setViewportView(jTableMenus);

		jButtonEliminar.setText("Eliminar");

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel1.setText("C�digo");

		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel2.setText("Restaurante");

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

		jTextFieldId.setText("");

		jTextFieldCodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jTextFieldCodigo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

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

		jComboBoxRestaurante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

		jComboBoxRestaurante
		.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Restaurante 1" }));
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup().addGap(74, 74, 74)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel1).addComponent(jLabel2))
										.addGap(44, 44, 44)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jComboBoxRestaurante).addComponent(jTextFieldCodigo)))
								.addGroup(layout.createSequentialGroup().addGap(134, 134, 134).addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addComponent(jButtonEliminar, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jButtonGuardar, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.PREFERRED_SIZE, 142,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 135,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(18, 18, 18)
										.addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 264,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(18, 18, 18).addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE, 421,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(74, Short.MAX_VALUE))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel7).addContainerGap(577,
						Short.MAX_VALUE))
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(177, 177, 177)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel1)
										.addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(
												jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(20, 20, 20)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jComboBoxRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 48,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel2)).addGap(145, 145, 145)
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE,
																36, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE,
																36, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(178, Short.MAX_VALUE))));
	}// </editor-fold>//GEN-END:initComponents

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField1ActionPerformed

	private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt)
			throws RemoteException, NotBoundException {  
    	
    	String id = jTextFieldId.getText();
		int Id = Integer.parseInt(id);
		
		ControlMenu cm = new ControlMenu();
		
		if (cm.searchMenuId(Id)) {
			System.out.println(m.toString());
			
			modeloMenus.setRowCount(0);
			m = cm.searchMenu(Id);

			Object[] menus = new Object[modeloMenus.getColumnCount()];
			
			menus[0] = m.getId();
					
			menus[1] = m.getNombre_restaurante();
			if(menus[1]==null) {
				setModeloTablaMenus();
				llenarListaMenus();
				setDatosMenus();
				return;
			}
			modeloMenus.addRow(menus);
			jTableMenus.setModel(modeloMenus);
		}
		
	}

	
	private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt)
			throws RemoteException, NotBoundException {

		ControlMenu cmenu = new ControlMenu();

		String id2 = jTextFieldId.getText();
		int id = Integer.parseInt(id2);
		//String restaurante = jComboBoxRestaurante.getT;				
		String restaurante = jComboBoxRestaurante.getSelectedItem().toString();

		m.setAll(id, restaurante);

		System.out.println(m.toString());

		if (cmenu.updateMenu(m)) {

			modeloMenus.setRowCount(0);

			try {
				llenarListaMenus();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			setDatosMenus();
			jTableMenus.setEnabled(false);
			jTextFieldCodigo.setText("");
			jComboBoxRestaurante.setSelectedIndex(-1);
			jTextFieldId.setText("");
		}

	}
	
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButtonGuardar;
	private javax.swing.JButton jButtonBuscar;
	private javax.swing.JButton jButtonActualizar;
	private javax.swing.JButton jButtonEliminar;
	private javax.swing.JComboBox<String> jComboBoxRestaurante;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTableMenus;
	private javax.swing.JTextField jTextFieldCodigo;
	//private javax.swing.JTextField jComboBoxRestaurante;
	private javax.swing.JTextField jTextFieldId;
	// End of variables declaration//GEN-END:variables
}
