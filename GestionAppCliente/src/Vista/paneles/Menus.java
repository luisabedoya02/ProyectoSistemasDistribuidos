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
import Modelo.Menu;
import Modelo.Mesa;


public class Menus extends javax.swing.JPanel {

	DefaultTableModel modeloMenus = new DefaultTableModel();

	ArrayList<Menu> listaMenus = new ArrayList<Menu>();

	public Menus() {
		initComponents();

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
			
			jTextField1.setText(id);
			jTextField5.setText(id);
			jTextField2.setText(restaurante);
			
		}
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel7 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTableMenus = new javax.swing.JTable();
		jButton4 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jTextField5 = new javax.swing.JTextField();
		jTextField1 = new javax.swing.JTextField();
		jButton2 = new javax.swing.JButton();
		jTextField2 = new javax.swing.JTextField();

		setBackground(new java.awt.Color(255, 255, 255));

		jLabel7.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
		jLabel7.setForeground(new java.awt.Color(128, 128, 131));
		jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		// jLabel7.setIcon(new
		// javax.swing.ImageIcon(getClass().getResource("/img1/feeedback.png"))); //
		// NOI18N
		jLabel7.setText("Menú");

		jTableMenus.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane1.setViewportView(jTableMenus);

		jButton4.setText("Eliminar");

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel1.setText("Código");

		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel2.setText("Restaurante");

		jButton1.setText("Guardar");

		jButton3.setText("Actualizar");

		jTextField5.setText("jTextField5");

		jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		jButton2.setText("Buscar");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

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
												.addComponent(jTextField2).addComponent(jTextField1)))
								.addGroup(layout.createSequentialGroup().addGap(134, 134, 134).addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.PREFERRED_SIZE, 142,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 135,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(18, 18, 18)
										.addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 264,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
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
										.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel1)
										.addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(
												jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(20, 20, 20)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 48,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel2)).addGap(145, 145, 145)
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE,
																36, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE,
																36, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(178, Short.MAX_VALUE))));
	}// </editor-fold>//GEN-END:initComponents

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField1ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton2ActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTableMenus;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField5;
	// End of variables declaration//GEN-END:variables
}
