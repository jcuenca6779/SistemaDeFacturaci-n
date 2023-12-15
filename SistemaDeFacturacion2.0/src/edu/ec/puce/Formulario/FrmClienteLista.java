package edu.ec.puce.Formulario;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import edu.ec.puce.Dominio.Cliente;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrmClienteLista extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;

	List<Cliente>listaClientes;
	public FrmClienteLista(List<Cliente> clientelist) {
		this.listaClientes=clientelist;
		setBounds(100, 100, 628, 376);
		getContentPane().setLayout(null);
		
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 825, 684);
		getContentPane().add(contentPane);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelar.setBounds(261, 298, 96, 23);
		contentPane.add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 595, 263);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00E9dula", "Nombres", "Apellidos", "Direccion", "Telefono", "Email"
			}
		));
		scrollPane.setViewportView(table);
		model=(DefaultTableModel)table.getModel();
		llenar();
	}

	private void agregarFila() {
		for (Cliente cliente : listaClientes) {
		Object[] fila=new Object[6];
		fila[0]=cliente.getCedula();
		fila[1]=cliente.getNombres();
		fila[2]=cliente.getApellidos();
		fila[3]=cliente.getDireccion();
		fila[4]=cliente.getTelefono();
		fila[5]=cliente.getEmail();
		model.addRow(fila);
		}
	}
	public void llenar() {
		model.setRowCount(0);
		agregarFila();
	}
	
	public void cerrarVentana() {
		this.dispose();
	}
	public DefaultTableModel getModeloTabla() {
	    return model;
	}
}
