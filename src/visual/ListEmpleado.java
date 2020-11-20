package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.Comercial;
import logico.Empleado;
import visual.RegCliente;
import logico.Administrativo;
import logico.Altice;

public class ListEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private static Object[] fila;
	private JTable table;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private String selecte = "";

	public ListEmpleado() {
		setTitle("Empleados Actuales");
		setBounds(100, 100, 557, 357);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						if(index >= 0) {
							btnModificar.setEnabled(true);
							btnEliminar.setEnabled(true);

							selecte = table.getValueAt(index, 0).toString();
						}
					}
				});
				model = new DefaultTableModel();
				String[] columnNames = {"Cedula","Nombre","Direccion","Telefono","Salario","Tipo", "Puesto/Zona"};
				model.setColumnIdentifiers(columnNames);
				table.setModel(model);
				scrollPane.setViewportView(table);
				loadEmpleados();
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					Empleado empleado = Altice.getInstance().buscarEmpleadoPorCedula(selecte);
					RegEmpleado modClient = new RegEmpleado(empleado);
					modClient.setModal(true);
					modClient.setVisible(true);
						
					}
				});
				buttonPane.add(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							Empleado empleado = Altice.getInstance().buscarEmpleadoPorCedula(selecte);
							 int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el Empleado: " + empleado.getNombre(),"Información",JOptionPane.WARNING_MESSAGE);
							  if(option == JOptionPane.OK_OPTION){
								Altice.getInstance().getMisEmpleados().remove(Altice.getInstance().indiceEmpleadoPorCedula(selecte));
								JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
								loadEmpleados();
							    btnEliminar.setEnabled(false);
							    btnModificar.setEnabled(false);
							    selecte = "";
						}
						
					}
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
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
		loadEmpleados();
	}
	public static void loadEmpleados() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		
		for(Empleado empleado : Altice.getInstance().getMisEmpleados()) {
			fila[0] = empleado.getCedula();
			fila[1] = empleado.getNombre();
			fila[2] = empleado.getDireccion();
			fila[3] = empleado.getTelefono();
			fila[4] = empleado.getSalario();
			
			if(empleado instanceof Administrativo) {
				fila[5] = "Administrativo";
				fila[6] = ((Administrativo) empleado).getPuesto();
			}
			else {
				fila[5] = "Comercial";
				fila[6] = ((Comercial) empleado).getZona();
			}
			model.addRow(fila);
		}
		
		
	}
	

}
