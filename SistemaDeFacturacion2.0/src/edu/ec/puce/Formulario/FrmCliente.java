package edu.ec.puce.Formulario;



import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import edu.ec.puce.Dominio.Cliente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class FrmCliente extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtCedula;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtDireccion;
	private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private JButton btnGuardar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JTable table;
	private DefaultTableModel model;
	private List<Cliente>listaClientes;
	private boolean bandera = true;

	
	public FrmCliente(List<Cliente> clientelist) {
		this.listaClientes = clientes != null ? clientes : new ArrayList<>();
		setTitle("Agregar Clientes");
		setBounds(100, 100, 450, 500);
		getContentPane().setLayout(null);
		
		JLabel lblCedula = new JLabel("Cédula");
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCedula.setBounds(39, 26, 118, 14);
		getContentPane().add(lblCedula);
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombres.setBounds(39, 65, 88, 14);
		getContentPane().add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApellidos.setBounds(39, 104, 88, 14);
		getContentPane().add(lblApellidos);
		
		JLabel lblTelefono = new JLabel("Télefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTelefono.setBounds(39, 143, 88, 14);
		getContentPane().add(lblTelefono);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(39, 182, 88, 14);
		getContentPane().add(lblEmail);
		
		JLabel lblDireccion = new JLabel("Dirección");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDireccion.setBounds(39, 221, 103, 14);
		getContentPane().add(lblDireccion);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(119, 25, 260, 20);
		getContentPane().add(txtCedula);
		txtCedula.setColumns(10);
		
		txtNombres = new JTextField();
		txtNombres.setColumns(10);
		txtNombres.setBounds(119, 64, 260, 20);
		getContentPane().add(txtNombres);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(119, 103, 260, 20);
		getContentPane().add(txtApellidos);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(119, 142, 260, 20);
		getContentPane().add(txtTelefono);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(119, 181, 260, 20);
		getContentPane().add(txtEmail);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(119, 220, 260, 20);
		getContentPane().add(txtDireccion);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				table.clearSelection();
				bandera=true;
				btnGuardar.setVisible(bandera);
				btnEliminar.setEnabled(false);
				btnActualizar.setVisible(false);
			}
		});
		btnNuevo.setBounds(10, 274, 90, 23);
		getContentPane().add(btnNuevo);
		
		btnGuardar = new JButton("Guardar");
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCliente();
			}
		});
		btnGuardar.setBounds(119, 274, 90, 23);
		getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();
			}
		});
		btnCancelar.setBounds(339, 274, 89, 23);
		getContentPane().add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 308, 408, 140);
		getContentPane().add(scrollPane);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult=JOptionPane.showConfirmDialog(null, "¿Estas seguro?", "Confirme", JOptionPane.YES_NO_OPTION);
				int filaSeleccionada = table.getSelectedRow();
				if(dialogResult==JOptionPane.YES_OPTION) {
					if (filaSeleccionada >= 0) {
						model.removeRow(filaSeleccionada);
						btnActualizar.setVisible(false);
						btnEliminar.setEnabled(false);
						btnGuardar.setVisible(true);
					}
					if (filaSeleccionada < listaClientes.size()) {
						listaClientes.remove(filaSeleccionada);
					}
				}
				else 
					{System.out.println("No");
			}
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(226, 274, 90, 23);
		getContentPane().add(btnEliminar);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada >= 0) {
					String cedula = table.getValueAt(filaSeleccionada, 0).toString();
	                String nombres = table.getValueAt(filaSeleccionada, 1).toString();
	                String apellidos = table.getValueAt(filaSeleccionada, 2).toString();
	                String telefono = table.getValueAt(filaSeleccionada, 3).toString();
	                String email = table.getValueAt(filaSeleccionada, 4).toString();
	                String direccion = table.getValueAt(filaSeleccionada, 5).toString();
	                txtCedula.setText(cedula);
	                txtNombres.setText(nombres);
	                txtApellidos.setText(apellidos);
	                txtTelefono.setText(telefono);
	                txtEmail.setText(email);
	                txtDireccion.setText(direccion);
					bandera=false;
					btnGuardar.setVisible(bandera);
					btnEliminar.setEnabled(true);
					btnActualizar.setVisible(true);
				}
			}
		});
		table.setBackground(new Color(192, 192, 192));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00E9dula", "Nombres", "Apellidos", "Tel\u00E9fono", "Email", "Direcci\u00F3n"
			}
		));
		scrollPane.setViewportView(table);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setVisible(false);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada >= 0) {
				 String cedula = txtCedula.getText();
		            String nombres = txtNombres.getText();
		            String apellidos = txtApellidos.getText();
		            String telefono = txtTelefono.getText();
		            String email = txtEmail.getText();
		            String direccion = txtDireccion.getText();

		         
		            model.setValueAt(cedula, filaSeleccionada, 0);
		            model.setValueAt(nombres, filaSeleccionada, 1);
		            model.setValueAt(apellidos, filaSeleccionada, 2);
		            model.setValueAt(telefono, filaSeleccionada, 3);
		            model.setValueAt(email, filaSeleccionada, 4);
		            model.setValueAt(direccion, filaSeleccionada, 5);
				}
			}
		});
		btnActualizar.setBounds(119, 275, 90, 23);
		getContentPane().add(btnActualizar);
		
		
		model = (DefaultTableModel) table.getModel();

	}
	
	private void limpiarCampos() {
		this.txtCedula.setText("");
		this.txtNombres.setText("");
		this.txtApellidos.setText("");
		this.txtTelefono.setText("");
		this.txtEmail.setText("");
		this.txtDireccion.setText("");
	}
	
	private void cerrarVentana() {
		this.setVisible(false);
	}
	
	private void crearCliente() {
		Cliente cliente = new Cliente(
				this.txtCedula.getText(),
				this.txtNombres.getText(),
				this.txtApellidos.getText(),
				this.txtTelefono.getText(),
				this.txtEmail.getText(),
				this.txtDireccion.getText()
				);
		clientes.add(cliente);
		agregarFila();
		limpiarCampos();
	}
	
	private void agregarFila() {
		Object[] fila=new Object[6];
		fila[0]= clientes.get(clientes.size()-1).getCedula();
		fila[1]= clientes.get(clientes.size()-1).getNombres();
		fila[2]= clientes.get(clientes.size()-1).getApellidos();
		fila[3]= clientes.get(clientes.size()-1).getTelefono();
		fila[4]= clientes.get(clientes.size()-1).getEmail();
		fila[5]= clientes.get(clientes.size()-1).getDireccion();
		this.model.addRow(fila);
		listaClientes.addAll(clientes);
		
	}
	
	public static ArrayList<Cliente> getClientes() {
		return clientes;
	}
}