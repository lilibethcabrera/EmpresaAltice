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
import logico.Plan;
import visual.RegCliente;
import logico.Altice;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class ListPlanes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private static Object[] fila;
	private JTable table;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private String selecte = "";

	public ListPlanes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListPlanes.class.getResource("/Imagenes/bill(1).png")));
		getContentPane().setBackground(SystemColor.activeCaptionBorder);
		setTitle("Planes Actuales");
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
							btnEliminar.setEnabled(true);

							selecte = table.getValueAt(index, 0).toString();
						}
					}
				});
				model = new DefaultTableModel();
				String[] columnNames = {"Id","Precio de Apertura","Mensualidad","Velocidad Internet","Minutos de Voz", "Cantidad de Canales"};
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
				btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					Plan plan = Altice.getInstance().buscarPlanPorId(selecte);
					RegPlan modClient = new RegPlan(plan);
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
							Plan plan = Altice.getInstance().buscarPlanPorId(selecte);
							 int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el plan de Id: " + plan.getId(),"Información",JOptionPane.WARNING_MESSAGE);
							  if(option == JOptionPane.OK_OPTION){
								Altice.getInstance().getMisPlanes().remove(Altice.getInstance().indicePlanPorId(selecte));
								JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
								loadPlanes();
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
		loadPlanes();
	}
	 //{"Id","Precio de Apertura","Mensualidad","Velocidad Internet","Minutos de Voz", "Cantidad de Canales"};
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
