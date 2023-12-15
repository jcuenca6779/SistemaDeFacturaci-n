package edu.ec.puce.Formulario;


import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.ec.puce.Dominio.Producto;

public class FrmFacturar extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblCedula;
	private JLabel lblNombres;
	private JLabel lblTelfono;
	private JLabel lblApellidos;
	private JLabel lblEmail;
	private JLabel lblDireccin;
	private JTable table;
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	private DefaultTableModel model;
	private JLabel lblIVA;
	private JLabel lblTotal;
	private JLabel lblSubtotal;


	public FrmFacturar() {
		setTitle("Factura");
		setBounds(100, 100, 590, 498);
		getContentPane().setLayout(null);
		
		JLabel lblCliente = new JLabel("CLIENTE");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCliente.setBounds(27, 11, 78, 27);
		getContentPane().add(lblCliente);
		
		JButton btnSeleccionarCliente = new JButton("Seleccionar cliente");
		btnSeleccionarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pedirDatos();
			}
		});
		btnSeleccionarCliente.setBounds(296, 13, 145, 28);
		getContentPane().add(btnSeleccionarCliente);
		
		lblCedula = new JLabel("Cedula:");
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCedula.setBounds(37, 53, 242, 27);
		getContentPane().add(lblCedula);
		
		lblNombres = new JLabel("Nombres:");
		lblNombres.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombres.setBounds(306, 53, 242, 27);
		getContentPane().add(lblNombres);
		
		lblTelfono = new JLabel("Teléfono:");
		lblTelfono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTelfono.setBounds(306, 93, 242, 27);
		getContentPane().add(lblTelfono);
		
		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApellidos.setBounds(37, 93, 242, 27);
		getContentPane().add(lblApellidos);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(37, 127, 242, 27);
		getContentPane().add(lblEmail);
		
		lblDireccin = new JLabel("Dirección:");
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDireccin.setBounds(306, 127, 242, 27);
		getContentPane().add(lblDireccin);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(459, 435, 89, 23);
		getContentPane().add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 191, 511, 141);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Descripci\u00F3n", "Precio", "Cantidad", "Total"
			}
		));
		scrollPane.setViewportView(table);
		model = (DefaultTableModel) table.getModel();
		
		lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSubtotal.setBounds(175, 343, 169, 14);
		getContentPane().add(lblSubtotal);
		
		lblIVA = new JLabel("IVA: ");
		lblIVA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIVA.setBounds(175, 368, 169, 14);
		getContentPane().add(lblIVA);
		
		lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotal.setBounds(175, 393, 169, 14);
		getContentPane().add(lblTotal);
		
		JButton btnEliminarProducto = new JButton("Eliminar Producto");
		btnEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarProducto();
			}
		});
		btnEliminarProducto.setBounds(397, 165, 151, 23);
		getContentPane().add(btnEliminarProducto);
		
		JButton btnAgregarProductos = new JButton("Agregar Producto");
		btnAgregarProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducto();
			}
		});
		btnAgregarProductos.setBounds(242, 165, 145, 23);
		getContentPane().add(btnAgregarProductos);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirDeNuevo();
			}
		});
		btnNuevo.setBounds(10, 435, 89, 23);
		getContentPane().add(btnNuevo);

	}
	private void pedirDatos() {
		ListaClientes listaCliente = new ListaClientes(new JDialog(), true, lblCedula, lblNombres, lblApellidos, lblTelfono, lblEmail, lblDireccin);
		listaCliente.setVisible(true);
	}
	
	private void agregarProducto() {
	    ListaProductos listaProductos = new ListaProductos(new JDialog(), true, this.model, this.productos, lblApellidos, lblApellidos, lblApellidos);
	    listaProductos.setVisible(true);
	    
	    // Actualizar los valores de subtotal, IVA y total después de agregar un producto
	    double nuevoSubtotal = obtenerNuevoSubtotal();
	    double nuevoIVA = calcularIVA(nuevoSubtotal);
	    double nuevoTotal = calcularTotal(nuevoSubtotal, nuevoIVA);

	    lblSubtotal.setText("Subtotal: " + nuevoSubtotal);
	    lblIVA.setText("IVA: " + nuevoIVA);
	    lblTotal.setText("Total: " + nuevoTotal);
	}

	private void eliminarProducto() {
	    EliminarProducto eliminarProducto = new EliminarProducto(new JDialog(), true, this.model, this.productos, lblApellidos, lblApellidos, lblApellidos);
	    eliminarProducto.setVisible(true);
	    
	    double nuevoSubtotal = obtenerNuevoSubtotal();
	    double nuevoIVA = calcularIVA(nuevoSubtotal);
	    double nuevoTotal = calcularTotal(nuevoSubtotal, nuevoIVA);

	    lblSubtotal.setText("Subtotal: " + nuevoSubtotal);
	    lblIVA.setText("IVA: " + nuevoIVA);
	    lblTotal.setText("Total: " + nuevoTotal);
	}

	private double obtenerNuevoSubtotal() {
		 double subtotal = 0.0;

		    for (Producto producto : productos) {
		        double precio = producto.getPrecio();
		        int cantidad = producto.getCantidad();
		        subtotal += precio * cantidad;
		    }

		    return subtotal;
	}

	private double calcularIVA(double subtotal) {
		double porcentajeIVA = 0.12; 
	    return subtotal * porcentajeIVA;
	}

	private double calcularTotal(double subtotal, double iva) {
		return subtotal + iva;
	}

	private void abrirDeNuevo() {
		Container menuPrincipal = this.getParent();
		this.dispose();
		FrmFacturar frmFacturar = new FrmFacturar();
		frmFacturar.setVisible(true);
		menuPrincipal.add(frmFacturar);
	}
}