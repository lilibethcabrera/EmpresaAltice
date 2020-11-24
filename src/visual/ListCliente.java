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
import visual.RegCliente;
import logico.Altice;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class ListCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private static Object[] fila;
	private JTable table;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private String selecte = "";
	private JButton btnListarFactura;

	public ListCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListCliente.class.getResource("/Imagenes/customer(1).png")));
		setTitle("Clientes Actuales");
		setBounds(100, 100, 557, 357);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaptionBorder);
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
							 btnListarFactura.setEnabled(true);
							if(Altice.getInstance().getMisClientes().get(index).getCantFacturasActivas() == 0) {
								btnEliminar.setEnabled(true);
							}else {
								btnEliminar.setEnabled(false);
							}
							selecte = table.getValueAt(index, 0).toString();
						}
					}
				});
				model = new DefaultTableModel();
				String[] columnNames = {"Cedula","Nombre","Direccion","Facturas Activas","Total Planes"};
				model.setColumnIdentifiers(columnNames);
				table.setModel(model);
				scrollPane.setViewportView(table);
				loadClientes();
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.activeCaptionBorder);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					Cliente client = Altice.getInstance().buscarClientePorCedula(selecte);
					RegCliente modClient = new RegCliente(client);
					modClient.setModal(true);
					modClient.setVisible(true);
						
					}
				});
				{
					btnListarFactura = new JButton("Listar Factura");
					btnListarFactura.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Cliente client = Altice.getInstance().buscarClientePorCedula(selecte);
							ListFacturas list = new ListFacturas(client.getMisFacturas(), client);
							list.setModal(true);
							list.setVisible(true);
						}
					});
					btnListarFactura.setEnabled(false);
					buttonPane.add(btnListarFactura);
				}
				buttonPane.add(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							Cliente cliente = Altice.getInstance().buscarClientePorCedula(selecte);
							 int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el Cliente: " + cliente.getNombre(),"Información",JOptionPane.WARNING_MESSAGE);
							  if(option == JOptionPane.OK_OPTION){
								Altice.getInstance().getMisClientes().remove(Altice.getInstance().indiceClientePorCedula(selecte));
								JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
								loadClientes();
							    btnEliminar.setEnabled(false);
							    btnModificar.setEnabled(false);
							    btnListarFactura.setEnabled(false);
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
		loadClientes();
	}
	public static void loadClientes() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for(int i = 0; i < Altice.getInstance().getMisClientes().size();i++) {
			fila[0] = Altice.getInstance().getMisClientes().get(i).getCedula();
			fila[1] = Altice.getInstance().getMisClientes().get(i).getNombre();
			fila[2] = Altice.getInstance().getMisClientes().get(i).getDireccion();
			fila[3] = Altice.getInstance().getMisClientes().get(i).getCantFacturasActivas();
			fila[4] = Altice.getInstance().getMisClientes().get(i).getMisPlanes().size();
		model.addRow(fila);
		}
	}
	

}
