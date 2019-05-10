/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.List;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.ControlCliente;
import Controlador.ControlMesa;
import Controlador.ControlPedido;
import Controlador.ControlPedidoDetalle;
import Modelo.Cliente;
import Modelo.Mesa;
import Modelo.Pedido;
import Modelo.Pedido_detalle;

public class ClienteVista extends javax.swing.JFrame {

	ControlCliente cc = new ControlCliente();
	Cliente c = new Cliente();
	private MessageBean bm;

	DefaultTableModel modeloMesas = new DefaultTableModel();
	DefaultTableModel modeloClientes = new DefaultTableModel();
	DefaultTableModel modeloPedidos = new DefaultTableModel();
	DefaultTableModel modeloPedidosDetalles = new DefaultTableModel();

	ArrayList<Mesa> listaMesas = new ArrayList<Mesa>();
	ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
	ArrayList<Pedido_detalle> listaPedidosDetalles = new ArrayList<Pedido_detalle>();

	public ClienteVista() throws RemoteException, NotBoundException {
		initComponents();
		grupoBotones.add(uno);
		grupoBotones.add(dos);
		grupoBotones.add(tres);
		grupoBotones.add(cuatro);
		grupoBotones.add(cinco);

		setModeloTablaMesas();
		llenarListaMesas();
		setDatosMesas();

		setModeloTablaClientes();
		llenarListaClientes();
		setDatosClientes();

		setModeloTablaPedidos();
		llenarListaPedidos();
		setDatosPedidos();

		

		setEventoMouseClicked(jTableClientes);
		setEventoMouseClicked(jTablePedidos);

	}

	public void setModeloTablaMesas() {
		String cabeceras[] = { "ID", "Codigo Mesa", "Estado", "Restaurante" };
		modeloMesas.setColumnIdentifiers(cabeceras);
		jTableMesas.setModel(modeloMesas);
	}

	public void llenarListaMesas() throws RemoteException, NotBoundException {
		ControlMesa cm = new ControlMesa();
		listaMesas = (ArrayList<Mesa>) cm.loadAll();
	}

	public void setDatosMesas() {
		Object[] mesas = new Object[modeloMesas.getColumnCount()];
		for (Mesa mesa : listaMesas) {
			mesas[0] = mesa.getId();
			mesas[1] = mesa.getCodigo_mesa();
			mesas[2] = mesa.getEstado();
			mesas[3] = mesa.getNombre_restaurante();
			modeloMesas.addRow(mesas);
		}
		jTableMesas.setModel(modeloMesas);
	}

	public void setModeloTablaClientes() {
		String cabeceras[] = { "Documento", "Nombre", "Teléfono", "E-mail", "Valoración" };
		modeloClientes.setColumnIdentifiers(cabeceras);
		jTableClientes.setModel(modeloClientes);
	}

	public void llenarListaClientes() throws RemoteException, NotBoundException {

		listaClientes = (ArrayList<Cliente>) cc.loadAll();
	}

	public void setDatosClientes() {
		Object[] clientes = new Object[modeloClientes.getColumnCount()];
		for (Cliente cliente : listaClientes) {
			clientes[0] = cliente.getDocumento();
			clientes[1] = cliente.getNombre_completo();
			clientes[2] = cliente.getTelefono();
			clientes[3] = cliente.getEmail();
			clientes[4] = cliente.getValoracion();
			modeloClientes.addRow(clientes);
		}
		jTableClientes.setModel(modeloClientes);
	}

	public void setModeloTablaPedidos() {
		String cabeceras[] = { "Id", "Cliente", "Mesa", "Fecha", "Total" };
		modeloPedidos.setColumnIdentifiers(cabeceras);
		jTablePedidos.setModel(modeloPedidos);
	}

	public void llenarListaPedidos() throws RemoteException, NotBoundException {
		ControlPedido cp = new ControlPedido();
		listaPedidos = (ArrayList<Pedido>) cp.loadAll();
	}

	public void setDatosPedidos() {

		Object[] pedidos = new Object[modeloPedidos.getColumnCount()];

		for (Pedido pedido : listaPedidos) {

			Cliente cliente = new Cliente();
			cliente = cc.searchCliente2(pedido.getId_cliente());

			pedidos[0] = pedido.getId();
			pedidos[1] = cliente.getNombre_completo();
			pedidos[2] = pedido.getId_mesa();
			pedidos[3] = pedido.getFecha();
			pedidos[4] = pedido.getPrecio_total();
			modeloPedidos.addRow(pedidos);
		}
		jTablePedidos.setModel(modeloPedidos);
	}

	public void setModeloTablaPedidosDetalles() {
		String cabeceras[] = { "Id", "Cantidad", "Producto", "Especificación", "Precio" };
		modeloPedidosDetalles.setColumnIdentifiers(cabeceras);
		jTablePedidosDetalles.setModel(modeloPedidosDetalles);
	}

	public void llenarListaPedidosDetalles(int idPedido) throws RemoteException, NotBoundException {
		ControlPedidoDetalle cpd = new ControlPedidoDetalle();
		listaPedidosDetalles = (ArrayList<Pedido_detalle>) cpd.loadAllId(idPedido);
		System.out.println("PRUEBA"+idPedido);
		
	}

	public void setDatosPedidosDetalles() {
		Object[] pedidos = new Object[modeloPedidosDetalles.getColumnCount()];
		
		for (Pedido_detalle pedidoDetalle : listaPedidosDetalles) {
			pedidos[0] = pedidoDetalle.getId_pedido();
			pedidos[1] = pedidoDetalle.getCantidad();
			pedidos[2] = pedidoDetalle.getId_producto();
			pedidos[3] = pedidoDetalle.getEspecificaciones();
			pedidos[4] = pedidoDetalle.getPrecio();
			modeloPedidosDetalles.addRow(pedidos);
		}
		jTablePedidosDetalles.setModel(modeloPedidosDetalles);
	}

	private void setEventoMouseClicked(JTable tbl) {
		tbl.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				jTableClientesMouseClicked(e);
				try {
					jTablePedidosMouseClicked(e);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	
	

	private void jTableClientesMouseClicked(java.awt.event.MouseEvent evt) {
		int fila = jTableClientes.rowAtPoint(evt.getPoint());
		if (fila < 0) {
			JOptionPane.showMessageDialog(null, "Selecciona una fila");
		} else {
			String documento = jTableClientes.getValueAt(fila, 0).toString();
			String nombre = jTableClientes.getValueAt(fila, 1).toString();

			jTextFieldDocumento.setText(documento);
			jLabelNombre.setText(nombre);
		}
	}
	
	private void jTablePedidosMouseClicked(java.awt.event.MouseEvent evt) throws RemoteException, NotBoundException {
		int fila = jTableClientes.rowAtPoint(evt.getPoint());
		if (fila < 0) {
			JOptionPane.showMessageDialog(null, "Selecciona una fila");
		} else {
			
			String idPedido1 = jTablePedidos.getValueAt(fila, 0).toString();
			int idPedido = Integer.parseInt(idPedido1);
			
			ControlPedido cp = new ControlPedido();
			Pedido pedido = new Pedido();
			pedido = cp.searchPedidoId(idPedido);
			System.out.println(pedido);
			
			setModeloTablaPedidosDetalles();
			modeloPedidosDetalles.setRowCount(0);
			try {
				llenarListaPedidosDetalles(pedido.getId());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setDatosPedidosDetalles();
			jTablePedidosDetalles.setEnabled(false);
		}
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		grupoBotones = new javax.swing.ButtonGroup();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTableMesas = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTablePedidos = new javax.swing.JTable();
		jScrollPane3 = new javax.swing.JScrollPane();
		jTablePedidosDetalles = new javax.swing.JTable();
		jLabel3 = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane4 = new javax.swing.JScrollPane();
		jTableClientes = new javax.swing.JTable();
		jButton1 = new javax.swing.JButton();
		jTextFieldDocumento = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jButtonValorar = new javax.swing.JButton();
		uno = new javax.swing.JRadioButton();
		dos = new javax.swing.JRadioButton();
		tres = new javax.swing.JRadioButton();
		cuatro = new javax.swing.JRadioButton();
		cinco = new javax.swing.JRadioButton();

		uno.setActionCommand("1");
		dos.setActionCommand("2");
		tres.setActionCommand("3");
		cuatro.setActionCommand("4");
		cinco.setActionCommand("5");
		jLabelNombre = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jTableMesas.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Mesa", "Estado" }));
		jScrollPane1.setViewportView(jTableMesas);

		jLabel1.setText("Estado de las mesas");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel1).addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE, 412,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(539, Short.MAX_VALUE)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(48, 48, 48).addComponent(jLabel1)
								.addGap(18, 18, 18).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
										128, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(415, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Mesas", jPanel1);

		jLabel2.setText("Últimos pedidos");

		jTablePedidos.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Cliente", "Mesa", "Fecha", "Total" }));
		jScrollPane2.setViewportView(jTablePedidos);

		jTablePedidosDetalles.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } },
				new String[] { "Cantidad", "Producto", "Especificación", "Precio" }));
		jScrollPane3.setViewportView(jTablePedidosDetalles);

		jLabel3.setText("Detalle del pedido");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 388,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2))
						.addGap(45, 45, 45)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel2Layout.createSequentialGroup().addComponent(jLabel3).addGap(0, 0,
										Short.MAX_VALUE))
								.addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE))
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(31, 31, 31)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(jLabel3))
						.addGap(34, 34, 34)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jScrollPane3).addComponent(jScrollPane2))
						.addContainerGap(113, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Pedidos", jPanel2);

		jTableClientes.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "Documento", "Nombre", "Teléfono", "E - Mail" }));
		jScrollPane4.setViewportView(jTableClientes);

		jButton1.setText("Buscar");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jTextFieldDocumento.setText("jTextFieldDocumento");

		jLabel4.setText("Documento:");

		jLabel5.setText("Nombre");

		jLabel7.setText("Puntaje");

		jButtonValorar.setText("Valorar");
		jButtonValorar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonValorarActionPerformed(evt);
			}
		});

		uno.setText("1");

		dos.setText("2");
		dos.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dosActionPerformed(evt);
			}
		});

		tres.setText("3");

		cuatro.setText("4");

		cinco.setText("5");

		jLabelNombre.setText("jLabelNombre");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addGap(40, 40, 40)
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(jPanel3Layout.createSequentialGroup().addComponent(jLabel4)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jTextFieldDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 107,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton1)))
						.addGap(48, 48, 48)
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel3Layout.createSequentialGroup()
										.addGroup(jPanel3Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel5).addComponent(jLabel7))
										.addGap(48, 48, 48)
										.addGroup(jPanel3Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(jPanel3Layout.createSequentialGroup().addComponent(uno)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(dos)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(tres)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(cuatro)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(cinco))
												.addGroup(jPanel3Layout.createSequentialGroup().addComponent(jLabel6)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jLabelNombre,
																javax.swing.GroupLayout.PREFERRED_SIZE, 184,
																javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addComponent(jButtonValorar))
						.addContainerGap(139, Short.MAX_VALUE)));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel3Layout.createSequentialGroup().addGap(53, 53, 53).addGroup(jPanel3Layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton1)
								.addComponent(jTextFieldDocumento, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel4))
								.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel3Layout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 371,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel3Layout.createSequentialGroup().addGap(27, 27, 27)
												.addGroup(jPanel3Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5).addComponent(jLabel6)
														.addComponent(jLabelNombre))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(jPanel3Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel7).addComponent(uno).addComponent(dos)
														.addComponent(tres).addComponent(cuatro).addComponent(cinco))
												.addGap(26, 26, 26).addComponent(jButtonValorar)))
								.addContainerGap(163, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Valoración Cliente", jPanel3);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(jTabbedPane1).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(jTabbedPane1).addContainerGap()));

		jTabbedPane1.getAccessibleContext().setAccessibleName("mesaTab");
		jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

		pack();
	}// </editor-fold>

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButtonValorarActionPerformed(java.awt.event.ActionEvent evt) {

		String valor = grupoBotones.getSelection().getActionCommand();
		int valoracion = Integer.parseInt(valor);
		System.out.println(valor);
		c.setValoracion(valoracion);
		String documento = jTextFieldDocumento.getText();
		c.setDocumento(documento);
		if (cc.updateValoracionCliente(c)) {
			
			modeloClientes.setRowCount(0);

			try {
				llenarListaClientes();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
			setDatosClientes();
			jTableClientes.setEnabled(false);

		}

	}

	private void dosActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ClienteVista().setVisible(true);
				} catch (RemoteException | NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	// Variables declaration - do not modify

	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButtonValorar;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabelNombre;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTable jTableMesas;
	private javax.swing.JTable jTablePedidos;
	private javax.swing.JTable jTablePedidosDetalles;
	private javax.swing.JTable jTableClientes;
	private javax.swing.JTextField jTextFieldDocumento;
	private javax.swing.ButtonGroup grupoBotones;
	private javax.swing.JRadioButton uno;
	private javax.swing.JRadioButton dos;
	private javax.swing.JRadioButton tres;
	private javax.swing.JRadioButton cuatro;
	private javax.swing.JRadioButton cinco;

	// End of variables declaration
}
