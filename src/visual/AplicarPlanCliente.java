package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Cliente;
import logico.Factura;
import logico.Plan;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class AplicarPlanCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private static Object[] fila;
	private JTable table;
	private JTextField txtBuscarCedula;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtId;
	private JTextField txtFecha;
	private JTextField txtMonto;
	private Cliente miCliente;
	private Plan miPlan;
	private Factura miFactura;
	private String selecte = "";
	private JButton btnRealizar = new JButton("Realizar");

	public AplicarPlanCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AplicarPlanCliente.class.getResource("/Imagenes/bill(1).png")));
		setTitle("Suscribir Cliente a Plan");
		setBounds(100, 100, 678, 583);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaptionBorder);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelCliente = new JPanel();
		panelCliente.setBackground(SystemColor.activeCaptionBorder);
		panelCliente.setBorder(new TitledBorder(null, "Datos Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCliente.setBounds(10, 75, 642, 102);
		contentPanel.add(panelCliente);
		panelCliente.setLayout(null);
		
		JLabel lblCedula = new JLabel("Cedula: ");
		lblCedula.setBounds(10, 22, 57, 14);
		panelCliente.add(lblCedula);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(10, 47, 57, 14);
		panelCliente.add(lblNombre);
		
		JLabel lblDireccion = new JLabel("Direccion: ");
		lblDireccion.setBounds(10, 72, 69, 14);
		panelCliente.add(lblDireccion);
		
		txtCedula = new JTextField();
		txtCedula.setEditable(false);
		txtCedula.setBounds(77, 19, 241, 20);
		panelCliente.add(txtCedula);
		txtCedula.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setText("");
		txtNombre.setBounds(77, 44, 311, 20);
		panelCliente.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setBounds(77, 69, 332, 20);
		panelCliente.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JPanel panelBuscar = new JPanel();
		panelBuscar.setBackground(SystemColor.activeCaptionBorder);
		panelBuscar.setBorder(new TitledBorder(null, "Buscar Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBuscar.setBounds(10, 11, 642, 53);
		contentPanel.add(panelBuscar);
		panelBuscar.setLayout(null);
		
		JLabel lblBuscar = new JLabel("Cedula:");
		lblBuscar.setBounds(10, 25, 46, 14);
		panelBuscar.add(lblBuscar);
		
		txtBuscarCedula = new JTextField();
		txtBuscarCedula.setBounds(55, 22, 314, 20);
		panelBuscar.add(txtBuscarCedula);
		txtBuscarCedula.setColumns(10);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(AplicarPlanCliente.class.getResource("/Imagenes/magnifier(1).png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				miCliente = Altice.getInstance().buscarClientePorCedula(txtBuscarCedula.getText());
				if(miCliente == null) {
					JOptionPane.showMessageDialog(null, "La cedula digitada no aplica a ningun cliente existente", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					loadPlanes();
					txtCedula.setText(miCliente.getCedula());
					txtNombre.setText(miCliente.getNombre());
					txtDireccion.setText(miCliente.getDireccion());	
				}
			}
		});
		btnBuscar.setBounds(379, 19, 33, 23);
		panelBuscar.add(btnBuscar);
		
		JPanel panelDatosFactura = new JPanel();
		panelDatosFactura.setBackground(SystemColor.activeCaptionBorder);
		panelDatosFactura.setBorder(new TitledBorder(null, "Datos Factura", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatosFactura.setBounds(10, 394, 642, 102);
		contentPanel.add(panelDatosFactura);
		panelDatosFactura.setLayout(null);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 23, 46, 14);
		panelDatosFactura.add(lblId);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(66, 20, 86, 20);
		panelDatosFactura.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(233, 23, 46, 14);
		panelDatosFactura.add(lblFecha);
		
		txtFecha = new JTextField();
	
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		txtFecha.setText(formatter.format(new Date()));
		txtFecha.setEditable(false);
		txtFecha.setBounds(289, 20, 125, 20);
		panelDatosFactura.add(txtFecha);
		txtFecha.setColumns(10);
		
		JLabel lblMonto = new JLabel("Monto a Pagar:");
		lblMonto.setBounds(10, 59, 86, 14);
		panelDatosFactura.add(lblMonto);
		
		txtMonto = new JTextField();
		txtMonto.setEditable(false);
		txtMonto.setBounds(106, 56, 114, 20);
		panelDatosFactura.add(txtMonto);
		txtMonto.setColumns(10);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 188, 642, 195);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						if(index >= 0) {
							selecte = table.getValueAt(index, 0).toString();
							btnRealizar.setEnabled(true);
							miPlan = Altice.getInstance().buscarPlanPorId(selecte);
							txtId.setText(miPlan.getId());
							txtMonto.setText(Float.toString(miPlan.getPrecio_apertura()));
						}
					}
				});
				model = new DefaultTableModel();
				String[] columnNames = {"Id","Precio de Apertura","Mensualidad","Velocidad Internet","Minutos de Voz", "Cantidad de Canales"};
				model.setColumnIdentifiers(columnNames);
				table.setModel(model);
				scrollPane.setViewportView(table);

			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.activeCaptionBorder);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRealizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea registrar el plan no.: " + miPlan.getId(),"Información",JOptionPane.WARNING_MESSAGE);
						  if(option == JOptionPane.OK_OPTION){
							String id_actual;
							int cant_facturas = Altice.getInstance().getMisFacturas().size();
							if(cant_facturas == 0) {
								id_actual = "0";
							}else {
								String id_ultimo = Altice.getInstance().getMisFacturas().get(cant_facturas - 1).getId();
								id_actual = Integer.toString(Integer.parseInt(id_ultimo) + 1);
							}
							
							
							Date fecha = new Date();
							try {
								fecha = new SimpleDateFormat("dd/MM/yyyy").parse(txtFecha.getText());
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							miFactura = new Factura(id_actual, fecha, Float.parseFloat(txtMonto.getText()),miPlan,miCliente, true); 
							JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
							Altice.getInstance().agregarPlanCliente(miPlan, miCliente.getCedula());
							Altice.getInstance().agregarFactura(miFactura);
							Altice.getInstance().agregarFacturaCliente(miCliente.getCedula(), miFactura);
							clean();
						    selecte = "";
					}
					}
				});
				btnRealizar.setEnabled(false);
				btnRealizar.setActionCommand("OK");
				buttonPane.add(btnRealizar);
				getRootPane().setDefaultButton(btnRealizar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
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
		txtBuscarCedula.setText("");
		txtCedula.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		txtMonto.setText("");
		txtId.setText("");
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		btnRealizar.setEnabled(false);
		
	}
	public static void loadPlanes() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for(int i = 0; i < Altice.getInstance().getMisPlanes().size();i++) {
			fila[0] = Altice.getInstance().getMisPlanes().get(i).getId();
			fila[1] = Altice.getInstance().getMisPlanes().get(i).getPrecio_apertura();
			fila[2] = Altice.getInstance().getMisPlanes().get(i).getMensualidad();
			fila[3] = Altice.getInstance().getMisPlanes().get(i).getVelocidad_internet();
			fila[4] = Altice.getInstance().getMisPlanes().get(i).getMinutos();
			fila[5] = Altice.getInstance().getMisPlanes().get(i).getCanales();
		model.addRow(fila);
		}
	}
}
