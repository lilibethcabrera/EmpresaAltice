package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

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
import logico.Factura;
import logico.Plan;
import visual.RegCliente;
import logico.Altice;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class PlanesCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private static Object[] fila;
	private JTable table;
	private JButton btnCancelar;
	private JButton btnFacturar;
	private JButton btnEliminar;
	private String selecte = "";
	private static Cliente miCliente;

	public PlanesCliente(Cliente cliente) {
		miCliente = cliente;
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListPlanes.class.getResource("/Imagenes/bill(1).png")));
		getContentPane().setBackground(SystemColor.activeCaptionBorder);
		setTitle("Planes Actuales");
		setBounds(100, 100, 592, 357);
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
							btnFacturar.setEnabled(true);
							btnEliminar.setEnabled(true);

							selecte = table.getValueAt(index, 0).toString();
						}
					}
				});
				model = new DefaultTableModel();
				String[] columnNames = {"Id","Precio de Apertura","Mensualidad","Velocidad Internet","Minutos de Voz", "Cantidad de Canales", "Ultimo mes Facturado"};
				model.setColumnIdentifiers(columnNames);
				table.setModel(model);
				scrollPane.setViewportView(table);
				loadPlanes();
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.activeCaptionBorder);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnFacturar = new JButton("Facturar");
				btnFacturar.setEnabled(false);
				btnFacturar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Plan plan = miCliente.buscarPlanPorId(selecte);
						int mesActual = Calendar.getInstance().get(Calendar.MONTH) + 1;
						if(plan.getUltimoMesFacturado() ==  mesActual) {
							JOptionPane.showMessageDialog(null, "Ese plan ya fue facturado este mes", "Error!", JOptionPane.ERROR_MESSAGE);

						}else {
							int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea facturar el plan de Id: " + plan.getId(),"Información",JOptionPane.WARNING_MESSAGE);
							  if(option == JOptionPane.OK_OPTION){
								Factura factura = Altice.getInstance().facturar(plan, miCliente);
								miCliente.agregarFactura(factura);
								JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
								loadPlanes();
							    btnEliminar.setEnabled(false);
							    btnFacturar.setEnabled(false);
							    selecte = "";
						}
						}
						
					
						
					}
				});
				buttonPane.add(btnFacturar);
			}
			{
				btnEliminar = new JButton("Cancelar Suscripcion");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							Plan plan = Altice.getInstance().buscarPlanPorId(selecte);
							 int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea cancelar el plan de Id: " + plan.getId(),"Información",JOptionPane.WARNING_MESSAGE);
							  if(option == JOptionPane.OK_OPTION){
								miCliente.getMisPlanes().remove(miCliente.indicePlanPorId(selecte));
								JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
								loadPlanes();
							    btnEliminar.setEnabled(false);
							    btnFacturar.setEnabled(false);
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
		loadPlanes();
	}
	 //{"Id","Precio de Apertura","Mensualidad","Velocidad Internet","Minutos de Voz", "Cantidad de Canales"};
	public static void loadPlanes() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for(int i = 0; i < miCliente.getMisPlanes().size();i++) {
			fila[0] = miCliente.getMisPlanes().get(i).getId();
			fila[1] = miCliente.getMisPlanes().get(i).getPrecio_apertura();
			fila[2] = miCliente.getMisPlanes().get(i).getMensualidad();
			fila[3] = miCliente.getMisPlanes().get(i).getVelocidad_internet();
			fila[4] = miCliente.getMisPlanes().get(i).getMinutos();
			fila[5] = miCliente.getMisPlanes().get(i).getCanales();
			fila[6] = miCliente.getMisPlanes().get(i).getUltimoMesFacturado();
		model.addRow(fila);
		}
	}
	

}
