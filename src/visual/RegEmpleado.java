package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import logico.Administrativo;
import logico.Altice;
import logico.Cliente;
import logico.Comercial;
import logico.Empleado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class RegEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private Empleado miEmpleado;
	private JSpinner spnFechaNacimiento = new JSpinner();
	private JTextField txtTelefono;
	private JTextField txtFechaContratacion;
	private JComboBox cbxZona = new JComboBox();
	private JComboBox cbxPuesto = new JComboBox();
	private JRadioButton rdbtnAdministrativo = new JRadioButton("Administrativo");
	private JRadioButton rdbtnComercial = new JRadioButton("Comercial");
	private JSpinner spnSalario = new JSpinner();
	
	private String cedulaVieja; // En caso de que cambie al modificar
	

	public RegEmpleado(Empleado empleado) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegEmpleado.class.getResource("/Imagenes/value(2).png")));
		getContentPane().setBackground(SystemColor.activeCaptionBorder);
		if(empleado == null) {
			setTitle("Registrar Empleado");
		}else {
			setTitle("Modificar Empleado");
			cedulaVieja = empleado.getCedula();
		}
		miEmpleado = empleado;
		setBounds(100, 100, 548, 313);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaptionBorder);
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
		lblFechaDeNacimiento.setBounds(10, 138, 124, 14);
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
		spnFechaNacimiento.setBounds(133, 135, 107, 20);
		contentPanel.add(spnFechaNacimiento);
		
		JLabel lblNumeroTelefnico = new JLabel("Numero Telef\u00F3nico");
		lblNumeroTelefnico.setBounds(10, 110, 107, 14);
		contentPanel.add(lblNumeroTelefnico);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 107, 143, 20);
		contentPanel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblSalario = new JLabel("Salario:");
		lblSalario.setBounds(287, 138, 76, 14);
		contentPanel.add(lblSalario);
		

		spnSalario.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnSalario.setBounds(373, 135, 70, 20);
		contentPanel.add(spnSalario);
		
		JLabel lblFechaContratacion = new JLabel("Fecha Contratacion: ");
		lblFechaContratacion.setBounds(253, 35, 107, 14);
		contentPanel.add(lblFechaContratacion);
		
		txtFechaContratacion = new JTextField();
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		txtFechaContratacion.setText(formatter.format(new Date()));
		
		txtFechaContratacion.setEditable(false);
		txtFechaContratacion.setBounds(370, 32, 86, 20);
		contentPanel.add(txtFechaContratacion);
		txtFechaContratacion.setColumns(10);
		
		JLabel lblTipoEmpleado = new JLabel("Tipo Empleado:");
		lblTipoEmpleado.setBounds(10, 163, 107, 20);
		contentPanel.add(lblTipoEmpleado);
		rdbtnAdministrativo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnComercial.setSelected(false);
				cbxPuesto.setEnabled(true);
				cbxZona.setEnabled(false);
				
			}
		});
		
		
		rdbtnAdministrativo.setSelected(true);
		rdbtnAdministrativo.setBounds(133, 162, 109, 23);
		contentPanel.add(rdbtnAdministrativo);
		rdbtnComercial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAdministrativo.setSelected(false);
				cbxPuesto.setEnabled(false);
				cbxZona.setEnabled(true);
			}
			
		});
		
		rdbtnComercial.setBounds(287, 162, 109, 23);
		contentPanel.add(rdbtnComercial);
		
		JLabel lblPuesto = new JLabel("Puesto: ");
		lblPuesto.setBounds(10, 194, 65, 14);
		contentPanel.add(lblPuesto);
		
		JLabel lblZona = new JLabel("Zona:");
		lblZona.setBounds(253, 194, 65, 14);
		contentPanel.add(lblZona);
		

		cbxPuesto.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Gerente", "Encargado Zona", "Finanzas", "Recursos Humanos"}));
		cbxPuesto.setBounds(96, 194, 124, 20);
		contentPanel.add(cbxPuesto);
		
		
		cbxZona.setEnabled(false);
		cbxZona.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Norte", "Sur", "Este", "Oeste"}));
		cbxZona.setBounds(297, 192, 107, 20);
		contentPanel.add(cbxZona);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.activeCaptionBorder);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtCedula.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtNombre.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Debe llenar los campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							if(rdbtnAdministrativo.isSelected()) {
								if(cbxPuesto.getSelectedIndex() == 0) {
									JOptionPane.showMessageDialog(null, "Seleccione un puesto", "Error", JOptionPane.ERROR_MESSAGE);
								}else {
									if(empleado == null) {
										Altice.getInstance().agregarEmpleado(administrativo());										
										clean();
									}else {
										Altice.getInstance().modificarEmpleado(administrativo(), cedulaVieja);
										ListEmpleado.loadEmpleados();
										JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
										dispose();
									}
								}
							}
							else {
								if(cbxZona.getSelectedIndex() == 0) {
									JOptionPane.showMessageDialog(null, "Seleccione una zona", "Error", JOptionPane.ERROR_MESSAGE);
								}else {
									if(empleado == null) {
										Altice.getInstance().agregarEmpleado(comercial());
										
										clean();
									}else {
										Altice.getInstance().modificarEmpleado(comercial(), cedulaVieja);
										ListEmpleado.loadEmpleados();
										JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
										dispose();
									}
								}

							}	
							JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
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
			if(miEmpleado != null) {
				loadEmpleado();
			}
		}
	}
	private void loadEmpleado() {
		txtCedula.setText(miEmpleado.getCedula());
		txtDireccion.setText(miEmpleado.getDireccion());
		txtNombre.setText(miEmpleado.getNombre());
		txtTelefono.setText(miEmpleado.getTelefono());
		spnSalario.setValue(miEmpleado.getSalario());
		
		if(miEmpleado instanceof Administrativo) {
			rdbtnAdministrativo.setSelected(true);
			rdbtnComercial.setSelected(false);
			
			cbxPuesto.setEnabled(true);
			cbxZona.setEnabled(false);
			cbxPuesto.setSelectedItem(((Administrativo) miEmpleado).getPuesto());
			
		}else {
			rdbtnAdministrativo.setSelected(false);
			rdbtnComercial.setSelected(true);
			
			cbxPuesto.setEnabled(false);
			cbxZona.setEnabled(true);
			cbxZona.setSelectedItem(((Comercial) miEmpleado).getZona());
		}
		
		SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
		spnFechaNacimiento.setModel(new SpinnerDateModel());
		spnFechaNacimiento.setEditor(new JSpinner.DateEditor(spnFechaNacimiento, model.toPattern()));
		spnFechaNacimiento.setValue(miEmpleado.getFechaNacimiento());
		
	}
	private Administrativo administrativo() {
		String cedula = txtCedula.getText();
		String nombre = txtNombre.getText();
		String direccion = txtDireccion.getText();
		Date fechaDeContratacion = new Date();
		try {
			fechaDeContratacion = new SimpleDateFormat("dd/MM/yyyy").parse(txtFechaContratacion.getText());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String telefono = txtTelefono.getText();
		float salario = (float) spnSalario.getValue();
		String puesto = cbxPuesto.getSelectedItem().toString();
		
		Date fechaDeNacimiento = (Date) spnFechaNacimiento.getValue();
		Administrativo empleado = new Administrativo(cedula, nombre, direccion, fechaDeNacimiento, fechaDeContratacion, salario, telefono, puesto);
		return empleado;
	}
	private Comercial comercial() {
		Comercial empleado;
		String cedula = txtCedula.getText();
		String nombre = txtNombre.getText();
		String direccion = txtDireccion.getText();
		Date fechaDeContratacion = new Date();
		try {
			fechaDeContratacion = new SimpleDateFormat("dd/MM/yyyy").parse(txtFechaContratacion.getText());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String telefono = txtTelefono.getText();
		float salario = (float) spnSalario.getValue();
		String zona = cbxZona.getSelectedItem().toString();
		
		Date fechaDeNacimiento = (Date) spnFechaNacimiento.getValue();
		empleado = new Comercial(cedula, nombre, direccion, fechaDeNacimiento, fechaDeContratacion, salario, telefono, zona);
		return empleado;
	}
	private void clean() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		
		spnSalario.setValue((float)0);
		
		cbxZona.setSelectedIndex(0);
		cbxZona.setEnabled(false);
		cbxPuesto.setSelectedIndex(0);
		cbxPuesto.setEnabled(true);
		rdbtnAdministrativo.setSelected(true);
		rdbtnComercial.setSelected(false);
		
		SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
		spnFechaNacimiento.setModel(new SpinnerDateModel());
		spnFechaNacimiento.setEditor(new JSpinner.DateEditor(spnFechaNacimiento, model.toPattern()));
		spnFechaNacimiento.setValue(new Date());
		
	}
}
