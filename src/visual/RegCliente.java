package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import logico.Altice;
import logico.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;

public class RegCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private Cliente miCliente;
	private JSpinner spnFechaNacimiento = new JSpinner();
	

	public RegCliente() {
		setTitle("Registrar Empleado");
		setBounds(100, 100, 477, 256);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Datos Generales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCedula = new JLabel("Cedula: ");
		lblCedula.setBounds(10, 35, 65, 14);
		contentPanel.add(lblCedula);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 60, 65, 14);
		contentPanel.add(lblNombre);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n: ");
		lblDireccin.setBounds(10, 85, 76, 14);
		contentPanel.add(lblDireccin);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento: ");
		lblFechaDeNacimiento.setBounds(10, 110, 124, 14);
		contentPanel.add(lblFechaDeNacimiento);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(96, 32, 124, 20);
		contentPanel.add(txtCedula);
		txtCedula.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(96, 57, 278, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(96, 82, 350, 20);
		contentPanel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
		spnFechaNacimiento.setModel(new SpinnerDateModel());
		spnFechaNacimiento.setEditor(new JSpinner.DateEditor(spnFechaNacimiento, model.toPattern()));
		spnFechaNacimiento.setBounds(133, 107, 107, 20);
		contentPanel.add(spnFechaNacimiento);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtCedula.getText().isEmpty() || txtNombre.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Debe llenar los campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							String cedula = txtCedula.getText();
							String nombre = txtNombre.getText();
							String direccion = txtDireccion.getText();
							Date fechaDeNacimiento = (Date) spnFechaNacimiento.getValue();
							Cliente miCliente = new Cliente(cedula,nombre,direccion, fechaDeNacimiento);
							Altice.getInstance().agregarCliente(miCliente);
							JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
							clean();
						}
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	private void clean() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		
	}
}
